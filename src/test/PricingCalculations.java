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

public class PricingCalculations {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut= -1;
	public String strBrowserType = null;
	public Logger logger = null;
	String strSensitivityReport;
	String strPricing;
	boolean btnFlag = false;

	String strCellID;

	
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
		GeneralUtil.configureLog4j("PricingCalculations");
		// Assign logger object
		GeneralUtil.logger("PricingCalculations");
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
	public void pricing_pricingCalculation() throws Exception {
		try {
			GeneralUtil.logger("Add and Calculate Pricing Report");
			// logger =
			// Logger.getLogger("Started verifying left corner of the window locate tabs");
			// Navigate to Calculations screen
			String strCalculationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Calculations']";
			WebElement objCalculation = GeneralUtil.getElement(
					strCalculationsXpath, "xpath", dynamicTimeOut);
			objCalculation.click();
			Thread.sleep(3000);
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

			strPricing = "AUTO" + timeStamp;
			Thread.sleep(1000);
			// Enter Task Name
			WebElement objTaskName = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[1]/div/div[2]/div/input",
							"xpath", dynamicTimeOut);
			objTaskName.clear();

			objTaskName.sendKeys(strPricing);

			GeneralUtil.logger.info("Entered Task Name as " + strPricing);

			// Click on Calculation Type
			WebElement objCalculationType = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[2]/div/div[2]/div/div/div/div/div/span[2]",
							"xpath", dynamicTimeOut);
			objCalculationType.click();

			// Select CalculationType item
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Pricing", "Calculation Type");
			GeneralUtil.logger
					.info("Selected Calculation Type as Pricing calculation type");
			// Enter Trade id
			WebElement objTradeSelection = GeneralUtil
					.getElement(
							"//div[contains(@class,'bodyContainer')]//div/div[1]/div/div[4]/div/div/div[2]/div[2]/div/div[1]/div/input",
							"xpath", dynamicTimeOut);
			objTradeSelection.sendKeys("2,3");
			GeneralUtil.logger.info("Entered 2,3 trade ids in Trade Selection");
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

			// Click on Calculate button
			WebElement objCalculate = GeneralUtil
					.getElement(
							"//*[@id='root']/div/div/div/div[2]/div[3]/div/div[4]/div/div[1]/div[2]/div[2]/div/div/div[1]/div[1]/button",
							"xpath", dynamicTimeOut);
			objCalculate.click();
			GeneralUtil.logger.info("Clicked on calculate button");

			// Verification for success notification
			Common.handleNotifications("Calculation Submitted");
			Thread.sleep(1000);
			// Click alarm notification if exists
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Get Grid column and row index and verify newly added calculation
			// is conpleted in status column
			int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common
					.getRowNumber(intColNumber, strPricing);
			int intactionColNumber = Common.getColumnNumber("Jobs Progress");

			String txtStatus = "";
			int count = 1;
			Thread.sleep(1000);
			WebElement objCellStatus = driver
					.findElement(By.xpath(String
							.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
									intRowNumber, intactionColNumber)));

			while (count < dynamicTimeOut) {
				objCellStatus = driver
						.findElement(By.xpath(String
								.format(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[%d]/div[%d]/div/span",
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
			
			
			/*int intColNumber = Common.getColumnNumber("Task Name");
			int intRowNumber = Common.getRowNumber(intColNumber,
					strPricing);
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
			}*/
			 txtStatus = objCellStatus.getText();

			if (txtStatus.equalsIgnoreCase("100%")) {
				GeneralUtil.logger
						.info(strPricing
								+ " Pricing Report calculation status is completed");
			} else {
				GeneralUtil.logger
						.error(strPricing
								+ " Pricing Report calculation status is not completed.Displayed status is "
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
	public void pricing_calculationsPricingTradePVs() throws Exception {

		try {
			GeneralUtil
					.logger("Step53 :Risk bookmark validation for Trade PVs");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div[text()='Risk']"))
					.click();
			driver.switchTo()
					.frame(driver.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[2]/div/iframe")));

			// Pricing bookmark validation
			if (driver.findElement(
					By.xpath("//div[@class='GOJU4GFOT']/table/tbody/tr/td[3]"))
					.isDisplayed()) {
				clickExpandPricing("Pricing");

			} else {
				driver.findElement(
						By.xpath("//div[@class='GOJU4GFFR']/div[2]/div/table/tbody/tr/td[1]"))
						.click();
				driver.findElement(
						By.xpath("//div[@class='gwt-MenuBar gwt-MenuBar-vertical']/table/tbody/tr/td"))
						.click();
				clickExpandPricing("Pricing");
			}

			// Pricing Trade PVs bookmark validation
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Trade PVs']"))
					.click();
			int intUniqueColNumber = 0;
			String strCellData;
			int intActColNumber = 0;
			int intRowNumber = 0;
			WebElement strCellElement;

			if (verifyTable()) {
				intUniqueColNumber = Common
						.getTableColumnNumber(
								"Trade ID",
								"//*[@id='SPGtable']/tbody/tr[2]/td[1]/div/div/table/tbody/tr/th/table/tbody/tr/th");
				intRowNumber = Common
						.getTableRowNumber(
								"//*[@id='SPGtable']/tbody/tr[3]/td[1]/div/div/div[@class='gwt-HTML']/table/tbody/tr",
								"th[%d]", intUniqueColNumber, "2");

				intActColNumber = Common
						.getTableColumnNumber(
								"PV.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										intRowNumber, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.captureScreenshot();
				GeneralUtil.logger.info("Trade PVs value " + strCellData
						+ " is displayed in the Risk Trade PVs page");
			} else {
				GeneralUtil.logger
						.error("Trade PVs value is not displayed in the Risk Trade PVs page and failed");
			}

			intRowNumber = Common
					.getTableRowNumber(
							"//*[@id='SPGtable']/tbody/tr[3]/td[1]/div/div/div[@class='gwt-HTML']/table/tbody/tr",
							"th[%d]", intUniqueColNumber, "3");

			if (verifyTable()) {
				intActColNumber = Common
						.getTableColumnNumber(
								"PV.RPT",
								"//*[@id='SPGtable']/tbody/tr[2]/td[2]/div/div/div[@class='gwt-HTML']/table/thead/tr[2]/th");
				strCellElement = driver
						.findElement(By.xpath(String
								.format("//*[@id='SPGtable']/tbody/tr[3]/td[2]/div/div/div[@class='gwt-HTML']/table/tbody/tr[%d]/td[%d]",
										intRowNumber, intActColNumber - 1)));
				strCellData = strCellElement.getText();
				GeneralUtil.logger.info("Trade PVs value " + strCellData
						+ " is displayed in the Risk Trade PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Trade PVs value is not displayed in the Risk Trade PVs page and failed");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Trade PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Trade PVs not verified");
		}
	}
	
	@Test(priority =2)
	public void pricing_calculationsPricingPortfolioPVs() throws Exception {
		try {
			GeneralUtil
					.logger("Step53 :Risk bookmark validation for Portfolio PVs");

			String strCellData;
			int intActColNumber = 0;

			// Pricing Portfolio PVs bookmark validation
			WebElement strCellElement;
			driver.findElement(
					By.xpath("//table[@class='gwt-TabBar']/tbody/tr/td[2]/div/div/div[2]"))
					.click();

			Thread.sleep(2000);
			driver.findElement(
					By.xpath(".//div[@class='gwt-HTML']/span[text()='Portfolio PVs']"))
					.click();
			Thread.sleep(5000);
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
				GeneralUtil.logger.info("Portfolio PVs value " + strCellData
						+ " is displayed in the Risk Portfolio PVs page");
				btnFlag = true;
			} else {
				GeneralUtil.logger
						.error("Portfolio PVs value is not displayed in the Risk Portfolio PVs page and failed");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		if (btnFlag) {
			GeneralUtil.logger
					.info("Risk bookmark Portfolio PVs verified successfully");
		} else {
			throw new Exception("Risk bookmark Portfolio PVs not verified");
		}
		//driver.switchTo().defaultContent();
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
		Thread.sleep(3000);
		
		//Common.loginOffXVA();
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

	public void clickExpandPricing(String expValue) {
		WebElement expandElement = driver.findElement(By
				.xpath("//div[@class='gwt-Tree']/div[3]/table/tbody/tr/td[1]"));
		expandElement.click();
		List<WebElement> txtPublicElement = driver.findElements(By
				.xpath("//div[@class='gwt-Tree']/div[3]/div/div"));
		for (int i = 0; i < txtPublicElement.size(); i++) {
			String publicText = txtPublicElement.get(i).getText();

			if (publicText.contains(expValue)) {
				WebElement subPublicCollapse = driver
						.findElement(By.xpath(String
								.format("//div[@class='gwt-Tree']/div[3]/div/div[%d]/table/tbody/tr/td[1]",
										i + 1)));
				subPublicCollapse.click();
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
