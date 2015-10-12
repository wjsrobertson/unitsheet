package org.unitsheet;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.junit.SpreadsheetRule;

import static org.junit.Assert.assertEquals;

@Workbook("classpath:spreadsheets/AverageOfTwoIntegers.xls")
@Ignore
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
