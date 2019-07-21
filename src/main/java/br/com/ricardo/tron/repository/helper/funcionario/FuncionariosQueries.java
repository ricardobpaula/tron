package br.com.ricardo.tron.repository.helper.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ricardo.tron.model.Funcionario;
import br.com.ricardo.tron.repository.filter.FuncionarioFilter;

public interface FuncionariosQueries {
	
	public Page<Funcionario> filtrar(FuncionarioFilter filtro, Pageable pageable);

}
