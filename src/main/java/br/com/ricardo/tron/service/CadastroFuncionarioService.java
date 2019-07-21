package br.com.ricardo.tron.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.tron.model.Funcionario;
import br.com.ricardo.tron.repository.Funcionarios;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroFuncionarioService {
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Transactional 
	public void salvar(Funcionario funcionario) {
		 funcionarios.save(funcionario);
		
		
		
	}
	
	@Transactional
	public void excluir(Funcionario funcionario) {
		try {
			funcionarios.delete(funcionario); 
			funcionarios.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar funcionario. Possui referência.");
		}
	}

}
