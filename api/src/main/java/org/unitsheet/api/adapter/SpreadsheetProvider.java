package org.unitsheet.api.adapter;

import java.io.IOException;
import java.io.InputStream;

public interface SpreadsheetProvider {

    WorksheetType worksheetType();

    SpreadsheetAdapter createSpreadsheetAdapter(InputStream inputStream) throws IOException;   //, TODO - add test context for specific config etc.

}
