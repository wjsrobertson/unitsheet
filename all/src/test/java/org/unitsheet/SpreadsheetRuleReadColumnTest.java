package org.unitsheet;

import org.junit.Rule;
import org.junit.Test;
import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.ReadColumn;
import org.unitsheet.annotations.Workbook;
import org.unitsheet.junit.SpreadsheetRule;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.unitsheet.utils.Collections.listOf;

@Workbook("classpath:spreadsheets/SumOfColumn.ods")
public class SpreadsheetRuleReadColumnTest {

    @Rule
    public SpreadsheetRule spreadsheetRule = new SpreadsheetRule();

    @ReadColumn(from = "B2", to = "B6")
    private List<Integer> listOfNumbers;

    @Test
    public void checkAllNumbersGetRead() {
        List<Integer> expcected = listOf(100, 200, 300, 400, 500);
        assertThat(listOfNumbers)
                .hasSize(5)
                .isEqualTo(expcected);
    }

}
