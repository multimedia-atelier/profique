package ma.profique.api;

import ma.profique.domain.DummyEntity;
import ma.profique.service.DummyEntityService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/dummies")
public class DummyResource extends BaseRestResource<DummyEntity> {

	@Inject
	public DummyResource(DummyEntityService service) {
		super(service, DummyEntity.class);
	}

}