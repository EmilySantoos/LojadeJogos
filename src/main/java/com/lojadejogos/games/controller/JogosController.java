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

import com.lojadejogos.games.model.Jogos;
import com.lojadejogos.games.repository.JogosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogosController {

    @Autowired
    private JogosRepository jogosRepository;
    
    @GetMapping
    public ResponseEntity<List<Jogos>> getAll(){
        return ResponseEntity.ok(jogosRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Jogos> getById(@PathVariable Long id){
        return jogosRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Jogos>> getByTitle(@PathVariable String descricao){
        return ResponseEntity.ok(jogosRepository.findAllByDescricaoIgnoreCase(descricao));
    }
    
    @PostMapping
    public ResponseEntity<Jogos> post(@Valid @RequestBody Jogos jogos){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jogosRepository.save(jogos));
    }
    
    @PutMapping
    public ResponseEntity<Jogos> put(@Valid @RequestBody Jogos jogos){
        return jogosRepository.findById(jogos.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(jogosRepository.save(jogos)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Jogos> jogos = jogosRepository.findById(id);
        
        if(!jogos.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        jogosRepository.deleteById(id);              
    }

}