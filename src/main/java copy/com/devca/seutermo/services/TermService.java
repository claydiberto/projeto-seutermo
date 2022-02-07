package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Term;
import com.devca.seutermo.repositories.TermRepository;

@Service
public class TermService {

	@Autowired
	private TermRepository repository;
		
	public List<Term> findAll() {
		return repository.findAll();
	}
	
	public Term findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Term getTerm() {
		Term term = new Term();
		return term;
	}
	
	public void save(Term term) {
		repository.save(term);
	}
	
	// modificar o satus do termo e dos itens quando fechar o termo
	// nao deletar
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 
}
