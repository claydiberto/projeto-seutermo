package com.devca.seutermo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devca.seutermo.entities.Analyst;
import com.devca.seutermo.repositories.AnalystRepository;

@Service
public class AnalystService implements UserDetailsService {

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
		analyst.setPassword(new BCryptPasswordEncoder().encode(analyst.getPassword()));
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

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Analyst analyst = repository.findByEmailContainingIgnoreCase(email)
				.orElseThrow(() -> new UsernameNotFoundException("O usuário " + email + " não foi encontrado."));
		return new User(analyst.getEmail(), analyst.getPassword(), true, true, true, true, analyst.getAuthorities());
	}

}
