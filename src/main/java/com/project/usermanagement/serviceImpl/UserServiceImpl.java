/**
 * 
 */
package com.project.usermanagement.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.usermanagement.api.models.AddUserRequest;
import com.project.usermanagement.api.models.JwtRequest;
import com.project.usermanagement.api.models.JwtResponse;
import com.project.usermanagement.exceptions.RecordNotFoundException;
import com.project.usermanagement.jwt.JwtTokenUtil;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.Role;
import com.project.usermanagement.models.User;
import com.project.usermanagement.repository.UsersRepository;
import com.project.usermanagement.service.RoleService;
import com.project.usermanagement.service.UserService;

/**
 * @author sivasaiv
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userRepo;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	RoleService roleService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public BaseResponse addUser(AddUserRequest request) {
		BaseResponse response = new BaseResponse();

		if (isUserExists(request.getEmailId())) {
			response.setStatus("FAILED");
			response.setMessage("User Already Exists");
			return response;
		}
		String encryptedpass = encryptPassword(request.getPassword());
		
		User user = new User(request.getName(), encryptedpass, true, request.getEmailId(), request.getPhoneNumber());

		Role role = getRole(request.getRole().toUpperCase());
		user.addRole(role);
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

	@Override
	public JwtResponse createAuthenticationToken(JwtRequest request) throws Exception  {
		
		authenticate(request.getUsername(), request.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String token = getToken(userDetails);
		return new JwtResponse("Bearer "+token);
	}

	private String getToken(final UserDetails userDetails) {
		return jwtTokenUtil.generateToken(userDetails);
	}

	private void authenticate(String userName, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	public Role getRole(String roleName) {
		Role role = roleService.getRoleByName(roleName);
		if(Objects.isNull(role)) {
			throw new RecordNotFoundException("Role Doesn't Exists :"+roleName);
		}
		return role;
	}
}
