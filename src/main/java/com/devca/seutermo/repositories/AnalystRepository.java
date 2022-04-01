package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, Long> {
	
	Analyst findByEmail(String email);

}
