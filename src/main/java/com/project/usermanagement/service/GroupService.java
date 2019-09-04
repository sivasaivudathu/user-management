/**
 * 
 */
package com.project.usermanagement.service;

import com.project.usermanagement.api.models.AddGroupRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.Group;

/**
 * @author sivasaiv
 *
 */
public interface GroupService {

	public BaseResponse addGroup(AddGroupRequest request);
	
	public Group getGroupByName(String groupName);
}
