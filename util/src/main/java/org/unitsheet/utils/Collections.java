package org.unitsheet.utils;

import java.lang.reflect.Array;
import java.util.*;

public class Collections {

    private Collections(){} // non-instantiable

    @SafeVarargs
    public static <T> Set<T> setOf(T... elements) {
        Set<T> set = new HashSet<>();
        set.addAll(Arrays.asList(elements));

        return java.util.Collections.unmodifiableSet(set);
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... elements) {
        List<T> list = Arrays.asList(elements);

        return java.util.Collections.unmodifiableList(list);
    }
}
