package com.refersh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refersh.dto.ErrorResponseDto;
import com.refersh.dto.SuccessResponseDto;
import com.refersh.entity.Users;

import com.refersh.service.UsersService;
import com.refersh.utils.ApiUrls;

@RestController
@RequestMapping(ApiUrls.USER)
public class UsersController {
	@Autowired
	private UsersService service;

	@GetMapping()
	public ResponseEntity<?> findAllUsers() {

		List<Users> users = service.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "DataNotFound"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", users), HttpStatus.OK);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserByID(@PathVariable int id) {
		try {
			Users users = service.findById(id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", users), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Id not Found", e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUsers(@RequestBody Users users, @PathVariable int id) {
		try {
			service.putData(users, id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Id not Found", e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable int id) {
		return new ResponseEntity<>(new SuccessResponseDto("Success", "Sucesss", null), HttpStatus.OK);
	}
}
