package com.devca.seutermo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	@Query(value = "SELECT * FROM TB_EQUIPMENT e WHERE e.EQUIPMENT_STATUS = 'DISPONIVEL'", 
			nativeQuery = true)
	List<Equipment> findAvailable();

	Page<Equipment> findBySerialNumberContainingIgnoreCase(Pageable pageable, String serialNumber);

}
