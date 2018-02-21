package test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Browser;
import util.GeneralUtil;
import util.PropertyUtils;

public class Common {

	public static WebDriver driver;
	public static String userName = null;
	public static String passWord = null;
	public static Integer scriptTimeOut = null;
	public static Integer dynamicTimeOut = 0;
	/**
	 * @param args
	 */

	static {

		userName = PropertyUtils.getConfigMessage("Username");

		passWord = PropertyUtils.getConfigMessage("Password");
		scriptTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("ScriptTimeout"));
		/*try {
			driver = Browser.getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	public static void loginToXVA() throws Exception {

		driver = GeneralUtil.generalGetDriver();
		// Login page
		WebElement userNameElement = driver.findElement(By
				.xpath(".//input[@type='text']"));
		userNameElement.sendKeys(userName);
		WebElement passWordElement = driver.findElement(By
				.xpath(".//input[@type='password']"));
		passWordElement.sendKeys(passWord);
		WebElement submitLogin = driver.findElement(By
				.xpath(".//input[@type='button']"));
		GeneralUtil.captureScreenshot();
		submitLogin.click();

		WebElement xvaLogoElement = driver.findElement(By
				.xpath("//img[@alt='NumerixOneview_XVA']"));
		if (xvaLogoElement.isDisplayed()) {

			GeneralUtil.logger.info("Login to XVA is successful");

			GeneralUtil.captureScreenshot();
		} else {

			GeneralUtil.captureScreenshot();

			throw new Exception("Login to XVA is NOT successful.");

		}

	}

	public static void download1(String csvDownload, String download,
			String downloadFile, String csvXpath, String downloadXpath)
			throws Exception {
		try {
			GeneralUtil.contextMenuItem(csvXpath, csvDownload);

			Long beforeTime = System.currentTimeMillis();
			GeneralUtil.contextMenuItem(downloadXpath, download);

			String path = FileSystemView.getFileSystemView().getHomeDirectory()
					.getPath();
			String executionResultsPath=System.getProperty("strDownload_Path");
			path = path.replace("Desktop", "Downloads");
			File destinationDir = new File(executionResultsPath);
			File file = null;
			boolean fileDownloaded = false;
			// while (!fileDownloaded) {
			int count = 1;
			while (count < dynamicTimeOut) {
				file = GeneralUtil.getLatestFilefromDir(path);
				if (file.getName().toString().matches(downloadFile)
						&& file.lastModified() > beforeTime) {
					fileDownloaded = true;
					FileUtils.moveFileToDirectory(file, destinationDir, true);
					break;
				}
				Thread.sleep(1000);
				count = count++;

			}
			GeneralUtil.logger.info("File " + "'" + file.getName().toString()
					+ "'" + " Downloaded Successfully");
			Thread.sleep(2000);

		} catch (Exception e) {
			throw new Exception("Unable to download file. Exception:"
					+ e.getMessage());
		}
	}

	public static void download(String csvDownload, String download,
			String downloadFile, String csvXpath, String downloadXpath)
			throws Exception {
		try {
			Thread.sleep(1000);
			GeneralUtil.contextMenuItem(csvXpath, csvDownload);

			Long beforeTime = System.currentTimeMillis();
			GeneralUtil.contextMenuItem(downloadXpath, download);
			
Thread.sleep(1000);
			String path = FileSystemView.getFileSystemView().getHomeDirectory()
					.getPath();
			//String executionResultsPath=System.getProperty("strDownload_Path");
			path = path.replace("Desktop", "Downloads");
			//File destinationDir = new File(executionResultsPath);

			
			File file = null;
			boolean fileDownloaded = false;
			while (!fileDownloaded) {
				Thread.sleep(2000);
				file = GeneralUtil.getLatestFilefromDir(path);
				if (file.getName().toString().matches(downloadFile)
						&& file.lastModified() > beforeTime)
					fileDownloaded = true;
				Thread.sleep(1000);
				//FileUtils.moveFileToDirectory(file, destinationDir, true);

			}
			GeneralUtil.logger.info("File " + "'" + file.getName().toString()
					+ "'" + " Downloaded Successfully");
			Thread.sleep(2000);

		} catch (Exception e) {
			throw new Exception("Unable to download file. Exception:"
					+ e.getMessage());
		}
	}

	public static boolean handleToolTip(String expMessage) throws Exception {

		WebElement popupTextElement = GeneralUtil
				.getElement(
						".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[3]/div/div[2]/div/div[3]/h4",
						"xpath", scriptTimeOut);
		String popupText = popupTextElement.getText();
		System.out.println(popupText);
		if (popupText.contains(expMessage)) {
			GeneralUtil.logger.info("Displayed message in tooltip is :"
					+ popupText);
			GeneralUtil.captureScreenshot();
			return true;
		} else {
			GeneralUtil.logger.info(expMessage
					+ " is not displayed in tool tip");
			return false;
		}
	}

	public static void loginOffXVA() throws Exception {
		String ssFileName = "LoginOffXVA";
		try {
			driver = Browser.getDriver();

			try {
				// Login page
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}

			/*
			 * String strAdministratorXpath =
			 * "//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/div[text()='Administrator']/span"
			 * ; WebElement objAdministrator =
			 * GeneralUtil.getElement(strAdministratorXpath, "xpath",
			 * dynamicTimeOut); objAdministrator.click();
			 * 
			 * WebElement objLogOff = GeneralUtil.getElement(
			 * "//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/ul/li[text()='Log Off']"
			 * , "xpath", dynamicTimeOut); objLogOff.click();
			 */
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/div[text()='Administrator']/span"))
					.click();
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/ul/li[text()='Log Off']"))
					.click();
			GeneralUtil.captureScreenshot();

			WebElement xvaLogoElement = driver.findElement(By
					.xpath(".//input[@type='button']"));
			if (xvaLogoElement.isDisplayed()) {

				GeneralUtil.logger
						.info("The application is logged out successfully and Numerix XVA Login window is opened.");

				//GeneralUtil.captureScreenshot();
			} else {

				GeneralUtil.captureScreenshot();

				GeneralUtil.logger
						.info("Unable to logout form XVA application.Numerix XVA Login window is not opened ");

			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();

			throw new Exception(
					"Unable to logout form XVA application. Exception:"
							+ e.getMessage());
		}

	}

	public static boolean handleNotifications(String expMessage)
			throws Exception {
		WebElement objNotificationsTest = GeneralUtil.getElement("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']","xpath", scriptTimeOut);
		String strnotifications = objNotificationsTest.getText();
		WebElement objNotificationsH4 = GeneralUtil.getElement(
				"//div[@class='notifications-wrapper']//h4", "xpath",
				scriptTimeOut);
		strnotifications = objNotificationsH4.getText() + ":"
				+ strnotifications;
		if (strnotifications.contains(expMessage)) {

			GeneralUtil.logger.info("Displayed notifications is :"
					+ strnotifications);
			GeneralUtil.captureScreenshot();
			// Thread.sleep(1000);
			try {

				WebElement notificationCloseButton = driver
						.findElement(By
								.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::span[@class='notification-dismiss']"));
				System.out.println("Start");
				if (notificationCloseButton.isDisplayed()) {
					notificationCloseButton.click();

				}
			} catch (Exception e) {

				return true;
			}
			return true;
		} else {
			GeneralUtil.logger.info(expMessage
					+ " is not displayed in notification");
			return false;
		}
	}

	public static void clickNotification(String xpath) throws Exception {
		try {
			WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath(xpath));
			refreshScreenElement.click();
		} catch (Exception e) {
			throw new Exception("Unable to click on Notification. Exception:"
					+ e.getMessage());
		}
	}

	public static Integer getColumnNumber(String strExpColumnName) {

		String strColumns = "//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]//div[starts-with(@class,'ag-header-cell')]";
		try {
			List<WebElement> objColumns = GeneralUtil.getElements(strColumns,
					"xpath", 30);
			for (int i = 0; i < objColumns.size(); i++) {
				WebElement objColumn1 = objColumns.get(i);
				WebElement objColumnText = objColumn1
						.findElement(By.xpath(String
								.format("//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]//div[starts-with(@class,'ag-header-cell ag-header-cell-sortable')][%d]//span[starts-with(@class,'ag-header-cell-text')]",
										i + 1)));
				String strColumnCalText = objColumnText.getText();
				if (strColumnCalText.equalsIgnoreCase(strExpColumnName)) {
					return i + 1;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Integer getColumnNumberForJobProgress(String strExpColumnName) {

		String strColumns = "//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]//div[starts-with(@class,'ag-header-cell')]";
		try {
			List<WebElement> objColumns = GeneralUtil.getElements(strColumns,
					"xpath", 30);
			for (int i = 0; i < objColumns.size(); i++) {
				WebElement objColumn1 = objColumns.get(i);
				WebElement objColumnText = objColumn1
						.findElement(By.xpath(String
								.format("//div[starts-with(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView---calcsTable')]/div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-cell ag-header-cell-sortable')][%d]//span[starts-with(@class,'ag-header-cell-text')]",
										i + 1)));
				String strColumnCalText = objColumnText.getText();
				if (strColumnCalText.equalsIgnoreCase(strExpColumnName)) {
					return i + 1;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Integer getRowNumber(Integer intColIndex,
			String strExpRowValue) {
		
		String strRows = "//*[contains(@class,'ag-body-container')]//div[contains(@class,'ag-row')]";
		try {
			Thread.sleep(1000);
			List<WebElement> objRows = GeneralUtil.getElements(strRows,
					"xpath", 30);
			for (int i = 0; i < objRows.size(); i++) {
				WebElement objRow = objRows.get(i);
				WebElement objColumnText = objRow.findElement(By.xpath(String
						.format("div[%d]//span", intColIndex)));
				String strRowText = objColumnText.getText();
				if (strRowText.equalsIgnoreCase(strExpRowValue)) {
					return i + 1;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Integer getTableColumnNumber(String strExpColumnName,
			String strColumns) {

		try {
			List<WebElement> objColumns = GeneralUtil.getElements(strColumns,
					"xpath", 30);
			for (int i = 0; i < objColumns.size(); i++) {
				WebElement objColumn1 = objColumns.get(i);

				String strColumnText = objColumn1.getText();
				if (strColumnText.equalsIgnoreCase(strExpColumnName)) {
					return i + 1;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Integer getTableColumnNumbers(String strColumns) {

		try {
			List<WebElement> objColumns = GeneralUtil.getElements(strColumns,
					"xpath", 30);
			// for(int i = 0;i < objColumns.size();i++){
			int i = objColumns.size();

			return i;

			// }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Integer getTableRowNumber(String strRowsXpath,
			String strCellXpath, Integer intColIndex, String strExpRowValue) {

		// String strRows = ".//*[@id='3SPGGrid']/div/div/table/tbody/tr";
		try {
			List<WebElement> objRows = GeneralUtil.getElements(strRowsXpath,
					"xpath", 30);
			for (int i = 0; i < objRows.size(); i++) {
				WebElement objRow = objRows.get(i);
				WebElement objColumnText = objRow.findElement(By.xpath(String
						.format(strCellXpath, intColIndex)));
				// WebElement objColumnText =
				// objRow.findElement(By.xpath("//th[2]"));
				String strRowText = objColumnText.getText();
				if (strRowText.equalsIgnoreCase(strExpRowValue)) {
					return i + 1;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
