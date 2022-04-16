package com.ankit.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="state")
public class State {
	public State() {}
	
	public State(Long stateId, String stateName,Country country) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.country=country;
	}
	public State(Long stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stateId")
	private Long stateId;
	private String stateName;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="countryId")
	private Country country;
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
