package org.unitsheet.simpleodf;

import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.api.adapter.WorksheetType;

import java.io.IOException;
import java.io.InputStream;

public class SimpleOdfSpreadsheetProvider implements SpreadsheetProvider {

    @Override
    public WorksheetType worksheetType() {
        return new OdsType();
    }

    @Override
    public SpreadsheetAdapter createSpreadsheetAdapter(InputStream inputStream) throws IOException {
        return new SimpleOdfSpreadsheetAdapter(inputStream);
    }

}
