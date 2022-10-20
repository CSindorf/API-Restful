package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
