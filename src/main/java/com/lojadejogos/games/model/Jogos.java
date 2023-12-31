package com.lojadejogos.games.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "tb_jogos")
public class Jogos {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "O Atributo Descrição é obrigatório")
		private String descricao;


		@OneToMany(fetch = FetchType.LAZY, mappedBy = "jogos", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("jogos")
		private List<JogosGames> jogosgames;


		public Long getId() {
			return this.id;
		}
		
		
		
		public void setId(Long id) {
			this.id = id;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}



		public List<JogosGames> getJogosgames() {
			return jogosgames;
		}



		public void setJogosgames(List<JogosGames> jogosgames) {
			this.jogosgames = jogosgames;
		}


	}


