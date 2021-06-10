package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.*;
import uz.cas.controllersestem.entity.enums.ProgressStatus;
import uz.cas.controllersestem.entity.enums.ProjectStatus;
import uz.cas.controllersestem.payload.request.ReqActivePercent;
import uz.cas.controllersestem.payload.request.ReqGetPercent;
import uz.cas.controllersestem.payload.request.ReqProgress;
import uz.cas.controllersestem.repository.CommentRepository;
import uz.cas.controllersestem.repository.ProgressRepository;
import uz.cas.controllersestem.repository.ProjectRepository;
import uz.cas.controllersestem.repository.UsersRepository;

import java.util.*;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<?> addProgress(ReqProgress reqProgress){
        Progress progress = new Progress();
        progress.setPercent(reqProgress.getPercent());
        progress.setProject(projectRepository.findById(reqProgress.getProjectId()).get());
        progress.setUsers(usersRepository.findById(reqProgress.getUserId()).get());
        progress.setStatus(ProgressStatus.start);
        progressRepository.save(progress);
        return ResponseEntity.status(200).body("Malumot saqlandi");
    }

    public ResponseEntity<?> editProgress(UUID id, ReqProgress reqProgress){
        Optional<Progress> byId = progressRepository.findById(id);
        if (byId.isPresent()){
            Progress progress = byId.get();
            progress.setUsers(usersRepository.findById(reqProgress.getUserId()).get());
            progress.setProject(projectRepository.findById(reqProgress.getProjectId()).get());
            progress.setPercent(reqProgress.getPercent());
            progressRepository.save(progress);
            return ResponseEntity.status(200).body("Malumot o'zgartirildi");
        }
        return ResponseEntity.status(500).body("Bunaqa malumot topilmadi");
    }

    public ResponseEntity<?> progressActive(UUID id, ReqActivePercent reqActivePercent){
        List<Progress> byStatus = progressRepository.findByStatusAndProjectAndUsers(ProgressStatus.active,
                projectRepository.findById(reqActivePercent.getProjectId()).get(),
                usersRepository.findById(reqActivePercent.getUserId()).get());
        for (Progress status : byStatus) {
            status.setStatus(ProgressStatus.finish);
            progressRepository.save(status);
        }
        Optional<Progress> byId = progressRepository.findById(id);
        Progress progress = byId.get();
        progress.setStatus(ProgressStatus.active);
        progressRepository.save(progress);
        List<Progress> progressList = progressRepository.findByStatusAndProject(ProgressStatus.active,
                projectRepository.findById(reqActivePercent.getProjectId()).get());
        float allPercent = 0;
        for (int i = 0; i < progressList.size(); i ++){
            allPercent += progressList.get(i).getPercent();
        }
        Optional<Project> optionalProject = projectRepository.findById(progress.getProject().getId());
        Project project = optionalProject.get();
        int count = project.getUsersList().size();
        if (allPercent/count == 100){
            project.setProjectStatus(ProjectStatus.inProgress);
        }
        project.setProjectPercent(allPercent/count);
        projectRepository.save(project);
        return ResponseEntity.status(200).body("qo'shildi");
    }

    public ResponseEntity<?> getPercentGIP(ReqGetPercent reqGetPercent){
        Project project  = projectRepository.findById(reqGetPercent.getProjectId()).get();
        Map<String, Object> editProject = new HashMap<>();
        editProject.put("id", project.getId());
        editProject.put("projectName",project.getProjectName());
        editProject.put("projectCreated", project.getProjectCreated());
        editProject.put("projectFinished", project.getProjectFinished());
        List<Comment> byProjectAndUsers = commentRepository.findByProjectAndUsers(project, project.getProjectManager());
        editProject.put("proRectorComment", byProjectAndUsers);
        editProject.put("projectManager", project.getProjectManager());
        editProject.put("projectPercent", project.getProjectPercent());
        editProject.put("projectStatus", project.getProjectStatus());
        editProject.put("projectMake", project.isProjectMake());
        editProject.put("document", project.getDocument());
        Optional<Users> byRoles = usersRepository.findById(2);
        editProject.put("proRector", byRoles.get() );
        List<Comment> byProjectAndUsers1 = commentRepository.findByProjectAndUsers(project, byRoles.get());
        editProject.put("proRectorComment", byProjectAndUsers1);
        List<Map<String, Object>> usersList = new ArrayList<>();
        for (Users users : project.getUsersList()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", users.getId());
            user.put("name", ""+users.getFirstName()+ "  " + users.getLastName());
            user.put("username", users.getUsername());
            List<Comment> byStatusAndProjectAndUsers = commentRepository.findByProjectAndUsers(project, users);
            Map<String, String> comment = new HashMap<>();
            List<Map<String, String>> comments = new ArrayList<>();
            for (Comment byStatusAndProjectAndUser : byStatusAndProjectAndUsers) {

                comment.put("comment", byStatusAndProjectAndUser.getComment());
                comment.put("createdAt", byStatusAndProjectAndUser.getCreatedAt().toString());
                comments.add(comment);
            }
                user.put("comment", comments);
            List<Progress> progresses = progressRepository.findByStatusAndProjectAndUsers(
                    ProgressStatus.active,
                    project,
                    users);
            float percent = 0;
            for (Progress progress : progresses) {
                percent += progress.getPercent();
            }
            user.put("userPercent", percent);

            List<Progress> progressesDisabled = progressRepository.findByStatusAndProjectAndUsers(
                    ProgressStatus.start,
                    project,
                    users);
            user.put("progresses",progressesDisabled);
            usersList.add(user);
        }
        editProject.put("usersList",usersList);
        return ResponseEntity.ok(editProject);
    }

    public ResponseEntity<?> deleteProgress(UUID uuid){
        progressRepository.deleteById(uuid);
        return ResponseEntity.ok("Malumot o'chirildi");
    }
}
