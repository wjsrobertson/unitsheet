package org.unitsheet.simpleodf;

import org.unitsheet.api.adapter.WorksheetType;

import java.util.Collection;

import static org.unitsheet.utils.Collections.setOf;

public class OdsType implements WorksheetType {
    @Override
    public Collection<String> supportedExtensions() {
        return setOf("ods");
    }
}
