package org.unitsheet.types.typeconverters;

import org.unitsheet.api.types.TypeConverter;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;

/**
 * Supports conversion from {@code String} and {@code Boolean} to {@code Boolean} and {@code boolean}.
 *
 * Uses the same rules as {@see Boolean.valueOf} - which in the case of converting from {@code String}s means that
 * anything which matches {@code "true"} case-insensitive convert to true, otherwise false.
 */
public class BooleanTypeConverter implements TypeConverter {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(Object source, Class<T> destinationClass) {
        checkNotNull(source, "source may not be null");
        checkNotNull(destinationClass, "destinationClass may not be null");

        if (source instanceof String) {
            return (T) Boolean.valueOf((String) source);
        }
        else if (source instanceof Boolean) {
            return (T) source;
        }

        return null;
    }
}
