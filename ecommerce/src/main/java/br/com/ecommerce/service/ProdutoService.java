package br.com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.entity.Produto;
import br.com.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	//get all
	public List<Produto> getAllProdutos(){
		return produtoRepository.findAll(); 
	}
	
	//get id
	public Produto getProdutoById(int id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	//save
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//update
	public Produto updateProduto (Produto produto, int id) { 
		
		Produto produtoExistenteNoBanco = produtoRepository.findById(id).get();
		produtoExistenteNoBanco.setNome(produto.getNome());
		produtoExistenteNoBanco.setDescricao(produto.getDescricao());
		produtoExistenteNoBanco.setQtdestoque(produto.getQtdestoque());
		produtoExistenteNoBanco.setDataCadastro(produto.getDataCadastro());
		produtoExistenteNoBanco.setValorUnitario(produto.getValorUnitario());
		produtoExistenteNoBanco.setCategoria(produto.getCategoria());
		
		return produtoRepository.save(produtoExistenteNoBanco);
	}
	
	//delete
	public Produto deleteProduto(int id) {
		produtoRepository.deleteById(id);
		return getProdutoById(id);
	}
}
