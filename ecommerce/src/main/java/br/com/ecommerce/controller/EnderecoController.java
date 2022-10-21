package br.com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.ConsultaCep;
import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;
	
	//get all
	@GetMapping
	public ResponseEntity<List<Endereco>> getAllEnderecos(){
		return new ResponseEntity <>(enderecoService.getAllEnderecos(),HttpStatus.OK); 
	}
	
	//get id
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable int id) {
		Endereco endereco = enderecoService.getEnderecoById(id);
		if(null != endereco)
		return new ResponseEntity <>(endereco,HttpStatus.OK); 
		else
			return new ResponseEntity <>(endereco,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/consulta-cnpj/{cep}")
	public ResponseEntity<ConsultaCep> consultaCepApiExterna(@PathVariable String cep){
		ConsultaCep consultaCep = enderecoService.consultaCepApiExterna(cep);
		if(null == consultaCep)
			return new ResponseEntity <>(consultaCep,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(consultaCep,HttpStatus.OK);
	}
	
	//save
	@GetMapping("cep/{cep}")
	public ResponseEntity<Endereco> saveEnderecoFromApi(@PathVariable String cep) {
		return new ResponseEntity <>(enderecoService.saveEnderecoFromApi(cep),HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, @PathVariable int id){
		return new ResponseEntity <>(enderecoService.updateEndereco(endereco, id),HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Endereco> deleteEndereco(@PathVariable int id) {
		Endereco endereco = enderecoService.getEnderecoById(id);
		if(null == endereco)
			return new ResponseEntity <>(endereco,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(enderecoService.deleteEndereco(id),HttpStatus.OK);
	}
}
