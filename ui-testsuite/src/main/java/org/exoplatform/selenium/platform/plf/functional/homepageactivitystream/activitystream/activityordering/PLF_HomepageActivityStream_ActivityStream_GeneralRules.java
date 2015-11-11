package  org.exoplatform.selenium.platform.plf.functional.homepageactivitystream.activitystream.activityordering;



import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.testng.annotations.*;
/**
 * @author chinhdtt
 *
 */
public class PLF_HomepageActivityStream_ActivityStream_GeneralRules extends Activity{
	ManageAccount acc; 
	HomePageActivity home; 
	NavigationToolbar nav; 
	ActionBar actBar;
	ManageMember mMember; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		acc = new ManageAccount(driver, this.plfVersion);
		home = new HomePageActivity(driver, this.plfVersion); 
		acc.signIn(DATA_USER1, DATA_PASS);
		nav = new NavigationToolbar(driver, this.plfVersion);
		mMember = new ManageMember(driver,this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}

	/**
	 * Case ID:77648.
	 * Test Case Name: Number of activities in Activity stream per page.
	 * Pre-Condition: There are many activities (more than 20) are shared on the stream
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2013/12/27 10:27:45
	 */
	@Test
	public  void test01_NumberOfActivitiesInActivityStreamPerPage() {
		info("Test 2: Number of activities in Activity stream per page");
		String firstActivity = "Activity 776148";
		String text = "Case 776148: Activity:";
		/*
		- Connect to Intranet
		- Check number of activities per page
		 *Input Data: 
		 *Expected Outcome: The number of activity per page is by default20		*/ 
		addActivity(true, firstActivity, false,"");
		nav.goToHomePage();		
		for(int i=1; i<=20; i++){
			addActivity(true, text+""+ String.valueOf(i), false, "");
		}
		waitForElementNotPresent(home.ELEMENT_ACTIVITY.replace("${activityText}", firstActivity));

		//delete data
		for(int i=20; i>=1; i--){
			home.deleteActivity(text+""+ String.valueOf(i));
			driver.navigate().refresh();
		}
		home.deleteActivity(firstActivity);		
	}

	/**
	 * Case ID:77649.
	 * Test Case Name: See more previous activities.
	 * Pre-Condition: More than 20 activities are shared in the activity stream
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2013/12/27 10:27:45
	 */
	@Test
	public  void test02_SeeMorePreviousActivities() {
		info("Test 3: See more previous activities");
		String text = "Case 776149: Activity:";

		nav.goToHomePage();		
		for(int i=1; i<=22; i++){
			addActivity(true, text+""+ String.valueOf(i), false, "");
		}
		/*
		- Connect to Intranet
		- Scrolls to the bottom of the activity stream
		 *Input Data: 
		 *Expected Outcome: 
		- Previous activitie's pages are loaded
		- A small activity indicator is displayed in the bottom of the page		*/ 
		for(int i=22; i>=1; i--){
			waitForActivityPresent(text+""+ String.valueOf(i), true);
		}
		//delete data
		for(int i=22; i>=1; i--){
			home.deleteActivity(text+""+ String.valueOf(i));
			driver.navigate().refresh();
		}
	}
}
