package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service

public class EmprestimoService {
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	//para puxar a lista de registros
	public List<Emprestimo> getAllEmprestimos(){
		return emprestimoRepository.findAll(); 
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Emprestimo getEmprestimoById(int id) {
<<<<<<< HEAD
		return emprestimoRepository.findById(id).orElse(null);
=======
		return emprestimoRepository.findById(id).get(); 
>>>>>>> origin/servicos
	}
	
	//para salvar
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	//para atualizar
	public Emprestimo updateEmprestimo (Emprestimo emprestimo, int id) { 
		
		Emprestimo emprestimoExistenteNoBanco = emprestimoRepository.findById(id).get();
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		//emprestimoExistenteNoBanco.setAluno(emprestimo.getAluno());
		//emprestimoExistenteNoBanco.setLivro(emprestimo.getLivro());
		//comentou os dois últimos porque na regra proposta pelo professor, não haveria possibilidade de edição destas duas pelo usuário
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	//para deletar
	public Emprestimo deleteEmprestimo(int id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}
}
