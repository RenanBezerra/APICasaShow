package br.com.gft.model;

public enum StatusGenero {
	ROCK("Rock"),
	POP("Pop"),
	REGGAE("Reggae"),
	PAGODE("Pagode"),
	JAZZ("Jazz"),
	GOSPEL("Gospel"),
	SERTANEJO("Sertanejo");
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	StatusGenero(String tipo) {
		this.tipo = tipo;
	}
}
