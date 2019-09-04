package com.project.usermanagement.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.usermanagement.models.User;

@Transactional
public interface UsersRepository extends JpaRepository<User, Integer> {
  
	public Optional<User>  findByName(String name);
	
	public Optional<User> findByEmailId(String emailId);
    
}
