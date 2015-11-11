package org.exoplatform.selenium;

import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.error;
import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestBase {

	public WebDriver driver;

	public WebDriver newDriver;

	public static String hubURL;

	public static String plfURL;

	public static String browser;

	public static String platform;

	protected String nativeEvent;

	protected int DEFAULT_TIMEOUT = 40000; //milliseconds = 30 seconds

	protected int WAIT_INTERVAL = 50; //milliseconds

	public int loopCount = 0;

	public boolean ieFlag;

	public boolean chromeFlag;

	/**
	 * 4.0 : Version 4.0.x.
	 * 4.1 : Version 4.1.x.
	 * 4.2 : Version 4.2.x.
	 * ect...
	 */
	protected String plfVersion = "";

	public final int ACTION_REPEAT = 5;

	public static boolean firstTimeLogin = false;

	public Actions action;

	/*======= Welcome Screen (Term and Conditions) =====*/
	public final By ELEMENT_FIRSTNAME_ACCOUNT = By.name("firstNameAccount");

	public final By ELEMENT_LASTNAME_ACCOUNT = By.name("lastNameAccount");

	public final By ELEMENT_EMAIL_ACCOUNT = By.name("emailAccount");

	public final By ELEMENT_CONFIRM_PASS_ACCOUNT = By.name("confirmUserPasswordAccount");

	public final By ELEMENT_ROOT_PASS_ACCOUNT = By.name("adminPassword");

	public final By ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT = By.name("confirmAdminPassword");

	public final By ELEMENT_AGREEMENT_CHECKBOX = By.xpath("//*[@id = 'agreement']");

	public final By ELEMENT_INPUT_USERNAME = By.name("username");

	public final By ELEMENT_CONTINUE_BUTTON = By.xpath("//button[text()='Continue' and @class='btn active']");

	public final By ELEMENT_START_BUTTON = By.xpath("//button[text()='Start']");

	public final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");

	public final By ELEMENT_INPUT_PASSWORD = By.name("password");

	public final By ELEMENT_ACCOUNT_NAME_LINK = By.xpath("//*[@id='UIUserPlatformToolBarPortlet']/a");

	public final By ELEMENT_PLF_INFORMATION = By.id("platformInfoDiv");

	public final String ELEMENT_TERM_CONDITION_BOX = "//div[@class='header' and text()='Terms and Conditions Agreement']/..";

	public final By ELEMENT_CONTINUE_BUTTON_DISABLE = By.xpath("//button[text()='Continue' and @class='btn inactive']");

	public final By ELEMENT_TERM_CONDITION_CONTENT = By.xpath("//div[@id='AccountSetup' and @class='content']");

	public final By ELEMENT_ACCOUNT_SETUP = By.xpath("//div[@class='header' and text()='Account Setup']");

	public final By ELEMENT_USER_ADMIN = By.id("adminFirstName");

	public final By ELEMENT_SKIP_BUTTON = By.xpath("//button[text()='Skip']");

	public final By ELEMENT_YOUR_ACCOUNT_LABEL = By.xpath("//h5[contains(text(), 'Create your account')]");

	public final By ELEMENT_ADMIN_PASS_LABEL = By.xpath("//h5[contains(text(), 'Admin Password')]");

	public final By ELEMENT_ACCOUNT_ERROR2 = By.xpath("//div[2][@class='createAccountError']/*[@class='accountSetupError']");

	public final By ELEMENT_ACCOUNT_ERROR1 = By.xpath("//div[1][@class='createAccountError']/*[@class='accountSetupError']");

	public final By ELEMENT_GOOGLE_PAGE_LOGO = By.id("hplogo");

	public final By ELEMENT_SIGN_OUT_LINK = By.className("uiIconPLFLogout");

	//Driver path
	public String uploadfile = Utils.getAbsoluteFilePath("TestData\\attachFile.exe");

	public String downloadfile = Utils.getAbsoluteFilePath("TestData\\downloadIE9.exe");

	public String ieDriver = Utils.getAbsoluteFilePath("TestData\\IEDriverServer.exe");

	public String chromeDriver = Utils.getAbsoluteFilePath("TestData\\chromedriver.exe");

	public String chromeDriverUbuntu = Utils.getAbsoluteFilePath("TestData\\chromedriver");

	/*========Default System Property=============*/
	public final String DEFAULT_NATIVE_EVENT = "true";

	public final String DEFAULT_BROWSER = "firefox";//iexplorer, firefox, chrome

	public final String DEFAULT_PLATFORM = "LINUX"; //win, ubuntu

	public final String DEFAULT_PLF_URL = "http://localhost:8080/portal";

	public final String DEFAULT_HUB_URL = "http://localhost:4444/wd/hub";

	//public final String DEFAULT_PLF_URL="http://192.168.3.28:8080/portal";

	/**
	 * Get System Property
	 */
	public void getSystemProperty() {
		nativeEvent = System.getProperty("nativeEvent");
		browser = System.getProperty("browser");
		platform = System.getProperty("platform");
		hubURL = System.getProperty("hubURL");
		plfURL = System.getProperty("plfURL");

		if (nativeEvent == null)
			nativeEvent = DEFAULT_NATIVE_EVENT;
		if (browser == null)
			browser = DEFAULT_BROWSER;
		if (hubURL == null)
			hubURL = DEFAULT_HUB_URL;
		if (plfURL == null)
			plfURL = DEFAULT_PLF_URL;
		if (platform == null)
			platform = DEFAULT_PLATFORM;
	}

	public TestBase() {
	}

	Actions actions;

	public TestBase(WebDriver dr) {
		driver = dr;
		actions = new Actions(driver);
	}

	/**
	 * Init Chrome driver
	 */
	public WebDriver initChromeDriver() throws Exception {
		info("Init chrome driver with HUB");
		getSystemProperty();
		String pathFile = "";
		String fs = File.separator;
		String temp = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";
		;
		pathFile = temp.replace("/", fs).replace("\\", fs);

		if (platform.contains("ubuntu")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
		} else if (platform.contains("win")) {
			System.setProperty("webdriver.chrome.driver", chromeDriver);
		} else {
			System.setProperty("webdriver.chrome.driver", chromeDriverUbuntu);
		}

		// Add the WebDriver proxy capability.
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String[] switches = { "start-maximized", "remote-debugging-port=9222" };
		capabilities.setCapability("chrome.switches", switches);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", pathFile);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		//capabilities.setCapability("nativeEvents", true);
		//return new ChromeDriver(capabilities);
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.LINUX);
		return new RemoteWebDriver(new URL(hubURL), capabilities);
	}

	/**
	 * Init IE driver
	 */
	public WebDriver initIEDriver() throws Exception{
		info("Init IE driver");
		System.setProperty("webdriver.ie.driver",ieDriver) ;
		DesiredCapabilities  capabilitiesIE = DesiredCapabilities.internetExplorer();
		capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
		if ("true".equals(nativeEvent)){
			info("Set nativeEvent is TRUE");
			capabilitiesIE.setCapability("nativeEvents", true);
		}
		else{
			info("Set nativeEvent is FALSE");
			capabilitiesIE.setCapability("nativeEvents", false);
		}
		capabilitiesIE.setCapability("javascriptEnabled", true);
		capabilitiesIE.setCapability("requireWindowFocus", true);
		capabilitiesIE.setCapability("enablePersistentHover", false);
		capabilitiesIE.setCapability("ignoreZoomSetting", true);
		capabilitiesIE.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilitiesIE.setCapability("initialBrowserUrl", plfURL);
		//return new InternetExplorerDriver(capabilitiesIE);
		return new RemoteWebDriver(new URL(hubURL), capabilitiesIE);
	}

	/**
	 * Init FF driver
	 */
	public WebDriver initFFDriver() throws Exception{
		String pathFile="";
		String fs = File.separator;
		String temp=System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput";;
		pathFile=temp.replace("/", fs).replace("\\", fs);
		info("Init FF driver");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("plugins.hide_infobar_for_missing_plugin", true);
		profile.setPreference("dom.max_script_run_time", 0);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		info("Save file to " + pathFile);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", pathFile);
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-xpinstall;" +
				"application/x-zip;application/x-zip-compressed;application/x-winzip;application/zip;" +
				"gzip/document;multipart/x-zip;application/x-gunzip;application/x-gzip;application/x-gzip-compressed;" +
				"application/x-bzip;application/gzipped;application/gzip-compressed;application/gzip" +
				"application/octet-stream" +
				";application/pdf;application/msword;text/plain;" +
				"application/octet;text/calendar;text/x-vcalendar;text/Calendar;" +
				"text/x-vCalendar;image/jpeg;image/jpg;image/jp_;application/jpg;" +
				"application/x-jpg;image/pjpeg;image/pipeg;image/vnd.swiftview-jpeg;image/x-xbitmap;image/png;application/xml;text/xml;text/icalendar;");

		profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf");
		profile.setPreference("pref.downloads.disable_button.edit_actions", true);
		profile.setPreference("pdfjs.disabled", true); 
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		return new RemoteWebDriver(new URL(hubURL), capabilities);
	}
	/**
	 * typeUsingRobot
	 * @param robot
	 * @param keycodes
	 */
	public void pressGroupKeysUsingRobot(int... keycodes){
		info("Copy a text on the home page of acme");
		Robot robot=null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		robot.setAutoDelay(20);
		for(int keycode:keycodes){
			robot.keyPress(keycode);
		}
		for(int keycode:keycodes){
			robot.keyRelease(keycode);
		}
	}
	public void initSeleniumTestWithOutTermAndCondition(Object... opParams) throws Exception{
		info("init selenium test");
		getSystemProperty();
		if(browser.contains("chrome")){
			driver = initChromeDriver();
			chromeFlag = true;
		}
		else if(platform.contains("firefox")){
			driver = initFFDriver();
		}
		else if(platform.contains("iexplorer")){
			driver = initIEDriver();
			ieFlag = true;
		}
		else{
			driver = initFFDriver();
		}
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.get(plfURL);
	}

	/**
	 * init newDriver
	 */
	public void initNewDriver() throws Exception{
		getSystemProperty();
		if("chrome".equals(browser)){
			newDriver = new ChromeDriver();
			chromeFlag = true;
		} else if ("iexplorer".equals(browser)){
			newDriver = initIEDriver();
			ieFlag = true;
		} else {
			newDriver = initFFDriver();
		}
	}
	public void initSeleniumTest(Object... opParams){
		try {
			initSeleniumTestWithOutTermAndCondition();
		} catch (Exception e) {
			error("Can't initialize Selenium configuration.");
		}
		termsAndConditions(opParams);
		checkPLFVersion();
	}

	/**
	 * Check term and conditions
	 * 
	 */
	public void termsAndConditions(Object... opParams){
		Boolean isCreateAccount = (Boolean)(opParams.length>0 ? opParams[0]:true);
		driver.navigate().to(plfURL);
		info("Agreement page");
		if (waitForAndGetElement(ELEMENT_AGREEMENT_CHECKBOX, 3000, 0, 2) != null) {
			info("-- Checking the terms and conditions agreement... --");
			click(ELEMENT_AGREEMENT_CHECKBOX, 2);
			click(ELEMENT_CONTINUE_BUTTON);
			waitForTextNotPresent("terms and conditions agreement");

			info("-- Creating an Admin account: FQA... --");
			if(isCreateAccount==true)
				accountSetup();
			firstTimeLogin = true;
			info("-- Administrator account (FQA) has been created successfully... --");
		}else if (waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT, 3000, 0, 2) != null){
			info("-- Creating an Admin account: FQA... --");
			accountSetup();
			firstTimeLogin = true;
			info("-- Administrator account (FQA) has been created successfully... --");
		} 
		Utils.pause(3000);     
	}

	/**
	 * Verify plf version
	 */
	public void checkPLFVersion(){
		try{
			info("Verify platform version");
			String des = waitForAndGetElement(ELEMENT_PLF_INFORMATION).getText();
			if(des.contains("v4.0")){
				this.plfVersion = "4.0";
				info("Platform version 4.0.x");
			}
			else if(des.contains("v4.1") || des.contains("v4.2")){
				this.plfVersion="4.1";
				info("Platform version 4.1.x or 4.2");
			}
		}catch(Exception e){
			info("Unknown platform version. Set to default platform version 4.1.x or 4.2");
			this.plfVersion="4.1";
		}

	}
	/**
	 * Account setup without Greeting
	 */
	public void accountSetupWithoutGreeting(){
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, "gtngtn", true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, "gtngtn", true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, "gtngtn", true);
		click(ELEMENT_SUBMIT_BUTTON);
		waitForTextNotPresent("Create your account");
	}

	/**
	 * Account Setup
	 */
	public void accountSetup(){
		accountSetupWithoutGreeting();
		click(ELEMENT_START_BUTTON);
		waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK);
		driver.navigate().refresh();
		click(ELEMENT_ACCOUNT_NAME_LINK);
		waitForAndGetElement(ELEMENT_SIGN_OUT_LINK);
		click(ELEMENT_SIGN_OUT_LINK);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
	}

	/**
	 * Get Element
	 * @param locator
	 * @param opParams
	 * @return WebElement
	 */
	public WebElement getElement(Object locator, Object... opParams) {
		By by = null;
		String text = "";
		if (locator instanceof By)
			by = (By) locator;
		else
			if(locator instanceof String){
				text = (String) locator;
				if(text.contains("//")){
					by = By.xpath(text);
				}
				else{
					by = By.cssSelector(text);
				}
			}

		WebDriver wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);	
		WebElement elem = null;
		try {
			elem = wDriver.findElement(by);
		} catch (NoSuchElementException e) {

		}
		return elem;
	}

	/**
	 * return element only in case the element is displayed.
	 * @param locator
	 * @param opParams
	 * @return WebElement
	 */
	public WebElement getDisplayedElement(Object locator, Object... opParams) {
		WebDriver wDriver = (WebDriver) (opParams.length > 0 ? opParams[0]: driver);
		WebElement e = null;
		By by = null;
		String text = "";
		if (locator instanceof By)
			by = (By) locator;
		else
			if(locator instanceof String){
				text = (String) locator;
				if(text.contains("//")){
					by = By.xpath(text);
				}
				else{
					by = By.cssSelector(text);
				}
			}

		try {
			if(by != null){
				if(wDriver.findElements(by).size()>0){
					e = wDriver.findElement(by);
					if (isDisplay(by)) return e;
				}
			}
		} catch (NoSuchElementException ex) {
			//			info("NoSuchElementException");
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			getDisplayedElement(locator);
		}
		finally{
			loopCount=0;
		}
		return null;
	}

	public boolean isElementPresent(Object locator) {
		return getElement(locator) != null;
	}

	public boolean isElementNotPresent(Object locator) {
		return !isElementPresent(locator);
	}

	/* @opPram[0]: timeout
	 * @opPram[1]: 0,1
	 * 0: No Assert
	 * 1: Assert
	 */
	public WebElement waitForAndGetElement(Object locator, Object... opParams) {
		WebElement elem = null;
		int timeout = (Integer) (opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT);
		int isAssert = (Integer) (opParams.length > 1 ? opParams[1]: 1);
		int notDisplayE = (Integer) (opParams.length > 2 ? opParams[2]: 0);
		WebDriver wDriver = (WebDriver) (opParams.length > 3 ? opParams[3]: driver);	
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator,wDriver);
			}else{
				elem = getDisplayedElement(locator,wDriver);
			}
			if (null != elem) return elem;
			Utils.pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		info("cannot find element after " + timeout/1000 + "s.");
		Utils.pause(1000);
		return null;
	}

	/**
	 * @opPram[0]: timeout
	 * @opPram[1]: 0,1
	 * 0: No Assert
	 * 1: Assert
	 */
	public WebElement waitForElementNotPresent(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
		int isAssert = opParams.length > 1 ? opParams[1]: 1;
		int notDisplayE = opParams.length > 2 ? opParams[2]: 0;

		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			if (notDisplayE == 2){
				elem = getElement(locator);
				//elem = getDisplayedElement(locator);
			}else{
				elem = getDisplayedElement(locator);
			}
			if (null == elem) return null;
			Utils.pause(WAIT_INTERVAL);
		}

		if (isAssert == 1)
			assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
		info("Element doesn't disappear after " + timeout/1000 + "s.");
		return elem;
	}

	/**
	 * Check a text is present or not
	 * @param text
	 * @param opts
	 * @return true or false
	 */
	public boolean isTextPresent(String text, int...opts) {
		int display = opts.length > 0 ? opts[0] : 1;
		Utils.pause(500);
		String allVisibleTexts = getText(By.xpath("//body"),display);
		return allVisibleTexts.contains(text);
	}

	/**
	 * Get a text
	 * @param locator
	 * @param opts
	 * @return string
	 */
	public String getText(Object locator,int...opts) {
		WebElement element = null;
		int display = opts.length > 0 ? opts[0] : 1;
		try {
			element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			return element.getText();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			return getText(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Get a list elements
	 * @param xpath
	 * @return list
	 */
	public List<WebElement> getElements(String xpath) {
		try {
			return driver.findElements(By.xpath(xpath));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			return getElements(xpath);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * check a text is not present
	 * @param text
	 * @return true or false
	 */
	public boolean isTextNotPresent(String text) {
		return !isTextPresent(text);
	}

	/**
	 * Drag and drop a object
	 * @param sourceLocator
	 * @param targetLocator
	 */
	public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
		info("--Drag and drop to object--");
		Actions action = new Actions(driver);
		try {
			WebElement source = waitForAndGetElement(sourceLocator);
			WebElement target = waitForAndGetElement(targetLocator);

			action.dragAndDrop(source, target).build().perform();

		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			dragAndDropToObject(sourceLocator, targetLocator);
		}catch (UnhandledAlertException e) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();
				switchToParentWindow();
			} catch (NoAlertPresentException eNoAlert) {
			}
		}

		finally {
			loopCount = 0;
		}
		Utils.pause(1000);
	}

	/**
	 * Click action
	 * @param locator
	 * @param opParams
	 */
	public void click(Object locator, Object... opParams) {
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);		
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
			if(element.isEnabled())
				actions.click(element).perform();
			else {
				debug("Element is not enabled");
				click(locator, notDisplay);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator, notDisplay);
		} finally {
			loopCount = 0;
		}
		Utils.pause(500);
	}


	/**
	 * Click by using javascript
	 * @param locator: locator of element
	 */
	public void clickByJavascript(Object locator, Object... opParams){
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);	
		WebElement element = waitForAndGetElement(locator,DEFAULT_TIMEOUT, 1, notDisplay);
		if (browser.contains("chrome")) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].scrollIntoView(true);", element);
			} catch (Exception e) {

			}
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

	/**
	 * Clear cache
	 */
	public void clearCache(){
		Actions actionObject = new Actions(driver);
		try{
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch(WebDriverException e){	
			debug("Retrying clear cache...");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}
	}

	/**
	 * Use this function to verify if a check-box is checked (using when creating a portal/publicMode)
	 * @param locator
	 * @param opParams
	 */
	public void check(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		//		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
			if (!element.isSelected()) {
				click(locator,notDisplayE);
			} else {
				info("Element " + locator + " is already checked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			check(locator, opParams);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Get value of a locator
	 * @param locator
	 * @return string
	 */
	public String getValue(Object locator) {
		try {
			return waitForAndGetElement(locator).getAttribute("value");
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return getValue(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * MouseOver action
	 * @param locator
	 * @param safeToSERE
	 * @param opParams
	 */
	public void mouseOver(Object locator, boolean safeToSERE, Object...opParams) {
		WebElement element;
		Actions actions = new Actions(driver);
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			if (safeToSERE) {
				for (int i = 1; i < ACTION_REPEAT; i++){
					element = waitForAndGetElement(locator, 5000, 0, notDisplay);
					if (browser.contains("chrome")) {
						try {
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].scrollIntoView(true);", element);
						} catch (Exception e) {

						}
					}
					if (element == null){
						Utils.pause(WAIT_INTERVAL);
					} else {
						actions.moveToElement(element).perform();
						break;
					}
				}
			} else {
				element = waitForAndGetElement(locator);
				if (browser.contains("chrome")) {
					try {
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", element);
					} catch (Exception e) {

					}
				}
				actions.moveToElement(element).perform();
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			mouseOver(locator, safeToSERE);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * MouseOver and click action
	 * @param locator
	 */
	public void mouseOverAndClick(Object locator) {
		WebElement element;
		Actions actions = new Actions(driver);
		if (ieFlag) {
			element = getDisplayedElement(locator);
		} else {
			element = waitForAndGetElement(locator);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
		}
		actions.moveToElement(element).click(element).build().perform();
	}

	/**
	 * Wait for a text is present
	 * @param text
	 * @param opts
	 */
	public void waitForTextPresent(String text, int...opts) {
		int waitTime = opts.length > 0 ? opts[0] : DEFAULT_TIMEOUT;
		int display = opts.length > 1 ? opts[1] : 1;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextPresent: " + text);
			}
			if (isTextPresent(text,display)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * Wait for a text is not present
	 * @param text
	 * @param wait
	 */
	public void waitForTextNotPresent(String text,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		for (int second = 0;; second++) {
			if (second >= waitTime/WAIT_INTERVAL) {
				Assert.fail("Timeout at waitForTextNotPresent: " + text);
			}
			if (isTextNotPresent(text)) {
				break;
			}
			Utils.pause(WAIT_INTERVAL);
		}
	}

	/**
	 * Wait a message
	 * @param message
	 * @param wait
	 */
	public void waitForMessage(String message,int...wait) {
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		Utils.pause(500);
		waitForAndGetElement("//*[contains(text(),'"+message+"')]",waitTime);
	}

	/**
	 * type action
	 * @param locator
	 * @param value
	 * @param validate
	 * @param opParams
	 */
	public void type(Object locator, String value, boolean validate, Object...opParams) {	
		int notDisplay = (Integer) (opParams.length > 0 ? opParams[0]: 0);
		try {
			for (int loop = 1;; loop++) {
				if (loop >= ACTION_REPEAT) {
					Assert.fail("Timeout at type: " + value + " into " + locator);
				}
				WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplay);
				if (browser.contains("chrome")) {
					try {
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", element);
					} catch (Exception e) {

					}
				}
				if (element != null){
					if (validate)element.clear();
					element.click();
					element.sendKeys(value);
					if (!validate || value.equals(getValue(locator))) {
						break;
					}
				}
				info("Repeat action..." + loop + "time(s)");
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			type(locator, value, validate, opParams);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Select option from combo box
	 * @param locator
	 * @param option
	 * @param display
	 */
	public void select(Object locator, String option, int...display) {
		int isDisplay = display.length > 0 ? display[0] : 1;
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into " + locator);
				}
				WebElement element=waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,isDisplay);
				if (browser.contains("chrome")) {
					try {
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", element);
					} catch (Exception e) {

					}
				}
				Select select = new Select(element);
				select.selectByVisibleText(option);
				if (option.equals(select.getFirstSelectedOption().getText())) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * un-check a checked-box
	 * @param locator
	 * @param opParams
	 */
	public void uncheck(Object locator, int... opParams) {
		int notDisplayE = opParams.length > 0 ? opParams[0]: 0;
		//		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, notDisplayE);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
			if (element.isSelected()) {
				click(locator,notDisplayE);
			} else {
				info("Element " + locator + " is already unchecked.");
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			uncheck(locator, opParams);
		} finally {
			loopCount = 0;
		}
	}
	/**
	 * Right click on a element
	 * @param locator
	 * @param opParams
	 */
	public void rightClickOnElement(Object locator, int...opParams) {
		int display = opParams.length > 0 ? opParams[0]: 0;
		Actions actions = new Actions(this.driver);
		Utils.pause(500);
		try {
			WebElement element = waitForAndGetElement(locator,DEFAULT_TIMEOUT,1,display);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
			actions.contextClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			rightClickOnElement(locator);
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			click(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * doubleClickOnElement
	 * @param locator
	 */
	public void doubleClickOnElement(Object locator) {
		Actions actions = new Actions(driver);
		try {
			WebElement element = waitForAndGetElement(locator);
			if (browser.contains("chrome")) {
				try {
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].scrollIntoView(true);", element);
				} catch (Exception e) {

				}
			}
			actions.doubleClick(element).perform();
		} catch (StaleElementReferenceException e) {
			checkCycling(e, 5);
			Utils.pause(1000);
			doubleClickOnElement(locator);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Check cycling
	 * @param e
	 * @param loopCountAllowed
	 */
	public void checkCycling(Exception e, int loopCountAllowed) {
		info("Exception:" + e.getClass().getName());
		if (loopCount > loopCountAllowed) {
			Assert.fail("Cycled: " + e.getMessage());
		}
		info("Repeat... " + loopCount + "time(s)");
		loopCount++;
	}

	/**
	 * function to switch to parent windows
	 */
	public void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			Utils.pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Check a locator is display or not
	 * @param locator
	 * @return true or false
	 */
	public boolean isDisplay(Object locator) {
		boolean bool = false;
		WebElement e = getElement(locator);
		try{
			if (e!=null)
				bool = e.isDisplayed();
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			Utils.pause(WAIT_INTERVAL);
			isDisplay(locator);
		}
		finally{
			loopCount=0;
		}
		return bool;
	}

	/**function set driver to auto open new window when click link
	 * @author lientm
	 */
	public void getDriverAutoOpenWindow(){
		FirefoxProfile fp = new FirefoxProfile();		
		fp.setPreference("browser.link.open_newwindow.restriction", 2);
		driver = new FirefoxDriver(fp);
		hubURL = System.getProperty("hubURL");
		if (hubURL==null) hubURL = DEFAULT_PLF_URL;
		action = new Actions(driver);
		termsAndConditions();
		checkPLFVersion();
	}

	/**
	 * function: check a file existed in folder
	 * @author lientm
	 * @param file: file name (eg: export.zip)
	 * @return: true -> file exist
	 * false-> file is not exist
	 */
	public boolean checkFileExisted(String file){
		boolean fileExists=false;
		String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput/";
		File folder = new File(pathFile);
		File[] listOfFiles = folder.listFiles();
		for (File file1 : listOfFiles) {
			if (file1.isFile()) {
				if(file1.getName().contains(file)){
					fileExists=true;
					break;
				}
			}
		}
		return fileExists;
	}

	/**
	 * function delete file in folder test output
	 * @author lientm
	 * @param file: file name
	 */
	public void deleteFile(String file){
		String pathFile = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput/" + file;
		File Files = new File(pathFile);
		if(checkFileExisted(file)){
			Files.setWritable(true);
			Files.delete();
		}
		if (checkFileExisted(file) == false){
			info("Delete file successfully");
		}else info("Have error when delete file");
	}

	/**
	 * Cut, paste a file from output to TestData
	 * @param fileName
	 */
	public void cutPasteFileFromOutputToTestData(String fileName){
		String source = System.getProperty("user.dir") + "/src/main/resources/TestData/TestOutput/" + fileName;
		//directory where file will be copied
		String target = System.getProperty("user.dir") + "/src/main/resources/TestData/";

		//name of source file
		File sourceFile = new File(source);
		String name = sourceFile.getName();

		File targetFile = new File(target+name);

		//copy file from one location to other
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//delete file in TestOutput
		deleteFile("TestOutput/" + fileName);
	}

	/**
	 * Select a language
	 *
	 */
	public enum Language{
		en, fr, vi, lo;
	}

	/**
	 * Get driver browser with a language
	 * @param language
	 */
	public void getDriverSetLanguage(Language language){
		String locale = language.toString();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", locale);
		driver = new FirefoxDriver(profile);
		hubURL = System.getProperty("hubURL");
		if (hubURL==null) hubURL = DEFAULT_PLF_URL;
		action = new Actions(driver);
		termsAndConditions();
	}

	/**
	 * function get current Date of system follow a format
	 * @author lientm
	 * @param fomat
	 * @return
	 */
	public String getCurrentDate(String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date(); 
		return (dateFormat.format(date));
	}


	/**
	 * Add 1 minute to current date time
	 * @param min
	 * @param format
	 * @return
	 */
	public String addMinuteToCurrentDateTime(int min, String...format){
		DateFormat dateFormat = format.length > 0 ? new SimpleDateFormat(format[0]) : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, min);
		return (dateFormat.format(cal.getTime()));	
	}
	/**
	 * Add time to Current date time
	 * @param number
	 * @param typeOfTime
	 * @param format
	 * @return
	 */
	public String addTimeToCurrentDateTime(int number, int typeOfTime, String...format){
		DateFormat dateFormat = format.length > 0 ? new SimpleDateFormat(format[0]) : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		switch (typeOfTime) {
		case 1: cal.add(Calendar.SECOND,number); break;
		case 2:  cal.add(Calendar.MINUTE, number); break;
		case 3: cal.add(Calendar.HOUR,number); break;
		default: cal.add(Calendar.MINUTE, number); break;
		}
		return (dateFormat.format(cal.getTime()));	
	}

	/** Get date in format "dd"
	 * @author thuntn
	 * @param gap: distance from current date
	 * @return date in format "dd"
	 */
	public String getDate(int gap, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, gap);
		return (dateFormat.format(cal.getTime()));	
	}

	/** Get day of week
	 * @author phuongdt
	 * @param gap: distance from current date
	 * @return day of week (monday, tuesday,..., sunday)
	 */
	public int getDayOfWeek(int gap){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, gap);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Get minute in format "mm" from current date
	 * @author havtt
	 * @return minute
	 * 
	 */
	public int getMinute(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		int minute = cal.get(Calendar.MINUTE);
		return (minute); 
	}

	/**
	 * Change attribute "display" of HTML tag from "none" to "block" 
	 * @author havtt
	 */
	public void changeDisplayAttributeHTML(String locator){
		WebElement element = waitForAndGetElement(locator, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';",element);	
	}


	/**
	 * Set preference run time
	 */
	public void setPreferenceRunTime(){
		FirefoxProfile fp = new FirefoxProfile();

		fp.setPreference("dom.max_script_run_time", 30);
	}

	/**
	 * Mouse hover by Javascript
	 * @param targetElement
	 */
	public void mouseHoverByJavaScript(WebElement targetElement)
	{
		String argu1 = "var evObj = document.createEvent('MouseEvents');";
		String argu2 = "evObj.initMouseEvent('mouseover',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);";
		String argu3 =  "arguments[0].dispatchEvent(evObj);";
		String javascript = argu1 + argu2 + argu3;
		((JavascriptExecutor)driver).executeScript(javascript, targetElement);
	}

	/**
	 * change lanugage of browser
	 * @param language
	 */
	public void initFFBrowserWithSetLanguageBrowser(String language){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages",language);   
		driver = new FirefoxDriver(profile);
		hubURL = System.getProperty("hubURL");
		if (hubURL==null) hubURL = DEFAULT_PLF_URL;
		action = new Actions(driver);
	}

	/**
	 * get random string
	 * @author phuongdt
	 * @return string
	 */
	public String getRandomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * get a list of random numbers author quynhpt
	 * 
	 * @return string
	 */
	public String getRandomNumber() {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Copy and paste a string from one locator to other
	 * @param origin
	 * @param target
	 * @param value
	 */
	public void copyPasteString(By origin, By target, String value) {
		WebElement element1 = driver.findElement(origin);
		WebElement element2 = driver.findElement(target);

		info("Type into the first locator");
		element1.clear();
		element1.click();
		element1.sendKeys(value);

		info("Copy from the first locator");
		element1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element1.sendKeys(Keys.chord(Keys.CONTROL, "c"));

		info("Paste to the second locator");
		element2.click();
		element2.sendKeys(Keys.chord(Keys.CONTROL, "v"));
	}

	/**
	 * Get minute in format "HH" from current date
	 * @author chinhdtt
	 * @return hours
	 * 
	 */
	public int getHours(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		int minute = cal.get(Calendar.HOUR);
		return (minute); 
	}

	/**
	 * Check exist a scroll bar
	 * @param object
	 * @return = true: if there is not scroll bar on element
	 *         = false: if there is scroll bar on element
	 */
	public boolean checkExitScrollBar(By object){
		WebElement element = waitForAndGetElement(object);
		String scrollHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].scrollHeight;", element));
		String offsetHeight = String.valueOf(((JavascriptExecutor)driver).executeScript("return arguments[0].offsetHeight;", element));
		info("scrollHeight: " + scrollHeight);
		info("offsetHeight: " + offsetHeight);
		int scroll = Integer.parseInt(scrollHeight);
		int offset = Integer.parseInt(offsetHeight);
		return scroll == offset;
	}



	/**
	 * Find new window handle
	 * @param existingHandles
	 * @param timeout
	 * @return string
	 */
	public String FindNewWindowHandle(Set<String> existingHandles, int timeout)
	{
		Calendar calEnd = Calendar.getInstance();
		String foundHandle = new String();
		calEnd.add(Calendar.SECOND, timeout);
		Date endTime = calEnd.getTime();
		Calendar calNow = Calendar.getInstance();
		while (foundHandle.isEmpty() && calNow.getTime().before(endTime))
		{
			Set<String> currentHandles = driver.getWindowHandles();
			if (currentHandles.size() != existingHandles.size())
			{
				for (String currentHandle : currentHandles)
				{
					if (!existingHandles.contains(currentHandle))
					{
						foundHandle = currentHandle;
						break;
					}
				}
			}

			if (foundHandle.isEmpty())
			{
				Utils.pause(250);
			}
		}

		// Note: could optionally check for handle found here and throw
		// an exception if no window was found.
		return foundHandle;
	}

	/**
	 * Upload file using autoit
	 * @param file: name of file
	 */
	protected void uploadFileUsingAutoIt(String file){
		info("Upload file using autoit");
		String fs = File.separator;
		try {
			Process proc=Runtime.getRuntime().exec(Utils.getAbsoluteFilePath("TestData\\uploadFile.exe") + " " + Utils.getAbsoluteFilePath(file.replace("/", fs)));
			InputStream is = proc.getInputStream();
			int retCode = 0;
			while(retCode != -1)
			{
				retCode = is.read();
				if(retCode == -1)
					info("Now Exiting");
			} 
			info("done upload");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Download file using autoit
	 * @param file
	 */
	public void downloadFileUsingAutoIt(String file){
		info("Download file using autoit");
		String download = "TestData\\downloadIE9.exe";
		String fs = File.separator;
		String pathDownload = Utils.getAbsoluteFilePath(download);
		try {
			Process proc=Runtime.getRuntime().exec(pathDownload + " " + Utils.getAbsoluteFilePath("TestData" +fs + "TestOutput" + fs + file));
			InputStream is = proc.getInputStream();
			int retCode = 0;
			while(retCode != -1)
			{
				retCode = is.read();
				info("Now Exiting");
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Click and save a file by robot
	 * @param element
	 */
	public void downloadFileUsingRobot(WebElement element) {
		info("Download file using Robot");
		try {
			Robot robot = new Robot();

			// Get the focus on the element..don't use click since it stalls the driver         
			element.sendKeys("");

			//simulate pressing enter           
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// Wait for the download manager to open           
			Utils.pause(2000);

			// Switch to download manager tray via Alt+N
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);

			// Press S key to save           
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_S);
			Utils.pause(2000);

			// Switch back to download manager tray via Alt+N
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);

			// Tab to X exit key
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

			// Press Enter to close the Download Manager
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get element by class name via Javascript
	 * @param className
	 * @param index
	 * @return
	 */
	public WebElement getElementByJavascript(String className,int...index){
		int i = index.length > 0 ? index[0] : 0;
		WebElement e = (WebElement)((JavascriptExecutor) driver).executeScript("return document.getElementsByClassName('"+className+"')["+i+"];");
		return e;
	}

	/**
	 *This function will try to get an element. if after timeout, the element is not found.
	 *The function will refresh the page and find the element again.
	 * @param element
	 */
	public void waitElementAndTryGetElement(Object element, Object... opParams){
		info("-- Starting finding element --");
		int timeout = (Integer) (opParams.length>0 ? opParams[0] : DEFAULT_TIMEOUT);
		Utils.pause(500);
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++){
			if (waitForAndGetElement(element, 5000, 0) != null){
				info("Element "+element+" is displayed");
				break;
			}
			info("Retry...[" + tick + "]");
			driver.navigate().refresh();
		}
		Utils.pause(2000);
		info("The elemnt is shown successfully");
	}

	/**
	 * Clear clipboard
	 */
	public void clearClipboard(){
		StringSelection stringSelection = new StringSelection("");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				stringSelection, null);  
	}

	/**
	 * Get clipboard
	 * @return
	 */
	public String getClipboard(){
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//odd: the Object param of getContents is not currently used
		Transferable contents = clipboard.getContents(null);
		try {
			result = (String)contents.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info("result is "+result);
		return result;
	}

	/**
	 * uploadFileUsingRobot
	 * @param fileLocation
	 */
	public void uploadFileUsingRobot(String fileLocation) {
		info("Upload file using Robot");
		String fs = File.separator;
		String path=getAbsoluteFilePath(fileLocation.replace("/", fs));
		info("path in uploadRobot:"+path);
		try {
			Robot robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_X);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_X);
			//Setting clipboard with file location
			setClipboardData(path);
			//native key strokes for CTRL, V and ENTER keys

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1000);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * setClipboardData
	 * @param string
	 */
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * This function returns a absolute path from a relative path
	 * @param relativeFilePath
	 * @return - FQA-2092: Run and check calendar sniff on IE and FF
	 */
	public String getAbsoluteFilePath(String relativeFilePath){
		String fs = File.separator;
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
		absolutePath=absolutePath.replace("/", fs);
		return absolutePath;
	}
}
