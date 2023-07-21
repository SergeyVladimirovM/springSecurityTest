package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("api/v1/products")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getAllProduct(Model model) {
        model.addAttribute("productList", productService.findAllProduct());
        return "products";
    }
}
