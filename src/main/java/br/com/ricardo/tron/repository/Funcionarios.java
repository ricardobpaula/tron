package br.com.ricardo.tron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ricardo.tron.model.Funcionario;
import br.com.ricardo.tron.repository.helper.funcionario.FuncionariosQueries;

	public interface Funcionarios extends JpaRepository<Funcionario, Long>, FuncionariosQueries{

}
