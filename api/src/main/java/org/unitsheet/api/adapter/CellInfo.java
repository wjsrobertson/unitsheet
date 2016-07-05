package org.unitsheet.api.adapter;

import java.util.Objects;

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

    public static Builder builder() {
        return new Builder();
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

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof CellInfo)) {
            return false;
        }

        CellInfo cellInfo = (CellInfo) other;

        return Objects.equals(sheetName, cellInfo.sheetName) &&
                Objects.equals(name, cellInfo.name) &&
                Objects.equals(row, cellInfo.row) &&
                Objects.equals(column, cellInfo.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheetName, name, row, column);
    }

    public static class Builder {
        private String sheetName;
        private String name;
        private Integer row;
        private Integer column;

        public CellInfo build() {
            return new CellInfo(sheetName, name, row, column);
        }

        public Builder withSheetName(String sheetName) {
            this.sheetName = sheetName;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRow(Integer row) {
            this.row = row;
            return this;
        }

        public Builder withColumn(Integer column) {
            this.column = column;
            return this;
        }
    }
}
