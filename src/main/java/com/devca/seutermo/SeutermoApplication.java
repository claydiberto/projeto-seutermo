package com.devca.seutermo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.entities.enums.EquipmentType;
import com.devca.seutermo.entities.enums.TermStatus;
import com.devca.seutermo.repositories.AnalystRepository;
import com.devca.seutermo.repositories.EmployeeRepository;
import com.devca.seutermo.repositories.PeripheralRepository;
import com.devca.seutermo.repositories.TermRepository;
import com.devca.seutermo.services.EquipmentService;

@SpringBootApplication
public class SeutermoApplication implements CommandLineRunner {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private AnalystRepository analystRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PeripheralRepository peripheralRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SeutermoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Equipment e1 = new Equipment();
		Equipment e2 = new Equipment();
		
		e1.setEquipmentType(EquipmentType.NOTEBOOK);
		e1.setFabricator("DELL");
		e1.setModel("LATITUDE");
		e1.setSerialNumber("EFIUE8EF9");
		
		e2.setEquipmentType(EquipmentType.DESKTOP);
		e2.setFabricator("LENOVO");
		e2.setModel("THINKPAD");
		e2.setSerialNumber("JSJS887ASD8");
				
		equipmentService.save(e1);
		equipmentService.save(e2);
		
		Analyst a1 = new Analyst(null, "ANTONIO", "ANASCIMENTO@CONSORCIOFTS.COM.BR", "METROFOR", "NJSDF9");
		analystRepository.save(a1);
		
		
		Employee c1 = new Employee();
		c1.setId(1L);
		c1.setName("JORGE");
		c1.setEmail("JORGE@FTS.COM.BR");
		c1.setCpf("546231561");
		employeeRepository.save(c1);
		
		Employee c2 = new Employee();
		c2.setId(2L);
		c2.setName("MARIA");
		c2.setEmail("MARIA@FTS.COM.BR");
		c2.setCpf("278349861");
		employeeRepository.save(c2);
		
		Peripheral p1 = new Peripheral();
		p1.setId(1L);
		p1.setName("MOUSE");
		peripheralRepository.save(p1);
		
		Peripheral p2 = new Peripheral();
		p2.setId(2L);
		p2.setName("KEYBOARD");
		peripheralRepository.save(p2);

		
		Term t = new Term();
		t.getListOfPeripherals().add(p1);
		t.getListOfPeripherals().add(p2);
		t.setMoment(LocalDateTime.now());
		t.setTermStatus(TermStatus.ENTREGUE);
		t.getListOfEquipments().add(e1);
		t.getListOfEquipments().add(e2);
		t.setAnalyst(a1);
		t.setEmployee(c1);
		termRepository.save(t);
		
	}

}
