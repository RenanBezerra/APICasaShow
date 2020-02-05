package br.com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.model.Usuarios;

public interface ListaUsuarios extends JpaRepository<Usuarios, Long>{

}
