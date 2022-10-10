package br.com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinema.cinema.entity.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Integer> {

}
