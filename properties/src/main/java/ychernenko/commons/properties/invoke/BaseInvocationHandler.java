package ychernenko.commons.properties.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BaseInvocationHandler implements InvocationHandler {

	private final Object objectMethodsDelegate;
	private final InvocationHandler propertiesDelegate;

	public BaseInvocationHandler(Object objectMethodsDelegate, InvocationHandler propertiesDelegate) {
		this.objectMethodsDelegate = objectMethodsDelegate;
		this.propertiesDelegate = propertiesDelegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getDeclaringClass().equals(Object.class)) {
			return method.invoke(objectMethodsDelegate, args);
		} else {
			return propertiesDelegate.invoke(proxy, method, args);
		}
	}
}
