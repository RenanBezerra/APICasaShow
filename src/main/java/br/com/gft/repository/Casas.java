package br.com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.model.EspacoEvento;

public interface Casas extends JpaRepository<EspacoEvento,Long> {

	
}
