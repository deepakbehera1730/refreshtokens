package com.refersh.utils;

public class ApiUrls {

	public static final String AUTH = "/auth";
	public static final String LOGIN = AUTH + "/login";
	public static final String REFRESH_TOKEN = AUTH + "/refresh-token";
	public static final String REGISTRATION = "/registration";
	public static final String USER = "/users";
	public static final String[] SWAGGER_URLS = { "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**",
	 "/swagger-ui/**", "/webjars/**", "/api/swagger-ui/index.html" };

}
