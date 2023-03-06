package com.refersh.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.refersh.entity.Users;
import com.refersh.repo.UsersRepo;

@Service
public class JwtServiceDetail implements UserDetailsService {

	@Autowired
	private UsersRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("404 user not found");
		}
		return new PrincipalService(user);

	}
}
