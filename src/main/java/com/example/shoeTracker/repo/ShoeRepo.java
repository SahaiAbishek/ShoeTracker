package com.example.shoeTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoeTracker.entity.ShoeEntity;

public interface ShoeRepo extends JpaRepository<ShoeEntity, Long> {

}
