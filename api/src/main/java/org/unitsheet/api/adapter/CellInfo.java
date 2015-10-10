package org.unitsheet.api.adapter;

public class CellInfo {

    private final String sheetName;
    private final String name;
    private final Integer row;
    private final Integer column;

    public CellInfo(String sheetName, String name, Integer row, Integer column) {
        this.sheetName = sheetName;
        this.name = name;
        this.row = row;
        this.column = column;
    }

    public String getSheetName() {
        return sheetName;
    }

    public String getName() {
        return name;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }
}
