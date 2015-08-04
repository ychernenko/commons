package ychernenko.commons.core.transform;

import ychernenko.commons.core.transform.parse.Parser;
import ychernenko.commons.core.transform.textualize.Textualizer;

public interface Transformer<T> extends Parser<T>, Textualizer<T> {

}
