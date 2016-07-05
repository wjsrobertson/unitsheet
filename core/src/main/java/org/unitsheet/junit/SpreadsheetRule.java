package org.unitsheet.junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.unitsheet.TestContext;
import org.unitsheet.TestManager;

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

    @Override // MethodRule
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object testObject) {
        TestContext testContext = createTestManager().createTestContext(testObject.getClass());
        testContext.getFieldPopulator().populateFieldsWithValuesFromSpreadsheet(testObject);

        return statement;
    }

}
