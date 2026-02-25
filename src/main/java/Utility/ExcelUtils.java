package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

    static Workbook workbook;

    public static void loadExcelFile() {
        if (workbook == null) { // Prevents reloading if already loaded
            try (FileInputStream fis = new FileInputStream(ConfigReader.get("excelPath"))) {
                workbook = WorkbookFactory.create(fis);
            } catch (EncryptedDocumentException | IOException e) {
                System.err.println("Could not load Excel file: " + e.getMessage());
            }
        }
    }

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) return ""; // Handle empty row

            Cell cell = row.getCell(colNum);
            if (cell == null) return ""; // Handle empty cell

            // DataFormatter converts any cell type (Numeric, String, etc.) into a String
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
            
        } catch (Exception e) {
            return "";
        }
    }

    public static int getRowCount(String sheetName) {
        return workbook.getSheet(sheetName).getLastRowNum();
    }
}