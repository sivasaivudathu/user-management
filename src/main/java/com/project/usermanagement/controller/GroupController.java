/**
 * 
 */
package com.project.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.usermanagement.api.models.AddGroupRequest;
import com.project.usermanagement.models.BaseResponse;
import com.project.usermanagement.service.GroupService;

/**
 * @author sivasaiv
 *
 */
@RestController
public class GroupController {

	@Autowired
	GroupService groupService;
	
	@PostMapping("/groups")
	public BaseResponse addGroup(@RequestBody AddGroupRequest request) {
		return groupService.addGroup(request);
	}
}
