package org.unitsheet.test;

import org.unitsheet.api.types.TypeConverter;

public class DummyTypeConverter implements TypeConverter {

    @Override
    public <T> T convert(Object source, Class<T> destinationClass) {
        throw new UnsupportedOperationException();
    }
}
