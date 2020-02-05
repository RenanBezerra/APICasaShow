package br.com.gft.model;

public enum StatusUsuario {
	MASCULINO,
	FEMININO;
	
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
