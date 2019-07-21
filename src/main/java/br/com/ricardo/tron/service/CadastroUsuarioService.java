package br.com.ricardo.tron.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.ricardo.tron.model.Usuario;
import br.com.ricardo.tron.repository.Usuarios;
import br.com.ricardo.tron.service.exception.ImpossivelExcluirEntidadeException;
import br.com.ricardo.tron.service.exception.LoginUsuarioJaCadastradoException;
import br.com.ricardo.tron.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional 
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarios.findByLoginIgnoreCase(usuario.getLogin());
		if (usuarioOptional.isPresent() && !usuarioOptional.get().equals(usuario)) {
			throw new LoginUsuarioJaCadastradoException("Login já cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória");
		}
		
		if(usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		}else if(StringUtils.isEmpty(usuario.getSenha())){
			usuario.setSenha(usuarioOptional.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		
		
		if(!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioOptional.get().getAtivo());
		}

		usuarios.save(usuario); 
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		try {
			usuarios.delete(usuario); 
			usuarios.flush(); 
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar usuario. Possui referência.");
		}
	}

}
