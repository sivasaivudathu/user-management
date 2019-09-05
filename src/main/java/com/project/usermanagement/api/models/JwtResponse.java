/**
 * 
 */
package com.project.usermanagement.api.models;

/**
 * @author sivasaiv
 *
 */
public class JwtResponse {

	private  String jwttoken;
	
	public JwtResponse() {
		
	}

	public JwtResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
}
