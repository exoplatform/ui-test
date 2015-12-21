package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform.displayModeType;
import org.testng.annotations.*;


public class SOC_People_Activity_View extends SOC_TestConfig{
	ArrayList<String> arrayUser;
	
	/**
	 * Create many users
	 * @param number
	 *               is the number of users that want to create
	 */
	public void createNewUser(int number){
		arrayUser  = new ArrayList<String>();
		navTool.goToAddUser();
		for(int i=0;i<number;i++){
			info("Add new a user");
			String user=getRandomString();
			String email=user+"@gmail.com";
			addUserPage.addUser(user,password, email,user,user);
			info("Add users to user's array");
			arrayUser.add(user);
			info("User"+i+": "+user);
		}
	}

	/**
	 *<li> Case ID:122639.</li>
	 *<li> Test Case Name: View activity of user who is not friend.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_ViewActivityOfUserWhoIsNotFriend() {
		info("Test 1: View activity of user who is not friend");
		info("Create 1 new users");
		createNewUser(1);

		/*Step Number: 1
		 *Step Name: Step 1: View user activities
		 *Step Description: 
			- Sign in system
			- Access Activities page on User Toolbar portlet in the upper right corner of the screen
			- Select Activity in the left pane
		 *Input Data: 

		 *Expected Outcome: 
			Show content of activities gadget:
			- Text area: allow user compose and new activities, share link
			- Attach link: allow user can share a link
			- All Updates: show all activities of user. They are displayed in order from the newest to the oldest
			- Network Updates: show all activities of users friends. They are displayed in order from the newest to the oldestBy default, if there is no one activity on list, show message to user knows: user does not have any updates yetWith each activity, other users can comment, like/unlike.
			- Space Updates : show all activities of space you joined.
			- My Status : show all your activities*/
		info(" View user activities");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		navTool.goToMyActivities();
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));

		/*Step number: 2
		 *Step Name: Step 2: View other user activity
		 *Step Description: 
			- AccessPeople page
			- Select an user who isn't users friend and click on name 
			- Select activity
		 *Input Data: 

		 *Expected Outcome: 
			Show all activities of user. Logged in User can't see the text box to add activities*/ 
		info("Show all activities of user. Logged in User can't see the text box to add activities");
		hp.goToFriendProfilePage(arrayUser.get(0));
		myProfile.goToActivity();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
		waitForElementNotPresent(hpAct.ELEMENT_COMPOSER_INPUT_FILED,5000,1);
	}

	/**
	 *<li> Case ID:122638.</li>
	 *<li> Test Case Name: View activity of user who is not friend but has common connection.</li>
	 *<li> Pre-Condition: User A and User B are connected
	 *User A and User C are connected
	 *User B and User C are NOT connected</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_ViewActivityOfUserWhoIsNotFriendButHasCommonConnection() {
		info("Test 2: View activity of user who is not friend but has common connection");
		info("Create 3 new users");
		createNewUser(3);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("User A sent request connection to User B");
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A sent request connection to User C");
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(2));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept connection with User A");
		hp.goToConnections();	
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User C login");
		magAc.signIn(arrayUser.get(2), password);
		Utils.pause(3000);
		
		info("User C accept connection with User A");
		hp.goToConnections();	
		connMag.acceptAConnection(arrayUser.get(0));

		/*Step Number: 1
		 *Step Name: Step 1: Post an activity
		 *Step Description: 
			- Login with user C
			- Go to Activities Stream of user C
			- Post an activity with content "Hello user A from user C"
		 *Input Data: 

		 *Expected Outcome: 
			The activity of user C "Hello user A from user C" is posted on activity stream of user A*/
		info("Post an activity on User A's activity");
		hp.goToFriendProfilePage(arrayUser.get(0));
		myProfile.goToActivity();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		hpAct.checkActivity(activity1);
		
		/*Step number: 2
		 *Step Name: Step 2: View activity stream of user A
		 *Step Description: 
			- Login with user A
			- Check on activity stream of user A
		 *Input Data: 

		 *Expected Outcome: 
			The activity of user C "Hello user A from user B" is shown in Activities Stream of user A.*/
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("The activity is shown on UserA's stream");
		navTool.goToMyActivities();
		hpAct.checkActivity(activity1);
		
		/*Step number: 3
		 *Step Name: Step 3: View activity stream of user B
		 *Step Description: 
			- Login with user B
			- Check on activity stream of user B
		 *Input Data: 

		 *Expected Outcome: 
			The activity of user C "Hello user A from user C" is shown in Activities Stream of user A.*/ 
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("The activity isnot shown on Home page of User A");
		hp.goToHomePage();
		hpAct.checkNotShownActivity(activity1);
		
		info("The activity is shown on UserA's stream");
		hp.goToFriendProfilePage(arrayUser.get(0));
		myProfile.goToActivity();
		hpAct.checkActivity(activity1);
	}
	
	/**
	*<li> Case ID:127062.</li>
	*<li> Test Case Name: View activity include mentioning on All Activities stream of mentioned user.</li>
	*<li> Pre-Condition: User A is connected with User B</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test03_ViewActivityIncludeMentioningOnAllActivitiesStreamOfMentionedUser() throws AWTException {
		info("Test 3: View activity include mentioning on All Activities stream of mentioned user");
		info("Create 2 new users");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("User A sent request connection to User B");
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept connection with User A");
		hp.goToConnections();	
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Share mention activity
		*Step Description: 
			- Login with User B
			- Go to activity of User A: Share an activity with content include mention UserA (For example: Hello @userb)
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is shared on activity of User B*/
		
		info("Post an activity on User A's activity");
		hp.goToFriendProfilePage(arrayUser.get(0));
		myProfile.goToActivity();
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: View All Activities stream of User B
		*Step Description: 
			- Go to homepage intranet: Show "All activities" of User B
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity at Step 1 is displayed on "All activities" stream of User B*/
		
		info("The activity is shown on Home activity Stream");
		hp.goToHomePage();
		hpAct.checkActivity(activity);

		/*Step number: 3
		*Step Name: Step 3: View My Activities stream of User B
		*Step Description: 
			- Go to homepage intranet: Show "My activities" of User A
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity at Step 1 is displayed on "My activities" stream of User B*/
		
		info("The activity is shown on My activity stream of User B");
		hp.goToHomePage();
		hp.selectDisplayMode(displayModeType.My_Activities);
		hpAct.checkActivity(activity);

		/*Step number: 4
		*Step Name: Step 4: View All Activities stream of User A
		*Step Description: 
			- Login with User B
			- Show "All activities" of User B
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity at Step 1 is displayed on "All activities" stream of User A*/
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("The activity is shown on Home activity Stream");
		hp.goToHomePage();
		hpAct.checkActivity(activity);

		/*Step number: 5
		*Step Name: Step 5: View My Activities stream of User B
		*Step Description: 
			- Login with User B
			- Show "My activities" of User B
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity at Step 1 is displayed on "My activities" stream of User B*/ 
		info("The activity is shown on My activity stream of User B");
		hp.goToHomePage();
		hp.selectDisplayMode(displayModeType.My_Activities);
		hpAct.checkActivity(activity);

 	}
	
	/**
	*<li> Case ID:127063.</li>
	*<li> Test Case Name: View mention activity of user who is not friend but has common connection.</li>
	*<li> Pre-Condition: User A and User B are connected
	*User A and User C are connected
	*User C share an activity that mentioned User A on activity stream</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test04_ViewMentionActivityOfUserWhoIsNotFriendButHasCommonConnection() throws AWTException {
		info("Test 4: View mention activity of user who is not friend but has common connection");
		info("Create 3 new users");
		createNewUser(3);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("User A sent request connection to User B");
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A sent request connection to User C");
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(2));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept connection with User A");
		hp.goToConnections();	
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User C login");
		magAc.signIn(arrayUser.get(2), password);
		Utils.pause(3000);
		
		info("User C accept connection with User A");
		hp.goToConnections();	
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1 : Share activity
		*Step Description: 
			- Login as user C
			- Share an activity that mentioned User A on activity stream
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity is displayed on activity stream of User C*/
		
		info("Share an activity that mentioned User A on activity stream");
		hp.goToHomePage();
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Mentioned user view shared activity
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Shared activity at Step 1 is displayed on activity stream of User A*/
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("Share an activity that mentioned User A on activity stream");
		hp.goToHomePage();
		hpAct.checkActivity(activity);

		/*Step number: 3
		*Step Name: Step 3: Not connected user view shared activity
		*Step Description: 
			- Login as user B
		*Input Data: 
			
		*Expected Outcome: 
			- User B CAN NOT see the shared activity at Step 1*/ 
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("Share an activity that mentioned User A on activity stream");
		hp.goToHomePage();
		hpAct.checkNotShownActivity(activity);


 	}
}