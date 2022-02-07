package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Employee;

public interface UserRepository extends JpaRepository<Employee, Long>{

}
