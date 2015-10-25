package org.unitsheet.utils.utils;

public class FilePaths {

    private FilePaths(){} // no instantiation

    public static String fileExtension(String filename) {
        if (Strings.isEmpty(filename)) {
            return null;
        }

        int beginIndex = filename.lastIndexOf('.');

        if (beginIndex >= 0) {
            int beginAfterDotIndex = beginIndex + 1;
            return filename.substring(beginAfterDotIndex);
        } else {
            return null;
        }
    }

}
