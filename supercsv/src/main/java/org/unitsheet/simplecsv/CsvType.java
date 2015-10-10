package org.unitsheet.simplecsv;

import org.unitsheet.api.adapter.WorksheetType;

import java.util.Collection;

import static org.unitsheet.utils.Collections.setOf;

/**
 */
public class CsvType implements WorksheetType {

    @Override
    public Collection<String> supportedExtensions() {
        return setOf("csv", "tsv");
    }

}
