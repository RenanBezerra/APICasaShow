package br.com.gft.model;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotEmpty(message ="Nome do Evento é obrigatorio")
	private String nomeEvento;
	

	@NotEmpty(message ="Descrição é obrigatorio")
	private String descricao;
	
	
	@NotNull
	private int ingresso;
	
	
	@NotNull(message="Data é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotEmpty(message ="Horario é obrigatorio")
	private String horario;

	
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorIngresso;
	
	@ManyToOne
	@JoinColumn(name="casa_codigo")
	private EspacoEvento casa;
	
	
	private String casaEvento;
	
	

	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIngresso() {
		return ingresso;
	}
	public void setIngresso(int ingresso) {
		this.ingresso = ingresso;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public BigDecimal getValorIngresso() {
		return valorIngresso;
	}
	public void setValorIngresso(BigDecimal valorIngresso) {
		this.valorIngresso = valorIngresso;
	}
	public EspacoEvento getCasa() {
		return casa;
	}
	public void setCasa(EspacoEvento casa) {
		this.casa = casa;
	}
	public String getCasaEvento() {
		return casaEvento;
	}
	public void setCasaEvento(String casaEvento) {
		this.casaEvento = casaEvento;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
