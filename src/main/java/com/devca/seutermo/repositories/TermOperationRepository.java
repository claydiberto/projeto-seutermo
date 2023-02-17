package com.devca.seutermo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devca.seutermo.entities.TermOperation;

@Repository
public interface TermOperationRepository extends JpaRepository<TermOperation, Long> {

}
