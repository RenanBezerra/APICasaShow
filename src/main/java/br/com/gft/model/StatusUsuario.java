package br.com.gft.model;

public enum StatusUsuario {
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	
	private String descricao;

	StatusUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
