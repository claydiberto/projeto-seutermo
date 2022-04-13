package com.devca.seutermo.services;

import org.springframework.stereotype.Service;

import com.devca.seutermo.dto.TermDTO;

@Service
public class TermDTOService {
	
	public TermDTO getTermDTO() {
		TermDTO dto = new TermDTO();
		return dto;
	}
	
	
}
