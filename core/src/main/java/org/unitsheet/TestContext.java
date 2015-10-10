package org.unitsheet;

import org.unitsheet.api.adapter.SpreadsheetAdapter;

public class TestContext {

    private final SpreadsheetAdapter spreadsheetAdapter;

    private final TestObjectFieldPopulator populator;

    public TestContext(SpreadsheetAdapter spreadsheetAdapter, TestObjectFieldPopulator populator) {
        this.spreadsheetAdapter = spreadsheetAdapter;
        this.populator = populator;
    }

    public TestObjectFieldPopulator getPopulator() {
        return populator;
    }

    public SpreadsheetAdapter getSpreadsheetAdapter() {
        return spreadsheetAdapter;
    }

}
