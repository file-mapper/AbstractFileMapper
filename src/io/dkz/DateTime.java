package io.dkz;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author Daniel Kiluange
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface DateTime {
    String pattern() default "ddMMyyyy";
}
