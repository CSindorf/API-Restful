package br.com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinema.cinema.entity.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

}
