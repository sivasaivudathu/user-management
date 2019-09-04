/**
 * 
 */
package com.project.usermanagement.service;

import java.util.Optional;

import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.User;

/**
 * @author sivasaiv
 *
 */
public interface UserService {

	public BaseResponse addUser(User user);
	
	public Optional<User> getUserByEmailId(String emailId);
}
