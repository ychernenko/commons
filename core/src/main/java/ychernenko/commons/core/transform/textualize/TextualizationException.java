package ychernenko.commons.core.transform.textualize;

import ychernenko.commons.core.transform.TransformationException;

public class TextualizationException extends TransformationException {

    public TextualizationException(String message) {
        super(message);
    }

    public TextualizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextualizationException(Throwable cause) {
        super(cause);
    }
}
