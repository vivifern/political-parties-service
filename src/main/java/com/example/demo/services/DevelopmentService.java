package com.example.demo.services;

import java.util.List;

import com.example.demo.exception.PoliticalException;
import com.example.demo.models.Development;

public interface DevelopmentService {

	public List<Development> findAllPartyLeaderTasks(Integer partyId, Long leaderId) throws PoliticalException;
}
