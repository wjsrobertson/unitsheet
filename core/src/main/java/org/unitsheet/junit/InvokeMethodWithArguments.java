package org.unitsheet.junit;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class InvokeMethodWithArguments extends Statement {

    private final FrameworkMethod testMethod;
    private final Object target;
    private final Object[] arguments;

    public InvokeMethodWithArguments(FrameworkMethod testMethod, Object target, Object[] arguments) {
        this.testMethod = testMethod;
        this.target = target;
        this.arguments = arguments;
    }

    @Override
    public void evaluate() throws Throwable {
        testMethod.invokeExplosively(target, arguments);
    }

}
