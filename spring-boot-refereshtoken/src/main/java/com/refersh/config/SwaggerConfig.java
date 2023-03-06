package com.refersh.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER= "Authorization";
	
	private ApiKey apikeys()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	private List<SecurityContext> securityContext()
	{
		return  Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	private List<SecurityReference> sf()

	{
		AuthorizationScope scopes=new AuthorizationScope("globel", "acessEverything"); 
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContext()).securitySchemes(Arrays.asList(apikeys())).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo getInfo() {
	
		return new ApiInfo("Refresh Token", "Using Spring Boot" ,"Designed By Deepak Behera","Email:Beherad592@gmail.com","mobile no:8626042810", "Api Urls	","Apis");  
	}

}
