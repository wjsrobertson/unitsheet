package org.unitsheet.reflection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.annotations.Column;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.ColumnInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.types.ObjectConverter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.unitsheet.utils.Collections.listOf;

@RunWith(MockitoJUnitRunner.class)
public class ColumnValueResolverTest {

    @Mock
    private SpreadsheetAdapter spreadsheet;

    @Mock
    private ObjectConverter objectConverter;

    @Mock
    private Column column;

    @Mock
    private List<Object> list;

    @InjectMocks
    private ReadColumnValueResolver underTest;

    @Test
    public void checkValueIsResolved() {
        /*
           Given a column which has two cells and happy path SpreadsheetAdapter & ObjectConverter
         */
        when(column.from()).thenReturn("A1");
        when(column.to()).thenReturn("A2");

        List<Object> rawList = listOf("raw1", "raw2");
        when(spreadsheet.getColumn(new ColumnInfo(null, createCellInfo("A1"), createCellInfo("A2"))))
                .thenReturn(rawList);

        when(objectConverter.convertType("raw1", String.class)).thenReturn("result1");
        when(objectConverter.convertType("raw2", String.class)).thenReturn("result2");

        // when
        Object value = underTest.resolveValue(column, String.class, spreadsheet);

        /*
         then value is a list containing the Strings "result1" and "result2"
          */
        assertThat(value).isInstanceOf(List.class);
        List resultList = (List) value;

        assertThat(resultList.get(0))
                .isInstanceOf(String.class)
                .isEqualTo("result1");

        assertThat(resultList.get(1))
                .isInstanceOf(String.class)
                .isEqualTo("result2");
    }

    private CellInfo createCellInfo(String a1) {
        return CellInfo.builder().withName(a1).build();
    }

}