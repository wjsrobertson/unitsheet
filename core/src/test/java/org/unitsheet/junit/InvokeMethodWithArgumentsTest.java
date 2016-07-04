package org.unitsheet.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InvokeMethodWithArgumentsTest {

    class SomeClass {}

    @Mock
    private FrameworkMethod testMethod;

    @Test
    public void checkEvaluate() throws Throwable {
        /*
         given an InvokeMethodWithArguments instance composed of a target and arguments
         */
        SomeClass target = new SomeClass();
        SomeClass[] arguments = new SomeClass[]{};
        InvokeMethodWithArguments underTest = new InvokeMethodWithArguments(testMethod, target, arguments);

        // when evaluate is called
        underTest.evaluate();

        // then invokeExplosively should have been called with arguments
        verify(testMethod).invokeExplosively(target, arguments);
    }
}