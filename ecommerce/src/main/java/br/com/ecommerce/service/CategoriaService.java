package br.com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;
	
	//get all
	public List<Categoria> getAllCategorias(){
		return categoriaRepository.findAll(); 
	}
	
	//get id
	public Categoria getCategoriaById(int id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	//save
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	//update
	public Categoria updateCategoria (Categoria categoria, int id) { 
		
		Categoria categoriaExistenteNoBanco = categoriaRepository.findById(id).get();
		categoriaExistenteNoBanco.setNome(categoria.getNome());
		categoriaExistenteNoBanco.setDescricao(categoria.getDescricao());
		
		return categoriaRepository.save(categoriaExistenteNoBanco);
	}
	
	//delete
	public Categoria deleteCategoria(int id) {
		categoriaRepository.deleteById(id);
		return getCategoriaById(id);
	}
}
