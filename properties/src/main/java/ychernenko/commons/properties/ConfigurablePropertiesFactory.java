package ychernenko.commons.properties;

import ychernenko.commons.properties.source.PropertiesSource;

public interface ConfigurablePropertiesFactory {

    <T> T create(Class<T> config, PropertiesSource source);
}
