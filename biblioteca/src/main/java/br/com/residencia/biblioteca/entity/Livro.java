package br.com.residencia.biblioteca.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "livros") 

public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigolivro")
	private int codigoLivro;

	@Column(name = "nomelivro")
	private String nomeLivro;

	@Column(name = "nomeautor")
	private String nomeAutor;

	@Column(name = "datalancamento")
	private Instant dataLancamento;

	@Column(name = "codigoisbn")
	private int codigoIsbn;

	@ManyToOne //precisa fazer a anotação de que é muitos livros para uma ediora
	@JoinColumn(name = "codigoeditora", referencedColumnName = "codigoeditora") //coloca os dados de qual coluna da tabela livros que vai conter essa foreign key e em qual coluna na tabela editora está esse id
	private Editora editora; //está relacionando com a editora, que é foreign key nessa tabela

	//aqui não coloca o join porque a tabela de livro não tem uma foreign key de empréstimo, então o join tem só na classe de empréstimo
	@OneToMany(mappedBy = "livro")
	private Set<Emprestimo> emprestimos;

	public int getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Instant getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Instant dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(int codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Set<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
	
}
