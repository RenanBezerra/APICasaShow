package br.com.gft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.model.EspacoEvento;

public interface Casas extends JpaRepository<EspacoEvento,Long> {
	Optional<EspacoEvento> findByNomeCasa(String nomeCasa);
	
}
