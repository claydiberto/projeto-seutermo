package com.devca.seutermo.dto;

import java.util.ArrayList;
import java.util.List;

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Peripheral;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermDevolutionDTO {
	
	private Long id;
	private Long termOperationId;
	private String signature;
	private Boolean damagedEquipment;
	
	private List<Equipment> equipments = new ArrayList<>();	
	private List<Peripheral> peripherals = new ArrayList<>();
	
	public TermDevolutionDTO(Long id) {
		this.id = id;
	}
	
	public void addEquipment(Equipment equipment) {
		getEquipments().add(equipment);
	}
	
	public void addPeripheral(Peripheral peripheral) {
		getPeripherals().add(peripheral);
	}
	

}
