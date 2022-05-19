package by.tolkach.report.service;

import by.tolkach.report.dto.Operation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookByDateService {

    public void createBook(List<Operation> operations) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("reportByDate");
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        createHeader(sheet);
        for (Operation operation: operations) {
            createBody(sheet, format, cellStyle, operations.indexOf(operation) + 1, operation);
        }

        try {
            workbook.write(new FileOutputStream("/home/hoho/dev/reportByDate" + LocalDateTime.now() + ".xlsx"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        Cell date = row.createCell(0);
        date.setCellValue("date");

        Cell accountId = row.createCell(1);
        accountId.setCellValue("accountId");

        Cell value = row.createCell(2);
        value.setCellValue("value");

        Cell currency = row.createCell(3);
        currency.setCellValue("currency");

        Cell description = row.createCell(4);
        description.setCellValue("description");

        Cell category = row.createCell(5);
        category.setCellValue("category");
    }

    public static void createBody(Sheet sheet, DataFormat format, CellStyle cellStyle, int rowNum, Operation operation) {
        cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));
        Row row = sheet.createRow(rowNum);
        Cell date = row.createCell(0);
        date.setCellStyle(cellStyle);
        date.setCellValue(operation.getDate().toString());

        Cell accountId = row.createCell(1);
        accountId.setCellValue(operation.getAccount().toString());

        Cell value = row.createCell(2);
        value.setCellValue(operation.getValue());

        Cell currency = row.createCell(3);
        currency.setCellValue(operation.getCurrency().toString());

        Cell description = row.createCell(4);
        description.setCellValue(operation.getDescription());

        Cell category = row.createCell(5);
        category.setCellValue(operation.getCategory().toString());
    }
}
