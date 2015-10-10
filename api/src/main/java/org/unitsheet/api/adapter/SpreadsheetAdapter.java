package org.unitsheet.api.adapter;

import java.util.List;

/**
 * Adapter around underlying spreadsheet implementation - POI etc.
 */
public interface SpreadsheetAdapter {

    /**
     * Returns {@code Object} instances to make life easy for the provider implementations.
     */
    public Object getCellValue(CellInfo cellInfo);

    public List<Object> getRange(String startCellName, String endCellName, String sheetName);

}
