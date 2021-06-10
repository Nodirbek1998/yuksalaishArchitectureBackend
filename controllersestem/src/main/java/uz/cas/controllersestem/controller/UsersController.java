package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.controllersestem.payload.request.ReqUser;
import uz.cas.controllersestem.service.UsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/uz/cas/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public HttpEntity<?> addUser(@Valid  @RequestBody ReqUser reqUser){
        return ResponseEntity.ok(usersService.addUser(reqUser));
    }
    @PostMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Integer id, @Valid @RequestBody ReqUser reqUser){
        return ResponseEntity.ok(usersService.editUser(id, reqUser));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok(usersService.deleteUser(id));
    }

    @GetMapping
    public HttpEntity<?> getAllUsers(){
        ResponseEntity<?> all = usersService.getAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/map")
    public HttpEntity<?> getAllUser(){
        ResponseEntity<?> all = usersService.getUser();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(usersService.getUser(id));
    }
}
