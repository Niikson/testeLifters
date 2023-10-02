package com.example.demo.model;

import java.io.Serializable;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "candidato")
public class Candidato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", columnDefinition = "VARCHAR(36)")
	@JdbcTypeCode(Types.VARCHAR)
	private UUID id;

	@NotNull
	private String nome;

	@NotNull
	private Integer numero;
	private String legenda;

	@NotNull
	private String cargo;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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
		return Objects.hash(alteradoEm, cargo, criadoEm, deletadoEm, id, legenda, nome, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		return Objects.equals(alteradoEm, other.alteradoEm) && Objects.equals(cargo, other.cargo)
				&& Objects.equals(criadoEm, other.criadoEm) && Objects.equals(deletadoEm, other.deletadoEm)
				&& Objects.equals(id, other.id) && Objects.equals(legenda, other.legenda)
				&& Objects.equals(nome, other.nome) && Objects.equals(numero, other.numero);
	}

}
