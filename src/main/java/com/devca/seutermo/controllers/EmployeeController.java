package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.services.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@RequestMapping(path = {"/employees", "/employees/search"})
	public String employees(Model model, String employeeName) {
		return employeesPageable(0, model, employeeName);
	}

	@GetMapping("/employees/page/{pageNumber}")
	public String employeesPageable(@PathVariable int pageNumber, Model model, String employeeName) {
		Page<Employee> employeeListPage = service.getEmployeePageable(pageNumber, 5, employeeName);
		model.addAttribute("employeeList", employeeListPage);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", employeeListPage.getTotalPages());
		model.addAttribute("totalItem", employeeListPage.getTotalElements());
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
