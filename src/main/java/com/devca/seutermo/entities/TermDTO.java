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
	private List<Equipment> listOfEquipments = new ArrayList<>();
	
	private String employeeSubscription;
	private String analystSubscription;
	
	public void addEquipment(Equipment equipment) {
		getListOfEquipments().add(equipment);
	}

}
