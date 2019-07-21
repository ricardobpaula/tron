package br.com.ricardo.tron.repository.helper.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ricardo.tron.model.Fornecedor;
import br.com.ricardo.tron.repository.filter.FornecedorFilter;

public interface FornecedoresQueries {

	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);
	
}

	
