package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserName(String name);
}
