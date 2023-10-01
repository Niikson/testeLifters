package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Eleitor;
import com.example.demo.model.Voto;
import com.example.demo.repository.EleitorRepository;

@Service
public class EleitorService {

	@Autowired
	private EleitorRepository eleitorRepository;

	@Autowired
	private VotoService votoService;

	public Eleitor criar(Eleitor eleitor) {
		Eleitor eleitorSalvo = eleitorJaExiste(eleitor);
		if (eleitorSalvo == null) {
			eleitorSalvo = eleitorRepository.save(eleitor);
		}
		return eleitorSalvo;
	}

	public Eleitor atualizar(UUID id, Eleitor eleitor) {
		Eleitor eleitorSalvo = eleitorRepository.findById(id);
		// Não existe eleitor com esse id para edição
		if (eleitorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		Eleitor eleitorJaExiste = eleitorJaExiste(eleitor);
		// Não existe eleitor com esse cpf
		if (eleitorJaExiste == null) {
			BeanUtils.copyProperties(eleitor, eleitorSalvo, "id");
			eleitorSalvo = eleitorRepository.save(eleitorSalvo);
		} else {
			// O eleitor com esse cpf é o mesmo que estou editando
			if (eleitorJaExiste.getId() == eleitor.getId()) {
				BeanUtils.copyProperties(eleitor, eleitorSalvo, "id");
				eleitorSalvo = eleitorRepository.save(eleitorSalvo);
			} else {
				throw new RuntimeException("Usuário com esse número já existe");
			}
		}
		return eleitorSalvo;
	}

	public void remover(UUID id) {
		// Verifica se o eleitor realizou algum voto
		Voto voto = votoService.eleitorJaVotou(id);
		if (voto == null) {
			eleitorRepository.deleteById(id);
		}
	}

	// Verifica se existe outro eleitor com esse cpf
	public Eleitor eleitorJaExiste(Eleitor eleitor) {
		Eleitor eleitorSalvo = eleitorRepository.findByCPF(eleitor.getCpf());
		if (eleitorSalvo != null) {
			return eleitorSalvo;
		}
		return null;
	}

}
