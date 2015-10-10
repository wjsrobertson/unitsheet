package org.unitsheet.types.typeconverters;

import org.unitsheet.api.types.TypeConverter;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;

public class StringTypeConverter implements TypeConverter {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(Object source, Class<T> destinationClass) {
        checkNotNull(source, "source may not be null");
        checkNotNull(destinationClass, "destinationClass may not be null");

        return (T) source.toString();
    }
}
