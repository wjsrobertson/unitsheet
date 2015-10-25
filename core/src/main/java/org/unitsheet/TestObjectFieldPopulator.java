package org.unitsheet;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.types.ObjectConverter;
import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.lang.reflect.Field;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static org.unitsheet.utils.ReflectionUtils.getObjectFieldsInOrder;
import static org.unitsheet.utils.ReflectionUtils.setObjectFieldValue;

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
                handleReadCellAnnotation(testObject, field, annotation);
            }


        }
    }

    private void handleReadCellAnnotation(Object testObject, Field field, ReadCell annotation) {
        Object cellValue = resolveCellValue(annotation);
        Object fieldValue = objectConverter.convertType(cellValue, field.getType());

        try {
            setObjectFieldValue(field, testObject, fieldValue);
        } catch (IllegalAccessException | SecurityException e) {
            throw new RuntimeException(); // TODO - handle me
        }
    }

    private Object resolveCellValue(ReadCell annotation) {
        String cellName = annotation.value();
        String sheetName = annotation.sheet();

        CellInfo cellInfo = new CellInfo(sheetName, cellName, null, null);

        return spreadsheet.getCellValue(cellInfo);
    }
}
