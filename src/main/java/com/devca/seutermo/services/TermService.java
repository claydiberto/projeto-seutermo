package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.dto.TermDTO;
import com.devca.seutermo.dto.TermDetailsDTO;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.EquipmentTerm;
import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.entities.TermOperation;
import com.devca.seutermo.enums.EquipmentStatus;
import com.devca.seutermo.enums.OperationType;
import com.devca.seutermo.repositories.EquipmentTermRepository;
import com.devca.seutermo.repositories.TermRepository;

@Service
public class TermService {

	@Autowired
	private TermRepository repository;
	
	@Autowired
	private EquipmentTermRepository equipmentTermRepository;
		
	@Autowired
	private AnalystService analystService;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private LocalityService localityService;
	
	public Term getTerm() {
		Term term = new Term();
		return term;
	}
		
	public List<Term> findAll() {
		return repository.findAll();
	}
	
	public Term findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Long saveNewTerm(TermDTO dto) {
		Term term = getTerm();
		
		term.setAnalyst(analystService.findById(dto.getAnalyst()));
		term.setEmployee(employeeService.findById(dto.getEmployee()));
		term.setLocality(localityService.findById(dto.getLocality()));
		
		for (Peripheral peripheral : dto.getPeripherals()) {
			term.addPeripheral(peripheral);
		}
		
		term = repository.saveAndFlush(term);
		
		for (Equipment equipment : dto.getEquipments()) {
			EquipmentTerm et = new EquipmentTerm();
			et.setTerm(term);
			et.setEquipment(equipment);
			et.setStatusEquipmentOnTerm(OperationType.DELIVERY);
			
			et = equipmentTermRepository.saveAndFlush(et);
			term.addEquipment(et);
			repository.save(term);
		}
		
		return term.getId();
	}
	
	public void saveOperationDelivery(Long termId, TermOperation termOperation) {
		Term term = findById(termId);
		term.setDelivery(termOperation);
		repository.save(term);
		
		for (EquipmentTerm equipmentTerm : term.getEquipments()) {
			Equipment equipment = equipmentTerm.getId().getEquipment();
			equipmentService.changeStatus(equipment, EquipmentStatus.EMPRESTADO);
		}
	}

	public TermDetailsDTO getTermDetail(Term term) {
		TermDetailsDTO dto = new TermDetailsDTO();
		
		dto.setId(term.getId());
		dto.setAnalyst(term.getAnalyst().getName());
		dto.setEmployeeName(term.getEmployee().getName());
		dto.setEmployeeEmail(term.getEmployee().getEmail());
		dto.setEmployeeCpf(term.getEmployee().getCpf());
		dto.setLocalityCnpj(term.getLocality().getCnpj());
		dto.setLocalityAddress(term.getLocality().getAddress());
		dto.setLocalityDistrict(term.getLocality().getDistrict());
		dto.setLocalityCep(term.getLocality().getCep());
		dto.setDeliveryInstant(term.getDelivery().getInstant());
		dto.setDeliverySignAnalyst(term.getDelivery().getAnalystSignature());
		dto.setDeliverySignEmployee(term.getDelivery().getEmployeeSignature());
		
		for (EquipmentTerm et : term.getEquipments()) {
			if (et.getStatusEquipmentOnTerm().equals(OperationType.DELIVERY)) {
				dto.addDeliveryEquipment(et.getId().getEquipment());				
			} else {
				dto.addDevolutionEquipment(et.getId().getEquipment());
			}
		}
		
		for (Peripheral peripheral : term.getPeripherals()) {
			dto.addPeripheral(peripheral);
		}
		
		return dto;
	}
	
	public void saveDevolution() {}
	

}
