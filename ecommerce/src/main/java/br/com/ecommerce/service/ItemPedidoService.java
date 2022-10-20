package br.com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.entity.ItemPedido;
import br.com.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	//get all
	public List<ItemPedido> getAllItemPedidos(){
		return itemPedidoRepository.findAll(); 
	}
	
	//get id
	public ItemPedido getItemPedidoById(int id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	//save
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	//update
	public ItemPedido updateItemPedido (ItemPedido itemPedido, int id) { 
		
		ItemPedido itemPedidoExistenteNoBanco = itemPedidoRepository.findById(id).get();
		itemPedidoExistenteNoBanco.setQuantidade(itemPedido.getQuantidade());
		itemPedidoExistenteNoBanco.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoExistenteNoBanco.setPercentualDeconto(itemPedido.getPercentualDeconto());
		itemPedidoExistenteNoBanco.setValorBruto(itemPedido.getValorBruto());
		itemPedidoExistenteNoBanco.setValorLiquido(itemPedido.getValorLiquido());
		itemPedidoExistenteNoBanco.setProduto(itemPedido.getProduto());
		itemPedidoExistenteNoBanco.setPedido(itemPedido.getPedido());
		
		return itemPedidoRepository.save(itemPedidoExistenteNoBanco);
	}
	
	//delete
	public ItemPedido deleteItemPedido(int id) {
		itemPedidoRepository.deleteById(id);
		return getItemPedidoById(id);
	}
}
