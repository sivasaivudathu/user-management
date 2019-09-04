/**
 * 
 */
package com.project.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.usermanagement.models.Role;

/**
 * @author sivasaiv
 *
 */
public interface RolesRepository extends JpaRepository<Role,Integer> {

	public Role findByRoleName(String roleName);
}
