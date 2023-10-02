package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Eleitor;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, UUID> {

	Optional<Eleitor> findById(UUID id);

	void deleteById(UUID id);

	Eleitor findByCpf(Integer cpf);

}
