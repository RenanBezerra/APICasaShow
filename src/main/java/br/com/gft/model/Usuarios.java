package br.com.gft.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example="Maria")
	@NotNull
	@NotEmpty(message="Nome é obrigatório")
	private String primeiroNome;
	
	@ApiModelProperty(example="Bezerra")
	@NotEmpty(message="Sobre nome é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String sobreNome;
	
	@ApiModelProperty(example="maria@gmail.com")
	@NotEmpty(message="Email é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@ApiModelProperty(example="123.456.789-78")
	@NotEmpty(message="CPF é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String cpf;
	
	@ApiModelProperty(example="09/09/1990")
	@NotNull(message="Data de nascimento é obrigatório")
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@JsonInclude(Include.NON_NULL)
	private Date dataNascimento;
	
	@ApiModelProperty(example="MASCULINO OU FEMININO")
	@Enumerated(EnumType.STRING)
	@JsonInclude(Include.NON_NULL)
	private StatusUsuario sexo;
	
	@ApiModelProperty(example="Titan")
	@NotEmpty(message="Nome do Usuario é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String nomeUsuario;
	
	@ApiModelProperty(example="******")
	@NotEmpty(message="Senha é obrigatório")
	@JsonInclude(Include.NON_NULL)
	private String senha;
	
	
	
	


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public StatusUsuario getSexo() {
		return sexo;
	}

	public void setSexo(StatusUsuario sexo) {
		this.sexo = sexo;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		Usuarios other = (Usuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
