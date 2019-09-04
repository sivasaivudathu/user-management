/**
 * 
 */
package com.project.usermanagement.service;

import com.project.usermanagement.api.models.AddRoleRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.Role;

/**
 * @author sivasaiv
 *
 */
public interface RoleService {

	public BaseResponse addRole(AddRoleRequest request);
	
	public Role getRoleByName(String roleName);
}
