package uz.cas.controllersestem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.cas.controllersestem.payload.request.ReqLogin;
import uz.cas.controllersestem.service.UsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/uz/cas")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid  @RequestBody ReqLogin reqLogin){
        ResponseEntity<?> login = usersService.login(reqLogin);
        return ResponseEntity.ok(login);
    }
}
