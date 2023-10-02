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
import com.example.demo.model.Candidato;
import com.example.demo.service.CandidatoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidatos")
public class CandidatoResource {

	@Autowired
	private CandidatoService candidatoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Candidato> listar() {
		return candidatoService.listar();
	}

	@PostMapping
	public ResponseEntity<Candidato> criar(@Valid @RequestBody Candidato candidato, HttpServletResponse response) {
		Candidato candidatoSalvo = candidatoService.criar(candidato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, candidatoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(candidatoSalvo);
	}

	@GetMapping("/{id}")
	public Candidato buscarPeloId(@PathVariable UUID id) {
		System.out.println(id);
		return candidatoService.buscarPeloId(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable UUID id) {
		candidatoService.remover(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Candidato> atualizar(@PathVariable UUID id, @Valid @RequestBody Candidato candidato) {
		Candidato candidatoSalvo = candidatoService.atualizar(id, candidato);
		return ResponseEntity.ok(candidatoSalvo);
	}

}
