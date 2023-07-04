package com.lojadejogos.games.model;

import java.math.BigDecimal;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogosGames")
public class JogosGames {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(max = 1000)
	private String nomedoJogo;
	
	@NotBlank 
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	private int quantidade;
	
	@NotNull
	@Positive
	private BigDecimal preco;
	
	@ManyToOne
	@JsonIgnoreProperties("jogosgames")
	private Jogos jogos;
	
	public Long getId() {
		return id;
	}

	public Jogos getJogos() {
		return jogos;
	}

	public void setJogos(Jogos jogos) {
		this.jogos = jogos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomedoJogo() {
		return nomedoJogo;
	}

	public void setNomedoJogo(String nomedoJogo) {
		this.nomedoJogo = nomedoJogo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	@NotNull
	private String disponivel;

	

}
