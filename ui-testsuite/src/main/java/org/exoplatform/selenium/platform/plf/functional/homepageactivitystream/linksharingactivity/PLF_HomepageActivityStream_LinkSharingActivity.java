package org.exoplatform.selenium.platform.plf.functional.homepageactivitystream.linksharingactivity;
import static org.exoplatform.selenium.TestLogger.info;
import junit.framework.Assert;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: LanLTK
 * @date: 01/09/2014
 */
public class PLF_HomepageActivityStream_LinkSharingActivity extends Activity{
	ManageAccount magAc;
	NavigationToolbar naviToolbar;
	HomePageActivity hpActivity;
	SpaceManagement spaceMag;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		naviToolbar = new NavigationToolbar(driver);
		hpActivity = new HomePageActivity(driver);
		spaceMag = new SpaceManagement(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);	
	}

	@AfterMethod

	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**CaseId: 77168, 77172, 77175, 77176, 77623, 77624, 77333 -> Functional-PLF/Homepage Activity Stream/Link Sharing Activity
	 */ 

	/*
	 * CaseId 77168: Add Link Sharing activity after share a link in intranet
	 * CaseId 77172: Verify the size of the content's image in the Link Sharing activity
	 * CaseId 77176: Dislike a Link sharing activity from like icon in intranet
	 * CaseId 77175: Delete a Link sharing activity from activity stream by its user in portal
	 */

	@Test	
	public void test01_02_03_04_LinkSharing_Intranet(){
		String link = "http://skype.com";
		int width = 75;

		// Add a link shared in intranet
		naviToolbar.goToHomePage();
		addActivity(false,"",true,link);

		// Like a link shared
		hpActivity.likeOrUnlikeActivity(link);

		// Dislike a link shared
		hpActivity.likeOrUnlikeActivity(link);

		// Verify the size of the content's image
		String ELEMENT_LINKSHARE_THUMBNAIL = "//*[contains(@alt,'Skype')]";
		int imageWidth = waitForAndGetElement(ELEMENT_LINKSHARE_THUMBNAIL).getSize().getWidth();
		info("Width = " + String.valueOf(imageWidth));
		Assert.assertEquals(width,imageWidth);
		// Delete a link shared
		hpActivity.deleteActivity(link);
	}


	/*
	 * CaseId 77623: Add Link Sharing activity after share a link in space
	 * CaseId 77624: Dislike a Link sharing activity from like icon in space
	 * CaseId 77333: Delete a Link sharing activity from activity stream by its user in space
	 */

	@Test	
	public void test05_06_07_LinkSharing_Space(){
		String link = "http://www.google.com";
		String spaceName = "Space77623";

		// Create data for Pre-Conditions
		spaceMag.goToMySpacePage();
		spaceMag.addNewSpace(spaceName, "");	

		// Start test case
		naviToolbar.goToHomePage();
		click(By.linkText(spaceName));
		info("Go to Space1");
		waitForAndGetElement(ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL);

		// Add a link shared in intranet	  
		addActivity(false,"",true,link);

		// Like a link shared
		hpActivity.likeOrUnlikeActivity(link);

		// Dislike a link shared
		hpActivity.likeOrUnlikeActivity(link);

		// Delete a link shared
		hpActivity.deleteActivity(link);

		//Delete activity before exit test cases
		spaceMag.goToMySpacePage();
		spaceMag.deleteSpace(spaceName,300000);
	}

}
