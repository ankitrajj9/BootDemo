package com.ankit.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepo extends JpaRepository<City, Long>{
	@Query(" from City city where city.state.stateId=:stateId ")
	List<City> getCityByStateId(@Param("stateId") Long stateId);
	
	@Query(" select city.cityName from City city where city.cityId=:cityId ")
	String getCityNameById(@Param("cityId") Long cityId);
	
	@Query(" select city.cityId from City city where city.cityName=:cityName ")
	int getCityIdByName(@Param("cityName") String cityName);
	
	@Query(" from City city where city.state.stateId=:stateId ")
	List<City> getCitiesByStateId(@Param("stateId") Long stateId);
}
