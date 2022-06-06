package br.com.andrefilipeos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAutenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public InvalidJwtAutenticationException(String exception) {
		super(exception);
	}
	
}
