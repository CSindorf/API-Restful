package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
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
	
	 // ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	//método para fazer a conversão de uma entidade normal para um DTO
	private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		editoraDTO.setCodigoeditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		return editoraDTO;
	}
	
	 // ----- DTO CONVERSOR - FIM -----
	
	//para puxar a lista de registros
	public List<Editora> getAllEditoras(){
		return editoraRepository.findAll(); 
	}
	
	//get all DTO
	public List<EditoraDTO> getAllEditorasDTO(){
		//cria uma lista de editoras com todas que foram encontradas lá no banco com o "findAll"
		List<Editora> listaEditora = editoraRepository.findAll();
		
		//cria uma lista vazia de "EditoraDTO" para preencher com as editoras encontradas acima
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		
		//1. Percorrer a lista de entidades Editora (chamada listaEditora)
				//2. Na lista de entidade, pegar cada entidade existente nela
				//3. Transformar cada entidade existente na lista em um DTO
				//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
				//5. Retornar/devolver a lista de DTO preenchida
				
				//OBS: para converter a entidade no DTO, usar o metodo toDTO
		
		
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Editora getEditoraById(int id) {
		return editoraRepository.findById(id).orElse(null);
	}
	
	//para salvar
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	//para salvar como DTO
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		//cria um "editora" e coloca dentro dele o "editoraDTO" que foi preenchido lá fazendo a conversão do método "toEntidade"
		Editora editora = toEntidade(editoraDTO);
			
		//coloca no "novaEditora" o que foi salvo no banco
		Editora novaEditora = editoraRepository.save(editora);
			
		//pega o que está salvo no banco e guardado em "novaEditora", chama o toDTO para converter para DTO e coloca o valor em "editoraAtualizadaDTO"
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora);
		return editoraAtualizadaDTO;
		//retorna o que foi preenchido e salvo no banco no formato DTO
	}
	
	//SALVAR DTO VERSÃO 2 - MAIS OTIMIZADO
	public EditoraDTO saveEditoraDTOVersaoDois(EditoraDTO editoraDTO) {
		Editora novaEditora = editoraRepository.save(toEntidade(editoraDTO));
		return toDTO(novaEditora);
		
		/* faz a mesma coisa do de cima
		 * salva no "novaEditora" o que foi colocado no DTO no "editoraDTO" e já fazendo a conversão do "toEntidade"
		 * depois no retorno já chama o conversor para DTO no "toDTO" com o "novaEditora" sendo passado para converter
		 * assim o return já volta com o convertido que estava no "novaEditora" */
	}
	
	//para atualizar
	public Editora updateEditora (Editora editora, int id) { 
		
		Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();
		editoraExistenteNoBanco.setNome(editora.getNome());
		
		return editoraRepository.save(editoraExistenteNoBanco);
	}
	
	//atualizar como DTO
	public EditoraDTO updateEditoraDTO (EditoraDTO editoraDTO, int id) { 
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			//pega o que foi digitado no "editoraDTO", chama o "toEntidade" para fazer a conversão e armazena no "editoraAtualizada"
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			//salva no banco o que foi digitado
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			//pega o que está salvo no banco e guardado em "editoraAtualizada", chama o toDTO para converter para DTO e coloca o valor em "editoraAtualizadaDTO"
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
			
		}
		return editoraAtualizadaDTO;
	}
	
	//para deletar
	public Editora deleteEditora(int id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}

}
