package by.tolkach.report.service;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.ReportType;
import by.tolkach.report.service.api.IBookService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService implements IBookService {

    public void createBook(List<Operation> operations, ReportType reportType) {

        int[] cells = new int[]{0, 1, 5};

        if (reportType.name().equals(ReportType.BY_DATE.name())) {
            cells[0] = 1;
            cells[1] = 0;
        } else if (reportType.name().equals(ReportType.BY_CATEGORY.name())) {
            cells[0] = 5;
            cells[2] = 0;
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(reportType.name());
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        createHeader(sheet, cells);
        for (Operation operation: operations) {
            createBody(sheet, format, cellStyle, operations.indexOf(operation) + 1, operation, cells);
        }

        try {
            workbook.write(new FileOutputStream("/home/hoho/dev/report_"+ reportType.name().toLowerCase()
                    + "_" + LocalDateTime.now() + ".xlsx"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createHeader(Sheet sheet, int[] cells) {
        Row row = sheet.createRow(0);

        Cell accountId = row.createCell(cells[0]);
        accountId.setCellValue("accountId");

        Cell date = row.createCell(cells[1]);
        date.setCellValue("date");

        Cell value = row.createCell(2);
        value.setCellValue("value");

        Cell currency = row.createCell(3);
        currency.setCellValue("currency");

        Cell description = row.createCell(4);
        description.setCellValue("description");

        Cell category = row.createCell(cells[2]);
        category.setCellValue("category");
    }

    public static void createBody(Sheet sheet, DataFormat format, CellStyle cellStyle, int rowNum, Operation operation,
                                  int[] cells) {
        cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));
        Row row = sheet.createRow(rowNum);

        Cell accountId = row.createCell(cells[0]);
        accountId.setCellValue(operation.getAccount().toString());

        Cell date = row.createCell(cells[1]);
        date.setCellStyle(cellStyle);
        date.setCellValue(operation.getDate().toString());

        Cell value = row.createCell(2);
        value.setCellValue(operation.getValue());

        Cell currency = row.createCell(3);
        currency.setCellValue(operation.getCurrency().toString());

        Cell description = row.createCell(4);
        description.setCellValue(operation.getDescription());

        Cell category = row.createCell(cells[2]);
        category.setCellValue(operation.getCategory().toString());
    }
}
