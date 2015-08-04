package ychernenko.commons.core.transform.textualize;

public interface Textualizer<T> {

    String textualize(T object) throws TextualizationException;
}
