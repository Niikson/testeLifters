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
import com.example.demo.model.Eleitor;
import com.example.demo.model.Voto;
import com.example.demo.service.EleitorService;
import com.example.demo.service.VotoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eleitores")
public class EleitorResource {

	@Autowired
	private EleitorService eleitorService;

	@Autowired
	private VotoService votoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Eleitor> listar() {
		return eleitorService.listar();
	}

	@PostMapping
	public ResponseEntity<Eleitor> criar(@Valid @RequestBody Eleitor eleitor, HttpServletResponse response) {
		Eleitor eleitorSalvo = eleitorService.criar(eleitor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, eleitorSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(eleitorSalvo);
	}

	@GetMapping("/{id}")
	public Eleitor buscarPeloId(@PathVariable UUID id) {
		return eleitorService.buscarPeloId(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable UUID id) {
		eleitorService.remover(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Eleitor> atualizar(@PathVariable UUID id, @Valid @RequestBody Eleitor eleitor) {
		Eleitor eleitorSalvo = eleitorService.atualizar(id, eleitor);
		return ResponseEntity.ok(eleitorSalvo);
	}

	@PostMapping("/{idCandidato}/votar")
	public ResponseEntity<Voto> votar(@PathVariable UUID idCandidato, @Valid @RequestBody Eleitor eleitor,
			HttpServletResponse response) {
		Voto votoSalvo = votoService.registrarVoto(idCandidato, eleitor.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(votoSalvo);
	}

}
