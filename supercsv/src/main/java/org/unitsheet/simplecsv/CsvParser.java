package org.unitsheet.simplecsv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CsvParser {

    public String determineNewLineType(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream);

        char[] buffer = new char[1024];
        int read;
        boolean lastCharWasCarriageReturn = false;
        String newlineChars = null;
        do {
            read = reader.read(buffer);

            for (int i=0 ; i<read ; i++) {
                char c = buffer[i];

                if (lastCharWasCarriageReturn) {
                    if (c == '\n') {
                        newlineChars = "\r\n";
                        break;
                    } else {
                        newlineChars = "\r";
                        break;
                    }
                } else if (c == '\n') {
                    newlineChars = "\n";
                    break;
                } else if (c == '\r') {
                    lastCharWasCarriageReturn = true;
                }
            }
        } while (read > 0);

        return newlineChars;
    }

}
