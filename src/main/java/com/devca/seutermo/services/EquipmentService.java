package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.entities.enums.EquipmentStatus;
import com.devca.seutermo.repositories.EquipmentRepository;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository repository;
		
	public List<Equipment> findAll() {
		return repository.findAll();
	}
	
	public Equipment findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Equipment getEquipment() {
		Equipment equipment = new Equipment();
		return equipment;
	}
	
	public void save(Equipment equipment) {
		repository.save(equipment);
	}
	
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void changeStatus(Equipment equipment, EquipmentStatus status) {
		if (status == EquipmentStatus.EMPRESTADO) {
			equipment.setEquipmentStatus(EquipmentStatus.EMPRESTADO);
		} else if (status == EquipmentStatus.DANIFICADO) {
			equipment.setEquipmentStatus(EquipmentStatus.DANIFICADO);
		} else {
			equipment.setEquipmentStatus(EquipmentStatus.DISPONIVEL);
		}
		
		save(equipment);		
	}
	 
}
