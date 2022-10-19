package br.com.residencia.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
	//método para fazer uma lista de livros que uma editora possui
	//significa encontrar uma lista de livros "by editora", então por editora
	public List<Livro> findByEditora(Editora editora);
}
