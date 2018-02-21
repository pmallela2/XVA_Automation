package test;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javafx.stage.Window;
import util.Browser;
import util.GeneralUtil;
import util.PropertyUtils;

public class Trades {

	public int dynamicTimeOut = 0;
	public int scriptTimeOut;
	public String strBrowserType = null;
	public Logger logger = null;
	public WebDriver driver;
	public String tradeId = "Test123";
	public String cloneTradeId = "Test1234";
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
		GeneralUtil.configureLog4j("Verify Trades");
		// Assign logger object
		GeneralUtil.logger("Verify Trades");
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
	public void trades_addTrade() throws Exception {
		try {
			GeneralUtil.logger.info("Started add Trade");
			driver.findElement(By.xpath("//i[@class = 'fa fa-tasks fa-fw']"))
					.click();
			try{
				if(driver.findElement(By.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")).isDisplayed()==true) {
			Common.handleNotifications("Failed to get trades filter for user Administrator");
				}
			}
				catch(Exception e){
					
				}
			GeneralUtil.captureScreenshot();

			// Navigate to Add New Trades>TRADES>IR>FRA
			driver.findElement(By.xpath(".//*[@id='Menu']")).click();
			GeneralUtil.selectDropDownItem(
					"//ul[@class = 'dropdown-menu']/div", "TRADES",
					"Add New Trade");
			GeneralUtil.selectDropDownItem(
					"//div[@class = 'dropdown-menu']/div", "IR", "TRADES");
			GeneralUtil.selectDropDownItem(
					"//div[@class = 'dropdown-menu']/div", "FRA", "IR");
			GeneralUtil.captureScreenshot();
			try{
				if(driver.findElement(By.xpath("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']")).isDisplayed()==true) {
			Common.handleNotifications("Failed to get markets filter for user Administrator");
				}}
			catch(Exception e){
				
			}

			// Fill the Trade detail page and save the trade
			driver.findElement(
					By.xpath(".//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[1]/div/div/input"))
					.sendKeys(tradeId);

			driver.findElement(
					By.xpath(".//div[@class='ReactTabs react-tabs']/div/div/div/div/div/div[5]/div/div/div/div/div/div/span/div"))
					.click();
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"EUR", "Currency");
Thread.sleep(1000);
			driver.findElement(
					By.xpath("//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[6]/div/div/div[1]/div/div/input"))
					.click();
//Thread.sleep(6000);
			driver.findElement(
					By.xpath("//div[contains(@class,'react-datepicker__day') and text()=14]"))
					.click();
			driver.findElement(
					By.xpath("//div[@class='src-components-SmartInputs-FastNumberInput-FastNumberInput-dark---fastNumber---363mJ']/input"))
					.sendKeys("45");
			driver.findElement(
					By.xpath("//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[8]/div/div/input"))
					.sendKeys("test");
			driver.findElement(
					By.xpath("//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[9]/div/div/input"))
					.sendKeys("10");
			driver.findElement(
					By.xpath("//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[10]/div/div/div[1]/div/div/input"))
					.click();

			driver.findElement(
					By.xpath("//div[contains(@class,'react-datepicker__day') and text()=30]"))
					.click();

			driver.findElement(
					By.xpath("//div[@class='ReactTabs react-tabs']/ul/li[2]"))
					.click();
			driver.findElement(
					By.xpath("//div[@class='ReactTabs react-tabs']/ul/li[3]"))
					.click();

			driver.findElement(
					By.xpath("//div[@class='src-components-Trade-Header-Header-dark---container---hohnu']/div[2]"))
					.click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			// GeneralUtil.handleToolTip("Trade Saved");

			Thread.sleep(1000);

			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"))
					.sendKeys(tradeId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
Thread.sleep(1000);
			// Validating the trade added or not
			String tradeIdtext = driver.findElement(By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div")).getText();

			if (tradeIdtext.equalsIgnoreCase(tradeId)) {
				GeneralUtil.logger.info("Trade id " + tradeId
						+ " is available in the Trade");
			} else {
				GeneralUtil.logger.error("Trade id " + tradeId
						+ " is not available in the Trade");
			}
			GeneralUtil.logger.info("Trade created successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "trades_addTrade" })
	public void trades_cloneTrade() throws Exception {
		try {
			GeneralUtil.logger.info("Started clone Trade");

			// Clone the added trade
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"));
			driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[1]/div/div/div/span/span[1]/span[2]")).click();
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clone");
			GeneralUtil.captureScreenshot();
			
			WebElement tradeIDElement = driver
					.findElement(By
							.xpath(".//*[@class='ReactTabs react-tabs']/div/div/div/div/div/div[1]/div/div/input"));
			tradeIDElement.clear();
			Thread.sleep(1000);
			tradeIDElement.sendKeys(cloneTradeId);
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//div[@class='ReactTabs react-tabs']/ul/li[2]"))
					.click();
			driver.findElement(
					By.xpath("//div[@class='ReactTabs react-tabs']/ul/li[3]"))
					.click();

			driver.findElement(
					By.xpath("//div[@class='src-components-Trade-Header-Header-dark---container---hohnu']/div[2]"))
					.click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.logger.info("Trade cloned successfully");
			Thread.sleep(1000);

			// Validating the trade is cloned or not
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(cloneTradeId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			if( driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div")).isDisplayed()==true) {
			
			
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"))
					.getText();
			
			if (tradeIdtext.equalsIgnoreCase(cloneTradeId)) {
				GeneralUtil.logger.info("Cloned Trade id " + cloneTradeId
						+ " is available in the Trade");
			} else {
				GeneralUtil.logger.error("Cloned Trade id " + cloneTradeId
						+ " is not available in the Trade");
			}
			}
		} catch (Exception e) {
			if(e.toString().contains(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div")) {
				GeneralUtil.logger.error("Element is not Visible");
			}else {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
			}
		}
	}

	@Test(dependsOnMethods = { "trades_cloneTrade" })
	public void trades_editTrade() throws Exception {
		try {
			GeneralUtil.logger.info("Started edit Trade");

			// Search the added trade id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(tradeId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			// Click on trade id
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"))
					.click();

			// Update the trade detail page and save the trade
			driver.findElement(
					By.xpath(".//div[@class='ReactTabs react-tabs']/div//div/div/div/div/div[2]/div/div/div/div/div/div/span/div"))
					.click();
			GeneralUtil.dropDownItemvisible(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Bank Of America", "Counterparty");

			driver.findElement(
					By.xpath("//div[@class='src-components-Trade-Header-Header-dark---container---hohnu']/div[2]"))
					.click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.logger.info("Trade created successfully");
			Thread.sleep(1000);

			SearchElement.clear();
			SearchElement.sendKeys(tradeId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating the trade is updated or not
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase(tradeId)) {
				GeneralUtil.logger.info("Edited Trade id " + tradeId
						+ " is available in the Trade");
			} else {
				GeneralUtil.logger.error("Edited Trade id " + tradeId
						+ " is not available in the Trade");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = { "trades_editTrade" })
	public void trades_downloadTrade() throws Exception {
		try {
			GeneralUtil.logger.info("Started download Trade");

			// Clear the filter for trade columns
			WebElement contextMenuElement = driver.findElement(By
					.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");
			// Search the added trade id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(tradeId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Download the trade and validating "Trades(.*).zip" file download
			// or not
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"CSV Download",
							"Download All",
							"Trades(.*).zip",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[5]/div/div/span[2]");

			GeneralUtil.logger.info("Trade downloaded successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	@Test(dependsOnMethods = { "trades_downloadTrade" })
	public void trades_deleteTrade() throws Exception {

		try {
			GeneralUtil.logger.info("Started delete Trade");
			// Clear the filter for trade columns
			WebElement contextMenuElement = driver.findElement(By
					.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			// Search the added trade id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(tradeId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Delete the added trade id
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.captureScreenshot();

			// Clear the filter for trade columns
			WebElement contextMenuCloneElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div"));
			GeneralUtil.contextMenu(contextMenuCloneElement);

			// Search the cloned trade id
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			SearchElement.clear();
			SearchElement.sendKeys(cloneTradeId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Delete the cloned trade id
			WebElement contextMenuCloneelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuCloneelement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.captureScreenshot();
			GeneralUtil.logger.info("Trade deleted successfully");
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			if(e.toString().contains(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div")) {
				GeneralUtil.logger.error("Elements are not visible for 'Test1234' Name");
			}
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(dependsOnMethods = "trades_deleteTrade", alwaysRun=true)
	public void trades_uploadTrade() throws Exception {
		try {
			GeneralUtil.logger.info("Started upload Trade");

			
			
			WebElement contextMenuElement = driver.findElement(By
					.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();

			// Clear the filter for trade columns
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");
			Thread.sleep(1000);
			// Click on CSV Upload and upload the 'CC_BermudanPRDCSwap.csv' file
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"CSV Upload");
			Thread.sleep(1000);
			
			
			GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\Trades\\CC_BermudanPRDCSwap.csv");
			

			Thread.sleep(2000);
			GeneralUtil.logger.info("Trade uploaded successfully");
			Thread.sleep(1000);

			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[3]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys("CC_BermudanPRDCSwap");
			
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating the 'CC_BermudanPRDCSwap.csv' is uploaded or not
			String tradeIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[3]/div/div/div"))
					.getText();

			if (tradeIdtext.equalsIgnoreCase("CC_BermudanPRDCSwap")) {
				GeneralUtil.logger.info("Uploaded Trade id " + tradeId
						+ " is available in the Trade");
			} else {
				GeneralUtil.logger.error("Uploaded Trade id " + tradeId
						+ " is not available in the Trade");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}
	
	@Test (dependsOnMethods = "trades_uploadTrade", alwaysRun=true)
	public void trades_veriryTradeColumn() throws Exception {
		try {
			GeneralUtil.logger.info("Started verify trade colums");

			WebElement contextMenuElement = driver.findElement(By
					.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();

			// Clear the filter for trade columns
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");
			Thread.sleep(1000);
			//WebElement scrollBar=driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[2]/div[@class='ps-scrollbar-x']"));
			//((JavascriptExecutor)driver).executeScript("window.scrollBy(2000,0)");
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", scrollBar);
			/*WebElement horizontalbar = driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[2]/div[@class='ps-scrollbar-x']"));
			Actions action = new Actions(driver);

			Actions moveToElement = action.moveToElement( horizontalbar );
			for (int i = 0; i < 20; i++) {
			    moveToElement.sendKeys(Keys.RIGHT).build().perform();
			}*/

			GeneralUtil.captureScreenshot();
			
			// Search currency with 'EUR'
			WebElement SearchElementCurrency = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[9]/div/div/input"));
			SearchElementCurrency.clear();
			SearchElementCurrency.sendKeys("EUR");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			
			/*WebElement horizontalbar1 = driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[2]/div[@class='ps-scrollbar-x']"));
			Actions action1 = new Actions(driver);

			Actions moveToElement1 = action.moveToElement( horizontalbar1 );
			for (int i = 0; i < 20; i++) {
			    moveToElement.sendKeys(Keys.LEFT).build().perform();
			}*/

			// Validating currency column is displayed only EUR
			String tradeCurrencytext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[9]/div/div/div"))
					.getText();
			if (tradeCurrencytext.equalsIgnoreCase("EUR")) {
				GeneralUtil.logger
						.info("Only EUR Currency is displayed after searching currency with EUR");
			} else {
				GeneralUtil.logger
						.error("Only EUR Currency is not displayed after searching currency with EUR");
			}

			// Clear the filter for trade columns
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");
			// Search status with 'Booked'
			GeneralUtil
					.selectItem(
							".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[8]/div/div/select",
							"Booked");

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating status column is displayed only Booked
			String tradeStatustext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[8]/div/div/div"))
					.getText();
			if (tradeStatustext.equalsIgnoreCase("Booked")) {
				GeneralUtil.logger
						.info("Only Booked Status is displayed after searching status with Booked");
			} else {
				GeneralUtil.logger
						.error("Only Booked Status is not displayed after searching status with Booked");
			}

			// Clear the filter for trade columns
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			// Search Legal Entity with 'Bank Of America US'
			WebElement SearchElementLegalEntity = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[7]/div/div/input"));
			SearchElementLegalEntity.clear();
			SearchElementLegalEntity.sendKeys("Bank Of America US");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating Legal Entity column is displayed only Bank Of America
			// US
			String tradeLegalEntitytext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[7]/div/div/div"))
					.getText();
			if (tradeLegalEntitytext.equalsIgnoreCase("Bank Of America US")) {
				GeneralUtil.logger
						.info("Only Bank Of America US Legal Entity is displayed after searching Legal Entity with Bank Of America US");
			} else {
				GeneralUtil.logger
						.error("Only Bank Of America US Legal Entity is not displayed after searching Legal Entity with Bank Of America US");
			}

			// Clear the filter for trade columns
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			// Search counterparty with 'Bank Of America'
			WebElement SearchElementCounterparty = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[6]/div/div/input"));
			SearchElementCounterparty.clear();
			SearchElementCounterparty.sendKeys("Bank Of America");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating counterparty column is displayed only Bank Of America
			String tradeCounterpartytext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[6]/div/div/div"))
					.getText();
			if (tradeCounterpartytext.equalsIgnoreCase("Bank Of America")) {
				GeneralUtil.logger
						.info("Only Bank Of America Counterparty is displayed after searching Counterparty with Bank Of America");
			} else {
				GeneralUtil.logger
						.error("Only Bank Of America Counterparty is not displayed after searching Counterparty with Bank Of America");
			}

			// Clear the filter for trade columns
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			// Search asset class with 'IR'
			WebElement SearchElementAssetClass = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[5]/div/div/input"));
			SearchElementAssetClass.clear();
			SearchElementAssetClass.sendKeys("IR");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating asset class column is displayed only IR
			String tradeAssetClasstext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[5]/div/div/div"))
					.getText();
			if (tradeAssetClasstext.equalsIgnoreCase("IR")) {
				GeneralUtil.logger
						.info("Only IR Asset Class is displayed after searching Asset Class with IR");
			} else {
				GeneralUtil.logger
						.error("Only IR Asset Class is not displayed after searching Asset Class with IR");
			}

			// Clear the filter for trade columns
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clear All Filters");

			// Search product with 'TRADE.IR.GENERICSWAP'
			WebElement SearchElementProduct = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[4]/div/div/input"));
			SearchElementProduct.clear();
			SearchElementProduct.sendKeys("TRADE.IR.GENERICSWAP");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating product column is displayed only TRADE.IR.GENERICSWAP
			String tradeProducttext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[4]/div/div/div"))
					.getText();
			if (tradeProducttext.equalsIgnoreCase("TRADE.IR.GENERICSWAP")) {
				GeneralUtil.logger
						.info("Only TRADE.IR.GENERICSWAP Product is displayed after searching Product with TRADE.IR.GENERICSWAP");
			} else {
				GeneralUtil.logger
						.error("Only TRADE.IR.GENERICSWAP Product is not displayed after searching Product with TRADE.IR.GENERICSWAP");
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(dependsOnMethods = "trades_veriryTradeColumn" , alwaysRun=true)
	public void trades_showHideTradeColumn() throws Exception {

		GeneralUtil.logger
				.info("Step19 : Started verify show hide Trade column");

		try {

			try {
				// Close the any error notifications
				for (int i = 1; i < 4; i++) {
					WebElement objnotification = GeneralUtil
							.getElement(
									"//div[starts-with(@class,'notification notification-error notification-visible')]/span",
									"xpath", 2);
					objnotification.click();
				}
			} catch (Exception e) {

			}

			// Click on Show Hide Columns Button
			WebElement objShowHideColumnsButton = GeneralUtil.getElement(
					"//div/span[@title='Show/Hide Columns']/i", "xpath",
					dynamicTimeOut);
			objShowHideColumnsButton.click();

			GeneralUtil.captureScreenshot();
			String strCheckBox = "html/body/div[1]/div/div/div/div[2]/div[3]/div/div[3]/div/div[1]/div[2]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div[2]/div/div/label[text()='Accrual End Date']/preceding::div[1]";
			WebElement objCheckBox = GeneralUtil.getElement(strCheckBox,
					"xpath", dynamicTimeOut);
			objCheckBox.click();

			objShowHideColumnsButton.click();

			// Check the check box for Accrual End Date in Show Hide Columns and
			// validating the 'Accrual End Date' column is displayed on trades
			// page

			List<WebElement> arrColumnText = GeneralUtil
					.getElements(
							"//div[starts-with(@class,'src-components-Tables-SimpleTables-Table-dark---main')]//div[starts-with(@class,'ag-header-container')]/div//span[starts-with(@class,'ag-header-cell-text')]",
							"xpath", dynamicTimeOut);
			boolean blnflog = false;
			for (int i = 0; i < arrColumnText.size(); i++) {
				String strActColText = arrColumnText.get(i).getText();
				if (strActColText.equalsIgnoreCase("Accrual End Date")) {
					GeneralUtil.logger.info(strActColText
							+ " coulmn is displayed in Trades grid");
					GeneralUtil.captureScreenshot();
					blnflog = true;
					break;
				}
			}

			if (!blnflog) {
				GeneralUtil.logger
						.error("'Accrual End Date' coulmn is not displayed in Trades grid");
			}
			// Uncheck the 'Accrual End Date' check box
			objShowHideColumnsButton.click();

			WebElement objCheckBox1 = GeneralUtil.getElement(strCheckBox,
					"xpath", dynamicTimeOut);
			objCheckBox1.click();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
		Thread.sleep(3000);
		//Common.loginOffXVA();
	}
}
