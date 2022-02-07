package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devca.seutermo.entities.Term;

public interface TermRepository extends JpaRepository<Term, Long> {

}
