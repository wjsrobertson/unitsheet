package org.unitsheet.source;

import static org.unitsheet.utils.ArgumentChecks.checkNotNull;

public class WorkbookSourceProvider {

    private static final String CLASSPATH_PREFIX = "classpath:";

    public WorkbookSource createWorkbookSourceForPath(String path) {
        checkNotNull("null workbook path provided", path);

        if (path.startsWith(CLASSPATH_PREFIX)) {
            String actualPath = path.replaceFirst(CLASSPATH_PREFIX, "");
            return new ClasspathWorkbookSource(actualPath);
        } else {
            return new FilesystemWorkbookSource(path);
        }
    }

}
