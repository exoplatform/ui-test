package org.exoplatform.selenium.cloud.createdomain;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Cloud_Create_Domain extends PlatformBase{
	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup; 

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTestWithOutTermAndCondition();
		baseUrl = "http://fqa.exocloud.vn/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		info("== Creating users to eXo Cloud ==");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Creating users: successful ==");
		driver.quit();
	}
	
	@Test
	public void Create_Domain(){
		String email = (System.getProperty("email") != null) ? System.getProperty("email"):"cloudtest021@cloudtest02.vn";
		String pass = (System.getProperty("pass") != null) ? System.getProperty("pass"): "12345678";
		String linkText = baseUrl+"registration";
		String linkUrl = "//a[contains(text(),'${linkText}')]";
		By ELEMENT_MAIL_CONTENT = By.xpath("//iframe[@id='messagecontframe']");
		type(By.xpath("//*[@id='email']"), email, true);
		click(By.xpath("//*[@id='t_submit']"));
		waitForAndGetElement(By.xpath("//*[@class='Greetings']"));
		Utils.pause(1000);
		driver.get("https://192.168.3.46/mail");
		type(By.id("rcmloginuser"), email, true);
		type(By.id("rcmloginpwd"), pass, true);
		click(By.xpath("//*[@class='button mainaction']"));
		click(By.xpath("//*[@id='messagelist']/tbody/tr[1]"));
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_MAIL_CONTENT));
		click(By.xpath(linkUrl.replace("${linkText}", linkText)));
		switchToParentWindow();
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}		
		// Perform the actions on new window
		driver.manage().window().maximize();
		type(By.id("first_name"), "FQA", true);
		type(By.id("last_name"), "FQA", true);
		type(By.id("phone_work"), "123", true);
		type(By.id("company"), "exo", true);
		type(By.id("password"), "gtngtn", true);
		type(By.id("password2"), "gtngtn", true);
		click(By.xpath("//*[@id='t_submit']"));
		waitForElementNotPresent(By.xpath("//*[@id='t_submit']"));
		waitForAndGetElement(By.xpath("//*[@class='TitleForm']"));
		//Close the new window, if that window no more required
		driver.close();
		//Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		//continue with original browser (first window)
		String newUrl = "http://"+email.substring(email.indexOf('@')+1, email.indexOf('.'))+"."+baseUrl.substring(baseUrl.indexOf("//")+2)+"portal";
		info(newUrl);
		baseUrl=newUrl;
		driver.get(newUrl);
		while(isElementPresent(By.xpath("//*[@class='TitleForm']"))){
			Utils.pause(10000);
			driver.navigate().refresh();
		}
	}
}
