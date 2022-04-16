package com.ankit.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StateRepo extends JpaRepository<State, Long>{
	@Query(" from State state where state.country.countryId=:countryId ")
	List<State> getStatesByCountryId(@Param("countryId") Long countryId);
	
	/*
	 * @Query(" select state.stateName from State state where state.stateId:stateId "
	 * ) String getStateNameById(@Param("stateId") Long stateId);
	 * 
	 * @Query(" select state.stateId from State state where state.stateName=:stateName "
	 * ) int getStateIdByName(@Param("stateName") String stateName);
	 */
	@Query(" select state.stateName from State state where state.stateId=:stateId ")
	String getStateNameById(@Param("stateId") Long stateId);
	
	@Query(" select state.stateId from State state where state.stateName=:stateName ")
	int getStateIdByName(@Param("stateName") String stateName);

}
