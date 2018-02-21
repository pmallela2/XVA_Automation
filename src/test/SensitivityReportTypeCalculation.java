package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import util.Browser;
import util.ExcelUtil;
import util.ExcelUtilHSSF;
import util.GeneralUtil;
import util.PropertyUtils;

public class SensitivityReportTypeCalculation {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut= -1;
	public String strBrowserType = null;
	public Logger logger = null;
	String strSensitivityReport;
	String strPricing;
	String strExposuresReport;
	String strCellID;
	String strTrade = "CC_BermudanSwap";
	String strTradeID = "2";
	String strWhatIfID;
	boolean btnFlag = false;

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
		GeneralUtil.configureLog4j("SensitivityReportTypeCalculation");
		// Assign logger object
		GeneralUtil.logger("SensitivityReportTypeCalculation");
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

	@Test(priority =0)
	public void sensitivityIRDeltaGammaByInstrumentReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Sensitivity IRDeltaGammaByInstrument Report type");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");

			// Click new Calculation button
			WebElement objNewCalculationBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[1]/div/div[1]/button",
							"xpath", dynamicTimeOut);
			objNewCalculationBtn.click();
			Thread.sleep(3000);
			GeneralUtil.logger.info("Clicked on add new Calculation button.");
			GeneralUtil.captureScreenshot();

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSensitivityReport = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();
			objTaskName.sendKeys(strSensitivityReport);
			GeneralUtil.logger.info("Entered Task Name as "
					+ strSensitivityReport);

			// Enter Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();
			// Enter CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Sensitivity Reports", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Sensitivity Reports");

			// Enter Trade Selection-By Expression
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("6");
			GeneralUtil.logger.info("Entered 6 trade ids in Trade Selection");
			GeneralUtil.captureScreenshot();

			// Click on Sensitivity Report Set-Up tab and enter details
			WebElement objSensitivityReportSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Sensitivity Report Set-Up')]",
					"xpath", dynamicTimeOut);
			objSensitivityReportSetUpTab.click();
			GeneralUtil.logger.info("Selected Sensitivity Report Set-Up tab");

			// Click on Report Type
			WebElement objReportType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[1]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportType.click();

			// Enter Report Type item
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDELTAGAMMA", "Report Type");
			GeneralUtil.logger.info("Selected Report type as IRDELTAGAMMA");
			// Click on Report Type
			WebElement objReportName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[2]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportName.click();

			// Enter Report Nameitem
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDeltaGammaByInstrument", "Report Name");
			GeneralUtil.logger
					.info("Selected Report Name as IRDeltaGammaByInstrument");
			
			WebElement objEQSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Scenarios checkbox box.");
			
			WebElement objEQSpotVolScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Vol Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotVolScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Vol Scenarios checkbox box.");
			
			WebElement objFXSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='FX Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objFXSpotScenarios.click();
			GeneralUtil.logger.info("Checked FX Spot Scenarios checkbox box.");
			
			WebElement objIRScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='IR Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objIRScenarios.click();
			GeneralUtil.logger.info("Checked IR Scenarios checkbox box.");
			
			WebElement objScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios checkbox box.");
			
			WebElement objScenariosOnScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios On Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenariosOnScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios On Scenarios checkbox box.");
			
			GeneralUtil.captureScreenshot();

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
			// Click Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");
			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			Thread.sleep(1000);
			
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSensitivityReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;

			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
									intRowNumber, intColNumber)));

			while (count < dynamicTimeOut) {
				 objCellStatus = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intactionColNumber)));
				 txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				/*Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);*/
				count++;
			}
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strSensitivityReport
								+ " Sensitivity Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strSensitivityReport
								+ " Sensitivity Report calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(priority =1)
	public void sensitivityIRDeltaGammaByBucketReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Sensitivity IRDeltaGammaByBucket Report Type");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");

			// Click new Calculation button
			WebElement objNewCalculationBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[1]/div/div[1]/button",
							"xpath", dynamicTimeOut);
			objNewCalculationBtn.click();
			Thread.sleep(3000);
			GeneralUtil.logger.info("Clicked on add new Calculation button.");
			GeneralUtil.captureScreenshot();

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSensitivityReport = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();
			objTaskName.sendKeys(strSensitivityReport);
			GeneralUtil.logger.info("Entered Task Name as "
					+ strSensitivityReport);

			// Enter Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();
			// Enter CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Sensitivity Reports", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Sensitivity Reports");

			// Enter Trade Selection-By Expression
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("6");
			GeneralUtil.logger.info("Entered 6 trade ids in Trade Selection");
			GeneralUtil.captureScreenshot();

			// Click on Sensitivity Report Set-Up tab and enter details
			WebElement objSensitivityReportSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Sensitivity Report Set-Up')]",
					"xpath", dynamicTimeOut);
			objSensitivityReportSetUpTab.click();
			GeneralUtil.logger.info("Selected Sensitivity Report Set-Up tab");

			// Click on Report Type
			WebElement objReportType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[1]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportType.click();

			// Enter Report Type item
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDELTAGAMMA", "Report Type");
			GeneralUtil.logger.info("Selected Report type as IRDELTAGAMMA");
			// Click on Report Type
			WebElement objReportName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[2]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportName.click();

			// Enter Report Nameitem
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDeltaGammaByBucket", "Report Name");
			GeneralUtil.logger
					.info("Selected Report Name as IRDeltaGammaByBucket");
			
			WebElement objEQSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Scenarios checkbox box.");
			
			WebElement objEQSpotVolScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Vol Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotVolScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Vol Scenarios checkbox box.");
			
			WebElement objFXSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='FX Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objFXSpotScenarios.click();
			GeneralUtil.logger.info("Checked FX Spot Scenarios checkbox box.");
			
			WebElement objIRScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='IR Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objIRScenarios.click();
			GeneralUtil.logger.info("Checked IR Scenarios checkbox box.");
			
			WebElement objScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios checkbox box.");
			
			WebElement objScenariosOnScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios On Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenariosOnScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios On Scenarios checkbox box.");
			
			GeneralUtil.captureScreenshot();

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
			// Click Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");
			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			Thread.sleep(1000);
			
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSensitivityReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;

			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
									intRowNumber, intColNumber)));

			while (count < dynamicTimeOut) {
				 objCellStatus = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intactionColNumber)));
				 txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				/*Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);*/
				count++;
			}
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strSensitivityReport
								+ " Sensitivity Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strSensitivityReport
								+ " Sensitivity Report calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(priority =2)
	public void sensitivityIRDeltaGammaByCurveReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Sensitivity IRDeltaGammaByCurve Report Type");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");

			// Click new Calculation button
			WebElement objNewCalculationBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[1]/div/div[1]/button",
							"xpath", dynamicTimeOut);
			objNewCalculationBtn.click();
			Thread.sleep(3000);
			GeneralUtil.logger.info("Clicked on add new Calculation button.");
			GeneralUtil.captureScreenshot();

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSensitivityReport = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();
			objTaskName.sendKeys(strSensitivityReport);
			GeneralUtil.logger.info("Entered Task Name as "
					+ strSensitivityReport);

			// Enter Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();
			// Enter CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Sensitivity Reports", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Sensitivity Reports");

			// Enter Trade Selection-By Expression
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("6");
			GeneralUtil.logger.info("Entered 6 trade ids in Trade Selection");
			GeneralUtil.captureScreenshot();

			// Click on Sensitivity Report Set-Up tab and enter details
			WebElement objSensitivityReportSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Sensitivity Report Set-Up')]",
					"xpath", dynamicTimeOut);
			objSensitivityReportSetUpTab.click();
			GeneralUtil.logger.info("Selected Sensitivity Report Set-Up tab");

			// Click on Report Type
			WebElement objReportType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[1]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportType.click();

			// Enter Report Type item
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDELTAGAMMA", "Report Type");
			GeneralUtil.logger.info("Selected Report type as IRDELTAGAMMA");
			// Click on Report Type
			WebElement objReportName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[2]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportName.click();

			// Enter Report Nameitem
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDeltaGammaByCurve", "Report Name");
			GeneralUtil.logger
					.info("Selected Report Name as IRDeltaGammaByCurve");
			
			WebElement objEQSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Scenarios checkbox box.");
			
			WebElement objEQSpotVolScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Vol Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotVolScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Vol Scenarios checkbox box.");
			
			WebElement objFXSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='FX Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objFXSpotScenarios.click();
			GeneralUtil.logger.info("Checked FX Spot Scenarios checkbox box.");
			
			WebElement objIRScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='IR Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objIRScenarios.click();
			GeneralUtil.logger.info("Checked IR Scenarios checkbox box.");
			
			WebElement objScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios checkbox box.");
			
			WebElement objScenariosOnScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios On Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenariosOnScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios On Scenarios checkbox box.");
			
			GeneralUtil.captureScreenshot();

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
			// Click Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");
			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			Thread.sleep(1000);
			
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSensitivityReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;

			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
									intRowNumber, intColNumber)));

			while (count < dynamicTimeOut) {
				 objCellStatus = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intactionColNumber)));
				 txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				/*Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);*/
				count++;
			}
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strSensitivityReport
								+ " Sensitivity Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strSensitivityReport
								+ " Sensitivity Report calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(priority =3)
	public void sensitivityIRDeltaGammaByInstrumentandBucketReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Sensitivity IRDeltaGammaByInstrumentandBucket Report Type");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");

			// Click new Calculation button
			WebElement objNewCalculationBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[1]/div/div[1]/button",
							"xpath", dynamicTimeOut);
			objNewCalculationBtn.click();
			Thread.sleep(3000);
			GeneralUtil.logger.info("Clicked on add new Calculation button.");
			GeneralUtil.captureScreenshot();

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSensitivityReport = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();
			objTaskName.sendKeys(strSensitivityReport);
			GeneralUtil.logger.info("Entered Task Name as "
					+ strSensitivityReport);

			// Enter Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();
			// Enter CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Sensitivity Reports", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Sensitivity Reports");

			// Enter Trade Selection-By Expression
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("6");
			GeneralUtil.logger.info("Entered 6 trade ids in Trade Selection");
			GeneralUtil.captureScreenshot();

			// Click on Sensitivity Report Set-Up tab and enter details
			WebElement objSensitivityReportSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Sensitivity Report Set-Up')]",
					"xpath", dynamicTimeOut);
			objSensitivityReportSetUpTab.click();
			GeneralUtil.logger.info("Selected Sensitivity Report Set-Up tab");

			// Click on Report Type
			WebElement objReportType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[1]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportType.click();

			// Enter Report Type item
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDELTAGAMMA", "Report Type");
			GeneralUtil.logger.info("Selected Report type as IRDELTAGAMMA");
			// Click on Report Type
			WebElement objReportName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[2]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportName.click();

			// Enter Report Nameitem
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"IRDeltaGammaByInstrumentandBucket", "Report Name");
			GeneralUtil.logger
					.info("Selected Report Name as IRDeltaGammaByInstrumentandBucket");
			
			WebElement objEQSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Scenarios checkbox box.");
			
			WebElement objEQSpotVolScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='EQ Spot Vol Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objEQSpotVolScenarios.click();
			GeneralUtil.logger.info("Checked EQ Spot Vol Scenarios checkbox box.");
			
			WebElement objFXSpotScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='FX Spot Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objFXSpotScenarios.click();
			GeneralUtil.logger.info("Checked FX Spot Scenarios checkbox box.");
			
			WebElement objIRScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='IR Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objIRScenarios.click();
			GeneralUtil.logger.info("Checked IR Scenarios checkbox box.");
			
			WebElement objScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios checkbox box.");
			
			WebElement objScenariosOnScenarios = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Scenarios On Scenarios']/following::label[1]",
							"xpath", dynamicTimeOut);
			objScenariosOnScenarios.click();
			GeneralUtil.logger.info("Checked Scenarios On Scenarios checkbox box.");
			
			GeneralUtil.captureScreenshot();

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
			// Click Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");
			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			Thread.sleep(1000);
			
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSensitivityReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;

			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
									intRowNumber, intColNumber)));

			while (count < dynamicTimeOut) {
				 objCellStatus = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intactionColNumber)));
				 txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				/*Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);*/
				count++;
			}
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strSensitivityReport
								+ " Sensitivity Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strSensitivityReport
								+ " Sensitivity Report calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(priority =4)
	public void sensitivityCRJumpToDefaultReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Sensitivity CRJumpToDefault Report Type");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");

			// Click new Calculation button
			WebElement objNewCalculationBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[1]/div/div[1]/button",
							"xpath", dynamicTimeOut);
			objNewCalculationBtn.click();
			Thread.sleep(3000);
			GeneralUtil.logger.info("Clicked on add new Calculation button.");
			GeneralUtil.captureScreenshot();

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strSensitivityReport = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();
			objTaskName.sendKeys(strSensitivityReport);
			GeneralUtil.logger.info("Entered Task Name as "
					+ strSensitivityReport);

			// Enter Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();
			// Enter CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Sensitivity Reports", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Sensitivity Reports");

			// Enter Trade Selection-By Expression
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("6");
			GeneralUtil.logger.info("Entered 6 trade ids in Trade Selection");
			GeneralUtil.captureScreenshot();

			// Click on Sensitivity Report Set-Up tab and enter details
			WebElement objSensitivityReportSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Sensitivity Report Set-Up')]",
					"xpath", dynamicTimeOut);
			objSensitivityReportSetUpTab.click();
			GeneralUtil.logger.info("Selected Sensitivity Report Set-Up tab");

			// Click on Report Type
			WebElement objReportType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[1]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportType.click();

			// Enter Report Type item
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"CRJUMPTODEFAULT", "Report Type");
			GeneralUtil.logger.info("Selected Report type as CRJUMPTODEFAULT");
			// Click on Report Type
			WebElement objReportName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div[contains(@class,'settingGroup')]/div[2]//div/span[2]",
							"xpath", dynamicTimeOut);
			objReportName.click();

			// Enter Report Nameitem
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"CRJumpToDefault", "Report Name");
			GeneralUtil.logger
					.info("Selected Report Name as CRJumpToDefault");
			GeneralUtil.captureScreenshot();

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
			// Click Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");
			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			Thread.sleep(1000);
			
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strSensitivityReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;

			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
									intRowNumber, intColNumber)));

			while (count < dynamicTimeOut) {
				 objCellStatus = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intactionColNumber)));
				 txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				/*Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);*/
				count++;
			}
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strSensitivityReport
								+ " Sensitivity Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strSensitivityReport
								+ " Sensitivity Report calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(priority =1)
	public void sensitivityReportCalculation_calculationsSensitivitiesBucketbyInstrument() throws Exception {
		try {
			GeneralUtil
					.logger("Step52 :Risk bookmark validation for IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk']"))
					.click();
			driver.switchTo()
					.frame(driver.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[2]/div/iframe")));

			// Navigate to Market Risk>Sensitivities>By Report Type
			if (driver.findElement(
					By.xpath("//div[@class='GOJU4GFOT']/table/tbody/tr/td[3]"))
					.isDisplayed()) {
				clickExpandMarketRisk("Market Risk", "Sensitivities",
						"By Report Type");

			} else {
				driver.findElement(
						By.xpath("//div[@class='GOJU4GFFR']/div[2]/div/table/tbody/tr/td[1]"))
						.click();
				driver.findElement(
						By.xpath("//div[@class='gwt-MenuBar gwt-MenuBar-vertical']/table/tbody/tr/td"))
						.click();
				clickExpandMarketRisk("Market Risk", "Sensitivities",
						"By Report Type");
			}

			// IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk
			// Factor bookmark validation
			WebElement ele=driver.findElement(By.xpath("//div[@class='gwt-HTML']/span[text()='IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor']"));
			/*Actions action = new Actions(driver).doubleClick(ele);
			action.build().perform();*/
			ele.click();
			ele.click();
			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			
			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"PV.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs value "
								+ strCellData
								+ " is displayed in the Risk IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs value is not displayed in the Risk IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs page and failed");
			}
		
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs verified successfully");
		} else {
			/*GeneralUtil.logger
					.error("Risk bookmark IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs not verified");*/
			throw new Exception("Risk bookmark IR Delta/Gamma by Bucket by Instrument - View by Trade by Risk Factor PVs not verified");
			
		}
	}

	@Test(priority =2)
	public void sensitivityReportCalculation_calculationsSensitivitiesTopdownRisk() throws Exception {
		try {
			GeneralUtil
					.logger("Step52 :Risk bookmark validation for Top-down Risk");
			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Top-down Risk']"))
					.click();
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Top-down Risk']"))
					.click();

			// Risk Top-down Risk bookmark validation
			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"IR Delta",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber+1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger.info("Top-down Risk PVs value "
						+ strCellData
						+ " is displayed in the Risk Top-down Risk PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Top-down Risk PVs value is not displayed in the Risk Top-down Risk PVs page and failed");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Top-down Risk PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Top-down Risk PVs not verified");
		}
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
	}

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

	public void clickExpandMarketRisk(String expValue, String subExpValue,
			String innerSubExpValue) {
		WebElement expandElement = driver.findElement(By
				.xpath("//div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
		expandElement.click();
		List<WebElement> txtPublicElement = driver.findElements(By
				.xpath("//div[@class='gwt-Tree']/div[3]/div/div"));
		int i = 0;
		for (i = 0; i <= txtPublicElement.size(); i++) {
			String publicText = txtPublicElement.get(i).getText();

			if (publicText.contains(expValue)) {
				WebElement subPublicExpand = driver
						.findElement(By.xpath(String
								.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/table/tbody/tr/td[1]",
										i + 1)));
				subPublicExpand.click();
				break;

			}
		}
		List<WebElement> txtSubElement = driver.findElements(By.xpath(String
				.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/div/div",
						i + 1)));
		int j = 0;
		for (j = 0; j <= txtSubElement.size(); j++) {
			String subText = txtSubElement.get(j).getText();

			if (subText.contains(subExpValue)) {
				WebElement subCounterpartyRiskExpand = driver
						.findElement(By.xpath(String
								.format("//div[@style='white-space: nowrap;']/div[%d]/div/div[%d]/table/tbody/tr/td[1]",
										i + 1, j + 1)));
				subCounterpartyRiskExpand.click();
				break;

			}
		}

		List<WebElement> txtInnerSubElement = driver
				.findElements(By.xpath(String
						.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/div/div[%d]/div/div",
								i + 1, j + 1)));
		for (int k = 0; k <= txtInnerSubElement.size(); k++) {
			String subInnerText = txtInnerSubElement.get(k).getText();

			if (subInnerText.contains(innerSubExpValue)) {
				WebElement subCMarketRiskExpand = driver
						.findElement(By.xpath(String
								.format("//div[@style='white-space: nowrap;']/div[%d]/div/div[%d]/div/div[%d]/table/tbody/tr/td[1]",
										i + 1, j + 1, k + 1)));
				subCMarketRiskExpand.click();
				break;

			}
		}
	}

	public boolean verifyTable() throws Exception {

		int hederNumbers = Common
				.getTableColumnNumbers("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr/th");

		if (hederNumbers > 1) {
			return true;
		}
		return false;

	}

}
