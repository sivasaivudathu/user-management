/**
 * 
 */
package com.project.usermanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.User;
import com.project.usermanagement.repository.UsersRepository;
import com.project.usermanagement.service.UserService;

/**
 * @author sivasaiv
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userRepo;

	@Override
	public BaseResponse addUser(User user) {
		BaseResponse response = new BaseResponse();

		if (isUserExists(user.getEmailId())) {
			response.setStatus("FAILED");
			response.setMessage("User Already Exists");
			return response;
		}
		String encryptedpass = encryptPassword(user.getPassword());
		user.setPassword(encryptedpass);
		user.setActive(true);
		userRepo.save(user);
		response.setStatus("SUCCESS");
		response.setMessage("User created SucessFully");
		return response;
	}

	@Override
	public Optional<User> getUserByEmailId(String emailId) {
		return userRepo.findByEmailId(emailId);
	}

	private boolean isUserExists(String emailId) {
		Optional<User> optionalUser = getUserByEmailId(emailId);
		return optionalUser.isPresent();
 	}
	
	private String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
