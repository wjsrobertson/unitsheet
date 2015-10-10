package org.unitsheet;

import org.unitsheet.api.adapter.SpreadsheetProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginSpreadsheetAdapterLoader {

    public List<SpreadsheetProvider> loadTypeConvertersFromPlugins() {
        ServiceLoader<SpreadsheetProvider> loader = ServiceLoader.load(SpreadsheetProvider.class);

        List<SpreadsheetProvider> pluginTypeConverters = new ArrayList<>();
        loader.iterator().forEachRemaining(converter -> pluginTypeConverters.add(converter));

        return pluginTypeConverters;
    }

}
