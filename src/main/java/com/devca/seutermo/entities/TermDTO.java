package com.devca.seutermo.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TermDTO {
	
	private Long id;
	private String analyst;
	private String employee;
	private String employeeSubscription;
	private String analystSubscription;
	private List<Equipment> listOfEquipments = new ArrayList<>();
	private List<Peripheral> listOfPeripherals = new ArrayList<>();
	private String moment;
	private String termStatus;		
	
	public void addEquipment(Equipment equipment) {
		getListOfEquipments().add(equipment);
	}
	
	public void addPeripheral(Peripheral peripheral) {
		getListOfPeripherals().add(peripheral);
	}

}
