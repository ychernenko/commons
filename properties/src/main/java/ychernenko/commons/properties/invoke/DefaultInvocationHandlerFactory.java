package ychernenko.commons.properties.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ychernenko.commons.properties.build.DefaultPropertyBuilderFactory;
import ychernenko.commons.properties.build.PropertyBuilderFactory;
import ychernenko.commons.properties.description.DefaultPropertyDescriptor;
import ychernenko.commons.properties.description.DefaultPropertyDescriptorFactory;
import ychernenko.commons.properties.description.PropertyDescriptorFactory;
import ychernenko.commons.properties.source.PropertiesSource;

public class DefaultInvocationHandlerFactory implements InvocationHandlerFactory {

    private final PropertyBuilderFactory propertyBuilderFactory;
    private final PropertyDescriptorFactory descriptorFactory;

    public DefaultInvocationHandlerFactory() {
        this(new DefaultPropertyBuilderFactory(), new DefaultPropertyDescriptorFactory());
    }

    public DefaultInvocationHandlerFactory(PropertyBuilderFactory propertyBuilderFactory, PropertyDescriptorFactory descriptorFactory) {
        this.propertyBuilderFactory = propertyBuilderFactory;
        this.descriptorFactory = descriptorFactory;
    }

    @Override
    public InvocationHandler create(Class config, PropertiesSource source) {
        Map<Method, PropertyEntry> values = getValues(config, source);
        Object objectMethodsDelegate = new ObjectMethodsDelegate(values);
        InvocationHandler propertiesDelegate = new EagerInvocationHandler(values);
        return new BaseInvocationHandler(objectMethodsDelegate, propertiesDelegate);
    }

    private <T> Map<Method, PropertyEntry> getValues(Class clazz, PropertiesSource source) {
        Map<Method, PropertyEntry> values = new HashMap<>();
        while (clazz != null && !clazz.equals(Object.class)) {
            for (Method method : clazz.getMethods()) {
                values.put(method, getPropertyEntry(method, source));
            }
            clazz = clazz.getSuperclass();
        }
        return values;
    }

    private <T> PropertyEntry getPropertyEntry(Method method, PropertiesSource source) {
        DefaultPropertyDescriptor<T> descriptor = descriptorFactory.create(method);
        Object value = propertyBuilderFactory.create(descriptor).load(descriptor, source);
        return new PropertyEntry(descriptor, value);
    }
}
