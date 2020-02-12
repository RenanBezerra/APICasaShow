package br.com.gft.controller;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.model.EspacoEvento;
import br.com.gft.model.Evento;
import br.com.gft.model.StatusUsuario;
import br.com.gft.model.Usuarios;
import br.com.gft.repository.Casas;
import br.com.gft.repository.Eventos;
import br.com.gft.repository.ListaUsuarios;


@Controller
public class ShowController {

	private static final String CADASTRO_CASA = "CadastroEspacoEventos";
	private static final String CADASTRO_USUARIO = "CadastroUsuario";
	private static final String CADASTRO_EVENTO = "CadastroEvento";
	
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
		ModelAndView mv = new ModelAndView(CADASTRO_CASA);
		mv.addObject(new EspacoEvento());
		return mv;
		
	}
	
	
	@RequestMapping(value ="/espacoeventos",method = RequestMethod.POST)
	public String salvar(@Validated EspacoEvento casaEvento,Errors errors,RedirectAttributes attributes) {
		// Todo: salvar no banco de dados
	
		if(errors.hasErrors()) {
			return CADASTRO_CASA;
		}
		casas.save(casaEvento);
		attributes.addFlashAttribute("mensagem", "casa cadastrada com sucesso!!!!!");
		return "redirect:/espacoeventos";
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
	
	@RequestMapping("/editarCasa/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView(CADASTRO_CASA);
		Optional<EspacoEvento> casaEvento = casas.findById(codigo);
		mv.addObject(casaEvento.get());
		return mv;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping("/evento")
	public ModelAndView Eventos() {
		ModelAndView mv = new ModelAndView(CADASTRO_EVENTO);
		mv.addObject(new Evento());
		return mv;
	}
	
	@RequestMapping(value ="/evento",method = RequestMethod.POST)
	public String salvar(@Validated Evento evento, Errors errors, RedirectAttributes attributes) {
		// Todo: salvar no banco de dados	
		if(errors.hasErrors()) {
			return CADASTRO_EVENTO;
		}
		eventos.save(evento);
		attributes.addFlashAttribute("mensagem", "Show cadastrado com sucesso!!!!!");
		return "redirect:/evento";
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
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	@RequestMapping("/cadastro")
	public ModelAndView CadastroUsuario() {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO);
		mv.addObject(new Usuarios());
		return mv;
	}
	
	@RequestMapping(value ="/cadastro",method = RequestMethod.POST)
	public String salvar(@Validated Usuarios usuarios, Errors errors, RedirectAttributes attributes) {
		
		// Todo: salvar no banco de dados
		if(errors.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		lista.save(usuarios);
		attributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso!!!!!");
		
		return "redirect:/cadastro";
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
	
	@RequestMapping("/editarUsuario/{codigo}")
	public ModelAndView edicaoUsuario(@PathVariable("codigo") Long id) {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO);
		Optional<Usuarios> usuarios = lista.findById(id);
		mv.addObject(usuarios.get());
		return mv;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping("/login")
	public String Login() {
		return"Login";
	}
	@RequestMapping("/historico")
	public String Historico() {
		return"HistoricoCliente";
	}
	
	
}
