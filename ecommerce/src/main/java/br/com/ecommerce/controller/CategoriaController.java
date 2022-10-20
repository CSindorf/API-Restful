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

import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	
	//get all
	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias(){
		return new ResponseEntity <>(categoriaService.getAllCategorias(),HttpStatus.OK); 
	}
	
	//get id
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
		Categoria categoria = categoriaService.getCategoriaById(id);
		if(null != categoria)
		return new ResponseEntity <>(categoria,HttpStatus.OK); 
		else
			return new ResponseEntity <>(categoria,HttpStatus.NOT_FOUND);
	}
	
	//save
	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		
		return new ResponseEntity <>(categoriaService.saveCategoria(categoria),HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, @PathVariable int id){
		return new ResponseEntity <>(categoriaService.updateCategoria(categoria, id),HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable int id) {
		Categoria categoria = categoriaService.getCategoriaById(id);
		if(null == categoria)
			return new ResponseEntity <>(categoria,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(categoriaService.deleteCategoria(id),HttpStatus.OK);
	}
}
