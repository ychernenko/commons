package ychernenko.commons.properties.invoke;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import ychernenko.commons.core.Joiner;

public class ObjectMethodsDelegate {

	private final Map<Method, PropertyEntry> values;

	public ObjectMethodsDelegate(Map<Method, PropertyEntry> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		String joined =  new Joiner("; ").join(new Enumeration<String>() {

			private final Iterator<PropertyEntry> entryIterator = values.values().iterator();

			@Override
			public boolean hasMoreElements() {
				return entryIterator.hasNext();
			}

			@Override
			public String nextElement() {
				PropertyEntry entry = entryIterator.next();
				return entry.getDescriptor().getKey() + "=" + entry.getValue();
			}
		});
		return "{" + joined + "}";
	}
}
