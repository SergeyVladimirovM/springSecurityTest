package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<Role> findAllRole();
    UUID saveRole(Role role);
}
