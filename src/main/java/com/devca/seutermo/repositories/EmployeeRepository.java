package com.devca.seutermo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	//Employee findByEmail(String email);

	Page<Employee> findByNameContainingIgnoreCase(Pageable pageable, String name);
	
}
