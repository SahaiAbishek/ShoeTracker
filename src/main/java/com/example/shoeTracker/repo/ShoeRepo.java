package com.example.shoeTracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoeTracker.entity.ShoeEntity;

public interface ShoeRepo extends JpaRepository<ShoeEntity, Long> {
	
	List<ShoeEntity> findByUsers_Id(Long id);

}
