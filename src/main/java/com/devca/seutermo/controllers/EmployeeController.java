package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employees")
	public String employees(Model model) {
		model.addAttribute("employeeList", service.findAll());
		return "employees";
	}
	
	@GetMapping("/employee")
	public String insert(Model model) {
		model.addAttribute("employee", service.getEmployee());
		return "employee-page";
	}

	@GetMapping("/employee/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("employee", service.findById(id));
		return "employee-page";
	}
	
	@PostMapping("/saveEmployee")
	public String save(@ModelAttribute("employee") Employee employee) {
		service.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return "redirect:/employees";
		} else
			return "redirect:/";
	}
	
}
