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

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.entities.TermDTO;
import com.devca.seutermo.entities.enums.EquipmentStatus;
import com.devca.seutermo.services.AnalystService;
import com.devca.seutermo.services.EmployeeService;
import com.devca.seutermo.services.EquipmentService;
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
	
	@GetMapping("/terms")
	public String terms(Model model) {
		model.addAttribute("termList", service.findAll());
		return "terms";
	}
	
	@GetMapping("/term")
	public String insert(Model model) {
		model.addAttribute("term", service.getTerm());
		model.addAttribute("termDTO", termDTOService.getTermDTO());
		model.addAttribute("analystList", analystService.findAll());
		model.addAttribute("employeeList", employeeService.findAll());
		model.addAttribute("equipmentList", equipmentService.findAll()); //find so em equip.disponivel
		return "term-page";
	}
	
	@PostMapping("/saveTerm")
	public String save(@Validated TermDTO termDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "/terms";
		}
		
		Term termo = service.getTerm(); 
		termo.setAnalyst(analystService.findByEmail(termDTO.getAnalyst()));
		termo.setEmployee(employeeService.findByEmail(termDTO.getEmployee()));
		
		for (String equipmentId : termDTO.getListOfEquipments()) {
			Equipment equipment = equipmentService.findById(Long.parseLong(equipmentId));
			termo.addEquipment(equipment);
			equipmentService.changeStatus(equipment, EquipmentStatus.EMPRESTADO);
		}
				
		service.save(termo);
		String str = "redirect:/signEmployee/" + termo.getId();
		return str;
	}
	
	@GetMapping("/signEmployee/{id}")
	public String signEmployee(@PathVariable("id") Long id, Model model) {
		model.addAttribute("term", service.findById(id));
		return "sign-employee";
	}
	
	@PostMapping("/saveSignEmployee")
	public String saveSignEmployee(@ModelAttribute("term") Term term, RedirectAttributes attributes) {
		service.save(term);
		String str = "redirect:/signAnalyst/" + term.getId();
		return str; 
	}
	
	@GetMapping("/signAnalyst/{id}")
	public String signAnalyst(@PathVariable("id") Long id, Model model) {
		model.addAttribute("term", service.findById(id));
		return "sign-analyst";
	}	
		
	@PostMapping("/saveSignAnalyst")
	public String saveSignAnalyst(@ModelAttribute("term") Term term) {		
		service.save(term);
		return "redirect:/terms"; 
	}

}
