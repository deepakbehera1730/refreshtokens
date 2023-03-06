package com.refersh.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.refersh.component.JwtServiceDetail;
import com.refersh.config.JwtResponse;
import com.refersh.config.JwtUtil;
import com.refersh.dto.ErrorResponseDto;
import com.refersh.dto.SuccessResponseDto;
import com.refersh.entity.Users;
import com.refersh.repo.UsersRepo;
import com.refersh.serviceimpl.UsersServiceImpl;
import com.refersh.utils.ApiUrls;

@RestController
public class AuthController {
	@Autowired
	private UsersServiceImpl impl;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private JwtServiceDetail userDetailsService;
	@Autowired
	private UsersRepo usersRepo;
	Random r=new Random(1000);

	@PostMapping(ApiUrls.REGISTRATION)
	public String addUsers(@RequestBody Users users) {
		impl.postData(users);
		return "Users Added";

	}

	@PostMapping(ApiUrls.LOGIN)
	public ResponseEntity<?> createUserToken(@RequestBody Users authenticationRequest) {

		try {
			
			impl.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final String refreshToken = jwtTokenUtil.refreshToken(token, userDetails);
		return new  ResponseEntity<>(new SuccessResponseDto("Success","Success",new JwtResponse(token, refreshToken)),HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),e.getMessage()),HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(ApiUrls.REFRESH_TOKEN)
	public ResponseEntity<?> refreshAuthenticationToken(@RequestParam(defaultValue = "") String refreshToken) {
		String name = jwtTokenUtil.extractUsername(refreshToken);
		Users users = usersRepo.findByUsername(name);
		if (users == null) {
			return new ResponseEntity<>("InvalidUser", HttpStatus.UNAUTHORIZED);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(name);

		if (jwtTokenUtil.canTokenBeRefreshed(refreshToken) && jwtTokenUtil.validateToken(refreshToken, userDetails)
				&& jwtTokenUtil.getTokenType(refreshToken).equalsIgnoreCase("refresh")) {
			String newaccessToken = jwtTokenUtil.generateToken(userDetails);
			return new ResponseEntity<>(new SuccessResponseDto("Success","Success",newaccessToken),HttpStatus.OK);
		} else {
			return new ResponseEntity<>("invalid Users", HttpStatus.UNAUTHORIZED);
		}

	}
	@PostMapping("/ForgetPassword")
	public ResponseEntity<?> generateOtp(@RequestParam String email)
	{
		System.out.println("Email:"+email);
		
		
		int opt=r.nextInt(99999);
		return new ResponseEntity<>(opt,HttpStatus.OK);
	}

}
