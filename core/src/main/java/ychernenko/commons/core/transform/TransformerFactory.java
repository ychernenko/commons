package ychernenko.commons.core.transform;

public interface TransformerFactory {

	<T> Transformer<T> create(Class<T> clazz);
}
