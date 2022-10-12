package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service

public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
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
	
	//para deletar
	public Livro deleteLivro(int id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}

}
