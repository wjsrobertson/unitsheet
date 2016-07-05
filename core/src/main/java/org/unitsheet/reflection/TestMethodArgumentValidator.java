package org.unitsheet.reflection;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.ReadColumn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import static org.unitsheet.utils.Collections.setOf;

public class TestMethodArgumentValidator {

    private static final Set<Class<?>> ALLOWED_TYPES = setOf(ReadCell.class, ReadColumn.class);

    public boolean hasValidArguments(Method m){
        for (Parameter parameter : m.getParameters()) {
            for (Class<?> allowedType : ALLOWED_TYPES) {
                Annotation[] annotations = parameter.getAnnotations();
                boolean hasValid = hasValidAnnotation(annotations);
                if (! hasValid) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasValidAnnotation(Annotation[] annotations) {
        boolean hasValid = false;
        for (Annotation annotation : annotations) {
            if (ALLOWED_TYPES.contains(annotation.annotationType())) {
                hasValid = true;
            }
        }
        return hasValid;
    }
}