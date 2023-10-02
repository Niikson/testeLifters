package com.example.demo.model;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "eleitor")
public class Eleitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "VARCHAR(36)")
	@JdbcTypeCode(Types.VARCHAR)
	private UUID id;

	@NotNull
	private String nome;

	@NotNull
	private Integer cpf;

	@Column(name = "idCargo", columnDefinition = "VARCHAR(36)")
	@JdbcTypeCode(Types.VARCHAR)
	@JsonDeserialize
	private UUID idCargo;
	private LocalDateTime criadoEm;
	private LocalDateTime alteradoEm;
	private LocalDateTime deletadoEm;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public UUID getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(UUID idCargo) {
		this.idCargo = idCargo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAlteradoEm() {
		return alteradoEm;
	}

	public void setAlteradoEm(LocalDateTime alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

	public LocalDateTime getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(LocalDateTime deletadoEm) {
		this.deletadoEm = deletadoEm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alteradoEm, cpf, criadoEm, deletadoEm, id, idCargo, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eleitor other = (Eleitor) obj;
		return Objects.equals(alteradoEm, other.alteradoEm) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(criadoEm, other.criadoEm) && Objects.equals(deletadoEm, other.deletadoEm)
				&& Objects.equals(id, other.id) && Objects.equals(idCargo, other.idCargo)
				&& Objects.equals(nome, other.nome);
	}

}
