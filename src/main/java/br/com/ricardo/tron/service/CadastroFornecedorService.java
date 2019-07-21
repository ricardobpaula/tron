package br.com.ricardo.tron.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardo.tron.model.Fornecedor;
import br.com.ricardo.tron.repository.Fornecedores;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;


@Service
public class CadastroFornecedorService {

	@Autowired
	private Fornecedores fornecedores;

	
	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {	
		return fornecedores.save(fornecedor);
		}
	
	@Transactional
	public void excluir(Fornecedor fornecedor) {
		try {
			fornecedores.delete(fornecedor); 
			fornecedores.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar fornecedor. Possui referência.");
		}
	}
	
	
	
}
