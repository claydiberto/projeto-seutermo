package com.devca.seutermo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Term;

public interface TermRepository extends JpaRepository<Term, Long> {
	
	//List<Term> findByEmployeeNameContainingIgnoreCase(String employeeName);

	Page<Term> findByEmployeeNameContainingIgnoreCaseOrderByIdDesc(Pageable pageable, String employeeName);

}
