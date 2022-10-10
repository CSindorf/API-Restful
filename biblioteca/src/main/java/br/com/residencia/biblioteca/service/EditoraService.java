package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service

public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;
	
	//para puxar a lista de registros
	public List<Editora> getAllEditoras(){
		return editoraRepository.findAll(); 
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Editora getEditoraById(int id) {
<<<<<<< HEAD
		return editoraRepository.findById(id).orElse(null);
=======
		return editoraRepository.findById(id).get(); 
>>>>>>> origin/servicos
	}
	
	//para salvar
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	//para atualizar
	public Editora updateEditora (Editora editora, int id) { 
		
		Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();
		editoraExistenteNoBanco.setNome(editora.getNome());
		
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	//para deletar
	public Editora deleteEditora(int id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}

}
