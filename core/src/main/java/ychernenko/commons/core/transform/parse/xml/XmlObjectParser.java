package ychernenko.commons.core.transform.parse.xml;

import static ychernenko.commons.core.Exceptions.asRuntime;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.*;

import ychernenko.commons.core.transform.parse.ParseException;
import ychernenko.commons.core.transform.parse.Parser;

public class XmlObjectParser<T> implements Parser<T> {

	private final Class<T> clazz;
	private final Marshaller messageMarshaller;
	private final Unmarshaller messageUnmarshaller;

	public XmlObjectParser(Class<T> clazz, JAXBContext jaxbContext) {
		this.clazz = clazz;
		try {
			this.messageMarshaller = jaxbContext.createMarshaller();
			this.messageUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw asRuntime(e);
		}
	}

	public String print(JAXBElement<?> element) {
		try {
			Writer writer = new StringWriter();
			this.messageMarshaller.marshal(element, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException("Can not print object.", e);
		}
	}

	@Override
	public T parse(String s) throws ParseException {
		return parse(new StringReader(s));
	}

	@SuppressWarnings("unchecked")
	public T parse(Reader r) throws ParseException {
		Object result;
		try {
			result = this.messageUnmarshaller.unmarshal(r);
		} catch (Exception e) {
			throw new ParseException("Message unmarshalling failed", e);
		}
		if (clazz.isAssignableFrom(result.getClass())) {
			return (T) result;
		} else if (JAXBElement.class.isAssignableFrom(result.getClass())) {
			return ((JAXBElement<T>) result).getValue();
		}
		throw new ParseException("Unknown type of unmarshalling result: " + result.getClass());
	}
}
