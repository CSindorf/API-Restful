package br.com.cinema.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinema.cinema.entity.Genero;
import br.com.cinema.cinema.repository.GeneroRepository;

@Service

public class GeneroService {
	@Autowired
	GeneroRepository generoRepository;
	
	public List<Genero> getAllGeneros(){
		return generoRepository.findAll(); 
	}
	
	public Genero getGeneroById(int id) {
		return generoRepository.findById(id).orElse(null);
	}
	
	public Genero saveGenero(Genero genero) {
		return generoRepository.save(genero);
	}
	
	public Genero updateGenero (Genero genero, int id) { 
		
		Genero generoExistenteNoBanco = generoRepository.findById(id).get();
		generoExistenteNoBanco.setNome(genero.getNome());
		
		return generoRepository.save(generoExistenteNoBanco);
	}
	
	public Genero deleteGenero(int id) {
		generoRepository.deleteById(id);
		return getGeneroById(id);
	}

}
