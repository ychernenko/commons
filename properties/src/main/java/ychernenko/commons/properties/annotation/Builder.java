package ychernenko.commons.properties.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ychernenko.commons.properties.build.PropertyBuilder;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Builder {

    Class<? extends PropertyBuilder> value();
}
