package org.unitsheet.reflection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.ReadColumn;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestMethodArgumentValidatorTest {

    @InjectMocks
    private TestMethodArgumentValidator underTest;

    static class HasValidCellArgs {
        @Test
        public void validTestMethod(@ReadCell("A1") int x, @ReadCell("A2") String y) {
        }
    }

    static class HasValidColumnArg {
        @Test
        public void validTestMethod(@ReadColumn(from="A1", to="B1") List<Integer> x) {
        }
    }

    static class HasInNoAnnotationArg {
        @Test
        public void validTestMethod(int x) {
        }
    }

    @Test
    public void checkHasValidArgumentsForCellArgs() throws NoSuchMethodException {
        Method validTestMethod = HasValidCellArgs.class.getDeclaredMethods()[0];

        boolean valid = underTest.hasValidArguments(validTestMethod);
        assertThat(valid).isTrue();
    }

    @Test
    public void checkHasValidArgumentsForColumnArgs() throws NoSuchMethodException {
        Method validTestMethod = HasValidColumnArg.class.getDeclaredMethods()[0];

        boolean valid = underTest.hasValidArguments(validTestMethod);
        assertThat(valid).isTrue();
    }

    @Test
    public void checkHasInvalidArgumentsForNonAnnotatedArg() throws NoSuchMethodException {
        Method validTestMethod = HasInNoAnnotationArg.class.getDeclaredMethods()[0];

        boolean valid = underTest.hasValidArguments(validTestMethod);
        assertThat(valid).isFalse();
    }
}