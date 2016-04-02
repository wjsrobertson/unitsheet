package org.unitsheet.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.api.types.TypeConverter;
import org.unitsheet.types.typeconverters.BooleanTypeConverter;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObjectConverterTest {

    @InjectMocks
    private ObjectConverter underTest;

    @Mock private TypeConverterRegistry typeConverterRegistry;
    private List<TypeConverter> typeConverters = new ArrayList<>();

    @Before
    public void setUp() {
        typeConverters.clear();
        when(typeConverterRegistry.getTypeConverters()).thenReturn(typeConverters);
    }

    @Test
    public void checkConvertTypeIgnoresNull() {
        // given
        String convertFrom = null;

        // when
        Number result = underTest.convertType(convertFrom, Number.class);

        // then
        assertThat(result).isNull();
    }

    @Test
    public void checkConvertTypeToSameTypeWorksForUnregisteredType() {
        /*
         given
          */
        class Banana {}
        Banana convertFrom = new Banana();

        // when
        Banana result = underTest.convertType(convertFrom, Banana.class);

        // then
        assertThat(result).isInstanceOf(Banana.class);
    }

    @Test
    public void checkConvertUsingRealTypeConverterReturnsResult() {
        /*
         given
          */
        typeConverters.add(new BooleanTypeConverter());
        String convertFrom = "true";

        // when
        Boolean result = underTest.convertType(convertFrom, Boolean.class);

        // then
        assertThat(result).isTrue();
    }
}