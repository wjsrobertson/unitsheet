package org.unitsheet.simplecsv;

import org.unitsheet.api.adapter.SpreadsheetAdapter;
import org.unitsheet.api.adapter.SpreadsheetProvider;
import org.unitsheet.api.adapter.WorksheetType;

import java.io.*;

/**
 */
public class SuperCsvSpreadsheetProvider implements SpreadsheetProvider {

    @Override
    public WorksheetType worksheetType() {
        return null;
    }

    @Override
    public SpreadsheetAdapter createSpreadsheetAdapter(InputStream inputStream) throws IOException {
        return null;
    }

    private void build(InputStream inputStream) throws IOException {
        /*
        ICsvListReader listReader = null;
        try {
            Reader reader = null;
            try {
                reader = new InputStreamReader(inputStream, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO - ignore
            }

            listReader = new CsvListReader(reader, CsvPreference.EXCEL_PREFERENCE);

            listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
            final CellProcessor[] processors = getProcessors();

            List<Object> customerList;
            while ((customerList = listReader.read(processors)) != null) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
                        listReader.getRowNumber(), customerList));
            }

        } finally {
            if (listReader != null) {
                listReader.close();
            }
        }
        */
    }

}
