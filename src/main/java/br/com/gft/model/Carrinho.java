package br.com.gft.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private static List<Produto> listcarrinho = new ArrayList<Produto>();
	
	private static BigDecimal totalvalor;

	public static List<Produto> getListcarrinho() {
		return listcarrinho;
	}

	public static void setListcarrinho(List<Produto> listcarrinho) {
		Carrinho.listcarrinho = listcarrinho;
	}

	public static BigDecimal getTotalvalor() {
		return totalvalor;
	}

	public static void setTotalvalor(BigDecimal totalvalor) {
		Carrinho.totalvalor = totalvalor;
	}
	
	
}
