package ychernenko.commons.core.transform.parse;

public class DefaultParser<T> implements Parser<T> {

    private final Class<T> clazz;

    public DefaultParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T parse(String value) {
        try {
            return clazz.getConstructor(new Class[]{String.class}).newInstance(value);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
