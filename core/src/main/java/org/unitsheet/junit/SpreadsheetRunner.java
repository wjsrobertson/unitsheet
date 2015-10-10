package org.unitsheet.junit;

import org.unitsheet.TestContext;
import org.unitsheet.TestManager;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 */
public class SpreadsheetRunner extends BlockJUnit4ClassRunner {

    private TestContext testContext;

    public SpreadsheetRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        testContext = createTestManager().createTestContext(testClass);
    }

    // This method is here for testing. JUnit controls lifecycle and instantiation of this class so it is not possible
    // to supply dependencies as constructor arguments. This method may be overridden in tests to allow use of a mock
    // TestManager.
    protected TestManager createTestManager() {
        return new TestManager();
    }

    @Override
    @SuppressWarnings("deprecation")
    protected Statement withBefores(FrameworkMethod method, Object testInstance, Statement statement) {
        testContext.getPopulator().populateFieldsWithValuesFromSpreadsheet(testInstance);

        return super.withBefores(method, testInstance, statement);
    }
}
