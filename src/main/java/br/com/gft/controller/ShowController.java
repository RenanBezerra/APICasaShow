package br.com.gft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gft.model.EspacoEvento;
import br.com.gft.model.Show;
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
	public String EspacoEventos() {
		return"CadastroEspacoEventos";
		
	}
	@RequestMapping(value ="/espacoeventos",method = RequestMethod.POST)
	public String salvar(EspacoEvento espacoevento) {
		// Todo: salvar no banco de dados
		
		casas.save(espacoevento);
		return "CadastroEspacoEventos";
	}
	
	
	@RequestMapping("/shows")
	public String Shows() {
		return"CadastroShow";
	}
	
	@RequestMapping(value ="/shows",method = RequestMethod.POST)
	public String salvar(Show show) {
		// Todo: salvar no banco de dados
		
		eventos.save(show);
		return "CadastroShow";
	}
	
	@RequestMapping("/listashows")
	public String ListaShows() {
		return"Shows";
	}
	
	
	@RequestMapping("/cadastro")
	public String CadastroUsuario() {
		return"CadastroUsuario";
	}
	
	@RequestMapping(value ="/cadastro",method = RequestMethod.POST)
	public String salvar(Usuarios usuario) {
		// Todo: salvar no banco de dados
		
		lista.save(usuario);
		return "CadastroShow";
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
