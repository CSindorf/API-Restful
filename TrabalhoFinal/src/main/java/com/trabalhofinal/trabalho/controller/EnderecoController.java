package com.trabalhofinal.trabalho.controller;

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

import com.trabalhofinal.trabalho.dto.EnderecoDTO;
import com.trabalhofinal.trabalho.entity.Endereco;
import com.trabalhofinal.trabalho.service.EnderecoService;

import br.com.ecommerce.dto.ConsultaCep;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

// CONTROLLER DOS DTO's
	@GetMapping("/search")
	public ResponseEntity<List<EnderecoDTO>> getAllEnderecos() {
		return new ResponseEntity<>(enderecoService.getAllEnderecos(), HttpStatus.OK);
	}

	@GetMapping("search/id/{id}")
	public ResponseEntity<EnderecoDTO> getById(@PathVariable int id) {
		EnderecoDTO enderecoDTO = enderecoService.getById(id);
		if (enderecoDTO != null) {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/consulta-cnpj/{cep}")
	public ResponseEntity<ConsultaCep> consultaCepApiExterna(@PathVariable String cep){
		ConsultaCep consultaCep = enderecoService.consultaCepApiExterna(cep);
		if(null == consultaCep)
			return new ResponseEntity <>(consultaCep,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity <>(consultaCep,HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Endereco> saveEnderecoFromApi(@PathVariable String cep) {
		return new ResponseEntity <>(enderecoService.saveEnderecoFromApi(cep),HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<EnderecoDTO> updateEditoraDTO(@RequestBody EnderecoDTO enderecoDTO,
			@PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.update(enderecoDTO, id), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EnderecoDTO> deleteClienteDTO(int id) {
		EnderecoDTO enderecoDTO = enderecoService.getById(id);
		if (enderecoDTO == null) {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(enderecoService.delete(id), HttpStatus.OK);
		}
	}
}
