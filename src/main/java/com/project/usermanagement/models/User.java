package com.project.usermanagement.models;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "name")
	private String name;

	private String password;

	@Column(name = "active")
	private boolean isActive;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "phone_no")
	private String phoneNumber;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups;

	public User() {

	}

	
	public User(String name, String password, boolean isActive, String emailId, String phoneNumber) {
		super();
		this.name = name;
		this.password = password;
		this.isActive = isActive;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @param user
	 */
	public User(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.isActive = user.isActive();
		this.emailId = user.getEmailId();
		this.phoneNumber = user.getPhoneNumber();
		this.roles = user.getRoles();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void addRole(Role role) {
		if (role != null) {
			if (roles == null) {
				roles = new HashSet<>();
			}
			roles.add(role);
		}
	}

	public void addGroup(Group group) {
		if (group != null) {
			if (groups == null) {
				groups = new HashSet<>();
			}
			groups.add(group);
		}

	}
}
