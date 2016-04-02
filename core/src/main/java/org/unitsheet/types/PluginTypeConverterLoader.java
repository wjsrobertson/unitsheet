package org.unitsheet.types;

import org.unitsheet.api.types.TypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginTypeConverterLoader {

    public List<TypeConverter> loadTypeConvertersFromPlugins() {
        ServiceLoader<TypeConverter> loader = ServiceLoader.load(TypeConverter.class);

        List<TypeConverter> pluginTypeConverters = new ArrayList<>();
        loader.iterator().forEachRemaining(pluginTypeConverters::add);

        return pluginTypeConverters;
    }
}
