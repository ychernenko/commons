package ychernenko.commons.properties.build;

import ychernenko.commons.properties.description.PropertyDescriptor;

public class DefaultPropertyBuilderFactory implements PropertyBuilderFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> PropertyBuilder<T> create(PropertyDescriptor<T> descriptor) {
        Class<PropertyBuilder<T>> clazz = descriptor.getBuilderClass();
        return clazz == null
                ? (PropertyBuilder<T>) new DefaultPropertyBuilder<>()
                : instantiate(clazz);
    }

    private static <T> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
