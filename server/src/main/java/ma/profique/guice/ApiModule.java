package ma.profique.guice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import ma.profique.api.jersey.JerseyApplication;
import ma.profique.api.jersey.JerseyAuthFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servisní guice modul.
 *
 */
public class ApiModule extends ServletModule {

    @Override
    protected void configureServlets() {

		bind(Gson.class).toInstance(configureGson());

        final Map<String, String> params = new HashMap<String, String>();
        params.put("javax.ws.rs.Application", JerseyApplication.class.getName());
	    params.put("com.sun.jersey.config.feature.DisableWADL", "true");
        params.put("com.sun.jersey.spi.container.ContainerRequestFilters", JerseyAuthFilter.class.getName());

	    serve("/api/*").with(GuiceContainer.class, params);
    }

	private Gson configureGson() {
		GsonBuilder result = new GsonBuilder();

		// FIXME: tohle mi vubec nefunguje, potrebuju to pro /api/applicants/_validation

		// result = result.registerTypeAdapter(Class.class, new ClassAdapter());
		return result.create();
	}

	private class ClassAdapter extends TypeAdapter<Class> {

		@Override
		public void write(JsonWriter out, Class value) throws IOException {
			if (value == null) {
				out.nullValue();
				return;
			}
			out.value(value.getName());
		}

		@Override
		public Class read(JsonReader in) throws IOException {
			throw new UnsupportedOperationException("We should never need to read Class from JSON object");
		}
	}
}
