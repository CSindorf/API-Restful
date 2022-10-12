package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service

public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;
	
	//para puxar a lista de registros
	public List<Editora> getAllEditoras(Editora editora){
		return editoraRepository.findAll(); 
	}
	
	//get all DTO
	public List<EditoraDTO> getAllEditorasDTO(){
		return editoraRepository.findAll(); 
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Editora getEditoraById(int id) {
		return editoraRepository.findById(id).orElse(null);
	}
	
	//para salvar
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	//para salvar o DTO
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		//tem que instanciar porque o save só aceita entidade e não um DTO
		Editora editora = new Editora();
		editora.setNome(editoraDTO.getNome());
			
		//salvando a editora que foi salva no banco no novaEditora, pra vir com id
		Editora novaEditora = editoraRepository.save(editora);
			
		//agora está pegando o que foi salvo e colocando no editoraDTO
		EditoraDTO novaEditoraDTO = new EditoraDTO();
			
		novaEditoraDTO.setCodigoeditora(novaEditora.getCodigoEditora());
		novaEditoraDTO.setNome(novaEditora.getNome());
		return novaEditoraDTO;
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
