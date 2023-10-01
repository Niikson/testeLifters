package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

	Voto findVotoEleitor(UUID idEleitor);

	Voto findVotoCandidato(UUID idCandidato);

}
