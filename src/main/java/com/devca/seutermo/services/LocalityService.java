package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Locality;
import com.devca.seutermo.repositories.LocalityRepository;

@Service
public class LocalityService {
	
	@Autowired
	private LocalityRepository repository;
		
	public List<Locality> findAll() {
		return repository.findAll();
	}

	public Locality findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Locality getLocality() {
		Locality locality = new Locality();
		return locality;
	}
	
	public void save(Locality locality) {
		repository.save(locality);
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
