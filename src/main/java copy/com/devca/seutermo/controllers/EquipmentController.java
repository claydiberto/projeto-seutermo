package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.services.EquipmentService;

@Controller
public class EquipmentController {

	@Autowired
	private EquipmentService service;
	
	@GetMapping("/equipments")
	public String equipments(Model model) {
		model.addAttribute("equipmentList", service.findAll());
		return "equipments";
	}
	
	@GetMapping("/equipment")
	public String saveEquipment(Model model) {
		model.addAttribute("equipment", service.getEquipment());
		return "equipment-page";
	}

	@GetMapping("/equipment/{id}")
	public String saveEquipment(@PathVariable("id") Long id, Model model) {
		model.addAttribute("equipment", service.findById(id));
		return "equipment-page";
	}
	
	@PostMapping("/saveEquipment")
	public String saveEquipment(@ModelAttribute("equipment") Equipment equipment) {		
		service.save(equipment);
		return "redirect:/equipments";
	}
	
	@GetMapping("/deleteEquipment/{id}")
	public String deleteEquipment(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return "redirect:/equipments";
		} else
			return "redirect:/";
	}
	
}
