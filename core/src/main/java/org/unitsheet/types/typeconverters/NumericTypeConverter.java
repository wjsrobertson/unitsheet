package org.unitsheet.types.typeconverters;

import org.unitsheet.api.types.TypeConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;

/**
 * Convert from Number.class, String.class
 *
 * Convert to             String.class,
             Byte.class, byte.class,
             Short.class, short.class,
             Integer.class, int.class,
             Long.class, long.class,
             Double.class, double.class,
             Float.class, float.class,
             BigDecimal.class, BigInteger.class,
             AtomicInteger.class, AtomicLong.class
 *
 */
public class NumericTypeConverter implements TypeConverter {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convert(Object source, Class<T> destinationClass) {
        checkNotNull(source, "source may not be null");
        checkNotNull(destinationClass, "destinationClass may not be null");

        Class<?> sourceClass = source.getClass();

        if (String.class.isAssignableFrom(sourceClass)) {
            Object convertedObject = convertStringToDestinationNumberType(destinationClass, (String) source);
            if (convertedObject != null) {
                return (T) convertedObject;
            }
        }
        else if (Number.class.isAssignableFrom(sourceClass)) {
            Object convertedObject = convertNumberToDestinationNumberType(destinationClass, (Number) source);
            if (convertedObject != null) {
                return (T) convertedObject;
            }
        }

        // null implies conversion failed... ObjectConverter will keep trying remaining typeconverters
        return null;
    }

    private <T> Object convertStringToDestinationNumberType(Class<T> destinationClass, String source) {
        Object returnObj = null;

        if (Byte.class.equals(destinationClass) || byte.class.equals(destinationClass)) {
            returnObj = Byte.valueOf(source);
        }
        else if (Short.class.equals(destinationClass) || short.class.equals(destinationClass)) {
            returnObj = Short.valueOf(source);
        }
        else if (Integer.class.equals(destinationClass) || int.class.equals(destinationClass)) {
            returnObj = Integer.valueOf(source);
        }
        else if (Long.class.equals(destinationClass) || long.class.equals(destinationClass)) {
            returnObj = Long.valueOf(source);
        }
        else if (Double.class.equals(destinationClass) || double.class.equals(destinationClass)) {
            returnObj = Double.valueOf(source);
        }
        else if (Float.class.equals(destinationClass) || float.class.equals(destinationClass)) {
            returnObj = Float.valueOf(source);
        }
        else if (BigInteger.class.equals(destinationClass)) {
            returnObj = new BigInteger(source);
        }
        else if (BigDecimal.class.equals(destinationClass)) {
            returnObj = new BigDecimal(source);
        }
        else if (AtomicInteger.class.equals(destinationClass)) {
            returnObj = new AtomicInteger(Integer.valueOf(source));
        }
        else if (AtomicLong.class.equals(destinationClass)) {
            returnObj = new AtomicLong(Long.valueOf(source));
        }

        return returnObj;
    }

    private <T> Object convertNumberToDestinationNumberType(Class<T> destinationClass, Number sourceNumber) {
        Object returnObj = null;

        if (Byte.class.equals(destinationClass) || byte.class.equals(destinationClass)) {
            returnObj = sourceNumber.byteValue();
        }
        else if (Short.class.equals(destinationClass) || short.class.equals(destinationClass)) {
            returnObj = sourceNumber.shortValue();
        }
        else if (Integer.class.equals(destinationClass) || int.class.equals(destinationClass)) {
            returnObj = sourceNumber.intValue();
        }
        else if (Long.class.equals(destinationClass) || long.class.equals(destinationClass)) {
            returnObj = sourceNumber.longValue();
        }
        else if (Double.class.equals(destinationClass) || double.class.equals(destinationClass)) {
            returnObj = sourceNumber.doubleValue();
        }
        else if (Float.class.equals(destinationClass) || float.class.equals(destinationClass)) {
            returnObj = sourceNumber.floatValue();
        }
        else if (BigInteger.class.equals(destinationClass)) {
            returnObj = new BigInteger(sourceNumber.toString().replaceAll("\\..*",""));
        }
        else if (BigDecimal.class.equals(destinationClass)) {
            returnObj = new BigDecimal(sourceNumber.toString());
        }
        else if (AtomicInteger.class.equals(destinationClass)) {
            returnObj = new AtomicInteger(sourceNumber.intValue());
        }
        else if (AtomicLong.class.equals(destinationClass)) {
            returnObj = new AtomicLong(sourceNumber.longValue());
        }

        return returnObj;
    }
}
