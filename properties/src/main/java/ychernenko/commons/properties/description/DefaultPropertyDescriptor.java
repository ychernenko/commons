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
    private final Class<PropertyBuilder<T>> builderClass;
    private String defaultValue;

    public DefaultPropertyDescriptor(String key, Class<T> type, boolean required, String defaultValue, Class<Getter<T>> defaultValueHolderClass, Class<Parser<T>> parserClass, Class<PropertyBuilder<T>> builderClass) {
        this.key = key;
        this.type = type;
        this.required = required;
        this.defaultValue = defaultValue;
        this.defaultValueHolderClass = defaultValueHolderClass;
        this.parserClass = parserClass;
        this.builderClass = builderClass;
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
    public Class<PropertyBuilder<T>> getBuilderClass() {
        return builderClass;
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
                ", builderClass=" + builderClass +
                '}';
    }
}
