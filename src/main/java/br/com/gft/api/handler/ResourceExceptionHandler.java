package br.com.gft.api.handler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.gft.api.exceptions.EspacoEventoExistenteException;
import br.com.gft.api.exceptions.EspacoEventoNaoEncontradoException;
import br.com.gft.api.exceptions.EventoExistenteException;
import br.com.gft.api.exceptions.EventoNaoEncontradoException;
import br.com.gft.api.exceptions.UsuarioExistenteException;
import br.com.gft.api.exceptions.UsuarioNaoEncontradoException;
import br.com.gft.model.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleUsuarioNaoEncontradoException
					(UsuarioNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O usuario não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		
		
		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(EspacoEventoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleEspacoEventoNaoEncontradoException
					(EspacoEventoNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A casa não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		
		
		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(EventoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleEventoNaoEncontradoException
					(EventoNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O evento não pode ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		
		
		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(EspacoEventoExistenteException.class)
	public ResponseEntity<DetalhesErro> handleEspacoEventoExistenteException
					(EspacoEventoExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("A casa ja existe");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
			return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<DetalhesErro> handleUsuarioExistenteException
					(UsuarioExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("O usuario ja existe");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
			return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	@ExceptionHandler(EventoExistenteException.class)
	public ResponseEntity<DetalhesErro> handleEventoExistenteException
					(EventoExistenteException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("O evento ja existe");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
			return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
								(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição invalida");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		 
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DetalhesErro> handleConstraintViolationException
								(ConstraintViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição invalida, prencha todos os atributos para salvar");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		 
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DetalhesErro> handleMethodArgumentTypeMismatchException
								(MethodArgumentTypeMismatchException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(450l);
		erro.setTitulo("Requisição invalida, rota inexistente verifique o nome da rota");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		 
	}
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<DetalhesErro> handleHttpRequestMethodNotSupportedException
								(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(405l);
		erro.setTitulo("Requisição invalida, para salvar verifique a rota correta");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		 
	}
	
	
	
}
