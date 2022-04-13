package com.devca.seutermo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.dto.SignDTO;
import com.devca.seutermo.entities.TermOperation;
import com.devca.seutermo.enums.OperationType;
import com.devca.seutermo.repositories.TermOperaionRepository;

@Service
public class TermOperationService {
	
	@Autowired
	private TermOperaionRepository repository;

	public TermOperation findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void save(TermOperation termOperation) {
		repository.save(termOperation);
	}
	
	public TermOperation getTermOperation() {
		TermOperation termOperation = new TermOperation();
		return termOperation;
	}

	public TermOperation saveSignEmployee(SignDTO signDTO) {
		TermOperation termOperation = getTermOperation();
		termOperation.setEmployeeSignature(signDTO.getSignature());
		termOperation = repository.saveAndFlush(termOperation);
		return termOperation;
	}

	public TermOperation saveSignAnalyst(SignDTO signDTO) {
		TermOperation termOperation = findById(signDTO.getTermOperationId());
		termOperation.setAnalystSignature(signDTO.getSignature());
		termOperation.setOperationType(OperationType.DELIVERY);
		termOperation.setInstant(LocalDateTime.now());
		return repository.saveAndFlush(termOperation);
		
	}
	
}
