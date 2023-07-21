package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("api/v1/persons")
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String getAllPerson(Model model) {
        model.addAttribute("personList", personService.findAllPerson());
        return "persons";
    }
}
