package br.com.residencia.biblioteca.entity;

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
		property = "codigoEditora"
		)
@Entity //diz o tipo de classe
@Table(name = "editora") //diz com qual tabela ela se comunica

public class Editora {
	@Id //diz que esse campo abaixo é uma chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //diz que o banco vai fazer a sequência dos números sequenciais
	@Column (name = "codigoeditora") //diz com qual coluna se comunica
	private int codigoEditora;
	
	@Column(name = "nome") //diz com qual coluna se comunica
	private String nome;
	
	//precisa fazer a anotação de que é uma editora para muitos livros diferentes.
	//O mapped diz que lá na classe livros ela foi instanciada com esse nome "editora". Linha 37
	//a classe que usa o OneToMany que precisa ter o mapped, a outra não e fica com o join
	@OneToMany(mappedBy = "editora")  
	private Set<Livro> livros; //aqui usa set porque uma editora pode ter vários livros, então o resultado tem que ser múltiplo, tipo um arrays

	public int getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(int codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}
		
}
