package com.devca.seutermo.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EquipmentTermPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "term_id")
	private Term term;
	
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;

}
