package ychernenko.commons.core.transform.parse;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapParser implements Parser<Map<String, String>> {

	public static final String MAP_ENTRY_SEPARATOR = ",";
	public static final String MAP_KEY_VALUE_SEPARATOR = ":";

	@Override
	public Map<String, String> parse(String value) {
		try {
			Map<String, String> map = new LinkedHashMap<>();
			for (String entry : value.split(MAP_ENTRY_SEPARATOR)) {
				String[] splittedEntry = entry.split(MAP_KEY_VALUE_SEPARATOR);
				map.put(splittedEntry[0], splittedEntry[1]);
			}
			return map;
		} catch (Exception e) {
			throw new ParseException(e);
		}
	}

}
