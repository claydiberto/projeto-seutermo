package com.devca.seutermo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Peripheral;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TermDetailsDTO {

	private Long id;
	private String analyst;
	private String employeeName;
	private String employeeEmail;
	private String employeeCpf;
	private String localityCnpj;
	private String localityAddress;
	private String localityDistrict;
	private String localityCep;
	private LocalDateTime deliveryInstant;
	private String deliverySignAnalyst;
	private String deliverySignEmployee;
	private List<Equipment> deliveryEquipments = new ArrayList<>();
	private List<Peripheral> deliveryPeripherals = new ArrayList<>();
	private LocalDateTime devolutionInstant;
	private String devolutionSignAnalyst;
	private String devolutionSignEmployee;
	private List<Equipment> devolutionEquipments = new ArrayList<>();
	
	public void addDeliveryEquipment(Equipment equipment) {
		getDeliveryEquipments().add(equipment);
	}
	
	public void addDevolutionEquipment(Equipment equipment) {
		getDevolutionEquipments().add(equipment);		
	}
	
	public void addPeripheral(Peripheral peripheral) {
		getDeliveryPeripherals().add(peripheral);
	}
	
}
