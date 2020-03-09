package br.com.gft.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.api.service.ComprasService;
import br.com.gft.model.Compra;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "Vendas")
@RestController
@RequestMapping("/api/compras")
public class CompraResources {

	@Autowired
	private ComprasService compraService;
	
	@ApiOperation("Lista as compras")
	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Compra>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(compraService.listar());
	}
	@ApiOperation("Salva as compras")
	@PostMapping
	public ResponseEntity<Void> salvar(@ApiParam(name ="corpo",value="Representação de uma nova compra")@Valid@RequestBody Compra compra) {
		 compra = compraService.salvar(compra);
		 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(compra.getId()).toUri();
	
		 return ResponseEntity.created(uri).build();
	}
	@ApiOperation("Lista a compra")
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<Compra>> buscar(@ApiParam(value ="ID de uma compra",example="1")@PathVariable("id") Long id) {
		Optional<Compra> compra = compraService.buscar(id);
		

		return ResponseEntity.status(HttpStatus.OK).body(compra);
		}
	
	
}
