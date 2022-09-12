package com.example.demo.services;

import com.example.demo.models.Party;

public interface PartyService {

	public String addParty(Party party);
	
	public Boolean findParty(String partyId);
}
