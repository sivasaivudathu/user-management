/**
 * 
 */
package com.project.usermanagement.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.usermanagement.api.models.AddGroupRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.models.Group;
import com.project.usermanagement.repository.GroupRepository;
import com.project.usermanagement.service.GroupService;

/**
 * @author sivasaiv
 *
 */
@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupRepository groupRepository;
	

	@Override
	public BaseResponse addGroup(AddGroupRequest request) {
		String groupName = request.getGroupName();
		if(isGroupExists(groupName)) {
			return new BaseResponse("FAILURE", "Group exists with name :"+ groupName);
		}
		
		Group group = new Group(groupName);
		groupRepository.save(group);
		return new BaseResponse("SUCCESS", "Group Created Successfully");
	}

	@Override
	public Group getGroupByName(String groupName) {
		return groupRepository.findByName(groupName);
	}
	
	private boolean isGroupExists(String groupName) {
		Group group = getGroupByName(groupName);
		return !Objects.isNull(group);
	}
}
