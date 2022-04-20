package com.devca.seutermo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.EquipmentTerm;
import com.devca.seutermo.entities.Locality;
import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.enums.EquipmentType;
import com.devca.seutermo.enums.OperationType;
import com.devca.seutermo.repositories.AnalystRepository;
import com.devca.seutermo.repositories.EmployeeRepository;
import com.devca.seutermo.repositories.EquipmentTermRepository;
import com.devca.seutermo.repositories.LocalityRepository;
import com.devca.seutermo.repositories.PeripheralRepository;
import com.devca.seutermo.repositories.TermRepository;
import com.devca.seutermo.services.EquipmentService;

@SpringBootApplication
public class SeutermoApplication implements CommandLineRunner {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentTermRepository equipmentTermRepository;
	
	
	
	@Autowired
	private TermRepository repository;
	
	@Autowired
	private AnalystRepository analystRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PeripheralRepository peripheralRepository;
	
	@Autowired
	private LocalityRepository localityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SeutermoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Locality l1 = new Locality();
		l1.setName("METROFOR");
		l1.setAddress("RUA PADRE MORORÓ, 441");
		l1.setCep("60010-100");
		l1.setCnpj("31023023000126");
		l1.setDistrict("MOURA BRASIL");
		
		localityRepository.save(l1);
		
		Equipment e1 = new Equipment();
		Equipment e2 = new Equipment();
		Equipment e3 = new Equipment();
		
		e1.setEquipmentType(EquipmentType.NOTEBOOK);
		e1.setFabricator("DELL");
		e1.setModel("LATITUDE");
		e1.setSerialNumber("EFIUE8EF9");
		
		e2.setEquipmentType(EquipmentType.DESKTOP);
		e2.setFabricator("LENOVO");
		e2.setModel("THINKPAD");
		e2.setSerialNumber("JSJS887ASD8");
		
		e3.setEquipmentType(EquipmentType.CHIP);
		e3.setFabricator("CLARO");
		e3.setModel("SIM CARD");
		e3.setSerialNumber("5522546116461321");
				
		equipmentService.save(e1);
		equipmentService.save(e2);
		equipmentService.save(e3);
		
		Analyst a1 = new Analyst(null, "ANTONIO CLAYDIBERTO ALBUQUERQUE DO NASCIMENTO", "ANASCIMENTO@CONSORCIOFTS.COM.BR", "NJSDF9");
		analystRepository.save(a1);
		
		Employee c1 = new Employee();
		c1.setId(1L);
		c1.setName("JORGE SERGIO DA SILVA");
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
		
		Peripheral p3 = new Peripheral();
		p3.setId(3L);
		p3.setName("MONITOR");
		peripheralRepository.save(p3);
		
		Peripheral p4 = new Peripheral();
		p4.setId(4L);
		p4.setName("CHARGER");
		peripheralRepository.save(p4);
		
		Term term = new Term();
		
		term.setAnalyst(a1);
		term.setEmployee(c2);
		term.setLocality(l1);
		term.addPeripheral(p4);
		term.addPeripheral(p1);
		term = repository.saveAndFlush(term);
		
		EquipmentTerm et = new EquipmentTerm();
		et.setEquipment(e1);
		et.setStatusEquipmentOnTerm(OperationType.DELIVERY);
		et.setTerm(term);
		et = equipmentTermRepository.saveAndFlush(et);
		
		term.addEquipment(et);
		
		repository.saveAndFlush(term);
		
	}

}
