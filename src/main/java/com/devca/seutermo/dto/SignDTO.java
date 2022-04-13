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
	
	public SignDTO(Long termId) {
		this.termId = termId;
	}

}
