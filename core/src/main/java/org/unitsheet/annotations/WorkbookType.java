package org.unitsheet.annotations;

import org.unitsheet.api.adapter.WorksheetType;

public @interface WorkbookType {

    Class<? extends WorksheetType> value();

}
