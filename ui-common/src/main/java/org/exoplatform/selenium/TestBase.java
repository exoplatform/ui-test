package org.exoplatform.selenium;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import static org.exoplatform.selenium.TestLogger.*;

public class TestBase {
  protected static WebDriver driver;
  protected static Actions actions ;
  protected String baseUrl;
  protected static int DEFAULT_TIMEOUT = 10000; //milliseconds = 10 seconds
  protected static int WAIT_INTERVAL = 100; //milliseconds  
  public static int loopCount = 0;	
  protected  static boolean ieFlag;	 
  protected  static boolean chromeFlag;

  public static final String AJAX_LOADING_MASK = "//div[@id='AjaxLoadingMask']";
  public static final String DEFAULT_BASEURL="http://localhost:8080";
   
  public void initSeleniumTest(){
	  String browser = System.getProperty("browser");
	  if("chrome".equals(browser)){
		  driver = new ChromeDriver();
		  chromeFlag = true;
	  } else if ("iexplorer".equals(browser)){
		  driver = new InternetExplorerDriver();
		  ieFlag = true;
	  } else {
		  driver = new FirefoxDriver();
	  }
	  baseUrl = System.getProperty("baseUrl");
	  if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
	  
  }
  
  public WebElement getElement(Object locator) {
    By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
    WebElement elem = null;
    try {
      elem = driver.findElement(by);
    } catch (NoSuchElementException e) {
    }
    return elem;
  }

  public boolean isElementPresent(Object locator) {
    return getElement(locator) != null;
  }

  public boolean isElementNotPresent(Object locator) {
    return !isElementPresent(locator);
  }

  public void waitForElementPresent(Object locator, int... timeInMillis) {
    WebElement elem = null;
    int timeout = timeInMillis.length>0 ? timeInMillis[0] : DEFAULT_TIMEOUT;
    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
      elem = getElement(locator);
      if (null != elem) break;
      pause(WAIT_INTERVAL);
    }
    debug("Timeout after " + timeout + "ms waiting for element present: " + locator);
  }

  public void waitForElementNotPresent(Object locator, int... timeInMillis) {
    WebElement elem = null;
    int timeout = timeInMillis.length>0 ? timeInMillis[0] : DEFAULT_TIMEOUT;
    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
      elem = getElement(locator);
      if (null == elem) break;
      pause(WAIT_INTERVAL);
    }
    debug("Timeout after " + timeout + "ms waiting for element not present: " + locator);
  }

  public WebElement waitForAndGetElement(Object locator, int... timeInMillis) {
    WebElement elem = null;
    int timeout = timeInMillis.length>0 ? timeInMillis[0] : DEFAULT_TIMEOUT;
    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
      elem = getElement(locator);
      if (null != elem) break;
      pause(WAIT_INTERVAL);
    }
    debug("Timeout after " + timeout + "ms waiting for element " + locator);
    return elem;
  }
  
  public boolean isTextPresent(String text) {
	  pause(500);
	  String allVisibleTexts = getText(By.xpath("//body"));
	  return allVisibleTexts.contains(text);
  }

  public String getText(Object locator) {
	  WebElement element = null;
	  try {
		  element = waitForAndGetElement(locator);
		  return element.getText();
	  } catch (StaleElementReferenceException e) {
		  pause(1000);
		  return getText(locator);
	  }
  }

  public static List<WebElement> getElements(String xpath) {
      try {
          return driver.findElements(By.xpath(xpath));
      } catch (StaleElementReferenceException e) {
          pause(1000);
          return getElements(xpath);
      } finally {
          loopCount = 0;
      }
  }
  
  public boolean isTextNotPresent(String text) {
	  return !isTextPresent(text);
  }

  public static String getTextFromAlert() {
	  try {
		  Alert alert = driver.switchTo().alert();
		  return alert.getText();
	  } catch (NoAlertPresentException e) {
		  return "";
	  }
  }

  public static void acceptAlert() {
	  try {
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
	  } catch (NoAlertPresentException e) {
	  }
  }

  public static void pause(long timeInMillis) {
	  try {
		  Thread.sleep(timeInMillis);
	  } catch (InterruptedException e) {
		  e.printStackTrace();
	  }
  }

  public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
	  info("--Drag and drop to object--");
	  Actions action = new Actions(driver);
	  try {
		  WebElement source = waitForAndGetElement(sourceLocator);
		  WebElement target = waitForAndGetElement(targetLocator);

		  action.dragAndDrop(source, target).build().perform();
	  } catch (StaleElementReferenceException e) {
		  debug("drag and drop error!");
	  } 
  }

  public void click(Object locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);
		  actions.click(element).perform();
	  } catch (StaleElementReferenceException e) {
		  pause(1000);
		  click(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public void clearCache(){
	  Actions actionObject = new Actions(driver);	 
	  actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
  }

  //Use this function to verify if a checkbox is checked (using when creating a portal/publicMode)
  public void check(Object locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);

		  if (!element.isSelected()) {
			  actions.click(element).perform();
		  } else {
			  Assert.fail("Element " + locator + " is already checked.");
		  }
	  } catch (StaleElementReferenceException e) {
		  pause(100);
		  check(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public  String getValue(Object locator) {
	  try {
		  return waitForAndGetElement(locator).getAttribute("value");
	  } catch (StaleElementReferenceException e) {
		  pause(100);
		  return getValue(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public  void mouseOver(Object locator, boolean safeToSERE) {
	  if (safeToSERE) {
		  try {
			  WebElement element = waitForAndGetElement(locator);
			  actions.moveToElement(element).perform();
		  } catch (StaleElementReferenceException e) {
			  pause(1000);
			  mouseOver(locator, safeToSERE);
		  } finally {
			  loopCount = 0;
		  }
	  } else {
		  WebElement element = waitForAndGetElement(locator);
		  actions.moveToElement(element).perform();
	  }
  }

  public void mouseOverAndClick(Object locator) {
	  WebElement element;
	  if (ieFlag) {
		  element = getElement(locator);
	  } else {
		  element = waitForAndGetElement(locator);
	  }
	  actions.moveToElement(element).click(element).build().perform();
  }
  
  public  void waitForTextPresent(String text) {
	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT) {
			  Assert.fail("Timeout at waitForTextPresent: " + text);
		  }
		  if (isTextPresent(text)) {
			  break;
		  }
		  pause(500);
	  }
  }

  public void waitForTextNotPresent(String text) {
	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT) {
			  Assert.fail("Timeout at waitForTextNotPresent: " + text);
		  }
		  if (isTextNotPresent(text)) {
			  break;
		  }
		  pause(500);
	  }
  }

  public void waitForMessage(String message) {
	  //info("--Verify message: " + message);
	  pause(500);
	  waitForTextPresent(message);
  }

  public void type(Object locator, String value, boolean validate) {
	  try {
		  for (int second = 0;; second++) {
			  if (second >= DEFAULT_TIMEOUT) {
				  Assert.fail("Timeout at type: " + value + " into " + locator);
			  }
			  WebElement element = waitForAndGetElement(locator);
			  element.clear();
			  element.click();
			  element.sendKeys(value);
			  if (!validate || value.equals(getValue(locator))) {
				  break;
			  }
			  pause(100);
		  }
	  } catch (StaleElementReferenceException e) {
		  pause(1000);
		  type(locator, value, validate);
	  } finally {
		  loopCount = 0;
	  }
  }

  // Select option from combo box
  public void select(Object locator, String option) {
	  try {
		  for (int second = 0;; second++) {
			  if (second >= DEFAULT_TIMEOUT) {
				  Assert.fail("Timeout at select: " + option + " into " + locator);
			  }
			  Select select = new Select(waitForAndGetElement(locator));
			  select.selectByVisibleText(option);
			  if (option.equals(select.getFirstSelectedOption().getText())) {
				  break;
			  }
			  pause(100);
		  }
	  } catch (StaleElementReferenceException e) {
		  pause(1000);
		  select(locator, option);
	  } finally {
		  loopCount = 0;
	  }
  }

  public void waitForConfirmation(String confirmationText) {
	  String message = getTextFromAlert();

	  //log("confirmation: " + message);

	  if (message.isEmpty()) {
		  if (loopCount > 5) {
			  Assert.fail("Message is empty");
		  }
		  pause(500);
		  loopCount++;
		  waitForConfirmation(confirmationText);
		  return;
	  }

	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT) {
			  Assert.fail("Timeout at waitForConfirmation: " + confirmationText);
		  }
		  if (message.equals(confirmationText)) {
			  break;
		  }
		  pause(100);
	  }
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  pause(500);
  }
  
  //This function return absolute path from relative path
  public String getAbsoluteFilePath(String relativeFilePath){
	  String curDir = System.getProperty("user.dir");
	  String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
	  return absolutePath;
  }
}
