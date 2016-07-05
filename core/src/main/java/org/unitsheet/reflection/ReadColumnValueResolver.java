package org.unitsheet.reflection;

import org.unitsheet.annotations.ReadColumn;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.ColumnInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.types.ObjectConverter;

import java.util.ArrayList;
import java.util.List;

public class ReadColumnValueResolver implements ValueResolver<ReadColumn> {

    private final ObjectConverter objectConverter;

    public ReadColumnValueResolver(ObjectConverter objectConverter) {
        this.objectConverter = objectConverter;
    }

    @Override
    public Class<?> getAnnotationType() {
        return ReadColumn.class;
    }

    @Override
    public Object resolveValue(ReadColumn annotation, Class<?> destType, SpreadsheetAdapter spreadsheet) {
        List<Object> column = getRawValues(annotation, spreadsheet);
        return convertToDestType(destType, column);
    }

    private List<Object> getRawValues(ReadColumn annotation, SpreadsheetAdapter spreadsheet) {
        ColumnInfo columnInfo = convertToColumnInfo(annotation);
        return spreadsheet.getColumn(columnInfo);
    }

    private List<Object> convertToDestType(Class<?> destType, List<Object> column) {
        List<Object> results = new ArrayList<>();

        for (Object rawValue : column) {
            Object value = objectConverter.convertType(rawValue, destType);
            results.add(value);
        }
        return results;
    }

    private ColumnInfo convertToColumnInfo(ReadColumn annotation) {
        String sheetName = annotation.sheet();
        String from = annotation.from();
        String to = annotation.to();

        CellInfo fromCellInfo = CellInfo.builder().withName(from).withSheetName(sheetName).build();
        CellInfo toCellInfo = CellInfo.builder().withName(to).withSheetName(sheetName).build();

        return new ColumnInfo(sheetName, fromCellInfo, toCellInfo);
    }
}
