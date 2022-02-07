package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.services.AnalystService;

@Controller
public class AnalystController {

	@Autowired
	private AnalystService service;
	
	@GetMapping("/analysts")
	public String analysts(Model model) {
		model.addAttribute("analystList", service.findAll());
		return "analysts";
	}
	
	@GetMapping("/analyst")
	public String insert(Model model) {
		model.addAttribute("analyst", service.getAnalyst());
		return "analyst-page";
	}

	@GetMapping("/analyst/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("analyst", service.findById(id));
		return "analyst-page";
	}
	
	@PostMapping("/saveAnalyst")
	public String save(@ModelAttribute("analyst") Analyst analyst) {		
		service.save(analyst);
		return "redirect:/analysts";
	}
	
	@GetMapping("/deleteAnalyst/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return "redirect:/analysts";
		} else
			return "redirect:/";
	}
	
}
