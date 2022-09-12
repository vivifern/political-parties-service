package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PoliticalException;
import com.example.demo.models.Leader;
import com.example.demo.models.Party;
import com.example.demo.services.DevelopmentService;
import com.example.demo.services.LeaderService;
import com.example.demo.services.PartyService;

@RestController
@RequestMapping("/party")
public class PartyController {

	@Autowired
	public PartyService partyService;
	
	@Autowired
	public LeaderService leaderService;
	
	@Autowired
	public DevelopmentService developmentService;
	
	@PostMapping(path = "/addparty")
	public ResponseEntity<?> addParty(@Valid @RequestBody Party party) {
		return ResponseEntity.ok(partyService.addParty(party));
	}
	
	@GetMapping(path = "/findParty")
	public ResponseEntity<?> findParty(@RequestParam String partyid){
		return ResponseEntity.ok(partyService.findParty(partyid));
	}
	
	@DeleteMapping(path = "/deleteleader")
	public ResponseEntity<?> deleteLeader(@RequestBody Leader leader) throws PoliticalException{
		return ResponseEntity.ok(leaderService.removeLeaderFromParty(leader));
	}
	
	@GetMapping(path = "/findpartyleaders")
	public ResponseEntity<?> findPartyLeaders(@RequestParam Integer partyid) throws PoliticalException{
		return ResponseEntity.ok(leaderService.findAllLeadersFromParty(partyid));
	}
	
	@GetMapping(path = "/findpartyleaderstasks")
	public ResponseEntity<?> findPartyLeadersTasks(@RequestParam String partyid, @RequestParam String leaderid) throws PoliticalException{
		return ResponseEntity.ok(developmentService.findAllPartyLeaderTasks
				(Integer.valueOf(partyid), Long.valueOf(leaderid)));
	}
}
