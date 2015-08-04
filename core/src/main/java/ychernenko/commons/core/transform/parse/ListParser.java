package ychernenko.commons.core.transform.parse;

import java.util.ArrayList;
import java.util.List;

public class ListParser<T> implements Parser<List<T>> {

    public static final String DEFAULT_SEPARATOR = ",";

    private final Parser<T> itemParser;

    public ListParser(Parser<T> itemParser) {
        this.itemParser = itemParser;
    }

    @Override
    public List<T> parse(String value) {
        String[] strings = value.split(DEFAULT_SEPARATOR);
        List<T> items = new ArrayList<>(strings.length);
        for (String string : strings) {
            items.add(itemParser.parse(string));
        }
        return items;
    }

}
