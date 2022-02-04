package com.devca.seutermo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devca.seutermo.entities.Term;

public interface TermRepository extends JpaRepository<Term, Long> {
	
	@Query("SELECT t FROM Term t JOIN t.listOfEquipments")
	List<Term> findEquipmentHasTerm();

}
