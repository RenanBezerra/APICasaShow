package br.com.gft.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.model.Usuarios;
import br.com.gft.repository.ListaUsuarios;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosResources {

	@Autowired
	private ListaUsuarios lista;
	
	@GetMapping
	public ResponseEntity<List<Usuarios>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(lista.findAll());
	}
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Usuarios usuarios ) {
		usuarios = lista.save(usuarios);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuarios.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<Usuarios>> buscar (@PathVariable("id") Long id){
		Optional<Usuarios> usuarios = lista.findById(id);

		if(usuarios.isEmpty()) {
				return ResponseEntity.notFound().build();
				
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@PathVariable("id")Long id) {
		try {
			lista.deleteById(id);
		} catch(EmptyResultDataAccessException e){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
			
		}
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Usuarios usuarios,
			@PathVariable("id")Long id) {
		usuarios.setId(id);
		lista.save(usuarios);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
