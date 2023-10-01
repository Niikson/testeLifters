package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	Cargo findById(UUID id);

	void deleteById(UUID id);

}
