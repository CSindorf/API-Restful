package br.com.cinema.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cinema.cinema.entity.Diretor;
import br.com.cinema.cinema.repository.DiretorRepository;

@Service

public class DiretorService {
	@Autowired
	DiretorRepository diretorRepository;
	
	public List<Diretor> getAllDiretores(){
		return diretorRepository.findAll(); 
	}
	
	public Diretor getDiretorById(int id) {
		return diretorRepository.findById(id).orElse(null);
	}
	
	public Diretor saveDiretor(Diretor diretor) {
		return diretorRepository.save(diretor);
	}
	
	public Diretor updateDiretor (Diretor diretor, int id) { 
		
		Diretor diretorExistenteNoBanco = diretorRepository.findById(id).get();
		diretorExistenteNoBanco.setNome(diretor.getNome());
		
		return diretorRepository.save(diretorExistenteNoBanco);
	}
	
	public Diretor deleteDiretor(int id) {
		diretorRepository.deleteById(id);
		return getDiretorById(id);
	}

}
