package br.com.ricardo.tron.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.tron.model.Produto;
import br.com.ricardo.tron.repository.Produtos;
import br.com.ricardo.tron.service.exception.CodigoProdutoJaCadastradoException;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;

@Service  
public class CadastroProdutoService {
	@Autowired
	private Produtos produtos;
	
	@Transactional 
	public void salvar(Produto produto) {
		Optional<Produto> produtoOptional = produtos.findByCodigoIgnoreCase(produto.getCodigo());
		if (produtoOptional.isPresent() && produto.isNovo()) {
			throw new CodigoProdutoJaCadastradoException("Codigo de produto já cadastrado");
		}
		
		produtos.save(produto); 

		
	}
	
	@Transactional
	public void excluir(Produto produto) {
		try {
			produtos.delete(produto); 
			produtos.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar produto. Possui referência.");
		}
	}
		
} 
