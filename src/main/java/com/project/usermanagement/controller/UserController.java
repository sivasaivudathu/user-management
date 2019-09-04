/**
 * 
 */
package com.project.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.User;
import com.project.usermanagement.service.UserService;

/**
 * @author sivasaiv
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public BaseResponse addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping(value = "/username")
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		return authentication.getName();
	}

}
