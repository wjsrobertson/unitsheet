package org.unitsheet;

import org.unitsheet.annotations.Workbook;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.source.WorkbookSource;
import org.unitsheet.source.WorkbookSourceProvider;
import org.unitsheet.types.ObjectConverter;
import org.unitsheet.types.PluginTypeConverterLoader;
import org.unitsheet.types.TypeConverterRegistry;

import java.io.*;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;
import static org.unitsheet.utils.FilePaths.fileExtension;

public class TestManager {

    private SpreadsheetAdapterRegistry spreadsheetAdapterRegistry;

    public TestManager() {
        PluginSpreadsheetAdapterLoader pluginSpreadsheetAdapterLoader = new PluginSpreadsheetAdapterLoader();
        spreadsheetAdapterRegistry = new SpreadsheetAdapterRegistry(pluginSpreadsheetAdapterLoader);
    }

    public TestContext createTestContext(Class<?> testClass) {
        Workbook annotation = testClass.getAnnotation(Workbook.class);
        if (annotation == null) {
            throw new RuntimeException(); // TODO - tidy me
        }

        String workbookPath = annotation.value();

        WorkbookSource workbookSource = new WorkbookSourceProvider().createWorkbookSourceForPath(workbookPath);
        InputStream inputStream = workbookSource.getInputStream();

        String workbookExtension = fileExtension(workbookPath);
        SpreadsheetProvider spreadsheetProvider
                = spreadsheetAdapterRegistry.getSpreadsheetAdapterBySupportedExtension(workbookExtension);
        checkNotNull(spreadsheetProvider, "No provider matches extension " + workbookExtension);

        SpreadsheetAdapter spreadsheetAdapter;
        try {
            spreadsheetAdapter = spreadsheetProvider.createSpreadsheetAdapter(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO - tidy me
        }

        PluginTypeConverterLoader pluginTypeConverterLoader = new PluginTypeConverterLoader();
        TypeConverterRegistry typeConverterRegistry = new TypeConverterRegistry(pluginTypeConverterLoader);
        ObjectConverter objectConverter = new ObjectConverter(typeConverterRegistry);

        TestObjectFieldPopulator populator = new TestObjectFieldPopulator(spreadsheetAdapter, objectConverter);

        return new TestContext(spreadsheetAdapter, populator); //TODO - FIXME
    }

}
