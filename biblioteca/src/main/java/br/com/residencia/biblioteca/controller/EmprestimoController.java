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

import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.exception.NoSuchElementFoundException;
import br.com.residencia.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")

public class EmprestimoController {
	
	@Autowired
	EmprestimoService emprestimoService;
	
	@GetMapping
	public ResponseEntity<List<Emprestimo>> getAllEmprestimos(){
		return new ResponseEntity <>(emprestimoService.getAllEmprestimos(),HttpStatus.OK); 
	}
	
	//get all usando DTO
	@GetMapping("/dto")
	public ResponseEntity<List<EmprestimoDTO>> getAllEmprestimosDTO(){
		return new ResponseEntity<>(emprestimoService.getAllEmprestimosDTO(),HttpStatus.OK);
	}
	
	//get by id
	@GetMapping("/{id}")
	public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable int id) {	
		Emprestimo emprestimo = emprestimoService.getEmprestimoById(id);
		if(null == emprestimo)
			throw new NoSuchElementFoundException("Não foi encontrar empréstimo com id "+id);
		else
			return new ResponseEntity <>(emprestimo,HttpStatus.OK);
	}
	
	//save normal
	@PostMapping
	public ResponseEntity<Emprestimo> saveEmprestimo(@RequestBody Emprestimo emprestimo) {
		return new ResponseEntity <>(emprestimoService.saveEmprestimo(emprestimo),HttpStatus.CREATED);
	}
	
	//aqui chama o método DTO, que é usado para corrigir alguns problemas do looping infinito
	@PostMapping("/dto")
	public ResponseEntity<EmprestimoDTO> saveEmprestimoDTO(@RequestBody EmprestimoDTO emprestimoDTO) {
		return new ResponseEntity <>(emprestimoService.saveEmprestimoDTO(emprestimoDTO),HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable int id){
		Emprestimo emprestimo2 = emprestimoService.getEmprestimoById(id);
		if(null == emprestimo2)
			throw new NoSuchElementFoundException("Não foi encontrar empréstimo com id "+id);
		else
			return new ResponseEntity <>(emprestimoService.updateEmprestimo(emprestimo, id),HttpStatus.OK);
	}
	
	//Update DTO
	@PutMapping("/dto/{id}")
	public ResponseEntity<EmprestimoDTO> updateEmprestimoDTO(@RequestBody EmprestimoDTO emprestimoDTO, @PathVariable int id){
		Emprestimo emprestimo = emprestimoService.getEmprestimoById(id);
		if(null == emprestimo)
			throw new NoSuchElementFoundException("Não foi encontrar empréstimo com id "+id);
		else
			return new ResponseEntity <>(emprestimoService.updateEmprestimoDTO(emprestimoDTO, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Emprestimo> deleteEmprestimo(@PathVariable int id) {
		Emprestimo emprestimo = emprestimoService.getEmprestimoById(id);
		if(null == emprestimo)
			throw new NoSuchElementFoundException("Não foi encontrar empréstimo com id "+id);
		else
			return new ResponseEntity <>(emprestimoService.deleteEmprestimo(id),HttpStatus.OK);
	}
}
