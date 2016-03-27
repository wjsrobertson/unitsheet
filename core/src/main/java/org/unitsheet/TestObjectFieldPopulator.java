package org.unitsheet;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.ReadColumn;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.ColumnInfo;
import org.unitsheet.types.ObjectConverter;
import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static org.unitsheet.utils.ReflectionUtils.getGenericTypeClass;
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
            ReadCell readCell = field.getAnnotation(ReadCell.class);
            if (readCell != null) {
                handleReadCellAnnotation(testObject, field, readCell);
            }

            ReadColumn readColumn = field.getAnnotation(ReadColumn.class);
            if (readColumn != null) {
                handleReadColumnAnnotation(testObject, field, readColumn);
            }
        }

    }

    private void handleReadColumnAnnotation(Object testObject, Field field, ReadColumn readColumn) {
        String sheetName = readColumn.sheet();
        String from = readColumn.from();
        String to = readColumn.to();

        CellInfo fromCellInfo = CellInfo.builder().withName(from).withSheetName(sheetName).build();
        CellInfo toCellInfo = CellInfo.builder().withName(to).withSheetName(sheetName).build();

        ColumnInfo columnInfo = new ColumnInfo(sheetName, fromCellInfo, toCellInfo);

        List<Object> column = spreadsheet.getColumn(columnInfo);
        List<Object> results = new ArrayList<>();

        Class<?> collectionGenericTypeClass = getGenericTypeClass(field);

        for (Object o : column) {
            Object value = objectConverter.convertType(o, collectionGenericTypeClass);
            results.add(value);
        }

        try {
            setObjectFieldValue(field, testObject, results);
        } catch (IllegalAccessException | SecurityException e) {
            throw new RuntimeException(); // TODO - handle me
        }
    }

    private void handleReadCellAnnotation(Object testObject, Field field, ReadCell readCell) {
        Object cellValue = resolveCellValue(readCell);
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

        CellInfo cellInfo = CellInfo.builder().withName(cellName).withSheetName(sheetName).build();

        return spreadsheet.getCellValue(cellInfo);
    }
}
