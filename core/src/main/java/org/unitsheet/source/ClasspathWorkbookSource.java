package org.unitsheet.source;

import java.io.BufferedInputStream;
import java.io.InputStream;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;


public class ClasspathWorkbookSource implements WorkbookSource {

    private final String path;

    public ClasspathWorkbookSource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public InputStream getInputStream() {
        String cleanPath = ensurePathStartwithSlash();
        InputStream inputStream = ClasspathWorkbookSource.class.getResourceAsStream(cleanPath);
        checkNotNull(inputStream, "null InputStream for " + cleanPath);

        return new BufferedInputStream(inputStream);
    }

    private String ensurePathStartwithSlash() {
        return "/" + path.replaceAll("^/", "");
    }

}
