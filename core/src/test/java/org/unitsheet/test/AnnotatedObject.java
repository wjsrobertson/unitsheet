package org.unitsheet.test;

import org.unitsheet.annotations.Cell;
import org.unitsheet.annotations.Workbook;

@Workbook("C:\\temp\\test.csv")
public class AnnotatedObject {

    @Cell("private")
    private String privateStringField = "prvt";

    @Cell("protected")
    protected String protectedStringField = "prtctd";

    @Cell("default")
    String defaultStringField = "dflt";

    @Cell("public")
    public String publicStringField = "pblc";

}
