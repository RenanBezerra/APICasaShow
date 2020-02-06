package br.com.gft.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.model.EspacoEvento;
import br.com.gft.model.Show;
import br.com.gft.model.StatusUsuario;
import br.com.gft.model.Usuarios;
import br.com.gft.repository.Casas;
import br.com.gft.repository.Eventos;
import br.com.gft.repository.ListaUsuarios;

@Controller
public class ShowController {

	@Autowired
	private Eventos eventos;
	
	@Autowired
	private Casas casas;
	
	@Autowired
	private ListaUsuarios lista;
	
	
	
	@RequestMapping("/home")
	public String Home() {
		return"Home";
		
	}
	
	@RequestMapping("/espacoeventos")
	public ModelAndView EspacoEventos() {
		ModelAndView mv = new ModelAndView("CadastroEspacoEventos");
		
		return mv;
		
	}
	
	
	
	
	
	@RequestMapping(value ="/espacoeventos",method = RequestMethod.POST)
	public ModelAndView salvar(EspacoEvento espacoevento) {
		// Todo: salvar no banco de dados
		
		casas.save(espacoevento);
		ModelAndView mv = new ModelAndView("CadastroEspacoEventos");
		mv.addObject("mensagem", "casa cadastrada com sucesso!!!!!");
		return mv;
	}

	
	@RequestMapping("/shows")
	public ModelAndView Shows() {
		ModelAndView mv = new ModelAndView("CadastroShow");
		
		return mv;
	}
	
	@RequestMapping(value ="/shows",method = RequestMethod.POST)
	public ModelAndView salvar(Show show) {
		// Todo: salvar no banco de dados
		
		eventos.save(show);
		ModelAndView mv = new ModelAndView("CadastroShow");
		mv.addObject("mensagem", "Show cadastrado com sucesso!!!!!");
		return mv;
	}
	
	@RequestMapping("/listashows")
	public String ListaShows() {
		return"Shows";
	}
	
	
	@RequestMapping("/cadastro")
	public ModelAndView CadastroUsuario() {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("todosStatusUsuario", StatusUsuario.values());
		return mv;
	}
	
	@RequestMapping(value ="/cadastro",method = RequestMethod.POST)
	public ModelAndView salvar(Usuarios usuario) {
		// Todo: salvar no banco de dados
		lista.save(usuario);
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject("mensagem", "Cadastro salvo com sucesso!!!!!");
		mv.addObject("todosStatusUsuario", StatusUsuario.values());
		return mv;
	}
	
	
	
	
	@RequestMapping("/login")
	public String Login() {
		return"Login";
	}
	@RequestMapping("/historico")
	public String Historico() {
		return"HistoricoCliente";
	}
	

}
