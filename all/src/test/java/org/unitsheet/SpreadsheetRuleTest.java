package org.unitsheet;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.junit.SpreadsheetRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Workbook("classpath:spreadsheets/AverageOfTwoIntegers.xls")
public class SpreadsheetRuleTest {

    @Rule
    public SpreadsheetRule spreadsheetRule = new SpreadsheetRule();

    @ReadCell("C2")
    private long privateStringField = 50l;

    @Test
    public void testX() {
        assertEquals(100l, privateStringField);
    }

}
