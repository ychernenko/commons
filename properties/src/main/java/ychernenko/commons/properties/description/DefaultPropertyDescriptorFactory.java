package ychernenko.commons.properties.description;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import ychernenko.commons.core.Getter;
import ychernenko.commons.core.transform.parse.Parser;
import ychernenko.commons.properties.annotation.*;
import ychernenko.commons.properties.build.PropertyBuilder;

public class DefaultPropertyDescriptorFactory implements PropertyDescriptorFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> DefaultPropertyDescriptor<T> create(Method method) {
		String key = getKey(method);
		boolean required = isRequired(method);
		String defaultValue = getDefaultValue(method);
		Class<? extends Getter> defaultValueHolder = getDefaultValueHolderClass(method);
		Class<? extends Parser> parserClass = getParserClass(method);
		Class<? extends PropertyBuilder> loaderClass = getLoaderClass(method);
		return new DefaultPropertyDescriptor(key, method.getReturnType(), required, defaultValue, defaultValueHolder, parserClass, loaderClass);
	}

	private static String getKey(Method method) {
		Key key = method.getAnnotation(Key.class);
		return key == null || key.value() == null ? method.getName() : key.value();
	}

	private static String getDefaultValue(AnnotatedElement method) {
		DefaultValue annotation = method.getAnnotation(DefaultValue.class);
		return annotation == null ? null : annotation.value();
	}

	private static boolean isRequired(AnnotatedElement method) {
		Required annotation = method.getAnnotation(Required.class);
		return annotation != null && annotation.value();
	}

	private static Class<? extends Parser> getParserClass(AnnotatedElement method) {
		PropertyParser annotation = method.getAnnotation(PropertyParser.class);
		return annotation == null ? null : annotation.value();
	}

	private static Class<? extends PropertyBuilder> getLoaderClass(AnnotatedElement method) {
		Builder annotation = method.getAnnotation(Builder.class);
		return annotation == null ? null : annotation.value();
	}

	private static Class<? extends Getter> getDefaultValueHolderClass(AnnotatedElement method) {
		DefaultValueGetter annotation = method.getAnnotation(DefaultValueGetter.class);
		return annotation == null ? null : annotation.value();
	}

}
