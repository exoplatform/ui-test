package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_Peple_Activity_LikeUnlike extends SOC_TestConfig{

	/**
	 *<li> Case ID:122791.</li>
	 *<li> Test Case Name: Likes/unlike friends activities.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_LikesunlikeFriendsActivities() {
		info("Test 1: Likes/unlike friends activities");
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
			Step 1: Create your comment ( browser 1)
		 *Input Data: 
			- Login by user AAA
			- Go to My profile
			- Create new activities
		 *Expected Outcome: 
			Activity is added successfully*/
		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Go to people page
		 *Input Data: 
			- Select people page on People Toolbar portlet
		 *Expected Outcome: 
			- Show content of People page*/

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Invite user
		 *Input Data: 
			- Select user who has no relation with user to add relation
			- Click on [Invite] (BBB)
		 *Expected Outcome: 
			Invited user successfully*/

		info("Click on Connections on the left panel");
		hp.goToConnections();
		info("Access people list, invite an user");
		connMag.connectToAUser(username2);
		
		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Accept the friend ( browser 2)
		 *Input Data: 
			- Sign in with user which invited at step 2 (BBB)
			- Select people page on People Toolbar portlet
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
		
		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Like friends activities
		 *Input Data: 
			- Go to my profile and click Like button in AAA's activities
		 *Expected Outcome: 
			Show the message: â€œyou like thisâ€. Like link is change to Unlike link*/
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		
		/*Step number: 6
		 *Step Name: -
		 *Step Description: 
			Step 6: Unlike a activity
		 *Input Data: 
			- User who liked the activity logs in
			- Select the activity
			- Click on Unlike link
		 *Expected Outcome: 
			User is moved out list of users like the activity. Unlike link is changed to Like link*/
		hpAct.unlikeActivity(activity1);
		
		/*Step number: 7
		 *Step Name: -
		 *Step Description: 
			Step 7: View all people like activity
		 *Input Data: 
			- Click on Link â€œnumber peopleâ€
		 *Expected Outcome: 
			Show all people like the activity. Move mouse over each user, a tool
			-tip appears to show name of user who likes*/ 
		hpAct.likeActivity(activity1);

	}

	/**
	 *<li> Case ID:122773.</li>
	 *<li> Test Case Name: Likes/unlike your activities.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_LikesunlikeYourActivities() {
		info("Test 2: Likes/unlike your activities");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add new activities
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
			- Select activity in the left pane
			- Enter some text into text box
			- Click on [Share] button
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Like activity
		 *Input Data: 
			- Click on Like under activity
		 *Expected Outcome: 
			Show the message: â€œyou like thisâ€. Like link is change to Unlike link*/
		hpAct.likeActivity(activity1);
		
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Unlike a activity
		 *Input Data: 
			- User who liked the activity logs in
			- Select the activity
			- Click on Unlike link
		 *Expected Outcome: 
			User is moved out list of users like the activity. Unlike link is changed to Like link*/ 
		hpAct.unlikeActivity(activity1);
	}
}