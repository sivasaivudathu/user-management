/**
 * 
 */
package com.project.usermanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.usermanagement.api.models.AddUserRequest;
import com.project.usermanagement.api.models.JwtRequest;
import com.project.usermanagement.api.models.JwtResponse;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.service.UserService;

/**
 * @author sivasaiv
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public BaseResponse addUser(@RequestBody AddUserRequest request) {
		return userService.addUser(request);
	}

	
	@PostMapping("/authenticate")
	@ResponseBody
	public JwtResponse createAuthenticationToken(@RequestBody @Valid JwtRequest request) throws Exception {
		return userService.createAuthenticationToken(request);
	}

}
