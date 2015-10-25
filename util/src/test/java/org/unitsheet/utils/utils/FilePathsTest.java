package org.unitsheet.utils.utils;

import org.junit.Test;

import static org.unitsheet.utils.utils.FilePaths.fileExtension;
import static org.junit.Assert.assertEquals;

public class FilePathsTest {

    @Test
    public void checkFileExtensionHappyPath() {
        assertEquals("txt", fileExtension("test.txt"));
    }

    @Test
    public void checkFileExtensionWithNothingAfterDotIsEmptyString() {
        assertEquals("", fileExtension("test."));
    }

    @Test
    public void checkFileExtensionsIsNullWhenFileExtensionAbsent() {
        assertEquals(null, fileExtension("test"));
    }

    @Test
    public void checkFileExtensionsIsNullWhenFilenameNull() {
        assertEquals(null, fileExtension(null));
    }

}
