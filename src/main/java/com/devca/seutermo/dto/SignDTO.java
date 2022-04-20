package com.devca.seutermo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignDTO {
	
	private Long termId;
	private Long termOperationId;
	private String signature;
	private String operation;
	
	public SignDTO(Long termId, String operation) {
		this.termId = termId;
		this.operation = operation;
	}

}
