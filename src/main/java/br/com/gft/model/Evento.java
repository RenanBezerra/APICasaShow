package br.com.gft.model;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example="Show Maravilha")
	@NotEmpty(message ="Nome do Evento é obrigatorio")
	private String nomeEvento;
	

	@ApiModelProperty(example="Gravação DVD")
	@NotEmpty(message ="Descrição é obrigatorio")
	@JsonInclude(Include.NON_NULL)
	private String descricao;
	
	
	@ApiModelProperty(example="900")
	@NotNull(message="Ingresso é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private int ingresso;
	
	@ApiModelProperty(example="01/01/2020")
	@NotNull(message="Data é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@JsonInclude(Include.NON_NULL)
	private Date data;
	
	@ApiModelProperty(example="21:00")
	@NotEmpty(message ="Horario é obrigatorio")
	@JsonInclude(Include.NON_NULL)
	private String horario;

	
	@ApiModelProperty(example="87.00")
	@NotNull
	@DecimalMin(value = "0.01", message = "Valornão pode ser menro que 0,01")
	@DecimalMax(value = "9999999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	@JsonInclude(Include.NON_NULL)
	private Double valorIngresso;
	
	@ApiModelProperty(example="Casas cadastradas")
	@ManyToOne
	@JoinColumn(name="casa_codigo",nullable=false)
	@JsonInclude(Include.NON_NULL)
	private EspacoEvento casa;
	
	@ApiModelProperty(example="Genero cadastrados")
	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	private StatusGenero genero;
	
	

	
	
	
	
	
	
	
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
	
	public Double getValorIngresso() {
		return valorIngresso;
	}
	public void setValorIngresso(Double valorIngresso) {
		this.valorIngresso = valorIngresso;
	}
	public EspacoEvento getCasa() {
		return casa;
	}
	public void setCasa(EspacoEvento casa) {
		this.casa = casa;
	}
	
	
	
	
	public StatusGenero getGenero() {
		return genero;
	}
	public void setGenero(StatusGenero genero) {
		this.genero = genero;
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
