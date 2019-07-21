package br.com.ricardo.tron.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ricardo.tron.model.Usuario;
import br.com.ricardo.tron.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	public Optional<Usuario> porLoginEAtivo(String login);
	
	public List<String> permissoes(Usuario usuario);
	
	public Usuario buscarComGrupos(Long id);

}
