package com.finalproject.springbootfoodapp.controller;

import com.finalproject.springbootfoodapp.entity.Role;
import com.finalproject.springbootfoodapp.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public Role createNewRole(@RequestBody Role role) throws Exception {
        return roleService.createNewRole(role);
    }
}
