package com.example.demo.models;

import java.io.Serializable;

public class Leader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer partyId;
	private String leaderName;
	private String leaderState;
	
	public Leader() {
	}
	
	public Leader(Integer partyId, String leaderName, String leaderState) {
		this.partyId = partyId;
		this.leaderName = leaderName;
		this.leaderState = leaderState;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getLeaderState() {
		return leaderState;
	}
	public void setLeaderState(String leaderState) {
		this.leaderState = leaderState;
	}

	@Override
	public String toString() {
		return "Leader [id=" + id + ", partyId=" + partyId + ", leaderName=" + leaderName + ", leaderState="
				+ leaderState + "]";
	}
	
}
