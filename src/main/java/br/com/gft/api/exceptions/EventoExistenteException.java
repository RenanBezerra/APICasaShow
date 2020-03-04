package br.com.gft.api.exceptions;

public class EventoExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventoExistenteException (String mensagem) {
		super(mensagem);
	}
	
	public EventoExistenteException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
