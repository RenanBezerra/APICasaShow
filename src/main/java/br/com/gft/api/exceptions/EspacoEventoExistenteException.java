package br.com.gft.api.exceptions;

public class EspacoEventoExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EspacoEventoExistenteException (String mensagem) {
		super(mensagem);
	}
	
	public EspacoEventoExistenteException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
}
