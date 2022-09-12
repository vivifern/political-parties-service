package com.example.demo.services.impl;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.PartyRepository;
import com.example.demo.exception.PoliticalException;
import com.example.demo.models.Development;
import com.example.demo.models.Developments;
import com.example.demo.services.DevelopmentService;

@Service
public class DevelopmentServiceImpl implements DevelopmentService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PartyRepository partyRepo;
	
	private final String URI_FIND_LEADER = "http://localhost:8081/leader/findleaderbyid";
	private final String URI_LEADER_TASKS = "http://localhost:8082/developer/getalldevelopments";
	
	@Override
	public List<Development> findAllPartyLeaderTasks(Integer partyId, Long leaderId) throws PoliticalException {
		if(Objects.isNull(partyRepo.findById(partyId))) 
			throw new PoliticalException("Party with Party Id - " + partyId + " does not exist");
		String uriFindLeader = urlBuilderString(URI_FIND_LEADER, allParams("leaderid"), allParams(String.valueOf(leaderId)));
		ResponseEntity<Long> result = restTemplate.getForEntity(uriFindLeader, Long.class);
		if(Objects.isNull(result) || Objects.isNull(result.getBody()))
			throw new PoliticalException("Leader with Leader Id - " + leaderId + " does not exist");
		String uriFindLeaderTaks = urlBuilderString(URI_LEADER_TASKS, allParams("leaderid"), allParams(String.valueOf(leaderId)));
		ResponseEntity<Developments> resultTasks = restTemplate.getForEntity(uriFindLeaderTaks, Developments.class);
		if(Objects.nonNull(resultTasks) && Objects.nonNull(resultTasks.getBody()) &&
				(!CollectionUtils.isEmpty(resultTasks.getBody().getDevelopments())))
        		return resultTasks.getBody().getDevelopments();
        return null;
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

}
