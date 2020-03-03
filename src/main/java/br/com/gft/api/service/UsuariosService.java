package br.com.gft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.model.Usuarios;
import br.com.gft.repository.ListaUsuarios;

@Service
public class UsuariosService {

	@Autowired
	private ListaUsuarios lista;
	
	
	public List<Usuarios> listar(){
		return lista.findAll();
	}
	
	public Usuarios buscar(Long id) {
		Optional<Usuarios> usuarios = lista.findById(id);
		
		if(usuarios.isEmpty()) {
			
		}
		
		return null;
	}
}
