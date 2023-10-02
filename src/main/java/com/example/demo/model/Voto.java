package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "voto")
public class Voto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", columnDefinition = "VARCHAR(36)")
	private UUID id;

	@NotNull
	private UUID idCandidato;

	@NotNull
	private UUID idEleitor;
	private LocalDateTime data;
	
	public Voto() {
		
	}

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
