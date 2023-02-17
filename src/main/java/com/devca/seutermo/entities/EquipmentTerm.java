package com.devca.seutermo.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.devca.seutermo.enums.OperationType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_term_equipments")
public class EquipmentTerm {
	
	@EmbeddedId
	private EquipmentTermPK id = new EquipmentTermPK();
	
	@Enumerated(EnumType.STRING)
	private OperationType statusEquipmentOnTerm;
	
	public void setTerm(Term term) {
		id.setTerm(term);
	}
	
	public void setEquipment(Equipment equipment) {
		id.setEquipment(equipment);
	}

}
