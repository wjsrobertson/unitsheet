package org.unitsheet;

import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.api.adapter.WorksheetType;

import java.util.List;
import java.util.Optional;

public class SpreadsheetAdapterRegistry {

    private final List<SpreadsheetProvider> adapters;

    public SpreadsheetAdapterRegistry(PluginSpreadsheetAdapterLoader pluginSpreadsheetAdapterLoader) {
        adapters = pluginSpreadsheetAdapterLoader.loadTypeConvertersFromPlugins();
    }

    public List<SpreadsheetProvider> getSpreadsheetAdapters() {
        return adapters;
    }

    public SpreadsheetProvider getSpreadsheetAdapterBySupportedExtension(String extension) {
        for (SpreadsheetProvider adapter : adapters) {
            if (adapter.worksheetType().supportedExtensions().contains(extension)) {
                return adapter;
            }
        }

        return null;
    }

    public SpreadsheetProvider getSpreadsheetAdapterBySpreadsheetType(WorksheetType worksheetType) {
        Optional<SpreadsheetProvider> first = adapters.stream()
                .filter(x -> x.worksheetType().getClass().equals(worksheetType.getClass()))
                .findFirst();

        if (first.isPresent()) {
            return first.get();
        } else {
            return null;
        }
    }



}
