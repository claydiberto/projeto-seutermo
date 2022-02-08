package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Term;
import com.devca.seutermo.services.TermService;

@Controller
public class TermController {
	
	@Autowired
	private TermService service;

//	@GetMapping("/terms")
//	public String terms(Model model) {
//		
//		Stream<Analyst> map = analystRepository.findAll().stream();
//		
//		model.addAllAttributes("analysts", map);
//		
//		return "null";
//	}
	
	@GetMapping("/terms")
	public String terms(Model model, Model analyst) {
		model.addAttribute("termList", service.findAll());
		return "terms";
	}
	
	@GetMapping("/term")
	public String insert(Model model) {
		model.addAttribute("term", service.getTerm());
		return "term-page";
	}

	@GetMapping("/term/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("term", service.findById(id));
		return "term-page";
	}
	
	@PostMapping("/saveTerm")
	public String save(@ModelAttribute("term") Term term) {		
		service.save(term);
		return "redirect:/terms";
	}
	
//	@GetMapping("/deleteEquipment/{id}")
//	public String deleteEquipment(@PathVariable("id") Long id) {
//		if (service.delete(id)) {
//			return "redirect:/equipments";
//		} else
//			return "redirect:/";
//	}

}
