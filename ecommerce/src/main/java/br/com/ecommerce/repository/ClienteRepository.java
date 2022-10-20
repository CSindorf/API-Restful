package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
