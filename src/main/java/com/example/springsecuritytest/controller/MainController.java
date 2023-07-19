package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.service.PersonService;
import com.example.springsecuritytest.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "Hello";
    }

    @GetMapping("/customer")
    public String customerPage() {
        return "Hello customer";
    }

    @GetMapping("/manager")
    public String managerPage() {
        return "Hello manager";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Hello admin";
    }
}
