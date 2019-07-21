package br.com.ricardo.tron.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ricardo.tron.model.Fornecedor;
import br.com.ricardo.tron.repository.helper.fornecedor.FornecedoresQueries;

public interface Fornecedores extends JpaRepository<Fornecedor, Long>, FornecedoresQueries {

	//public Optional<Fornecedor> findByNomeIgnoreCase(String nome);
	
}
