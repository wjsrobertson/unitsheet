package org.unitsheet.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.api.adapter.WorksheetType;

import java.io.IOException;
import java.io.InputStream;

public class ApachePoiHSSFSpreadsheetProvider implements SpreadsheetProvider {

    @Override
    public WorksheetType worksheetType() {
        return new XlsType();
    }

    @Override
    public SpreadsheetAdapter createSpreadsheetAdapter(InputStream inputStream)
            throws IOException {

        return new ApachePoiSpreadsheetAdapter(new HSSFWorkbook(inputStream));
    }
}
