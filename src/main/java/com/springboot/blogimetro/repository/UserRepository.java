package com.springboot.blogimetro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blogimetro.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
}
