package org.unitsheet.types;

import org.unitsheet.api.types.TypeConverter;

public class ObjectConverter {

    private final TypeConverterRegistry typeConverterRegistry;

    public ObjectConverter(TypeConverterRegistry typeConverterRegistry) {
        this.typeConverterRegistry = typeConverterRegistry;
    }

    @SuppressWarnings("unchecked")
    public <T> T convertType(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        if (source.getClass().equals(destinationClass)) {
            return (T) source;
        }

        Class<?> sourceClass = source.getClass();

        for (TypeConverter converter: typeConverterRegistry.getTypeConverters()) {
            T result = converter.convert(source, destinationClass);
            if (result != null) {
                return result;
            }
        }

        // TODO - add nice exception
        throw new RuntimeException(
                "Can't convert object type object " + source + " from " + sourceClass + " to " + destinationClass);
    }

}
