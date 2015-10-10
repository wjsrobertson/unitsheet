package org.unitsheet.types.typeconverters;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BooleanTypeConverterTest {

    private BooleanTypeConverter underTest = new BooleanTypeConverter();

    @Test
    public void checkConvertFromTrueStringsSucceeds() {
        String[] trueValues = {"TRUE", "true", "true"};

        for (String trueValue: trueValues) {
            boolean converted = underTest.convert(trueValue, boolean.class);
            assertTrue("converting failed from string '" + trueValue + "' to boolean primitive", converted);
        }
        for (String trueValue: trueValues) {
            Boolean converted = underTest.convert(trueValue, Boolean.class);
            assertTrue("converting failed from string '" + trueValue + "' to Boolean object", converted);
        }
    }

    @Test
    public void checkConvertFromFalseStringsSucceeds() {
        String[] falseValues = {"FALSE", "false", "anythingexcepttrue", ""};

        for (String falseValue: falseValues) {
            boolean converted = underTest.convert(falseValue, boolean.class);
            assertFalse("converting failed from string '" + falseValue + "' to boolean primitive", converted);
        }
        for (String trueValue: falseValues) {
            Boolean converted = underTest.convert(trueValue, Boolean.class);
            assertFalse("converting failed from string '" + trueValue + "' to Boolean object", converted);
        }
    }

    @Test
    public void checkConvertFromInvalidTypesReturnsNull() {
        Object[] invalidObjects = new Object[] {Integer.valueOf(1), new Object(), new HashSet<String>()};
        for (Object invalidObject: invalidObjects) {
            Boolean convertedObject = underTest.convert(invalidObject, Boolean.class);
            assertNull("converting from '" + invalidObject + "' to Boolean object should return null", convertedObject);
        }
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenSourceIsNull() {
        underTest.convert(null, Boolean.class);
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenDestinationIsNull() {
        underTest.convert(Boolean.FALSE, null);
    }

}
