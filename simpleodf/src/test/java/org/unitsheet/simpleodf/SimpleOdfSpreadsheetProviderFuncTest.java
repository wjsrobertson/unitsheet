package org.unitsheet.simpleodf;

import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.ColumnInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class SimpleOdfSpreadsheetProviderFuncTest {

    private SimpleOdfSpreadsheetProvider underTest;
    private SpreadsheetAdapter averageToIntegersAdapter;
    private SpreadsheetAdapter sumColumnAdapter;

    @Before
    public void setUp() throws Exception {
        underTest = new SimpleOdfSpreadsheetProvider();

        averageToIntegersAdapter = getSpreadsheetAdapter("/spreadsheets/AverageOfTwoIntegers.ods");
        sumColumnAdapter = getSpreadsheetAdapter("/spreadsheets/SumOfColumn.ods");

    }

    private SpreadsheetAdapter getSpreadsheetAdapter(String spreadsheetName) throws IOException {
        try (InputStream inputStream = SimpleOdfSpreadsheetProviderFuncTest.class.getResourceAsStream(spreadsheetName)) {
            return underTest.createSpreadsheetAdapter(inputStream);
        }
    }

    @Test
    public void checkGetCellByName() {
        CellInfo cellInfo = new CellInfo(null, "C3", null, null);
        Object value = averageToIntegersAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

    @Test
    public void checkGetCellByRowAndColumn() {
        CellInfo cellInfo = new CellInfo(null, null, 2, 2);
        Object value = averageToIntegersAdapter.getCellValue(cellInfo);
        assertEquals("200", value);
    }

    @Test
    public void checkGetColumn() {
        CellInfo from = new CellInfo(null, "B2", null, null);
        CellInfo to = new CellInfo(null, "B6", null, null);
        ColumnInfo columnInfo = new ColumnInfo(null, to, from);

        List<Object> column = sumColumnAdapter.getColumn(columnInfo);
        assertThat(column).hasSize(5);
        assertThat(column).isEqualTo(asList(new Object[]{"100", "200", "300", "400", "500"}));
    }
}
