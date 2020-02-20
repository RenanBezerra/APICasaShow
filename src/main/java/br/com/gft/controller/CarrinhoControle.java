package br.com.gft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.model.Evento;
import br.com.gft.model.ItensCompra;
import br.com.gft.repository.Eventos;

@Controller
public class CarrinhoControle {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	@Autowired
	private Eventos eventos;

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		mv.addObject("listaItens", itensCompra);
		return mv;
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public ModelAndView alterarQuantidade(@PathVariable Long id,@PathVariable Integer acao) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		
		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(id)) {
				if(acao.equals(1)) {
					
				
				it.setQuantidade(it.getQuantidade() + 1);
				}else if (acao==0){
					it.setQuantidade(it.getQuantidade()-1);
				
				}
				break;
			}
		}
		mv.addObject("listaItens", itensCompra);
		return mv;
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public ModelAndView adicionarCarrinho(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		Optional<Evento> prod = eventos.findById(id);
		Evento produto = prod.get();

		int controle = 0;
		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				controle = 1;
				break;
			}
		}

		if (controle == 0) {

			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setValorUnitario(produto.getValorIngresso());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotal(item.getValorUnitario());
			itensCompra.add(item);
		}
			mv.addObject("listaItens", itensCompra);
			return mv;

		
	}

}
