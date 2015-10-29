package org.unitsheet.api.adapter;

public class ColumnInfo {

    private final String sheetName;

    private final CellInfo fromCellInfo;

    private final CellInfo toCellInfo;

    public ColumnInfo(String sheetName, CellInfo fromCellInfo, CellInfo toCellInfo) {
        this.sheetName = sheetName;
        this.fromCellInfo = fromCellInfo;
        this.toCellInfo = toCellInfo;
    }

    public String getSheetName() {
        return sheetName;
    }

    public CellInfo getFromCellInfo() {
        return fromCellInfo;
    }

    public CellInfo getToCellInfo() {
        return toCellInfo;
    }
}
