package util;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import test.ExecuteTests;

public class Browser {

	public static WebDriver Driver;
	public static String strBrowserType;
	private static ThreadLocal<WebDriver> ThreadDriver = null;
	public static String baseUrl;
	public static String intMinWait;
	public static String intMidWait;
	public static long intMaxWait;
	public static long strPollingEvery;
	public static String browserHandle;

	public static WebDriver IntilizeBrowser() throws Exception {
		DesiredCapabilities capabilities = null;
		try {
			// Thread local is the class which creates separate instance of
			// webdriver for every thread.
			ThreadDriver = new ThreadLocal<>();
			Driver = ThreadDriver.get();

			if (strBrowserType.equalsIgnoreCase("firefox")) {
				File file = new File(System.getProperty("user.dir") + "/Resources/geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

				DesiredCapabilities dc = DesiredCapabilities.firefox();
				// dc.merge(capabillities);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setPreference("browser.download.folderList", 4);
				profile.setPreference("browser.download.dir", GeneralUtil.strDownload_Path);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/msword, application/csv, application/ris, text/csv, data:image/png, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				Driver = new FirefoxDriver(dc);
				// System.out.println("Initiated Firefox browser instance");
				GeneralUtil.logger.info("Initiated " + strBrowserType + " browser instance");

			} else if (strBrowserType.equalsIgnoreCase("IE") || strBrowserType.equalsIgnoreCase("internet explorer")) {
				File file = new File(System.getProperty("user.dir") + "/Resources/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				Driver = new InternetExplorerDriver();
				ThreadDriver.set(Driver);
				capabilities = DesiredCapabilities.internetExplorer();
				// System.out.println("Initiated IE browser instance");
				GeneralUtil.logger.info("Initiated " + strBrowserType + " browser instance");
			} /*else if (strBrowserType.equalsIgnoreCase("Chrome")) {
				File file = new File(System.getProperty("user.dir") + "/Resources/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches", "--disable-extensions");
				// options.addArguments("--disable-extensions");
				Driver = new ChromeDriver(options);
				ThreadDriver.set(Driver);
				capabilities = DesiredCapabilities.chrome();
				// System.out.println("Initiated Chrome browser instance");
				GeneralUtil.logger.info("Initiated " + strBrowserType + " browser instance");
			}*/
			
			else if (strBrowserType.equalsIgnoreCase("Chrome")) {
				 
				File file = new File(System.getProperty("user.dir") + "/Resources/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				 HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
				 chromePrefs.put("profile.default_content_settings.popups", 0);  
				 chromePrefs.put("download.default_directory", GeneralUtil.downloadFilePath); 
				 ChromeOptions options = new ChromeOptions();  
				 options.setExperimentalOption("prefs", chromePrefs);  
				options.addArguments("chrome.switches", "--disable-extensions");
				// options.addArguments("--disable-extensions");
				Driver = new ChromeDriver(options);
				ThreadDriver.set(Driver);
				capabilities = DesiredCapabilities.chrome();
				// System.out.println("Initiated Chrome browser instance");
				GeneralUtil.logger.info("Initiated " + strBrowserType + " browser instance");
				
				
				
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
			Driver.manage().window().maximize();
			Driver.manage().timeouts().setScriptTimeout(Long.valueOf(GeneralUtil.scriptTimeOut), TimeUnit.SECONDS);
			Driver.manage().timeouts().pageLoadTimeout(Long.valueOf(GeneralUtil.pageLoadTimeOut), TimeUnit.SECONDS);
			Driver.manage().timeouts().implicitlyWait(Long.valueOf(GeneralUtil.implicitTimeOut), TimeUnit.SECONDS);

		} catch (Exception e) {
			GeneralUtil.logger.error(strBrowserType + " browser instance initiation is failed.");
			throw new Exception(e.getMessage());
		}
		return Driver;
	}

	public static void setBrowserType(String browserType) {
		strBrowserType = browserType;
	}

	public static WebDriver getDriver() throws Exception {
		if (Driver != null) {
			try {
				if (Driver.getWindowHandles() != null || Driver.getWindowHandles().isEmpty()) {
					return Driver;
				}

			} catch (Exception e) {
				return IntilizeBrowser();
			}

			return Driver;
		} else
			return IntilizeBrowser();
	}

	public static void NavigateToURL() {
		UrlValidator urlValidator = new UrlValidator();
		if (ExecuteTests.targetServerURL.equalsIgnoreCase("Windows")) {
			GeneralUtil.logger.info("Windows Url");
			Driver.navigate().to(PropertyUtils.getConfigMessage("WINDOWS_URL"));
		} else if (ExecuteTests.targetServerURL.equalsIgnoreCase("Linux")) {
			GeneralUtil.logger.info("Linux Url");
			Driver.navigate().to(PropertyUtils.getConfigMessage("LINUX_URL"));
		} else if (urlValidator.isValid(ExecuteTests.targetServerURL)) {
			GeneralUtil.logger.info(ExecuteTests.targetServerURL);
			Driver.navigate().to(ExecuteTests.targetServerURL);
		}else
		{
			GeneralUtil.logger.info("Url");
			Driver.navigate().to(PropertyUtils.getConfigMessage("URL"));
		}
		// Driver.manage().window().maximize();
	}

	public static String strGetWindowTitle() {
		return Driver.getTitle();
	}

}
