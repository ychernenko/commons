package ychernenko.commons.properties.invoke;

import ychernenko.commons.properties.description.PropertyDescriptor;

public class PropertyEntry {

	private final PropertyDescriptor descriptor;
	private final Object value;

	public PropertyEntry(PropertyDescriptor descriptor, Object value) {
		this.descriptor = descriptor;
		this.value = value;
	}

	public PropertyDescriptor getDescriptor() {
		return descriptor;
	}

	public Object getValue() {
		return value;
	}
}
