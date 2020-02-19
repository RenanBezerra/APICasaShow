package br.com.gft.controller;



import java.util.ArrayList;
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

import br.com.gft.model.Carrinho;
import br.com.gft.model.EspacoEvento;
import br.com.gft.model.Evento;
import br.com.gft.model.Produto;
import br.com.gft.model.StatusGenero;
import br.com.gft.model.StatusUsuario;
import br.com.gft.model.Usuarios;
import br.com.gft.repository.Casas;
import br.com.gft.repository.Eventos;
import br.com.gft.repository.ListaUsuarios;
import br.com.gft.service.CadastroCasaService;
import br.com.gft.service.CadastroEventoService;


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
	
	@Autowired
	private CadastroEventoService cadastroEventoService;
	
	@Autowired
	private CadastroCasaService cadastroCasaService;
	
	
///////////////////////////////////////////////	
	
	@RequestMapping("/home")
	public String Home() {
		return"Home";
		
	}
	////////////////////////////////// CASAS
	@RequestMapping("/espacoeventos")
	public ModelAndView EspacoEventos() {
		ModelAndView mv = new ModelAndView(CADASTRO_CASA);
		mv.addObject(new EspacoEvento());
		return mv;
		
	}
	
	
	@RequestMapping(value ="/espacoeventos",method = RequestMethod.POST)////salvar casa
	public String salvar(@Validated EspacoEvento casaEvento,Errors errors,RedirectAttributes attributes) {
		// Todo: salvar no banco de dados
		if(errors.hasErrors()) {
			return CADASTRO_CASA;
		}
		cadastroCasaService.salvar(casaEvento);
		attributes.addFlashAttribute("mensagem", "casa cadastrada com sucesso!!!!!");
		return "redirect:/espacoeventos";
	}

	@ModelAttribute("todosEspacoEvento") ///lista da casa
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
	
	@RequestMapping("/editarCasa/{codigo}") //////editar casa
	public ModelAndView edicao(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView(CADASTRO_CASA);
		Optional<EspacoEvento> casaEvento = casas.findById(codigo);
		mv.addObject(casaEvento.get());
		return mv;
	}
	
	@RequestMapping(value="/listacasas/{codigo}", method = RequestMethod.POST) ////////exclui casa
	public String excluirCasa(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroCasaService.excluirCasa(codigo);
		
		attributes.addFlashAttribute("mensagem", "Casa excluida com sucesso!");
		return "redirect:/listacasas";
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////EVENTOS
	@RequestMapping("/evento")
	public ModelAndView Eventos() {
		ModelAndView mv = new ModelAndView(CADASTRO_EVENTO);
		mv.addObject(new Evento());
		return mv;
	}
	
	@RequestMapping(value ="/evento",method = RequestMethod.POST)//////////SALVA EVENTO
	public String salvar(@Validated Evento evento, Errors errors, RedirectAttributes attributes) {
		// Todo: salvar no banco de dados	
		if(errors.hasErrors()) {
			return CADASTRO_EVENTO;
		}
		try {
			cadastroEventoService.salvar(evento);
			attributes.addFlashAttribute("mensagem", "Show cadastrado com sucesso!!!!!");
			return "redirect:/evento";			
		} catch (IllegalArgumentException e) {
			errors.rejectValue("data",null, e.getMessage());
			return CADASTRO_EVENTO;
		}
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
	@RequestMapping("/editarEvento/{codigo}")////////////////EDITA EVENTO
	public ModelAndView edicaoEvento(@PathVariable("codigo") Long id) {
		ModelAndView mv = new ModelAndView(CADASTRO_EVENTO);
		Optional<Evento> evento = eventos.findById(id);
		mv.addObject(evento.get());
		return mv;
	}
	
	@ModelAttribute("todosGeneros") 
	public List<StatusGenero> todosGeneros(){
			return Arrays.asList(StatusGenero.values());
	}
	@ModelAttribute("todosEventosShows") 
	public List<Evento> todosEventosShows(){
			return eventos.findAll();
	}
	
	
	
	@RequestMapping(value="/listashows/{id}",method = RequestMethod.POST)//////////////ECLUI EVENTO
	public String excluirEvento(@PathVariable Long id,RedirectAttributes attributes) {
		cadastroEventoService.excluirEvento(id);
		attributes.addFlashAttribute("mensagem", "Evento excluido com sucesso!");
		return "redirect:/listashows";
	}
	
//	@RequestMapping(value="/listashows/{codigo}", method = RequestMethod.POST)
//	public String excluirEvento(@PathVariable Long id) {
//		eventos.deleteById(id);
//		
//		
//		return "redirect:/listashows";
//	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	USUARIO
	
	
	
	@RequestMapping("/cadastro")
	public ModelAndView CadastroUsuario() {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO);
		mv.addObject(new Usuarios());
		return mv;
	}
	
	@RequestMapping(value ="/cadastro",method = RequestMethod.POST)/////// SALVA USUARIO
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
	
	@RequestMapping("/editarUsuario/{codigo}")//////////// EDITA USUARIO
	public ModelAndView edicaoUsuario(@PathVariable("codigo") Long id) {
		ModelAndView mv = new ModelAndView(CADASTRO_USUARIO);
		Optional<Usuarios> usuarios = lista.findById(id);
		mv.addObject(usuarios.get());
		return mv;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CARRINHO
	
//	@ModelAttribute("todasCompras")
//	static List<TodasCompras> todasCompras(){
//			return Arrays.asList(TodasCompras.values());
//	}
	
//	@ModelAttribute("todosEventosShows") 
//	public List<Evento> todosEventosShows(){
//			return eventos.findAll();
//	}
	
//	@ModelAttribute("todasCompras")
//	public List<Evento> todasCompras(){
//		return Arrays.asList();
//	}
	
	
	
	
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("Carrinho");
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////SECURITY	
	@RequestMapping("/login")
	public String Login() {
		return"Login";
	}
	@RequestMapping("/historico")
	public String Historico() {
		return"HistoricoCliente";
	}
	
	
}
