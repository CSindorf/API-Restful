package br.com.residencia.biblioteca.dto;

public class EditoraDTO {
	private int codigoeditora;
	private String nome;
	
	public EditoraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EditoraDTO(int codigoeditora, String nome) {
		this.codigoeditora = codigoeditora;
		this.nome = nome;
	}

	public int getCodigoeditora() {
		return codigoeditora;
	}

	public void setCodigoeditora(int codigoeditora) {
		this.codigoeditora = codigoeditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
