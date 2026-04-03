package com.watsappclone.start.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.watsappclone.start.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 User findByEmail(String email);
}