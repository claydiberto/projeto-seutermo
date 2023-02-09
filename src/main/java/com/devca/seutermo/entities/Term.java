package com.devca.seutermo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_term")
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "analyst_id")
	private Analyst analyst;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@OneToMany
	@JoinColumn(name = "term_id")
	private List<EquipmentTerm> equipments = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_term_peripherals", joinColumns = @JoinColumn(name = "term_id"), inverseJoinColumns = @JoinColumn(name = "peripheral_id"))
	private List<Peripheral> peripherals = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "locality_id")
	private Locality locality;
	
	@OneToOne
	private TermOperation delivery;
	
	@OneToOne
	private TermOperation devolution;
	
	public void addPeripheral(Peripheral peripheral) {
		getPeripherals().add(peripheral);
	}
	
	public void addEquipment(EquipmentTerm equipmentTerm) {
		getEquipments().add(equipmentTerm);
	}

}
