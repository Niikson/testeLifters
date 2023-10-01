package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "voto")
public class Voto {

	@NotNull
	private UUID idCandidato;

	@NotNull
	private UUID idEleitor;
	private LocalDateTime data;

	public Voto(UUID idCandidato, UUID idEleitor) {
		this.idCandidato = idCandidato;
		this.idEleitor = idEleitor;
	}

	public UUID getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(UUID idCandidato) {
		this.idCandidato = idCandidato;
	}

	public UUID getIdEleitor() {
		return idEleitor;
	}

	public void setIdEleitor(UUID idEleitor) {
		this.idEleitor = idEleitor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
