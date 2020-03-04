package br.com.gft.api.exceptions;

public class EventoNaoEncontradoException extends RuntimeException{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventoNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public EventoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
