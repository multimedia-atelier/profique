package ma.profique.api;

import com.google.inject.Inject;
import ma.profique.api.jersey.ResultFactory;
import ma.profique.service.Service;

import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class BaseRestResource<E> extends BaseResource {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	private Service<E> service;
	private Class<E> entityClass;

	@Inject
	private Validator validator;

	protected BaseRestResource(Service<E> service, Class<E> entityClass) {
		this.service = service;
		this.entityClass = entityClass;
	}

	@POST
	public Response post(E entity) {
		entity = service.create(entity);
		return ResultFactory.resultOkWithCrossOrigin(entity);
	}

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") String id) {
		if ("_validation".equals(id)) {
			return ResultFactory.resultOkWithCrossOrigin(validator.getConstraintsForClass(entityClass));
		}
		return Response.status(404).build();
	}

}