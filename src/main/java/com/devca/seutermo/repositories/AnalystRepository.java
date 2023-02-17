package com.devca.seutermo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Analyst;

@Repository
public interface AnalystRepository extends JpaRepository<Analyst, Long> {
	
	Analyst findByEmail(String email);

	Optional<Analyst> findByEmailContainingIgnoreCase(String email);

}
