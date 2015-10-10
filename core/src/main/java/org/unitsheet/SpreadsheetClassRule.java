package org.unitsheet;

import org.junit.ClassRule;

import java.lang.annotation.Annotation;

public class SpreadsheetClassRule implements ClassRule {

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

}
