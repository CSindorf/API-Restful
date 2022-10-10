package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.service.AlunoService;

@RestController //anotação para saber que a classe é de um controller
@RequestMapping("/alunos") //anotação para definir qual vai ser o endereço desse controller. O http://localhost não precisa, somente o que viria depois da /

public class AlunoController {
	
	@Autowired //o Spring Tools controla a utilização da instância abaixo, então assim não precisa criar um objeto com o "= new AlunoRepository"
	AlunoService alunoService;
	
	//agora temos que disponibilizar os métodos crud, que foram feitos no service (listar, editar, atualizar, salvar, etc)
	
	//o GetMapping informa por qual método vai recuperar essa listagem
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos(){
		return new ResponseEntity <>(alunoService.getAllAlunos(),HttpStatus.OK); 
		//o ResponseEntity permite manipular o status de retorno da requisição
	}
	
	//aqui tem o /{id} porque na url ele vai retornar o caminho todo + o id do aluno: /aluno/id-do-aluno
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable int id) {
		//esse PathVariable indica que uma variável é retornada para a url, nesse caso, o id do aluno
		Aluno aluno = alunoService.getAlunoById(id);
		if(null != aluno)
		return new ResponseEntity <>(aluno,HttpStatus.OK); 
		else
			return new ResponseEntity <>(aluno,HttpStatus.NOT_FOUND);
		
		//aqui cria um objeto do aluno pesquisado para fazer a pesquisa do if
		//se o aluno for diferente de null, então se achar um aluno, ele da o ok e mostra os dados
		//se não encontrar o aluno, ele da o status de not found ao invés de um erro
	}
	
	//aqui usa o post porque ele vai enviar uma requisição para salvar um aluno no banco
	@PostMapping
	public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno) {
		//o RequestBody informa que os dados vão ser enviados no body da requisição. Se não colocar isso, ele não acha os dados e não funciona
		
		return new ResponseEntity <>(alunoService.saveAluno(aluno),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno aluno, @PathVariable int id){
		return new ResponseEntity <>(alunoService.updateAluno(aluno, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> deleteAluno(@PathVariable int id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if(null == aluno)
			return new ResponseEntity <>(aluno,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(alunoService.deleteAluno(id),HttpStatus.OK);
		
		//verifica se o id existe antes de deletar. Se não existe já da o not found e não um erro
	}
	
	/*@GetMapping("/hello")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity <>("Funcionamento",HttpStatus.NOT_FOUND);
		
		//o ResponseEntity permite manipular o status de retorno da requisição. Nesse caso ela dava OK, agora mudou para dar NOT_FOUND
		//serve para se procurar um aluno que não existe por exemplo, ele não dar um retorno de OK mesmo rodando a api direito, ele precisa dizer que não achou
	}*/
}
