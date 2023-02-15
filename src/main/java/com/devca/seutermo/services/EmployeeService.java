package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee getEmployee() {
		Employee employee = new Employee();
		return employee;
	}

	@Transactional(readOnly = true)
	public Employee findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Employee> getEmployeePageable(int currentPage, int size, String employeeName) {
		Pageable pageable = PageRequest.of(currentPage, size, Sort.by(Sort.Direction.ASC, "name"));
		if (employeeName != null) {
			return repository.findByNameContainingIgnoreCase(pageable, employeeName);
		} else {
			return repository.findAll(pageable);
		}
	}

	@Transactional
	public void save(Employee employee) {
		repository.save(employee);
	}

	@Transactional
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
