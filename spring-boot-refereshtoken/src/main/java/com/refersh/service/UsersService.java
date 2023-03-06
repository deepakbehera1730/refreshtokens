package com.refersh.service;

import java.util.List;

import com.refersh.entity.Users;

public interface UsersService {
	public List<Users> getAll();

	public Users postData(Users users);

	public Users findById(int id) throws Exception;

	public Users putData(Users users, int id) throws Exception;

	public void deleteById(int id);
}
