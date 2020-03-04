package br.com.gft.api.exceptions;

public class EspacoEventoNaoEncontradoException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EspacoEventoNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public EspacoEventoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
}
