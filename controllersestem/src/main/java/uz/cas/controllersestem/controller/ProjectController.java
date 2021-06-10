package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.controllersestem.payload.ReqLogin;
import uz.cas.controllersestem.payload.ReqProject;
import uz.cas.controllersestem.service.ProjectService;

@Controller
@RequestMapping("/uz/cas/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @PostMapping
    public HttpEntity<?> addProject(@RequestBody ReqProject reqProject) {
        System.out.println(reqProject.getProjectCreated() + reqProject.getProjectName() + reqProject.getUsersList() + reqProject.getProjectManager());
        projectService.addProject(reqProject);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProject(@PathVariable Integer id, @RequestBody ReqProject reqProject) {
        return ResponseEntity.ok(projectService.editProject(id, reqProject));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProject(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping("/all")
    public HttpEntity<?> ProjectComplete() {
        return ResponseEntity.ok(projectService.complete());
    }

    @PostMapping("/user")
    public HttpEntity<?> getProjectUser(@RequestBody ReqLogin username) {
        return ResponseEntity.ok(projectService.getUsernameProject(username));
    }
}
