package ma.profique.api.jersey;

import com.google.appengine.api.search.MatchScorer;
import com.google.appengine.api.utils.SystemProperty;

import javax.ws.rs.core.Response;

public class ResultFactory {

	private static final boolean FORCE_CORS = true;

	public static Response resultOkWithCrossOrigin(Object result) {
		return decorate(Response.ok(result)).build();
	}

	public static Response resultClientErrorWithCrossOrigin(Object result) {
		return decorate(Response.status(422).entity(result)).build();
	}

	public static Response resultServerErrorWithCrossOrigin(Object result) {
		return decorate(Response.status(500).entity(result)).build();
	}

	private static Response.ResponseBuilder decorate(Response.ResponseBuilder builder) {
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production && !FORCE_CORS) {
			return builder;
		} else {
			return builder.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "X-Filename, Origin, X-Requested-With, Content-Type, Accept");
		}
	}

}