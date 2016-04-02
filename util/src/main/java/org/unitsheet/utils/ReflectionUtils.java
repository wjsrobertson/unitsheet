package org.unitsheet.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ReflectionUtils {

    private ReflectionUtils(){} // non-instantiable

    /**
     * Gets the generic type of a field.
     *
     * Assumes there is only one generic argument.
     *
     * TODO - use optional, handle non-happy path
     */
    public static Optional<Class<?>> getGenericTypeClass(Field field) {
        Class<?> typeClass = null;

        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == 1) {
                Type actualTypeArgument = actualTypeArguments[0];
                if (actualTypeArgument instanceof Class) {
                    typeClass = (Class) actualTypeArgument;
                }
            } else {
                // TODO
            }
        } else {
            // TODO
        }

        return Optional.ofNullable(typeClass);
    }

    // TODO - cleanup
    public static SortedSet<Field> getObjectFieldsInOrder(Object object) {
        Class<?> clazz = object.getClass();

        Set<Field> allFields = new HashSet<>();
        allFields.addAll(asList(clazz.getFields()));
        allFields.addAll(asList(clazz.getDeclaredFields()));

        Set<Field> noSynthetic = allFields.stream().filter(x -> !x.isSynthetic()).collect(Collectors.toSet());

        SortedSet<Field> sorted = new TreeSet<>((Field x, Field y) -> x.getName().compareTo(y.getName()));
        sorted.addAll(noSynthetic);

        return sorted;
    }

    // TODO - check security permissions / SecurityException
    public static void setObjectFieldValue(Field field, Object object, Object value)
            throws IllegalAccessException, SecurityException {

        boolean accessibleField = field.isAccessible();
        if (!accessibleField) {
            field.setAccessible(true);
        }

        field.set(object, value);

        if (!accessibleField) {
            field.setAccessible(false);
        }
    }

}
