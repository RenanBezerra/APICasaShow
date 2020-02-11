package br.com.gft.controller;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.model.EspacoEvento;
import br.com.gft.model.Evento;
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
		mv.addObject(new EspacoEvento());
		return mv;
		
	}
	
	
	@RequestMapping(value ="/espacoeventos",method = RequestMethod.POST)
	public ModelAndView salvar(@Validated EspacoEvento casaEvento,Errors errors) {
		// Todo: salvar no banco de dados
		ModelAndView mv = new ModelAndView("CadastroEspacoEventos");
		if(errors.hasErrors()) {
			return mv;
		}
		casas.save(casaEvento);
		mv.addObject("mensagem", "casa cadastrada com sucesso!!!!!");
		return mv;
	}

	@ModelAttribute("todosEspacoEvento") 
	public List<EspacoEvento> todosEspacoEvento(){
			return casas.findAll();
	}
	@RequestMapping("/listacasas")
	public ModelAndView PesquisaCasas() {
		List<EspacoEvento> todasCasas = casas.findAll(); 
		ModelAndView mv = new ModelAndView("PesquisaCasas");
		mv.addObject("casas", todasCasas);
		return mv;
		
	}
	@RequestMapping("/evento")
	public ModelAndView Eventos() {
		ModelAndView mv = new ModelAndView("CadastroEvento");
		mv.addObject(new Evento());
		return mv;
	}
	
	@RequestMapping(value ="/evento",method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Evento evento, Errors errors) {
		// Todo: salvar no banco de dados
		ModelAndView mv = new ModelAndView("CadastroEvento");
		if(errors.hasErrors()) {
			return mv;
		}
		eventos.save(evento);
		mv.addObject("mensagem", "Show cadastrado com sucesso!!!!!");
		return mv;
	}
	
	@RequestMapping("/listashows")
	public ModelAndView ListaShows() {
		
//		List<EspacoEvento> todasCasas = casas.findAll(); 
//		ModelAndView mv = new ModelAndView("PesquisaCasas");
//		mv.addObject("casas", todasCasas);
//		return mv;
		List<Evento> todosEventos = eventos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaShows");
		mv.addObject("eventos", todosEventos);
		return mv;
	}
	
	@RequestMapping("/cadastro")
	public ModelAndView CadastroUsuario() {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		mv.addObject(new Usuarios());
		return mv;
	}
	
	@RequestMapping(value ="/cadastro",method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Usuarios usuarios, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroUsuario");
		// Todo: salvar no banco de dados
		if(errors.hasErrors()) {
			return mv;
		}
		lista.save(usuarios);
		mv.addObject("mensagem", "Cadastro salvo com sucesso!!!!!");
		return mv;
	}
	
	
	@ModelAttribute("todosStatusUsuario")
	public List<StatusUsuario> todosStatusUsuario(){
			return Arrays.asList(StatusUsuario.values());
	}
	
	
	@RequestMapping("/pesquisausuario")
	public ModelAndView PesquisaUsuario() {
		List<Usuarios> todosUsuarios = lista.findAll();
		ModelAndView mv = new ModelAndView("PesquisaUsuario");
		mv.addObject("lista", todosUsuarios);
		
		
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
