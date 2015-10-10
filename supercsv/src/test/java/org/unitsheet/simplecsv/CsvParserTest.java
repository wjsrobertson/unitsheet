package org.unitsheet.simplecsv;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CsvParserTest {

    CsvParser parser = new CsvParser();

    @Test
    public void checkDetermineNewLineTypeForN() throws IOException {
        String newlineSeperated = "banana\napple\npear";

        String newLine = parser.determineNewLineType(stringToInputStream(newlineSeperated));
        assertEquals("\n", newLine);
    }

    @Test
    public void checkDetermineNewLineTypeForR() throws IOException {
        String returnSeperated = "quick\rbrown\rfox";

        String newLine = parser.determineNewLineType(stringToInputStream(returnSeperated));
        assertEquals("\r", newLine);
    }

    @Test
    public void checkDetermineNewLineTypeForRN() throws IOException {
        String crSeperated = "quick\r\nbrown\r\nfox";

        String newLine = parser.determineNewLineType(stringToInputStream(crSeperated));
        assertEquals("\r\n", newLine);
    }

    @Test
    public void checkDetermineNewLineTypeNullWhenNoNewlines() throws IOException {
        String crSeperated = "quick";

        String newLine = parser.determineNewLineType(stringToInputStream(crSeperated));
        assertNull(newLine);
    }

    private InputStream stringToInputStream(String string) {
        try {
            return new ByteArrayInputStream(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException cantHappenForUtf8) {
           throw new RuntimeException(cantHappenForUtf8);
        }
    }

}
