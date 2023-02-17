package com.devca.seutermo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Role;
import com.devca.seutermo.enums.RoleName;
import com.devca.seutermo.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
	private RoleRepository repository;

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findRoleUser() {
        return repository.findByRoleName(RoleName.USER.toString());
    }

    public Optional<Role> findById() {
        return repository.findById(2L);
    }
    
}
