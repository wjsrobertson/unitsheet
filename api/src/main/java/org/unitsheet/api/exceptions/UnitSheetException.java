package org.unitsheet.api.exceptions;

public class UnitSheetException extends RuntimeException {

    public UnitSheetException() {
    }

    public UnitSheetException(String message) {
        super(message);
    }

    public UnitSheetException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnitSheetException(Throwable cause) {
        super(cause);
    }

    public UnitSheetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
