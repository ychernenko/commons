package ychernenko.commons.properties;

import java.lang.reflect.InvocationHandler;

import ychernenko.commons.properties.invoke.DefaultInvocationHandlerFactory;
import ychernenko.commons.properties.invoke.InvocationHandlerFactory;
import ychernenko.commons.properties.source.PropertiesSource;

public class DefaultConfigurablePropertiesFactory implements ConfigurablePropertiesFactory {

	private final ProxyFactory proxyFactory;
	private final InvocationHandlerFactory invocationHandlerFactory;

	public DefaultConfigurablePropertiesFactory() {
		this(new ProxyFactory(), new DefaultInvocationHandlerFactory());
	}

	public DefaultConfigurablePropertiesFactory(ProxyFactory proxyFactory, InvocationHandlerFactory invocationHandlerFactory) {
		this.proxyFactory = proxyFactory;
		this.invocationHandlerFactory = invocationHandlerFactory;
	}


	@Override
	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> config, PropertiesSource source) {
		InvocationHandler handler = invocationHandlerFactory.create(config, source);
		return proxyFactory.create(config, handler);
	}

}
