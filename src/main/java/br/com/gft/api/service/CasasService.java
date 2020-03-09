package br.com.gft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.gft.api.exceptions.EspacoEventoExistenteException;
import br.com.gft.api.exceptions.EspacoEventoNaoEncontradoException;
import br.com.gft.model.EspacoEvento;
import br.com.gft.repository.Casas;

@Service
public class CasasService {

	@Autowired
	private Casas casas;
	
	
		public List<EspacoEvento> listar(){
			return casas.findAll();
		}
		
		
		
		public Optional<EspacoEvento> buscar(Long id) {
			Optional<EspacoEvento> casaEvento =casas.findById(id);
			
			if(casaEvento.isEmpty()) {
				throw new EspacoEventoNaoEncontradoException("A casa não pode ser encontrada.");
				
				
			}
			return casaEvento;
		}
		
		public EspacoEvento salvar(EspacoEvento casaEvento) {
			if(casaEvento.getCodigo() != null) {
				Optional<EspacoEvento> a = casas.findById(casaEvento.getCodigo());
				
				 if(!a.isEmpty()) {
					 throw new EspacoEventoExistenteException("A casa ja existe");
				 }
			}
			
			return casas.save(casaEvento);
		}
		
		public void deletar(Long id) {
			try {
				casas.deleteById(id);
			}catch(EmptyResultDataAccessException e) {
				throw new EspacoEventoNaoEncontradoException("A casa não pode ser encontrada");
				
			}
		}
		
		public void atualizar(EspacoEvento casaEvento) {
			verificarExistencia(casaEvento);
			casas.save(casaEvento);
		}
		
		private void verificarExistencia(EspacoEvento casaEvento) {
			buscar(casaEvento.getCodigo());
		}
		
		public List<EspacoEvento> listarCrescente(){
			return casas.findAll(Sort.by(Sort.Direction.ASC,"nomeCasa"));
		}
		
		public List<EspacoEvento> listarDecrescente(){
			return casas.findAll(Sort.by(Sort.Direction.DESC,"nomeCasa"));
		}
		
		
		public EspacoEvento buscarNome(String nomeCasa) {
			EspacoEvento casaEvento =casas.findByNomeCasa(nomeCasa);
			
			if(casaEvento == null) {
				throw new EspacoEventoNaoEncontradoException("A casa não pode ser encontrada.");
				
				
			}
			return casaEvento;
		}
		
}
