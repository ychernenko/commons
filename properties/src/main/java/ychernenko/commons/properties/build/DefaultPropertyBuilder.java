package ychernenko.commons.properties.build;

import ychernenko.commons.core.Defaults;
import ychernenko.commons.core.transform.parse.Parser;
import ychernenko.commons.core.transform.parse.ParserFactory;
import ychernenko.commons.properties.MissingPropertyException;
import ychernenko.commons.properties.ParserNotFoundException;
import ychernenko.commons.properties.PropertyParseException;
import ychernenko.commons.properties.description.DefaultPropertyDescriptor;
import ychernenko.commons.properties.description.PropertyDescriptor;
import ychernenko.commons.properties.source.PropertiesSource;

public class DefaultPropertyBuilder<T> implements PropertyBuilder<T> {

    private ParserFactory parserFactory = new ParserFactory();

    @Override
    public T build(DefaultPropertyDescriptor<T> descriptor, PropertiesSource source) {
        String key = descriptor.getKey();
        String string = source.getPropertyValue(key);
        if (string == null)
            string = descriptor.getDefaultValue();
        if (string == null && descriptor.getDefaultValueHolderClass() != null)
            return instantiate(descriptor.getDefaultValueHolderClass()).get();
        if (string != null) {
            Parser<T> parser = getParser(descriptor);
            if (parser == null) {
                throw new ParserNotFoundException(descriptor.toString());
            }
            try {
                return parser.parse(string);
            } catch (Exception e) {
                throw new PropertyParseException("Failed to parse '" + string + "' for property " + descriptor.toString(), e);
            }
        } else if (descriptor.isRequired()) {
            throw new MissingPropertyException(key);
        } else if (descriptor.getType().isPrimitive()) {
            return Defaults.defaultValue(descriptor.getType());
        }
        return null;
    }

    private Parser<T> getParser(PropertyDescriptor<T> descriptor) {
        Class<Parser<T>> clazz = descriptor.getParserClass();
        return clazz == null
                ? parserFactory.create(descriptor.getType())
                : instantiate(clazz);
    }

    private static <T> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
