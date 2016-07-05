package org.unitsheet.junit;

import org.junit.Test;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.unitsheet.TestContext;
import org.unitsheet.TestManager;
import org.unitsheet.reflection.TestMethodArgumentValidator;

import java.lang.reflect.Method;
import java.util.List;

public class SpreadsheetRunner extends BlockJUnit4ClassRunner {

    private TestContext testContext;

    public SpreadsheetRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        testContext = createTestManager().createTestContext(testClass);
    }

    protected TestManager createTestManager() {
        return new TestManager();
    }

    protected TestMethodArgumentValidator getTestMethodArgumentValidator() {
        return new TestMethodArgumentValidator();
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Statement withBefores(FrameworkMethod method, Object testInstance, Statement statement) {
        testContext.getFieldPopulator().populateFieldsWithValuesFromSpreadsheet(testInstance);

        return super.withBefores(method, testInstance, statement);
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        if (method.getMethod().getParameters().length > 0) {
            Object[] arguments = testContext.getArgumentResolver().resolveArguments(method.getMethod());
            return new InvokeMethodWithArguments(method, test, arguments);
        } else {
            return new InvokeMethod(method, test);
        }
    }

    @Override
    protected void validateTestMethods(List<Throwable> errors) {
        validateTestMethodArguments(errors);
    }

    // TODO - hacked in, needs test
    private void validateTestMethodArguments(List<Throwable> errors) {
        List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(Test.class);

        for (FrameworkMethod frameworkMethod : methods) {
            Method method = frameworkMethod.getMethod();
            boolean hasValidArguments = getTestMethodArgumentValidator().hasValidArguments(method);
            if (!hasValidArguments) {
                String msg = "Method " + frameworkMethod.getName() + " has parameters without @ReadCell or @ReadColumn";
                errors.add(new Exception(msg));
            }
        }
    }
}
