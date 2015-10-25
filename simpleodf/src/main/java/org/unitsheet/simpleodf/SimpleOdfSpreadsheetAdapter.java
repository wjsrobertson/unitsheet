package org.unitsheet.simpleodf;

import org.unitsheet.api.adapter.CellInfo;
import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.CellRange;
import org.odftoolkit.simple.table.Table;
import org.unitsheet.api.exceptions.InvalidColumnRangeException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.unitsheet.utils.Strings.isNotEmpty;

public class SimpleOdfSpreadsheetAdapter implements SpreadsheetAdapter {

    private final SpreadsheetDocument spreadsheetDocument;

    public SimpleOdfSpreadsheetAdapter(InputStream inputStream) {
        try {
            spreadsheetDocument = SpreadsheetDocument.loadDocument(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e); // TODO - handle me
        }
    }

    @Override
    public Object getCellValue(CellInfo cellInfo) {
        Table sheet = resolveSheet(cellInfo.getSheetName());

        Cell cell;
        if (cellInfo.getRow() != null && cellInfo.getColumn() != null) {
            cell = sheet.getCellByPosition(cellInfo.getColumn(), cellInfo.getRow());
        } else {
            cell = sheet.getCellByPosition(cellInfo.getName());
        }

        return cell.getStringValue();
    }

    private Table resolveSheet(String sheetName) {
        Table sheet;
        if (isNotEmpty(sheetName)) {
            sheet = spreadsheetDocument.getSheetByName(sheetName);
        } else {
            sheet = spreadsheetDocument.getSheetByIndex(0);
        }
        return sheet;
    }

    @Override
    public List<Object> getColumn(CellInfo start, CellInfo end, String sheetName) {
        Table sheet = resolveSheet(sheetName);

        CellRange cellRangeByPosition = sheet.getCellRangeByPosition(start.getName(), start.getName());
        int numCols = cellRangeByPosition.getColumnNumber();

        if (numCols > 1) {
            throw new InvalidColumnRangeException("Too many columns: " + numCols); // TODO - add some detail
        }

        int numRows = cellRangeByPosition.getRowNumber();

        List<Object> objects = new ArrayList<>();
        for (int i=0 ; i<numRows ; i++) {
            Cell cellByPosition = cellRangeByPosition.getCellByPosition(numCols, i);
            objects.add(objects);
        }

        return objects;
    }

}
