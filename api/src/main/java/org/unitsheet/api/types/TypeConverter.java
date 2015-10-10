package org.unitsheet.api.types;

/**
 * A list of these objects is used in a chain of responsibility to convert from an instance of one object type
 * to another. e.g. Converting a {@code String} to an {@code Integer}.
 */
public interface TypeConverter {

    /**
     * Implementations must return null if they fail to convert
     *
     * @param source
     * @param destinationClass
     * @return
     */
    public <T> T convert(Object source, Class<T> destinationClass);

}
