package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
