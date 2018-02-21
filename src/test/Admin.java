package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.Browser;
import util.GeneralUtil;
import util.PropertyUtils;

public class Admin {
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());;
	public int dynamicTimeOut = 0;
	public int scriptTimeOut;
	public String strBrowserType = null;
	public Logger logger = null;
	public WebDriver driver;
	public String serverSettingsName = "AggregationThreadCount";
	public String userName = "User123";
	public String userRole = "Administrators";
	public String userPassword = "password";
	public String addUserRole = "role123";
	public String editAuthorizationFunctions = "addRoles";
	public String nxtTemplateSettings = "CALENDAR.ASX";
	public String addAitionalFieldsName = "AdditionalFields_"+timeStamp;
	public String addRetentionPoliciesName = "RetentionPolicies_"+timeStamp;
	public String editRetentionPoliciesName = "RetentionPoliciesEdit_"+timeStamp;
	public String cloneRetentionPoliciesName = "RetentionPoliciesClone_"+timeStamp;
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
		GeneralUtil.configureLog4j("Admin Authorizations");
		// Assign logger object
		GeneralUtil.logger("Admin Authorizations");
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
	public void admin_verifyAdminTabs() throws Exception {
		try {
			GeneralUtil.logger.info("Step62 : Verify Admin Items");

			// Validating Admin Tab is expand or not and verify Admin sub tabs
			if (driver
					.findElement(
							By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Server Monitor']"))
					.isDisplayed()) {
				String strServerMonitorXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Server Monitor']";
				VerifyElementPresent("Server Monitor", strServerMonitorXpath,
						dynamicTimeOut);

				String strAuthorizationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']";
				VerifyElementPresent("Authorizations", strAuthorizationsXpath,
						dynamicTimeOut);

				String strTemplateSettingsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']";
				VerifyElementPresent("Template Settings",
						strTemplateSettingsXpath, dynamicTimeOut);

				String strAdditionalFieldsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[4]/div/div/div[text() ='Additional Fields']";
				VerifyElementPresent("Additional Fields",
						strAdditionalFieldsXpath, dynamicTimeOut);

				String strRetentionPoliciesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']";
				VerifyElementPresent("Retention Policies",
						strRetentionPoliciesXpath, dynamicTimeOut);
			}
			// Expand Admin Tab and verify Admin sub tabs
			else {
				driver.findElement(
						By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/div/div/div/Strong[text()='Admin']"))
						.click();

				String strServerMonitorXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Server Monitor']";
				VerifyElementPresent("Server Monitor", strServerMonitorXpath,
						dynamicTimeOut);

				String strAuthorizationsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']";
				VerifyElementPresent("Authorizations", strAuthorizationsXpath,
						dynamicTimeOut);

				String strTemplateSettingsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']";
				VerifyElementPresent("Template Settings",
						strTemplateSettingsXpath, dynamicTimeOut);

				String strAdditionalFieldsXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[4]/div/div/div[text() ='Additional Fields']";
				VerifyElementPresent("Additional Fields",
						strAdditionalFieldsXpath, dynamicTimeOut);

				String strRetentionPoliciesXpath = "//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']";
				VerifyElementPresent("Retention Policies",
						strRetentionPoliciesXpath, dynamicTimeOut);
			}
		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
			
		}

	}

	@Test(dependsOnMethods = "admin_verifyAdminTabs",alwaysRun=true)
	public void admin_serverMonitor() throws Exception {
		try {
			GeneralUtil.logger.info("Step63 to 67 :Server Monitor in Admin");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[1]/div/div/div[text() ='Server Monitor']"))
					.click();

			// Validating Server Monitor tabs(Server Settings,Audit Log,Server
			// Process Monitor & Server Logs
			String strServerSettingsXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Server Settings']";
			VerifyElementPresent("Server Settings", strServerSettingsXpath,
					dynamicTimeOut);

			String strAuditLogXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Audit Log']";
			VerifyElementPresent("Audit Log", strAuditLogXpath, dynamicTimeOut);

			String strServerProcessMonitorXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Server Process Monitor']";
			VerifyElementPresent("Server Process Monitor",
					strServerProcessMonitorXpath, dynamicTimeOut);

			String strServerLogsXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Server Logs']";
			VerifyElementPresent("Server Logs", strServerLogsXpath,
					dynamicTimeOut);

			// Perform Server Settings action(export CSV or Excel)file
			WebElement SearchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			SearchElement.clear();
			SearchElement.sendKeys(serverSettingsName);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			WebElement contextMenuelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuelement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

			// Perform Audit Log action(open log file)
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Audit Log']"))
					.click();
			GeneralUtil.logger
					.info("Audit Log implementation is pending due to TT-46398");

			// Perform Server Process Monitor action(export CSV or Excel)file
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Server Process Monitor']"))
					.click();
			Thread.sleep(1000);
			WebElement contextMenuSecondelement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div/div[1]/div/div/div"));
			GeneralUtil.contextMenu(contextMenuSecondelement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

			// Perform Server Logs action(log screen open)
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Server Logs']"))
					.click();

			WebElement serverLogsElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/div/div/span"));
			String serverLogsText = serverLogsElement.getText();

			String today = getToday("E MMM dd yyyy");
			if (serverLogsText.contains(today)) {
				GeneralUtil.logger.info("Server Log " + serverLogsText
						+ " is available in the Server Log page");
			} else {
				GeneralUtil.logger.error("Server Log " + serverLogsText
						+ " is not available in the Server Log page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}

	}
	
	@Test(dependsOnMethods =  "admin_serverMonitor",alwaysRun=true)
	public void admin_verifyAuthorizationsTabs() throws Exception {
		try {
			GeneralUtil.logger.info("Step68 :Verify Authorizations Tabs");
			WebElement authorizationsElements = driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					;
			
			// Create instance of Javascript executor
						JavascriptExecutor je = (JavascriptExecutor) driver;
						// Identify the WebElement which will appear after scrolling
						// down
						// WebElement element = driver.findElement(By.tagName("...."));
						// now execute query which actually will scroll until that
						// element is not appeared on page.
						je.executeScript("arguments[0].scrollIntoView(true);",
								authorizationsElements);
						je.executeScript("arguments[0].click();", authorizationsElements);
						Thread.sleep(2000);

			// Validating Authorizations tabs(Users,Roles & Authorization
			// Functions
			String strUsersXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Users']";
			VerifyElementPresent("Users", strUsersXpath, dynamicTimeOut);
			Thread.sleep(2000);
			String strRolesXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Roles']";
			VerifyElementPresent("Roles", strRolesXpath, dynamicTimeOut);

			String strAuthorizationFunctionsXpath = "//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Authorization Functions']";
			VerifyElementPresent("Authorization Functions",
					strAuthorizationFunctionsXpath, dynamicTimeOut);

			// Add user in Authorizations tabs
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");

			WebElement userNameElement = driver
					.findElement(By
							.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/input"));
			userNameElement.clear();
			userNameElement.sendKeys(userName);
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div/span[2]"))
					.click();
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					userRole, "Roles");
			Thread.sleep(1000);
			WebElement userPasswordElement = driver
					.findElement(By
							.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[3]/div/div[2]/div/input"));
			userPasswordElement.clear();
			userPasswordElement.sendKeys(userPassword);

			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating user added or not
			Common.handleNotifications("Update User");
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"))
					.sendKeys(userName);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String authorizationUsername = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (authorizationUsername.contains(userName)) {
				GeneralUtil.logger.info("Authorization user name "
						+ authorizationUsername
						+ " is available in the Authorization user page");
			} else {
				GeneralUtil.logger.error("Authorization user name "
						+ authorizationUsername
						+ " is not available in the Authorization user page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);

		}
		GeneralUtil.logger.info("Authorization user created successfully");
	}

	@Test(dependsOnMethods = { "admin_verifyAuthorizationsTabs" })
	public void admin_editUserAuthorizations() throws Exception {
		try {
			GeneralUtil.logger.info("Step68 :Edit a User Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			// Search user in search text field
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(userName);

			// Update user in Authorizations tabs
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			driver.findElement(By.xpath("//span[@class='Select-clear-zone']"))
					.click();
			driver.findElement(
					By.xpath("//div[@class='Select-control']/span/div[1]"))
					.click();
			GeneralUtil.selectDropDownItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"system", "Roles");

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating user updated or not
			Common.handleNotifications("Update User");
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchSecondElement.sendKeys(userName);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String authorizationUsername = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (authorizationUsername.contains(userName)) {
				GeneralUtil.logger.info("Authorization edited user name "
						+ authorizationUsername
						+ " is available in the Authorization user page");
			} else {
				GeneralUtil.logger.error("Authorization edited user name "
						+ authorizationUsername
						+ " is not available in the Authorization user page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization user edited successfully");
	}

	@Test(dependsOnMethods = { "admin_editUserAuthorizations" })
	public void admin_exportUserAuthorizations() throws Exception {
		try {
			GeneralUtil.logger.info("Step69 :Export a User Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			// Search user in search text field
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(userName);

			// Export user in Authorizations tabs
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization user exported successfully");
	}

	@Test(dependsOnMethods = { "admin_exportUserAuthorizations" })
	public void admin_deleteUserAuthorizations() throws Exception {
		try {
			GeneralUtil.logger.info("Step69 :delete a User Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			// Search user in search text field
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(userName);

			// Perform Delete user
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			Common.handleNotifications("Delete User");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization user deleted successfully");
	}

	@Test(dependsOnMethods =  "admin_deleteUserAuthorizations",alwaysRun=true)
	public void admin_addRole() throws Exception {
		try {
			GeneralUtil.logger.info("Step70 :Add Role Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Roles']"))
					.click();
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(userRole);
			Thread.sleep(2000);
			// Add Role in Authorizations tab
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");

			WebElement roleElement = driver
					.findElement(By
							.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/input"));
			roleElement.clear();
			roleElement.sendKeys(addUserRole);

			WebElement chkAdministratorElement = driver.findElement(By
					.xpath(".//*[@id='checkbox']/div/label"));
			chkAdministratorElement.click();

			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating Role is added or not
			Common.handleNotifications("Update Role");
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchElement.sendKeys(addUserRole);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String authorizationRolename = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (authorizationRolename.contains(addUserRole)) {
				GeneralUtil.logger.info("Authorization role name "
						+ authorizationRolename
						+ " is available in the Authorization role page");
			} else {
				GeneralUtil.logger.error("Authorization role name "
						+ authorizationRolename
						+ " is not available in the Authorization role page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization Role created successfully");
	}

	@Test(dependsOnMethods = { "admin_addRole" })
	public void admin_editRole() throws Exception {
		try {
			GeneralUtil.logger.info("Step70 :Edit Role Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Roles']"))
					.click();
			// Search the added role
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addUserRole);

			// Update the role
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();

			WebElement chkAdministratorElement = driver.findElement(By
					.xpath("//*[@id='checkbox']/div/label"));
			chkAdministratorElement.click();

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// validating role updated or not
			Common.handleNotifications("Update Role");
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchElement.sendKeys(addUserRole);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String authorizationRolename = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (authorizationRolename.contains(addUserRole)) {
				GeneralUtil.logger.info("Authorization role name "
						+ authorizationRolename
						+ " is available in the Authorization role page");
			} else {
				GeneralUtil.logger.error("Authorization role name "
						+ authorizationRolename
						+ " is not available in the Authorization role page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization Role edited successfully");
	}

	@Test(dependsOnMethods = { "admin_editRole" })
	public void admin_exportRole() throws Exception {
		try {
			GeneralUtil.logger.info("Step70 :Export Role Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Roles']"))
					.click();
			// search the added role
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addUserRole);

			// Export the role
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization Role exported successfully");
	}

	@Test(dependsOnMethods = { "admin_exportRole" })
	public void admin_deleteRole() throws Exception {
		try {
			GeneralUtil.logger.info("Step70 :Delete Role Authorizations");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Roles']"))
					.click();
			// search the updated the role
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addUserRole);

			// Delete the role
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			Common.handleNotifications("Delete Role");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization Role deleted successfully");
	}

	@Test(dependsOnMethods = "admin_deleteRole",alwaysRun=true)
	public void admin_editAuthorizationFunctions() throws Exception {
		try {
			GeneralUtil.logger.info("Step71 :Edit Authorization Functions");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Authorization Functions']"))
					.click();
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(editAuthorizationFunctions);
			Thread.sleep(2000);
			// update the Authorization Functions
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();

			WebElement chkAdministratorElement = driver.findElement(By
					.xpath(".//*[@id='checkbox']/div/label"));
			chkAdministratorElement.click();

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating Authorization Functions update or not
			//Common.handleNotifications("Update Auth Function");
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchElement.sendKeys(editAuthorizationFunctions);
			Thread.sleep(1000);
			GeneralUtil.captureScreenshot();
			String authorizationFunctionsname = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();
			if (authorizationFunctionsname.contains(addUserRole)) {
				GeneralUtil.logger.info("Authorization role name "
						+ authorizationFunctionsname
						+ " is available in the Authorization role page");
			} else {
				GeneralUtil.logger.error("Authorization role name "
						+ authorizationFunctionsname
						+ " is not available in the Authorization role page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Authorization Functions edited successfully");
	}

	@Test(dependsOnMethods = { "admin_editAuthorizationFunctions" })
	public void admin_exportAuthorizationFunctions() throws Exception {
		try {
			GeneralUtil.logger.info("Step71 :Export Authorization Functions");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[2]/div/div/div[text() ='Authorizations']"))
					.click();
			driver.findElement(
					By.xpath("//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div/ul/li/label[text()='Authorization Functions']"))
					.click();
			// Search the Authorization Functions
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(editAuthorizationFunctions);
Thread.sleep(1000);
			// Export Authorization Functions
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger
				.info("Authorization Functions exported successfully");
	}

	@Test(dependsOnMethods = "admin_exportAuthorizationFunctions",alwaysRun=true)
	public void admin_uploladTemplateSettings() throws Exception {
		try {
			GeneralUtil.logger.info("Step72 :Upload Template Settings");
			WebElement templateSettingsElements = driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']"))
					;
			
			// Create instance of Javascript executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			// Identify the WebElement which will appear after scrolling
			// down
			// WebElement element = driver.findElement(By.tagName("...."));
			// now execute query which actually will scroll until that
			// element is not appeared on page.
			je.executeScript("arguments[0].scrollIntoView(true);",
					templateSettingsElements);
			je.executeScript("arguments[0].click();", templateSettingsElements);
			
			Thread.sleep(20000);
			// Upload 'ASX.nxt' file in Template Settings
			WebElement contextMenuElement = driver.findElement(By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"NXT Upload");
			Thread.sleep(1000);
			
			
			GeneralUtil.uploadFile(casPath + "\\cas\\data\\Content\\Repository\\Calendar\\ASX.nxt");
			Thread.sleep(20000);
			
			//Common.handleNotifications("Upload template");
			
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			WebElement searchElement = driver
					.findElement(By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(nxtTemplateSettings);
			Thread.sleep(1000);

			// Validating 'ASX.nxt' file is uploaded or not
			GeneralUtil.captureScreenshot();
			String templateSettingsname = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]/span/span[2]"))
					.getText();
			if (templateSettingsname.contains(nxtTemplateSettings)) {
				GeneralUtil.logger.info("Template Settings name "
						+ templateSettingsname
						+ " is available in the Template Settings page");
			} else {
				GeneralUtil.logger.error("Template Settings name "
						+ templateSettingsname
						+ " is not available in the Template Settings page");
			}
			

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Template Settings upload successfully");
	}

	@Test(dependsOnMethods = { "admin_uploladTemplateSettings" })
	public void admin_downloadTemplateSettings() throws Exception {
		try {
			GeneralUtil.logger("Step72 :Download Template Settings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']"))
					.click();
			// Search the upload 'ASX.nxt' file
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(nxtTemplateSettings);

			// Download "Templates(.*).zip" file in Template Settings
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"NXT Download",
							"Download All",
							"Templates(.*).zip",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Template Settings download successfully");
	}

	@Test(dependsOnMethods = { "admin_downloadTemplateSettings" })
	public void admin_exportTemplateSettings() throws Exception {
		try {
			GeneralUtil.logger("Step72 :Export Template Settings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']"))
					.click();
			// Search the upload 'ASX.nxt' file
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(nxtTemplateSettings);

			// Export the "export(.*).csv" file
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Template Settings export successfully");
	}

	@Test(dependsOnMethods = { "admin_exportTemplateSettings" })
	public void admin_deleteTemplateSettings() throws Exception {
		try {
			GeneralUtil.logger("Step72 :Delete Template Settings");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[3]/div/div/div[text() ='Template Settings']"))
					.click();
			// Search the upload 'ASX.nxt' file
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(nxtTemplateSettings);

			// Delete the 'ASX.nxt' file
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			Common.handleNotifications("Success");
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Template Settings delete successfully");
	}

	@Test(dependsOnMethods = "admin_deleteTemplateSettings",alwaysRun=true)
	public void admin_addAdditionalFields() throws Exception {
		try {
			GeneralUtil.logger("Step73 :Add Additional Fields");
			GeneralUtil.logger.info("Step73 :Add Additional Fields");
			WebElement additionalFieldsElements = driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[4]/div/div/div[text() ='Additional Fields']"));
			
			// Create instance of Javascript executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			// Identify the WebElement which will appear after scrolling
			// down
			// WebElement element = driver.findElement(By.tagName("...."));
			// now execute query which actually will scroll until that
			// element is not appeared on page.
			je.executeScript("arguments[0].scrollIntoView(true);",
					additionalFieldsElements);
			je.executeScript("arguments[0].click();", additionalFieldsElements);
			
			Thread.sleep(7000);

			// Adding Additional Fields
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[1]/div/div[2]/div/input"))
					.sendKeys(addAitionalFieldsName);

			// Enter values in add page in Additional Fields
			driver.findElement(
					By.xpath("//div[@class='react-draggable']/div/div/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/div/div/span/div[1]"))
					.click();
			GeneralUtil.contextMenuItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Long");
			driver.findElement(
					By.xpath("//div[@class='react-draggable']/div/div/div[2]/div/div[1]/div[3]/div/div[2]/div/div/div/div/div/span/div[1]"))
					.click();
			GeneralUtil.contextMenuItem(
					"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
					"Calculation Output");
			
			/*driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[4]/div/div[2]/div/input"))
					.sendKeys("Trade Property");*/
			/*driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div[5]/div/div[2]/div/input"))
					.sendKeys("Trade Property key");*/
			
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			// Enter values in add page in Additional Fields
						/*driver.findElement(
								By.xpath("html/body/div[1]/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div/div[2]/div[4]/div/div[2]/div/div/div/div/div/span[1]/div[1]"))
								.click();
						GeneralUtil.contextMenuItem(
								"//div[@class='Select-menu-outer']/div/div[1]/div/div/div",
								"Calculation Output");*/
			if(driver.findElement(By.xpath("//div[text() = 'Save']")).isDisplayed()==true) {
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			}
			

			// Validating Additional Fields added or not
		/*	Common.handleNotifications("Save Additional Fields");
			GeneralUtil.captureScreenshot();
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			Thread.sleep(1000);
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div/div[1]/div[2]/span"))
					.click();
			WebElement chkSelectAllElement = driver.findElement(By
					.xpath(".//*[@id='selectAll']"));
			chkSelectAllElement.click();
			WebElement searchFilterElement = driver.findElement(By
					.xpath(".//*[@id='ag-mini-filter']/input"));
			searchFilterElement.clear();
			searchFilterElement.sendKeys(addAitionalFieldsName);
			WebElement chkaddedElement = driver.findElement(By
					.xpath(".//*[@id='richList']/div/div/div[1]/label"));
			chkaddedElement.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"))
					.click();
			String additionalFieldsname = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (additionalFieldsname.contains(addAitionalFieldsName)) {
				GeneralUtil.logger.info("Additional Fields name "
						+ additionalFieldsname
						+ " is available in the Additional Fields page");
			} else {
				GeneralUtil.logger.error("Additional Fields name "
						+ additionalFieldsname
						+ " is not available in the Additional Fields page");
			}*/

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			if(e.toString().contains("//div[text() = 'Save']")) {
				GeneralUtil.logger.error("Save Element not visible");
			}
			else {
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}}
		GeneralUtil.logger.info("Additional Fields added successfully");
	}

	@Test(dependsOnMethods = { "admin_addAdditionalFields" })
	public void admin_exportAdditionalFields() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Export Additional Fields");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[4]/div/div/div[text() ='Additional Fields']"))
					.click();
			Thread.sleep(1000);

			// search the added Additional Fields in name context menu
			//COMMENTED BY SESHA PHANI
			/*driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div/div[1]/div[2]/span"))
					.click();
			WebElement chkSelectAllElement = driver.findElement(By
					.xpath(".//*[@id='selectAll']"));
			chkSelectAllElement.click();*/
			WebElement searchFilterElement = driver.findElement(By
					.xpath("//div[@class='ag-pinned-left-header']/div[2]/div/div/div/input[@id='float-asset']"));
			searchFilterElement.clear();
			searchFilterElement.sendKeys(addAitionalFieldsName);
			//COMMENTED BY SESHA PHANI
			/*WebElement chkaddedElement = driver.findElement(By
					.xpath(".//*[@id='richList']/div/div/div[1]/label"));
			chkaddedElement.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"))
					.click();*/

			// performed export on added Additional Fields
			Thread.sleep(1000);
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			Thread.sleep(1000);
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Thread.sleep(1000);
			Common.download(
							"Export",
							"Excel Export",
							"export(.*).xls",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Additional Fields export successfully");
	}

	@Test(dependsOnMethods = { "admin_exportAdditionalFields" })
	public void admin_deleteAdditionalFields() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Delete Additional Fields");
			
			Thread.sleep(2000);
			// search the added Additional Fields in name context menu
			/*driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div/div[1]/div[2]/span"))
					.click();
			WebElement searchFilterElement = driver.findElement(By
					.xpath(".//*[@id='ag-mini-filter']/input"));
			searchFilterElement.clear();
			searchFilterElement.sendKeys(addAitionalFieldsName);
			WebElement chkSelectAllElement = driver.findElement(By
					.xpath(".//*[@id='selectAll']"));
			chkSelectAllElement.click();
			chkSelectAllElement.click();
			WebElement chkaddedElement = driver.findElement(By
					.xpath(".//*[@id='richList']/div/div/div[1]/label"));
			chkaddedElement.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div"))
					.click();*/
			
			// performed delete on added Additional Fields
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");
			//driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			Common.handleNotifications("Save Additional Fields");
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Additional Fields delete successfully");
	}

	@Test(dependsOnMethods = "admin_deleteAdditionalFields",alwaysRun=true)
	public void admin_addRetentionPolicies() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Add Retention Policies");
			WebElement retentionPoliciesElement = driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']"))
					;
			
			// Create instance of Javascript executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			// Identify the WebElement which will appear after scrolling
			// down
			// WebElement element = driver.findElement(By.tagName("...."));
			// now execute query which actually will scroll until that
			// element is not appeared on page.
			je.executeScript("arguments[0].scrollIntoView(true);",retentionPoliciesElement);
			je.executeScript("arguments[0].click();", retentionPoliciesElement);
			Thread.sleep(1000);

			// Adding Retention Policies
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Add");
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input"))
					.sendKeys(addRetentionPoliciesName);

			driver.findElement(By.xpath("//div[text() = 'OK']")).click();
			if(driver.findElement(By.xpath("//div[text() = 'Save']")).isDisplayed()==true) {
			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating Retention Policies added or not
			Common.handleNotifications("Save Retention Policy");
			GeneralUtil.captureScreenshot();
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			//driver.findElement(By.xpath("//div[text() = 'Cancel']")).click();
			}
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addRetentionPoliciesName);
			Thread.sleep(1000);
			String retentionPoliciesName = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (retentionPoliciesName.contains(addRetentionPoliciesName)) {
				GeneralUtil.logger.info("Retention Policies name "
						+ retentionPoliciesName
						+ " is available in the Retention Policies page");
			} else {
				GeneralUtil.logger.error("Retention Policies name "
						+ retentionPoliciesName
						+ " is not available in the Retention Policies page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			if(e.toString().contains("//div[text() = 'Save']")) {
				GeneralUtil.logger.error("Save Element not visible");
			}
			else {
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}}
		GeneralUtil.logger.info("Retention Policies added successfully");
	}

	@Test(dependsOnMethods = { "admin_addRetentionPolicies" })
	public void admin_editRetentionPolicies() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Edit Retention Policies");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']"))
					.click();
			Thread.sleep(1000);
			// Updating Retention Policies
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addRetentionPoliciesName);
			driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div/div[1]/span/span[1]/span[2]")).click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			driver.findElement(
					By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.click();
			WebElement policyNameElement = driver
					.findElement(By
							.xpath(".//*[@id='root']/div/div/div/div[2]/div[3]/div/div[6]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div/div[4]/div/div/div/input"));
			policyNameElement.clear();
			policyNameElement.sendKeys(editRetentionPoliciesName);

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating Retention Policies edited or not
			Common.handleNotifications("Save Retention Policy");
			GeneralUtil.captureScreenshot();
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			//driver.findElement(By.xpath("//div[text() = 'Cancel']")).click();
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchSecondElement.sendKeys(editRetentionPoliciesName);

			String retentionPoliciesName = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (retentionPoliciesName.contains(editRetentionPoliciesName)) {
				GeneralUtil.logger.info("Retention Policies name "
						+ retentionPoliciesName
						+ " is available in the Retention Policies page");
			} else {
				GeneralUtil.logger.error("Retention Policies name "
						+ retentionPoliciesName
						+ " is not available in the Retention Policies page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Retention Policies edited successfully");
	}

	@Test(dependsOnMethods = { "admin_editRetentionPolicies" })
	public void admin_cloneRetentionPolicies() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Clone Retention Policies");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']"))
					.click();
			Thread.sleep(1000);
			// Search the added Retention Policies
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addRetentionPoliciesName);
			
			// Cloned the Retention Policies
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Clone");
			driver.findElement(
					By.xpath("html/body/div[4]/div/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/input"))
					.sendKeys(cloneRetentionPoliciesName);

			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			driver.findElement(By.xpath("//div[text() = 'Save']")).click();
			driver.findElement(By.xpath("//div[text() = 'OK']")).click();

			// Validating the Retention Policies cloned or not
			Common.handleNotifications("Save Retention Policy");
			GeneralUtil.captureScreenshot();
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");
			//driver.findElement(By.xpath("//div[text() = 'Cancel']")).click();
			WebElement searchSecondElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchSecondElement.clear();
			searchSecondElement.sendKeys(cloneRetentionPoliciesName);

			String retentionPoliciesName = driver
					.findElement(
							By.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"))
					.getText();
			if (retentionPoliciesName.contains(cloneRetentionPoliciesName)) {
				GeneralUtil.logger.info("Retention Policies name "
						+ retentionPoliciesName
						+ " is available in the Retention Policies page");
			} else {
				GeneralUtil.logger.error("Retention Policies name "
						+ retentionPoliciesName
						+ " is not available in the Retention Policies page");
			}

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Retention Policies cloned successfully");
	}

	@Test(dependsOnMethods = { "admin_cloneRetentionPolicies" })
	public void admin_exportRetentionPolicies() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Export Retention Policies");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']"))
					.click();
			Thread.sleep(1000);
			// Search the added Retention Policies
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addRetentionPoliciesName);
			driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div/div[1]/span/span[1]/span[2]")).click();
			// Export the Retention Policies
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			Common
					.download(
							"Export",
							"CSV Export",
							"export(.*).csv",
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							".//*[@id='borderLayout_eRootPanel']/div[4]/div/div/span[2]");
			GeneralUtil.captureScreenshot();

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Retention Policies export successfully");
	}

	@Test(dependsOnMethods = { "admin_exportRetentionPolicies" })
	public void admin_deleteRetentionPolicies() throws Exception {
		try {
			GeneralUtil.logger.info("Step73 :Delete Retention Policies");
			driver.findElement(
					By.xpath("//div[starts-with(@class,'src-routes-Home-components-MainPage-SidebarContent')]/div/div/div[starts-with(@class,'src-components-BetterTreeView-TreeView-TreeView')]//ul/li/span/ul/li[5]/div/div/div[text() ='Retention Policies']"))
					.click();
			Thread.sleep(1000);
			// Search the added Retention Policies
			WebElement searchElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchElement.clear();
			searchElement.sendKeys(addRetentionPoliciesName);
			Thread.sleep(1000);
			//driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div/div[1]/span/span[1]/span[1]")).click();
			// Delete the added Retention Policies
			WebElement contextMenuElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuElement);
			GeneralUtil.captureScreenshot();
			
			//Changed By SESHA PHANI From "Delete" to "Delete Checked Items"
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete Checked Items");
			
			WebElement confirmationElement = driver
					.findElement(By
							.xpath("html/body/div[3]/div/div/div/div/div/div[2]/div/div/div[1]/button"));
			confirmationElement.click();
			// Validating the Retention Policies deleted or not
			Common.handleNotifications("Delete Retention Policy");
			//TT 45574 - Application change in in build 4.6 
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Search the edited Retention Policies
			WebElement searchEditElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchEditElement.clear();
			searchEditElement.sendKeys(editRetentionPoliciesName);
			Thread.sleep(1000);
			//driver.findElement(By.xpath(".//*[@id='borderLayout_eGridPanel']/div[1]/div/div[4]/div[3]/div/div[1]/div/div[1]/span/span[1]/span[1]")).click();
			// Delete the updated Retention Policies
			WebElement contextMenuEditElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuEditElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");

			// Validating the Retention Policies deleted or not
			Common.handleNotifications("Delete Retention Policy");
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

			// Search the cloned Retention Policies
			//COMMENTED BY SESHA PHANI
			/*WebElement searchCloneElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[1]/div[3]/div/div[2]/div[1]/div/div/input"));
			searchCloneElement.clear();
			searchCloneElement.sendKeys(cloneRetentionPoliciesName);
			Thread.sleep(1000);

			// Delete the cloned Retention Policies
			WebElement contextMenuCloneElement = driver
					.findElement(By
							.xpath(".//*[@ref='center']/div/div[4]/div[3]/div/div[1]/div[1]/div[1]"));
			GeneralUtil.contextMenu(contextMenuCloneElement);
			GeneralUtil.captureScreenshot();
			GeneralUtil
					.contextMenuItem(
							".//*[@id='borderLayout_eRootPanel']/div[3]/div/div/span[2]",
							"Delete");

			// Validating the Retention Policies deleted or not
			Common.handleNotifications("Delete Retention Policy");*/
			//Common.clickNotification("//i[@class='fa fa-bell-o fa-fw']");

		} catch (Exception e) {
			GeneralUtil.captureScreenshot();
			e.printStackTrace();
			GeneralUtil.logger.error(e);
			throw new Exception(e);
		}
		GeneralUtil.logger.info("Retention Policies delete successfully");
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

	public String getToday(String format) {
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
	}

}
