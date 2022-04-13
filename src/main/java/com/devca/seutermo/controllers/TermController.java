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

import com.devca.seutermo.dto.SignDTO;
import com.devca.seutermo.dto.TermDTO;
import com.devca.seutermo.dto.TermDetailsDTO;
import com.devca.seutermo.entities.TermOperation;
import com.devca.seutermo.services.AnalystService;
import com.devca.seutermo.services.EmployeeService;
import com.devca.seutermo.services.EquipmentService;
import com.devca.seutermo.services.LocalityService;
import com.devca.seutermo.services.PeripheralService;
import com.devca.seutermo.services.TermDTOService;
import com.devca.seutermo.services.TermOperationService;
import com.devca.seutermo.services.TermService;

@Controller
public class TermController {
	
	@Autowired
	private TermService service;
	
	@Autowired
	private TermDTOService termDtoService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AnalystService analystService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PeripheralService peripheralService;
	
	@Autowired
	private LocalityService localityService;
	
	@Autowired
	private TermOperationService termOperationService;
	
	@GetMapping("/terms")
	public String terms(Model model) {
		model.addAttribute("termList", service.findAll());
		return "terms";
	}
	
	@GetMapping("/term")
	public String insert(Model model) {
		model.addAttribute("termDTO", termDtoService.getTermDTO());
		model.addAttribute("analystList", analystService.findAll());
		model.addAttribute("employeeList", employeeService.findAll());
		model.addAttribute("equipmentList", equipmentService.findAvailable());
		model.addAttribute("peripheralList", peripheralService.findAll());
		model.addAttribute("localityList", localityService.findAll());
		return "term-page";
	}
	
	@PostMapping("/saveTerm")
	public String save(@Validated TermDTO termDTO, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "/terms";
		}
		return "redirect:/signEmployee/" + service.saveNewTerm(termDTO);
	}
	
	@GetMapping("/signEmployee/{termId}")
	public String signEmployee(@PathVariable("termId") Long termId, Model model) {
		SignDTO signDTO = new SignDTO(termId);
		model.addAttribute("signDTO", signDTO);
		return "sign-employee";
	}
	
	@PostMapping("/saveSignEmployee")
	public String saveSignEmployee(@ModelAttribute("signDTO") SignDTO signDTO, RedirectAttributes attributes, Model model) {		
		TermOperation termOperation = termOperationService.getTermOperation();
		termOperation = termOperationService.saveSignEmployee(signDTO);
		signDTO.setTermOperationId(termOperation.getId());
		model.addAttribute("signDTO", signDTO);
		return "sign-analyst";
	}
	
	@PostMapping("/saveSignAnalyst")
	public String saveSignAnalyst(@ModelAttribute("signDTO") SignDTO signDTO) {		
		TermOperation termOperation = termOperationService.saveSignAnalyst(signDTO);
		service.saveOperationDelivery(signDTO.getTermId(), termOperation);
		return "redirect:/terms"; 
	}	
	
	@GetMapping("/term/details/{id}")
	public String termDetails(@PathVariable("id") Long id, Model model) {
		TermDetailsDTO dto = service.getTermDetail(service.findById(id));
		model.addAttribute("term", dto);
		return "term-detail";
	}
	
	@GetMapping("/term/return/{id}")
	public String returnTerm() {
		return null;
	}

}
