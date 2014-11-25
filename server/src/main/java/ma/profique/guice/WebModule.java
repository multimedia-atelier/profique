package ma.profique.guice;

import com.google.inject.servlet.ServletModule;

/**
 * Servisní guice modul.
 *
 */
public class WebModule extends ServletModule {
    @Override
    protected void configureServlets() {
        // serve("/admin/*").with(Frontend.class);
    }
}
