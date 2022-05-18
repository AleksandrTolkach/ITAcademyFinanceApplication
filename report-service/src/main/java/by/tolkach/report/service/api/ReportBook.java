package by.tolkach.report.service.api;

import by.tolkach.report.dto.reportParam.ExtendedParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReportBook {

    public void createBook(ExtendedParam param, String reportName) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(reportName);
        DataFormat format = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();

        try {
            workbook.write(new FileOutputStream("/home/hoho/dev/" + reportName + LocalDateTime.now()));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
