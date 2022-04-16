package com.ankit.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepo extends JpaRepository<Country, Long>{
	@Query(" select country.countryName from Country country where country.countryId=:countryId ")
	String getCountryNameById(@Param("countryId") Long countryId);
	
	@Query(" select country.countryId from Country country where country.countryName=:countryName ")
	int getCountryIdByName(@Param("countryName") String countryName);

}
