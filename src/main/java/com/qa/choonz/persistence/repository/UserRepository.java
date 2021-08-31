package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.ChoonzUser;

@Repository
public interface UserRepository extends JpaRepository<ChoonzUser, Long>{
	ChoonzUser findByUsername(String username);
}
