package br.com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idEndereco")
@Entity //diz o tipo de classe
@Table(name = "enderecos") //diz com qual tabela ela se comunica
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "idendereco")
	private int idEndereco;
	
	@Column (name = "cep")
	private String cep;
	
	@Column (name = "rua")
	private String rua;
	
	@Column (name = "bairro")
	private String bairro;
	
	@Column (name = "cidade")
	private String cidade;
	
	@Column (name = "numero")
	private int numero;
	
	@Column (name = "complemento")
	private String complemento;
	
	@Column (name = "uf")
	private String uf;
	
	@OneToOne
	private Cliente cliente;

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", numero=" + numero + ", complemento=" + complemento + ", uf=" + uf
				+ ", cliente=" + cliente + "]";
	}
	
}
