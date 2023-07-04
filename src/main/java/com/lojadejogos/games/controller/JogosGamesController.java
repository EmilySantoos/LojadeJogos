package com.lojadejogos.games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lojadejogos.games.repository.JogosGamesRepository;
//import com.lojadejogos.games.repository.JogosRepository;//

import jakarta.validation.Valid;

import com.lojadejogos.games.model.JogosGames;

@RestController
@RequestMapping("/jogosgames")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogosGamesController {

	@Autowired
	private JogosGamesRepository jogosgamesRepository;

/*	@Autowired
	private JogosRepository jogosRepository;/*/

	@GetMapping
	public ResponseEntity<List<JogosGames>> getAll() {
		return ResponseEntity.ok(jogosgamesRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<JogosGames> getById(@PathVariable Long id) {
		return jogosgamesRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/nomedoJogo/{nomedoJogo}")
	public ResponseEntity<List<JogosGames>> getByTitulo(@PathVariable String nomedoJogo) {
		return ResponseEntity.ok(jogosgamesRepository.findAllByNomedoJogoIgnoreCase(nomedoJogo));
	}

	@PostMapping
	public ResponseEntity<JogosGames> post(@Valid @RequestBody JogosGames jogosgames) {
		return ResponseEntity.status(HttpStatus.CREATED).body(jogosgamesRepository.save(jogosgames));
	}

	@PutMapping
	public ResponseEntity<JogosGames> put(@Valid @RequestBody JogosGames jogosgames) {
		return jogosgamesRepository.findById(jogosgames.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(jogosgamesRepository.save(jogosgames)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	/*@PostMapping
	public ResponseEntity<JogosGames> post(@Valid @RequestBody JogosGames jogosgames) {
		if (jogosRepository.existsById(jogosgames.getDescricao().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(jogosRepository.save(jogosgames));

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse jogo não existe!", null);
	}

	@PutMapping
	public ResponseEntity<JogosGames> put(@Valid @RequestBody JogosGames jogosgames) {
		if (jogosRepository.existsById(jogosgames.getDescricao().getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(jogosgamesRepository.save(jogosgames));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o jogo não existe!", null);

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}*/

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<JogosGames> jogosgames = jogosgamesRepository.findById(id);

		if (jogosgames.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		jogosgamesRepository.deleteById(id);
	}}