package br.com.gft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.model.EspacoEvento;
import br.com.gft.repository.Casas;

@Service
public class CadastroCasaService {
	
	@Autowired
	private Casas casas;
	
	public void salvar (EspacoEvento casaEvento) {
		casas.save(casaEvento);
		
	}

	public void excluirCasa(Long codigo) {
		casas.deleteById(codigo);
		
	}

}
