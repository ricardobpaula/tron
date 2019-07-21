package br.com.ricardo.tron.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ricardo.tron.model.Usuario;
import br.com.ricardo.tron.repository.helper.usuario.UsuariosQueries;


public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	public Optional<Usuario> findByLoginIgnoreCase(String login);
	public List<Usuario> findByIdIn(Long[] ids);

}
