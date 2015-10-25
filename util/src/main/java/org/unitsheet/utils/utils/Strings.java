package org.unitsheet.utils.utils;

public class Strings {

    private Strings(){} // non-instantiable

    private static final String EMPTY_STRING = "";

    public static boolean isNotEmpty(String string) {
        return ! isEmpty(string);
    }

    public static boolean isEmpty(String string) {
        return string == null || EMPTY_STRING.equals(string);
    }

}
