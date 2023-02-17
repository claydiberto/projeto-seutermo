package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Peripheral;

@Repository
public interface PeripheralRepository extends JpaRepository<Peripheral, Long> {

}
