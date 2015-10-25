package org.unitsheet.utils.utils;

public final class ArgumentChecks {

    private ArgumentChecks(){} // non-instantiable

    public static void checkNotNull(Object argument, String message) {
        if (argument == null) {
            throw new NullPointerException(message);
        }
    }

}
