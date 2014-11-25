package ma.profique.api.jersey;

import ma.profique.api.DummyResource;
import ma.profique.api.UploadResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Tohle je naše Jersey aplikace.
 * Sem je potřeba přidávat další vznikající resources.
 *
 */
public class JerseyApplication extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // resources
        classes.add(DummyResource.class);
	    classes.add(UploadResource.class);
	    classes.add(GlobalExceptionMapper.class);

        // utils
        classes.add(JsonWriter.class);
        classes.add(JsonReader.class);

        return classes;
    }

}
