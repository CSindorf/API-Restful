package br.com.residencia.biblioteca.dto;

import java.util.List;

public class EditoraDTO {
	private int codigoeditora;
	private String nome;
	private List<LivroDTO> listaLivrosDTO; //criou a lista de livros para poder consultar quais livros cada editora possui

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

	public List<LivroDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(List<LivroDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}
	
}
