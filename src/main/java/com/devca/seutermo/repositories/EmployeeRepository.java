package com.devca.seutermo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	//Employee findByEmail(String email);

	Page<Employee> findByNameContainingIgnoreCase(Pageable pageable, String name);
	
}
