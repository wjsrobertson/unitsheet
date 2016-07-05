package org.unitsheet;

import org.junit.Rule;
import org.junit.Test;
import org.unitsheet.annotations.Cell;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.junit.SpreadsheetRule;

import static org.junit.Assert.assertEquals;

@Workbook("classpath:spreadsheets/AverageOfTwoIntegers.xls")
public class SpreadsheetRuleTest {

    @Rule
    public SpreadsheetRule spreadsheetRule = new SpreadsheetRule();

    @Cell("C2")
    private long privateStringField = 0l;

    @Test
    public void testX() {
        assertEquals(100l, privateStringField);
    }
}
