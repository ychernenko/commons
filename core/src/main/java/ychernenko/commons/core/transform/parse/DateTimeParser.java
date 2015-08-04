package ychernenko.commons.core.transform.parse;

import java.util.Date;
import javax.xml.bind.DatatypeConverter;

public class DateTimeParser implements Parser<Date> {

	@Override
	public Date parse(String value) {
		try {
			return DatatypeConverter.parseDateTime(value).getTime();
		} catch (Exception e) {
			throw new ParseException(e);
		}
	}
}
