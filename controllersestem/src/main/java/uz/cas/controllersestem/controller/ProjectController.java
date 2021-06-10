package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.controllersestem.payload.request.ReqComment;
import uz.cas.controllersestem.payload.request.ReqProject;
import uz.cas.controllersestem.payload.request.ReqUsername;
import uz.cas.controllersestem.service.ProjectService;

import javax.validation.Valid;

@Controller
@RequestMapping("/uz/cas/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @PostMapping
    public HttpEntity<?> addProject(@Valid  @RequestBody ReqProject reqProject){
        return ResponseEntity.ok(projectService.addProject(reqProject));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProject(@PathVariable Integer id, @RequestBody ReqProject reqProject){
        return ResponseEntity.ok(projectService.editProject(id, reqProject));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Integer id){
        return ResponseEntity.ok(projectService.deleteProject(id));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getProject(@PathVariable Integer id){
        return ResponseEntity.ok(projectService.getProject(id));
    }
    @GetMapping("/all")
    public HttpEntity<?> ProjectComplete(){
        return ResponseEntity.ok(projectService.complete());
    }

    @PostMapping("/user")
    public HttpEntity<?> getProjectUser(@RequestBody ReqUsername username){
        return ResponseEntity.ok(projectService.getUsernameProject(username));
    }
    @PostMapping("/user/gip")
    public HttpEntity<?> getGipProject(@RequestBody ReqUsername username){
        return ResponseEntity.ok(projectService.getGipProject(username));
    }
    @PostMapping("/{id}")
    public HttpEntity<?> addDocument(@PathVariable Integer id, @Valid  @RequestBody ReqComment reqComment){
        ResponseEntity<?> responseEntity = projectService.addDocument(reqComment, id);
        return ResponseEntity.ok(responseEntity);
    }
    @GetMapping("/active/{id}")
    public HttpEntity<?> projectActive(@PathVariable Integer id){
        ResponseEntity<?> responseEntity = projectService.activeProject(id);
        return ResponseEntity.ok(responseEntity);
    }
    @GetMapping("/active")
    public HttpEntity<?> activeProject(){
        return ResponseEntity.ok(projectService.activeProject());
    }
    @GetMapping("/inProgress")
    public HttpEntity<?> inProgressProject(){
        return ResponseEntity.ok(projectService.inProgressProject());
    }
    @GetMapping("/finished")
    public HttpEntity<?> finishedProject(){
        return ResponseEntity.ok(projectService.finishedProject());
    }
}
