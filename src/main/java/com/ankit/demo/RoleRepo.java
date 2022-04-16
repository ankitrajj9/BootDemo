package com.ankit.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<State, Long> {
	
}
