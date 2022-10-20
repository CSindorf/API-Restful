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
		property = "idPedido"
		)
@Entity //diz o tipo de classe
@Table(name = "pedidos") //diz com qual tabela ela se comunica
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "idpedido")
	private int idPedido;
	
	@Column (name = "datapedido")
	private Instant dataPedido;
	
	@Column (name = "dataentrega")
	private Instant dataEntrega;
	
	@Column (name = "dataenvio")
	private Instant dataEnvio;
	
	@Column (name = "status")
	private String status;
	
	@Column (name = "valortotal")
	private BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
	private Cliente cliente; 
	
	@OneToMany(mappedBy = "pedido")  
	private Set<ItemPedido> itempedido;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Instant getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Instant dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Instant getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Instant dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItempedido() {
		return itempedido;
	}

	public void setItempedido(Set<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}
	
}
