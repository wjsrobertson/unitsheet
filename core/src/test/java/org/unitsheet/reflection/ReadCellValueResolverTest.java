package org.unitsheet.reflection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.annotations.ReadCell;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.types.ObjectConverter;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadCellValueResolverTest {

    @Mock
    private SpreadsheetAdapter spreadsheet;

    @Mock
    private ObjectConverter objectConverter;

    @Mock
    private ReadCell readCell;

    @InjectMocks
    private ReadCellValueResolver underTest;

    @Test
    public void checkValueIsResolved() {
        /*
         given ReadCell referencing cell A1, and happy path SpreadsheetAdapter and ObjectConverter calls
          */
        when(readCell.value()).thenReturn("A1");
        when(spreadsheet.getCellValue(CellInfo.builder().withName("A1").build())).thenReturn("raw_value");
        when(objectConverter.convertType("raw_value", String.class)).thenReturn("result");

        // when value is resolved
        Object value = underTest.resolveValue(readCell, String.class, spreadsheet);

        // then the value is the String "result"
        assertThat(value)
                .isInstanceOf(String.class)
                .isEqualTo("result");
    }
}