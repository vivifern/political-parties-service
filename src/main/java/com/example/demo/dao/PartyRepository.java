package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Party;

public interface PartyRepository extends CrudRepository<Party, Integer>{

	public List<Party> findByName(String name);
	
	public Optional<Party> findById(Integer id);
	
}
