package br.com.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.dto.ConsultaCep;
import br.com.ecommerce.dto.EnderecoDTO;
import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	 // ----- DTO CONVERSOR -----
	
	//método para fazer a conversão de um DTO para uma entidade normal
	private Endereco toEntidade (EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		return endereco;
	}
	
	//método para fazer a conversão de uma entidade normal para um DTO
	private EnderecoDTO toDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		return enderecoDTO;
	}
	
	 // ----- DTO CONVERSOR - FIM -----
	
	//get all
	public List<Endereco> getAllEnderecos(){
		return enderecoRepository.findAll(); 
	}
	
	//get id
	public Endereco getEnderecoById(int id) {
		return enderecoRepository.findById(id).orElse(null);
	}
	
	//save
	public ConsultaCep consultaCepApiExterna(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = "https://viacep.com.br/ws/{cep}/json";
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("cep", cep);
		
		ConsultaCep consultaCep = restTemplate.getForObject(uri, ConsultaCep.class, params);
		
		return consultaCep;
	}
	
	public Endereco saveEnderecoFromApi(String cep) {
		ConsultaCep consultaCep = consultaCepApiExterna(cep);
		
		Endereco endereco = new Endereco();
		
		endereco.setCep(consultaCep.getCep());
		endereco.setBairro(consultaCep.getBairro());
		endereco.setCidade(consultaCep.getLocalidade());
		endereco.setComplemento(consultaCep.getComplemento());
		endereco.setNumero(consultaCep.getNumero());
		endereco.setRua(consultaCep.getLogradouro());
		endereco.setUf(consultaCep.getUf());
		
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
