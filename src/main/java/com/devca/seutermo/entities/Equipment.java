package com.devca.seutermo.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devca.seutermo.entities.enums.EquipmentStatus;
import com.devca.seutermo.entities.enums.EquipmentType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_equipment")
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	private EquipmentType equipmentType;
	private String fabricator;
	private String model;
	private String serialNumber;
	
	@Enumerated
	private EquipmentStatus equipmentStatus = EquipmentStatus.DISPONIVEL;
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listOfEquipments")
//	private List<Term> listOfTerms = new ArrayList<>();
	
}
