package org.unitsheet.junit;

import org.unitsheet.TestContext;
import org.unitsheet.TestManager;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class SpreadsheetRule implements MethodRule {

    /*
        This method is here for testing purposes.
        SpreadsheetRule gets instantiated by users from within Junit tests, so there is no scope to inject the
        TestManager dependency.
        This method may be overridden in tests to allow use of a mock TestManager.
    */
    protected TestManager createTestManager() {
        return new TestManager();
    }

    @Override
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object testObject) {
        TestContext testContext = createTestManager().createTestContext(testObject.getClass());
        testContext.getPopulator().populateFieldsWithValuesFromSpreadsheet(testObject);

        return statement;
    }
}
