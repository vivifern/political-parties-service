package com.example.demo.services.impl;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.PartyRepository;
import com.example.demo.exception.PoliticalException;
import com.example.demo.models.Leader;
import com.example.demo.models.Leaders;
import com.example.demo.services.LeaderService;

@Service
public class LeaderServiceImpl implements LeaderService {

	private final String URI_FIND_LEADER = "http://localhost:8081/leader/findleader";
	private final String URI_REMOVE_LEADER = "http://localhost:8081/leader/removeleader";
	private final String URI_FIND_PARTY_LEADER = "http://localhost:8081/leader/findleaderbyparty";
	
	@Autowired
	PartyRepository partyRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Boolean removeLeaderFromParty(Leader leader) throws PoliticalException {
		if(Objects.isNull(partyRepository.findById(leader.getPartyId()))) 
			throw new PoliticalException("Party with Party Id - " + leader.getPartyId() + " does not exist");
        ResponseEntity<Long> result = null;
			String uriFindLeader = urlBuilderString(URI_FIND_LEADER, allParams("leadername", "partyid", "leaderstate"), 
					allParams(leader.getLeaderName(), String.valueOf(leader.getPartyId()), leader.getLeaderState()));
			result = restTemplate.getForEntity(uriFindLeader, Long.class);
        if(Objects.isNull(result) || Long.valueOf(result.getBody()) < 1 )
			throw new PoliticalException("Leader with details - " + leader.toString() + " does not exist");
		else {
			String uriRemoveLeader = urlBuilderString(URI_REMOVE_LEADER, allParams("leaderid"), 
					allParams(String.valueOf(result.getBody())));
			try {
				restTemplate.delete(uriRemoveLeader);
			} catch (RestClientException e) {
				return false;
			}
		}
		return true;
	}
	
	private String urlBuilderString(String url, String[] paramnames, String[] paramvalues) {
		int counter = 0;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			for(String param : paramvalues) {
				uriBuilder.addParameter(paramnames[counter], param);
				counter++;
			}
			return uriBuilder.toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String[] allParams(String... paraNames) {
		return paraNames;
	}

	@Override
	public List<Leader> findAllLeadersFromParty(Integer partyId) throws PoliticalException {
		if(Objects.isNull(partyRepository.findById(partyId).get())) 
			throw new PoliticalException("Party with Party Id - " + partyId + " does not exist");
		String uriFindLeader = urlBuilderString(URI_FIND_PARTY_LEADER, allParams("partyid"), allParams(String.valueOf(partyId)));
		ResponseEntity<Leaders> result = restTemplate.getForEntity(uriFindLeader, Leaders.class);
		return result.getBody().getLeaders();
	}

}
