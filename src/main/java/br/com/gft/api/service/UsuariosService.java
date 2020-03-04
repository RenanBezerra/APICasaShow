package br.com.gft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.api.exceptions.UsuarioExistenteException;
import br.com.gft.api.exceptions.UsuarioNaoEncontradoException;
import br.com.gft.model.Usuarios;
import br.com.gft.repository.ListaUsuarios;

@Service
public class UsuariosService {

	@Autowired
	private ListaUsuarios lista;
	
	
	public List<Usuarios> listar(){
		return lista.findAll();
	}
	
	public Optional<Usuarios> buscar(Long id) {
		Optional<Usuarios> usuarios = lista.findById(id);
		
		if(usuarios.isEmpty()) {
			throw new UsuarioNaoEncontradoException("O usuarios não pode ser encontrado.");
		}
		
		return usuarios;
	}
	
	public Usuarios salvar(Usuarios usuarios) {
		if(usuarios.getId() != null) {
			Optional<Usuarios> a = lista.findById(usuarios.getId());
			
			if(!a.isEmpty()) {
				throw new UsuarioExistenteException("o usuarios ja existe");
			}
		}
		
		
		return lista.save(usuarios);
	}
	
	public void deletar(Long id) {
		try{
			lista.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException("O usuarios não pode ser encontrado");
		}
		
	}
	
	public void atualizar(Usuarios usuarios) {
		verificarExistencia(usuarios);
		lista.save(usuarios);
	}
	
	private void verificarExistencia(Usuarios usuarios) {
		buscar(usuarios.getId());
	}
	
}
