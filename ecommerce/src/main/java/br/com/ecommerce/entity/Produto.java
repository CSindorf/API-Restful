package br.com.ecommerce.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idProduto"
		)
@Entity //diz o tipo de classe
@Table(name = "produtos") //diz com qual tabela ela se comunica
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "idproduto")
	private int idProduto;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "descricao", unique = true)
	private String descricao;
	
	@Column (name = "qtdestoque")
	private int qtdestoque;
	
	@Column (name = "datacadastro")
	private Instant dataCadastro;
	
	@Column (name = "valorunitario")
	private BigDecimal valorUnitario;
	
	@Column (name = "imagem")
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto")  
	private Set<ItemPedido> itempedido;

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdestoque() {
		return qtdestoque;
	}

	public void setQtdestoque(int qtdestoque) {
		this.qtdestoque = qtdestoque;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	} 
	
}
