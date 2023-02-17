package com.devca.seutermo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
	
	//List<Term> findByEmployeeNameContainingIgnoreCase(String employeeName);

	Page<Term> findByEmployeeNameContainingIgnoreCaseOrderByIdDesc(Pageable pageable, String employeeName);

}
