package com.stewart.sports_store.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public String login() {
        return "123";
    }
}
