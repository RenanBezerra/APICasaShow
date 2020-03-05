package br.com.gft.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


import br.com.gft.api.service.UsuariosService;
import br.com.gft.model.Usuarios;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags ="Usuarios" )
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosResources {

	@Autowired
	private UsuariosService usuariosService;
	
	@ApiOperation("Atualiza os usuarios")
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Usuarios>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(usuariosService.listar());
	}
	@ApiOperation("Salva o usuario")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name ="corpo",value="Representação de novo Usuario")@RequestBody Usuarios usuarios ) {
		usuarios = usuariosService.salvar(usuarios);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuarios.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}
	
	@ApiOperation("Lista o usuario")
	@GetMapping(value="/{id}")
	public ResponseEntity<?> buscar (@ApiParam(value ="ID de o Usuario",example="1")@PathVariable("id") Long id){
	
			
		Optional<Usuarios> usuarios = usuariosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	@ApiOperation("Deleta o usuario")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@ApiParam(value ="ID de o Usuario",example="1")@PathVariable("id")Long id) {
			usuariosService.deletar(id);
		return ResponseEntity.noContent().build();
			
		}
	@ApiOperation("Atualiza o usuario")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> atualizar(@ApiParam(name ="corpo",value="Representação de um Usuario com os novos dados")@RequestBody Usuarios usuarios,
			@PathVariable("id")Long id) {
		usuarios.setId(id);
		
			usuariosService.atualizar(usuarios);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
}
