package org.unitsheet.poi;

import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ApachePoiHSSFSpreadsheetProviderFuncTest {

    private ApachePoiHSSFSpreadsheetProvider underTest;
    private SpreadsheetAdapter spreadsheetAdapter;

    @Before
    public void setUp() throws Exception {
        underTest = new ApachePoiHSSFSpreadsheetProvider();
        InputStream inputStream = ApachePoiHSSFSpreadsheetProviderFuncTest.class
                .getResourceAsStream("/spreadsheets/AverageOfTwoIntegers.xls");

        spreadsheetAdapter = underTest.createSpreadsheetAdapter(inputStream);
    }

    @Test
    public void checkGetCellByName() {
        CellInfo cellInfo = new CellInfo(null, "C3", null, null);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals(Double.valueOf(200), value);
    }

    @Test
    public void checkGetCellByRowAndColumn() {
        CellInfo cellInfo = new CellInfo(null, null, 2, 2);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals(Double.valueOf(200), value);
    }

}
