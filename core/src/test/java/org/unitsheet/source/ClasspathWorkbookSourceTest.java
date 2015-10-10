package org.unitsheet.source;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class ClasspathWorkbookSourceTest {

    private ClasspathWorkbookSource underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new ClasspathWorkbookSource("/text-files/test.txt");
    }

    @Test
    public void checkFileCanStreamFromClasspath() throws IOException {
        BufferedReader reader = new BufferedReader (new InputStreamReader(underTest.getInputStream()));

        assertEquals("test file contents", reader.readLine());
    }


}
