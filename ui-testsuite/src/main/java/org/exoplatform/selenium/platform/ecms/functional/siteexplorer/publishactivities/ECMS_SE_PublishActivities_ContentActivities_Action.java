package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.publishactivities;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * By havtt
 * Sep, 18th, 2013
 * 
 * Update by QuynhPT
 * date 22/10/2014
 *
 */

public class ECMS_SE_PublishActivities_ContentActivities_Action extends PlatformBase{
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;

	//Ecms
	ContextMenu cMenu;
	ContentTemplate cTemplate;

	//Social
	HomePageActivity sActivity;

	//QuynhPT changed from BeforeMethod to BeforeTest
	@BeforeTest
	public void beforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		actBar = new ActionBar(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver);
		sActivity = new HomePageActivity(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	//QuynhPT changed from AfterMethod to AfterTest
	@AfterTest
	public void afterTest() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 81229
	 * Dislike a Content activity from like icon
	 * Steps:
	 * 1. Connect to Intranet 
	 * 2. Select a content activity and click [like] icon
	 * ==> The Content activity is displayed in the activity stream 
	 * like icon + number of likes to + 1
	 * 3. Click on [like] icon again
	 * ==> The Content activity is disliked by the user, the number of like is updated to -1
	 */
	@Test
	public void test01_DislikeContentActivityFromLikeIcon(){
		String FILE_TITLE_01 = "ECMS_SE_Content_00";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewFile(FILE_TITLE_01, FILE_TITLE_01, FILE_TITLE_01);
		waitForAndGetElement(By.linkText(FILE_TITLE_01));

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		WebElement LIKE = waitForAndGetElement(sActivity.ELEMENT_LIKE_ICON.replace("${activityText}",FILE_TITLE_01));
		String nbLike_old = LIKE.getText();
		info(nbLike_old);

		info("Like a node activity");
		//like
		sActivity.likeOrUnlikeActivity(FILE_TITLE_01);
		Utils.pause(1000);

		//dislike
		info("Dislike a node activity");
		sActivity.likeOrUnlikeActivity(FILE_TITLE_01);
		Utils.pause(1000);

		info("Restore data");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(FILE_TITLE_01));

	}
}