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

import br.com.gft.api.service.CasasService;
import br.com.gft.model.EspacoEvento;

@RestController
@RequestMapping("/api")
public class EspacoEventoResources {
	
	@Autowired
	private CasasService casasService;

	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<EspacoEvento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listar());
	}
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid@RequestBody EspacoEvento casaEvento) {
		 casaEvento = casasService.salvar(casaEvento);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(casaEvento.getCodigo()).toUri();
	
		 return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<EspacoEvento>> buscar(@PathVariable("id") Long id) {
		Optional<EspacoEvento> espacoEvento = casasService.buscar(id);
		

		return ResponseEntity.status(HttpStatus.OK).body(espacoEvento);
		}
	
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@PathVariable("id")Long id) {
		
			casasService.deletar(id);
			
			return ResponseEntity.noContent().build();
	}
	
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>atualizar(@RequestBody EspacoEvento casaEvento,
			@PathVariable("id")Long codigo) {
		casaEvento.setCodigo(codigo);
		

			casasService.atualizar(casaEvento);
		
		return ResponseEntity.noContent().build();
		
		
	}
}
