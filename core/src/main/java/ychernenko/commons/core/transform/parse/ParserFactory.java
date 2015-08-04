package ychernenko.commons.core.transform.parse;

import java.math.BigInteger;
import java.net.URL;
import java.util.*;
import javax.xml.datatype.Duration;

import ychernenko.commons.core.Primitives;

public class ParserFactory {

	private static final Map<Class, Parser> PARSERS = new HashMap<>();
	static {
		PARSERS.put(Integer.class, new DefaultParser<>(Integer.class));
		PARSERS.put(BigInteger.class, new DefaultParser<>(BigInteger.class));
		PARSERS.put(Long.class, new DefaultParser<>(Long.class));
		PARSERS.put(String.class, new DefaultParser<>(String.class));
		PARSERS.put(Boolean.class, new DefaultParser<>(Boolean.class));
		PARSERS.put(Date.class, new DateTimeParser());
		PARSERS.put(URL.class, new UrlParser());
		PARSERS.put(Duration.class, new DurationParser());
		PARSERS.put(List.class, new ListParser<>(new DefaultParser<>(String.class)));
		PARSERS.put(Collection.class, PARSERS.get(List.class));
		PARSERS.put(Map.class, new MapParser());
		PARSERS.put(Class.class, new ClassParser());
	}

	public <T> Parser<T> create(Class<T> clazz) {
		return get(clazz);
	}

	@SuppressWarnings("unchecked")
	private static <T> Parser<T> get(Class<T> clazz) {
		return PARSERS.get(clazz.isPrimitive()
								   ? Primitives.getWrapper(clazz)
								   : clazz);
	}

	public static Parser<Integer> getInt() {
		return get(Integer.class);
	}

	public static Parser<BigInteger> getBigInteger() {
		return get(BigInteger.class);
	}

	public static Parser<Long> getLong() {
		return get(Long.class);
	}

	public static Parser<String> getString() {
		return get(String.class);
	}

	public static Parser<Boolean> getBoolean() {
		return get(Boolean.class);
	}

	public static Parser<Date> getDate() {
		return get(Date.class);
	}

	public static Parser<URL> getURL() {
		return get(URL.class);
	}

	public static Parser<Duration> getDuration() {
		return get(Duration.class);
	}

	public static Parser<List> getList() {
		return get(List.class);
	}

	public static Parser<Map> getMap() {
		return get(Map.class);
	}
}
