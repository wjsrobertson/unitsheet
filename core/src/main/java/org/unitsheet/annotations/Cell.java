package org.unitsheet.annotations;

import java.lang.annotation.*;

/**
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Cell {

    String sheet() default "";

    // TODO - sort out name / value - do we need both?
    //String name() default "";

    String value();

}
