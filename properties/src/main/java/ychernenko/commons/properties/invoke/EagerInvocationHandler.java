package ychernenko.commons.properties.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class EagerInvocationHandler implements InvocationHandler {

    private final Map<Method, PropertyEntry> values;

    public EagerInvocationHandler(Map<Method, PropertyEntry> values) {
        this.values = values;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PropertyEntry entry = values.get(method);
        return entry == null ? null : entry.getValue();
    }
}
