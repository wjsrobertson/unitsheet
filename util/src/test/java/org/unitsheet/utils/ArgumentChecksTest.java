package org.unitsheet.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentChecksTest {

    @Test
    public void checkThatCheckNotNullThrowsNPEWhenParameterIsNull() throws Exception {
        try {
            ArgumentChecks.checkNotNull(null, "message");
            fail("Exception should be thrown");
        } catch (NullPointerException e) {
            assertThat(e.getMessage()).contains("message");
        }
    }

    @Test
    public void checkThatCheckNotNullDoesNotThrowNPEWhenParameterIsNotNull() throws Exception {
        try {
            ArgumentChecks.checkNotNull("some not null field", "message");
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}