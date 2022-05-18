package by.tolkach.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("reportByBalance");
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        createBody(sheet, format, cellStyle);
        createHeader(sheet);

        try {
            workbook.write(new FileOutputStream("/home/hoho/dev/test.xlsx"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        Cell accountId = row.createCell(0);
        accountId.setCellValue("accountId");

        Cell description = row.createCell(1);
        description.setCellValue("description");

        Cell value = row.createCell(2);
        value.setCellValue("value");

        Cell currency = row.createCell(3);
        currency.setCellValue("currency");

        Cell date = row.createCell(4);
        date.setCellValue("date");

        Cell category = row.createCell(5);
        category.setCellValue("category");
    }

    public static void createBody(Sheet sheet, DataFormat format, CellStyle cellStyle) {
        cellStyle.setDataFormat(format.getFormat("dd.mm.yyyy hh:mm:ss"));
        for (int i = 1; i < 3; i++) {
            Row row = sheet.createRow(i);
            Cell accountId = row.createCell(0);
            accountId.setCellValue("accountId");

            Cell description = row.createCell(1);
            description.setCellValue("description");

            Cell value = row.createCell(2);
            value.setCellValue(i + 100);

            Cell currency = row.createCell(3);
            currency.setCellValue("currency");

            Cell date = row.createCell(4);
            date.setCellStyle(cellStyle);
            date.setCellValue(new Date());

            Cell category = row.createCell(5);
            category.setCellValue("category");
        }
    }
}
