package org.unitsheet.utils;

import java.util.*;

public class Collections {

    private Collections(){} // non-instantiable

    @SafeVarargs
    public static <T> Set<T> setOf(T... elements) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(elements));
        return set;
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... elements) {
        List<T> set = new ArrayList<>();
        set.addAll(Arrays.asList(elements));
        return set;
    }

}
