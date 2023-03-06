package com.refersh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.refersh.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);

}
