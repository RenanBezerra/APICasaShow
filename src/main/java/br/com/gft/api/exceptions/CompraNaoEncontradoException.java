package br.com.gft.api.exceptions;

public class CompraNaoEncontradoException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompraNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public CompraNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
}
