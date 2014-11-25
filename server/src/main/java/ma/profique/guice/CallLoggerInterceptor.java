package ma.profique.guice;

import ma.profique.util.CallContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class CallLoggerInterceptor implements MethodInterceptor {

    private Logger log = LoggerFactory.getLogger(CallLoggerInterceptor.class);

    @Inject
    private Provider<CallContext> callContextProvider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("User '"+callContextProvider.get().getUserName()+"' is calling "+invocation.getMethod().getName());
        return invocation.proceed();
    }

}
