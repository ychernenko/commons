package ychernenko.commons.properties.source;

import java.util.Collection;

public interface PropertiesSource {

	Collection<String> getPropertyNames();
	String getPropertyValue(String name);
}
