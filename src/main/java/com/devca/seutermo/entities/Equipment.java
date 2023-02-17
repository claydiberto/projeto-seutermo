package com.devca.seutermo.entities;

import javax.persistence.*;

import com.devca.seutermo.enums.EquipmentStatus;
import com.devca.seutermo.enums.EquipmentType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_equipment")
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EquipmentType equipmentType;
	
	@Enumerated(EnumType.STRING)
	private EquipmentStatus equipmentStatus = EquipmentStatus.DISPONIVEL;

	private String fabricator;
	private String model;
	
	@Column(nullable = false, unique = true)
	private String serialNumber;

}
