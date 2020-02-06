package br.com.gft.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.OneToMany;



@Entity
public class EspacoEvento {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	
	private String nome;
	

	private String local;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="casa")
	private List<Show> shows;

	
	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}

	
	
	public List<Show> getShows() {
		return shows;
	}


	public void setShows(List<Show> shows) {
		this.shows = shows;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspacoEvento other = (EspacoEvento) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
}
