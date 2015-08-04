package ychernenko.commons.core.transform.parse;

public class ClassParser implements Parser<Class<?>> {

    @Override
    public Class<?> parse(String value) {
        try {
            return Class.forName(value);
        } catch (ClassNotFoundException e) {
            throw new ParseException(e);
        }
    }
}
