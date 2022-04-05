package com.devca.seutermo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.entities.TermDTO;
import com.devca.seutermo.entities.enums.EquipmentStatus;
import com.devca.seutermo.entities.enums.StatusTerm;
import com.devca.seutermo.repositories.TermRepository;

@Service
public class TermService {

	@Autowired
	private TermRepository repository;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AnalystService analystService;
	
	@Autowired
	private EmployeeService employeeService;
		
	public List<Term> findAll() {
		return repository.findAll();
	}
	
	public Term findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Term getTerm() {
		Term term = new Term();
		return term;
	}
	
	public Long save(TermDTO termDTO) {
		Term term = getTerm(); 
		term.setAnalyst(analystService.findByEmail(termDTO.getAnalyst()));
		term.setEmployee(employeeService.findByEmail(termDTO.getEmployee()));
		term.setStatusTerm(StatusTerm.ENTREGUE);
		term.setMoment(LocalDateTime.now());
		
		for (Equipment equipmentId : termDTO.getListOfEquipments()) {
			Equipment equipment = equipmentService.findById(equipmentId.getId());
			term.addEquipment(equipment);
			equipmentService.changeStatus(equipment, EquipmentStatus.EMPRESTADO);
		}
		
		repository.save(term);
		return term.getId();
	}
	
	public void saveSign(TermDTO termDTO, String signOwner) {
		Term term = findById(termDTO.getId());
		
		if (signOwner == "employee") {
			term.setEmployeeSubscription(termDTO.getEmployeeSubscription());
		}  else if (signOwner == "analyst") {
			term.setAnalystSubscription(termDTO.getAnalystSubscription());
		}
		
		repository.save(term);
	}
	
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 
}
