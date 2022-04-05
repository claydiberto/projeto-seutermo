package com.devca.seutermo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devca.seutermo.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	@Query(value = "SELECT * FROM TB_EQUIPMENT e WHERE e.EQUIPMENT_STATUS = 0", 
			nativeQuery = true)
	List<Equipment> findAvailable();

}
