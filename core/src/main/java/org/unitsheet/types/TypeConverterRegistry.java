package org.unitsheet.types;

import org.unitsheet.api.types.TypeConverter;
import org.unitsheet.types.typeconverters.BooleanTypeConverter;
import org.unitsheet.types.typeconverters.NumericTypeConverter;
import org.unitsheet.types.typeconverters.StringTypeConverter;

import java.util.ArrayList;
import java.util.List;

public class TypeConverterRegistry {

    private final List<TypeConverter> typeConverters = new ArrayList<>();

    public TypeConverterRegistry(PluginTypeConverterLoader pluginTypeConverterLoader) {
        addPluginTypeConverters(pluginTypeConverterLoader);
        addDefaultConverters();
    }

    private void addPluginTypeConverters(PluginTypeConverterLoader pluginTypeConverterLoader) {
        typeConverters.addAll(pluginTypeConverterLoader.loadTypeConvertersFromPlugins());
    }

    private void addDefaultConverters() {
        typeConverters.add(new NumericTypeConverter());
        typeConverters.add(new BooleanTypeConverter());
        typeConverters.add(new StringTypeConverter());
    }

    public List<TypeConverter> getTypeConverters() {
        return typeConverters;
    }
}
