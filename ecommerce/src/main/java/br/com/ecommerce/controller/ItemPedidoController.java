package br.com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.entity.ItemPedido;
import br.com.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedidos")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;
	
	//get all
	@GetMapping
	public ResponseEntity<List<ItemPedido>> getAllItemPedidos(){
		return new ResponseEntity <>(itemPedidoService.getAllItemPedidos(),HttpStatus.OK); 
	}
	
	//get id
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable int id) {
		ItemPedido itemPedido = itemPedidoService.getItemPedidoById(id);
		if(null != itemPedido)
		return new ResponseEntity <>(itemPedido,HttpStatus.OK); 
		else
			return new ResponseEntity <>(itemPedido,HttpStatus.NOT_FOUND);
	}
	
	//save
	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido) {
		
		return new ResponseEntity <>(itemPedidoService.saveItemPedido(itemPedido),HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody ItemPedido itemPedido, @PathVariable int id){
		return new ResponseEntity <>(itemPedidoService.updateItemPedido(itemPedido, id),HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ItemPedido> deleteItemPedido(@PathVariable int id) {
		ItemPedido itemPedido = itemPedidoService.getItemPedidoById(id);
		if(null == itemPedido)
			return new ResponseEntity <>(itemPedido,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(itemPedidoService.deleteItemPedido(id),HttpStatus.OK);
	}
}
