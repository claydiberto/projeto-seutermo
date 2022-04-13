package com.devca.seutermo;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devca.seutermo.dto.TermDTO;
import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.entities.Employee;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Locality;
import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.enums.EquipmentType;
import com.devca.seutermo.repositories.AnalystRepository;
import com.devca.seutermo.repositories.EmployeeRepository;
import com.devca.seutermo.repositories.LocalityRepository;
import com.devca.seutermo.repositories.PeripheralRepository;
import com.devca.seutermo.services.EquipmentService;
import com.devca.seutermo.services.TermService;

@SpringBootApplication
public class SeutermoApplication implements CommandLineRunner {
	
	@Autowired
	private EquipmentService equipmentService;
	
	
	@Autowired
	private TermService termService;
	
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

//		Term t = new Term();
//		t.getListOfPeripherals().add(p1);
//		t.getListOfPeripherals().add(p2);
//		t.getListOfPeripherals().add(p3);
//		t.getListOfPeripherals().add(p4);
//		t.setMoment(LocalDateTime.now());
//		//t.setTermStatus(TermStatus.ENTREGUE);
//		t.getListOfEquipments().add(e1);
//		t.getListOfEquipments().add(e2);
//		t.getListOfEquipments().add(e3);
//		t.setAnalyst(a1);
//		t.setEmployee(c1);
//		t.setAnalystSubscription("data:image/png;base64,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\n"
//				+ "");
//		t.setEmployeeSubscription("data:image/png;base64,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\n"
//				+ "");
//		//termRepository.save(t);
		
		
//		
//			TermDTO termo = new TermDTO();
//			termo.setAnalyst(a1.getEmail());
//			termo.setEmployee(c2.getEmail());
//			termo.addEquipment(e1);
//			termo.addEquipment(e2);
//			//termo.setEquipmentTermStatus("ENTREGUE");
//			termo.addPeripheral(p4);
//			termo.addPeripheral(p2);
//			
//			
//			
//			long id = termService.saveTermDTO(termo);
//
//			Term term = termService.findById(id);
//			term.setLocality(l1);
//			termService.save(term);
		
			
			
		
			
//		
//		System.out.println("========== TERMO DATA ==========");
//		System.out.println(termo1.getAnalyst().getEmail());
//		for (EquipmentTerm equi : termo1.getEquipmentList()) {
//			System.out.println(equi.getEquipment().getSerialNumber());
//			System.out.println(equi.getEquipmentStatus());
//		}
//		
//		
//		Long iddd = termService.saveTerm(termo1);
		
//		System.out.println(iddd);
	
		
		
		
	}

}
