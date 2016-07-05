package org.unitsheet.api.adapter;

import java.util.List;

/**
 * Adapter around underlying spreadsheet implementation - POI etc.
 */
public interface SpreadsheetAdapter {

    /**
     * Returns {@code Object} instances to make life easy for the provider implementations.
     */
    Object getCellValue(CellInfo cellInfo);

    List<Object> getColumn(ColumnInfo columnInfo);

}
