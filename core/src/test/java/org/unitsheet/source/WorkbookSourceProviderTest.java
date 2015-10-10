package org.unitsheet.source;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkbookSourceProviderTest {

    private final WorkbookSourceProvider underTest = new WorkbookSourceProvider();

    @Test
    public void checkClasspathPrefixMeansClasspathWorkbooksourceReturned() {
        WorkbookSource workbookSource = underTest.createWorkbookSourceForPath("classpath:testpath");
        assertTrue(workbookSource instanceof ClasspathWorkbookSource);
    }

    @Test
    public void checkClasspathPrefixStrippedFromPath() {
        WorkbookSource workbookSource = underTest.createWorkbookSourceForPath("classpath:testpath");
        assertEquals("testpath", workbookSource.getPath());
    }

    @Test
    public void checkNoClasspathPrefixMeansFileWorkbooksourceReturned() {
        WorkbookSource workbookSource = underTest.createWorkbookSourceForPath("testpath");
        assertTrue(workbookSource instanceof FilesystemWorkbookSource);
    }

}
