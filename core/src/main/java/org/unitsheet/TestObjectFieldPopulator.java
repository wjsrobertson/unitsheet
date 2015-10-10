package org.unitsheet;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.types.ObjectConverter;
import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.lang.reflect.Field;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Arrays.asList;

public class TestObjectFieldPopulator {

    private final SpreadsheetAdapter spreadsheet;

    private final ObjectConverter objectConverter;

    public TestObjectFieldPopulator(SpreadsheetAdapter spreadsheet, ObjectConverter objectConverter) {
        this.spreadsheet = spreadsheet;
        this.objectConverter = objectConverter;
    }

    public void populateFieldsWithValuesFromSpreadsheet(Object testObject) {
        SortedSet<Field> fields = getObjectFieldsInOrder(testObject);

        for (Field field : fields) {
            ReadCell annotation = field.getAnnotation(ReadCell.class);
            if (annotation != null) {
                String cellName = annotation.value();
                String sheetName = annotation.sheet();

                CellInfo cellInfo = new CellInfo(sheetName, cellName, null, null);

                Object cellValue = spreadsheet.getCellValue(cellInfo);
                Object fieldValue = objectConverter.convertType(cellValue, field.getType());

                try {
                    setObjectFieldValue(field, testObject, fieldValue);
                } catch (IllegalAccessException | SecurityException e) {
                    throw new RuntimeException(); // TODO - handle me
                }
            }
        }
    }

    private SortedSet<Field> getObjectFieldsInOrder(Object object) {
        Class<?> clazz = object.getClass();
        SortedSet<Field> fields = new TreeSet<>((Field x, Field y) -> x.getName().compareTo(y.getName()));
        fields.addAll(asList(clazz.getFields()));
        fields.addAll(asList(clazz.getDeclaredFields()));

        return fields;
    }

    private void setObjectFieldValue(Field field, Object object, Object value)
            throws IllegalAccessException, SecurityException {

        boolean accessibleField = field.isAccessible();
        if (!accessibleField) {
            field.setAccessible(true);
        }

        field.set(object, value);

        if (!accessibleField) {
            field.setAccessible(false);
        }
    }


}
