package ychernenko.commons.core.transform;

public class TransformationException extends RuntimeException {

	public TransformationException(String message) {
		super(message);
	}

	public TransformationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransformationException(Throwable cause) {
		super(cause);
	}
}
