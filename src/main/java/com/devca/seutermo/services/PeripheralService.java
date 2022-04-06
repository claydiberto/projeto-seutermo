package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devca.seutermo.entities.Peripheral;
import com.devca.seutermo.repositories.PeripheralRepository;

@Service
public class PeripheralService {
	
	@Autowired
	private PeripheralRepository repository;
		
	public List<Peripheral> findAll() {
		return repository.findAll();
	}

	public Peripheral findById(Long id) {
		return repository.findById(id).get();
	}
	
	public Peripheral getPeripheral() {
		Peripheral peripheral = new Peripheral();
		return peripheral;
	}
	
	public void save(Peripheral peripheral) {
		repository.save(peripheral);
	}


}
