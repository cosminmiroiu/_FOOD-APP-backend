package com.finalproject.springbootfoodapp.service;

import com.finalproject.springbootfoodapp.entity.Role;
import com.finalproject.springbootfoodapp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleByRoleId(Long id) {
        return roleRepository.findById(id).get();
    }
}
