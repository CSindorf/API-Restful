package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service

public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	 // ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Livro toEntidade (LivroDTO livroDTO) {
		Livro livro = new Livro();
		livro.setNomeLivro(livroDTO.getNomeLivro());
		livro.setCodigoIsbn(livroDTO.getCodigoIsbn());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		return livro;
	}
	
	//método para fazer a conversão de uma entidade normal para um DTO
	public LivroDTO toDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		livroDTO.setCodigoLivro(livro.getCodigoLivro());
		livroDTO.setNomeLivro(livro.getNomeLivro());
		livroDTO.setCodigoIsbn(livro.getCodigoIsbn());
		livroDTO.setDataLancamento(livro.getDataLancamento());
		livroDTO.setNomeAutor(livro.getNomeAutor());
		return livroDTO;
	}
	
	 // ----- DTO CONVERSOR - FIM -----
	
	//para puxar a lista de registros
	public List<Livro> getAllLivros(){
		return livroRepository.findAll(); 
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Livro getLivroById(int id) {
		return livroRepository.findById(id).orElse(null);
	}
	
	//para salvar
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	//para salvar como DTO
	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
				
		LivroDTO livroAtualizadoDTO = toDTO(novoLivro);
		return livroAtualizadoDTO;
	}
	
	//para atualizar
	public Livro updateLivro (Livro livro, int id) { 
		
		Livro livroExistenteNoBanco = livroRepository.findById(id).get();
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		//livroExistenteNoBanco.setEditora(livro.getEditora());
		//comentou editora porque na regra proposta pelo professor, não haveria possibilidade de edição desta parte pelo usuário
		
		return livroRepository.save(livroExistenteNoBanco);
	}
	
	//atualizar como DTO
	public LivroDTO updateLivroDTO (LivroDTO livroDTO, int id) { 
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
			
		if(livroExistenteNoBanco != null) {
			livroExistenteNoBanco = toEntidade(livroDTO);
			
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
				
			livroAtualizadoDTO = toDTO(livroAtualizado);
			
		}
		return livroAtualizadoDTO;
	}
	
	//para deletar
	public Livro deleteLivro(int id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}

}
