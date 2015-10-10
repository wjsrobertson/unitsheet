package org.unitsheet.supercsv;

import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.simplecsv.SuperCsvSpreadsheetProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class SuperCsvSpreadsheetProviderFuncTest {

    private SuperCsvSpreadsheetProvider underTest;
    private SpreadsheetAdapter spreadsheetAdapter;

    @Before
    public void setUp() throws Exception {
        underTest = new  SuperCsvSpreadsheetProvider();
        InputStream inputStream =  SuperCsvSpreadsheetProviderFuncTest.class
                .getResourceAsStream("/spreadsheets/AverageOfTwoIntegers.csv");

        spreadsheetAdapter = underTest.createSpreadsheetAdapter(inputStream);
    }

    @Test
    public void checkGetCellByName() {
        CellInfo cellInfo = new CellInfo(null, "B2", null, null);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

    @Test
    public void checkGetCellByRowAndColumn() {
        CellInfo cellInfo = new CellInfo(null, null, 1, 1);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

}
