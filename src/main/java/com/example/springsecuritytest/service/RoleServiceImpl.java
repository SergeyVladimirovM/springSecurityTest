package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Role;
import com.example.springsecuritytest.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public UUID saveRole(Role role) {
        return roleRepository.save(role).getId();
    }
}
