package com.guidelk.tourism.controller;

import com.guidelk.tourism.entity.AuthRequest;
import com.guidelk.tourism.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("logins")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        return this.loginService.authenticate(authRequest);
    }

    @GetMapping("/checkValidToken")
    public String checkValidToken() {
        return "Success";
    }
}
