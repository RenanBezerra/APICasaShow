package br.com.gft.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.gft.api.service.EventosService;
import br.com.gft.model.Evento;

@RestController
@RequestMapping("/api/evento")
public class EventoResources {

	@Autowired
	private EventosService eventoService;
	
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Evento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid@RequestBody Evento evento ) {
		evento = eventoService.salvar(evento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(evento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<Evento>> buscar (@PathVariable("id") Long id){
		Optional<Evento> evento = eventoService.buscar(id);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(evento);
		
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@PathVariable("id")Long id) {
	
			eventoService.deletar(id);
		
		return ResponseEntity.noContent().build();
			
		}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>atualizar(@RequestBody Evento evento,
			@PathVariable("id")Long id) {
		evento.setId(id);
		eventoService.atualizar(evento);
	
		return ResponseEntity.noContent().build();
	}
}
