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
