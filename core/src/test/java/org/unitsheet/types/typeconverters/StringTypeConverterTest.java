package org.unitsheet.types.typeconverters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 */
public class StringTypeConverterTest {

    private StringTypeConverter underTest = new StringTypeConverter();

    @Test
    public void testConvertToStringFromVarious() throws Exception {
        StringBuilder builder = new StringBuilder("this is a test");
        assertEquals("this is a test", underTest.convert(builder, String.class));

        Long testLong = new Long(99999);
        assertEquals("99999", underTest.convert(testLong, String.class));
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenSourceIsNull() {
        underTest.convert(null, String.class);
    }

    @Test(expected = NullPointerException.class)
    public void checkConvertFailsWithNPEWhenDestinationIsNull() {
        underTest.convert(new Object(), null);
    }

}
