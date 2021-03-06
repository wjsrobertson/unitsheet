package org.unitsheet.reflection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.annotations.Cell;
import org.unitsheet.api.adapter.SpreadsheetAdapter;


import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.unitsheet.utils.Collections.setOf;

@RunWith(MockitoJUnitRunner.class)
public class TestObjectFieldPopulatorTest {

    @Mock
    private SpreadsheetAdapter spreadsheet;

    @Mock
    private ValueResolver valueResolver;

    private TestObjectFieldPopulator underTest;

    static class SomeClass {
        @Cell("A1")
        public String stringField;
    }

    @Before
    public void setUp() throws Exception {
        underTest = new TestObjectFieldPopulator(spreadsheet, setOf(valueResolver));
    }

    @Test
    public void checkName() {
        /*
        given a test object with @Cell annotation and a ValueResolver for @Cell
         */
        when(valueResolver.getAnnotationType()).thenReturn(Cell.class);
        when(valueResolver.resolveValue(isA(Cell.class), eq(String.class), eq(spreadsheet)))
                .thenReturn("value");
        SomeClass testObject = new SomeClass();

        // when the test object fields are populated
        underTest.populateFieldsWithValuesFromSpreadsheet(testObject);

        // then the string field is set to the value from the ValueResolver
        assertThat(testObject.stringField).isEqualTo("value");
    }
}