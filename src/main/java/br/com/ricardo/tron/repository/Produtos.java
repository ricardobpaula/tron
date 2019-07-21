package br.com.ricardo.tron.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ricardo.tron.model.Produto;
import br.com.ricardo.tron.repository.helper.produto.ProdutosQueries;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {
	
	public Optional<Produto> findByCodigoIgnoreCase(String codigo);

}
