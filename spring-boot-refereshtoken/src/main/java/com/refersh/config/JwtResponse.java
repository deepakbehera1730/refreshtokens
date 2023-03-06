package com.refersh.config;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	

	public JwtResponse(String jwtToken, String refreshToken) {
		super();
		this.jwtToken = jwtToken;
		this.refreshToken = refreshToken;
	}

	private final String jwtToken;
	private final String refreshToken;


	public String getJwtToken() {
		return jwtToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	

	
}
