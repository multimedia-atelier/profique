package ma.profique.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.googlecode.objectify.Objectify;
import ma.profique.service.DummyEntityService;
import ma.profique.service.Service;
import ma.profique.service.impl.DummyEntityServiceImpl;
import ma.profique.service.impl.OfyProvider;
import ma.profique.util.DontValidate;
import ma.profique.util.LogCalls;
import org.aopalliance.intercept.MethodInterceptor;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;

/**
 * Servisní guice modul.
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DummyEntityService.class).to(DummyEntityServiceImpl.class);
        bind(Objectify.class).toProvider(OfyProvider.class);

	    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	    bind(javax.validation.Validator.class).toInstance(validator);

        // ... a tady mocná AOP kouzla!
        MethodInterceptor logger = new CallLoggerInterceptor();
        requestInjection(logger);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogCalls.class), logger);
        bindInterceptor(Matchers.annotatedWith(LogCalls.class), Matchers.any(), new MethodInterceptor[]{logger});

	    ValidatorInterceptor validatorInterceptor = new ValidatorInterceptor();
	    requestInjection(validatorInterceptor);
	    bindInterceptor(Matchers.subclassesOf(Service.class), new ValidateMatcher(), new MethodInterceptor[]{validatorInterceptor});

    }

	private class ValidateMatcher implements Matcher<Method> {

		@Override
		public boolean matches(Method method) {
			return !method.isSynthetic() && method.getAnnotation(DontValidate.class) == null;
		}

		@Override
		public Matcher<Method> and(Matcher<? super Method> other) {
			throw new IllegalStateException("Not implemented");
		}

		@Override
		public Matcher<Method> or(Matcher<? super Method> other) {
			throw new IllegalStateException("Not implemented");
		}
	}

}
