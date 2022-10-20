package br.com.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	//get all
	public List<Endereco> getAllEnderecos(){
		return enderecoRepository.findAll(); 
	}
	
	//get id
	public Endereco getEnderecoById(int id) {
		return enderecoRepository.findById(id).orElse(null);
	}
	
	//save
	public Endereco consultaCepApiExterna(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cep", cep);
		
		Endereco consultaCep = restTemplate.getForObject(uri, Endereco.class, params);
		
		return consultaCep;
	}
	
	public Endereco saveEnderecoFromApi(String cep) {
		Endereco consultaCep = consultaCepApiExterna(cep);
		
		Endereco endereco = new Endereco();
		
		endereco.setCep(consultaCep.getCep());
		
		return enderecoRepository.save(endereco);
	}
	
	//update
	public Endereco updateEndereco (Endereco endereco, int id) { 
		
		Endereco enderecoExistenteNoBanco = enderecoRepository.findById(id).get();
		enderecoExistenteNoBanco.setBairro(endereco.getBairro());
		enderecoExistenteNoBanco.setCidade(endereco.getCidade());
		enderecoExistenteNoBanco.setComplemento(endereco.getComplemento());
		enderecoExistenteNoBanco.setNumero(endereco.getNumero());
		enderecoExistenteNoBanco.setRua(endereco.getRua());
		enderecoExistenteNoBanco.setUf(endereco.getUf());
		
		return enderecoRepository.save(enderecoExistenteNoBanco);
	}
	
	//delete
	public Endereco deleteEndereco(int id) {
		enderecoRepository.deleteById(id);
		return getEnderecoById(id);
	}
	
	
}
