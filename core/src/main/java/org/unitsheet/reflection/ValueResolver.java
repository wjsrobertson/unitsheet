package org.unitsheet.reflection;

import org.unitsheet.api.adapter.SpreadsheetAdapter;

public interface ValueResolver<T> {

    Class<?> getAnnotationType();

    Object resolveValue(T annotation, Class<?> destType, SpreadsheetAdapter spreadsheet);

}
