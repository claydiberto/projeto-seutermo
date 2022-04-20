package com.devca.seutermo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Term;

public interface TermRepository extends JpaRepository<Term, Long> {
	
	List<Term> findByEmployeeNameContainingIgnoreCase(String employeeName);	 

}
