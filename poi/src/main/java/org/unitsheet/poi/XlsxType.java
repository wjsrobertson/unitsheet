package org.unitsheet.poi;

import org.unitsheet.api.adapter.WorksheetType;

import java.util.Collection;

import static org.unitsheet.utils.Collections.setOf;

public class XlsxType implements WorksheetType {

    @Override
    public Collection<String> supportedExtensions() {
        return setOf ("xlsx");
    }

}
