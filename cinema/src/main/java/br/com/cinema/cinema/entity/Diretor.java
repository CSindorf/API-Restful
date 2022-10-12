package br.com.cinema.cinema.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idDiretor"
		)
@Entity
@Table(name = "diretores")

public class Diretor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_diretor")
	private int idDiretor;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "diretor")  
	private Set<Filme> filme;

	public int getIdDiretor() {
		return idDiretor;
	}

	public void setIdDiretor(int idDiretor) {
		this.idDiretor = idDiretor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Filme> getFilme() {
		return filme;
	}

	public void setFilme(Set<Filme> filme) {
		this.filme = filme;
	}
		
}
