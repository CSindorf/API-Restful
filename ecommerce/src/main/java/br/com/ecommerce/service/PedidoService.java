package br.com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	//get all
	public List<Pedido> getAllPedidos(){
		return pedidoRepository.findAll(); 
	}
	
	//get id
	public Pedido getPedidoById(int id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	//save
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	//update
	public Pedido updatePedido (Pedido pedido, int id) { 
		
		Pedido pedidoExistenteNoBanco = pedidoRepository.findById(id).get();
		pedidoExistenteNoBanco.setDataPedido(pedido.getDataPedido());
		pedidoExistenteNoBanco.setDataEntrega(pedido.getDataEntrega());
		pedidoExistenteNoBanco.setDataEnvio(pedido.getDataEnvio());
		pedidoExistenteNoBanco.setStatus(pedido.getStatus());
		pedidoExistenteNoBanco.setValorTotal(pedido.getValorTotal());
		pedidoExistenteNoBanco.setCliente(pedido.getCliente());
		
		return pedidoRepository.save(pedidoExistenteNoBanco);
	}
	
	//delete
	public Pedido deletePedido(int id) {
		pedidoRepository.deleteById(id);
		return getPedidoById(id);
	}
}
