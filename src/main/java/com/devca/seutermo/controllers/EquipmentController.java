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

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.services.EquipmentService;

@Controller
public class EquipmentController {

	@Autowired
	private EquipmentService service;

	// @GetMapping("/page/{pageNumber}")
	// public String findPaginated(@PathVariable int pageNumber, Model model) {
	// Page<Equipment> equipmentListPage = service.findEquipmentPageable(pageNumber,
	// 2);
	// model.addAttribute("equipmentList", equipmentListPage);
	// model.addAttribute("currentPage", pageNumber);
	// model.addAttribute("totalPages", equipmentListPage.getTotalPages());
	// model.addAttribute("totalItem", equipmentListPage.getTotalElements());
	// return "equipments";

	// }

	// @RequestMapping(path = { "/equipments", "/equipments/search", })

	@RequestMapping(path = {"/equipments", "/equipments/search"})
	public String equipments(Model model, String serialNumber) {
		//model.addAttribute("equipmentList", service.findBySerialNumber(serialNumber));
		return equipmentsPageable(0, model, serialNumber);
	}

	@GetMapping("/equipments/page/{pageNumber}")
	public String equipmentsPageable(@PathVariable int pageNumber, Model model, String serialNumber) {
		Page<Equipment> equipmentListPage = service.getEquipmentPageable(pageNumber, 3, serialNumber);
		model.addAttribute("equipmentList", equipmentListPage);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", equipmentListPage.getTotalPages());
		model.addAttribute("totalItem", equipmentListPage.getTotalElements());
		return "equipments";
		// return findPaginated(0, model);
	}

	@GetMapping("/equipment")
	public String insert(Model model) {
		model.addAttribute("equipment", service.getEquipment());
		return "equipment-page";
	}

	@GetMapping("/equipment/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("equipment", service.findById(id));
		return "equipment-page";
	}

	@PostMapping("/saveEquipment")
	public String save(@ModelAttribute("equipment") Equipment equipment) {
		service.save(equipment);
		return "redirect:/equipments";
	}

	@GetMapping("/deleteEquipment/{id}")
	public String delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return "redirect:/equipments";
		} else
			return "redirect:/";
	}

}
