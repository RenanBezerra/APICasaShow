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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Espaço-Evento")
@RestController
@RequestMapping("/api")
public class EspacoEventoResources {
	
	@Autowired
	private CasasService casasService;

	@ApiOperation("Lista as casas")
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<EspacoEvento>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listar());
	}
	@ApiOperation("Salva as casas")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name ="corpo",value="Representação de um novo Espaço Evento")@Valid@RequestBody EspacoEvento casaEvento) {
		 casaEvento = casasService.salvar(casaEvento);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(casaEvento.getCodigo()).toUri();
	
		 return ResponseEntity.created(uri).build();
	}
	@ApiOperation("Lista a casa")
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<EspacoEvento>> buscar(@ApiParam(value ="ID de um Espaco_Evento",example="1")@PathVariable("id") Long id) {
		Optional<EspacoEvento> espacoEvento = casasService.buscar(id);
		

		return ResponseEntity.status(HttpStatus.OK).body(espacoEvento);
		}
	
	
	@ApiOperation("Deleta a casa ")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>deletar(@ApiParam(value ="ID de um Espaco_Evento",example="1")@PathVariable("id")Long id) {
		
			casasService.deletar(id);
			
			return ResponseEntity.noContent().build();
	}
	
	
	@ApiOperation("Atualiza a casa")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>atualizar(@ApiParam(name ="corpo",value="Representação de um Espaço Evento com os novos dados")@RequestBody EspacoEvento casaEvento,
			@PathVariable("id")Long codigo) {
		casaEvento.setCodigo(codigo);
		

			casasService.atualizar(casaEvento);
		
		return ResponseEntity.noContent().build();
		
		
	}
	
	@ApiOperation("Lista as casas em ordem alfabética crescente por nome")
	@GetMapping(value="/asc",produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<EspacoEvento>> listarCrescente() {
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarCrescente());
	}
	
	
	@ApiOperation("Lista as casas em ordem alfabética decrescente por nome ")
	@GetMapping(value="/desc", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<EspacoEvento>> listarDecrescente() {
		return ResponseEntity.status(HttpStatus.OK).body(casasService.listarDecrescente());
	}
}
