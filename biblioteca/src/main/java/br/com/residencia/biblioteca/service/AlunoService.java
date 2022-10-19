package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.repository.AlunoRepository;

@Service //anotação que diz que a classe é de um serviço, que faz a requisição para o banco para devolver para o controller
//todos os métodos desse tipo precisam ter retorno, porque eles tem que retornar algo para o controller, que é quem pediu as informações

public class AlunoService {
	
	@Autowired //o Spring Tools controla a utilização da instância abaixo, então assim não precisa criar um objeto com o "= new AlunoRepository"
	AlunoRepository alunoRepository;
	
	// ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Aluno toEntidade (AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		return aluno;
	}
		
	//método para fazer a conversão de uma entidade normal para um DTO
	private AlunoDTO toDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		return alunoDTO;
	}
		
	 // ----- DTO CONVERSOR - FIM -----
	
	//para puxar a lista de alunos
	//no <Aluno> tem que ficar o nome da classe usada
	public List<Aluno> getAllAlunos(){
		return alunoRepository.findAll(); 
		//esse find all vem do repository, por isso a gente instanciou ele acima
	}
	
	//get all DTO
	public List<AlunoDTO> getAllAlunosDTO(){
		//cria uma lista de alunos com todas que foram encontradas lá no banco com o "findAll"
		List<Aluno> listaAluno = alunoRepository.findAll();
		
		//cria uma lista vazia de "AlunoDTO" para preencher com as alunos encontradas acima
		List<AlunoDTO> listaAlunoDTO = new ArrayList<>();
		
		//1. Percorrer a lista de entidades Aluno (chamada listaAluno)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Aluno aluno: listaAluno) {
			//3. Transformar cada entidade existente na lista em um DTO
			AlunoDTO alunoDTO = toDTO(aluno);
			
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaAlunoDTO.add(alunoDTO);
		}
		//5. Retornar/devolver a lista de DTO preenchida
		return listaAlunoDTO;
	}
	
	//para pegar um único aluno do banco, pelo Id dele
	public Aluno getAlunoById(int id) {
		return alunoRepository.findById(id).orElse(null);
		//return alunoRepository.findById(id).get(); 
		//usa esse e não o getById porque esse tem alguns tratamentos de erro que o outro não tem, mas para retornar um registro pelo id os dois serviriam
		//esse indica "se achar, devolve o aluno com id, se não achar, manda a resposta como null"
		//usa esse e não o getById porque esse tem alguns tratamentos de erro que o outro não tem, mas para retornar um registro pelo id os dois serviriam
		//TAMBÉM PODERIA USAR-> return alunoRepository.findById(id).orElse(null); 
		//esse orElse diz que, se não encontrar o id que foi pesquisado ele retorna nulo e também pode ser usado no lugar do get()
	}
	
	//para salvar um aluno
	//tem que usar o (Aluno aluno) porque signfica que vai salvar um "objeto" do tipo Aluno (criado na classe Aluno)
	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	//para salvar como DTO
	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		//cria um "aluno" e coloca dentro dele o "alunoDTO" que foi preenchido lá fazendo a conversão do método "toEntidade"
		Aluno aluno = toEntidade(alunoDTO);
			
		//coloca no "novaAluno" o que foi salvo no banco
		Aluno novaAluno = alunoRepository.save(aluno);
			
		//pega o que está salvo no banco e guardado em "novaAluno", chama o toDTO para converter para DTO e coloca o valor em "alunoAtualizadaDTO"
		AlunoDTO alunoAtualizadaDTO = toDTO(novaAluno);
		return alunoAtualizadaDTO;
		//retorna o que foi preenchido e salvo no banco no formato DTO
	}
	
	//para atualizar um aluno
	//mesmo de cima, tem que usar o (Aluno aluno) porque signfica que vai atualizar um "objeto" do tipo Aluno (criado na classe Aluno)
	//adiciona o id porque primeiro ele vai buscar se já existe esse registro no banco
	public Aluno updateAluno (Aluno aluno, int id) { 
		
		Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();
		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		//primeiro ele pega os dados que já existem no banco para garantir que é um registro que já existe
		//porque se por algum motivo quam for atualizar deixar algum dado em branco, o método vai comparar os campos atuais com o que existe no banco, vai ver que tem uma coisa em branco, então vai interpretar como se fosse um registro novo
		//não coloca o set numeroMatriculaAluno porque a pessoa não pode trocar o Id, se não da problema
		
		return alunoRepository.save(alunoExistenteNoBanco);
		//usa o mesmo método "save" porque na documentação dele tem um if dizendo que se for novo ele registra no banco, se não, ele faz um merge com o que já existe lá
		//mas precisa da parte de cima, de achar ele primeiro, para garantir que se a pessoa deixar algum campo em branco, ele não vai criar um registro novo
	}
	
	//atualizar como DTO
	public AlunoDTO updateAlunoDTO (AlunoDTO alunoDTO, int id) { 
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadaDTO = new AlunoDTO();
		
		if(alunoExistenteNoBanco != null) {
			//pega o que foi digitado no "alunoDTO", chama o "toEntidade" para fazer a conversão e armazena no "alunoAtualizada"
			alunoExistenteNoBanco = toEntidade(alunoDTO);
			
			//salva no banco o que foi digitado
			Aluno alunoAtualizada = alunoRepository.save(alunoExistenteNoBanco);
			
			//pega o que está salvo no banco e guardado em "alunoAtualizada", chama o toDTO para converter para DTO e coloca o valor em "alunoAtualizadaDTO"
			alunoAtualizadaDTO = toDTO(alunoAtualizada);
			
		}
		return alunoAtualizadaDTO;
	}

	//para deletar um aluno
	public Aluno deleteAluno(int id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
		//como precisa ter o retorno de alguma coisa, mas o método de deletar é void, coloca para procurar para ele retornar se ainda existe ou não aquele id que supostamente foi apagado
	}
}
