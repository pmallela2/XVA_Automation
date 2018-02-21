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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jmx.snmp.Timestamp;

import util.Browser;
import util.GeneralUtil;
import util.PropertyUtils;


public class MarketData {

	public int dynamicTimeOut = 0;
	
	public int scriptTimeOut;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());;
	//SimpleDateFormat sdf;Timestamp timestamp;String timeStamp;
	public Logger logger = null;
	public WebDriver driver;
	public String marketId = "Demo_EOD_FXSPOT";
	public String editMarketId = "Demo_EOD_FXSPOTEdit";
	public String cloneMarketId = "Demo_EOD_FXSPOTClone";
	public String fixingId = "AUD-BB"+timeStamp;
	public String editFixingsId = "AUD-BB"+timeStamp;
	public String cloneFixingsId = "AUD-BB"+timeStamp;
	public String marketDescriptorsId = "EQ_MarketDescriptor_"+timeStamp;
	public String editMarketDescriptorsId = "EQ_MarketDescriptor_"+timeStamp+"_Edit";
	public String cloneMarketDescriptorsId = "EQ_MarketDescriptor_"+timeStamp+"_Clone";
	public String strBrowserType = null;
	public String casPath;
	public String strmenu = "//div[@class='ag-menu']//span[@class='ag-menu-option-text']";

	@BeforeClass
	public void beforeClass() throws Exception {
		// Read general timings from properties file
		/*sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		timestamp = new Timestamp(System.currentTimeMillis());
		timeStamp=sdf.format(timestamp).toString();*/
		
		dynamicTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("DynamicTimeOut"));

		scriptTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("ScriptTimeout"));
		
		strBrowserType = PropertyUtils.getConfigMessage("Browser");
		
		casPath = PropertyUtils.getConfigMessage("Upload_Path");
		// Defined all properties and Configure log4j
		GeneralUtil.configureLog4j("Markets Data");
		// Assign logger object
		GeneralUtil.logger("Markets Data");
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

	@Test(alwaysRun=true)
	public void marketData_verifyMarketDataTabs() throws Exception {
		try {
			GeneralUtil.logger.info("Step24 : Verify Market Data Items");

			// Validating Market Data Tab is expand or not and verify Market
			// Data sub tabs
			if (driver
					.findElement(
							By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
					.isDisplayed()) {
				String strMarketXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']";
				verifyElementPresent("Markets", strMarketXpath, dynamicTimeOut);

				String strFixingsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']";
				verifyElementPresent("Fixings", strFixingsXpath, dynamicTimeOut);

				String strMarketDescriptorsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']";
				verifyElementPresent("Market Descriptors",
						strMarketDescriptorsXpath, dynamicTimeOut);
			}
			// Expand Market Data Tab and verify Market Data sub tabs
			else {
				driver.findElement(
						By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Market Data']"))
						.click();

				String strMarketXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']";
				verifyElementPresent("Markets", strMarketXpath, dynamicTimeOut);

				String strFixingsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Fixings']";
				verifyElementPresent("Fixings", strFixingsXpath, dynamicTimeOut);

				String strMarketDescriptorsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Market Descriptors']";
				verifyElementPresent("Market Descriptors",
						strMarketDescriptorsXpath, dynamicTimeOut);
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
	}

	@Test(dependsOnMethods = "marketData_deleteMarketDescriptors", alwaysRun=true)
	public void marketData_uploadMarket() throws Exception {
		try {
			GeneralUtil.logger.info("Step25 :Upload a Market");

			// Click on Markets
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
					.click();

			// Upload the "CVADemo_2014-01-06_FXSPOT.csv" file
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"CSV Upload");
			
			Thread.sleep(1000);
			GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\Markets\\CVADemo_2014-01-06_EOD_FXSPOT.csv");

			Thread.sleep(1000);
			WebElement uploadSearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			uploadSearchElement.clear();
			uploadSearchElement.sendKeys(marketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Validating the uploaded the "CVADemo_2014-01-06_FXSPOT.csv" file
			// or not
			/*String marketIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]"))
					.getText();*/
			String marketIdtext = driver
					.findElement(
							By.xpath("//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div/span"))
					.getText();
			

			if (marketIdtext.equalsIgnoreCase(marketId)) {
				GeneralUtil.logger.info("Markets id " + marketId
						+ " is available in the Markets");
			} else {
				GeneralUtil.logger.error("Markets id " + marketId
						+ " is not available in the Markets");
			}
			GeneralUtil.logger.info("Market uploaded successfully");
			Thread.sleep(1000);

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}

	public boolean waitUntilExists(String object, String accessBy,
			int timeOut) throws InterruptedException {
		boolean result = false;
		//Mode accessBy = Mode.valueOf(mode);
		int count = 1;
		while (count < timeOut) {
				if (driver.findElement(By.xpath(object)) != null) {
					result = true;
					count = 200;
				}
			count++;
		}
		return result;
	}
	public boolean handleNotifications(String expMessage)
			throws Exception {
		
		boolean blnFind = waitUntilExists("//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']",
				"xpath", 3000);
		
		WebElement objNotificationsTest = GeneralUtil
				.getElement(
						"//div[@class='notifications-wrapper']//h4//following-sibling::div[@class='notification-message']",
						"xpath", 3000);
		String strnotifications = objNotificationsTest.getText();
		WebElement objNotificationsH4 = GeneralUtil.getElement(
				"//div[@class='notifications-wrapper']//h4", "xpath",
				30);
		strnotifications = objNotificationsH4.getText() + ":"
				+ strnotifications;
		if (strnotifications.contains(expMessage)) {

			GeneralUtil.logger.info("Displayed notifications is :"
					+ strnotifications);
			GeneralUtil.captureScreenshot();
			// Thread.sleep(1000);
			try {

				WebElement notificationCloseButton = driver.findElement(By
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
	@Test(dependsOnMethods = { "marketData_uploadMarket" })
	public void marketData_editMarket() throws Exception {
		try {
			GeneralUtil.logger.info("Step26 :Edit a Market");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
					.click();

			// Search the upload market id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Edit the uploaded market id
			WebElement marketNameElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]"));
			marketNameElement.click();
			WebElement nameTextElement = driver
					.findElement(By
							.xpath(".//div[@class='ReactTabs react-tabs']/div/div/div/div[2]/div/div[2]/div/input"));
			nameTextElement.clear();
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
			editMarketId = editMarketId + timeStamp;
			nameTextElement.sendKeys(editMarketId);
Thread.sleep(500);
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			driver.findElement(By.xpath("//div[text() = 'Close']")).click();
			
			//handleNotifications("Success");

			Thread.sleep(1000);

			//TT 45574 - Application change in in build 4.6
			 /* WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
*/
			
		//	driver.findElement(By.xpath("//div[text() = 'Close']")).click();
			
			
			
			WebElement SearchElement1 = driver
					.findElement(By
							.xpath("//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement1.clear();
			SearchElement1.sendKeys(editMarketId);	
			/*SearchElement1.clear();
			SearchElement1.sendKeys(editMarketId);*/
			Thread.sleep(1000);
			int count = 1;
			while (count < dynamicTimeOut) {
				SearchElement1.clear();
				SearchElement1.sendKeys(editMarketId);				
				List<WebElement> rows =  driver.findElements(By.xpath("//div[contains(@class,'ag-body-container')]/div[contains(@class,'ag-row')]"));
				if (rows.size()>=1) {
						break;
				}
				Thread.sleep(2000);
				count++;
			}
			//div[contains(@class,'ag-body-container')]/div[contains(@class,'ag-row')]
			
			

			// Validating the market id is edited or not
			String marketIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]"))
					.getText();
			if (marketIdtext.equalsIgnoreCase(editMarketId)) {
				GeneralUtil.logger.info("Markets id " + editMarketId
						+ " is available in the Markets");

			} else {
				GeneralUtil.logger.info("Markets id " + editMarketId
						+ " is not available in the Markets");
			}

			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market edited successfully");

	}

	@Test(dependsOnMethods = { "marketData_editMarket" })
	public void marketData_cloneMarket() throws Exception {
		try {
			GeneralUtil.logger.info("Step27 :clone a Market");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
					.click();
			// Search the edit market id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(editMarketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Clone the market id
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clone");
			WebElement nameTextElement = driver
					.findElement(By
							.xpath(".//div[@class='ReactTabs react-tabs']/div/div/div/div[2]/div/div[2]/div/input"));
			nameTextElement.clear();
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
			cloneMarketId = cloneMarketId + timeStamp;
			nameTextElement.sendKeys(cloneMarketId);
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			driver.findElement(By.xpath("//div[text() = 'Close']")).click();
			GeneralUtil.captureScreenshot();

			// GeneralUtil.handleToolTip("Trade Saved");

			Thread.sleep(1000);
		//	driver.findElement(By.xpath("//div[text() = 'Close']")).click();
			/*WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));*/
			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			int count = 1;
			while (count < dynamicTimeOut) {
				SearchElement1.clear();
				SearchElement1.sendKeys(cloneMarketId);				
				List<WebElement> rows =  driver.findElements(By.xpath("//div[contains(@class,'ag-body-container')]/div[contains(@class,'ag-row')]"));
				if (rows.size()>=1) {
						break;
				}
				Thread.sleep(2000);
				count++;
			}

			// Validating the market id is cloned or not
			String marketIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]"))
					.getText();
			if (marketIdtext.equalsIgnoreCase(cloneMarketId)) {
				GeneralUtil.logger.info("Markets id " + cloneMarketId
						+ " is available in the Markets");

			} else {
				GeneralUtil.logger.info("Markets id " + cloneMarketId
						+ " is not available in the Markets");
			}

			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market clone successfully");

	}

	@Test(dependsOnMethods = { "marketData_editMarket" })
	public void marketData_downloadMarket() throws Exception {
		try {
			GeneralUtil.logger.info("Step28 :download a Market");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
					.click();
			Thread.sleep(2000);
			// Search the edit market id
			/*WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			Thread.sleep(500);
			SearchElement.clear();
			Thread.sleep(500);
			SearchElement.sendKeys(editMarketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();*/
			
			WebElement contextMenuelement1 = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
					dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement1);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
			GeneralUtil.captureScreenshot();

			// Download market id and Validating the market id is download or
			// not
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"CSV Download",
							"Download All",
							"Markets(.*).zip",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[5]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market download successfully");

	}

	@Test(dependsOnMethods = { "marketData_downloadMarket" })
	public void marketData_deleteMarket() throws Exception {
		try {
			GeneralUtil.logger.info("Step29 :delete a Market");
		//	driver.findElement(
		//			By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Markets']"))
		//			.click();
			
			WebElement contextMenuelement1 = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
					dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement1);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
			GeneralUtil.captureScreenshot();

			// Search the edit market id
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(editMarketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			

			// Delete the added market id and validating
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.captureScreenshot();
			
			WebElement contextMenuelement2 = GeneralUtil.getElement(
					"//*[@ref='center']/div/div[4]/div[3]/div", "xpath",
					dynamicTimeOut);
			GeneralUtil.contextMenu(contextMenuelement2);
			GeneralUtil.captureScreenshot();
			GeneralUtil.contextMenuItem(strmenu, "Clear All Filters");
			GeneralUtil.captureScreenshot();

			WebElement SearchElement1 = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[2]/div/div/input"));
			// Search the clone market id
			SearchElement1.clear();
			SearchElement1.sendKeys(cloneMarketId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Delete the cloned market id and validating
			WebElement contextMenuSecondelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[2]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuSecondelement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		//GeneralUtil.logger.info("Market deleted successfully");

	}

	@Test(dependsOnMethods ="marketData_verifyMarketDataTabs", alwaysRun=true)
	public void marketData_addFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step31 :Add a Fixings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']"))
					.click();
			GeneralUtil.captureScreenshot();
			Thread.sleep(1000);

			// Add the Fixings
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Add");
			Thread.sleep(1000);

			driver.findElement(
					By.xpath("//div[@class='react-draggable']/div/div/div[2]/div/div[1]/div/div/div[2]/div/input"))
					.sendKeys(fixingId);
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			

			WebElement contextMenuRightElement = driver
					.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuRightElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			Thread.sleep(1000);
			//TT 45574 - Application change in in build 4.6
			// Validating the Fixings added or not
			Common.handleNotifications("Save Fixings");
			/*WebDriverWait NDriver = new WebDriverWait(driver, 120);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(fixingId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(fixingId)) {
				GeneralUtil.logger.info("Fixings id " + fixingId
						+ " is available in the Fixings");
			} else {
				GeneralUtil.logger.error("Fixings id " + fixingId
						+ " is not available in the Fixings");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings created successfully");
	}

	@Test(dependsOnMethods = { "marketData_addFixings" })
	public void marketData_editFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step31 :Edit a Fixings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']"))
					.click();
			// Search the added Fixings
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(fixingId);
			Thread.sleep(1000);

			// Click on added Fixings
			WebElement fixingIdElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			fixingIdElement.click();

			// Update the Fixings name and save
			WebElement nameTextElement = driver
					.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/input"));
			nameTextElement.clear();
			nameTextElement.sendKeys(editFixingsId);

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Fixings edited or not
			Common.handleNotifications("Save Fixings");

			Thread.sleep(1000);
			//TT 45574 - Application change in in build 4.6
			//Common.clickNotification("");
			SearchElement.clear();
			SearchElement.sendKeys(editFixingsId);
			Thread.sleep(1000);

			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(editFixingsId)) {
				GeneralUtil.logger.info("Fixings id " + editFixingsId
						+ " is available in the Fixings");
			} else {
				GeneralUtil.logger.error("Fixings id " + editFixingsId
						+ " is not available in the Fixings");
			}

			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings edited successfully");

	}

	@Test(dependsOnMethods = { "marketData_editFixings" })
	public void marketData_cloneFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step33 :Clone a Fixings");

			// Search the edit Fixings
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			Thread.sleep(1000);
			SearchElement.sendKeys(editFixingsId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Clone the edited fixings
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Clone");

			// Update the Fixings name and save the fixings
			WebElement nameTextElement = driver
					.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/input"));
			nameTextElement.clear();
			nameTextElement.sendKeys(cloneFixingsId);

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Fixings cloned or not
			Common.handleNotifications("Save Fixings");

			Thread.sleep(1000);
			
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/

			SearchElement.clear();
			SearchElement.sendKeys(cloneFixingsId);
			Thread.sleep(1000);

			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(cloneFixingsId)) {
				GeneralUtil.logger.info("Fixings id " + cloneFixingsId
						+ " is available in the Fixings");
			} else {
				GeneralUtil.logger.error("Fixings id " + cloneFixingsId
						+ " is not available in the Fixings");
			}

			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings cloned successfully");

	}

	@Test(dependsOnMethods = { "marketData_cloneFixings" })
	public void marketData_uploadFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step33 :Upload a Fixings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']"))
					.click();
			// Search the edit Fixings
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(editFixingsId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Upload the 'AUD-BB-6M.csv' the CSV Upload
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"CSV Upload");
			Thread.sleep(1000);
			GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\Fixings\\AUD-BB-6M.csv");
			Thread.sleep(2000);
			// Validating the 'AUD-BB-6M' file uploaded or not
			//Common.handleNotifications("Upload fixing");

			Thread.sleep(1000);
			/*//TT 45574 - Application change in in build 4.6
			WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();
*/
			SearchElement.clear();
			SearchElement.sendKeys("AUD-BB-6M");
			Thread.sleep(1000);

			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase("AUD-BB-6M")) {
				GeneralUtil.logger
						.info("Fixings id AUD-BB-6M is available in the Fixings");
			} else {
				GeneralUtil.logger
						.error("Fixings id AUD-BB-6M is not available in the Fixings");
			}

			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings uploaded successfully");

	}

	@Test(dependsOnMethods = { "marketData_uploadFixings" })
	public void marketData_downloadFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step33 :Download a Fixings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']"))
					.click();
			Thread.sleep(2000);
			// Search the edit Fixings
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(editFixingsId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Download the fixings and validating "Fixings(.*).zip" file
			// download or not
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Thread.sleep(1000);
			Common
					.download(
							"CSV Download",
							"Download All",
							"Fixings(.*).zip",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[5]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings downloaded successfully");

	}

	@Test(dependsOnMethods = { "marketData_downloadFixings" })
	public void marketData_deleteFixings() throws Exception {
		try {
			GeneralUtil.logger.info("Step33 :Delete a Fixings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Fixings']"))
					.click();
			// Search the edit Fixings
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(editFixingsId);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Delete the added fixings
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the added fixings deleted or not
			Common.handleNotifications("Delete Fixings");
			GeneralUtil.captureScreenshot();
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			SearchElement.clear();
			SearchElement.sendKeys("AUD-BB-6M");
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();

			// Delete the clone fixings
			WebElement contextMenuSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuSecondElement);
			GeneralUtil.captureScreenshot();
			Thread.sleep(1000);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			// Validating the cloned fixings deleted or not
			Common.handleNotifications("Delete Fixings");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Fixings deleted successfully");

	}

	@Test(dependsOnMethods = "marketData_deleteFixings", alwaysRun=true)
	public void marketData_addMarketDescriptors() throws Exception {
		try {

			GeneralUtil.logger.info("Step36 :Add a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();
			GeneralUtil.captureScreenshot();
Thread.sleep(1000);
			// Add the Market Descriptors
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			//Thread.sleep(2000);
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");
			Thread.sleep(1000);
			// fill the add page on Market Descriptors and click on ok button
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input"))
					.sendKeys(marketDescriptorsId);
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Create the Insert column page
			addMarketDescriptorContextMenu("KEY");
			addMarketDescriptorContextMenu("Company Description");
			addMarketDescriptorContextMenu("Sector Name");
			addMarketDescriptorContextMenu("Industry Name");

			// Fill the inserted column and save the Market Descriptor
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[1]/div/div/input"))
					.sendKeys("EQ.USD-US-AXP.SPOT.MID");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[2]/div/div/input"))
					.sendKeys(
							"American Express Company is a global payment and travel company. The Company's principal products and services are charge and credit payment card products and travel-related services offered to consumers and businesses around the world.");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[3]/div/div/input"))
					.sendKeys("Financials");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[4]/div/div/input"))
					.sendKeys("Consumer Finance");
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Market Descriptor is added or not
			Common.handleNotifications("Save Market Descriptor");
			
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 120);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(marketDescriptorsId)) {
				GeneralUtil.logger.info("Market Descriptors id "
						+ marketDescriptorsId
						+ " is available in the Market Descriptors");
			} else {
				GeneralUtil.logger.error("Market Descriptors id "
						+ marketDescriptorsId
						+ " is not available in the Market Descriptors");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors created successfully");
	}

	@Test(dependsOnMethods = { "marketData_addMarketDescriptors" })
	public void marketData_editMarketDescriptors() throws Exception {
		try {
			GeneralUtil.logger.info("Step36 :edit a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();

			// Search the added Market Descriptor
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);

			// Click on added Market Descriptors
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();

			// Fill the edit Market Descriptors column and save
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[1]/div/div/input"))
					.sendKeys("EQ.USD-US-AXP.SPOT.MID");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[2]/div/div/input"))
					.sendKeys(
							"American Express Company is a global payment and travel company. The Company's principal products and services are charge and credit payment card products and travel-related services offered to consumers and businesses around the world.");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[3]/div/div/input"))
					.sendKeys("Financials");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[4]/div/div/input"))
					.sendKeys("Consumer Finance");
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Market Descriptor is edited or not
			Common.handleNotifications("Save Market Descriptor");
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 120);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			WebElement SearchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchSecondElement.clear();
			SearchSecondElement.sendKeys(marketDescriptorsId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(editMarketDescriptorsId)) {
				GeneralUtil.logger.info("Market Descriptors id "
						+ marketDescriptorsId
						+ " is available in the Market Descriptors");
			} else {
				GeneralUtil.logger.error("Market Descriptors id "
						+ marketDescriptorsId
						+ " is not available in the Market Descriptors");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors edited successfully");
	}

	@Test(dependsOnMethods = { "marketData_editMarketDescriptors" })
	public void marketData_cloneMarketDescriptors() throws Exception {
		try {

			GeneralUtil.logger.info("Step36 :Clone a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();
			// Search the added Market Descriptor
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Clone");
			// Fill the cloned column and save the Market Descriptor
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input"))
					.sendKeys(cloneMarketDescriptorsId);
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[1]/div/div/input"))
					.sendKeys("EQ.USD-US-AXP.SPOT.MID");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[2]/div/div/input"))
					.sendKeys(
							"American Express Company is a global payment and travel company. The Company's principal products and services are charge and credit payment card products and travel-related services offered to consumers and businesses around the world.");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[3]/div/div/input"))
					.sendKeys("Financials");
			driver.findElement(
					By.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div/div/div/div[3]/div/div[2]/div[4]/div/div/input"))
					.sendKeys("Consumer Finance");
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Market Descriptor is cloned or not
			Common.handleNotifications("Save Market Descriptor");
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 120);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			Thread.sleep(1000);
			WebElement SearchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchSecondElement.clear();
			Thread.sleep(1000);
			SearchSecondElement.sendKeys(cloneMarketDescriptorsId);

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase(cloneMarketDescriptorsId)) {
				GeneralUtil.logger.info("Market Descriptors id "
						+ cloneMarketDescriptorsId
						+ " is available in the Market Descriptors");
			} else {
				GeneralUtil.logger.error("Market Descriptors id "
						+ cloneMarketDescriptorsId
						+ " is not available in the Market Descriptors");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors cloned successfully");
	}

	@Test(dependsOnMethods = { "marketData_cloneMarketDescriptors" })
	public void marketData_uploadMarketDescriptors() throws Exception {
		try {

			GeneralUtil.logger.info("Step36 :Upload a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();
			// Search the added Market Descriptor
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"CSV Upload");
			Thread.sleep(1000);
			GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Data\\MarketDescriptors\\EQ_MarketDescriptor_Default.csv");
			Thread.sleep(1000);
			// Validating the Market Descriptor is uploaded or not
			Common.handleNotifications("Upload market descriptor");
			Thread.sleep(1000);
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 120);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();*/
			Thread.sleep(1000);
			WebElement SearchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchSecondElement.clear();
			Thread.sleep(1000);
			SearchSecondElement.sendKeys("EQ_MarketDescriptor_Default");

			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String fixingsIdtext = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();

			if (fixingsIdtext.equalsIgnoreCase("EQ_MarketDescriptor_Default")) {
				GeneralUtil.logger
						.info("Market Descriptors id EQ_MarketDescriptor_Default is available in the Market Descriptors");
			} else {
				GeneralUtil.logger
						.error("Market Descriptors id EQ_MarketDescriptor_Default is not available in the Market Descriptors");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors Uploaded successfully");
	}

	@Test(dependsOnMethods = { "marketData_uploadMarketDescriptors" })
	public void marketData_downloadMarketDescriptors() throws Exception {
		try {

			GeneralUtil.logger.info("Step36 :Download a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();
			// Search the added Market Descriptor and "MarketDescriptor(.*).zip"
			// file download or not
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);

			// Download the Market Descriptors and
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			Common
					.download(
							"CSV Download",
							"Download All",
							"MarketDescriptor(.*).zip",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors Download successfully");
	}

	@Test(dependsOnMethods = { "marketData_downloadMarketDescriptors" })
	public void marketData_deleteMarketDescriptors() throws Exception {
		try {

			GeneralUtil.logger.info("Step36 :Delete a Market Descriptors");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Market Descriptors']"))
					.click();
			// Search the added Market Descriptor
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(marketDescriptorsId);
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();

			// Delete the added Market Descriptors
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			// CommonTest.handleNotifications("Delete Market Descriptor");

			Thread.sleep(1000);
			//TT 45574 - Application change in in build 4.6
			/*WebDriverWait NDriver = new WebDriverWait(driver, 300);
			WebElement launchToolTip = NDriver.until(ExpectedConditions
					.visibilityOfElementLocated(By
							.xpath("//i[@class='fa fa-bell-o fa-fw']")));
			WebElement refreshScreenElement = driver.findElement(By
					.xpath("//i[@class='fa fa-bell-o fa-fw']"));
			refreshScreenElement.click();
			Thread.sleep(1000);*/

			// Search the cloned Market Descriptor
			WebElement SearchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchSecondElement.clear();
			SearchSecondElement.sendKeys(cloneMarketDescriptorsId);
			WebElement contextMenuSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuSecondElement);
			GeneralUtil.captureScreenshot();

			// Delete the cloned Market Descriptors
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			// CommonTest.handleNotifications("Delete Market Descriptor");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Market Descriptors delete successfully");
	}

	public void addMarketDescriptorContextMenu(String ColumnTitle)
			throws Exception {
		try {
			WebElement contextMenuRightElement = driver
					.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]//*[@ref='center']/div/div[4]/div[3]/div"));
			GeneralUtil.contextMenu(contextMenuRightElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Insert Column");
			driver.findElement(
					By.xpath("//input[@placeholder = 'Insert Column']"))
					.sendKeys(ColumnTitle);

			driver.findElement(
					By.xpath("//div[3]/div/button/div/div[text() = 'Save']"))
					.click();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			GeneralUtil.logger.error("Element is not displayed");
		}
	}

	@AfterClass
	public void afterClass() throws Exception {
		
		driver.quit();
		Thread.sleep(3000);
		//Common.loginOffXVA();
	}

	public void verifyElementPresent(String strTrade, String strXpath,
			int timeOut) {
		try {
			boolean blnFind = GeneralUtil.waitUntilExists(strXpath, "xpath",
					timeOut);
			if (blnFind)
				GeneralUtil.logger.info(strTrade
						+ " MarketData item is displayed with given xpath: "
						+ strXpath);
			else
				GeneralUtil.logger
						.error(strTrade
								+ " MarketData item is not displayed with given xpath: "
								+ strXpath);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			GeneralUtil.logger
					.error("Element is not displayed.Detailes are Locator:"
							+ strXpath + " LocatorType:xpath WaitTime:"
							+ timeOut);
		}
	}
}
