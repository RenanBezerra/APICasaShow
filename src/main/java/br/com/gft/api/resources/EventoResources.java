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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags ="Evento")
@RestController
@RequestMapping("/api/evento")
public class EventoResources {

	@Autowired
	private EventosService eventoService;
	
	@ApiOperation("Lista eventos")
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Evento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listar());
	}
	
	@ApiOperation("Salva o evento")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name ="corpo",value="Representação de um novo evento")@Valid@RequestBody Evento evento ) {
		evento = eventoService.salvar(evento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(evento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	@ApiOperation("Lista o evento")
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<Evento>> buscar (@ApiParam(value="ID de um Evento",example="1")@PathVariable("id") Long id){
		Optional<Evento> evento = eventoService.buscar(id);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(evento);
		
	}
	@ApiOperation("Deleta o evento")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@ApiParam(value="ID de um Evento",example="1")@PathVariable("id")Long id) {
	
			eventoService.deletar(id);
		
		return ResponseEntity.noContent().build();
			
		}
	@ApiOperation("Atualiza o evento")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>atualizar(@ApiParam(name ="corpo",value="Representação de um Evento com os novos dados")@RequestBody Evento evento,
			@PathVariable("id")Long id) {
		evento.setId(id);
		eventoService.atualizar(evento);
	
		return ResponseEntity.noContent().build();
	}
	@ApiOperation("Lista capacidade em ordem crescente")
	@GetMapping(value= "/capacidade/asc")
	public ResponseEntity<List<Evento>> listarCrescente() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarCapacidadeCrescente());
	}
	@ApiOperation("Lista capacidade em ordem decrescente")
	@GetMapping(value= "/capacidade/desc")
	public ResponseEntity<List<Evento>> listarDecrescente() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarCapacidadeDecrescente());
	}
	@ApiOperation("Lista data em ordem crescente")
	@GetMapping(value= "/data/asc")
	public ResponseEntity<List<Evento>> listarDataAsc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarDataAsc());
	}
	@ApiOperation("Lista data em ordem decrescente")
	@GetMapping(value= "/data/desc")
	public ResponseEntity<List<Evento>> listarDataDesc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarDataDesc());
	}
	@ApiOperation("Lista nome em ordem crescente")
	@GetMapping(value= "/nome/asc")
	public ResponseEntity<List<Evento>> listarNomeAsc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarNomeAsc());
	}
	@ApiOperation("Lista nome em ordem decrescente")
	@GetMapping(value= "/nome/desc")
	public ResponseEntity<List<Evento>> listarNomeDesc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarNomeDesc());
	}
	@ApiOperation("Lista valor do ingresso em ordem crescente")
	@GetMapping(value= "/preco/asc")
	public ResponseEntity<List<Evento>> listarPrecoAsc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarPrecoAsc());
	}
	@ApiOperation("Lista valor do ingresso em ordem decrescente")
	@GetMapping(value= "/preco/desc")
	public ResponseEntity<List<Evento>> listarPrecoDesc() {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listarPrecoDesc());
	}
}
