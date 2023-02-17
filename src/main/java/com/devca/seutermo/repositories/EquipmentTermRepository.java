package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.EquipmentTerm;
import com.devca.seutermo.entities.EquipmentTermPK;

@Repository
public interface EquipmentTermRepository extends JpaRepository<EquipmentTerm, EquipmentTermPK> {

}
