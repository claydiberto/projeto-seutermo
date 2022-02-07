package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
		
	public List<Employee> findAll() {
		return repository.findAll();
	}
	
	public Employee findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Employee getEmployee() {
		Employee employee = new Employee();
		return employee;
	}
	
	public void save(Employee employee) {
		repository.save(employee);
	}

	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 
}
