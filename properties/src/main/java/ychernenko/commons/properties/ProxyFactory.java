package ychernenko.commons.properties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> clazz, InvocationHandler handler) {
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces(clazz), handler);
	}

	private static Class[] interfaces(Class clazz) {
		return new Class[]{clazz};
	}
}
