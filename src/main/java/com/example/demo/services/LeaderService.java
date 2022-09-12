package com.example.demo.services;

import java.util.List;

import com.example.demo.exception.PoliticalException;
import com.example.demo.models.Leader;

public interface LeaderService {

	public Boolean removeLeaderFromParty(Leader leader) throws PoliticalException;
	
	public List<Leader> findAllLeadersFromParty(Integer partyId) throws PoliticalException;
	
}
