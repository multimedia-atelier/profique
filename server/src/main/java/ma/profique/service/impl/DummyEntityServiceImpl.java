package ma.profique.service.impl;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import ma.profique.domain.DummyEntity;
import ma.profique.service.DummyEntityService;
import ma.profique.util.LogCalls;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Implementace našeho uložení applicant. V podstatě jen zavoláme Objectify.
 */
public class DummyEntityServiceImpl extends AbstractServiceImpl implements DummyEntityService {

    private Logger log = Logger.getLogger(DummyEntityServiceImpl.class.getName());

	@Override
	@LogCalls
	public DummyEntity create(DummyEntity applicant) {
		ofy().save().entity(applicant).now();
		return applicant;
	}


}
