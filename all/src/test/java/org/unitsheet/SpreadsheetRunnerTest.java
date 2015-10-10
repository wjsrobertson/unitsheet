package org.unitsheet;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.annotations.ForceWorkbookType;
import org.unitsheet.junit.SpreadsheetRunner;
import org.unitsheet.poi.XlsType;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(SpreadsheetRunner.class)
@Workbook("classpath:spreadsheets/AverageOfTwoIntegers.ods")
@ForceWorkbookType(XlsType.class)
public class SpreadsheetRunnerTest {

    @ReadCell("C2")
    private long privateStringField = 50l;

    @Test
    public void testX() {
        assertEquals(100l, privateStringField);
    }

}
