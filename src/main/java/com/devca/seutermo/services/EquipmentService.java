package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devca.seutermo.entities.Equipment;
import com.devca.seutermo.enums.EquipmentStatus;
import com.devca.seutermo.repositories.EquipmentRepository;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository repository;

	@Transactional(readOnly = true)
	public List<Equipment> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Equipment> findAvailable() {
		return repository.findAvailable();
	}

	@Transactional(readOnly = true)
	public Equipment findById(Long id) {
		return repository.findById(id).get();
	}

	public Equipment getEquipment() {
		Equipment equipment = new Equipment();
		return equipment;
	}

	@Transactional(readOnly = true)
	public Page<Equipment> getEquipmentPageable(int currentPage, int size, String serialNumber) {
		Pageable pageable = PageRequest.of(currentPage, size, Sort.by(Sort.Direction.DESC, "id"));
		if (serialNumber != null) {
			return repository.findBySerialNumberContainingIgnoreCase(pageable, serialNumber);
		} else {
			return repository.findAll(pageable);
		}
	}

	@Transactional
	public void save(Equipment equipment) {
		repository.save(equipment);
	}

	@Transactional
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public void changeStatus(Equipment equipment, EquipmentStatus status) {
		equipment.setEquipmentStatus(status);
		save(equipment);
	}

}
