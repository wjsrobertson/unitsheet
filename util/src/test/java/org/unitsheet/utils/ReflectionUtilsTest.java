package org.unitsheet.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.unitsheet.utils.ClassUtils.isCollection;
import static org.unitsheet.utils.ReflectionUtils.*;

public class ReflectionUtilsTest {

    static class ClassWithFields {
        public List<String> stringList;
        public String[] stringArray;
        public String string;
    }

    static class ClassWithListMethod {
        public void listMethod(List<String> stringList){
        }
    }

    static class ClassWithStringMethod {
        public void stringMethod(String string){
        }
    }

    @Test
    public void checkStringListHasGenericTypeClassString() throws NoSuchFieldException {
        // given
        Class<ClassWithFields> aClass = ClassWithFields.class;
        Field field = aClass.getField("stringList");

        // when
        Class<?> typeClass = getGenericTypeClass(field.getGenericType()).get();

        // then
        assertThat(typeClass.getName()).isEqualTo("java.lang.String");
    }

    @Test
    public void checkGetObjectFieldsInOrder() {
        // given
        ClassWithFields test = new ClassWithFields();

        // then
        SortedSet<Field> fields = getObjectFieldsInOrder(test);

        /*
         then
          */
        assertThat(fields.size()).isEqualTo(3);

        Iterator<Field> iterator = fields.iterator();
        Field first = iterator.next();
        Field second = iterator.next();
        Field third = iterator.next();

        assertThat(first.getName()).isEqualTo("string");
        assertThat(second.getName()).isEqualTo("stringArray");
        assertThat(third.getName()).isEqualTo("stringList");
    }

    @Test
    public void checkSetObjectFieldValue() throws NoSuchFieldException, IllegalAccessException {
        // given
        ClassWithFields test = new ClassWithFields();

        /*
        when
         */
        Field field = test.getClass().getDeclaredField("stringList");
        ArrayList<String> list = new ArrayList<String>();
        setObjectFieldValue(field, test, list);

        // then
        assertThat(test.stringList).isEqualTo(list);
    }

    @Test
    public void checkDetermineTypeForNonCollectionField() throws NoSuchFieldException {
        // given
        Class<ClassWithFields> aClass = ClassWithFields.class;
        Field field = aClass.getField("string");

        // when
        Class<?> typeClass = determineType(field);

        // then
        assertThat(typeClass.getName()).isEqualTo("java.lang.String");
    }

    @Test
    public void checkDetermineTypeForCollectionField() throws NoSuchFieldException {
        // given
        Class<ClassWithFields> aClass = ClassWithFields.class;
        Field field = aClass.getField("stringList");

        // when
        Class<?> typeClass = determineType(field);

        // then
        assertThat(typeClass.getName()).isEqualTo("java.lang.String");
    }

    @Test
    public void checkDetermineTypeForNonCollectionParam() {
        // given
        Class<ClassWithStringMethod> aClass = ClassWithStringMethod.class;
        Method method = aClass.getMethods()[0];
        Parameter stringParameter = method.getParameters()[0];

        // when
        Class<?> typeClass = determineType(stringParameter);

        // then
        assertThat(typeClass.getName()).isEqualTo("java.lang.String");
    }

    @Test
    public void checkDetermineTypeForCollectionParam() {
        // given
        Class<ClassWithListMethod> aClass = ClassWithListMethod.class;
        Method method = aClass.getMethods()[0];
        Parameter listParameter = method.getParameters()[0];

        // when
        Class<?> typeClass = determineType(listParameter);

        // then
        assertThat(typeClass.getName()).isEqualTo("java.lang.String");
    }
}
