package util;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelUtil {
	public void activateSheet(String sheetName);

	public void activateSheet(int sheetNumber);

	public List<Row> getRowsCollection() throws IOException;

	public List<Row> getRowsCollection(String sheetName) throws IOException;

	public List<Row> getRowsCollection(int sheetNumber) throws IOException;

	public List<Row> getEntireRowsCollection() throws IOException;

	public int getSheetCount() throws IOException;

	public String[] getSheetNames() throws IOException;

	public void closeWorkbook() throws IOException;

	public Workbook getWorkbook() throws IOException;

	public int getRowCount();

	public int getRowCount(String sheetName);

	public int getColumnCount();

	public int getColumnCount(String sheetName);

	public int getColumnCount(int rowNumber);

	public String getCell(int rowNumber, int columnNumber);

	public String getCell2(int rowNumber, int columnNumber);

	public double getNumericCell(int rowNumber, int columnNumber);

	public Row getRow(int rowNumber);

	public boolean doesSheetExist(String sheetName);

	public String getCellData(String sheetName, String columnName, int rowNumber);

	public String getCellData(String sheetName, int columnNumber, int rowNumber);

	public boolean setCellData(String sheetName, String columnName,
			int rowNumber, String data);

	public boolean setCellData(String sheetName, String columnName,
			int rowNumber, String data, String url);

	public boolean addColumn(String sheetName, String columnName);

	public boolean addSheet(String sheetName);

	public boolean addHyperLink(String sheetName, String screenShotColName,
			String testCaseName, int index, String url, String message);

	public int getCellRowNum(String sheetName, String columnName,
			String cellValue);

	public boolean removeSheet(String sheetName);

	public boolean setActiveSheet(String sheetName);

	public boolean removeColumn(String sheetName, int columnNumber);
}
