package br.com.cinema.cinema.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "filmes")

public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_filme")
	private int idFilme;
	
	@Column(name = "nome_br")
	private String nomeBr;
	
	@Column(name = "nome_en")
	private String nomeEn;
	
	@Column(name = "ano_lancamento")
	private Instant anoLancamento;
	
	@Column(name = "sinopse")
	private String sinopse;

	@ManyToOne
	@JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name = "id_diretor", referencedColumnName = "id_diretor")
	private Diretor diretor;

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public String getNomeBr() {
		return nomeBr;
	}

	public void setNomeBr(String nomeBr) {
		this.nomeBr = nomeBr;
	}

	public String getNomeEn() {
		return nomeEn;
	}

	public void setNomeEn(String nomeEn) {
		this.nomeEn = nomeEn;
	}

	public Instant getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Instant anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
		
}
