package com.example.shoeTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shoeTracker.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

	@Query("SELECT p FROM UserEntity p WHERE LOWER(p.userName) = LOWER(:userName) and p.password = (:password)") 
    public UserEntity find(@Param("userName") String userName,@Param("password") String password);
}
