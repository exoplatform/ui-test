package org.exoplatform.selenium.platform.plf.functional.homepagegadgets;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.List;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleSearch;

/**
 * @author HaVTT
 * date: 10/02/2014
 *    
 */

public class PLF_HomepageGadgets_WhoIsOnline extends Activity {

	ManageAccount acc;
	HomePageGadget hg;
	NavigationToolbar navToolBar;
	
	PeopleConnection peopleC;
	PeopleSearch peopleS;
	String user = "John Smith";
	String user1 = "Jack Miller";
	String user2 = "James Davis";
	String user3 = "Mary Williams";
	String user4 = "FQA VN";
	String user5 = "Root Root";

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		acc = new ManageAccount(driver, this.plfVersion);
		hg = new HomePageGadget(driver, this.plfVersion);
		peopleC = new PeopleConnection(driver, this.plfVersion);
		peopleS = new PeopleSearch(driver);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**@date 24/4/2014
	 * @author lientm: insert
	 * CaseID 121057
	 * Automatic refresh of the Gadget "Who's online?"
	 **/
	@Test
	public void test01_AutomaticRefresh(){
		info("Test 01: Automatic refresh of the Gadget Who's online?");
		//loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		newDriver = new FirefoxDriver();
		newDriver.get(plfURL);
		driver.manage().window().maximize();
		ManageAccount  acc1 = new ManageAccount(newDriver);
		//HomePageGadget hg1 =new HomePageGadget(newDriver, this.plfVersion);
		acc1.signIn(DATA_USER2, DATA_PASS);
		Utils.pause(2000);
		
		//user1 check user2 display on Who is online gadget after 1 minus
		Utils.pause(2000);//buffer 1s
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER2,user3, null, false, null, 0);
		//user 2 logout
		acc1.signOut();
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
		//user1 check user2 not display on who is online gadget after 1 minus

		Utils.pause(2000);//buffer 1s

		waitForElementNotPresent(hg.ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",DATA_USER2));

	}	
	
	/** 
	 * Case ID: 121087.
	 * Test Case Name: Check the display of Gadget "Who's online?" when start server with "minimal"profile
	 * This case pending because can not start package with "minimal" profile activating
	 */
	@Test (groups = "pending")
	public  void test02_CheckDisplayOfWhoOnlineGadGetWhenStartingServerWithMinimalProfile() {
		info("Test 02: Check the display of Gadget Who's online? when start server with minimal profile");
		
	}
	
	/** 
	 * Case ID: 121158.
	 * Test Case Name: Display 18 last connected users in the gadget "Who's online?"
     * Case ID: 121164.
	 * Test Case Name: Display 3 connectes users row in the gadget "Who's online?"
	 * Case ID: 121167.
	 * Test Case Name: Display 6 users by row in the gadget "Who's online?"
	 */
	@Test
	public  void test03_04_05_Display18LastConnectedUsersInWhoOnlineGadGet() {
		info("Test 03: Display 18 last connected users in the gadget Who's online?");
		String password="gtngtn";
		
		String []username = new String[19];
		navToolBar.goToNewStaff();
		
		for(int i = 0; i<19; i++){
			username[i]=getRandomString();
			info("Create use "+username[i]);	
			acc.addNewUserAccount(username[i], password, password, username[i], username[i], "", username[i]+"@gmail.com", null, null, false);
			Utils.pause(2000);
			button.ok();
		}
		
		WebDriver[] nDriver = new WebDriver[19];
		for(int i = 0; i<19; i++){
			nDriver[i] = new FirefoxDriver();
			nDriver[i] .get(plfURL);
			driver.manage().window().maximize();
			ManageAccount  acc1 = new ManageAccount(nDriver[i]);
			acc1.signIn(username[i], password);
			Utils.pause(2000);
		}
		List<WebElement> result = driver.findElements(By.xpath(".//*[@id='onlineList']/li"));
		assert result.size()==18;
		for(int i = 0; i<19; i++){
			nDriver[i].close();
		}
	}
	
	/**
	 * CaseID 121247
	 * Check the popup information of online user who is a connection
	 * 
	 */
	@Test
	public void test06_checkDisplayOfOnlineConnectedUser(){
		String activity = "HelloWorld79685";

		info("USER1: Share a status on activity stream");
		addActivity(true, activity, false,"");

		info("USER1: Go to My Connection");
		navToolBar.goToConnectionPage();

		info("USER1: Connect with user acc 2");
		peopleC.connectPeople("Mary Williams");

		info("USER2: Switch to other window to login");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		hg=new HomePageGadget(newDriver, this.plfVersion);

		info("USER2: 79686 - Check popup information of online user when receiving a connection request");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, true, activity, true, true);

		info("USER2: Accept invitation from user acc 1");
		hg.acceptInvitationGadget("John Smith");

		info("USER2: 79685 - Check popup information of online user 1 when having a connection");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, true, activity, true, true);

		info("Restore data");
		info("--Cancel Connection--");
		peopleC = new PeopleConnection(newDriver, this.plfVersion);
		navToolBar = new NavigationToolbar(newDriver, this.plfVersion);
		navToolBar.goToConnectionPage();
		peopleS.searchPeople(true,"John Smith");
		peopleC.removeConnection("John Smith");
		info("--Close the 2nd browser window--");
		Utils.pause(500);
		newDriver.quit();
	}

	/**
	 * CaseID 121189
	 * Display a long title of an activity in pop up for a connected user
	 * Bug: https://jira.exoplatform.org/browse/PLF-4280
	 */
	@Test
	public void test07_checkUpdateOfOnlineUser(){
		String longActivity = "Merry Xmas and Happy New Year 2014 to every body all around the world. We wish you a merry xmas and a happy new year with lots of joy and happiness and luck !!!";

		info("USER1: Share a status on activity stream");
		addActivity(true, longActivity, false,"");

		info("USER1: Go to My Connection");
		navToolBar.goToConnectionPage();

		info("USER1: Connect with user acc 2");
		peopleC.connectPeople("Mary Williams");

		info("USER2: Switch to other window to login");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		hg=new HomePageGadget(newDriver, this.plfVersion);
		newDriver.navigate().refresh();

		info("USER2: Accept invitation from user acc 1");
		hg.acceptInvitationGadget("John Smith");
		newDriver.navigate().refresh();
		
		info("USER2: Check updated long status of user acc 1");
		hg.checkTruncatedStatusOnWhoIsOnlineGadget(DATA_USER1);

		info("USER2: Connect to other acc from Who'sOnline");
		hg.connectPeoplefromWhoisOnlineGadget(DATA_USER1);

		info("Restore data");
		info("--Cancel Connection--");
		peopleC = new PeopleConnection(newDriver, this.plfVersion);
		navToolBar = new NavigationToolbar(newDriver, this.plfVersion);
		navToolBar.goToConnectionPage();
		peopleS.searchPeople(true,"John Smith");
		peopleC.removeConnection("John Smith");
		info("--Close the 2nd browser window--");
		Utils.pause(500);
		newDriver.quit();
	}

	
}
