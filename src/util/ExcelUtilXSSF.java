package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The class ExcelUtil Contains core excel reading and writing functionality
 * implementations. Supported for .xlsx file extensions.
 * 
 * @author CSC
 * @version 1.0
 * @since 2016-06-30
 * 
 */

public class ExcelUtilXSSF implements ExcelUtil {

	public String path = "";
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	// Initializing private variables
	public ExcelUtilXSSF(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			/*
			 * if(doesSheetExist(sheetName)){ boolean setActiveSheetSatus =
			 * setActiveSheet(sheetName); if(!setActiveSheetSatus){
			 * System.out.println
			 * ("Unable to set the sheet '"+sheetName+"' as active.");
			 * System.exit(0); } } else{
			 * System.out.println("Excel doesn't contains the sheet by name '"
			 * +sheetName+"'."); System.exit(0); }
			 */
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExcelUtilXSSF(String path, String sheetName) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExcelUtilXSSF(String path, int sheetNumber) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(sheetNumber);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void activateSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}

	public void activateSheet(int sheetNumber) {
		sheet = workbook.getSheetAt(sheetNumber);
	}

	public List<Row> getRowsCollection() throws IOException {
		List<Row> filteredRows = new ArrayList<Row>();
		if (sheet != null) {

			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				Row row = (Row) rows.next();
				filteredRows.add(row);
			}
		}
		return filteredRows;
	}

	public List<Row> getRowsCollection(String sheetName) throws IOException {
		List<Row> filteredRows = new ArrayList<Row>();
		sheet = workbook.getSheet(sheetName);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			filteredRows.add(row);
		}
		return filteredRows;
	}

	public List<Row> getRowsCollection(int sheetNumber) throws IOException {
		List<Row> filteredRows = new ArrayList<Row>();
		sheet = workbook.getSheetAt(sheetNumber);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Row row = (Row) rows.next();
			filteredRows.add(row);
		}
		return filteredRows;
	}

	public List<Row> getEntireRowsCollection() throws IOException {
		// FileInputStream fis = new FileInputStream(new File(excelPath));
		// HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// HSSFSheet sheet = workbook.getSheetAt (0);
		// workbook.getNumberOfSheets();
		List<Row> filteredRows = new ArrayList<Row>();
		// ArrayList<List<HSSFRow>> TwoD = new ArrayList<List<HSSFRow>>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheet = workbook.getSheetAt(i);

			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				Row row = (Row) rows.next();
				filteredRows.add(row);
				/*
				 * Iterator<Cell> cells = row.cellIterator (); while
				 * (cells.hasNext ()){ HSSFCell cell = (HSSFCell) cells.next ();
				 * if (cell.toString().contains("GHH")) { filteredRows.add(row);
				 * } }
				 */
			}
		}
		return filteredRows;
	}

	public int getSheetCount() throws IOException {
		int sheetCount = 0;
		if (workbook != null) {
			sheetCount = workbook.getNumberOfSheets();
		}
		return sheetCount;
	}

	public String[] getSheetNames() throws IOException {
		int sheetCount = 0;
		String[] sheetNames = null;
		if (workbook != null) {
			sheetCount = workbook.getNumberOfSheets();
			sheetNames = new String[sheetCount];
		}
		for (int i = 0; i < sheetCount; i++) {
			sheetNames[i] = workbook.getSheetName(i);
		}
		return sheetNames;
	}

	public void closeWorkbook() throws IOException {
		// workbook.close();
	}

	public Workbook getWorkbook() throws IOException {
		return workbook;
	}

	/**
	 * Returns total number of rows present in an excel sheet if the specified
	 * named sheet is present in the given excel file.
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return the total number of rows present in the sheet
	 */
	public int getRowCount() {
		int rowCount = 0;
		if (sheet != null)
			rowCount = sheet.getLastRowNum() + 1;
		return rowCount;
	}

	public int getRowCount(String sheetName) {
		int rowCount = 0;
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet != null)
			rowCount = sheet.getLastRowNum() + 1;
		return rowCount;
	}

	/**
	 * Returns total number of columns present in an excel sheet if the
	 * specified named sheet is present in the given excel file.
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return the total number of columns present in the sheet
	 */
	public int getColumnCount() {
		int columnCount = 0;
		if (sheet != null) {
			row = sheet.getRow(0);
			if (row != null)
				columnCount = row.getLastCellNum();
		}
		return columnCount;
	}

	public int getColumnCount(String sheetName) {
		int columnCount = 0;
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if (sheet != null) {
			row = sheet.getRow(0);
			if (row != null)
				columnCount = row.getLastCellNum();
		}
		return columnCount;
	}

	public int getColumnCount(int rowNumber) {
		int columnCount = 0;
		if (sheet != null) {
			row = sheet.getRow(rowNumber);
			if (row != null)
				columnCount = row.getLastCellNum();
		}
		return columnCount;
	}

	public String getCell(int rowNumber, int columnNumber) {
		String cellValue = null;
		try {
			if (sheet != null) {
				row = sheet.getRow(rowNumber);
				if (row != null)

					cellValue = row.getCell(columnNumber).toString();
			}
		} catch (Exception e) {
			return null;
		}
		return cellValue;
	}

	public String getCell2(int rowNumber, int columnNumber) {
		String cellValue = null;
		if (sheet != null) {
			row = sheet.getRow(rowNumber);
			if (row != null)
				cellValue = row.getCell(columnNumber).toString();
		}
		return cellValue;
	}

	public double getNumericCell(int rowNumber, int columnNumber) {
		double cellValue = -1000.0;
		try {
			if (sheet != null) {
				row = sheet.getRow(rowNumber);
				if (row != null) {
					XSSFCell cell = row.getCell(columnNumber);
					if (cell != null)
						cellValue = cell.getNumericCellValue();
				}
			}
		} catch (Exception e) {
			return cellValue;
		}
		return cellValue;
	}

	public XSSFRow getRow(int rowNumber) {
		XSSFRow row = null;
		if (sheet != null) {
			row = sheet.getRow(rowNumber);
		}
		return row;
	}

	/**
	 * Verify whether a sheet is present in the excel file or not
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return true if the sheet is present, else false
	 * 
	 */
	public boolean doesSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			int indexUpper = workbook.getSheetIndex(sheetName.toUpperCase());
			int indexLower = workbook.getSheetIndex(sheetName.toLowerCase());
			if (indexUpper == -1 && indexLower == -1) {
				return false;
			} else
				return true;
		} else
			return true;
	}

	/**
	 * Get a cell data by row number and column name
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnName
	 *            the name of the column
	 * @param rowNumber
	 *            the row number
	 * @return the cell data in the given row under the column name of the given
	 *         excel sheet
	 * 
	 */

	public String getCellData(String sheetName, String columnName, int rowNumber) {
		try {
			// verifies row number value
			if (rowNumber <= 0)
				return "";
			// verifies sheet name
			if (!doesSheetExist(sheetName))
				return "";
			// verifies column name
			int columnNumber = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim()
						.equals(columnName.trim()))
					columnNumber = i;
			}
			if (columnNumber == -1)
				return "";

			// Get cell data
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber - 1);
			if (row == null)
				return "";
			cell = row.getCell(columnNumber);
			if (cell == null)
				return "";
			// Validates the cell type and converts the corresponding cell data
			// to string
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNumber + " or column " + columnName
					+ " does not exist in xls";
		}
	}

	/**
	 * Get a cell data by row number and column number
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnNumber
	 *            the column number
	 * @param rowNumber
	 *            the row number
	 * @return the cell data in the given row and column number of the given
	 *         excel sheet
	 * 
	 */
	public String getCellData(String sheetName, int columnNumber, int rowNumber) {

		try {
			// verifies row number value
			if (rowNumber <= 0)
				return "";
			// verifies sheet name
			if (!doesSheetExist(sheetName))
				return "";
			// verifies column number
			if (columnNumber == -1)
				return "";

			// Get cell data
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber - 1);
			if (row == null)
				return "";
			cell = row.getCell(columnNumber);
			if (cell == null)
				return "";
			// Validates the cell type and converts the corresponding cell data
			// to string
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNumber + " or column " + columnNumber
					+ " does not exist in xls";
		}

	}

	/**
	 * Set a cell data by row number and column name
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnName
	 *            the column name
	 * @param rowNumber
	 *            the row number
	 * @param data
	 *            the data to set
	 * @return boolean true if the data setting for the specified cell is
	 *         successful, else false
	 * 
	 */
	public boolean setCellData(String sheetName, String columnName,
			int rowNumber, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			// verfying sheet name
			if (!doesSheetExist(sheetName))
				return false;

			// verifying row number value
			if (rowNumber <= 0)
				return false;

			// getting column number specific to column name
			int columnNumber = -1;
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim()
						.equals(columnName))
					columnNumber = i;
			}

			// verifies column number
			if (columnNumber == -1)
				return false;

			// preparing for data setting to column
			sheet.autoSizeColumn(columnNumber);
			row = sheet.getRow(rowNumber - 1);
			if (row == null)
				row = sheet.createRow(rowNumber - 1);

			cell = row.getCell(columnNumber);
			if (cell == null)
				cell = row.createCell(columnNumber);

			cell.setCellValue(data);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Set a cell data by row number and column name as a hyper link
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnName
	 *            the column name
	 * @param rowNumber
	 *            the row number
	 * @param data
	 *            the data to set
	 * @param url
	 *            the url for the hyper link
	 * @return boolean true if the data setting for the specified cell is
	 *         successful, else false
	 * 
	 */
	public boolean setCellData(String sheetName, String columnName,
			int rowNumber, String data, String url) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			// verifying row number
			if (rowNumber <= 0)
				return false;

			// verifying sheet name
			if (!doesSheetExist(sheetName))
				return false;

			// getting column number specific to column name
			int colNum = -1;
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim()
						.equalsIgnoreCase(columnName))
					colNum = i;
			}

			// verifying column number
			if (colNum == -1)
				return false;

			// preparing for data setting to column
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNumber - 1);
			if (row == null)
				row = sheet.createRow(rowNumber - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

			// cell style for hyperlinks
			// by default hypelrinks are blue and underlined
			CellStyle hlink_style = workbook.createCellStyle();
			XSSFFont hlink_font = workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			// hlink_style.setWrapText(true);

			XSSFHyperlink link = createHelper
					.createHyperlink(XSSFHyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Add a column by column name
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnName
	 *            the column name
	 * @return boolean true if the specified column addition is successful, else
	 *         false
	 * 
	 */
	public boolean addColumn(String sheetName, String columnName) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

			// verfying sheet name
			if (!doesSheetExist(sheetName))
				return false;
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(columnName);
			cell.setCellStyle(style);

			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Add a sheet to an excel file
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return boolean true if the specified sheet addition is successful, else
	 *         false
	 * 
	 */
	public boolean addSheet(String sheetName) {

		FileOutputStream fileOut;
		if (doesSheetExist(sheetName))
			return false;
		try {
			workbook.createSheet(sheetName);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addHyperLink(String sheetName, String screenShotColName,
			String testCaseName, int index, String url, String message) {
		// System.out.println("ADDING addHyperLink******************");

		url = url.replace('\\', '/');
		if (!doesSheetExist(sheetName))
			return false;

		sheet = workbook.getSheet(sheetName);
		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
				setCellData(sheetName, screenShotColName, i + index, message,
						url);
				break;
			}
		}
		return true;
	}

	/**
	 * Get a cell row number by column name and cell data
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnName
	 *            the column name
	 * @param cellValue
	 *            the value of the cell
	 * @return row number of the cell if specified data is present, else -1
	 * 
	 */
	public int getCellRowNum(String sheetName, String columnName,
			String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, columnName, i).equalsIgnoreCase(
					cellValue)) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * Delete a sheet from an excel file
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return boolean true if the specified sheet deletion is successful, else
	 *         false
	 * 
	 */
	public boolean removeSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Set an active sheet in an excel file
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @return boolean true if the specified sheet deletion is successful, else
	 *         false
	 * 
	 */
	public boolean setActiveSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		try {
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Remove a column by column name
	 * 
	 * 
	 * 
	 * @param sheetName
	 *            the name of the sheet
	 * @param columnNumber
	 *            the column number
	 * @return boolean true if the specified column deletion is successful, else
	 *         false
	 * 
	 */
	public boolean removeColumn(String sheetName, int columnNumber) {
		try {
			if (!doesSheetExist(sheetName))
				return false;
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(XSSFCellStyle.NO_FILL);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(columnNumber);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
}
