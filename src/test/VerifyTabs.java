package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.Browser;
import util.GeneralUtil;
import util.PropertyUtils;

public class VerifyTabs {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut= -1;
	public String strBrowserType = null;
	public Logger logger = null;

	public WebDriver driver;
	

	@BeforeClass
	public void beforeClass() throws Exception {
		// Read general timings from properties file
		dynamicTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("DynamicTimeOut"));

		scriptTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("ScriptTimeout"));

		strBrowserType = PropertyUtils.getConfigMessage("Browser");
		// Defined all properties and Configure log4j
		GeneralUtil.configureLog4j("VerifyTabs");
		// Assign logger object
		GeneralUtil.logger("VerifyTabs");
		// Create a new screenshot folder with time stamp
		//GeneralUtil.createScreenshotFolder();

		// Initiated browser instance for browser defined in properties file
		try {
			Browser.setBrowserType(strBrowserType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create driver object for browser
		try {
			driver = Browser.getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Launch and login to XVA application
		try {
			Browser.NavigateToURL();
			Common.loginToXVA();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Verify tabs in the left corner of the window locate tabs: Risk,Trades,
	// Market Data,
	// Static,Calculations,sensitives & Markets,scenarios,Admin,custom layout .
	@Test(alwaysRun=true)
	public void dashboard_verifyTabs() throws Exception {
		try {
		GeneralUtil.logger.info("Step7 : Verify Treeview Items");
		Thread.sleep(3000);
		// verify trade data type is exist
		String strTradeXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li[1]/div/div/div[text()='Trades']";
		verifyElementPresent("Trades", strTradeXpath, dynamicTimeOut);
		// verify Market Data data type is exist
		String strMarketDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Market Data']";
		verifyElementPresent("Market Data", strMarketDataXpath, dynamicTimeOut);
		// verify Risk data type is exist
		//String strRiskXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk']";
		//verifyElementPresent("Risk", strRiskXpath, dynamicTimeOut);
		// verify Static data type is exist
		String strStaticXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Static']";
		verifyElementPresent("Static", strStaticXpath, dynamicTimeOut);
		// verify Calculations data type is exist
		String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
		verifyElementPresent("Calculations", strCalculationsXpath,
				dynamicTimeOut);
		// verify Scenarios data type is exist
		String strSensitivitiesMarketScenariosXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Sensitivities & Market Scenarios']";
		verifyElementPresent("Sensitivities & Market Scenarios",
				strSensitivitiesMarketScenariosXpath, dynamicTimeOut);
		// verify Admin data type is exist
		String strAdminXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Admin']";
		verifyElementPresent("Admin", strAdminXpath, dynamicTimeOut);
		// verify Custom Layout data type is exist
		String strCustomLayoutXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Custom Layout']";
		verifyElementPresent("Custom Layout", strCustomLayoutXpath,
				dynamicTimeOut);
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	// verify in the upper right corner of the screen there is current user's
	// name (Administrator) and current date and time.
	@Test(dependsOnMethods = "dashboard_verifyTabs",alwaysRun=true)
	public void dashboard_verifyUserNameandDateTime() throws Exception {
		try {
			GeneralUtil.logger("Step9 :Verify User Nameand Date Time");
			// Get displayed date and time from application about window
			WebElement objDateAndTime = GeneralUtil
					.getElement(
							"//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-Header-dark---dateContainer')]",
							"xpath", dynamicTimeOut);
			String strdateTime = objDateAndTime.getText();
			String today = getToday("yyyy-mm-dd, hh:mm:ss a");
			// datetime = System.currentTimeMillis();
			// Verivy displayed date is equal to today date and time
			isExpire(strdateTime, today);

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	// Click on Administrator and verify that Opens a drop-down menu giving
	// users access to Global
	// Settings, Reset password,About and the option to Logout.
	@Test(dependsOnMethods =  "dashboard_verifyUserNameandDateTime",alwaysRun=true)
	public void dashboard_verifyAdministratorDropDownItems() throws Exception {
		GeneralUtil.logger.info("Step10 : Verify Administrator DropDown Items");

		try {
			// define expected drop down items
			String[] values = { "Global Settings", "Reset Password", "About",
					"Log Off" };

			// Close notifications
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}

			// Click on administrator dropdown
			String strAdministratorXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/div[text()='Administrator']/span";
			WebElement objAdministrator = GeneralUtil.getElement(
					strAdministratorXpath, "xpath", dynamicTimeOut);
			objAdministrator.click();

			// Verify for each dropdown item is displayed as expected
			List<WebElement> arrDropDownItems = GeneralUtil
					.getElements(
							"//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/ul/li",
							"xpath", dynamicTimeOut);
			for (int j = 0; j < values.length; j++) {
				boolean blnflog = false;
				for (int i = 0; i < arrDropDownItems.size(); i++) {
					String strActItemText = arrDropDownItems.get(i).getText();
					if (strActItemText.equalsIgnoreCase(values[j])) {
						GeneralUtil.logger.info(values[j]
								+ " is displayed in Administration drop down");
						blnflog = true;
						break;
					}
				}

				if (!blnflog) {
					GeneralUtil.logger.error(values[j]
							+ " is not displayed in Administration drop down");
				}

			}

			objAdministrator.click();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	// Click on Settings . Check the Server date if it matches the date of
	// server started.
	@Test(dependsOnMethods = "dashboard_verifyAdministratorDropDownItems",alwaysRun=true)
	public void dashboard_verifyGlobalSttingsWindow() throws Exception {
		try {
			GeneralUtil
					.logger.info("Step11: Validate Server time in Global Sttings");
			// Close notifications
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}
			// Click on Administrator
			String strAdministratorXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/div[text()='Administrator']/span";
			WebElement objAdministrator = GeneralUtil.getElement(
					strAdministratorXpath, "xpath", dynamicTimeOut);
			objAdministrator.click();
			// Select GlobalSettings
			WebElement objGlobalSettings = GeneralUtil
					.getElement(
							"//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/ul/li[text()='Global Settings']",
							"xpath", dynamicTimeOut);
			objGlobalSettings.click();
			// Get serverdate from applicatrion field
			WebElement objserverDate = GeneralUtil
					.getElement(
							"html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div[starts-with(@class,'src-components-Dashboard-UserSettings-GlobalSettings-GlobalSetting-dark---serverDate')]/div/div/span",
							"xpath", dynamicTimeOut);

			String strServerDate = objserverDate.getText();
			String strServerDate1 = strServerDate;
			// Get today date and format it to EEE MMM dd yyyy HH:mm format
			Date date = new Date();
			String today = new SimpleDateFormat("EEE MMM dd yyyy HH:mm")
					.format(date);

			strServerDate = strServerDate.replace(
					" GMT+0530 (India Standard Time)", "");

			strServerDate = strServerDate.substring(0,
					(strServerDate.length() - 3));

			// Compare serverdate frome application and today date
			if (today.equalsIgnoreCase(strServerDate)) {
				GeneralUtil.logger
						.info("Server date is mathed the server standerd. "
								+ strServerDate1);
			} else
				GeneralUtil.logger
						.info("Server date is not mathed the server standerd. "
								+ strServerDate1);
			// close GlobalSttingsWindow
			WebElement objAoutClose = GeneralUtil
					.getElement(
							"html/body/div[2]/div/div/div/div/div/div[1]/div/span[2]/i",
							"xpath", dynamicTimeOut);
			objAoutClose.click();
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}
		} catch (Exception e) {

			GeneralUtil.logger
					.error("Unable to validate Server date in Global Settings window. Exception is :"
							+ e.getMessage());
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	// Click on about and verify About window is opened. Check Version and
	// Copyright on the screen.
	 @Test(dependsOnMethods = "dashboard_verifyGlobalSttingsWindow",alwaysRun=true)
	public void dashboard_verifyAboutWindow() throws Exception {
		try {
			if(driver.findElement(By.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")).isDisplayed()==true) {
			Common.handleNotifications("Failed to get global setting for user Administrator");
			}
			}catch(Exception e){
				if(e.toString().contains("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")){
					GeneralUtil.logger.error("Notification Not found");
				}
			}
		try{
			Thread.sleep(1000);
			GeneralUtil.logger.info("Step11:Verify About Window");
			// Close notifications
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}
			// Close notifications
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}
			// Close notifications
			try {
				WebElement objnotification = GeneralUtil
						.getElement(
								"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
								"xpath", 1);
				objnotification.click();
			} catch (Exception e) {

			}
			// Click on Administrator
			String strAdministratorXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/div[text()='Administrator']/span";
			WebElement objAdministrator = GeneralUtil.getElement(
					strAdministratorXpath, "xpath", dynamicTimeOut);
			objAdministrator.click();
			// Select about drop down
			WebElement objAbout = GeneralUtil
					.getElement(
							"//div[starts-with(@class,'src-routes-Home-components-MainPage-layouts-Header-UserStatus-dark---userInfo')]/ul/li[text()='About']",
							"xpath", dynamicTimeOut);
			objAbout.click();
			// Get text from AboutCopyright field
			// cjeck matchs with given text
			WebElement objAboutCopyright = GeneralUtil
					.getElement(
							"html/body/div[2]/div/div/div/div/div/div[2]/div/div/div[7]/span/parent::div",
							"xpath", dynamicTimeOut);
			String strCopyright = objAboutCopyright.getText();
			strCopyright = strCopyright.replace("\n", "");
			String strExpCopyRight = "Copyright (C) 2017 Numerix LLC. All rights reserved.Visit www.numerix.com for more information";
			if (strExpCopyRight.equalsIgnoreCase(strCopyright))
				GeneralUtil.logger.info(strCopyright
						+ " is displayed in About window");
			else
				GeneralUtil.logger.error(strCopyright
						+ " is not displayed in About window");

			WebElement objAoutClose = GeneralUtil
					.getElement(
							"html/body/div[2]/div/div/div/div/div/div[1]/div/span[2]/i",
							"xpath", dynamicTimeOut);
			objAoutClose.click();

		} catch (Exception e) {

			GeneralUtil.logger
					.error("Unable to validate copy rights text in About window. Exception is :"
							+ e.getMessage());
			
			
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
			
		}

	}

	// Verify Trades screen has an additional icon which allows users to
	// customize the columns
	// that will appear on the screen. These columns will be used to filter
	// trades.
	@Test(dependsOnMethods = "dashboard_verifyAboutWindow",alwaysRun=true)
	public void dashboard_verifyColumnsFilter() throws Exception {

		GeneralUtil.logger.info("Step8&9 : Verify Columns Filter");
		/*if(driver.findElement(By.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")).isDisplayed()==true) {
			Common.handleNotifications("Failed to get trades filter for user Administrator");
			}
		else {
			
		}*/
		
		String strTradeXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li[1]/div/div/div[text()='Trades']";
		try {
			WebElement objTradeTreeviewItem = GeneralUtil.getElement(
					strTradeXpath, "xpath", dynamicTimeOut);
			objTradeTreeviewItem.click();
			Thread.sleep(6000);
			try{
			if(driver.findElement(By.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")).isDisplayed()==true) {
			Common.handleNotifications("Failed to get trades filter for user Administrator");
			}
			} catch (Exception e) {

			}
			
			// Close notifications if any exist
			try {
				for (int i = 1; i < 4; i++) {
					WebElement objnotification = GeneralUtil
							.getElement(
									"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
									"xpath", 2);
					objnotification.click();
				}
			} catch (Exception e) {

			}

			/*
			 * String strCheckBox =
			 * "html/body/div[1]/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div[2]/div/div/label[text()='Accrual End Date']/preceding::div[1]"
			 * ; WebElement objCheckBox = GeneralUtil.getElement(strCheckBox,
			 * "xpath", DynamicTimeOut); objCheckBox.click();
			 */

			// objShowHideColumnsButton.click();

			//
			String strActColText = "";
			List<WebElement> arrColumnText = GeneralUtil
					.getElements(
							"//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]/div//span[starts-with(@class,'ag-header-cell-text')]",
							"xpath", dynamicTimeOut);
			

			for (int i = 0; i < arrColumnText.size()-1; i++) {
				try {
					WebElement objnotification = GeneralUtil
							.getElement(
									"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
									"xpath", 1);
					objnotification.click();
				} catch (Exception e) {

				}
				strActColText = arrColumnText.get(i).getText();
				WebElement objShowHideColumnsButton = GeneralUtil.getElement(
						"//div/span[@title='Show/Hide Columns']/i", "xpath",
						dynamicTimeOut);
				objShowHideColumnsButton.click();
				boolean blnChecked = false;
				JavascriptExecutor js = (JavascriptExecutor) driver;

				String TitleName = js.executeScript("return document.title;")
						.toString();
				System.out.println("Title of the page = " + TitleName);

				WebElement lstElement = driver
						.findElement(By.xpath(String
								.format("html/body/div[1]/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[2]/div/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div/div[2]/div/div/label[text()='%s']",
										strActColText)));

				blnChecked = (boolean) (js.executeScript(
						"return arguments[0].control.checked; ", lstElement));
				if (blnChecked)
					GeneralUtil.logger.info(strActColText
							+ " checkbox is chached");
				else
					GeneralUtil.logger.error(strActColText
							+ " checkbox is not chached");
				objShowHideColumnsButton.click();
				/*
				 * if(strActColText.equalsIgnoreCase("Accrual End Date") ) {
				 * GeneralUtil.logger.info(strActColText
				 * +" coulmn is displayed in Trades grid"); blnflog = true;
				 * break; }
				 */
			}

			GeneralUtil.logger("Step13 : Verify application log out");
			
			Common.loginOffXVA();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@AfterClass
	public void afterClass() throws Exception {
		// Close the browser
		
		driver.quit();
		
		
	}

	/*
	 * public void ColumnsFilterVerify() {
	 * 
	 * GeneralUtil.logger("Step8&9 : Columns Filter Verify");
	 * 
	 * String strTradeXpath =
	 * "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li[1]/div/div/div[text()='Trades']"
	 * ; try { WebElement objTradeTreeviewItem =
	 * GeneralUtil.getElement(strTradeXpath, "xpath", DynamicTimeOut);
	 * objTradeTreeviewItem.click();
	 * 
	 * try { WebElement objnotification = GeneralUtil.getElement(
	 * "//div[starts-with(@class,'notification notification-error notification-visible')]/span"
	 * , "xpath", 1); objnotification.click(); } catch (Exception e) {
	 * 
	 * } WebElement objShowHideColumnsButton =
	 * GeneralUtil.getElement("//div/span[@title='Show/Hide Columns']/i",
	 * "xpath", DynamicTimeOut); objShowHideColumnsButton.click();
	 * 
	 * 
	 * String strCheckBox =
	 * "html/body/div[1]/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div[2]/div/div/label[text()='Accrual End Date']/preceding::div[1]"
	 * ; WebElement objCheckBox = GeneralUtil.getElement(strCheckBox, "xpath",
	 * DynamicTimeOut); objCheckBox.click();
	 * 
	 * objShowHideColumnsButton.click();
	 * 
	 * //
	 * 
	 * List<WebElement> arrColumnText = GeneralUtil.getElements(
	 * "//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]/div//span[starts-with(@class,'ag-header-cell-text')]"
	 * , "xpath", DynamicTimeOut); boolean blnflog = false; for(int i = 0;i <
	 * arrColumnText.size();i++) { String strActColText =
	 * arrColumnText.get(i).getText();
	 * if(strActColText.equalsIgnoreCase("Accrual End Date") ) {
	 * GeneralUtil.logger.info(strActColText
	 * +" coulmn is displayed in Trades grid"); blnflog = true; break; } }
	 * 
	 * if(!blnflog) { GeneralUtil.logger.error(
	 * "'Accrual End Date' coulmn is not displayed in Trades grid"); }
	 * 
	 * objShowHideColumnsButton.click();
	 * 
	 * WebElement objCheckBox1 = GeneralUtil.getElement(strCheckBox, "xpath",
	 * DynamicTimeOut); objCheckBox1.click();
	 * 
	 * GeneralUtil.logger("Step13 : Verify application log out");
	 * CommonTest.loginOffXVA();
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	public void verifyElementPresent(String strTrade, String strXpath,
			int timeOut) {
		try {
			boolean blnFind = GeneralUtil.waitUntilExists(strXpath, "xpath",
					timeOut);
			if (blnFind)
				GeneralUtil.logger.info(strTrade
						+ " treeview item is displayed with given xpath: "
						+ strXpath);
			else
				GeneralUtil.logger.error(strTrade
						+ " treeview item is not displayed with given xpath: "
						+ strXpath);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			GeneralUtil.logger
					.error("Element is not displayed.Detailes are Locator:"
							+ strXpath + " LocatorType:xpath WaitTime:"
							+ timeOut);
		}
	}

	public void isExpire(String expdate, String today) throws Exception {
		if (expdate.isEmpty() || expdate.trim().equals("")) {
			GeneralUtil.logger
					.error("Expected data and time variable is Emplty.");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-mm-dd, hh:mm:ss a"); // Jan-20-2015 1:30:55 PM
			Date d = null;
			Date d1 = null;

			// String date = new
			// SimpleDateFormat("yyyy-mm-dd, hh:mm:ss a").format(expdate.toUpperCase());

			try {
				// System.out.println("expdate>> "+date);
				// System.out.println("today>> "+today+"\n\n");
				d = sdf.parse(expdate);
				d1 = sdf.parse(today);
				if (d1.compareTo(d) < 0) {// not expired
					GeneralUtil.logger.error("Displayed date and time:" + d
							+ " is not equal to system current time:" + d1);
				} else if (d.compareTo(d1) == 0) {// both date are same

				}
				if (d.getTime() == d1.getTime()) {// expired
					GeneralUtil.logger
							.info("Displayed date and time is equal to system current time");
				} else if (d.getTime() < d1.getTime()) {// not expired
					GeneralUtil.logger.info("Displayed date and time:" + d
							+ " is not equal to system current time:" + d1);
				} else {// expired
					GeneralUtil.logger.info("Displayed date and time:" + d
							+ " is grater then to system current time:" + d1);
				}
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}

	public String getToday(String format) {
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
	}

}
