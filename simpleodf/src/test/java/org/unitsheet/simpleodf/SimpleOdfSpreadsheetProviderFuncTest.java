package org.unitsheet.simpleodf;

import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class SimpleOdfSpreadsheetProviderFuncTest {

    private  SimpleOdfSpreadsheetProvider underTest;
    private SpreadsheetAdapter spreadsheetAdapter;

    @Before
    public void setUp() throws Exception {
        underTest = new  SimpleOdfSpreadsheetProvider();
        InputStream inputStream =  SimpleOdfSpreadsheetProviderFuncTest.class
                .getResourceAsStream("/spreadsheets/AverageOfTwoIntegers.ods");

        spreadsheetAdapter = underTest.createSpreadsheetAdapter(inputStream);
    }

    @Test
    public void checkGetCellByName() {
        CellInfo cellInfo = new CellInfo(null, "C3", null, null);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

    @Test
    public void checkGetCellByRowAndColumn() {
        CellInfo cellInfo = new CellInfo(null, null, 2, 2);
        Object value = spreadsheetAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

}
