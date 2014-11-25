package ma.profique.api;

import ma.profique.api.jersey.ResultFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

public abstract class BaseResource {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	@OPTIONS
	public Response get() {
		log.info("Serving OPTIONS");
		return ResultFactory.resultOkWithCrossOrigin(null);
	}

}