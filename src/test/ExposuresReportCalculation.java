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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import util.Browser;
import util.ExcelUtil;
import util.ExcelUtilHSSF;
import util.GeneralUtil;
import util.PropertyUtils;

public class ExposuresReportCalculation {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut= -1;
	public String strBrowserType = null;
	public Logger logger = null;
	String strSensitivityReport;
	String strPricing;
	String strExposuresReport;
	String strRegulatoryReport;
	String strCellID;
	String strTrade = "CC_BermudanSwap";
	String strTradeID = "2";
	String strWhatIfID;
	boolean btnFlag = false;
	String selectedMarketsDate;

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
		GeneralUtil.configureLog4j("ExposuresReportCalculation");
		// Assign logger object
		GeneralUtil.logger("ExposuresReportCalculation");
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
	public void exposuresReportCalculation_Create() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Exposure report");
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

			// Enter details in Job Set-Up tab after click on Add new
			// Calculation button
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
					.format(new Date());

			strExposuresReport = "AUTO" + timeStamp;
			Thread.sleep(1000);

			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();

			objTaskName.sendKeys(strExposuresReport);
			
			GeneralUtil.logger.info("Entered Task Name as "
					+ strExposuresReport);
			// Click on Calculation Type
						WebElement objSelectedMarkets = driver.findElement(By.xpath(".//*[@class='ReactTabs react-tabs']/div/div/div[2]/div/div/div[1]/div/div[2]/div/div/div/div/div/span[1]/div[1]/span[2]"));
						selectedMarketsDate=objSelectedMarkets.getText();
						selectedMarketsDate=selectedMarketsDate.substring(1, 11);
			
			
			//Get the date from Selected Markets
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();

			

			// Select CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Exposures", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Exposures Reports");
			
			
			/*driver.findElement(
					By.xpath(".//*[@class='ReactTabs react-tabs']/div/div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[3]")).click();
			
			driver.findElement(
					By.xpath("//div[@class='Select-menu-outer']/div/div[1]/div/div/div[3]")).click();*/
			
			// Enter trade ids
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("2,3");
			GeneralUtil.logger.info("Entered 2,3 trade ids in Trade Selection");

			// Click on Calculate Risk Measures checkbox and verify Risk
			// Measures Set-Up tab is displayed or not
			WebElement objCheckbox = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate Risk Measures']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCheckbox.click();
			GeneralUtil.logger
					.info("Chacked Calculate Risk Measures check box.");
			GeneralUtil.captureScreenshot();

			// Click on Risk Measures Set-Up tab and enter details
			WebElement objRiskMeasuresSetUpTab = GeneralUtil.getElement(
					"//label[contains(text(),'Risk Measures Set-Up')]",
					"xpath", dynamicTimeOut);
			objRiskMeasuresSetUpTab.click();
			GeneralUtil.logger.info("Selected Risk Measures Set-Up tab");

			GeneralUtil.captureScreenshot();
			WebElement objCalculateXVA = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate XVA']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculateXVA.click();
			GeneralUtil.logger.info("Checked Calculate XVA checkbox box.");

			WebElement objCalculatePFE = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate PFE']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculatePFE.click();
			GeneralUtil.logger.info("Chacked Calculate PFE checkbox box.");

			WebElement objCalculateIncrementalMeasures = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate Incremental Measures']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculateIncrementalMeasures.click();
			GeneralUtil.logger
					.info("Checked Calculate Incremental Measures checkbox box.");

			WebElement objCalculatePreMarginMeasures = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate Pre-Margin Measures']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculatePreMarginMeasures.click();
			GeneralUtil.logger
					.info("Chacked Calculate Pre-Margin Measures checkbox box.");

			WebElement objCalculateKVAforSACCR = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate KVA for SA-CCR']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculateKVAforSACCR.click();
			GeneralUtil.logger
					.info("Chacked Calculate KVA for SA-CCR checkbox box.");

			WebElement objStandaloneTradeMeasures = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Standalone Trade Measures']/following::label[1]",
							"xpath", dynamicTimeOut);
			objStandaloneTradeMeasures.click();
			GeneralUtil.logger
					.info("Chacked Standalone Trade Measures checkbox box.");

			WebElement objCalculateMarginal = GeneralUtil
					.getElement(
							"//div/span/label/span[text()='Calculate Marginal']/following::label[1]",
							"xpath", dynamicTimeOut);
			objCalculateMarginal.click();
			GeneralUtil.logger.info("Chacked Calculate Marginal checkbox box.");

			GeneralUtil.captureScreenshot();
			
		/*	// Click on Regulatory Capital Set-Up tab and enter details
						WebElement objRegulatoryCapitalSetUpTab = GeneralUtil.getElement(
								"//label[contains(text(),'Regulatory Capital Set-Up')]",
								"xpath", dynamicTimeOut);
						objRegulatoryCapitalSetUpTab.click();
						GeneralUtil.logger.info("Selected Regulatory Capital Set-Up");
						
						
			// Click on Run BASEL III CVA Calculations checkbox and verify Risk
						WebElement objRunBASELIIICVACalculationsCheckbox = GeneralUtil
									.getElement(
											"//div/span/label/span[text()='Run BASEL III CVA Calculations']/following::label[1]",
											"xpath", dynamicTimeOut);
						objRunBASELIIICVACalculationsCheckbox.click();*/
			
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
			// Click on Calculate button
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
			
			Thread.sleep(20000);
			// is conpleted in status column
			int intColNumber = Common.getColumnNumber("Task Name");
			int intIDColNumber = Common.getColumnNumber("ID");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strExposuresReport);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");

			
			/* * String tradeIdtext = driver .findElement( By.xpath(String.format(
			 * "//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/div/span"
			 * , intactionColNumber,intRowNumber) )) .getText();*/
			 

			int count = 1;
			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
									intRowNumber, intactionColNumber)));

			while (count < dynamicTimeOut) {
				objCellStatus = driver
						.findElement(By.xpath(String
								.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
										intRowNumber, intactionColNumber)));
				String txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("100%")) {
					break;
				}
				Thread.sleep(2000);
				//TT 45574 - Application change in in build 4.6 
				Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);
				count++;
			}
			String txtStatus = objCellStatus.getText();
			
			/*Thread.sleep(1000);
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strExposuresReport);
			int intactionColNumber = Common.getColumnNumber("Status");

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
				String txtStatus = objCellStatus.getText();
				System.out.println(count + ";" + txtStatus);
				Thread.sleep(2000);
				if (txtStatus.equals("Completed")) {
					break;
				}
				Thread.sleep(2000);
				Common
						.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
				Thread.sleep(2000);
				count++;
			}
			String txtStatus = objCellStatus.getText();*/
			GeneralUtil.captureScreenshot();
			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger.info(strExposuresReport
						+ " Exposures Report calculation status is completed");
				WebElement objCellID = driver
						.findElement(By.xpath(String
								.format("//div[contains(@class,'src-components-Dashboard-Calculation-CalculationBrowser-TasksView')]//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div//span",
										intRowNumber, intColNumber-1)));
				strCellID = objCellID.getText();

			} else {
				GeneralUtil.logger
						.error(strExposuresReport
								+ " Exposures Report calculation status is not completed.Displayed status is "
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
	public void exposuresReportCalculation_submitWhatIfAtTradesScreen() throws Exception {
		try {
			GeneralUtil.logger("Submit What-If At Trades Screen");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Trades screen
			String strTradesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li[1]/div/div/div[text()='Trades']";
			WebElement objCalculation = GeneralUtil.getElement(strTradesXpath,
					"xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(4000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to trade by selecting the Trades treeview item.");
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

			// Open what-if grid by clicking on what-if button
			WebElement objWhatIfBtn = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[1]/div/div[2]/div/div[2]/div[2]/div/div",
							"xpath", dynamicTimeOut);
			objWhatIfBtn.click();
			Thread.sleep(4000);
			GeneralUtil.logger.info("Clicked on What-If button.");

			// Select Exposures
			WebElement objExposures = GeneralUtil
					.getElement(
							".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div/div/span[2]/span",
							"xpath", dynamicTimeOut);
			objExposures.click();
			Thread.sleep(1000);
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Exposures: " + strCellID + " - " + strExposuresReport+", Main, "+selectedMarketsDate,
					"Exposures");
			GeneralUtil.logger.info("Selected Exposures as  Exposures: "
					+ strCellID + " - " + strExposuresReport);

			// Select Counterparties checkbox at grid column
/*			Thread.sleep(1000);
			WebElement objCounterparties = GeneralUtil
					.getElement(
							"//div[1]/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div[3]/div/div[1]/div[2]/span/span[2]",
							"xpath", dynamicTimeOut);
			objCounterparties.click();*/
			
			WebElement objMainCounterparties = GeneralUtil
					.getElement(
							"//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]/i",
							"xpath", dynamicTimeOut);
			objMainCounterparties.click();
			Thread.sleep(1000);
			WebElement objCounterparties = GeneralUtil
					.getElement(
							"//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div[2]/div[2]/span/span[1]",
							"xpath", dynamicTimeOut);
			objCounterparties.click();
			Thread.sleep(1000);
			// Search in trade grid at first column
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(strTradeID);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			// Click on check box at trade id column cell
			WebElement objTradeidCheckBox = GeneralUtil
					.getElement(
							".//*[@ref='center']/div/div[4]/div[1]/div/div[1]/div/span/span[1]/span[2]",
							"xpath", dynamicTimeOut);
			objTradeidCheckBox.click();
			Thread.sleep(1000);
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
			GeneralUtil.captureScreenshot();
			// Click Submit what-if button
			WebElement objSubmitWhatIf = GeneralUtil
					.getElement(
							"//div[text()='Submit What-If']//parent::div/parent::button",
							"xpath", dynamicTimeOut);
			objSubmitWhatIf.click();
			GeneralUtil.logger.info("Clicked on Submit what-if button.");

			Thread.sleep(1000);

			String strnotifications1 = "";

			try {
				WebElement objNotificationsTest = GeneralUtil
						.getElement(
								"//div[@class='notifications-wrapper']//h4[text()='Calculation Submitted']//following-sibling::div[@class='notification-message']",
								"xpath", 200);
				strnotifications1 = objNotificationsTest.getText();
				System.out.println(strnotifications1);
				WebElement objNotificationsH4 = GeneralUtil
						.getElement(
								"//div[@class='notifications-wrapper']//h4[text()='Calculation Submitted']",
								"xpath", scriptTimeOut);
				String strnotifications = objNotificationsH4.getText() + ":"
						+ strnotifications1;
				if (strnotifications.contains("Calculation Submitted")) {

					GeneralUtil.logger.info("Displayed notifications is :"
							+ strnotifications);
					GeneralUtil.captureScreenshot();

					for (int i = strnotifications1.length(); i > 0; i--) {
						if (strnotifications1.charAt(i - 1) == ' ') {
							strnotifications1.substring(i + 1,
									strnotifications1.length());
							System.out.println(strnotifications1.substring(i,
									strnotifications1.length() - 2));
							strWhatIfID = strnotifications1.substring(i,
									strnotifications1.length() - 2);
							System.out.println(strWhatIfID);
							break;
						}
					}
				} else {
					GeneralUtil.logger
							.info("Calculation Submitted"
									+ " is not displayed in notification.Displayed message is : "
									+ strnotifications);
					// return false;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch blockF
				e.printStackTrace();

			}

			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			// Navigate to Calculations screen
			String strTradesXpath1 = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation1 = GeneralUtil.getElement(
					strTradesXpath1, "xpath", dynamicTimeOut);
			objCalculation1.click();
			Thread.sleep(2000);
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger
					.info("Navigated to Calculations by selecting the Calculations treeview item.");
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// System.out.println("strWhatIfID" + strWhatIfID);
			WebElement SearchElement1 = driver
					.findElement(By
							.xpath("html/body/div[1]/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			// SearchElement1.clear();
			SearchElement1.sendKeys(strWhatIfID);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			// is conpleted in status column
			int intactionColNumber = Common.getColumnNumberForJobProgress("Jobs Progress");
			Thread.sleep(1000);
			String txtStatus = "";
			int count = 1;
			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
									1, intactionColNumber)));

			while (count < dynamicTimeOut) {
				objCellStatus = driver
						.findElement(By.xpath(String
								.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
										1, intactionColNumber)));
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

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger.info("WhatIf-Dashboard_Trade_" + strWhatIfID
						+ " calculation status is completed");
			} else {
				GeneralUtil.logger
						.error("WhatIf-Dashboard_Trade_"
								+ strWhatIfID
								+ " calculation status is not completed.Displayed status is "
								+ txtStatus);
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);

		}

	}

	//@Test(priority =2)
	public void exposuresReportCalculation_calculationsExposuresCapitalCVABaselIII() throws Exception {

		try {

			GeneralUtil.logger
					.info("Step56 :Risk bookmark validation for Capital CVA Basel III");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk']"))
					.click();
			driver.switchTo()
					.frame(driver.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[2]/div/iframe")));

			// Capital bookmark validation

			if (driver.findElement(
					By.xpath("//div[@class='GOJU4GFOT']/table/tbody/tr/td[3]"))
					.isDisplayed()) {
				clickExpandCounterpartyRiskCapital("Counterparty Risk", "Capital");

			} else {
				driver.findElement(
						By.xpath("//div[@class='GOJU4GFFR']/div[2]/div/table/tbody/tr/td[1]"))
						.click();
				driver.findElement(
						By.xpath("//div[@class='gwt-MenuBar gwt-MenuBar-vertical']/table/tbody/tr/td"))
						.click();
				clickExpandCounterpartyRiskCapital("Counterparty Risk", "Capital");
			}

			// Capital CVA Basel III bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Capital CVA Basel III']"))
					.click();
			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin Average PFE.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Capital CVA Basel III PFE value "
								+ strCellData
								+ " is displayed in the Risk Capital CVA Basel III PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Capital CVA Basel III PFE value is not displayed in the Risk Capital CVA Basel III PFE page and failed");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Capital CVA Basel III PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Capital CVA Basel III PFE not verified");
		}

	}

	@Test(priority =2)
	public void exposuresReportCalculation_calculationsExposuresCapitalSACCRbyCounterparty() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Capital SA-CCR by Counterparty");
			
	driver.findElement(
			By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk']"))
			.click();
	driver.switchTo()
			.frame(driver.findElement(By
					.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[2]/div/iframe")));

	// Capital bookmark validation

	if (driver.findElement(
			By.xpath("//div[@class='GOJU4GFOT']/table/tbody/tr/td[3]"))
			.isDisplayed()) {
		clickExpandCounterpartyRiskCapital("Counterparty Risk", "Capital");

	} else {
		driver.findElement(
				By.xpath("//div[@class='GOJU4GFFR']/div[2]/div/table/tbody/tr/td[1]"))
				.click();
		driver.findElement(
				By.xpath("//div[@class='gwt-MenuBar gwt-MenuBar-vertical']/table/tbody/tr/td"))
				.click();
		clickExpandCounterpartyRiskCapital("Counterparty Risk", "Capital");
	}
			// Capital SA-CCR by Counterparty bookmark validation

			/*driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);*/
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Capital SA-CCR by Counterparty']"))
					.click();
			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"PFE",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Capital SA-CCR by Counterparty PFE value "
								+ strCellData
								+ " is displayed in the Risk Capital SA-CCR by Counterparty PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Capital SA-CCR by Counterparty PFE value is not displayed in the Risk Capital SA-CCR by Counterparty PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Capital SA-CCR by Counterparty PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Capital SA-CCR by Counterparty PFE not verified");
		}
	}

	@Test(priority =3)
	public void exposuresReportCalculation_calculationsExposuresCapitalSACCRbyNettingSet() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Capital SA-CCR by Netting Set");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			// Capital SA-CCR by Netting Set bookmark validation

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Capital SA-CCR by Netting Set']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"PFE",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Capital SA-CCR by Netting Set PFE value "
								+ strCellData
								+ " is displayed in the Risk Capital SA-CCR by Netting Set PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Capital SA-CCR by Netting Set PFE value is not displayed in the Risk Capital SA-CCR by Netting Set PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Capital SA-CCR by Netting Set PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Capital SA-CCR by Netting Set PFE not verified");
		}
	}

	@Test(priority =4)
	public void exposuresReportCalculation_calculationsExposuresNettingSetSACCR() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Netting Set SA-CCR");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			// Netting Set SA-CCR bookmark validation

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Netting Set SA-CCR']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"PFE",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber + 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Netting Set SA-CCR PFE value "
								+ strCellData
								+ " is displayed in the Risk Netting Set SA-CCR PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Netting Set SA-CCR PFE value is not displayed in the Risk Netting Set SA-CCR PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Netting Set SA-CCR PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Netting Set SA-CCR PFE not verified");
		}
	}

	@Test(priority =5)
	public void exposuresReportCalculation_calculationsExposuresAveragePFE() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Average PFE EPE ENE EE by Counterparty");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			// PFE bookmark validation
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			WebElement closeElement = driver
					.findElement(By
							.xpath("//div[@style='white-space: nowrap;']/div[3]/div/div[1]/table/tbody/tr/td[1]"));
			closeElement.click();
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//div[@class='gwt-Tree']/div[3]/div/div[3]/table/tbody/tr/td[1]"))
					.click();
			Thread.sleep(1000);
			WebElement expandElement = driver
					.findElement(By
							.xpath("//div[@class= 'gwt-TabPanelBottom']/div[2]/div/table/tbody/tr[2]/td/div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
			expandElement.click();
			Thread.sleep(1000);
			clickExpandCounterpartyRisk("Counterparty Risk", "PFE");

			// Average PFE EPE ENE EE by Counterparty bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Average PFE/E-E by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin Average ENE.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Average PFE EPE ENE EE by Counterparty PVs value "
								+ strCellData
								+ " is displayed in the Risk Average PFE/E-E by Counterparty PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Average PFE/E-E by Counterparty PFE value is not displayed in the Risk Average PFE EPE ENE EE by Counterparty PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Average PFE/E-E by Counterparty PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Average PFE/E-E by Counterparty PFE not verified");
		}
	}

	@Test(priority =6)
	public void exposuresReportCalculation_calculationsExposuresEffectivePFE() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Effective PFE EPE ENE EE by Counterparty");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			// Effective PFE EPE ENE EE by Counterparty bookmark validation

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Effective PFE/E-E by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin Effective EPE.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Effective PFE EPE ENE EE by Counterparty PFE value "
								+ strCellData
								+ " is displayed in the Risk Effective PFE EPE ENE EE by Counterparty PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Effective PFE EPE ENE EE by Counterparty PFE value is not displayed in the Risk Effective PFE EPE ENE EE by Counterparty PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Effective PFE EPE ENE EE by Counterparty PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Effective PFE EPE ENE EE by Counterparty PFE not verified");
		}
	}

	@Test(priority =7)
	public void exposuresReportCalculation_calculationsExposuresPostMarginPFE() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin Incremental PFE");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			// Post-Margin Incremental PFE bookmark validation

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Pre-Margin PFE by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin EE.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin Incremental PFE value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin Incremental PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin Incremental PFE value is not displayed in the Risk Post-Margin Incremental PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin Incremental PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin Incremental PFE PFE not verified");
		}
	}

	@Test(priority =8)
	public void exposuresReportCalculation_calculationsExposuresPostMarginCounterparty() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin PFE by Counterparty");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			// Post-Margin PFE by Counterparty bookmark validation

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin PFE by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Post-Margin PFE.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber + 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin PFE by Counterparty PFE value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin PFE by Counterparty PFE page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin PFE by Counterparty PFE value is not displayed in the Risk Post-Margin PFE by Counterparty PFE page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin PFE by Counterparty PFE verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin PFE by Counterparty PFE PFE not verified");
		}
	}

	@Test(priority =9)
	public void exposuresReportCalculation_calculationsExposuresPostMarginIncrementalXVA() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin Incremental XVA");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			// XVA bookmark validation
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			WebElement closeElement = driver
					.findElement(By
							.xpath("//div[@style='white-space: nowrap;']/div[3]/div/div[2]/table/tbody/tr/td[1]"));
			closeElement.click();
			driver.findElement(
					By.xpath("//div[@class='gwt-Tree']/div[3]/div/div[3]/table/tbody/tr/td[1]"))
					.click();
			WebElement expandElement = driver
					.findElement(By
							.xpath("//div[@class= 'gwt-TabPanelBottom']/div[2]/div/table/tbody/tr[2]/td/div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
			expandElement.click();
			Thread.sleep(1000);
			clickExpandCounterpartyRisk("Counterparty Risk", "XVA");

			// Post-Margin Incremental XVA bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin Incremental XVA']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Post-Margin CVA Unilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin Incremental XVA PVs value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin Incremental XVA PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin Incremental XVA PVs value is not displayed in the Risk Post-Margin Incremental XVA PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin Incremental XVA PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin Incremental XVA PVs not verified");
		}
	}

	@Test(priority =10)
	public void exposuresReportCalculation_calculationsExposuresPostMarginMarginalXVA() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin Marginal XVA");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Post-Margin Marginal XVA bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin Marginal XVA']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Post-Margin CVA Unilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin Marginal XVA PVs value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin Marginal XVA PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin Marginal XVA PVs value is not displayed in the Risk Post-Margin Marginal XVA PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin Marginal XVA PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin Marginal XVA PVs not verified");
		}
	}

	@Test(priority =11)
	public void exposuresReportCalculation_calculationsExposuresPostMarginXVA() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin XVA");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Post-Margin XVA bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin XVA']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Post-Margin CVA Unilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger.info("Post-Margin XVA PVs value "
						+ strCellData
						+ " is displayed in the Risk Post-Margin XVA PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin XVA PVs value is not displayed in the Risk Post-Margin XVA PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin XVA PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin XVA PVs not verified");
		}
	}

	@Test(priority =12)
	public void exposuresReportCalculation_calculationsExposuresPostMarginXVAIncrementbyCounterparty() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin XVA Increment by Counterparty");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Post-Margin XVA Increment by Counterparty bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin XVA Increment by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Post-Margin BCVA Bilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin XVA Increment by Counterparty PVs value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin XVA Increment by Counterparty PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin XVA Increment by Counterparty PVs value is not displayed in the Risk Post-Margin XVA Increment by Counterparty PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin XVA Increment by Counterparty PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin XVA Increment by Counterparty PVs not verified");
		}
	}

	@Test(priority =13)
	public void exposuresReportCalculation_calculationsExposuresPostMarginXVAPortfolio() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Post-Margin XVA Portfolio Trade Allocation");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Post-Margin XVA Portfolio Trade Allocation bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Post-Margin XVA Portfolio Trade Allocation ']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Total Post-Margin CVA Unilateral Marginal Allocated.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Post-Margin XVA Portfolio Trade Allocation PVs value "
								+ strCellData
								+ " is displayed in the Risk Post-Margin XVA Portfolio Trade Allocation PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Post-Margin XVA Portfolio Trade Allocation PVs value is not displayed in the Risk Post-Margin XVA Portfolio Trade Allocation PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Post-Margin XVA Portfolio Trade Allocation PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Post-Margin XVA Portfolio Trade Allocation PVs not verified");
		}
	}

	@Test(priority =14)
	public void exposuresReportCalculation_calculationsExposuresPreMarginXVA() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Pre-Margin XVA");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Pre-Margin XVA bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Pre-Margin XVA']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin CVA Bilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger.info("Pre-Margin XVA PVs value "
						+ strCellData
						+ " is displayed in the Risk Pre-Margin XVA PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Pre-Margin XVA PVs value is not displayed in the Risk Pre-Margin XVA PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger.info("Pre-Margin XVA PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Pre-Margin XVA PVs not verified");
		}
	}

	@Test(priority =15)
	public void exposuresReportCalculation_calculationsExposuresPreMarginXVAIncrement() throws Exception {
		try {
			GeneralUtil
					.logger("Step56 :Risk bookmark validation for Pre-Margin XVA Increment by Counterparty");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;

			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);

			// Pre-Margin XVA Increment by Counterparty bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Pre-Margin XVA Increment by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Pre-Margin BCVA Unilateral.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[3]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("Pre-Margin XVA Increment by Counterparty PVs value "
								+ strCellData
								+ " is displayed in the Risk Pre-Margin XVA Increment by Counterparty PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Pre-Margin XVA Increment by Counterparty PVs value is not displayed in the Risk Pre-Margin XVA Increment by Counterparty PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Pre-Margin XVA Increment by Counterparty PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Pre-Margin XVA Increment by Counterparty PVs not verified");
		}
	}

	@Test(priority =16)
	public void exposuresReportCalculation_calculationsExposuresPVforWhatIfTrades() throws Exception {
		try {
			GeneralUtil
					.logger("Step61 :Risk bookmark validation for PV for What-If Trades");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			// What-If bookmark validation
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			WebElement closeElement = driver
					.findElement(By
							.xpath("//div[@style='white-space: nowrap;']/div[3]/div/div[5]/table/tbody/tr/td[1]"));
			closeElement.click();
			driver.findElement(
					By.xpath("//div[@class='gwt-Tree']/div[3]/div/div[3]/table/tbody/tr/td[1]"))
					.click();
			WebElement expandElement = driver
					.findElement(By
							.xpath("//div[@class= 'gwt-TabPanelBottom']/div[2]/div/table/tbody/tr[2]/td/div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
			expandElement.click();
			Thread.sleep(1000);
			clickExpandCounterpartyRisk("Counterparty Risk", "What-If");
			Thread.sleep(1000);
			// PV for What-If Trades bookmark validation

			driver.findElement(By.xpath(".//div[@class='gwt-HTML']/span[text()='PV for What-If Trades']")).click();
			Thread.sleep(1000);
			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Exposure PV.CCY",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber+1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("PV for What-If Trades PVs value "
								+ strCellData
								+ " is displayed in the Risk PV for What-If Trades PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("PV for What-If Trades PVs value is not displayed in the Risk PV for What-If Trades PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark PV for What-If Trades PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark PV for What-If Trades PVs not verified");
		}
	}

	//@Test(priority =17)
	public void exposuresReportCalculation_calculationsExposuresWhatIfPostMarginIncrementalCVA() throws Exception {
		try {
			GeneralUtil
					.logger("Step61 :Risk bookmark validation for What-If Post-Margin Incremental CVA");

			String strCellData = null;
			int intActColNumber = 0;
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();
			Thread.sleep(2000);
			// What-If Post-Margin Incremental CVA bookmark validation

			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Capital SA-CCR by Counterparty']"))
					.click();

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"Capital SA-CCR",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										1, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger
						.info("What-If Post-Margin Incremental CVA PVs value "
								+ strCellData
								+ " is displayed in the Risk What-If Post-Margin Incremental CVA PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("What-If Post-Margin Incremental CVA PVs value is not displayed in the Risk What-If Post-Margin Incremental CVA PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark What-If Post-Margin Incremental CVA PVs verified successfully");
		} else {
			GeneralUtil.logger
					.error("Risk bookmark What-If Post-Margin Incremental CVA PVs not verified");
		}
		//driver.switchTo().defaultContent();
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
		Thread.sleep(3000);
		//Common.loginOffXVA();
	}

	public void clickExpandCounterpartyRiskCapital(String expValue, String subExpValue) throws InterruptedException {
		WebElement expandElement = driver.findElement(By
				.xpath("//div[@class= 'gwt-TabPanelBottom']/div[2]/div/table/tbody/tr[2]/td/div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
		expandElement.click();
		Thread.sleep(1000);
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
		Thread.sleep(1000);
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
	}
	
	public void clickExpandCounterpartyRisk(String expValue, String subExpValue) throws InterruptedException {
		WebElement expandElement = driver.findElement(By
				.xpath("//div[@class= 'gwt-TabPanelBottom']/div[2]/div/table/tbody/tr[2]/td/div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
		expandElement.click();
		Thread.sleep(1000);
		List<WebElement> txtPublicElement = driver.findElements(By
				.xpath("//div[@class='gwt-Tree']/div[3]/div/div"));
		int i = 0;
		for (i = 0; i <= txtPublicElement.size(); i++) {
			String publicText = txtPublicElement.get(i).getText();

			if (publicText.contains(expValue)) {
				WebElement subPublicExpand = driver
						.findElement(By.xpath(String
								.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/table/tbody/tr/td[1]",
										i)));
				subPublicExpand.click();
				break;

			}
		}
		Thread.sleep(1000);
		List<WebElement> txtSubElement = driver.findElements(By.xpath(String
				.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/div/div",
						i)));
		int j = 0;
		for (j = 0; j <= txtSubElement.size(); j++) {
			String subText = txtSubElement.get(j).getText();

			if (subText.contains(subExpValue)) {
				WebElement subCounterpartyRiskExpand = driver
						.findElement(By.xpath(String
								.format("//div[@style='white-space: nowrap;']/div[%d]/div/div[%d]/table/tbody/tr/td[1]",
										i, j + 1)));
				subCounterpartyRiskExpand.click();
				break;

			}
		}
	}

	public void VerifyElementPresent(String strTrade, String strXpath,
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

	public String getToday(String format) {
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
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
