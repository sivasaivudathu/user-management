/**
 * 
 */
package com.project.usermanagement.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.usermanagement.api.models.AddRoleRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.Role;
import com.project.usermanagement.repository.RolesRepository;
import com.project.usermanagement.service.RoleService;

/**
 * @author sivasaiv
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RolesRepository roleRepository;
	
	@Override
	public BaseResponse addRole(AddRoleRequest request) {
		String roleName = request.getRoleName().toUpperCase();
		if(isRoleExists(roleName)) {
			return new BaseResponse("FAILED", "Role alredy exixts with name :"+ roleName);
		}
		Role role = new Role(roleName);
		roleRepository.save(role);
		return new BaseResponse("SUCCESS","Role Added Successfully");
	}
	
	private boolean isRoleExists(String roleName) {
		Role role = getRoleByName(roleName);
	    return !Objects.isNull(role);
	}

	@Override
	public Role getRoleByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
