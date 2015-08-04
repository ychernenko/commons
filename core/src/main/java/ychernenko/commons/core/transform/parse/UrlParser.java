package ychernenko.commons.core.transform.parse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class UrlParser implements Parser<URL> {

	@Override
	public URL parse(String value) {
		return parseUrl(value);
	}

	public static URL parseUrl(String address) {
		try {
			if (address.startsWith("classpath")) {
				return new URL(null, address, new ClassPathURLStreamHandler(Thread.currentThread().getContextClassLoader()));
			}
			return new URL(address);
		} catch (MalformedURLException e) {
			throw new ParseException(e);
		}
	}

	public static URL parseHostPort(String hostPost) {
		return parseUrl("http://" + hostPost);
	}


	public static class ClassPathURLStreamHandler extends URLStreamHandler {

		private final ClassLoader classLoader;

		public ClassPathURLStreamHandler(ClassLoader classLoader) {
			this.classLoader = classLoader;
		}

		@Override
		protected URLConnection openConnection(URL url) throws IOException {
			String path = url.getPath();
			URL resource = classLoader.getResource(path);
			if (resource == null) {
				throw new IOException("Resource '" + path + "' not found in classpath");
			}
			return resource.openConnection();
		}
	}
}
