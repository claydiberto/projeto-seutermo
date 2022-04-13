package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.EquipmentTerm;
import com.devca.seutermo.entities.EquipmentTermPK;

public interface EquipmentTermRepository extends JpaRepository<EquipmentTerm, EquipmentTermPK> {

}
