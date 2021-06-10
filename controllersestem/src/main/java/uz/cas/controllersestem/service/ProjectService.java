package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.Progress;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Role;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.entity.enums.ProjectStatus;
import uz.cas.controllersestem.payload.request.ReqComment;
import uz.cas.controllersestem.payload.request.ReqProject;
import uz.cas.controllersestem.payload.request.ReqUsername;
import uz.cas.controllersestem.repository.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProgressRepository progressRepository;

    public ResponseEntity<?> addProject(ReqProject reqProject){
        HashSet<Role> roles = new HashSet<>(roleRepository.findAll());
        Project project = new Project();
        project.setProjectManager(usersRepository.findById(reqProject.getProjectManager()).get());
        project.setProjectName(reqProject.getProjectName());
        project.setProjectPercent(0);
        project.setUsersList(usersRepository.findAllById(reqProject.getUsersList()));
        project.setProjectFinished(reqProject.getProjectFinished());
        project.setProjectCreated(reqProject.getProjectCreated());
        project.setProjectStatus(ProjectStatus.active);
        projectRepository.save(project);
        return ResponseEntity.ok("Project yaratildi");
    }

    public ResponseEntity<?> editProject(Integer id, ReqProject reqProject){
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()){
            Project project = byId.get();
            project.setProjectCreated(reqProject.getProjectCreated());
            project.setProjectFinished(reqProject.getProjectFinished());
            project.setProjectName(reqProject.getProjectName());
            project.setProjectManager(usersRepository.findById(reqProject.getProjectManager()).get());
            project.setUsersList(usersRepository.findAllById(reqProject.getUsersList()));
            projectRepository.save(project);
            return ResponseEntity.status(200).body("Project o'zgartirildi");
        }
        return ResponseEntity.status(400).body("bunday id li project topilmadi");
    }

    public ResponseEntity<?> deleteProject(Integer id){
         commentRepository.deleteComment(id);
         progressRepository.deleteProgress(id);
        projectRepository.deleteById(id);
        return ResponseEntity.status(200).body("malumot o'chirildi");
    }

    public ResponseEntity<?> getProject(Integer id){
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()){
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.ok("Bunday project topilmadi");
    }
    public ResponseEntity<?> complete(){
        return ResponseEntity.ok( projectRepository.findAll());
    }

    public ResponseEntity<?> getUsernameProject(ReqUsername username){
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.active);
        if (byProjectStatus.size() > 0){
            List<Project> userProject = new ArrayList<>();
            for (Project project : byProjectStatus) {
                for (Users user : project.getUsersList()) {
                    if (user.getUsername().equals(username.getUsername())){
                        userProject.add(project);
                    }
                }
            }
            return ResponseEntity.ok(userProject);
        }
        return ResponseEntity.ok("malumot topilmadi");
    }
    public ResponseEntity<?> getGipProject(ReqUsername username){
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.active);
        if (byProjectStatus.size() > 0){
            List<Project> userProject = new ArrayList<>();
            for (Project project : byProjectStatus) {
                if (project.getProjectManager().getUsername().equals(username.getUsername())){
                    userProject.add(project);
                }
            }
            return ResponseEntity.ok(userProject);
        }
        return ResponseEntity.ok("malumot topilmadi");
    }

    public ResponseEntity<?> addDocument(ReqComment reqComment, Integer id){
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()) {
            Project project = byId.get();
            project.setDocument(reqComment.getComment());
            projectRepository.save(project);
            return ResponseEntity.ok("Hujjatlar saqlandi");
        }
        return ResponseEntity.ok("Kechirasiz bunday project topilmadi");
    }
    public ResponseEntity<?> activeProject(Integer id){
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()){
            Project project = byId.get();
            project.setProjectMake(true);
            projectRepository.save(project);
            return ResponseEntity.ok("Project ishga tushdi");
        }
        return ResponseEntity.ok("Bunday project yo'q");
    }

    public ResponseEntity<?> activeProject(){
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.active);
        return ResponseEntity.ok(byProjectStatus);
    }
    public ResponseEntity<?> inProgressProject(){
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.inProgress);
        return ResponseEntity.ok(byProjectStatus);
    }
    public ResponseEntity<?> finishedProject(){
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.finished);
        return ResponseEntity.ok(byProjectStatus);
    }
}

