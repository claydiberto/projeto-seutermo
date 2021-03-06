package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
