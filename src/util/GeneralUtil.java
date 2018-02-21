package util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;

import freemarker.core.ReturnInstruction.Return;


public class GeneralUtil {

	public static WebDriver driver = null;

	public static String userName = null;
	public static String passWord = null;

	public static String browser = null;
	public static String screenshotFilePath = null;
	public static String implicitTimeOut = null;
	public static Integer dynamicTimeOut = 0;
	public static Integer pageLoadTimeOut = null;
	public static Integer scriptTimeOut = null;
	public static String enableScreenshotFeature = null;
	public static Logger logger = null;
	public static String strBrowserType = null;
	public static String strDownload_Path = null;
	public static String downloadFilePath = null;
	// public static String screenshotFileName = null;

	static {

		/*
		 * userName = PropertyUtils.getConfigMessage("Username");
		 * 
		 * passWord = PropertyUtils.getConfigMessage("Password");
		 */

		dynamicTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("DynamicTimeOut"));

		scriptTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("ScriptTimeout"));

		pageLoadTimeOut = Integer.valueOf(PropertyUtils
				.getConfigMessage("PageLoadTimeOut"));

		implicitTimeOut = PropertyUtils.getConfigMessage("ImplicitTimeOut");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_EEE_hh_mm");

		System.setProperty("results.folder.name", dateFormat.format(new Date()));
		
		String baseResultsFolder = System.getProperty("results.folder.name");
		
		// PropertyConfigurator.configure("Log4j.properties");



		enableScreenshotFeature = PropertyUtils
				.getConfigMessage("EnableScreenShotFeature");
		if (!enableScreenshotFeature.equalsIgnoreCase("true")
				&& !enableScreenshotFeature.equalsIgnoreCase("false")) {
			logger.error("Please provide true to enable and false for disabling the screenshot feature");
		}

		if (enableScreenshotFeature.equalsIgnoreCase("true")) {
			screenshotFilePath = System.getProperty("user.dir") + "\\ExecutionResults\\" + baseResultsFolder
					+ "\\" + PropertyUtils.getConfigMessage("ScreenshotDirectory") + "\\";
			System.out.println(screenshotFilePath);
			File ssDirectory = new File(screenshotFilePath);
			if (!ssDirectory.exists())
				ssDirectory.mkdir();
			System.setProperty("scr.folder", screenshotFilePath);
			//screenshotFilePath = screenshotFilePath
				//	+ new SimpleDateFormat("MMM_dd_EEE").format(new Date());
			//File datedSSdirectory = new File(screenshotFilePath);
			//if (!datedSSdirectory.exists())
				//datedSSdirectory.mkdir();
		}
		
		strBrowserType = PropertyUtils.getConfigMessage("Browser");
		//strDownload_Path = PropertyUtils.getConfigMessage("Download_Path");
		String path = FileSystemView.getFileSystemView().getHomeDirectory()
				.getPath();
		path = path.replace("Desktop", "Downloads");
		/*try {
           FileUtils.cleanDirectory(new File(path));
     } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
     }*/
     strBrowserType = PropertyUtils.getConfigMessage("Browser");
     downloadFilePath=System.getProperty("user.dir") + "\\ExecutionResults\\" + baseResultsFolder
                  + "\\" + PropertyUtils.getConfigMessage("DownloadDirectory") + "\\";
     System.out.println(downloadFilePath);
     File sssDirectory = new File(downloadFilePath);
     if (!sssDirectory.exists())
            sssDirectory.mkdir();
     System.setProperty("strDownload_Path", downloadFilePath);


		try {
			Browser.setBrowserType(strBrowserType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static WebDriver generalGetDriver() {
		try {
			driver = Browser.getDriver();
			return Browser.getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;		
	}

	/**
	 * @param args
	 */
	public static void logger(String loggerName) {
		logger = Logger.getLogger(loggerName);
	}

	public static void configureLog4j(String productName) {
		Properties props = new Properties();
		// Here we have defined root logger
		props.put("log4j.rootLogger", "INFO,CONSOLE,DRF,HTML");

		// Here we define the console logger
		props.put("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
		props.put("log4j.appender.CONSOLE.layout",
				"org.apache.log4j.PatternLayout");
		props.put("log4j.appender.CONSOLE.layout.ConversionPattern",
				" <%d{yyyy-MM-dd HH:mm:ss}> - %C{1} - %-50c{2} - %m%n");

		// Here we define the file logger
		props.put("log4j.appender.DRF",
				"org.apache.log4j.DailyRollingFileAppender");
		props.put("log4j.appender.DRF.Append", "false");
		props.put(
				"log4j.appender.DRF.File",
				String.format(
						"./ExecutionResults/${results.folder.name}/logs/XVA_%s_Execution_Results.log",
						productName));
		// props.put("log4j.appender.DRF.File","./Logs/FirstCucumberLog.log");
		props.put("log4j.appender.DRF.layout", "org.apache.log4j.PatternLayout");
		props.put("log4j.appender.DRF.layout.ConversionPattern",
				" <%d{yyyy-MM-dd HH:mm:ss}> - %C{1} - %-50c{2} - %m%n");

		// Here we define the html logger
		props.put("log4j.appender.HTML", "org.apache.log4j.FileAppender");
		props.put("log4j.appender.HTML.Append", "false");
		props.put(
				"log4j.appender.HTML.File",
				String.format(
						"./ExecutionResults/${results.folder.name}/logs/XVA_%s_Execution_Results.html",
						productName));
		// props.put("log4j.appender.HTML.File","./Logs/FirstSecondCucumberLog.html");
		// props.put("log4j.appender.HTML.layout","org.apache.log4j.HTMLLayout");
		props.put("log4j.appender.HTML.layout", "util.CustomHTMLLayout");
		props.put("log4j.appender.HTML.layout.Title",
				String.format("XVA_%s_Execution_Results.html", productName));
		props.put("log4j.appender.HTML.layout.LocationInfo", "false");
		LogManager.resetConfiguration();
		PropertyConfigurator.configure(props);
	}

	public static void webDriverWaitExpectedConditions(String condition,
			String elementXPath, int waitTime) throws Exception {
		try {
			if (condition.equalsIgnoreCase("elementToBeClickable")) {
				WebDriverWait NDriver = new WebDriverWait(driver, waitTime);
				NDriver.until(ExpectedConditions.elementToBeClickable(By
						.xpath(elementXPath)));
			}
		} catch (Exception e) {
			throw new Exception("Element with xpath: " + elementXPath
					+ " is not in " + condition + " State.");
		}

	}

	public static List<WebElement> getElements(String locator,
			String locatorType, int waitTime) throws Exception {
		List<WebElement> ElementList = null;
		try {
			boolean blnFind = waitUntilExists(locator, locatorType, waitTime);
			if (blnFind) {
				if (locatorType.equalsIgnoreCase("xpath")) {
					/*
					 * return driver .findElement(By.xpath(locator));
					 */
					ElementList = driver.findElements(By.xpath(locator));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(
					"Element is not displayed.Detailes are Locator:" + locator
							+ " LocatorType:" + locatorType + " WaitTime:"
							+ waitTime);
		}
		// FindElement()
		return ElementList;
	}

	public static WebElement getElement(String locator, String locatorType,
			int waitTime) throws Exception {
		try {
			boolean blnFind = waitUntilExists(locator, locatorType, waitTime);
			if (blnFind) {
				if (locatorType.equalsIgnoreCase("xpath")) {
					return driver.findElement(By.xpath(locator));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(
					"Element is not displayed.Detailes are Locator:" + locator
							+ " LocatorType:" + locatorType + " WaitTime:"
							+ waitTime);
		}
		// FindElement()
		return null;
	}

	public static boolean waitUntilExists(String object, String mode,
			int timeOut) throws InterruptedException {
		boolean result = false;
		Mode accessBy = Mode.valueOf(mode);
		int count = 1;
		while (count < timeOut) {
			switch (accessBy) {
			case xpath:
				if (driver.findElement(By.xpath(object)) != null) {
					result = true;
					count = 300;
				}
				break;
			case id:
				if (driver.findElement(By.id(object)) != null) {
					result = true;
					count = 300;
				}
				break;
			case linkText:
				if (driver.findElement(By.linkText(object)) != null) {
					result = true;
					count = 300;
				}
				break;
			case cssSel:
				if (driver.findElement(By.cssSelector(object)) != null) {
					result = true;
					count = 300;
				}
				break;
			default:
				break;
			}
			count++;
		}
		return result;
	}

	private enum Mode {
		xpath, id, linkText, value, cssSel;
	}

	public static void openBrowser() throws Exception {
		DesiredCapabilities capabilities = null;
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				capabilities = DesiredCapabilities.firefox();
				// System.out.println("Initiated Firefox browser instance");
				logger.info("Initiated " + browser + " browser instance");
			} else if (browser.equalsIgnoreCase("IE")
					|| browser.equalsIgnoreCase("internet explorer")) {
				File file = new File(System.getProperty("user.dir")
						+ "/Resources/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				capabilities = DesiredCapabilities.internetExplorer();
				// System.out.println("Initiated IE browser instance");
				logger.info("Initiated " + browser + " browser instance");
			} else if (browser.equalsIgnoreCase("Chrome")) {
				File file = new File(System.getProperty("user.dir")
						+ "/Resources/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver",
						file.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches", "--disable-extensions");
				// options.addArguments("--disable-extensions");
				driver = new ChromeDriver(options);
				capabilities = DesiredCapabilities.chrome();
				// System.out.println("Initiated Chrome browser instance");
				logger.info("Initiated " + browser + " browser instance");
			}

			// Printing capabilities
			if (capabilities != null) {
				// System.out.println(capabilities);
				// logger.info(capabilities);
			}

			/*
			 * EventFiringWebDriver efw = new EventFiringWebDriver(driver);
			 * ScreenShotListener screenShotListener = new ScreenShotListener();
			 * efw.register(screenShotListener);
			 */
			driver.manage().window().maximize();
			driver.manage()
					.timeouts()
					.setScriptTimeout(Long.valueOf(scriptTimeOut),
							TimeUnit.SECONDS);
			driver.manage()
					.timeouts()
					.pageLoadTimeout(Long.valueOf(pageLoadTimeOut),
							TimeUnit.SECONDS);
			driver.manage()
					.timeouts()
					.implicitlyWait(Long.valueOf(implicitTimeOut),
							TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(browser + " browser instance initiation fails.");
			throw new Exception(e.getMessage());
		}
	}

	public static void captureScreenshot() throws IOException {
		if (enableScreenshotFeature.equalsIgnoreCase("true")) {
			try {
				String screenshotFileName = Thread.currentThread()
						.getStackTrace()[2].getMethodName();
				// get the screenshot folder location from enviroment variable
				// set in beginning of test
				String scrFolder = System.getProperty("scr.folder");

				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(
						scrFile,
						new File(scrFolder
								+ "//"
								+ new SimpleDateFormat("HHmmss").format(
										Calendar.getInstance().getTime())
										.toString() + "_" + screenshotFileName
								+ ".png"));
				logger.info("Captured screenshot " + screenshotFileName
						+ ".png");
			} catch (Exception e) {
				logger.error("Unable to capture screen shot "
						+ e.getMessage().substring(0, 20));
			}
		}
	}

	public static void highlightTheElementRed(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));

			// dynamicWait(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: red; border: 2px solid red;");
			// dynamicWait(driver);
		} catch (Exception e) {
			// System.out.println("Object not found to highlight");
			logger.error("Object not found to highlight");
		}
	}

	public static void highlightTheElementGreen(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			// dynamicWait(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: green; border: 2px solid green;");
			// dynamicWait(driver);
		} catch (Exception e) {
			// System.out.println("Object not found to highlight");
			logger.error("Object not found to highlight");
		}
	}

	public static void unHighlightTheElement(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			// dynamicWait(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
			// dynamicWait(driver);
		} catch (Exception e) {
			// System.out.println("Object not found to highlight");
			logger.error("Object not found to unhighlight");
		}
	}

	public static Properties getMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", "smtp.office365.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.connectiontimeout", "100000");
		properties.put("mail.smtp.timeout", "100000");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.starttls.required", "true");
		return properties;
	}

	public static File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public static void createScreenshotFolder() {
//		String scrFolder = screenshotFilePath
//				+ "//"
//				+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(
//						Calendar.getInstance().getTime()).toString();
//		System.setProperty("scr.folder", scrFolder);
//		new File(scrFolder).mkdir();
	}

	public static void selectRadiobuttonItem(String strXpath, String ExpValue,
			String radiobuttonName) throws Exception {
		boolean btnFlag = false;
		List<WebElement> selectElement = driver
				.findElements(By.xpath(strXpath));
		for (WebElement element : selectElement) {
			if (element.getText().equalsIgnoreCase(ExpValue)) {
				element.click();
				logger.info(ExpValue + " is selected in " + radiobuttonName
						+ " Radiobutton.");
				btnFlag = true;
				break;
			}
		}
		if (!btnFlag) {
			throw new Exception(ExpValue + " is not displayed in "
					+ radiobuttonName + " Radiobutton.");
		}
	}

	public static void selectDropDownItem(String strXpath, String ExpValue,
			String dropDownName) throws Exception {
		boolean btnFlag = false;
		List<WebElement> selectElement = driver
				.findElements(By.xpath(strXpath));
		for (WebElement suggestion : selectElement) {
			String expText = suggestion.getText();
			if (expText.equalsIgnoreCase(ExpValue)) {
				suggestion.click();
				logger.info(ExpValue + " item is selected in " + dropDownName
						+ " Dropdown.");
				btnFlag = true;
				break;
			}
		}

		if (!btnFlag) {
			throw new Exception(ExpValue + " item is not displayed in "
					+ dropDownName + " Dropdown.");
		}
	}

	public static void contextMenuItem(String strXpath, String ExpValue)
			throws Exception {
		boolean btnFlag = false;
		List<WebElement> clickElement = driver.findElements(By.xpath(strXpath));
		for (WebElement suggestion : clickElement) {
			if (suggestion.getText().equalsIgnoreCase(ExpValue)) {
				suggestion.click();
				logger.info(ExpValue + " item is clicked on ContextMenu.");
				btnFlag = true;
				break;
			}
		}

		if (!btnFlag) {
			throw new Exception(ExpValue
					+ " item is not clicked on ContextMenu.");
		}
	}

	public static void selectItem(String strXpath, String ExpValue)
			throws Exception {
		boolean btnFlag = false;
		WebElement mySelectElm = driver.findElement(By.xpath(strXpath));

		if (mySelectElm.isDisplayed()) {
			Select mySelect = new Select(mySelectElm);
			mySelect.selectByVisibleText(ExpValue);
			logger.info(ExpValue + " item is clicked on Select Menu.");
			btnFlag = true;

		}

		if (!btnFlag) {
			throw new Exception(ExpValue
					+ " item is not clicked on Select Menu.");
		}
	}

	public static boolean verifyDropDownItem(String strXpath, String ExpValue,
			String dropDownName) throws Exception {

		List<WebElement> selectElement = driver
				.findElements(By.xpath(strXpath));
		for (WebElement suggestion : selectElement) {
			if (suggestion.getText().equalsIgnoreCase(ExpValue)) {
				return true;
			}
		}
		return false;
	}

	public static void contextMenu(WebElement element) {
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();

			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			// Setting clipboard with file location
			setClipboardData(fileLocation);
			Thread.sleep(1000);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void doubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver).doubleClick(element);
			action.build().perform();

			GeneralUtil.logger.info("Double clicked the element");
		} catch (StaleElementReferenceException e) {
			GeneralUtil.logger
					.error("Element is not attached to the page document "
							+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			GeneralUtil.logger.error("Element " + element
					+ " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			GeneralUtil.logger.error("Element " + element
					+ " was not clickable " + e.getStackTrace());
		}
	}

	public static void dropDownItemvisible(String strXpath, String ExpValue,
			String dropDownName) throws Exception {
		boolean btnFlag = false;
		List<WebElement> selectElement = driver
				.findElements(By.xpath(strXpath));
		for (WebElement suggestion : selectElement) {
			if (suggestion.getText().equalsIgnoreCase(ExpValue)) {
				// Create instance of Javascript executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				// Identify the WebElement which will appear after scrolling
				// down
				// WebElement element = driver.findElement(By.tagName("...."));
				// now execute query which actually will scroll until that
				// element is not appeared on page.
				je.executeScript("arguments[0].scrollIntoView(true);",
						suggestion);
				je.executeScript("arguments[0].click();", suggestion);

				logger.info(ExpValue + " item is selected in " + dropDownName
						+ " Dropdown.");
				btnFlag = true;
				break;
			}
		}

	}

}
