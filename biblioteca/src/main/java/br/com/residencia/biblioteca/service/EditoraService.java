package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.biblioteca.dto.ConsultaCNPJDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service

public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;
	
	//para usar o repositório dos livros aqui, para pegar a lista de livros que uma editora possui
	@Autowired
	LivroRepository livroRepository;
	
	//para fazer a conversão da lista de livros da editoa do service do livro
	@Autowired
	LivroService livroService;
	
	//para fazer o envio de email que está no update com DTO
	@Autowired
	EmailService emailService;
	
	 // ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		editora.setCodigoEditora(editoraDTO.getCodigoeditora());
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
		for(Editora editora: listaEditora) {
			//3. Transformar cada entidade existente na lista em um DTO
			EditoraDTO editoraDTO = toDTO(editora);
			
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEditoraDTO.add(editoraDTO);
		}
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEditoraDTO;
	}
	
	//get all DTO mostrando quais livros a editora tem
	public List<EditoraDTO> getAllEditoraLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();

		for(Editora editora: listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			//cria ua lista de livros para cada editora, mas precisa fazer a conversão dela para DTO
			listaLivros = livroRepository.findByEditora(editora);
			
			//faz o "for" para pegar a lista de livros e converter ela para DTO
			//usa o "livroService" puxado lá no começo no "autowired" para usar a conversão que já existe no "LivroService"
			for(Livro livro : listaLivros) {
				//aqui faz  conversão para DTO
				LivroDTO livroDTO = livroService.toDTO(livro);
				
				//pega os livros que foram convertidos e coloca em "listaLivrosDTO" para poder exibir eles
				listaLivrosDTO.add(livroDTO);
			}
			//seta na "editoraDTO" a lista de livros "listaLivrosDTO"
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);
			listaEditoraDTO.add(editoraDTO);
		}
		return listaEditoraDTO;
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
			editoraDTO.setCodigoeditora(editoraExistenteNoBanco.getCodigoEditora());
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			//salva no banco o que foi digitado
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			//pega o que está salvo no banco e guardado em "editoraAtualizada", chama o toDTO para converter para DTO e coloca o valor em "editoraAtualizadaDTO"
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
			
		}
		//envio de email pela api
		emailService.sendEmail("catarina.sindorf@gmail.com", "teste", "teste de envio");
		return editoraAtualizadaDTO;
	}
	
	//para deletar
	public Editora deleteEditora(int id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}
	
	//método para usar a consulta da API externa de CNPJ
	public ConsultaCNPJDTO consultaCnpjApiExterna(String cnpj) {
		//isto é do próprio Spring, que pode ser utilizado para consumir a API externa
		RestTemplate restTemplate = new RestTemplate();
		
		//vai ficar com a url do serviço de consulta de cnpj (tem na documentação https://developers.receitaws.com.br/#/operations/queryCNPJFree)
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
		ConsultaCNPJDTO consultaCNPJDTO = restTemplate.getForObject(uri, ConsultaCNPJDTO.class, params);
		
		return consultaCNPJDTO;
	}
	
	//post de uma nova editora usando o nome que vem da consulta com o cnpj
	public Editora saveEditoraFromApi(String cnpj) {
		//consulta o cnpj para pegar os dados
		ConsultaCNPJDTO consultaCNPJDTO = consultaCnpjApiExterna(cnpj);
		
		//cria um objeto para guardar o dado desejado da consulta do cnpj 
		Editora editora = new Editora();
		
		//seta no "editora" o nome achado na consulta do cnpj 
		editora.setNome(consultaCNPJDTO.getNome());
		
		//salva a editora com o nome achado na consulta
		return editoraRepository.save(editora);
	}

}
