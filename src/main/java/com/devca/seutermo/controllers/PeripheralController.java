package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.services.PeripheralService;

@Controller
public class PeripheralController {

	@Autowired
	private PeripheralService service;
	
	@GetMapping("/peripherals")
	public String peripherals(Model model) {
		model.addAttribute("peripheralList", service.findAll());
		model.addAttribute("peripheral", service.getPeripheral());
		return "peripheral-page";
	}
	
	@PostMapping("/savePeripheral")
	public String save(@ModelAttribute("peripheral") Peripheral peripheral) {		
		service.save(peripheral);
		return "redirect:/peripherals";
	}	
	
}
