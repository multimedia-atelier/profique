package ma.profique.util;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 */
public abstract class ClientException extends IllegalArgumentException {

	public ClientException() {
	}

	public ClientException(String s) {
		super(s);
	}

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(Throwable cause) {
		super(cause);
	}
}
