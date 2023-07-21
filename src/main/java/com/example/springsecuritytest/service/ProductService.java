package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
}
