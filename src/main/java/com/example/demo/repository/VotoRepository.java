package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

	Voto findByIdEleitor(UUID idEleitor);

	Voto findByIdCandidato(UUID idCandidato);

}
