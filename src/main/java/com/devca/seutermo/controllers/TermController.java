package com.devca.seutermo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devca.seutermo.services.TermService;

@Controller
@RequestMapping(value = "/terms")
public class TermController {
	
	@Autowired
	private TermService service;

	@GetMapping
	public String terms(Model model) {
		model.addAttribute("termList", service.findAll());
		return "terms";
	}

}
