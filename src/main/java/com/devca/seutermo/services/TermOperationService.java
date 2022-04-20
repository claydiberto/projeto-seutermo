package com.devca.seutermo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.dto.SignDTO;
import com.devca.seutermo.entities.TermOperation;
import com.devca.seutermo.enums.OperationType;
import com.devca.seutermo.repositories.TermOperationRepository;

@Service
public class TermOperationService {
	
	@Autowired
	private TermOperationRepository repository;

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

	public Long saveSignEmployee(SignDTO signDTO) {
		TermOperation termOperation = getTermOperation();
		termOperation.setEmployeeSignature(signDTO.getSignature());
		
		if (signDTO.getOperation().equals("delivery")) {
			termOperation.setOperationType(OperationType.DELIVERY);
		} else {
			termOperation.setOperationType(OperationType.DEVOLUTION);
		}
		
		return repository.saveAndFlush(termOperation).getId();
	}

	public TermOperation saveSignAnalyst(SignDTO signDTO) {
		TermOperation termOperation = findById(signDTO.getTermOperationId());
		termOperation.setAnalystSignature(signDTO.getSignature());
		termOperation.setInstant(LocalDateTime.now());
		return repository.saveAndFlush(termOperation);
	}
	
}
