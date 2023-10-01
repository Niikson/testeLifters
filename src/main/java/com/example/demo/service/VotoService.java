package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Voto;
import com.example.demo.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;

	public Voto registrarVoto(UUID idCandidato, UUID idEleitor) {
		Voto voto = eleitorJaVotou(idEleitor);
		if (voto != null) {
			return voto;
		} else {
			voto = new Voto(idCandidato, idEleitor);
			voto.setData(LocalDateTime.now());
			return votoRepository.save(voto);
		}
	}

	// Verifica se o candidato possui algum voto computado
	public Voto candidatoPossuiVoto(UUID idCandidato) {
		Voto votoSalvo = votoRepository.findVotoCandidato(idCandidato);
		if (votoSalvo != null) {
			return votoSalvo;
		}
		return null;
	}

	// Verifica se o eleitor já realizou algum voto
	public Voto eleitorJaVotou(UUID idEleitor) {
		Voto votoSalvo = votoRepository.findVotoEleitor(idEleitor);
		if (votoSalvo != null) {
			return votoSalvo;
		}
		return null;
	}

}
