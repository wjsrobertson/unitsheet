package org.unitsheet.test;

import org.unitsheet.annotations.ReadCell;
import org.unitsheet.annotations.Workbook;

@Workbook("C:\\temp\\test.csv")
public class AnnotatedObject {

    @ReadCell("private")
    private String privateStringField = "prvt";

    @ReadCell("protected")
    protected String protectedStringField = "prtctd";

    @ReadCell("default")
    String defaultStringField = "dflt";

    @ReadCell("public")
    public String publicStringField = "pblc";

}
