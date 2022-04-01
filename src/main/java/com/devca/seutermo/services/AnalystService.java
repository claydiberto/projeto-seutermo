package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.repositories.AnalystRepository;

@Service
public class AnalystService {

	@Autowired
	private AnalystRepository repository;
		
	public List<Analyst> findAll() {
		return repository.findAll();
	}
	
	public Analyst findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Analyst findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Analyst getAnalyst() {
		Analyst analyst = new Analyst();
		return analyst;
	}
	
	public void save(Analyst analyst) {
		repository.save(analyst);
	}

	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	 
}
