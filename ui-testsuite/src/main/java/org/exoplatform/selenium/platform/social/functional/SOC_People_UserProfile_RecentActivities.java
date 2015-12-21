package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class SOC_People_UserProfile_RecentActivities extends SOC_TestConfig{

	/**
	 *<li> Case ID:125280.</li>
	 *<li> Test Case Name: Check display of the activities without title.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User A has activities in his stream (wiki,content,poll without title)</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test01_CheckDisplayOfTheActivitiesWithoutTitle() throws AWTException {
		info("Test 1: Check display of the activities without title");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		String uploadFileName = atData.getAttachFileByArrayTypeRandom(9);
		String textDes1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String folderPath=siteExPath.getSiteExpPathByIndex(6);
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		info("share a document and comment");
		driver.navigate().refresh();
		hpAct.openUploadPopup("",folderPath);
		hpAct.uploadFileFromAS("TestData/",uploadFileName);
		hpAct.shareFileActivity("",folderPath, uploadFileName, textDes);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",uploadFileName));
		hpAct.addComment(textDes, comment);

		info("add activity");
		driver.navigate().refresh();
		hpAct.addActivity(textDes1, link);
		Utils.pause(3000);
		driver.navigate().refresh();
		hpAct.addActivity(textDes2, "");

		info("goto my profile");
		navTool.goToMyProfile();
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		click(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);

		/*Step Number: 1
		 *Step Name: Step 1 : go to profile
		 *Step Description: 
			- Login with User B
			- Go to User A profile
		 *Input Data: 

		 *Expected Outcome: 
			- The User B profile page is displayed
			- The section "Recent activities" is displayed at the bottom of the mid
			-column*/

		/*Step number: 2
		 *Step Name: Step 2: Check Activity
		 *Step Description: 
			- Check activity of Recent Activities section
		 *Input Data: 

		 *Expected Outcome: 
			The type filename (wiki page's name, poll's name, content's name ...etc) is displayed*/ 
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_FILE.replace("${title}",textDes).replace("${file}", uploadFileName));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_FILE_USER_ICON.replace("${user}", username1));
		waitForAndGetElement(hpAct.ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", comment));

		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK.replace("${title}", textDes1).replace("${link}", link));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK_USER_ICON.replace("${user}", username1));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", textDes2));
	}

	/**
	 *<li> Case ID:125284.</li>
	 *<li> Test Case Name: Check display of the classic activities.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User A has activities in his stream (wiki,forum,poll,document...with title, is mentioned,post activity)</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test02_CheckDisplayOfTheClassicActivities() throws AWTException {
		info("Test 2: Check display of the classic activities");
		String link = lnkData.getLinkByArrayTypeRandom(1);
		String textDes1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mention = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		info("add mention");
		hpAct.mentionUserActivity(username2,mention);

		info("add activity");
		driver.navigate().refresh();
		hpAct.addActivity(textDes1, link);
		Utils.pause(3000);
		driver.navigate().refresh();
		hpAct.addActivity(textDes2, "");
		/*Step Number: 1
		 *Step Name: Step 1 : go to profile
		 *Step Description: 
			- Login with User B
			- Go to User A profile
		 *Input Data: 

		 *Expected Outcome: 
			- The User A profile page is displayed
			- The section "Recent activities" is displayed at the bottom of the mid
			-column*/
		magAc.signIn(username2,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));

		/*Step number: 2
		 *Step Name: Step 2: Check Activity
		 *Step Description: 
			- Check classic activity of Recent Activities section
		 *Input Data: 

		 *Expected Outcome: 
			Only a summary of the activity is displayed : 
			- Avatar
			- Type
			- Activity message / title
			- Source link if provided*/
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK.replace("${title}", textDes1).replace("${link}", link));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK_USER_ICON.replace("${user}", username1));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}", textDes2));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", mention).replace("${user}",DATA_USER1));

		/*Step number: 4
		 *Step Name: Step 4: Check summary activity
		 *Step Description: 
			Click on on summary activity in recent activities
		 *Input Data: 

		 *Expected Outcome: 
			the page is redirected to the activity detail page with all information and actions as it is on the main activity stream.*/ 
		click(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);

		/*Step number: 3
		 *Step Name: Step 3: Check link's activity
		 *Step Description: 
			Click on a link in recent activities (wiki,URL...)
		 *Input Data: 

		 *Expected Outcome: 
			it is kept displaying in page. If it is an external link, a new tab is opened to display*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK.replace("${title}", textDes1).replace("${link}", link));

	}

	/**
	 *<li> Case ID:125279.</li>
	 *<li> Test Case Name: Check display of the section Recent Activities.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User A has 6 activities in his stream :posted activity</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckDisplayOfTheSectionRecentActivities() {
		String link = lnkData.getLinkByArrayTypeRandom(1);
		String textDes1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes5 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes6 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		info("add activity");
		driver.navigate().refresh();
		hpAct.addActivity(textDes1, link);
		Utils.pause(3000);
		driver.navigate().refresh();
		hpAct.addActivity(textDes2, "");
		driver.navigate().refresh();
		hpAct.addActivity(textDes3, "");
		driver.navigate().refresh();
		hpAct.addActivity(textDes4, "");
		driver.navigate().refresh();
		hpAct.addActivity(textDes5, "");
		driver.navigate().refresh();
		hpAct.addActivity(textDes6, "");

		info("Test 3: Check display of the section Recent Activities");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile
		 *Step Description: 
			- Login with User B
			- Go to User A profile
		 *Input Data: 

		 *Expected Outcome: 
			- The User B profile page is displayed
			- A section "Recent activities" is added at the bottom of mid
			-column the page*/
		magAc.signIn(username2,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));

		/*Step number: 2
		 *Step Name: Step 2: Check Recent Activities
		 *Step Description: 
			- Check activities of Recent Activities section
		 *Input Data: 

		 *Expected Outcome: 
			The section lists the last 5 activities of the user based on the filter My Activities*/ 
		waitForAndGetElement(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes6)));
		waitForAndGetElement(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes5)));
		waitForAndGetElement(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes4)));
		waitForAndGetElement(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes3)));
		waitForAndGetElement(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes2)));
		waitForElementNotPresent(By.xpath(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", textDes1)));
	}

	/**
	 *<li> Case ID:125310.</li>
	 *<li> Test Case Name: Check display of the user status.</li>
	 *<li> Pre-Condition: - User A and User B are created</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckDisplayOfTheUserStatus() {
		info("Test 4: Check display of the user status");
		String iconOnline = chatStatus.getIcon(1);
		String statusOnline = chatStatus.getStatus(1);
		String iconOffline = chatStatus.getIcon(0);
		String statusOffline = chatStatus.getStatus(0);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		/*Step Number: 1
		 *Step Name: Step 1 : Go to user profile
		 *Step Description: 
			- Login with User A
			- Go to User B profile
		 *Input Data: 

		 *Expected Outcome: 
			- The profile of User B is displayed
			- A user status, symbolized by a round shape, is displayed next to the user name. The status isn't clickable but a simple visual indication.*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));

		/*Step number: 2
		 *Step Name: Step 2 : Check user B's status
		 *Step Description: 
			- User B is offline
		 *Input Data: 

		 *Expected Outcome: 
			- A grey dot is displayed next to the user full name*/
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconOffline).replace("${status}", statusOffline));

		/*Step number: 3
		 *Step Name: Step 3 : Check user A's status
		 *Step Description: 
			- Return to user A's profile
		 *Input Data: 

		 *Expected Outcome: 
			- A green dot is displayed next to the user full name*/ 
		navTool.goToMyProfile();
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconOnline).replace("${status}", statusOnline));

	}

	/**
	 *<li> Case ID:125282.</li>
	 *<li> Test Case Name: Check Recent activities when the user doesn't have activity.</li>
	 *<li> Case ID:125283.</li>
	 *<li> Test Case Name: Check Recent activities when the user doesn't have activity (viewing his own profile).</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User B doesn't have any activity</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_06_CheckRecentActivitiesWhenTheUserDoesntHaveActivity() {
		info("Test 5: Check Recent activities when the user doesn't have activity");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		String msgRecent_me = activityMes.getActivityMessage(5);
		String msgRecent_other = activityMes.getActivityMessage(6);


		/*Step Number: 1
		 *Step Name: Step 1 : Go to user profile
		 *Step Description: 
			- Login with User A
			- Go to profile of User B
		 *Input Data: 

		 *Expected Outcome: 
			- The profile of User B is displayed
			- A message is displayed in the section Recent Activity : "This user does not have activities yet." and the button View All is hidden.*/ 
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_other));

		info("Test 6: Check Recent activities when the user doesn't have activity (viewing his own profile)");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to user profile
		 *Step Description: 
			- Login with User A
			- Go to User A profile : click user menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- The profile of User A is displayed
			- A message is displayed in the section : "You do not have activities yet." and the button View All is hidden.*/ 
		navTool.goToMyProfile();
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_me));
	}

	/**
	 *<li> Case ID:125281.</li>
	 *<li> Test Case Name: Check View All button in Recent Activities.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User A has 6 activities in his stream (wiki/content/forum activity and classic activity with title)</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_CheckViewAllButtonInRecentActivities() {
		info("Test 7: Check View All button in Recent Activities");
		String link = lnkData.getLinkByArrayTypeRandom(1);
		String textDes1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String textDes2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		info("add activity");
		driver.navigate().refresh();
		hpAct.addActivity(textDes1, link);
		Utils.pause(3000);
		driver.navigate().refresh();
		hpAct.addActivity(textDes2, "");

		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile
		 *Step Description: 
			- Login with User B
			- Go to User A profile
		 *Input Data: 

		 *Expected Outcome: 
			- The User B profile page is displayed
			- The section "Recent activities" is displayed at the bottom of the mid
			-column*/
		magAc.signIn(username2,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));

		/*Step number: 2
		 *Step Name: Step 2: Check Activity
		 *Step Description: 
			- Check section
		 *Input Data: 

		 *Expected Outcome: 
			A button [View All] at the bottom of the section*/
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);

		/*Step number: 3
		 *Step Name: Step 3: Click View All
		 *Step Description: 
			- Click [View All] button
		 *Input Data: 

		 *Expected Outcome: 
			The user is redirected to Users A's Activity Stream*/ 
		click(myProfile.ELEMENT_RECENT_ACTIVITY_VIEWALL_BTN);
		waitForAndGetElement(By.xpath(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", textDes1)));
		waitForAndGetElement(By.xpath(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", textDes2)));
	}
}