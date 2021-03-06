package com.devca.seutermo.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.devca.seutermo.enums.EquipmentStatus;
import com.devca.seutermo.enums.EquipmentType;

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
	
	@Enumerated
	private EquipmentStatus equipmentStatus = EquipmentStatus.DISPONIVEL;

	private String fabricator;
	private String model;
	private String serialNumber;

}
