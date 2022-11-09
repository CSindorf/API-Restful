package com.trabalhofinal.trabalho.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PedidoItensDTO {
	private Integer idPedido;
	private Instant dataPedido;
	private Double valorTotal;
	private List<ResumoItensDTO> resumoItensDTO = new ArrayList<>();
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Instant getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Instant dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<ResumoItensDTO> getResumoItensDTO() {
		return resumoItensDTO;
	}
	public void setResumoItensDTO(List<ResumoItensDTO> resumoItensDTO) {
		this.resumoItensDTO = resumoItensDTO;
	}
	
}
