package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {

	Optional<Cargo> findById(UUID id);

	void deleteById(UUID id);

}
