package ma.profique.service.impl;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import ma.profique.domain.DummyEntity;

import javax.inject.Provider;

/**
 * Naše implementace Ofy Providera, která nás odstíní od statické Objectify hnusoty.
 */
public class OfyProvider implements Provider<Objectify> {

    {
        ObjectifyService.register(DummyEntity.class);
    }

    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }
}
