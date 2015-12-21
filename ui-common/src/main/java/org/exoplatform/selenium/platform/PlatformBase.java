package org.exoplatform.selenium.platform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static org.exoplatform.selenium.TestLogger.info;

public class PlatformBase extends TestBase {

	public static String DATA_USER1 = "john";
	public static String DATA_USER2 = "mary";
	public static String DATA_USER3 = "james";
	public static String DATA_USER4 = "demo";
	public static String USER_ROOT = "root";
	public static String PASS_ROOT = "gtn";
	public static String DATA_PASS = "gtn";
	public static String DATA_NAME_USER1 = "John Smith";
	public static String DATA_NAME_USER2 = "Mary Williams";
	public static String DATA_NAME_USER3 = "James Davis";
	public static String DATA_NAME_USER4 = "Jack Miller";
	public static String DATA_NAME_ROOT = "Root Root";
	public static String ADMINISTRATION_GROUP = "Administration";
	public static String PLATFORM_GROUP = "Platform";
	public static String password="123456";
	
	//Gmail
	public final String GMAIL_URL = "https://mail.google.com";
	public final String EMAIL_ADDRESS1 = "exomailtest01@gmail.com";
	public final String EMAIL_ADDRESS2 = "fqaexovn@gmail.com";
	public final String EMAIL_PASS = "exoadmin1";
	public final String ELEMENT_MAIL_SUBJECT = ".//span[contains(.,'${subject}')]";
	public final By ELEMENT_DELETE_MAIL = By.xpath("//*[@id=':ro']/div[2]//*[@class='ar9 T-I-J3 J-J5-Ji']");
	public final By ELEMENT_DELETE_MAIL_2 = By.xpath(".//*[@class='iH']//*[@data-tooltip='Delete']//*[@class='ar9 T-I-J3 J-J5-Ji']");
	public final By ELEMENT_GMAIL_INBOX = By.xpath("//a[contains(@title, 'Inbox')]");
	public final By ELEMENT_MAIL_CONTENT = By.xpath("//*[contains(@class, 'adP adO')]/div");
	public final By ELEMENT_GMAIL_USERNAME = By.id("Email");
	public final By ELEMENT_GMAIL_NEXT_BTN=By.id("next");
	public final By ELEMENT_GMAIL_PASS = By.id("Passwd");
	public final By ELEMENT_GMAIL_SIGN_IN = By.id("signIn");
	public final String ELEMENT_GMAIL_TITLE = "//td/div[@class='xS']//div[@class='xT']//span/b[contains(text(),'{$title}')]";
	public final By ELEMENT_GMAIL_COMPOSE = By.xpath("//div[contains(text(),'COMPOSE')]");
	public final By ELEMENT_GMAIL_SHOW_DETAIL = By.xpath("//img[@aria-label='Show details']");
	public final String ELEMENT_GMAIL_TO_FIELD = "//td/span[text()='to:']/../..//span[text()='${to}']";
	public final By ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC = By.cssSelector(".gb_d.gbii");
	public final By ELEMENT_GMAIL_ADD_ACCOUNT = By.linkText("Add account");
	public final By ELEMENT_FIRST_MAIL = By.xpath("//tr[1]//span[contains(text(),'Hi')]");
	public final String ELEMENT_GMAIL_CONTENT = ".//span[contains(.,'\"${title}\" page was modified') or contains(.,'${title}')]";
	
	public final By ELEMENT_GMAIL_SIGN_IN_LINK = By.xpath("//a[@id='gmail-sign-in' and contains(text(),'Sign in')]");

	public ManageAlert alert = new ManageAlert(driver);
	
	//page navigation
	public By ELEMENT_NEXT_PAGE=By.xpath("//*[@class='uiIconNextArrow']");
	public By ELEMENT_PREVIOUS_PAGE=By.xpath("//*[@class='uiIconPrevArrow']");
	public By ELEMENT_TOTAL_PAGE=By.xpath("//*[@class='pagesTotalNumber']");
	public By ELEMENT_CURRENT_PAGE=By.xpath("//*[@class='active']/*[contains(@href,'objectId') or contains(@href,'javascript')]");
	public String ELEMENT_ANY_PAGE="//*[contains(@href,'ShowPage') and text()='$page']";
	public String ELEMENT_PAGINATOR_PAGE_LINK = "//*[@class='pagination uiPageIterator clearfix']//a[contains(Text(),'${number}')]";
	
	//frame
	public final By ELEMENT_FILEFORM_BLANK_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public final By ELEMENT_FILEFORM_BLANK_NAME = By.id("name");
	//Email notification
	public final By ELEMENT_GMAIL_PREVIOUS_EMAIL = By.xpath(".//*[@class='gE hI']");
	public final String ELEMENT_GMAIL_CONTENT_LINK_WIKI = ".//a[contains(@href,'${page}')]";
	
	//User list popup
	public final String ELEMENT_USER_CHECKBOX = "//*[text()='$user']/../..//*[@type='checkbox']";
	public final By ELEMENT_SEARCH_USER_INPUT = By.xpath("//*[@name='Quick Search']");
	public final By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@data-original-title='Quick Search']");
	public final By ELEMENT_SELECT_SEARCH = By.name("filter");
	public final By ELEMENT_ADD_USERS_BUTTON = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");
	
	//Membership popup
	public final String ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT=".//*[@id='UIGroupMembershipSelector']//*[contains(text(),'$groupName')]";
    public final By ELEMENT_MEMBERSHIP_POPUP=By.xpath(".//*[@id='UIGroupMembershipSelector']");
    
    //Group popup
    public final String ELEMENT_GROUP_NAME=".//*[@id='UIGroupSelector']//*[contains(text(),'$group')]";
    public final By ELEMENT_SELECT_THIS_GROUP=By.xpath(".//*[@id='UIGroupSelector']//*[contains(text(),'Select this Group')]");
    public final By ELEMENT_SELECT_GROUP_POPUP=By.xpath(".//*[@id='UIGroupSelector']");
		
    //Social NETWORK account
    public final String SOCIAL_NETWORKS_ACCOUNT_FACEBOOK = "exomailtest01@gmail.com";
    public final String SOCIAL_NETWORKS_ACCOUNT_TWITTER = "exofqatwitter@gmail.com";
    public final String SOCIAL_NETWORKS_ACCOUNT_LINKEDIN = "exofqalinkedin@gmail.com";
    public final String SOCIAL_NETWORKS_ACCOUNT_GOOGLE = "exofqagplus@gmail.com";
    public final String SOCIAL_NETWORKS_ACCOUNT_SUSPENDED = "exosuspend@gmail.com";
    
    public final String SOCIAL_NETWORKS_PASSWORD = "exoadmin1";
    
    
	/**
	 * Available option
	 */
	public enum selectInvitationOption{
		ALWAYS, NEVER, ASK
	}


	/**
	 * Arrow option
	 */
	public enum selectArrowOption{
		NEXT, PREVIOUS, NOW
	}

	/****************************Method*************************************/
	/**
	 * get default user pass from data driven
	 * @param userDataFile
	 * @param userSheet
	 * @param opParams
	 * @throws Exception
	 */
	public static void getDefaultUserPass(String userDataFile, String userSheet, Object... opParams) throws Exception{
		info("Get deault user pass from data driven");
		UserDatabase userData = new UserDatabase();
		userData.setUserData(userDataFile,userSheet,opParams);
		DATA_USER1 = userData.userName.get(0);
		DATA_PASS = userData.password.get(0);
		DATA_NAME_USER1 =userData.fullName.get(0);
		DATA_USER2 = userData.userName.get(1);
		DATA_NAME_USER2 =userData.fullName.get(1);
		USER_ROOT = userData.userName.get(4);
		PASS_ROOT = userData.password.get(4);
		DATA_USER3 = userData.userName.get(2);
		DATA_NAME_USER3 =userData.fullName.get(2);
		DATA_USER4 = userData.userName.get(3);
		DATA_NAME_USER4 =userData.fullName.get(3);
	}

	/**
	 * Type a text to a Frame using for CKEDITOR
	 * By QuynhPT
	 * @param frameLocator
	 * @param content
	 */
	public void inputFrame(By frameLocator,String content){
		info("Finding the frameLocator:"+frameLocator);
		WebElement e = waitForAndGetElement(frameLocator,DEFAULT_TIMEOUT,1,2);
		info("Switch to the frame:"+frameLocator);
		driver.switchTo().frame(e);
		WebElement inputsummary = driver.switchTo().activeElement();
		info("focus on the text area");
		inputsummary.click();
		info("Input the content:"+content);
		inputsummary.clear();
		inputsummary.sendKeys(content);
		info("Back to parent window");
		switchToParentWindow();
	}
	/**
	 * Switch into the frame
	 * @param frameLocator
	 */
	public void switchFrame(By frameLocator,Object... param){
		info("Finding the frameLocator:"+frameLocator);
		WebElement e = waitForAndGetElement(frameLocator,DEFAULT_TIMEOUT,1,2);
		info("Switch to the frame:"+frameLocator);
		driver.switchTo().frame(e);
		WebElement inputsummary = driver.switchTo().activeElement();
		info("focus on the text area");
		inputsummary.click();
		if(param.length>0)
			inputsummary.sendKeys("\n");
	}
	
	/**
	 * Select option from combo box
	 * @param locator
	 * @param option
	 */
	public void selectOption(Object locator, String option) {
		try {
			for (int second = 0;; second++) {
				if (second >= DEFAULT_TIMEOUT / WAIT_INTERVAL) {
					Assert.fail("Timeout at select: " + option + " into "
							+ locator);
				}
			
				Select select = new Select(waitForAndGetElement(locator));
				select.selectByValue(option);
				if (option.equals(select.getFirstSelectedOption().getAttribute(
						"value"))) {
					break;
				}
				Utils.pause(WAIT_INTERVAL);
			}
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT / WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			select(locator, option);
		} finally {
			loopCount = 0;
		}
	}

	/**
	 * Add by @author vuna2
	 * <li> Switch to a new browser/ Popup window</li> 
	 */
	public void switchToNewWindow(){
		Set<String> windowids = driver.getWindowHandles(); 
		Iterator<String> iter= windowids.iterator();
		while(iter.hasNext()) {
			String windowHandle = iter.next(); 
			driver.switchTo().window(windowHandle);
			info("Switch to new windown successfully");
		} 
	}
	
	/**
	 * Switch to new browser window
	 * @param user
	 * @param pass
	 */
	public void switchToNewBrowserWindow(String user, String pass){
		ManageLogInOut magAcc = new ManageLogInOut(driver);
		magAcc = new ManageLogInOut(driver);

		this.openNewBrowser();
		if (user != null){
			if (isElementNotPresent(ELEMENT_INPUT_USERNAME)){
				magAcc.signOut();
			}else{
				info("-- User.logIn: " + user);
			}
			magAcc.signIn(user, pass);
			Utils.pause(1000);
		}
	}
	
	/**
	 * Add by @author vuna2
	 * Open a new browser by Javascript
	 */
	public void openNewBrowser(){
		//Open new browser by Javascript
		((JavascriptExecutor) driver).executeScript("window.open()");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.navigate().to(baseUrl);
	}
	
	/**
	 * Add by @author vuna2
	 * Open a new browser by Javascript
	 */
	public void openNewBrowser(String url){
		//Open new browser by Javascript
		((JavascriptExecutor) driver).executeScript("window.open()");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.navigate().to(url);
	}
	
	/**
	 * function: switch between windows using title windows
	 * @param windowTitle
	 */
	 public void switchBetweenWindowsUsingTitle(String windowTitle) {
         Set<String> windows = driver.getWindowHandles();
         for (String window : windows) {
             driver.switchTo().window(window);
             if (driver.getTitle().contains(windowTitle)) {
                 return;
             }
         }
     }
	/**
	 * switch between browsers using window handle
	 * @param windowHandle
	 */
	 public void switchBetweenBrowsers(String windowHandle){
		 Set<String> windows = driver.getWindowHandles();
		 for (String window : windows) {
             driver.switchTo().window(window);
             if (driver.getWindowHandle().contains(windowHandle)) {
                 return;
             }
         }
	 }
	/**
	 * Go to gmail and login by new browser
	 * @param email
	 * @param pass
	 */
	public void goToMail(String email, String pass){	
		//((JavascriptExecutor) driver).executeScript("window.open()");
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"n");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		info("Go to gmail");
		driver.navigate().to(GMAIL_URL);
		driver.manage().window().maximize();

		//login to mail
		if(waitForAndGetElement(ELEMENT_GMAIL_USERNAME, 5000,0) == null){
			if (waitForAndGetElement(ELEMENT_GMAIL_SIGN_IN_LINK,3000,0) != null)
				click(ELEMENT_GMAIL_SIGN_IN_LINK); 
			else{
				click(ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC);
				click(ELEMENT_GMAIL_ADD_ACCOUNT);
			}
		}
		type(ELEMENT_GMAIL_USERNAME, email, true);
		click(ELEMENT_GMAIL_NEXT_BTN);
		Utils.pause(1000);
		type(ELEMENT_GMAIL_PASS, pass, true);
		click(ELEMENT_GMAIL_SIGN_IN);
		//clearCache();
		Utils.pause(2000);
		click(ELEMENT_GMAIL_INBOX);
		Utils.pause(2000);
	}
	/**
	 * Open gmail when user is logging
	 */
	public void openMail(){
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"n");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		info("Go to gmail");
		driver.navigate().to(GMAIL_URL);
		driver.manage().window().maximize();
		Utils.pause(2000);
		click(ELEMENT_GMAIL_INBOX);
		Utils.pause(2000);
	}
	
	/**
	 * Open mail by opening new tab
	 * @param email
	 * @param pass
	 */
	public void goToMailByTab(String email, String pass){
	    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(0));
	    info("Go to gmail");
		driver.navigate().to(GMAIL_URL);
		
		//login to mail
		if(waitForAndGetElement(ELEMENT_GMAIL_USERNAME, 5000,0) == null){
			if (waitForAndGetElement(ELEMENT_GMAIL_SIGN_IN_LINK,3000,0) != null)
				click(ELEMENT_GMAIL_SIGN_IN_LINK); 
			else{
				click(ELEMENT_GMAIL_SIGNIN_DIFFERENT_ACC);
				click(ELEMENT_GMAIL_ADD_ACCOUNT);
			}
		}
		type(ELEMENT_GMAIL_USERNAME, email, true);
		click(ELEMENT_GMAIL_NEXT_BTN);
		Utils.pause(1000);
		type(ELEMENT_GMAIL_PASS, pass, true);
		click(ELEMENT_GMAIL_SIGN_IN);
		clearCache();
		Utils.pause(2000);
		click(ELEMENT_GMAIL_INBOX);
		Utils.pause(2000);

	}
	
	/**
	 * function: check content of mail then delete mail
	 * @param mail element title of mail
	 * @param content mail content
	 */
	public void checkAndDeleteMail(By mail, String content){
		info("Check and delete mail");
		waitForAndGetElement(mail,300000);
		click(mail);	
		if(waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${content}",content),20000,0) == null )
			click(ELEMENT_FIRST_MAIL);
		assert waitForAndGetElement(ELEMENT_MAIL_CONTENT).getText().contains(content);
		info("Found notify mail");

		info("delete mail");
		if (waitForAndGetElement(ELEMENT_DELETE_MAIL_2, 5000, 0) == null){
			click(ELEMENT_DELETE_MAIL);
			info("Delete 1");
		}else {
			click(ELEMENT_DELETE_MAIL_2);
			info("Delete 2");
		}
		waitForElementNotPresent(mail);
		Utils.pause(1000);
	}
	
	/**
	 * Get list all Browsers
	 */
	public void getAllChildWindows() {
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
			info("driver.title:" + driver.getTitle());
			driver.manage().window().maximize();
		}
	}
	
	/**
	 * Close all child drivers
	 * @param parentTitle
	 *                    is the tilte of parent browsers
	 */
	public void closeChildBrowsers(String parentWindow){
		info("parentWindow:"+parentWindow);
		Set<String> handlers=driver.getWindowHandles(); 
		//Handler will have all the three window handles
		for(String windowHandle  : handlers){
		     driver.switchTo().window(windowHandle);
		     info("windowHandle"+windowHandle);
		     //If it is not the parent window it will close the child window 
		     if(!windowHandle.contains(parentWindow)){
		    	  info("close driver.title:"+driver.getTitle());
		    	  Utils.pause(2000);
				  driver.close();
		     }
		   
	    }
		switchToParentWindow();
	}
	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 * @param  opParams if true check it's present, false check if it's not present
	 */
	public void checkEmailNotification(String title,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
	
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		}
		if (opParams.length > 0) {
			if (checkOrNo == true)
				waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
            else 
            	waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
		}
		
		//close windows mail
		if (opParams.length > 1)
			driver.close();
	}
	
	
	/**
	 * User pageinator
	 * @param locator
	 * @param exceptionMessage
	 */
	public void usePaginator(Object locator, String exceptionMessage) {
		String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

		if (waitForAndGetElement(page1, 5000, 0) != null)
			click(page1);
		Utils.pause(500);
		int totalPages = 0;
		if (waitForAndGetElement(ELEMENT_TOTAL_PAGE, 3000, 0) != null){
			totalPages = isElementPresent(ELEMENT_TOTAL_PAGE) ? Integer.valueOf(getText(ELEMENT_TOTAL_PAGE)) : 1;
		}
		info("-- The total pages is: " + totalPages);
		int i = 1;
		while (isElementNotPresent(locator)) {
			if (i == totalPages) {
				info(exceptionMessage);
				break;
			}
			if (waitForAndGetElement(ELEMENT_NEXT_PAGE, 3000, 0) != null){
				click(ELEMENT_NEXT_PAGE);
			}
			Utils.pause(500);
		}
	}
	
	/**
	 * Define filter user option
	 */
	public enum filterOption{
		userName,firstName,lastName,email;
	}
	/**
	 * Search users in user list popup
	 * @param user
	 * @param op
	 */
	public void searchUser(String user,filterOption op){
		if(!user.isEmpty()){
			info("Type user into the search field");
			type(ELEMENT_SEARCH_USER_INPUT,user,true);
			switch(op){
			case userName:
				selectOption(ELEMENT_SELECT_SEARCH,filterOption.userName.name());
				break;
			case firstName:
				selectOption(ELEMENT_SELECT_SEARCH,filterOption.firstName.name());
				break;
			case lastName:
				selectOption(ELEMENT_SELECT_SEARCH,filterOption.lastName.name());
				break;
			case email:
				selectOption(ELEMENT_SELECT_SEARCH,filterOption.email.name());
				break;
			}
			click(ELEMENT_QUICK_SEARCH_BUTTON);
			Utils.pause(2000);
			info("the user is shown in searched result list");
		}
		
	}
	/**
	 * Select a user in User list
	 * @param user
	 * @param op
	 */
	public void selectUser(String user,filterOption op){
		searchUser(user,op);
		info("Select the user");
		check(ELEMENT_USER_CHECKBOX.replace("$user",user), 2);
		info("Click on Add button");
		click(ELEMENT_ADD_USERS_BUTTON);
		waitForElementNotPresent(ELEMENT_ADD_USERS_BUTTON);
		info("the user is added");
	}
	
	
	/**
	 * Select a membership in the list
	 * @param group
	 * @param membership
	 */
    public void selectMembership(String group,String membership){
		String[] groups = group.split("/");
		for(String groupName:groups){
			info("Select the group:"+groupName);
			click(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT
					.replace("$groupName",groupName));
		}
		if(!membership.isEmpty()){
			info("Select the membership:"+membership);
			click(ELEMENT_GROUP_MEMBERSHIP_NAME_SELECT
					.replace("$groupName",membership));
		}
		waitForElementNotPresent(ELEMENT_MEMBERSHIP_POPUP);
	}
    
   
    /**
     * Select a group
     * @param group
     */
    public void selectGroup(String group){
    	String[] groups = group.split("/");
		for(String groupName:groups){
			info("Select the group:"+groupName);
			click(ELEMENT_GROUP_NAME
					.replace("$group",groupName));
		}
		info("Select the group");
		click(ELEMENT_SELECT_THIS_GROUP);
		waitForElementNotPresent(ELEMENT_SELECT_GROUP_POPUP);
    }
	
}
