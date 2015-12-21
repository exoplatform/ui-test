package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_Activity_Add extends SOC_TestConfig{

	/**
	 *<li> Case ID:122798.</li>
	 *<li> Test Case Name: Add a share link.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_AddAShareLink() {
		info("Test 1: Add a share link");
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
		
		
		info("Click on Connections on the left panel");
		hp.goToConnections();
		
		info("Access people list, invite an user");
		connMag.connectToAUser(username2);
		
		info("Invited user accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept");
		connMag.verifyConnection(username1, true);
		
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
	
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to my profile page
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
		 *Expected Outcome: 
			- User activities page is displayed. It focus on activity list*/
		magAc.signIn(username1,password);
		navTool.goToMyActivities();

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Share a link
		 *Input Data: 
			- Select activity in the left pane
			- Click on Attach icon
			- Enter a valid URL
			- Hit Enter from keyboard or click on [Attach] button
		 *Expected Outcome: 
			Get information from link successfully. Some information are:
			- Title of site
			- Description of site
			- URL user has entered
			- If it gets image from link, there is a check box to allows display or not display image
			- Close link: allow user closes the link shareOther user has not seen the link share*/

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Add share links on activity list
		 *Input Data: 
			- Enter chars into text box
			- Click on[Share] button
		 *Expected Outcome: 
			A link is shared with some text on activity. Other user can view and click on shared link*/ 
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
		
		magAc.signIn(username2, password);
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}",username1));
		uBase.goToActivityTab();
		click(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", link));
	}

	/**
	 *<li> Case ID:122771.</li>
	 *<li> Test Case Name: Add new activity for users contact.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_AddNewActivityForUsersContact() {
		info("Test 2: Add new activity for users contact");
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
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to people page
		 *Input Data: 
			- Sign in system with user AAA
			- Select people page
		 *Expected Outcome: 
			- Show content of People page*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Invite user
		 *Input Data: 
			- Select user who has no relation with user to add relation
			- Click on [Invite] (BBB)
		 *Expected Outcome: 
			Invited user successfully*/

		info("Access people list, invite an user");
		connMag.connectToAUser(username2);
		
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Accept the friend
		 *Input Data: 
			- Sign in with user which invited at step 2 (BBB)
			- Select people page 
			- Click on button [accept]
		 *Expected Outcome: 
			- After the user clicks on [accept], the relation between two users has set. The user will be added into users relations user list. 
			- By side each the user, has a [remove] button to user can remove from this relation.=> Two user become friend*/
		info("Invited user accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept");
		connMag.verifyConnection(username1, true);
		
		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Update activities for user who invited (BBB)
		 *Input Data: 
			- Go to My profile and update Profile and click Save.
		 *Expected Outcome: 
			Status update successfully*/
		
		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Add new activities for user AAA
		 *Input Data: 
			On friends activities click on name user AAA
		 *Expected Outcome: 
			User can see the text box to add new activity for user AAA and add activity for this user successfully*/
		magAc.signIn(username1,password);
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.goToUserByUserName(username2);
		uBase.goToActivityTab();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
	}

	/**
	 *<li> Case ID:122785.</li>
	 *<li> Test Case Name: Add new your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_AddNewYourActivity() {
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
		
		
		info("Click on Connections on the left panel");
		hp.goToConnections();
		
		info("Access people list, invite an user");
		connMag.connectToAUser(username2);
		
		info("Invited user accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept");
		connMag.verifyConnection(username1, true);
		
		info("Test 3: Add new your activity");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to my profile page
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
		 *Expected Outcome: 
			- Show content of People page. It focus on activity list*/
		magAc.signIn(username1,password);
		navTool.goToMyActivities();
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add new activities
		 *Input Data: 
			- Select activity
			- Enter some text into text box
			- Click on Add button
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/ 
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		magAc.signIn(username2, password);
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}",username1));
		uBase.goToActivityTab();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", activity1));
	}

	/**
	 *<li> Case ID:122777.</li>
	 *<li> Test Case Name: Close share link.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CloseShareLink() {
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		info("Test 4: Close share link");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to my profile page
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
		 *Expected Outcome: 
			- User activities page is displayed. It focus on activity list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		navTool.goToMyActivities();
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Share a link
		 *Input Data: 
			- Select activity in the left pane
			- Click on Attach icon
			- Enter a not exited URL
			- Hit Enter from keyboard or click on [Attach] button on Share link form
			- Click on [Close] icon
		 *Expected Outcome: 
			Close share link form, and the link is not shared*/ 
		hpAct.addLink(link);
		hpAct.closeShareLink();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
	}
}