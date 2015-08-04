package ychernenko.commons.properties.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ychernenko.commons.core.transform.parse.Parser;

@Retention(RUNTIME)
@Target(METHOD)
public @interface PropertyParser {

    Class<? extends Parser> value();
}
