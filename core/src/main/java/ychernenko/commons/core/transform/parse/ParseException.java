package ychernenko.commons.core.transform.parse;

import ychernenko.commons.core.transform.TransformationException;

public class ParseException extends TransformationException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
