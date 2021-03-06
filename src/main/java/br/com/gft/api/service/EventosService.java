package br.com.gft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.gft.api.exceptions.EventoExistenteException;
import br.com.gft.api.exceptions.EventoNaoEncontradoException;
import br.com.gft.model.Evento;
import br.com.gft.repository.Eventos;

@Service
public class EventosService {

	@Autowired
	private Eventos eventos;

	public List<Evento> listar() {
		return eventos.findAll();
	}

	public Optional<Evento> buscar(Long id) {
		Optional<Evento> evento = eventos.findById(id);

		if (evento.isEmpty()) {
			throw new EventoNaoEncontradoException("O evento não pode ser encontrado.");
		}

		return evento;
	}

	public Evento salvar(Evento evento) {
		if (evento.getId() != null) {
			Optional<Evento> a = eventos.findById(evento.getId());

			if (!a.isEmpty()) {
				throw new EventoExistenteException("O evento já existe");
			}
		}

		return eventos.save(evento);
	}

	public void deletar(Long id) {
		try {
			eventos.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EventoNaoEncontradoException("O evento não pode ser encontrado");
		}
	}

	public void atualizar(Evento evento) {
		verificarExistencia(evento);
		eventos.save(evento);
	}

	private void verificarExistencia(Evento evento) {
		buscar(evento.getId());
	}

	public List<Evento> listarCapacidadeCrescente() {

		return eventos.findAll(Sort.by(Sort.Direction.ASC, "ingresso"));
	}

	public List<Evento> listarCapacidadeDecrescente() {

		return eventos.findAll(Sort.by(Sort.Direction.DESC, "ingresso"));
	}

	public List<Evento> listarDataAsc() {

		return eventos.findAll(Sort.by(Sort.Direction.ASC, "data"));
	}

	public List<Evento> listarDataDesc() {

		return eventos.findAll(Sort.by(Sort.Direction.DESC, "data"));
	}

	public List<Evento> listarNomeAsc() {

		return eventos.findAll(Sort.by(Sort.Direction.ASC, "nomeEvento"));
	}

	public List<Evento> listarNomeDesc() {

		return eventos.findAll(Sort.by(Sort.Direction.DESC, "nomeEvento"));
	}
	public List<Evento> listarPrecoAsc() {

		return eventos.findAll(Sort.by(Sort.Direction.ASC, "valorIngresso"));
	}
	public List<Evento> listarPrecoDesc() {

		return eventos.findAll(Sort.by(Sort.Direction.DESC, "valorIngresso"));
	}
}
