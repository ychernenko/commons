package ychernenko.commons.properties.invoke;

import java.lang.reflect.InvocationHandler;

import ychernenko.commons.properties.source.PropertiesSource;

public interface InvocationHandlerFactory {

	InvocationHandler create(Class config, PropertiesSource source);
}
