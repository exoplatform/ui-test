package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_Activity_RelationActivity extends SOC_TestConfig{

	/**
	 *<li> Case ID:122756.</li>
	 *<li> Test Case Name: Add Relation Activity for both connected user.</li>
	 *<li> Case ID:122754.</li>
	 *<li> Test Case Name: Update Add Relation Activity after connecting to another user.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_04_AddRelationActivityForBothConnectedUser() {
		info("Test 1: Add Relation Activity for both connected user");
		String activity1 = "I'm now connected with";
		

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
		
		String comment1 = "I'm now connected with "+ username1+" "+username1;
		String comment2 = "I'm now connected with "+ username2+" "+username2;

		/*Step Number: 1
		 *Step Name: Send request
		 *Step Description: 
			- Connect to Intranet with the user A
			- Send an invitation to connect to the user B
		 *Input Data: 

		 *Expected Outcome: 
			Invitation is sent*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		info("Access people list, invite an user");
		connMag.connectToAUser(username2);
		
		info("Test 4: Update Add Relation Activity after connecting to another user");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet as user A
			- Accept invitation from user B
		 *Input Data: 

		 *Expected Outcome: 
			- A Relation activity is updated in the activity stream: it displays thenumber of connections of the user + 1
			- A comment is added with the message "I'm now connected with user B"*/ 

		/*Step number: 2
		 *Step Name: Accept invitation
		 *Step Description: 
			- Connect to Intranet with the user B
			- Accept invitation
		 *Input Data: 

		 *Expected Outcome: 
			- A Relation activity is added to the activity stream with comment"I'm now connected with User A"*/
		info("Invited user accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept");
		connMag.verifyConnection(username1, true);
		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));

		/*Step number: 3
		 *Step Name: Check activity
		 *Step Description: 
			- Come back to Intranet with the user A
		 *Input Data: 

		 *Expected Outcome: 
			- A Relation activity is added to the activity stream with comment"I'm now connected with User B"*/ 
		magAc.signIn(username1,password);
		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1)
				.replace("${commentText}",comment2));
	}

	/**
	 *<li> Case ID:122758.</li>
	 *<li> Test Case Name: Delete a Relation activity from activity stream by its user.</li>
	 *<li> Pre-Condition: - A relation activity of user A is already shared</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_DeleteARelationActivityFromActivityStreamByItsUser() {
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
		String activity1 = "I'm now connected with";
		String comment1 = "I'm now connected with "+ username1+" "+username1;
		
		
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
		Utils.pause(2000);
		navTool.goToMyActivities();
		
		info("Test 2: Delete a Relation activity from activity stream by its user");
		/*Step Number: 1
		 *Step Name: Step 1: Connect to Intranet
		 *Step Description: 
			- Connect to Intranet as user A
		 *Input Data: 

		 *Expected Outcome: 
			The Relation activity is displayed in the activity stream*/
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		
		/*Step number: 2
		 *Step Name: Step 2: Show [remove] icon
		 *Step Description: 
			- Move the mouse over the Relation activity
		 *Input Data: 

		 *Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/

		/*Step number: 3
		 *Step Name: Step 3: Click [remove] icon
		 *Step Description: 
			- Click on the (X) icon
			- Click on OK button of confirmation message
		 *Input Data: 

		 *Expected Outcome: 
			The Relation activity is removed from the activity stream*/ 
		hpAct.deleteActivity(activity1);

	}

	/**
	 *<li> Case ID:122759.</li>
	 *<li> Test Case Name: Dislike a Relation activity from like icon.</li>
	 *<li> Pre-Condition: - A Relation activity of user A is already shared
	- And it's liked by the user A</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_DislikeARelationActivityFromLikeIcon() {
		

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
		String activity1 = "I'm now connected with";
		String comment1 = "I'm now connected with "+ username1+" "+username1;
		
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
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		navTool.goToMyActivities();
		info("Test 3: Dislike a Relation activity from like icon");
		/*Step Number: 1
		 *Step Name: Display Relation activity
		 *Step Description: 
			- Connect to Intranet with User A
		 *Input Data: 

		 *Expected Outcome: 
			- The Relation activity is displayed in the activity stream*like icon + number of likes to 1*/
		hpAct.likeActivity(activity1);
		
		/*Step number: 2
		 *Step Name: Like activity
		 *Step Description: 
			- Click on like icon
		 *Input Data: 

		 *Expected Outcome: 
			- The Relation activity is disliked by the user, the number of like is updated to 
			-1*/ 
		hpAct.unlikeActivity(activity1);
	}
}