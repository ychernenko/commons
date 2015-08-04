package ychernenko.commons.properties.build;

import ychernenko.commons.properties.description.PropertyDescriptor;

public interface PropertyBuilderFactory {

	<T> PropertyBuilder<T> create(PropertyDescriptor<T> descriptor);
}
