package org.unitsheet.source;

import java.io.InputStream;

public interface WorkbookSource {

    String getPath();

    InputStream getInputStream();

}
