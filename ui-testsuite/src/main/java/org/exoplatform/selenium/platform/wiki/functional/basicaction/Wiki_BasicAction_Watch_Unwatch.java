package org.exoplatform.selenium.platform.wiki.functional.basicaction;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author lientm
 * @date 12/5/2014
 */
public class Wiki_BasicAction_Watch_Unwatch extends BasicAction {
	ManageAccount magAc;
	NavigationToolbar nav;
	PeopleProfile pepPro;
	String mail = "//b[text()='\"${title}\" page was modified']";
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		nav = new NavigationToolbar(driver, this.plfVersion);
		pepPro = new PeopleProfile(driver);
		magAc.signIn(DATA_USER1, DATA_PASS); 
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * CaseId: 78298
	 * Watch page then add child page -> there is not notification mail
	 */
	@Test
	public void test01_CheckNotificationEmail_WhenAddNewChildPage(){
		String title = "Parent page" + getRandomNumber();
		String content = "content of parent page";
		String childTitle = "Child page" + getRandomNumber();
		String childContent = "content of child page";
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "exomailtest01@gmail.com");
		pepPro.saveCancelUpdateInfo(true);
		
		info("Add new wiki page then watch its");
		goToWiki();
		addBlankWikiPage(title, content, 0);	
		watchWikiPage();

		info("Add new child page");
		addBlankWikiPage(childTitle, childContent, 0);
		String handlesBefore = driver.getWindowHandle();
		goToMail(EMAIL_ADDRESS1,EMAIL_PASS);
		Utils.pause(300000);
		checkEmailNotification(title,By.xpath(mail.replace("${title}", title)),false);
		
		//waitForElementNotPresent(By.xpath(mail.replace("${title}", title)));
		//info("Wait sucess");
		
		driver.switchTo().window(handlesBefore);
		info("Delete data");
		click(By.linkText(title));
		deleteCurrentWikiPage();
	}
	
	/**
	 * CaseId: 78299 + 78380
	 * Watch page then edit having select publish activity -> there is notification mail
	 * Watch page then edit no having select publish activity -> there is not notification mail
	 * Unwatch page then edit having select publish activity -> there is not notification mail
	 */
	@Test
	public void test02_CheckNotificationEmail_WhenWatchUnwatchPage(){
		String title = "Page" + getRandomNumber();
		String content = "content"+getRandomNumber();
		String newTitle = "Page update" + getRandomNumber();
		String newContent = "content update"+getRandomNumber();
		String newTitle2 = "Page update" + getRandomNumber();
		String newContent2 = "content update"+getRandomNumber();
		String newTitle3 = "Page update" + getRandomNumber();
		String newContent3 = "content update"+getRandomNumber();
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "exomailtest01@gmail.com");
		pepPro.saveCancelUpdateInfo(true);
		info("Open Wiki portlet");
		goToWiki();
		addBlankWikiPage(title, content, 0);	
		info("Watch the page");
		watchWikiPage();
		editPageWithCheckPublicActivity(newTitle, newContent);
		String handlesBefore = driver.getWindowHandle();
		goToMail(EMAIL_ADDRESS1,EMAIL_PASS);
		Utils.pause(300000);
		checkEmailNotification(title,By.xpath(mail.replace("${title}", newTitle)),true);
		//checkAndDeleteMail(By.xpath(mail.replace("${title}", newTitle)), newContent);
		String handlesAfter = driver.getWindowHandle();
		
		info("Edit page and not select publish activity -> there is not notification mail");
		driver.switchTo().window(handlesBefore);
		editWikiPage(newTitle2, newContent2, 0);
		driver.switchTo().window(handlesAfter);
		Utils.pause(300000);
		checkEmailNotification(title,By.xpath(mail.replace("${title}", newTitle2)),false);
		//waitForElementNotPresent(By.xpath(mail.replace("${title}", newTitle2)));
		
		info("Unwatch page then edit having select publish activity -> there is not notification mail");
		driver.switchTo().window(handlesBefore);
		unwatchWikiPage();
		editPageWithCheckPublicActivity(newTitle3, newContent3);
		driver.switchTo().window(handlesAfter);
		Utils.pause(300000);
		checkEmailNotification(title,By.xpath(mail.replace("${title}", newTitle3)),false);
		//waitForElementNotPresent(By.xpath(mail.replace("${title}", newTitle3)));
		
		driver.switchTo().window(handlesBefore);
		deleteCurrentWikiPage();
	}
}
