package com.grpc.adminsecuity.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/verifyusers")
    public String verifyData() {
        return "User verified!";
    }

}
