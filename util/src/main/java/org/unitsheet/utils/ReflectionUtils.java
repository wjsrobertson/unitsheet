package org.unitsheet.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Arrays.asList;

public class ReflectionUtils {

    private ReflectionUtils(){}

    /**
     * Gets the generic type of a field.
     *
     * Assumes there is only one generic argument.
     *
     * e.g. For a field of type List<String> the result will be java.lang.String
     */
    public static String getGenericType(Field field) {
        String typeName = null;

        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == 1) {
                Type actualTypeArgument = actualTypeArguments[0];
                typeName = actualTypeArgument.getTypeName();
            } else {
                // TODO
            }

            parameterizedType.toString();
        } else {
            // TODO
        }

        return typeName;
    }

    public static SortedSet<Field> getObjectFieldsInOrder(Object object) {
        Class<?> clazz = object.getClass();
        SortedSet<Field> fields = new TreeSet<>((Field x, Field y) -> x.getName().compareTo(y.getName()));
        fields.addAll(asList(clazz.getFields()));
        fields.addAll(asList(clazz.getDeclaredFields()));

        return fields;
    }

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
