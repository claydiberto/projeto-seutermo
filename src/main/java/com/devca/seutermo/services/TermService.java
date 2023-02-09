package com.devca.seutermo.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true)
	public List<Term> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Term findById(Long id) {
		return repository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public List<Term> findByEmployeeName(String employeeName){
		return repository.findByEmployeeNameContainingIgnoreCase(employeeName);
	}
	
	@Transactional
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
			EquipmentTerm equipmentTerm = new EquipmentTerm();
			
			equipmentTerm.setTerm(term);
			equipmentTerm.setEquipment(equipment);
			equipmentTerm.setStatusEquipmentOnTerm(OperationType.DELIVERY);
			
			equipmentTerm = equipmentTermRepository.saveAndFlush(equipmentTerm);
			term.addEquipment(equipmentTerm);
			repository.save(term);
		}
		
		return term.getId();
	}
	
	@Transactional
	public void saveOperation(Long termId, TermOperation termOperation) {
		Term term = findById(termId);
		
		if (termOperation.getOperationType().equals(OperationType.DELIVERY)) {
			term.setDelivery(termOperation);
			repository.save(term);
			for (EquipmentTerm equipmentTerm : term.getEquipments()) {
				equipmentService.changeStatus(equipmentTerm.getId().getEquipment(), EquipmentStatus.EMPRESTADO);
			}
			
		} else {
			term.setDevolution(termOperation);
			repository.save(term);
			for (EquipmentTerm equipmentTerm : term.getEquipments()) {
				if (equipmentTerm.getStatusEquipmentOnTerm().equals(OperationType.DEVOLUTION)) {
					equipmentService.changeStatus(equipmentTerm.getId().getEquipment(), EquipmentStatus.DISPONIVEL);					
				}
			}
		}
		
	}

	@Transactional
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
		dto.setLocalityZip(term.getLocality().getZip());
		dto.setLocalityCity(term.getLocality().getCity());
		dto.setLocalityUf(term.getLocality().getUf());
		dto.setLocalityCompanyName(term.getLocality().getCompanyName());
		dto.setLocalityNumber(term.getLocality().getNumber());
		dto.setDeliveryInstant(term.getDelivery().getInstant());
		dto.setDeliverySignAnalyst(term.getDelivery().getAnalystSignature());
		dto.setDeliverySignEmployee(term.getDelivery().getEmployeeSignature());
		
		if (!Objects.isNull(term.getDevolution()) ) {
			dto.setDevolutionInstant(term.getDevolution().getInstant());
			dto.setDevolutionSignAnalyst(term.getDevolution().getAnalystSignature());
			dto.setDevolutionSignEmployee(term.getDevolution().getEmployeeSignature());
		}
		
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
	
	@Transactional(readOnly = true)
	public TermDTO getTermDevolution(Long id) {
		Term term = findById(id);
		TermDTO termDTO = new TermDTO();
		
		termDTO.setId(term.getId());
		
		for (EquipmentTerm equipmentTerm : term.getEquipments()) {
			if (equipmentTerm.getStatusEquipmentOnTerm().equals(OperationType.DELIVERY)) {
				termDTO.addEquipment(equipmentTerm.getId().getEquipment());
			}
		}
		
		return termDTO;
	}
	
	@Transactional
	public void saveDevolution(TermDTO termDTO) {
		Term term = findById(termDTO.getId());
		
		for (Equipment equipment : termDTO.getEquipments()) {
			
			for (EquipmentTerm equipmentTerm : term.getEquipments()) {
				
				if (equipmentTerm.getId().getEquipment().equals(equipment)) {
					equipmentTerm.setStatusEquipmentOnTerm(OperationType.DEVOLUTION);
					equipmentTermRepository.saveAndFlush(equipmentTerm);
				}
				
			}
			
		}

	}
	
}
