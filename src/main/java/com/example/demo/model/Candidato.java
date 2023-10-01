package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "candidato")
public class Candidato {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	@NotNull
	private String nome;

	@NotNull
	@Size(min = 2, max = 5)
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
