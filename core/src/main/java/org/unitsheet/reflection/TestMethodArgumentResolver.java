package org.unitsheet.reflection;

import org.unitsheet.api.adapter.SpreadsheetAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.unitsheet.utils.ReflectionUtils.determineType;

public class TestMethodArgumentResolver {

    private final SpreadsheetAdapter spreadsheet;

    private final Set<ValueResolver> valueResolvers;

    public TestMethodArgumentResolver(SpreadsheetAdapter spreadsheet, Set<ValueResolver> valueResolvers) {
        this.spreadsheet = spreadsheet;
        this.valueResolvers = valueResolvers;
    }

    @SuppressWarnings("unchecked")
    public Object[] resolveArguments(Method method) {
        Parameter[] parameters = method.getParameters();
        List<Object> results = new ArrayList<>();

        for (Parameter parameter : parameters) {
            for (ValueResolver valueResolver : valueResolvers) {
                Annotation annotation = parameter.getAnnotation(valueResolver.getAnnotationType());
                if (annotation != null) {
                    Class<?> destType = determineType(parameter);
                    Object result = valueResolver.resolveValue(annotation, destType, spreadsheet);

                    results.add(result);
                }
            }
        }

        return results.toArray();
    }


}
