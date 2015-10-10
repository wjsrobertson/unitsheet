package org.unitsheet.types.typeconverters;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.unitsheet.utils.Collections.listOf;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class NumericTypeConverterTest {

    private NumericTypeConverter underTest = new NumericTypeConverter();

    private List<Object> sampleJava5NumberInstances = listOf (
        Byte.valueOf((byte)101),
        Short.valueOf((short)101),
        Integer.valueOf(101),
        Long.valueOf(101),
        Float.valueOf(101f),
        Double.valueOf(101d),
        new BigDecimal("101"),
        new BigInteger("101"),
        new AtomicInteger(101),
        new AtomicLong(101) );

    private List<Class<? extends Number>> java5NumberTypes = listOf (
        Byte.class,
        Short.class,
        Integer.class,
        Long.class,
        Float.class,
        Double.class,
        BigDecimal.class,
        BigInteger.class,
        AtomicInteger.class,
        AtomicLong.class );

    private static final double FLOATING_POINT_EPSILON = 0.000001;

    @Test
    public void checkConvertsAllJava5NumberInstancesToAllJava5NumberInstances() {
         for (int i = 0; i < sampleJava5NumberInstances.size(); i++) {
            Object sourceNumberObject = sampleJava5NumberInstances.get(i);
            Class<?> sourceClass = sourceNumberObject.getClass();

            for (Class<? extends Number> destinationClass: java5NumberTypes) {
                Number convertedObject = underTest.convert(sourceNumberObject, destinationClass);

                // check returned number has correct value (101)
                String msg = "Type conversion problem when converting from " + sourceClass + " to " + destinationClass;
                assertEquals(msg, 101, convertedObject.intValue());

                // check return type is of requested class
                Class<? extends Number> actualDestinationClass = convertedObject.getClass();
                assertEquals("unexpected return type " + actualDestinationClass, destinationClass, actualDestinationClass);
            }
        }
    }

    @Test
    public void checkConvertsStringToAllJava5NumberInstances() {
        for (Class<? extends Number> destinationClass: java5NumberTypes) {
            Number convertedObject = underTest.convert("123", destinationClass);

            // check returned number has correct value (123)
            String message = "Type conversion problem when converting to " + destinationClass;
            assertEquals(message, 123, convertedObject.intValue());

            // check return type is of requested class
            Class<? extends Number> actualDestinationClass = convertedObject.getClass();
            message = "unexpected return type " + actualDestinationClass;
            assertEquals(message, destinationClass, actualDestinationClass);
        }
    }

    @Test
    public void checkConvertToPrimitivesFromString() {
        byte convertedByte = underTest.convert("42", byte.class);
        assertEquals(42, convertedByte);

        short convertedShort = underTest.convert("42", short.class);
        assertEquals(42, convertedShort);

        int convertedInt = underTest.convert("42", int.class);
        assertEquals(42, convertedInt);

        long convertedLong = underTest.convert("42", long.class);
        assertEquals(42, convertedLong);

        float convertedFloat = underTest.convert("42", float.class);
        assertEquals(42f, convertedFloat, FLOATING_POINT_EPSILON);

        double convertedDouble = underTest.convert("42", double.class);
        assertEquals(42d, convertedDouble, FLOATING_POINT_EPSILON);
    }

    @Test
    public void checkConvertFromStringToBigDecimalWithValueLargerThanLongMaxValue() {
        BigInteger largeNumber = BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN);
        String largeNumberAsString = largeNumber.toString();

        BigInteger converted = underTest.convert(largeNumberAsString, BigInteger.class);

        assertEquals(largeNumber, converted);
    }

    @Test
    public void checkConvertFromBigIntegerWithValueLargerThanLongMaxValue() {
        BigInteger largeNumber = BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TEN);

        BigInteger converted = underTest.convert(largeNumber, BigInteger.class);

        assertEquals(largeNumber, converted);
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenSourceIsNull() {
        underTest.convert(null, Double.class);
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenDestinationIsNull() {
        underTest.convert(Double.valueOf(10), null);
    }

}
