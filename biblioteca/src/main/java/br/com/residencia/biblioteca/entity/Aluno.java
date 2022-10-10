package br.com.residencia.biblioteca.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //diz o tipo de classe
@Table(name = "alunos") //diz com qual tabela ela se comunica

public class Aluno {
	@Id //diz que esse campo abaixo é uma chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //diz que o banco vai fazer a sequência dos números sequenciais
	@Column (name = "numeromatriculaaluno") //diz com qual coluna se comunica
	private int numeroMatriculaAluno;
	
	@Column(name = "nome") //diz com qual coluna se comunica
	private String nome;
	
	@Column(name = "datanascimento") //diz com qual coluna se comunica
	private Instant dataNascimento;
	
	@Column(name = "cpf") //diz com qual coluna se comunica
	private String cpf;

	@Column(name = "logradouro") //diz com qual coluna se comunica
	private String logradouro;
	
	@Column(name = "numerologradouro") //diz com qual coluna se comunica
	private String numeroLogradouro;
	
	@Column(name = "complemento") //diz com qual coluna se comunica
	private String complemento;
	
	@Column(name = "bairro") //diz com qual coluna se comunica
	private String bairro;
	
	@Column(name = "cidade") //diz com qual coluna se comunica
	private String cidade;
	
	//um aluno pode ter vários empréstimos
	@OneToMany(mappedBy = "aluno")  
	private Set<Emprestimo> emprestimo;

	public int getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(int numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Set<Emprestimo> getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Set<Emprestimo> emprestimo) {
		this.emprestimo = emprestimo;
	}

}
