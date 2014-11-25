package ma.profique.util;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 */
public class ValidationException extends ClientException {

	private Class entity;
	private Method method;
	private Set<ConstraintViolation<Object>> result;

	public ValidationException(Class entity, Method method, Set<ConstraintViolation<Object>> result) {
		this.entity = entity;
		this.method = method;
		this.result = result;
	}

	public Class getEntity() {
		return entity;
	}

	public Method getMethod() {
		return method;
	}

	public Set<ConstraintViolation<Object>> getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "ValidationException{" +
				"entity=" + entity +
				", method=" + method +
				", result=" + result +
				'}';
	}
}
