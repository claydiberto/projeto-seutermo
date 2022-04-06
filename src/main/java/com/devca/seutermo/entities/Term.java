package com.devca.seutermo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devca.seutermo.entities.enums.TermStatus;

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

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String employeeSubscription;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private String analystSubscription;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_term_equipment", joinColumns = @JoinColumn(name = "term_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> listOfEquipments = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_term_peripherals", joinColumns = @JoinColumn(name = "term_id"), inverseJoinColumns = @JoinColumn(name = "peripheral_id"))
	private List<Peripheral> listOfPeripherals = new ArrayList<>();

	private LocalDateTime moment;
	private TermStatus termStatus;

	public void addEquipment(Equipment equipment) {
		getListOfEquipments().add(equipment);
	}

	public void addPeripheral(Peripheral peripheral) {
		getListOfPeripherals().add(peripheral);
	}

}
