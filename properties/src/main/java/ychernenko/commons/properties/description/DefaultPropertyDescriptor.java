package ychernenko.commons.properties.description;

import ychernenko.commons.core.Getter;
import ychernenko.commons.core.transform.parse.Parser;
import ychernenko.commons.properties.build.PropertyBuilder;

public class DefaultPropertyDescriptor<T> implements PropertyDescriptor<T> {

    private final String key;
    private final Class<T> type;
    private final boolean required;
    private final Class<Getter<T>> defaultValueHolderClass;
    private final Class<Parser<T>> parserClass;
    private final Class<PropertyBuilder<T>> loaderClass;
    private String defaultValue;

    public DefaultPropertyDescriptor(String key, Class<T> type, boolean required, String defaultValue, Class<Getter<T>> defaultValueHolderClass, Class<Parser<T>> parserClass, Class<PropertyBuilder<T>> loaderClass) {
        this.key = key;
        this.type = type;
        this.required = required;
        this.defaultValue = defaultValue;
        this.defaultValueHolderClass = defaultValueHolderClass;
        this.parserClass = parserClass;
        this.loaderClass = loaderClass;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Class<Parser<T>> getParserClass() {
        return parserClass;
    }

    @Override
    public Class<PropertyBuilder<T>> getLoaderClass() {
        return loaderClass;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public Class<Getter<T>> getDefaultValueHolderClass() {
        return defaultValueHolderClass;
    }

    @Override
    public String toString() {
        return "{" +
                "key='" + key + '\'' +
                ", type=" + type +
                ", required=" + required +
                ", defaultValue='" + defaultValue + '\'' +
                ", parserClass=" + parserClass +
                ", loaderClass=" + loaderClass +
                '}';
    }
}
