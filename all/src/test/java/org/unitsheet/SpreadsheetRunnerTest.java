package org.unitsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitsheet.annotations.Cell;
import org.unitsheet.annotations.WorkbookType;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.junit.SpreadsheetRunner;
import org.unitsheet.poi.XlsType;

import static org.junit.Assert.assertEquals;

@RunWith(SpreadsheetRunner.class)
@Workbook("classpath:spreadsheets/AverageOfTwoIntegers.xls")
@WorkbookType(XlsType.class)
public class SpreadsheetRunnerTest {

    @Cell("C2")
    private long privateStringField = 50L;

    @Test
    public void testField() {
        assertEquals(100L, privateStringField);
    }

    @Test
    public void testParameters(@Cell("C2") long param1, @Cell("C2") long param2) {
        assertEquals(100L, param1);
        assertEquals(100L, param2);
    }
}
