package org.unitsheet;

import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.reflection.TestMethodArgumentResolver;
import org.unitsheet.reflection.TestObjectFieldPopulator;

public class TestContext {

    private final SpreadsheetAdapter spreadsheetAdapter;

    private final TestObjectFieldPopulator populator;

    private final TestMethodArgumentResolver argumentResolver;

    public TestContext(SpreadsheetAdapter spreadsheetAdapter,
                       TestObjectFieldPopulator populator,
                       TestMethodArgumentResolver argumentResolver) {

        this.spreadsheetAdapter = spreadsheetAdapter;
        this.populator = populator;
        this.argumentResolver = argumentResolver;
    }

    public TestObjectFieldPopulator getFieldPopulator() {
        return populator;
    }

    public SpreadsheetAdapter getSpreadsheetAdapter() {
        return spreadsheetAdapter;
    }

    public TestMethodArgumentResolver getArgumentResolver() {
        return argumentResolver;
    }
}
