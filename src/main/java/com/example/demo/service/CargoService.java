package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cargo;
import com.example.demo.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	public Cargo atualizar(UUID id, Cargo cargo) {
		Cargo cargoSalvo = cargoRepository.findById(id);
		if (cargoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(cargo, cargoSalvo, "id");
		return cargoRepository.save(cargoSalvo);
	}

}
