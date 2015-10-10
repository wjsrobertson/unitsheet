package org.unitsheet.annotations;

import org.unitsheet.api.adapter.WorksheetType;

public @interface ForceWorkbookType {

    Class<? extends WorksheetType> value();

}
