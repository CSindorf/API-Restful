package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service

public class EmprestimoService {
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	// ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Emprestimo toEntidade (EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimo.setDataEntrega(emprestimoDTO.getDataEntrega());
		emprestimo.setValorEmprestimo(emprestimoDTO.getValorEmprestimo());
		emprestimo.setCodigoEmprestimo(emprestimoDTO.getCodigoEmprestimo());
		return emprestimo;
	}
		
	//método para fazer a conversão de uma entidade normal para um DTO
	public EmprestimoDTO toDTO(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
		emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoDTO.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoDTO.setValorEmprestimo(emprestimo.getValorEmprestimo());
		emprestimoDTO.setCodigoEmprestimo(emprestimo.getCodigoEmprestimo());
		return emprestimoDTO;
	}
		
	 // ----- DTO CONVERSOR - FIM -----
	
	//para puxar a lista de registros
	public List<Emprestimo> getAllEmprestimos(){
		return emprestimoRepository.findAll(); 
	}
	
	//get all DTO
	public List<EmprestimoDTO> getAllEmprestimosDTO(){
		//cria uma lista de alunos com todas que foram encontradas lá no banco com o "findAll"
		List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();
		
		//cria uma lista vazia de "AlunoDTO" para preencher com as alunos encontradas acima
		List<EmprestimoDTO> listaEmprestimoDTO = new ArrayList<>();
		
		//1. Percorrer a lista de entidades Aluno (chamada listaAluno)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Emprestimo emprestimo: listaEmprestimo) {
			//3. Transformar cada entidade existente na lista em um DTO
			EmprestimoDTO emprestimoDTO = toDTO(emprestimo);
			
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEmprestimoDTO.add(emprestimoDTO);
		}
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEmprestimoDTO;
	}
	
	//para pegar um único registro do banco, pelo Id dele
	public Emprestimo getEmprestimoById(int id) {
		return emprestimoRepository.findById(id).orElse(null);
	}
	
	//para salvar
	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	//para salvar como DTO
	public EmprestimoDTO saveEmprestimoDTO(EmprestimoDTO emprestimoDTO) {
		//cria um "aluno" e coloca dentro dele o "alunoDTO" que foi preenchido lá fazendo a conversão do método "toEntidade"
		Emprestimo emprestimo = toEntidade(emprestimoDTO);
			
		//coloca no "novaAluno" o que foi salvo no banco
		Emprestimo novaEmprestimo = emprestimoRepository.save(emprestimo);
			
		//pega o que está salvo no banco e guardado em "novaAluno", chama o toDTO para converter para DTO e coloca o valor em "alunoAtualizadaDTO"
		EmprestimoDTO emprestimoAtualizadaDTO = toDTO(novaEmprestimo);
		return emprestimoAtualizadaDTO;
		//retorna o que foi preenchido e salvo no banco no formato DTO
	}
	
	//para atualizar
	public Emprestimo updateEmprestimo (Emprestimo emprestimo, int id) { 
		
		Emprestimo emprestimoExistenteNoBanco = emprestimoRepository.findById(id).get();
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		emprestimoExistenteNoBanco.setAluno(emprestimo.getAluno());
		emprestimoExistenteNoBanco.setLivro(emprestimo.getLivro());
		//comentou os dois últimos porque na regra proposta pelo professor, não haveria possibilidade de edição destas duas pelo usuário
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
	}
	
	//atualizar como DTO
	public EmprestimoDTO updateEmprestimoDTO (EmprestimoDTO emprestimoDTO, int id) { 
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		EmprestimoDTO emprestimoAtualizadaDTO = new EmprestimoDTO();
		
		if(emprestimoExistenteNoBanco != null) {
			//pega o que foi digitado no "alunoDTO", chama o "toEntidade" para fazer a conversão e armazena no "alunoAtualizada"
			emprestimoDTO.setCodigoEmprestimo(emprestimoExistenteNoBanco.getCodigoEmprestimo());
			emprestimoExistenteNoBanco = toEntidade(emprestimoDTO);
			
			//salva no banco o que foi digitado
			Emprestimo emprestimoAtualizada = emprestimoRepository.save(emprestimoExistenteNoBanco);
			
			//pega o que está salvo no banco e guardado em "alunoAtualizada", chama o toDTO para converter para DTO e coloca o valor em "alunoAtualizadaDTO"
			emprestimoAtualizadaDTO = toDTO(emprestimoAtualizada);
			
		}
		return emprestimoAtualizadaDTO;
	}
	
	//para deletar
	public Emprestimo deleteEmprestimo(int id) {
		emprestimoRepository.deleteById(id);
		return getEmprestimoById(id);
	}
}
