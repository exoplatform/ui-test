package org.exoplatform.selenium.platform.wiki.functional.activities;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Permalink;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * @date 05-Nov-2013
 *
 */

public class Wiki_Activity_Delete extends Permalink{
	
	ManageAccount acc;
	HomePageActivity home;
	NavigationToolbar nav;
	Permalink per; 
	Dialog dialog; 
	ManageMember mMember; 

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		per = new Permalink();
		mMember = new ManageMember(driver, this.plfVersion);
		home = new HomePageActivity(driver,this.plfVersion);
		nav = new NavigationToolbar(driver,this.plfVersion);
		acc = new ManageAccount(driver,this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Author: HaTV
	 * Date: 19 - Jan -2015
	 * Case ID:118077.
	 * Test Case Name: Remove wiki's activity after delete wiki's page of portal.
	 * Pre-Condition: 
	 * -- Create a space and make it's wiki public ( Click Restricted link at the top then click Make public)
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 */
	@Test 
	public  void test01_RemoveWikiActivityAfterDeleteWikiPage() {
		info("Remove wiki's activity after delete wiki's page of portal");
		String random =getRandomNumber();
		String random1 =getRandomNumber();
		String random2 =getRandomNumber();
		String spaceName = "Space"+random;
		String ParentTitle = "Parent title"+random;
		String ParentContent = "Parent content"+random;
		String ParentNewContent = "Parent new content case"+random;
		String ChildTitle1 = "Child title"+random1;
		String ChildContent1 = "Child content"+random1;
		String ChildTitle2 = "Child title"+random2;
		String ChildContent2 = "Child content"+random2;
		
		//Add new space
		info("Add new space");
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, spaceName);
		
		//Create a wiki page for space
		info("Create a wiki page for space");
		home.goToSpecificSpace(spaceName);
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(ParentTitle, ParentContent, 0);
		makePublicPage();
		editPageWithCheckPublicActivity(ParentTitle, ParentNewContent);
		info("Check the display of wiki activity");
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",ParentTitle));
		
		//Create some sub wiki pages for space
		info("Create a sub wiki page 1 for space");
		home.goToSpecificSpace(spaceName);
		Utils.pause(2000);
		goToWikiFromSpace(spaceName);
		click(By.linkText(ParentTitle));
		Utils.pause(2000);
		addBlankWikiPage(ChildTitle1, ChildContent1, 0);
		info("Check the display of wiki activity");
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",ChildTitle1));
		
		info("Create a sub wiki page 2 for space");
		home.goToSpecificSpace(spaceName);
		Utils.pause(2000);
		goToWikiFromSpace(spaceName);
		click(By.linkText(ParentTitle));
		Utils.pause(2000);
		addBlankWikiPage(ChildTitle2, ChildContent2, 0);
		info("Check the display of wiki activity");
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",ChildTitle2));
		
		//Delete sub wiki page
		info ("Delete sub wiki page");
		click(By.linkText(spaceName));
		Utils.pause(2000);
		goToWikiFromSpace(spaceName);
		click(By.linkText(ParentTitle));
		Utils.pause(2000);
		click(By.linkText(ChildTitle1));
		Utils.pause(2000);
		deleteCurrentWikiPage();
		click(By.linkText(ChildTitle2));
		Utils.pause(2000);
		deleteCurrentWikiPage();
		Utils.pause(2000);
		
		//Check the activity on Homepage Intranet
		nav.goToHomePage();
		waitForElementNotPresent(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", ChildTitle1)));
		waitForElementNotPresent(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", ChildTitle2)));
		
		//Delete parent wiki page
		info ("Delete sub wiki page");
		click(By.linkText(spaceName));
		Utils.pause(2000);
		goToWikiFromSpace(spaceName);
		click(By.linkText(ParentTitle));
		Utils.pause(2000);
		deleteCurrentWikiPage();
		
		//Check the activity on Homepage Intranet
		nav.goToHomePage();
		waitForElementNotPresent(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", ParentTitle)));
	
		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,600000);
	}

	/**
	 * Case ID:118080.
	 * Test Case Name: Delete a Wiki activity from activity stream by owner.
	 * Pre-Condition: 
	 * -- Create a space and make it's wiki public ( Click Restricted link at the top then click Make public)
	 * -- Have a space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/06 10:33:45
	 */
	@Test 
	public  void test02_DeleteAWikiActivityFromActivityStreamByOwner() {
		info("Test 2: Delete a Wiki activity from activity stream by owner");
		String random =getRandomNumber();
		String spaceName = "Space"+random;
		String title = "Title"+random;
		String content = "Content"+random;
		String newcontent = "Content case"+random;
		
		//Pre-Condition 
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, spaceName);
		/*
		- Connect to Intranet
		- Add a Wiki activity for space 
		 *Input Data: 
		 *Expected Outcome: 
		- The Wiki activity for space displayed in the activity stream		*/
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title, content, 0);
		makePublicPage();
		editPageWithCheckPublicActivity(title, newcontent);
		/*
		- Back to activity stream 
		- Move the mouse over the Wiki activity for space 
		 *Input Data: 
		 *Expected Outcome: A (X) icon is displayed in the top right panel of the activity		*/
		info("Check the display of wiki activity");
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",title));

		/*
		- Click on the (X) icon
		- Click on [OK] button of confirmation message
		 *Input Data: 
		 *Expected Outcome: The Wiki activity for space removed from the activity stream		*/ 
		mouseOver(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", title)), true);
		waitForAndGetElement(By.xpath(home.ELEMENT_ACTIVITY_DELETE.replace("${activityText}", title)), DEFAULT_TIMEOUT,1,2);
		info("Click on X icon");
		home.deleteActivity(title);	
		waitForElementNotPresent(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", title)));

		//Delete data test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,600000);
	}
	

	/**
	 * Case ID:118084.
	 * Test Case Name: Remove wiki's page of space.
	 * Pre-Condition: Create a space and make it's wiki public ( Click Restricted link at the top then click Make public)
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/06 10:33:45
	 */
	@Test
	public  void test03_RemoveWikisPageOfSpace() {
		info("Test 3 Remove wiki's page of space");
		String random =getRandomNumber();
		String spaceName = "Space"+random;
		String title = "Title"+random;
		String content = "Content"+random;
		String newcontent = "Content case"+random;

		//Pre-Condition 
		mMember.goToAllSpaces();
		mMember.addNewSpace(spaceName, spaceName);

		/*
		- Connect to Intranet
		- Add a Wiki activity for space 
		 *Input Data: 
		 *Expected Outcome: 
		- The Wiki activity for space displayed in the activity stream		*/
		goToWikiFromSpace(spaceName);
		addBlankWikiPage(title, content, 0);
		makePublicPage(true);
		editPageWithCheckPublicActivity(title, newcontent);
		
		info("Check homepage");
		info("Check the display of wiki activity");
		nav.goToHomePage();
		waitForAndGetElement(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}",title));


		/*
		- Go to Space -> Wiki 
		- Select created page
		- Click More 
		- Delete page
		 *Input Data: 
		 *Expected Outcome: 
		- Wiki page is deleted successfully		*/
		click(By.linkText(title));
		Utils.pause(2000);
		deleteCurrentWikiPage();


		/*
		- Back to activity stream
		 *Input Data: 
		 *Expected Outcome: The Wiki activity for space is removed from the activity stream		*/ 
		nav.goToHomePage();
		waitForElementNotPresent(By.xpath(home.ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", title)));

		//Delete date test
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,600000);		
	}
}
