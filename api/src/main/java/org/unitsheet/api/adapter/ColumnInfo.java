package org.unitsheet.api.adapter;

import java.util.Objects;

// TODO - use Builder
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

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ColumnInfo that = (ColumnInfo) other;

        return Objects.equals(sheetName, that.sheetName) &&
                Objects.equals(fromCellInfo, that.fromCellInfo) &&
                Objects.equals(toCellInfo, that.toCellInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheetName, fromCellInfo, toCellInfo);
    }
}
