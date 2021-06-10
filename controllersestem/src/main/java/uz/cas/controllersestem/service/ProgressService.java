package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.Comment;
import uz.cas.controllersestem.entity.Progress;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.payload.ReqGetPercent;
import uz.cas.controllersestem.payload.ReqProgress;
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

    public ResponseEntity<?> addProgress(ReqProgress reqProgress) {
        Progress progress = new Progress();
        progress.setPercent(reqProgress.getPercent());
        progress.setProject(projectRepository.findById(reqProgress.getProjectId()).get());
        progress.setUsers(usersRepository.findById(reqProgress.getUserId()).get());
        progressRepository.save(progress);
        return ResponseEntity.status(200).body("Malumot saqlandi");
    }

    public ResponseEntity<?> editProgress(UUID id, ReqProgress reqProgress) {
        Optional<Progress> byId = progressRepository.findById(id);
        if (byId.isPresent()) {
            Progress progress = byId.get();
            progress.setUsers(usersRepository.findById(reqProgress.getUserId()).get());
            progress.setProject(projectRepository.findById(reqProgress.getProjectId()).get());
            progress.setPercent(reqProgress.getPercent());
            progressRepository.save(progress);
            return ResponseEntity.status(200).body("Malumot o'zgartirildi");
        }
        return ResponseEntity.status(500).body("Bunaqa malumot topilmadi");
    }

    public ResponseEntity<?> progressActive(UUID id) {
        Optional<Progress> byId = progressRepository.findById(id);
        Progress progress = byId.get();
        progress.setStatus(true);
        progressRepository.save(progress);
        List<Progress> progressList = progressRepository.findByStatus(true);
        float allPercent = 0;
        for (int i = 0; i < progressList.size(); i++) {
            allPercent += progressList.get(i).getPercent();
        }
        Optional<Project> optionalProject = projectRepository.findById(progress.getProject().getId());
        Project project = optionalProject.get();
        int count = project.getUsersList().size();
        project.setProjectPercent(allPercent / count);
        projectRepository.save(project);
        return ResponseEntity.status(200).body("qo'shildi");
    }

    public ResponseEntity<?> getPercent(ReqGetPercent reqGetPercent) {
        Project project = projectRepository.findById(reqGetPercent.getProjectId()).get();
        Map<String, Object> editProject = new HashMap<>();
        editProject.put("id", project.getId());
        editProject.put("projectName", project.getProjectName());
        editProject.put("projectCreated", project.getProjectCreated());
        editProject.put("projectFinished", project.getProjectFinished());
        editProject.put("projectManager", project.getProjectManager());
        editProject.put("projectPercent", project.getProjectPercent());
        editProject.put("projectStatus", project.getProjectStatus());
        List<Map<String, Object>> usersList = new ArrayList<>();
        for (Users users : project.getUsersList()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", users.getId());
            user.put("name", "" + users.getFirstName() + "  " + users.getLastName());
            user.put("username", users.getUsername());
            Optional<Comment> byStatusAndProjectAndUsers = commentRepository.findByStatusAndProjectAndUsers(true, project, users);
            if (byStatusAndProjectAndUsers.isPresent()) {
                user.put("comment", byStatusAndProjectAndUsers.get().getComment());
            }

            List<Progress> progresses = progressRepository.findByStatusAndProjectAndUsers(
                    true,
                    project,
                    users);
            float percent = 0;
            for (Progress progress : progresses) {
                percent += progress.getPercent();
            }
            user.put("userPercent", percent);
            usersList.add(user);
        }
        editProject.put("usersList", usersList);
        return ResponseEntity.ok(editProject);
    }

    public ResponseEntity<?> getPercentGIP(ReqGetPercent reqGetPercent) {
        Project project = projectRepository.findById(reqGetPercent.getProjectId()).get();
        Map<String, Object> editProject = new HashMap<>();
        editProject.put("id", project.getId());
        editProject.put("projectName", project.getProjectName());
        editProject.put("projectCreated", project.getProjectCreated());
        editProject.put("projectFinished", project.getProjectFinished());
        editProject.put("projectManager", project.getProjectManager());
        editProject.put("projectPercent", project.getProjectPercent());
        editProject.put("projectStatus", project.getProjectStatus());
        List<Map<String, Object>> usersList = new ArrayList<>();
        for (Users users : project.getUsersList()) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", users.getId());
            user.put("name", "" + users.getFirstName() + "  " + users.getLastName());
            user.put("username", users.getUsername());
            Optional<Comment> byStatusAndProjectAndUsers = commentRepository.findByStatusAndProjectAndUsers(true, project, users);
            if (byStatusAndProjectAndUsers.isPresent()) {
                user.put("comment", byStatusAndProjectAndUsers.get().getComment());
            }
            List<Progress> progresses = progressRepository.findByStatusAndProjectAndUsers(
                    true,
                    project,
                    users);
            float percent = 0;
            for (Progress progress : progresses) {
                percent += progress.getPercent();
            }
            user.put("userPercent", percent);

            List<Progress> progressesDisabled = progressRepository.findByStatusAndProjectAndUsers(
                    false,
                    project,
                    users);
            user.put("progresses", progressesDisabled);
            usersList.add(user);
        }
        editProject.put("usersList", usersList);
        return ResponseEntity.ok(editProject);
    }
}
