package ychernenko.commons.properties.description;

import ychernenko.commons.core.Getter;
import ychernenko.commons.core.transform.parse.Parser;
import ychernenko.commons.properties.build.PropertyBuilder;

public interface PropertyDescriptor<T> {

    String getKey();

    boolean isRequired();

    String getDefaultValue();

    Class<Parser<T>> getParserClass();

    Class<PropertyBuilder<T>> getLoaderClass();

    Class<T> getType();

    Class<Getter<T>> getDefaultValueHolderClass();
}
