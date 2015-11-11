package org.exoplatform.selenium.platform.plf.functional.navigation;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 * 
 * @author havtt
 * @date 11/03/2014
 *
 */
public class PLF_UserNavigation extends PlatformBase {
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navTool;
	PeopleProfile peoPro;
	PeopleConnection peoConn;
	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login with " + DATA_USER1);
		peoConn = new PeopleConnection(driver);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navTool = new NavigationToolbar(driver, this.plfVersion);
		peoPro = new PeopleProfile(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Display a hover under item of user's toolbar
	 * CaseID: 76874
	 * 
	 * Display a hover under item of another user's toolbar
	 * CaseID: 76876
	 * 
	 * CaseID: 76882
	 * Display a style of user's applications in toolbar
	 * 
	 * CaseID: 76883
	 * Display a style of selected item in user's toolbar
	 * 
	 * CaseID: 76884
	 * Display a style of another user's applications in toolbar
	 * 
	 * CaseID: 76885
	 * Display a style of selected item in another user's toolbar
	 * 
	 * CaseID: 76893
	 * Verify the size of the user's informations toolbar
	 * 
	 * PENDING: Can't check CSS style: Refer https://jira.exoplatform.org/browse/FQA-1740
	 */
	@Test(groups="pending")
	public void test01_To_07_DisplayAHover(){
	}
	
	/**
	 * Display a hover under item of user's toolbar
	 * CaseID: 76877
	 * PENDING: Can't check truncated
	 */
	@Test(groups="pending")
	public void test08_DisplayLongUsername(){
		String username = "longlonglongusernameabcdefuuq";
		String password = username;
		String firstName = username;
		String lastName = username;
		String displayName = username;
		String email = username+"@gmail.com";
		
		info("Create new user");
		navTool.goToNewStaff();
		magAcc.addNewUserAccount(username, password, password, firstName, lastName, displayName, email, null, null, true);
		
		
		info("login with new user created");
		magAcc.signOut();
		magAcc.signIn(username, password);
		
		info("Check display of long username");
		navTool.goToMyProfile();
		//TODO: find the truncated element on UI
	}
	
	/**
	 * CaseID: 76887
	 * Open the personal page of user
	 * 
	 * CaseID: 76878
	 * Display user's applications in toolbar
	 * 
	 */
	@Test
	public void test09_10_DisplayAppOnUserProfile(){
		
		info("76887: Go to My profile page");
		navTool.goToMyProfile();
		Utils.pause(2000);
		
		info("76878: Check the display of apps on page");
		waitForAndGetElement(ELEMENT_MY_PROFILE_TAB);
		waitForAndGetElement(ELEMENT_MY_ACTIVITY_STREAM_TAB);
		waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(ELEMENT_MY_WIKI_TAB);
		waitForAndGetElement(ELEMENT_MY_DASHBOARD_TAB);
		
	}
	
	/**
	 * CaseID: 76881
	 * Display applications for another user's personal page in toolbar
	 * 
	 * CaseID: 76890
	 * Open the personal page of another user
	 */
	@Test
	public void test11_12_DisplayAppOnUserProfileofAnotherUser(){
		String user = "Mary Williams";
		
		info("76890: Open the personal page of another user");
		navTool.goToConnectionPage();
		peoConn.goToUserProfile(user);
		
		info("76881: Check the display of apps on page");
		waitForAndGetElement(ELEMENT_MY_PROFILE_TAB);
		waitForAndGetElement(ELEMENT_MY_ACTIVITY_STREAM_TAB);
		waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(ELEMENT_MY_WIKI_TAB);
	}
	
	/**
	 * CaseID: 76886
	 * Edit the user's avatar from the toolbar
	 * 
	 */
	@Test
	public void test13_CheckAvatar(){
		String file = "ECMS_DMS_SE_Upload_imgfile.jpg";
		
		info("Go to My profile page");
		navTool.goToMyProfile();
		Utils.pause(2000);
		
		info("76886: Edit the user's avatar from the toolbar");
		peoPro.goToEditProfile();
		peoPro.changeAvatar("TestData/"+file);
	}
}
