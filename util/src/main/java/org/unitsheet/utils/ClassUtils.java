package org.unitsheet.utils;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.stream.Stream;

public class ClassUtils {

    public static boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    public static boolean hasPublicNoArgumentConstructor(Class<?> type) {
        if (type.getDeclaredConstructors().length == 0) {
            return true;
        }

        return Stream.of(type.getDeclaredConstructors()).anyMatch((c) -> c.getParameterCount() == 0 && (c.getModifiers() & Modifier.PUBLIC) > 0 );

    }

    /**
     * If a class represents an array then get the component type
     * <p/>
     * i.e. for "java.lang.String[]" return "java.lang.String"
     */
    public static String getComponentTypeName(Class<?> aClass) {
        String typeName = null;
        if (aClass.isArray()) {
            Class<?> componentType = aClass.getComponentType();
            typeName = componentType.getTypeName();
        }
        return typeName;
    }
}
