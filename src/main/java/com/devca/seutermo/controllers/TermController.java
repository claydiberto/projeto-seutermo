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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	private final String UPLOAD_DIR = "./src/main/resources/signImages/";
	
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
		model.addAttribute("equipmentList", equipmentService.findAll());
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
//		return "redirect:/signEmployee";
//		return "redirect:/terms";
	}
	
	//
	//
	//
	
	
	
	
	
	@GetMapping("/signEmployee/{id}")
	public String signEmployee(@PathVariable("id") Long id, Model model) {
		model.addAttribute("term", service.findById(id));
		return "sign-employee";
	}
	
	//backup
	//
//	@PostMapping("/saveSignEmployee")
//	public String saveSignEmployee(@ModelAttribute("term") Term term, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {		
//		service.save(term);
//		String str = "redirect:/signAnalyst/" + term.getId();
//		return str; 
//	}
	
	@PostMapping("/saveSignEmployee")
	public String saveSignEmployee(@ModelAttribute("term") Term term, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
		
//		// normalize the file path
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        // save the file on the local file system
//        Path path = null;
//        try {
//            path = Paths.get(UPLOAD_DIR + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
		//term.setEmployeeSubscription(path.toString());
		
		System.out.println(term.getEmployeeSubscription());
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
	
	

	
//	@PostMapping("/saveTerm")
//	public String save(@ModelAttribute("termDTO") TermDTO termDTO) {
//		
//		//tenho string de email de analyst e employee
//		
//		
//		Term term = service.getTerm();
//		String test = termDTO.getAnalyst();
//		
//		term.setAnalyst(analystService.findByEmail(test));
//		
//		String test2 = termDTO.getEmployee();
//		System.out.println(test);
//		System.out.println(term.getAnalyst().getName());
//		
//		
//		
//		return "redirect:/terms";
//	}
	

//	@GetMapping("/deleteEquipment/{id}")
//	public String deleteEquipment(@PathVariable("id") Long id) {
//		if (service.delete(id)) {
//			return "redirect:/equipments";
//		} else
//			return "redirect:/";
//	}

}
