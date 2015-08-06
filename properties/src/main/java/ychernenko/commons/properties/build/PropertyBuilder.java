package ychernenko.commons.properties.build;

import ychernenko.commons.properties.description.DefaultPropertyDescriptor;
import ychernenko.commons.properties.source.PropertiesSource;

public interface PropertyBuilder<T> {

    T build(DefaultPropertyDescriptor<T> descriptor, PropertiesSource source);
}
