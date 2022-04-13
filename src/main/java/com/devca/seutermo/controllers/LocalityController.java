package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Locality;
import com.devca.seutermo.services.LocalityService;

@Controller
public class LocalityController {

	@Autowired
	private LocalityService service;
	
	@GetMapping("/localities")
	public String localities(Model model) {
		model.addAttribute("localityList", service.findAll());
		model.addAttribute("locality", service.getLocality());
		return "locality-page";
	}
	
	@PostMapping("/saveLocality")
	public String save(@ModelAttribute("locality") Locality locality) {		
		service.save(locality);
		return "redirect:/localities";
	}
	
	@GetMapping("/deleteLocality/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return "redirect:/localities";
		} else
			return "redirect:/localities";
	}
	
}
