package com.example.demo.services.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PartyRepository;
import com.example.demo.models.Party;
import com.example.demo.services.PartyService;

@Service
public class PartyServiceImpl implements PartyService{
	
	@Autowired
	PartyRepository partyRepo;

	@Override
	public String addParty(Party party) {
		String confirmationString = "Party Added";
		if(!party.equals(partyRepo.save(party)))
			confirmationString = "Party could not be Added";
		return confirmationString;
	}

	@Override
	public Boolean findParty(String partyId) {
		return StringUtils.equals(partyId, String.valueOf(
				partyRepo.findById(Integer.valueOf(partyId)).get().getId()));
	}

}
