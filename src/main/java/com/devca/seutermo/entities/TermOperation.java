package com.devca.seutermo.entities;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.devca.seutermo.enums.OperationType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_term_operation")
public class TermOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private OperationType operationType;
	
	private LocalDateTime instant;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String employeeSignature;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String analystSignature;

}
