/**
 * 
 */
package com.project.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.usermanagement.api.models.AddRoleRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.service.RoleService;

/**
 * @author sivasaiv
 *
 */
@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@PostMapping("/roles")
	public BaseResponse addRole(@RequestBody AddRoleRequest request) {
		return roleService.addRole(request);
	}
}
