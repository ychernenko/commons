package ychernenko.commons.properties.description;

import java.lang.reflect.Method;

public interface PropertyDescriptorFactory {

    <T> DefaultPropertyDescriptor<T> create(Method method);
}
