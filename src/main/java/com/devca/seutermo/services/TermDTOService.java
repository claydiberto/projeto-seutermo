package com.devca.seutermo.services;

import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.TermDTO;

@Service
public class TermDTOService {
	
	public TermDTO getTermDTO() {
		TermDTO dto = new TermDTO();
		return dto;
	}

}
