package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devca.seutermo.entities.TermDTO;
import com.devca.seutermo.services.AnalystService;
import com.devca.seutermo.services.EmployeeService;
import com.devca.seutermo.services.EquipmentService;
import com.devca.seutermo.services.PeripheralService;
import com.devca.seutermo.services.TermDTOService;
import com.devca.seutermo.services.TermService;

@Controller
public class TermController {
	
	@Autowired
	private TermService service;
	
	@Autowired
	private TermDTOService termDTOService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AnalystService analystService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PeripheralService peripheralService;
	
	@GetMapping("/terms")
	public String terms(Model model) {
		model.addAttribute("termList", service.findAll());
		return "terms";
	}
	
	@GetMapping("/term")
	public String insert(Model model) {
		model.addAttribute("termDTO", termDTOService.getTermDTO());
		model.addAttribute("analystList", analystService.findAll());
		model.addAttribute("employeeList", employeeService.findAll());
		model.addAttribute("equipmentList", equipmentService.findAvailable());
		model.addAttribute("peripheralList", peripheralService.findAll());
		return "term-page";
	}
	
	@PostMapping("/saveTerm")
	public String save(@Validated TermDTO termDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/terms";
		}
		Long id = service.save(termDTO);
		return "redirect:/signEmployee/" + id;
	}
	
	@GetMapping("/signEmployee/{id}")
	public String signEmployee(@PathVariable("id") Long id, Model model) {
		TermDTO termDTO = termDTOService.getTermDTO();
		termDTO.setId(id);
		model.addAttribute("termDTO", termDTO);
		return "sign-employee";
	}
	
	@PostMapping("/saveSignEmployee")
	public String saveSignEmployee(@ModelAttribute("termDTO") TermDTO termDTO, RedirectAttributes attributes) {		
		service.saveSign(termDTO, "employee");
		return "redirect:/signAnalyst/" + termDTO.getId();
	}
	
	@GetMapping("/signAnalyst/{id}")
	public String signAnalyst(@PathVariable("id") Long id, Model model) {
		TermDTO termDTO = termDTOService.getTermDTO();
		termDTO.setId(id);
		model.addAttribute("termDTO", termDTO);
		return "sign-analyst";
	}	
		
	@PostMapping("/saveSignAnalyst")
	public String saveSignAnalyst(@ModelAttribute("termDTO") TermDTO termDTO) {		
		service.saveSign(termDTO, "analyst");
		return "redirect:/terms"; 
	}
	
	@GetMapping("/term/details/{id}")
	public String termDetails(@PathVariable("id") Long id, Model model) {		
		model.addAttribute("term", this.service.findById(id) );
		return "term-detail";
	}	

}
