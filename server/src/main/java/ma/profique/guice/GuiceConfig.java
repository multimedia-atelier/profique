package ma.profique.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Konfigurace Guice - dva moduly: servis a api.
 */
public class GuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ServiceModule(),
		        new WebModule(),
                new ApiModule()
        );
    }

}
