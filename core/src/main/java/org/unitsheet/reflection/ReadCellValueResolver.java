package org.unitsheet.reflection;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.types.ObjectConverter;

public class ReadCellValueResolver implements ValueResolver<ReadCell> {

    private final ObjectConverter objectConverter;

    public ReadCellValueResolver(ObjectConverter objectConverter) {
        this.objectConverter = objectConverter;
    }

    @Override
    public Class<?> getAnnotationType() {
        return ReadCell.class;
    }

    @Override
    public Object resolveValue(ReadCell annotation, Class<?> destType, SpreadsheetAdapter spreadsheet) {
        Object cellValue = getRawCellValue(annotation, spreadsheet);
        return objectConverter.convertType(cellValue, destType);
    }

    private Object getRawCellValue(ReadCell annotation, SpreadsheetAdapter spreadsheet) {
        CellInfo cellInfo = convertToCellInfo(annotation);
        return spreadsheet.getCellValue(cellInfo);
    }

    private CellInfo convertToCellInfo(ReadCell annotation) {
        String cellName = annotation.value();
        String sheetName = annotation.sheet();

        return CellInfo.builder()
                .withName(cellName)
                .withSheetName(sheetName)
                .build();
    }
}
