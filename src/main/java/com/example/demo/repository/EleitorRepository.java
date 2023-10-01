package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Eleitor;

public interface EleitorRepository extends JpaRepository<Eleitor, Long> {

	Eleitor findById(UUID id);

	void deleteById(UUID id);

	Eleitor findByCPF(Integer cpf);

}
