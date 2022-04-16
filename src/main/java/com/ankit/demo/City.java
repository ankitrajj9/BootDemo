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
@Table(name="city")
public class City {
	
public City() {}


public City(Long cityId, String cityName, State state) {
	super();
	this.cityId = cityId;
	this.cityName = cityName;
	this.state = state;
}
public City(Long cityId, String cityName) {
	super();
	this.cityId = cityId;
	this.cityName = cityName;
}

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="cityId")
private Long cityId;
private String cityName;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="stateId")
private State state;
public Long getCityId() {
	return cityId;
}
public void setCityId(Long cityId) {
	this.cityId = cityId;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public State getState() {
	return state;
}
public void setState(State state) {
	this.state = state;
}

}
