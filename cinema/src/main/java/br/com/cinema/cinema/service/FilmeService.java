package br.com.cinema.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinema.cinema.entity.Filme;
import br.com.cinema.cinema.repository.FilmeRepository;

@Service

public class FilmeService {
	@Autowired
	FilmeRepository filmeRepository;
	
	public List<Filme> getAllFilmes(){
		return filmeRepository.findAll(); 
	}
	
	public Filme getFilmeById(int id) {
		return filmeRepository.findById(id).orElse(null);
	}
	
	public Filme saveFilme(Filme filme) {
		return filmeRepository.save(filme);
	}
	
	public Filme updateFilme (Filme filme, int id) { 
		
		Filme filmeExistenteNoBanco = filmeRepository.findById(id).get();
		filmeExistenteNoBanco.setNomeBr(filme.getNomeBr());
		filmeExistenteNoBanco.setNomeEn(filme.getNomeEn());
		filmeExistenteNoBanco.setAnoLancamento(filme.getAnoLancamento());
		filmeExistenteNoBanco.setSinopse(filme.getSinopse());
		
		return filmeRepository.save(filmeExistenteNoBanco);
	}
	
	public Filme deleteFilme(int id) {
		filmeRepository.deleteById(id);
		return getFilmeById(id);
	}

}
