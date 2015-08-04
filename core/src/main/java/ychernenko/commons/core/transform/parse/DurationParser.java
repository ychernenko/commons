package ychernenko.commons.core.transform.parse;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

public class DurationParser implements Parser<Duration> {

	private static final DatatypeFactory FACTORY;
	static {
		try {
			FACTORY = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public Duration parse(String value) {
		return FACTORY.newDuration(value);
	}

	public static String asString(long millis) {
		return FACTORY.newDuration(millis).toString();
	}
}
