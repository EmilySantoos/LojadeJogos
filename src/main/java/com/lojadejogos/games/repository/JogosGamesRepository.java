package com.lojadejogos.games.repository;


import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lojadejogos.games.model.JogosGames;

public interface JogosGamesRepository extends JpaRepository<JogosGames,Long>{

	
	public List <JogosGames> findAllByNomedoJogoIgnoreCase(@Param("nomedoJogo") String nomedoJogo);
}
