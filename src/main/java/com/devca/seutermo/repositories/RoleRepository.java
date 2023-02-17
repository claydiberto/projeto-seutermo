package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByRoleName(String roleName);
}