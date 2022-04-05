package com.devca.seutermo.entities;

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
	
//	private Instant moment;
//	private StatusTerm statusTerm;
	
	@ManyToOne
	@JoinColumn(name = "analyst_id")
	private Analyst analyst;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_term_equipment",
			joinColumns = @JoinColumn(name = "term_id"),
			inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> listOfEquipments = new ArrayList<>();
	
	@Lob @Basic(fetch = FetchType.LAZY)
	private String employeeSubscription;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	private String analystSubscription;
	
	public void addEquipment(Equipment equipment) {
		getListOfEquipments().add(equipment);
	}
	
	//	
//	private List<Peripherals> peripherals = new ArrayList<>();

}
