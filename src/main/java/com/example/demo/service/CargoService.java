package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Cargo;
import com.example.demo.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	public List<Cargo> listar() {
		return cargoRepository.findAll();
	}

	public Cargo buscarPeloId(@PathVariable UUID id) {
		return cargoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public void remover(@PathVariable UUID id) {
		cargoRepository.deleteById(id);
	}

	public Cargo criar(Cargo cargo) {
		Cargo cargoSalvo = cargoRepository.save(cargo);
		return cargoSalvo;
	}

	public Cargo atualizar(UUID id, Cargo cargo) {
		Cargo cargoSalvo = cargoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		if (cargoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(cargo, cargoSalvo, "id");
		return cargoRepository.save(cargoSalvo);
	}

}
