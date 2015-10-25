package org.unitsheet.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import static org.fest.assertions.Assertions.assertThat;
import static org.unitsheet.utils.ReflectionUtils.getGenericType;
import static org.unitsheet.utils.ReflectionUtils.getObjectFieldsInOrder;
import static org.unitsheet.utils.ReflectionUtils.setObjectFieldValue;

public class ReflectionUtilsTest {

    static class ClassWithGenericFields {
        public List<String> stringList;
        public String[] stringArray;
    }

    @Test
    public void checkStringListHasGenericTypeString() throws NoSuchFieldException {
        // given
        Class<ClassWithGenericFields> aClass = ClassWithGenericFields.class;
        Field field = aClass.getField("stringList");

        // when
        String typeName = getGenericType(field);

        // then
        assertThat(typeName).isEqualTo("java.lang.String");
    }

    @Test
    public void checkGetObjectFieldsInOrder() {
        // given
        ClassWithGenericFields test = new ClassWithGenericFields();

        // then
        SortedSet<Field> fields = getObjectFieldsInOrder(test);

        /*
         then
          */
        assertThat(fields.size()).isEqualTo(2);

        Iterator<Field> iterator = fields.iterator();
        Field first = iterator.next();
        Field second = iterator.next();

        assertThat(first.getName()).isEqualTo("stringArray");
        assertThat(second.getName()).isEqualTo("stringList");
    }

    @Test
    public void checkSetObjectFieldValue() throws NoSuchFieldException, IllegalAccessException {
        // given
        ClassWithGenericFields test = new ClassWithGenericFields();

        // when
        Field field = test.getClass().getDeclaredField("stringList");
        ArrayList<String> list = new ArrayList<String>();
        setObjectFieldValue(field, test, list);

        // then
        assertThat(test.stringList).isEqualTo(list);
    }


}
