package org.unitsheet.types;

import org.junit.Test;
import org.unitsheet.api.types.TypeConverter;
import org.unitsheet.test.DummyTypeConverter;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class PluginTypeConverterLoaderTest {

    private PluginTypeConverterLoader underTest = new PluginTypeConverterLoader();

    @Test
    public void checkLoadTypeConvertersFromPluginsFindDummyTypeConverter() {
        List<TypeConverter> typeConverters = underTest.loadTypeConvertersFromPlugins();

        assertThat(typeConverters).hasSize(1);
        assertThat(typeConverters.get(0)).isInstanceOf(DummyTypeConverter.class);
    }
}