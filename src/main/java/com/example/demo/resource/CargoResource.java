package com.example.demo.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Cargo;
import com.example.demo.repository.CargoRepository;
import com.example.demo.service.CargoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cargo")
public class CargoResource {

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Cargo> listar() {
		return cargoRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Cargo> criar(@Valid @RequestBody Cargo cargo, HttpServletResponse response) {
		Cargo cargoSalvo = cargoRepository.save(cargo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cargoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoSalvo);
	}

	@GetMapping("/{id}")
	public Cargo buscarPeloId(@PathVariable UUID id) {
		return cargoRepository.findById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable UUID id) {
		cargoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cargo> atualizar(@PathVariable UUID id, @Valid @RequestBody Cargo cargo) {
		Cargo cargoSalvo = cargoService.atualizar(id, cargo);
		return ResponseEntity.ok(cargoSalvo);
	}

}
