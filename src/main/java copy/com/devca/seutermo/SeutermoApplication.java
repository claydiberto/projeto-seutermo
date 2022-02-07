package com.devca.seutermo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.Term;
import com.devca.seutermo.entities.enums.EquipmentType;
import com.devca.seutermo.repositories.AnalystRepository;
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
		
		Term t = new Term();
		t.getListOfEquipments().add(e1);
		t.getListOfEquipments().add(e2);
		t.setAnalyst(a1);
		termRepository.save(t);
		
//		List<Term> listEq = termRepository.findEquipmentHasTerm();
//		
//		for (Term te: listEq) {
//			System.out.println(te.getAnalyst().getName());
//			
//			for (Equipment eq: te.getListOfEquipments()) {
//				System.out.println(eq.getFabricator());
//				System.out.println(eq.getSerialNumber());
//			}
//		}
		
		
	}
	
	

}
