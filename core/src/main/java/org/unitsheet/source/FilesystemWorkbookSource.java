package org.unitsheet.source;

import java.io.*;

public class FilesystemWorkbookSource implements WorkbookSource {

    private final String path;

    public FilesystemWorkbookSource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new BufferedInputStream(new FileInputStream(new File(path)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // TODO - tidy me
        }
    }

}
