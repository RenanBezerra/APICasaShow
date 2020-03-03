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

import br.com.gft.model.EspacoEvento;
import br.com.gft.repository.Casas;

@RestController
@RequestMapping("/api")
public class EspacoEventoResources {
	
	@Autowired
	private Casas casas;

	@GetMapping
	public ResponseEntity<List<EspacoEvento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(casas.findAll());
	}
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody EspacoEvento casaEvento) {
		 casaEvento = casas.save(casaEvento);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(casaEvento.getCodigo()).toUri();
	
		 return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<EspacoEvento>> buscar(@PathVariable("id") Long id) {
		Optional<EspacoEvento> espacoEvento = casas.findById(id);
		
		if(espacoEvento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(espacoEvento);
		
		
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@PathVariable("id")Long id) {
		try {
			casas.deleteById(id);
			} catch(EmptyResultDataAccessException e) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.noContent().build();
	}
	
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>atualizar(@RequestBody EspacoEvento casaEvento,
			@PathVariable("id")Long codigo) {
		casaEvento.setCodigo(codigo);
		casas.save(casaEvento);
	
		return ResponseEntity.noContent().build();
	}
}
