package br.com.ricardo.tron.service.exception;

public class LoginUsuarioJaCadastradoException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public LoginUsuarioJaCadastradoException(String message) {
		super(message);
	}

}
