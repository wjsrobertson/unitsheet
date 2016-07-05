package org.unitsheet.reflection;

import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

import static org.unitsheet.utils.ReflectionUtils.*;

public class TestObjectFieldPopulator {

    private final SpreadsheetAdapter spreadsheet;

    private final Set<ValueResolver> valueResolvers;

    public TestObjectFieldPopulator(SpreadsheetAdapter spreadsheet, Set<ValueResolver> valueResolvers) {
        this.spreadsheet = spreadsheet;
        this.valueResolvers = valueResolvers;
    }

    @SuppressWarnings("unchecked")
    public void populateFieldsWithValuesFromSpreadsheet(Object testObject) {
        SortedSet<Field> fields = getObjectFieldsInOrder(testObject);

        for (Field field : fields) {
            for (ValueResolver valueResolver : valueResolvers) {
                Annotation annotation = field.getAnnotation(valueResolver.getAnnotationType());
                if (annotation != null) {
                    Class<?> destType = determineType(field);
                    Object result = valueResolver.resolveValue(annotation, destType, spreadsheet);

                    setObjectFieldValueQuietly(field, testObject, result);
                }
            }
        }
    }

    private void setObjectFieldValueQuietly(Field field, Object testObject, Object result) {
        try {
            setObjectFieldValue(field, testObject, result);
        } catch (IllegalAccessException | SecurityException e) {
            throw new RuntimeException(); // TODO - handle me
        }
    }

}