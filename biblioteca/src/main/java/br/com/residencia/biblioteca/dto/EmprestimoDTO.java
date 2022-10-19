package br.com.residencia.biblioteca.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmprestimoDTO {
	private int codigoEmprestimo;
	private Instant dataEmprestimo;
	private Instant dataEntrega;
	private BigDecimal valorEmprestimo;
	public int getCodigoEmprestimo() {
		return codigoEmprestimo;
	}
	public void setCodigoEmprestimo(int codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}
	public Instant getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(Instant dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public Instant getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public BigDecimal getValorEmprestimo() {
		return valorEmprestimo;
	}
	public void setValorEmprestimo(BigDecimal valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}
	
}
