package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.service.PersonService;
import com.example.springsecuritytest.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("api/v1/admin_page")
@RequestMapping("/admin_page")
public class AdminController {

    private final PersonService personService;
    private final ProductService productService;

    public AdminController(PersonService personService, ProductService productService) {
        this.personService = personService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getAllPersonAndProduct(Model model) {
        model.addAttribute("personList", personService.findAllPerson());
        model.addAttribute("productList", productService.findAllProduct());
        return "admin_page";
    }
}
