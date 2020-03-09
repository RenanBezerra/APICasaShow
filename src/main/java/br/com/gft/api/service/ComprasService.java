package br.com.gft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.api.exceptions.CompraNaoEncontradoException;
import br.com.gft.model.Compra;
import br.com.gft.repository.CompraRepositorio;

@Service
public class ComprasService {



		@Autowired
		private CompraRepositorio compraRepository;
		
		public List<Compra> listar(){
			return compraRepository.findAll();
		}
		
		public Optional<Compra> buscar(Long id){
			Optional<Compra> compra = compraRepository.findById(id);
			
			if(compra.isEmpty()) {
				throw new CompraNaoEncontradoException("A compra não pode ser encontrada.");
			}
			return compra;
		
		
	}
		
		public Compra salvar(Compra compra) {
			if(compra.getId() != null) {
				Optional<Compra> a = compraRepository.findById(compra.getId());
				
				if(!a.isEmpty()) {
					throw new CompraNaoEncontradoException("A compra não existe");
				}
			}
			
			return compraRepository.save(compra);
		}
}
