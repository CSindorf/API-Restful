package br.com.ecommerce.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idItemPedido"
		)
@Entity //diz o tipo de classe
@Table(name = "itempedido") //diz com qual tabela ela se comunica
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "iditempedido")
	private int idItemPedido;
	
	@Column (name = "quantidade")
	private int quantidade;
	
	@Column (name = "precovenda")
	private BigDecimal precoVenda;
	
	@Column (name = "percentualdesconto")
	private BigDecimal percentualDeconto;
	
	@Column (name = "valorbruto")
	private BigDecimal valorBruto;
	
	@Column (name = "valorliquido")
	private BigDecimal valorLiquido;
	
	@ManyToOne
	@JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
	private Produto produto; 
	
	@ManyToOne
	@JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
	private Pedido pedido;

	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPercentualDeconto() {
		return percentualDeconto;
	}

	public void setPercentualDeconto(BigDecimal percentualDeconto) {
		this.percentualDeconto = percentualDeconto;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	} 
	
}
