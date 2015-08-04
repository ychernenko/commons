package ychernenko.commons.core;

import java.util.Enumeration;

public class Joiner {

	private final String separator;

	public Joiner(String separator) {
		this.separator = separator;
	}

	public String join(Enumeration<?> elements) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		while (elements.hasMoreElements()) {
			if (first) {
				first = false;
			} else {
				builder.append(separator);
			}
			builder.append(elements.nextElement());
		}
		return builder.toString();
	}
}
