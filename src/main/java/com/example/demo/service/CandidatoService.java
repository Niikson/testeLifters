package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Candidato;
import com.example.demo.model.Voto;
import com.example.demo.repository.CandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository candidatoRepository;

	@Autowired
	private VotoService votoService;

	public List<Candidato> listar() {
		return candidatoRepository.findAll();
	}

	public Candidato buscarPeloId(@PathVariable UUID id) {
		return candidatoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Candidato criar(Candidato candidato) {
		Candidato candidatoSalvo = candidatoJaExiste(candidato);
		if (candidatoSalvo == null) {
			candidatoSalvo = candidatoRepository.save(candidato);
		}
		return candidatoSalvo;
	}

	public Candidato atualizar(UUID id, Candidato candidato) {
		Candidato candidatoSalvo = candidatoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		// Não existe candidato com esse id para edição
		if (candidatoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		Candidato candidatoJaExiste = candidatoJaExiste(candidato);
		// Não existe candidato com esse número
		if (candidatoJaExiste == null) {
			BeanUtils.copyProperties(candidato, candidatoSalvo, "id");
			candidatoSalvo = candidatoRepository.save(candidatoSalvo);
		} else {
			// O candidato com esse número é o mesmo que estou editando
			if (candidatoJaExiste.getId() == candidato.getId()) {
				BeanUtils.copyProperties(candidato, candidatoSalvo, "id");
				candidatoSalvo = candidatoRepository.save(candidatoSalvo);
			} else {
				throw new RuntimeException("Usuário com esse número já existe");
			}
		}
		return candidatoSalvo;
	}

	public void remover(UUID id) {
		// Verifica se o candidato possui voto computado
		Voto voto = votoService.candidatoPossuiVoto(id);
		if (voto == null) {
			candidatoRepository.deleteById(id);
		}
	}

	// Verifica se existe outro candidato com esse número
	public Candidato candidatoJaExiste(Candidato candidato) {
		Candidato candidatoSalvo = candidatoRepository.findByNumero(candidato.getNumero());
		if (candidatoSalvo != null) {
			return candidatoSalvo;
		}
		return null;
	}

}
