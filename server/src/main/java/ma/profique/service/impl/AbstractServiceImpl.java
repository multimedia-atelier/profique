package ma.profique.service.impl;

import com.googlecode.objectify.Objectify;
import ma.profique.util.CallContext;
import ma.profique.util.DontValidate;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Bázová třída pro implementace servis, zajišťuje, že nám do každé
 * servisy Guice nainjektuje potřebné poskytovatele.
 *
 */
public class AbstractServiceImpl {

	private Provider<Objectify> ofyProvider;

	private Provider<CallContext> callContextProvider;

	@Inject
	@DontValidate
	public void setOfyProvider(OfyProvider ofyProvider) {
		this.ofyProvider = ofyProvider;
	}

	@Inject
	@DontValidate
	public void setCallContextProvider(Provider<CallContext> callContextProvider) {
		this.callContextProvider = callContextProvider;
	}

	@DontValidate
	protected Objectify ofy() {
		return ofyProvider.get();
	}

	@DontValidate
	protected CallContext callContext() {
		return callContextProvider.get();
	}

	@Override
	@DontValidate
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	@DontValidate
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	@DontValidate
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
