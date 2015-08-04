package ychernenko.commons.core.transform.parse;

public interface Parser<T> {

    T parse(String value) throws ParseException;
}
