package br.com.gft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gft.model.Evento;
import br.com.gft.repository.Eventos;

@Service
public class CadastroEventoService {
	
	@Autowired
	private Eventos eventos;
	
	public void salvar (Evento evento) {
		try {
			eventos.save(evento);
			
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data invalido!");
		}
	}

	public void excluirEvento(Long id) {
		eventos.deleteById(id);
	}
	

}
