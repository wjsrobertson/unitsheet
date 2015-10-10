package org.unitsheet;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SpreadsheetTestRule implements TestRule {

    @Override
    public Statement apply(Statement statement, Description description) {
        return null;
    }
}
