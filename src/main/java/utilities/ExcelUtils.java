package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	public static final String EXCEL_FILE_PATH = "src/test/resources/testData/TestData.xlsx";
	
	/**
	 * Reads data from an Excel file and returns it as a 2D array.
	 * 
	 * @param filePath the path to the Excel file
	 * @param sheetName the name of the sheet to read from
	 * @return a 2D array containing the data from the specified sheet
	 */
	public static Object[][] readExcelData(String sheetName) {
		// Implementation for reading Excel data goes here
		try(FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH)) {
			// Create a workbook instance
			Workbook workbook = WorkbookFactory.create(fis);
			// Get the specified sheet
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}
			
			// Determine the number of rows and columns
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
			Object[][] data = new Object[rowCount - 1][colCount];
			
			// Read data from the sheet
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = cell.toString(); // Convert cell value to string
				}
			}
			
			return data;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Object[0][0]; // Placeholder return statement
	}
	/**
	 * Writes data to an Excel file.
	 * 
	 * @param filePath the path to the Excel file
	 * @param sheetName the name of the sheet to write to
	 * @param data the 2D array containing the data to write
	 */
	

}
