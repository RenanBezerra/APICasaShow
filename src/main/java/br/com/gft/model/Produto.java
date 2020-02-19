package br.com.gft.model;

public class Produto {
	
	private Evento produto;
	private int qtd_produto;
	
	
	
	
	public Produto(Evento produto,int qtd) {
		this.produto = produto;
		this.qtd_produto=qtd;
	}
	
	
	
	public Evento getProduto() {
		return produto;
	}
	public void setProduto(Evento produto) {
		this.produto = produto;
	}
	public int getQtd_produto() {
		return qtd_produto;
	}
	public void setQtd_produto(int qtd_produto) {
		this.qtd_produto = qtd_produto;
	}
	
	

}
