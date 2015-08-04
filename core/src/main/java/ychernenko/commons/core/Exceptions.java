package ychernenko.commons.core;

public class Exceptions {

	public static RuntimeException asRuntime(Exception e) {
		return e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e);
	}
}
