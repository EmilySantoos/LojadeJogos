package com.lojadejogos.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lojadejogos.games.model.Jogos;


public interface JogosRepository extends JpaRepository<Jogos,Long> {



		 public List<Jogos> findAllByDescricaoIgnoreCase(@Param("descricaodoJogo") String descricao);

		 
			
		}


