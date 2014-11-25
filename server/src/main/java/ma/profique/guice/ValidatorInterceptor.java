package ma.profique.guice;

import com.googlecode.objectify.annotation.Entity;
import ma.profique.util.DontValidate;
import ma.profique.util.ValidationException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 *
 */
@Singleton
public class ValidatorInterceptor implements MethodInterceptor {

    private Logger log = LoggerFactory.getLogger(ValidatorInterceptor.class);

	@Inject
	private javax.validation.Validator validator;

	@Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
	    Method m = invocation.getMethod();
	    if (m.getAnnotation(DontValidate.class) != null) return invocation.proceed();
        log.info("Running validator on "+m);
	    for (Object o : invocation.getArguments()) {
		    if (o.getClass().getAnnotation(Entity.class) != null) {
			    log.info("Running validator on parameter "+o);
			    Set<ConstraintViolation<Object>> result = validator.validate(o);
			    if (result != null && !result.isEmpty()) {
				    throw new ValidationException(o.getClass(), m, result);
			    }
		    }
		    
	    }
        return invocation.proceed();
    }

}
