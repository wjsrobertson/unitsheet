package org.unitsheet.types;

import org.unitsheet.types.typeconverters.BooleanTypeConverter;
import org.unitsheet.types.typeconverters.NumericTypeConverter;
import org.unitsheet.types.typeconverters.StringTypeConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TypeConverterRegistryTest {

    private TypeConverterRegistry underTest;

    @Mock PluginTypeConverterLoader pluginTypeConverterLoader;

    @Before
    public void setUp() {
        underTest = new TypeConverterRegistry(pluginTypeConverterLoader);
    }

    @Test
    public void checkRegistryContainsBooleanConverterByDefault() {
        long numBooleanTypeConverters =
                underTest.getTypeConverters().stream().filter(x -> x instanceof BooleanTypeConverter).count();

        assertTrue("BooleanTypeConverter not found in list", numBooleanTypeConverters == 1);
    }

    @Test
    public void checkRegistryContainsStringConverterByDefault() {
        long numStringTypeConverters =
                underTest.getTypeConverters().stream().filter(x -> x instanceof StringTypeConverter).count();

        assertTrue("StringTypeConverter not found in list", numStringTypeConverters == 1);
    }

    @Test
    public void checkRegistryContainsIntegerConverterByDefault() {
        long numNumericTypeConverters =
                underTest.getTypeConverters().stream().filter(x -> x instanceof NumericTypeConverter).count();

        assertTrue("NumericTypeConverter not found in list", numNumericTypeConverters == 1);
    }


}
