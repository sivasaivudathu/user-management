/**
 * 
 */
package com.project.usermanagement.service;

import java.util.Optional;

import com.project.usermanagement.api.models.AddUserRequest;
import com.project.usermanagement.api.models.JwtRequest;
import com.project.usermanagement.api.models.JwtResponse;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.User;

/**
 * @author sivasaiv
 *
 */
public interface UserService {

	public BaseResponse addUser(AddUserRequest request);
	
	public Optional<User> getUserByEmailId(String emailId);
	
	public JwtResponse createAuthenticationToken(JwtRequest request)  throws Exception;
}
