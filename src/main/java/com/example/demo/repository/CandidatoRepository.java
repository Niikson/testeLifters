package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {

	Optional<Candidato> findById(UUID id);

	void deleteById(UUID id);

	Candidato findByNumero(Integer numero);

}
