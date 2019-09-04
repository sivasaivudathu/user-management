/**
 * 
 */
package com.project.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.usermanagement.models.Group;

/**
 * @author sivasaiv
 *
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

	public Group findByName(String groupName);
}
