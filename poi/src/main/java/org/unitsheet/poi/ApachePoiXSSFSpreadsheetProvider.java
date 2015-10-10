package org.unitsheet.poi;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.api.adapter.WorksheetType;

import java.io.IOException;
import java.io.InputStream;

public class ApachePoiXSSFSpreadsheetProvider implements SpreadsheetProvider {

    @Override
    public WorksheetType worksheetType() {
        return new XlsxType();
    }

    @Override
    public SpreadsheetAdapter createSpreadsheetAdapter(InputStream inputStream)
            throws IOException {

        return new ApachePoiSpreadsheetAdapter(new XSSFWorkbook(inputStream));
    }
}
