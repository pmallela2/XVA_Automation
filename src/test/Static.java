package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import util.Browser;
import util.ExcelUtil;
import util.ExcelUtilHSSF;
import util.GeneralUtil;
import util.PropertyUtils;

public class Static {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut= -1;
	public String strBrowserType = null;
	public Logger logger = null;
	public String strmenu = "//div[@class='ag-menu']//span[@class='ag-menu-option-text']";
	public String strNewCalender;
	public String strNewConventions;
	public String strNewCounterparties;
	//public String strNewCounterpartiesStoringVariable;
	public String strCURRENCY;
	public String strCurrencyPair;
	public String strTimeZone;
	public String strRiskScripts;
	public String strReferenceData;
	public String strSupervisoryParameter;
	public String strRiskWeights;
	public String strSACCRRiskWeights;
	public String strSACVARiskWeights;
	public String strSACCRGeneral;
	public WebDriver driver;
	public String casPath;


	@BeforeClass
	public void beforeClass() throws Exception {
		// Read general timings from properties file
		dynamicTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("DynamicTimeOut"));

		scriptTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("ScriptTimeout"));

		strBrowserType = PropertyUtils.getConfigMessage("Browser");
		
		casPath = PropertyUtils.getConfigMessage("Upload_Path");
		// Defined all properties and Configure log4j
		GeneralUtil.configureLog4j("Static");
		// Assign logger object
		GeneralUtil.logger("Static");
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

		// Lunch and login to XVA application
		try {
			Browser.NavigateToURL();
			Common.loginToXVA();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	
	@Test (alwaysRun=true)
	public void static_verifySubNodesForStaticNodeInTreeView() throws Exception {
		try{
			GeneralUtil.logger("Step35 : Verify Sub Nodes For Static Node In TreeView");

		GeneralUtil.logger.info("Started Verify Sub Nodes For Static Node");
		// verify Static data type is exist
		String strStaticXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Static']";
		verifyElementPresent("Static", strStaticXpath, dynamicTimeOut);
		// verify Calendars data type is exist
		String strCalendarsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calendars']";
		verifyElementPresent("Calendars", strCalendarsXpath, dynamicTimeOut);
		// verify Conventions data type is exist
		String strConventionsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Conventions']";
		verifyElementPresent("Conventions", strConventionsXpath, dynamicTimeOut);
		// verify Counterparties data type is exist
		String strCounterpartiesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Counterparties']";
		verifyElementPresent("Counterparties", strCounterpartiesXpath,
				dynamicTimeOut);
		// verify Currencies data type is exist
		String strCurrenciesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currencies']";
		verifyElementPresent("Currencies", strCurrenciesXpath, dynamicTimeOut);
		// verify CurrencyPairs data type is exist
		String strCurrencyPairsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currency Pairs']";
		verifyElementPresent("Currency Pairs", strCurrencyPairsXpath,
				dynamicTimeOut);
		// verify TimeZones data type is exist
		String strTimeZonesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Timezones']";
		verifyElementPresent("TimeZones", strTimeZonesXpath, dynamicTimeOut);
		// verify Capital Parameters data type is exist
		String strCapitalParametersXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Capital Parameters']";
		verifyElementPresent("Capital Parameters", strCapitalParametersXpath,
				dynamicTimeOut);
		// verify Risk Scripts data type is exist
		String strRiskScriptsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk Scripts']";
		verifyElementPresent("Risk Scripts", strRiskScriptsXpath,
				dynamicTimeOut);
		// verify Reference Data data type is exist
		String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Reference Data']";
		verifyElementPresent("Reference Data", strReferenceDataXpath,
				dynamicTimeOut);
		} catch (Exception e) {

			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_verifySubNodesForStaticNodeInTreeView", alwaysRun=true)
	public void static_addCalendars() throws Exception {
		GeneralUtil.logger("Step36 : Add new Calender");
		GeneralUtil.logger.info("Started add calender");
		try {
			// Navigate to Calculations screen
			String strCalendarsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calendars']";
			WebElement objCalendars = GeneralUtil.getElement(strCalendarsXpath,
					"xpath", dynamicTimeOut);
			objCalendars.click();
			GeneralUtil.logger
					.info("Navigated to Calendars by selecting the Calendars treeview item.");

			// Right click on element and select add context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			// Enter name and clicked on ok button
			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strNewCalender = "Auto" + timeStamp;
			objName.sendKeys(strNewCalender);

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			GeneralUtil.logger.info("Entered name and clicked on ok button.");

			// Verification for success notification
			Common.handleNotifications("Save Calendar");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("Clicked on Save button.");

			// Clicked OK button on Save Confirmation window
			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("Clicked OK button on Save Confirmation window.");

			// Verification for success notification
			Common.handleNotifications("Save Calendar");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strNewCalender);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strNewCalender)) {
				GeneralUtil.logger.info("Added new Calender:" + strNewCalender
						+ " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new Calender:"
						+ strNewCalender);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add Calendars.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editCalender" })
	public void static_deleteCalender() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new Calender");
			GeneralUtil.logger.info("Stated");
			GeneralUtil.logger.info("Started Delete new Calender.");

			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			// Right click on element and select Delete context Menue item
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			// Verification for success notification
			Common.handleNotifications("Delete calendar");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_addCalendars" })
	public void static_editCalender() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Edit Calender");
		GeneralUtil.logger.info("Stated");
		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys(strNewCalender);

		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		driver.findElement(
				By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"))
				.click();

		

		// Right click on element and select add context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[contains(@class,'holidaysContainer')]//*[@ref='center']/div/div[4]/div[3]/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Add");
		GeneralUtil.captureScreenshot();

		// Click on save button
		WebElement objSaveButton = GeneralUtil.getElement(
				"//div[text()='Save']", "xpath", dynamicTimeOut);
		objSaveButton.click();

		WebElement objOkButton = GeneralUtil
				.getElement(
						"//div[@class='src-components-ModalDialog-Modal-dark---container---3osy3']//div[contains(@class,'buttonContainer')]/div[1]/button",
						"xpath", dynamicTimeOut);
		objOkButton.click();
		// Verification for success notification
		Common.handleNotifications("Save Calendar");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_deleteCalender", alwaysRun=true)
	public void static_uploadCalender() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Calender");
		GeneralUtil.logger.info("Stated");
		// Right click on element and select Clear all filters context Menue
		// item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();
		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));

		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		// Right click on element and select context Menue item
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "NXT Upload");
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\Calendar\\BEIJING.nxt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload calendar template");
	} catch (Exception e) {
		GeneralUtil.captureScreenshot();
		e.printStackTrace();
		GeneralUtil.logger.error(e);
		throw new Exception(e);
	}
	}

	@Test(dependsOnMethods = "static_uploadCalender", alwaysRun=true)
	public void static_downloadCalender() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Calender");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		Common.download("NXT Download", "Download All", "calendar(.*).zip",
				strmenu, strmenu);
		GeneralUtil.logger.info("Calender downloaded successfully");
	} catch (Exception e) {
		GeneralUtil.captureScreenshot();
		e.printStackTrace();
		GeneralUtil.logger.error(e);
		throw new Exception(e);
	}
	}

	@Test(dependsOnMethods = "static_downloadCalender", alwaysRun=true)
	public void static_addConventions() throws Exception {
		GeneralUtil.logger("Add new conventions");
		GeneralUtil.logger.info("Started Add new convention");
		try {

			String strCalendarsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Conventions']";
			WebElement objCalendars = GeneralUtil.getElement(strCalendarsXpath,
					"xpath", dynamicTimeOut);
			objCalendars.click();

			GeneralUtil.logger
					.info("Navigated to Conventions by selecting the Conventions treeview item.");

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			String strConventions = "Auto" + timeStamp;
			objName.sendKeys(strConventions);

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			GeneralUtil.logger.info("Entered name and clicked on ok button.");
			// Verification for success notification
			Common.handleNotifications("Save Convention");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on save button.");
			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK button on save confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Convention");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strConventions);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"))
					.getText();
			strNewConventions = "CONV." + strConventions;
			if (tradeIdtext.equalsIgnoreCase("CONV." + strConventions)) {
				GeneralUtil.logger.info("Added new Calender:" + strConventions
						+ " Successfully.");

			} else {
				GeneralUtil.logger.error("Unable to add new Calender:"
						+ strConventions);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add Calendars.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_addConventions" })
	public void static_editConventions() throws Exception {
		GeneralUtil.logger("Edit conventions");
		GeneralUtil.logger.info("Started Edit convention");
		try {

			clearAllFilters();

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strNewConventions);

			Thread.sleep(1000);

			GeneralUtil.captureScreenshot();

			driver.findElement(
					By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"))
					.click();
			GeneralUtil.logger.info("Searched with " + strNewConventions
					+ " and Clicked on the convention " + strNewConventions);

			// Right click on element and select context Menue item
			WebElement contextMenuelement1 = GeneralUtil
					.getElement(
							"//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement1);
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			GeneralUtil.logger
					.info("Added new row in parament table by selecting the contextmentItem add.");

			WebElement objName1 = GeneralUtil
					.getElement(
							"//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div",
							"xpath", dynamicTimeOut);
			objName1.click();

			WebElement objInput = GeneralUtil
					.getElement(
							"//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/input",
							"xpath", dynamicTimeOut);
			String StrInput = "AUTOPARA1";
			objInput.sendKeys(StrInput);

			WebElement objValue = GeneralUtil
					.getElement(
							"//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div",
							"xpath", dynamicTimeOut);

			objValue.click();

			WebElement objValueInput = GeneralUtil
					.getElement(
							"//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/input",
							"xpath", dynamicTimeOut);
			// String objValueInput = "1";
			objValueInput.sendKeys("1");

			GeneralUtil.logger.info("Entered name as: " + StrInput
					+ " and value as:1");
			GeneralUtil.captureScreenshot();
			// Click on save button
			WebElement objSaveButton1 = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton1.click();

			GeneralUtil.logger.info("Clicked on save button.");

			GeneralUtil.captureScreenshot();
			WebElement objOkButton3 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton3.click();

			GeneralUtil.logger
					.info("Clicked OK buttion on Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Convention");

			clearAllFilters();

			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement1.clear();
			SearchElement1.sendKeys(strNewConventions);
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"))
					.click();

			GeneralUtil.logger.info("Searched with " + strNewConventions
					+ " and Clicked on the convention " + strNewConventions);

			GeneralUtil.captureScreenshot();
Thread.sleep(1000);
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[contains(@class,'src-components-Dashboard-ReferenceData-Convention-Convention-dark---detailsContainer')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(StrInput)) {
				GeneralUtil.logger.info("Parameter " + StrInput
						+ " is added successfully to " + strNewConventions
						+ " convention while editing it");

			} else {
				GeneralUtil.logger
						.error("Unable edit convention "
								+ strNewConventions
								+ " Parameter "
								+ StrInput
								+ " is not added displayed in convention. displayed paramer is :"
								+ tradeIdtext);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to edit Convention."
					+ e.getMessage());
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_editConventions" })
	public void static_deleteConventions() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new Conventions");
			GeneralUtil.logger.info("Started Delete new Conventions.");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Delete convention");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_deleteConventions", alwaysRun=true)
	public void static_uploadConventions() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Conventions");
		GeneralUtil.logger.info("Stated");
		String strConventionsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Conventions']";
		WebElement objConventions = GeneralUtil.getElement(strConventionsXpath,
				"xpath", dynamicTimeOut);
		objConventions.click();
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("");

		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "NXT Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\Convention\\CMDTY\\USD-COMEX-GCA.nxt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload Conventions template");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadConventions", alwaysRun=true)
	public void static_downloadConventions() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Conventions");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("NXT Download", "Download All",
				"convention(.*).zip", strmenu, strmenu);
		GeneralUtil.logger.info("Conventions downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadConventions", alwaysRun=true)
	public void static_addCounterparties() throws Exception {

		GeneralUtil.logger = Logger.getLogger("Add Counterparties");
		GeneralUtil.logger.info("Stated");
		try {

			String strCalendarsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Counterparties']";
			WebElement objCalendars = GeneralUtil.getElement(strCalendarsXpath,
					"xpath", dynamicTimeOut);
			objCalendars.click();
			GeneralUtil.captureScreenshot();

			GeneralUtil.logger
					.info("Navigated to Counterparties by selecting the Counterparties treeview item.");

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());
			String creditTextBoxValue="test"+timeStamp;
			String ratingTextBoxValue="rating"+timeStamp;

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strNewCounterparties = "Auto" + timeStamp;
			objName.sendKeys(strNewCounterparties);

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();
			GeneralUtil.logger.info("Entered name and clicked on ok button.");
			Thread.sleep(1000);
			WebElement subWindow = GeneralUtil.getElement(
					"//div[starts-with(@class,'src-components-Dashboard-ReferenceData-Counterparty-Counterparty-dark---detailsContainer')]",
					"xpath", dynamicTimeOut);
			subWindow.click();
			WebElement creditKeyTextBox = GeneralUtil.getElement(
					".//*[@class='ReactTabs__TabPanel ReactTabs__TabPanel--selected']/div/div/div[3]/div/div[2]/div/input",
					"xpath", dynamicTimeOut);
			creditKeyTextBox.click();
			creditKeyTextBox.sendKeys(creditTextBoxValue);
			WebElement capitalInfoLabel = GeneralUtil.getElement(
					"//ul[@class='ReactTabs__TabList']//label[text()='Capital Info']",
					"xpath", dynamicTimeOut);
			capitalInfoLabel.click();
			WebElement ratingTextBox = GeneralUtil.getElement(
					"//div[@class='ReactTabs__TabPanel ReactTabs__TabPanel--selected']/div/div/div[1]/div/div[2]/div/input",
					"xpath", dynamicTimeOut);
			ratingTextBox.sendKeys(ratingTextBoxValue);

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("Clicked save button.");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("Clicked ok button on confirmation window.");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strNewCounterparties);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//div[@id='counterParty']//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strNewCounterparties)) {
				GeneralUtil.logger.info("Added new Counterparties:"
						+ tradeIdtext + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new Counterparties:"
						+ strNewCounterparties);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add Counterparties.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addCounterparties" })
	public void static_editCounterparties() throws Exception {

		GeneralUtil.logger = Logger.getLogger("Edit Counterparties");
		GeneralUtil.logger.info("Stated");
		try {
			//TT 45574 - Application change in in build 4.6
			/*try{
				Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				}catch(Exception e){
					
				}*/
			WebElement generalLabel = GeneralUtil.getElement(
					"//ul[@class='ReactTabs__TabList']//label[text()='General']",
					"xpath", dynamicTimeOut);
			generalLabel.click();
			WebElement objNameCounterparty = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());
			objNameCounterparty.clear();
			objNameCounterparty.sendKeys("AUTO" + timeStamp);
			GeneralUtil.logger.info("Edited name from " + strNewCounterparties
					+ " to " + "AUTO" + timeStamp + ".");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("Clicked save button.");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked ok button on confirmation window.");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strNewCounterparties);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//div[@id='counterParty']//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase("AUTO" + timeStamp)) {
				GeneralUtil.logger.info("Added new Counterparties:"
						+ tradeIdtext + " Successfully.");
				strNewCounterparties = "AUTO" + timeStamp;
			} else {
				GeneralUtil.logger.error("Unable to eidt Counterparties:"
						+ "AUTO" + timeStamp);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add Counterparties.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editCounterparties" })
	public void static_deleteCounterparties() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new Counterparties");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("Clicked ok button on delete confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Delete Counterparties");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCounterparties", alwaysRun=true)
	public void static_uploadCounterparties() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Counterparties");
		GeneralUtil.logger.info("Stated");
		String strCounterpartiesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Counterparties']";
		WebElement objCounterparties = GeneralUtil.getElement(
				strCounterpartiesXpath, "xpath", dynamicTimeOut);
		objCounterparties.click();
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("Citigroup");
		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "CSV Upload");
	
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\data\\Counterparties\\Citigroup.csv");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload Counterparties template");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadCounterparties", alwaysRun=true)
	public void static_downloadCounterparties() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Counterparties");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("CSV Download", "Download All",
				"Counterparties(.*).zip", strmenu, strmenu);
		GeneralUtil.logger.info("Counterparties downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadCounterparties", alwaysRun=true)
	public void static_addCurrencies() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Add Currencies");
			GeneralUtil.logger.info("Stated");
			String strCURRENCYsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currencies']";
			WebElement objCalendars = GeneralUtil.getElement(strCURRENCYsXpath,
					"xpath", dynamicTimeOut);
			objCalendars.click();

			GeneralUtil.logger
					.info("Navigated to Currencies by selecting the Currencies treeview item.");

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strCURRENCY = "Auto" + timeStamp;
			objName.sendKeys(strCURRENCY);

			WebElement objRank = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objRank.sendKeys("100");

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			GeneralUtil.logger.info("Entered name as: " + strCURRENCY
					+ " and Rank as: 100.");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger.info("Clicked OK button on confirmation window");
			// Verification for success notification
			Common.handleNotifications("Save Currency");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strCURRENCY);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strCURRENCY)) {
				GeneralUtil.logger.info("Added new Currency:" + strCURRENCY
						+ " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new Currency:"
						+ strCURRENCY);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add Currency.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addCurrencies" })
	public void static_editCurrencies() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Edit Currencies");
			GeneralUtil.logger.info("Stated");
			int intColNumber = Common.getColumnNumber("Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strCURRENCY);

			intColNumber = Common.getColumnNumber("IR Index");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "BBSW");
			GeneralUtil.logger
					.info("Entered BBSW value in as IR Index for currency "
							+ strCURRENCY);

			intColNumber = Common.getColumnNumber("IR Index Tenor");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "6M");
			GeneralUtil.logger
					.info("Entered 6M value in as IR Index Tenor for currency "
							+ strCURRENCY);

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger.info("Clicked OK button on confirmation window");
			// Verification for success notification
			Common.handleNotifications("Save Currency");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strCURRENCY);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strCURRENCY)) {
				GeneralUtil.logger.info("Successfully edited the currency:"
						+ strCURRENCY + ".");
			} else {
				GeneralUtil.logger.error("Unable to edit Currency:"
						+ strCURRENCY);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	public void gridSendKeys(String strXpath, String strTestData) {

		WebElement objName;
		try {
			objName = GeneralUtil.getElement(strXpath, "xpath", dynamicTimeOut);
			objName.click();

			WebElement objInput = GeneralUtil.getElement(strXpath + "/input",
					"xpath", dynamicTimeOut);
			// objInput.clear();
			objInput.sendKeys(strTestData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void gridSendKeysJS(String strXpath, String strTestData) {

		try {

			WebElement objInput1 = GeneralUtil.getElement(strXpath + "/span",
					"xpath", dynamicTimeOut);

			objInput1.click();

			WebElement objInput = GeneralUtil.getElement(strXpath
					+ "/div/input", "xpath", dynamicTimeOut);

			// driver.execute_script("document.getElementById('q').value='value here'")

			// JavascriptExecutor js = (JavascriptExecutor)driver;

			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].setAttribute('value', '"+strTestData+"')",
			// objInput);

			objInput.click();
			objInput.sendKeys(strTestData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "static_editCurrencies" })
	public void static_deleteCurrencies() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new Currencies");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Currency");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCurrencies", alwaysRun=true)
	public void static_uploadCurrencies() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Currencies");
		GeneralUtil.logger.info("Stated");
		String strCounterpartiesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currencies']";
		WebElement objCounterparties = GeneralUtil.getElement(
				strCounterpartiesXpath, "xpath", dynamicTimeOut);
		objCounterparties.click();
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("USD");

		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "NXT Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\Settings\\Currency.nxt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload Currencies template");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadCurrencies", alwaysRun=true)
	public void static_downloadCurrencies() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Currencies");
		GeneralUtil.logger.info("Stated");
		// Right click on element and select context Menue item
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("NXT Download", "Download",
				"SETTINGS.CURRENCY(.*).nxt", strmenu, strmenu);
		GeneralUtil.logger.info("Currencies downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadCurrencies", alwaysRun=true)
	public void static_addCurrencyPair() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Add CurrencyPair");
			GeneralUtil.logger.info("Stated");
			String strCurrencyPairXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currency Pairs']";
			WebElement objCurrencyPair = GeneralUtil.getElement(
					strCurrencyPairXpath, "xpath", dynamicTimeOut);
			objCurrencyPair.click();

			GeneralUtil.logger
					.info("Navigated to Currencies by selecting the CurrencyPair treeview item.");

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strCurrencyPair = "AUTOUSD" + timeStamp;
			objName.sendKeys(strCurrencyPair);

			WebElement objRank = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objRank.sendKeys("100");

			GeneralUtil.logger.info("Entered name as: " + strCurrencyPair
					+ " and Rank as: 100.");
			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();
			GeneralUtil.logger.info("Clicked OK button on confirmation window");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on Save button");
			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			// Verification for success notification
			Common.handleNotifications("Save Currencypair");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strCurrencyPair);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strCurrencyPair)) {
				GeneralUtil.logger.info("Added new CurrencyPair:"
						+ strCurrencyPair + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new CurrencyPair:"
						+ strCurrencyPair);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add CurrencyPair.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addCurrencyPair" })
	public void static_editCurrencyPair() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Edit CurrencyPair");
			GeneralUtil.logger.info("Stated");
			int intColNumber = Common.getColumnNumber("Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strCurrencyPair);

			intColNumber = Common.getColumnNumber("Base Currency");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "USD");
			GeneralUtil.logger
					.info("Entered USD value in as Term Currency for CurrencyPair "
							+ strCurrencyPair);

			intColNumber = Common.getColumnNumber("Term Currency");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "MXN");
			GeneralUtil.logger
					.info("Entered MXN value in as Term Currency for CurrencyPair "
							+ strCurrencyPair);

			GeneralUtil.logger
					.info("Edited : "
							+ strCurrencyPair
							+ "by entering the Base Currency as: USD, Term Currency as:MXN");

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("Clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger.info("Clicked OK button on confirmation window");
			// Verification for success notification
			Common.handleNotifications("Save Currencypair");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strCurrencyPair);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strCurrencyPair)) {
				GeneralUtil.logger.info("Successfully edited the CurrencyPair:"
						+ strCurrencyPair + ".");
			} else {
				GeneralUtil.logger.error("Unable to edit CurrencyPair:"
						+ strCurrencyPair);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editCurrencyPair" })
	public void static_deleteCurrencyPair() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new CurrencyPair");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();
			GeneralUtil.logger.info("Clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Currencypair");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCurrencyPair", alwaysRun=true)
	public void static_uploadCurrencyPair() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload CurrencyPair");
		GeneralUtil.logger.info("Stated");
		String strCurrencyPairXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Currency Pairs']";
		WebElement objCurrencyPair = GeneralUtil.getElement(
				strCurrencyPairXpath, "xpath", dynamicTimeOut);
		objCurrencyPair.click();

		GeneralUtil.logger
				.info("Navigated to Currencies by selecting the CurrencyPair treeview item.");
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("USD");
		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "NXT Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\Settings\\CurrencyPair.nxt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload CurrencyPair template");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadCurrencyPair", alwaysRun=true)
	public void static_downloadCurrencyPair() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download CurrencyPair");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("NXT Download", "Download",
				"SETTINGS.CURRENCY(.*).nxt", strmenu, strmenu);
		GeneralUtil.logger.info("CurrencyPair downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadCurrencyPair", alwaysRun=true)
	public void static_addTimeZone() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Add TimeZone");
			GeneralUtil.logger.info("Stated");
			String strTimeZoneXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Timezones']";
			WebElement objTimeZone = GeneralUtil.getElement(strTimeZoneXpath,
					"xpath", dynamicTimeOut);
			objTimeZone.click();

			GeneralUtil.logger
					.info("Navigated to Currencies by selecting the TimeZone treeview item.");

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strTimeZone = "AUTOUSD" + timeStamp;
			objName.sendKeys(strTimeZone);

			GeneralUtil.captureScreenshot();
			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			GeneralUtil.logger.info("Entered name as: " + strTimeZone
					+ " and clicked on ok button");

			// Verification for success notification
			Common.handleNotifications("Save Timezone TIMEZONE");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strTimeZone);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']//div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strTimeZone)) {
				GeneralUtil.logger.info("Added new TimeZone:" + strTimeZone
						+ " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new TimeZone:"
						+ strTimeZone);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add TimeZone.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addTimeZone" })
	public void static_editTimeZone() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Edit TimeZone");
			GeneralUtil.logger.info("Stated");
			WebElement objName = GeneralUtil
					.getElement(
							"//div[contains(@class,'detailsBorder')]//div/div[2]/div/div/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objName.sendKeys("StandardOffset");

			WebElement objStandardOffset = GeneralUtil
					.getElement(
							"//div[contains(@class,'detailsBorder')]//div/div[2]/div/div/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objStandardOffset.sendKeys("UTC+1");

			WebElement objDSTOffset = GeneralUtil
					.getElement(
							"//div[contains(@class,'detailsBorder')]//div/div[2]/div/div/div/div[3]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objDSTOffset.sendKeys("UTC+2");

			WebElement objChangeTime = GeneralUtil
					.getElement(
							"//div[contains(@class,'detailsBorder')]//div/div[2]/div/div/div/div[4]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			objChangeTime.sendKeys("2:00");

			GeneralUtil.logger
					.info("Entered StandardOffset as: UTC+1, DSTOffset as:UTC+2, ChangeTime as:2:00");
			GeneralUtil.captureScreenshot();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Timezone TIMEZONE");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strTimeZone);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']//div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strTimeZone)) {
				GeneralUtil.logger.info("edited TimeZone:" + strTimeZone
						+ " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to edit TimeZone:"
						+ strTimeZone);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to edit TimeZone.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editTimeZone" })
	public void static_deleteTimeZone() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new TimeZone");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Delete timezone TIMEZONE");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteTimeZone", alwaysRun=true)
	public void static_uploadTimeZone() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload TimeZone");
		GeneralUtil.logger.info("Stated");
		String strTimeZoneXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Timezones']";
		WebElement objTimeZone = GeneralUtil.getElement(strTimeZoneXpath,
				"xpath", dynamicTimeOut);
		objTimeZone.click();
		GeneralUtil.logger
				.info("Navigated to Currencies by selecting the TimeZone treeview item.");

		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("DUBLIN");
		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "NXT Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\TimeZone\\DUBLIN.nxt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload timezone template");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadTimeZone", alwaysRun=true)
	public void static_downloadTimeZone() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download TimeZone");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("NXT Download", "Download All", "timezone(.*).zip",
				strmenu, strmenu);

		GeneralUtil.logger.info("TimeZone downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadTimeZone", alwaysRun=true)
	public void static_addRiskScripts() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Add RiskScripts");
			GeneralUtil.logger.info("Stated");
			String strRiskScriptsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk Scripts']";
			WebElement objRiskScripts = GeneralUtil.getElement(
					strRiskScriptsXpath, "xpath", dynamicTimeOut);
			objRiskScripts.click();

			GeneralUtil.logger
					.info("Navigated to Currencies by selecting the Risk Scripts treeview item.");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			String strRiskScripts = "AUTOUSD" + timeStamp;
			objName.sendKeys(strRiskScripts);

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			GeneralUtil.logger.info("Entered name as: " + strRiskScripts
					+ " and clicked on ok button");
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Script");

			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strRiskScripts);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']//div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strRiskScripts)) {
				GeneralUtil.logger.info("Added new RiskScripts:"
						+ strRiskScripts + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new RiskScripts:"
						+ strRiskScripts);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add RiskScripts.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addRiskScripts" })
	public void static_editRiskScripts() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Edit RiskScripts");
			GeneralUtil.logger.info("Stated");
			WebElement objName = GeneralUtil
					.getElement(
							"//div[contains(@class,'detailsContainer')]//div/div[2]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());
			String strEditRiskScripts = "AUTOUSD" + timeStamp;
			objName.clear();
			objName.sendKeys(strEditRiskScripts);

			GeneralUtil.logger.info("edited " + strRiskScripts + " name form "
					+ strRiskScripts + " to " + strEditRiskScripts);
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Script");
			//TT 45574 - Application change in in build 4.6
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strEditRiskScripts);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']//div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strEditRiskScripts)) {
				GeneralUtil.logger.info("Edited RiskScripts:" + strRiskScripts
						+ " Successfully.");
				strRiskScripts = strEditRiskScripts;
			} else {
				GeneralUtil.logger.error("Unable to edit RiskScripts:"
						+ strEditRiskScripts);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to edit RiskScripts.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editRiskScripts" })
	public void static_deleteRiskScripts() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new RiskScripts");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save RiskScripts");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteRiskScripts", alwaysRun=true)
	public void static_uploadRiskScripts() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Risk Scripts");
		GeneralUtil.logger.info("Stated");
		String strRiskScriptsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk Scripts']";
		WebElement objRiskScripts = GeneralUtil.getElement(strRiskScriptsXpath,
				"xpath", dynamicTimeOut);
		objRiskScripts.click();
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("COLLATERALSCRIPT");
		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "TXT Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\Scripts\\COLLATERALSCRIPT.txt");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload risk script");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadRiskScripts", alwaysRun=true)
	public void static_downloadRiskScripts() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Risk Scripts");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("TXT Download", "Download All",
				"RiskCalculationScript(.*).zip", strmenu, strmenu);
		GeneralUtil.logger.info("RiskS cripts downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	
	@Test(dependsOnMethods = "static_downloadRiskScripts", alwaysRun=true)
	public void static_addReferenceData() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Add ReferenceData");
			GeneralUtil.logger.info("Stated");
			String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Reference Data']";
			WebElement objReferenceData = GeneralUtil.getElement(
					strReferenceDataXpath, "xpath", dynamicTimeOut);
			objReferenceData.click();

			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			WebElement objName = GeneralUtil
					.getElement(
							"//div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			strReferenceData = "AUTO" + timeStamp;
			objName.sendKeys(strReferenceData);

			WebElement objReferenceDataType = GeneralUtil
					.getElement(
							"html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);

			objReferenceDataType.click();

			GeneralUtil
					.selectDropDownItem(
							"html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div",
							"REFERENCEDATA.IR.CURVEOPTIONS",
							"Reference Data Type");

			WebElement objOkButton = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div[2]/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton.click();

			

			WebElement objReferenceDataPara = GeneralUtil
					.getElement(
							".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objReferenceDataPara.sendKeys("AUTOPARA");

			WebElement objReferenceDataDate = GeneralUtil
					.getElement(
							".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div/div/div/div/span/i",
							"xpath", dynamicTimeOut);
			objReferenceDataDate.click();

			// Right click on element and select context Menue item
			WebElement contextMenuelement3 = GeneralUtil
					.getElement(
							"//div[starts-with(@class,'src-components-ModalDialog-Modal-dark')]//*[@ref='center']/div/div[4]/div[3]/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement3);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			WebElement objSaveDiolog = GeneralUtil
					.getElement(
							" //div[starts-with(@class,'src-components-ModalDialog-Modal-dark---container')]//div[text()='Save']",
							"xpath", dynamicTimeOut);
			objSaveDiolog.click();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			// Verification for success notification
			//Common.handleNotifications("Save Reference Data");

			WebElement objCancelButton = GeneralUtil.getElement(
					"//div[text()='Cancel']", "xpath", dynamicTimeOut);
			objCancelButton.click();

			//TT 45574 - Application change in in build 4.6
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strReferenceData);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strReferenceData)) {
				GeneralUtil.logger.info("Added new ReferenceData:"
						+ strReferenceData + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add new ReferenceData:"
						+ strReferenceData);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to add ReferenceData.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_addReferenceData" })
	public void static_editReferenceData() throws Exception {
		try {

			GeneralUtil.logger("Step36 : Edit ReferenceData");
			GeneralUtil.logger.info("Stated");
			driver.findElement(
					By.xpath("/.//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.click();

			WebElement objReferenceDataPara = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input",
							"xpath", dynamicTimeOut);

			objReferenceDataPara.clear();
			objReferenceDataPara.sendKeys("AUTOEditPARA");
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			// Verification for success notification
			Common.handleNotifications("Save Reference Data");

			WebElement objCancelButton = GeneralUtil.getElement(
					"//div[text()='Cancel']", "xpath", dynamicTimeOut);
			objCancelButton.click();

			////TT 45574 - Application change in in build 4.6
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strReferenceData);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strReferenceData)) {
				GeneralUtil.logger.info("Edited ReferenceData:"
						+ strReferenceData + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to edit ReferenceData:"
						+ strReferenceData);
			}

		} catch (Exception e) {
			GeneralUtil.logger.error("Unable to edit ReferenceData.");
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_editReferenceData" })
	public void static_deleteReferenceData() throws Exception {
		try {
			GeneralUtil.logger("Step36 : Delete new ReferenceData");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");

			WebElement objNotificationsH4 = GeneralUtil.getElement(
					"//div[starts-with(@class,'notification')]/h4", "xpath",
					scriptTimeOut);
			String strnotificationsh4 = objNotificationsH4.getText();
			if (strnotificationsh4.contains("Delete Reference Data")) {

				GeneralUtil.logger.info("Displayed notifications is :"
						+ strnotificationsh4);
				GeneralUtil.captureScreenshot();
			} else
				GeneralUtil.logger.info("Delete Reference Data"
						+ " is not displayed in notification");
			

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteReferenceData", alwaysRun=true)
	public void static_uploadReferenceData() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Upload Reference Data");
		GeneralUtil.logger.info("Stated");
		String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Reference Data']";
		WebElement objReferenceData = GeneralUtil.getElement(
				strReferenceDataXpath, "xpath", dynamicTimeOut);
		objReferenceData.click();
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();

		WebElement SearchElement = driver
				.findElement(By
						.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
		SearchElement.clear();
		SearchElement.sendKeys("CDXPROTOTYPE");
		// Right click on element and select context Menue item
		WebElement contextMenuelement = GeneralUtil
				.getElement(
						"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
						"xpath", dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "CSV Upload");
		
		Thread.sleep(1000);
		GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\ReferenceData\\CDXPROTOTYPE.csv");

		Thread.sleep(2000);
		// Verification for success notification
		Common.handleNotifications("Upload Reference Data");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_uploadReferenceData", alwaysRun=true)
	public void static_downloadReferenceData() throws Exception {
		try{
		GeneralUtil.logger = Logger.getLogger("Download Reference Data");
		GeneralUtil.logger.info("Stated");
		// Right click on element and select context Menue item
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("CSV Download", "Download All",
				"ReferenceData(.*).zip", strmenu, strmenu);
		GeneralUtil.logger.info("ReferenceData downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "static_downloadReferenceData", alwaysRun=true)
	public void static_addCapitalSupervisoryParameter() throws Exception {
		try {

			GeneralUtil
					.logger("Step36 :CapitalParameter- Add SupervisoryParameter");
			GeneralUtil.logger.info("Stated");
			String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Capital Parameters']";
			WebElement objReferenceData = GeneralUtil.getElement(
					strReferenceDataXpath, "xpath", dynamicTimeOut);
			objReferenceData.click();
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSupervisoryParameter = "AUTO" + timeStamp;

			Integer intColNumber = Common.getColumnNumber("Asset Class");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							2, intColNumber), strSupervisoryParameter);
			GeneralUtil.logger
					.info("Entered Asset Class value as IR for SupervisoryParameter "
							+ strSupervisoryParameter);

			intColNumber = Common.getColumnNumber("Sub Class");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							2, intColNumber), "Auto");
			GeneralUtil.logger
					.info("Entered Asset Class value as IR for SupervisoryParameter "
							+ strSupervisoryParameter);

			intColNumber = Common.getColumnNumber("Supervisory Factor");
			gridSendKeysJS(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							2, intColNumber), "0.0025");
			GeneralUtil.logger
					.info("Entered Supervisory Factor value as 0.0025 for Supervisory Parameter "
							+ strSupervisoryParameter);
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSupervisoryParameter);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSupervisoryParameter)) {
				GeneralUtil.logger.info("added Supervisory Parameter:"
						+ strSupervisoryParameter + " Successfully.");
			} else {
				GeneralUtil.logger.error("Unable to add Supervisory Parameter:"
						+ strSupervisoryParameter);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_addCapitalSupervisoryParameter" })
	public void static_editCapitalSupervisoryParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 :CapitalParameter- Edit SupervisoryParameter");
			GeneralUtil.logger.info("Stated");
			clearAllFilters();

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSupervisoryParameter);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			int intColNumber = Common.getColumnNumber("Asset Class");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSupervisoryParameter);

			intColNumber = Common
					.getColumnNumber("Supervisory Correlation");
			gridSendKeys(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "0.5");
			GeneralUtil.logger
					.info("Entered Supervisory Correlation as 0.5 for SupervisoryParameter "
							+ strSupervisoryParameter);

			intColNumber = Common
					.getColumnNumber("Supervisory Option Volatility");
			gridSendKeysJS(
					String.format(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div",
							intRowNumber, intColNumber), "1");
			GeneralUtil.logger
					.info("Entered Supervisory Option Volatility value as 1 for SupervisoryParameter "
							+ strSupervisoryParameter);

			GeneralUtil.captureScreenshot();
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement1.clear();
			SearchElement1.sendKeys(strSupervisoryParameter);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSupervisoryParameter)) {
				GeneralUtil.logger.info("Edited Supervisory Parameter:"
						+ strSupervisoryParameter + " Successfully.");
			} else {
				GeneralUtil.logger
						.error("Unable to Edit Supervisory Parameter:"
								+ strSupervisoryParameter);
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_editCapitalSupervisoryParameter" })
	public void static_exportCapitalSupervisoryParameter() throws Exception {
		try{
		GeneralUtil.logger = Logger
				.getLogger("Export Capital -SupervisoryParameter");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver
				.findElement(By
						.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("Export", "CSV Export", "export(.*).csv", strmenu,
				strmenu);
		GeneralUtil.logger
				.info("CapitalSupervisoryParameter downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_exportCapitalSupervisoryParameter" })
	public void static_deleteCapitalSupervisoryParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 : Delete CapitalParameter- Supervisor Parameter");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil
					.getElement(
							"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div",
							"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCapitalSupervisoryParameter", alwaysRun=true)
	public void static_addCapitalSACCRRiskWeightsParameter() throws Exception {
		try {

			GeneralUtil
					.logger("Step36 :CapitalParameter- Add SACCRRiskWeights");
			GeneralUtil.logger.info("Stated");
			String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Capital Parameters']";
			WebElement objReferenceData = GeneralUtil.getElement(
					strReferenceDataXpath, "xpath", dynamicTimeOut);
			objReferenceData.click();

			GeneralUtil.logger
					.info("Navigated to Capital Parameters by selecting the Capital Parameters treeview item.");

			Thread.sleep(1000);
			WebElement objLabelTab = GeneralUtil.getElement(
					"//label[text()='SACCR Risk Weights']", "xpath",
					dynamicTimeOut);
			objLabelTab.click();
			Thread.sleep(1000);
			GeneralUtil.logger
					.info("selected SACCR Risk Weights tab and navigated to SACCR Risk Weights screen.");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div",
					"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSACCRRiskWeights = "AUTO" + timeStamp;

			Integer intColNumber = Common.getColumnNumber("Rating");
			gridSendKeys(
					"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[2]/div[1]/div/div",
					strSACCRRiskWeights);
			GeneralUtil.logger
					.info("Entered Asset Class value as IR for SACCRRiskWeightsParameter "
							+ strSACCRRiskWeights);

			intColNumber = Common.getColumnNumber("Sub Risk Weight Class");
			gridSendKeysJS(
					".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[2]/div[2]/div/div",
					"0.007");
			GeneralUtil.logger
					.info("Entered Sub Risk Weight Class value as 0.007 for SACCRRiskWeightsParameter "
							+ strSACCRRiskWeights);
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSACCRRiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSACCRRiskWeights)) {
				GeneralUtil.logger.info("added SACCRRiskWeights Parameter:"
						+ strSACCRRiskWeights + " Successfully.");
			} else {
				GeneralUtil.logger
						.error("Unable to add SACCRRiskWeights Parameter:"
								+ strSACCRRiskWeights);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_addCapitalSACCRRiskWeightsParameter" })
	public void static_editCapitalSACCRRiskWeightsParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 :CapitalParameter- Edit SACCRRiskWeights");
			GeneralUtil.logger.info("Stated");
			clearAllFilters();

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSACCRRiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			gridSendKeysJS(
					".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div/div",
					"0.078");
			GeneralUtil.logger
					.info("Entered Sub Risk Weight Class value as 0.078 for SACCRRiskWeightsParameter "
							+ strSACCRRiskWeights);

			GeneralUtil.captureScreenshot();
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement1.clear();
			SearchElement1.sendKeys(strSACCRRiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSACCRRiskWeights)) {
				GeneralUtil.logger.info("Edited SACCR Risk Weights Parameter:"
						+ strSACCRRiskWeights + " Successfully.");
			} else {
				GeneralUtil.logger
						.error("Unable to Edit SACCR Risk Weights Parameter:"
								+ strSACCRRiskWeights);
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_editCapitalSACCRRiskWeightsParameter" })
	public void static_exportCapitalSACCRRiskWeightsParameter() throws Exception {
		try{
		GeneralUtil.logger = Logger
				.getLogger("Export Capital -SACCRRiskWeightsParameter");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver.findElement(By
				.xpath("//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("Export", "CSV Export", "export(.*).csv", strmenu,
				strmenu);
		GeneralUtil.logger
				.info("Capital SACCR Risk Weights Parameter downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_exportCapitalSACCRRiskWeightsParameter" })
	public void static_deleteCapitalSACCRRiskWeightsParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 : Delete CapitalParameter- SACCRRiskWeights");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div",
					"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCapitalSACCRRiskWeightsParameter", alwaysRun=true)
	public void static_addCapitalSACVARiskWeightsParameter() throws Exception {
		try {

			GeneralUtil
					.logger("Step36 :CapitalParameter- Add SACVARiskWeights");
			GeneralUtil.logger.info("Stated");
			String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Capital Parameters']";
			WebElement objReferenceData = GeneralUtil.getElement(
					strReferenceDataXpath, "xpath", dynamicTimeOut);
			objReferenceData.click();

			GeneralUtil.logger
					.info("Navigated to Capital Parameters by selecting the Capital Parameters treeview item.");

			Thread.sleep(1000);
			WebElement objLabelTab = GeneralUtil.getElement(
					"//label[text()='SACVA Risk Weights']", "xpath",
					dynamicTimeOut);
			objLabelTab.click();
			Thread.sleep(1000);
			GeneralUtil.logger
					.info("selected SACVA Risk Weights tab and navigated to SACVA Risk Weights screen.");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div",
					"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Add");
			GeneralUtil.captureScreenshot();

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSACVARiskWeights = "AUTO" + timeStamp;

			Integer intColNumber = Common.getColumnNumber("Rating");
			gridSendKeys(
					"//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[2]/div[1]/div/div",
					strSACVARiskWeights);
			GeneralUtil.logger
					.info("Entered Asset Class value as IR for SACVARiskWeightsParameter "
							+ strSACVARiskWeights);

			intColNumber = Common.getColumnNumber("Sub Risk Weight Class");
			gridSendKeysJS(
					".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[2]/div[2]/div/div",
					"0.007");
			GeneralUtil.logger
					.info("Entered Sub Risk Weight Class value as 0.007 for SACVARiskWeightsParameter "
							+ strSACVARiskWeights);

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSACVARiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSACVARiskWeights)) {
				GeneralUtil.logger.info("added SACVARiskWeights Parameter:"
						+ strSACVARiskWeights + " Successfully.");
			} else {
				GeneralUtil.logger
						.error("Unable to add SACVARiskWeights Parameter:"
								+ strSACVARiskWeights);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_addCapitalSACVARiskWeightsParameter" })
	public void static_editCapitalSACVARiskWeightsParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 :CapitalParameter- Edit SACVARiskWeights");
			GeneralUtil.logger.info("Stated");
			clearAllFilters();

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strSACVARiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// intColNumber =
			// CommonTest.getColumnNumber("Sub Risk Weight Class");
			gridSendKeysJS(
					".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[2]/div/div",
					"0.078");
			GeneralUtil.logger
					.info("Entered Sub Risk Weight Class value as 0.078 for SACVARiskWeightsParameter "
							+ strSACVARiskWeights);

			GeneralUtil.captureScreenshot();
			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();

			GeneralUtil.logger
					.info("clicked OK button on confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement1.clear();
			SearchElement1.sendKeys(strSACVARiskWeights);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div/span"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(strSACVARiskWeights)) {
				GeneralUtil.logger.info("Edited SACVA Risk Weights Parameter:"
						+ strSACVARiskWeights + " Successfully.");
			} else {
				GeneralUtil.logger
						.error("Unable to Edit SACVA Risk Weights Parameter:"
								+ strSACVARiskWeights);
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "static_editCapitalSACVARiskWeightsParameter" })
	public void static_exportCapitalSACVARiskWeightsParameter() throws Exception {
		try{
		GeneralUtil.logger = Logger
				.getLogger("Export Capital -SACVARiskWeightsParameter");
		GeneralUtil.logger.info("Stated");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver.findElement(By
				.xpath("//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("Export", "CSV Export", "export(.*).csv", strmenu,
				strmenu);
		GeneralUtil.logger
				.info("Capital SACVA Risk Weights Parameter downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "static_exportCapitalSACVARiskWeightsParameter" })
	public void static_deleteCapitalSACVARiskWeightsParameter() throws Exception {
		try {
			GeneralUtil
					.logger("Step36 : Delete CapitalParameter- SACVARiskWeights");
			GeneralUtil.logger.info("Stated");
			// Right click on element and select context Menue item
			WebElement contextMenuelement = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div",
					"xpath", dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Delete");
			GeneralUtil.captureScreenshot();

			// Click on save button
			WebElement objSaveButton = GeneralUtil.getElement(
					"//div[text()='Save']", "xpath", dynamicTimeOut);
			objSaveButton.click();

			GeneralUtil.logger.info("clicked on Save button");
			GeneralUtil.captureScreenshot();

			WebElement objOkButton2 = GeneralUtil.getElement(
					"//div/div/div/div/div/div[2]/div/div/div[1]/button",
					"xpath", dynamicTimeOut);
			objOkButton2.click();
			GeneralUtil.logger
					.info("Clicked OK buttion on delete Confirmation window.");
			// Verification for success notification
			Common.handleNotifications("Save Capital Parameters");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = "static_deleteCapitalSACVARiskWeightsParameter", alwaysRun=true)
	public void static_exportCapitalSACCRGeneralParameter() throws Exception {
		try{
		GeneralUtil.logger = Logger
				.getLogger("Export Capital -SACCRGeneralParameter");
		GeneralUtil.logger.info("Stated");
		String strReferenceDataXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Capital Parameters']";
		WebElement objReferenceData = GeneralUtil.getElement(
				strReferenceDataXpath, "xpath", dynamicTimeOut);
		objReferenceData.click();

		GeneralUtil.logger
				.info("Navigated to Capital Parameters by selecting the Capital Parameters treeview item.");

		Thread.sleep(1000);
		WebElement objLabelTab = GeneralUtil.getElement(
				"//label[text()='SACCR General Parameters']", "xpath",
				dynamicTimeOut);
		objLabelTab.click();
		Thread.sleep(1000);
		GeneralUtil.logger
				.info("selected SACCR Generak Parameter tab and navigated to SACCR Generak Parameter screen.");
		Thread.sleep(1000);
		GeneralUtil.captureScreenshot();
		WebElement contextMenuelement = driver.findElement(By
				.xpath("//*[@ref='center']/div/div[4]/div[3]//div/div[2]/div"));
		GeneralUtil.contextMenu(contextMenuelement);
		GeneralUtil.captureScreenshot();

		Common.download("Export", "CSV Export", "export(.*).csv", strmenu,
				strmenu);
		GeneralUtil.logger
				.info("Capital SACCR Generak Parameter Parameter downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	public void verifyElementPresent(String strTrade, String strXpath,
			int timeOut) {
		try {
			boolean blnFind = GeneralUtil.waitUntilExists(strXpath, "xpath",
					timeOut);
			if (blnFind)
				GeneralUtil.logger.info(strTrade
						+ " treeview item is displayed.");
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

	public void clearAllFilters() throws Exception {
		// Right click on element and select context Menue item
		WebElement contextMenuelement1 = GeneralUtil.getElement(
				"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
				dynamicTimeOut);
		GeneralUtil.contextMenu(contextMenuelement1);
		GeneralUtil.captureScreenshot();
		GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
		GeneralUtil.captureScreenshot();
		Thread.sleep(1000);
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
		Thread.sleep(3000);
		
	}
}
