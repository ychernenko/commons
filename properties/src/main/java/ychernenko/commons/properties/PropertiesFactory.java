package ychernenko.commons.properties;

import ychernenko.commons.properties.source.PropertiesSource;

public interface PropertiesFactory<T> {

    T create(PropertiesSource source);
}
