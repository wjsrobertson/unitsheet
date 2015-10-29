package org.unitsheet.poi;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.util.List;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;
import static org.unitsheet.utils.Strings.isNotEmpty;

public class ApachePoiSpreadsheetAdapter implements SpreadsheetAdapter {

    private final Workbook workbook;

    public ApachePoiSpreadsheetAdapter(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public Object getCellValue(CellInfo cellInfo) {
        checkNotNull(cellInfo, "no cell information received");

        Sheet sheet = getSheetWithFirstByDefault(cellInfo.getSheetName());

        CellReference cellReference;
        if (cellInfo.getRow() != null && cellInfo.getColumn() != null) {
            cellReference = new CellReference(cellInfo.getRow(), cellInfo.getColumn());
        } else {
            cellReference = new CellReference(cellInfo.getName());
        }

        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());

        return getCellValue(cell);
    }

    private Sheet getSheetWithFirstByDefault(String sheetName) {
        int sheetIndex = 0;

        if (isNotEmpty(sheetName)) {
            sheetIndex = workbook.getSheetIndex(sheetName);
        }

        return workbook.getSheetAt(sheetIndex);
    }

    public Object getCellValue(Cell cell) {
        Object value = null;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
        }

        return value;
    }

    @Override
    public List<Object> getColumn(String sheetName, CellInfo start, CellInfo end) {
        return null;
    }

}
