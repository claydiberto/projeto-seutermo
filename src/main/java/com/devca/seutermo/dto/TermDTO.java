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
public class TermDTO {
	
	private Long id;
	private Long locality;
//	private Long delivery;
//	private Long devolution;
	private Long analyst;
	private Long employee;
	private String statusEquipmentOnTerm;
	
	private List<Equipment> equipments = new ArrayList<>();
	private List<Peripheral> peripherals = new ArrayList<>();
		
	public void addEquipment(Equipment equipment) {
		getEquipments().add(equipment);
	}
	
	public void addPeripheral(Peripheral peripheral) {
		getPeripherals().add(peripheral);
	}

}
