package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_Activity_Comment extends SOC_TestConfig{

	/**
	 *<li> Case ID:122799.</li>
	 *<li> Test Case Name: Comment on your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CommentOnYourActivity() {
		info("Test 1: Comment on your activity");
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
		magAc.signIn(DATA_USER1, DATA_PASS);
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Show comment form
		 *Input Data: 
			- Click on Comment link under the activity
		 *Expected Outcome: 
			Show a text box allow user add comment for activity*/

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Add a comment
		 *Input Data: 
			- Enter some text into text box comment
			- Click on Comment button
		 *Expected Outcome: 
			Add a comment successfully. Other user can view activity and comment of one. With each comment, some information is display:
			- Avatar of user comment
			- Name of user comment
			- Content of comment
			- Time comment is posted*/ 
		String comment=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity1, comment);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment));
	}

	/**
	 *<li> Case ID:122792.</li>
	 *<li> Test Case Name: Comment on your friends activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CommentOnYourFriendsActivity() {
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);

		info("Test 2: Comment on your friends activity");
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
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Go to people page
		 *Input Data: 
			- Select people page
		 *Expected Outcome: 
			- Show content of People page*/

		info("Click on Connections on the left panel");
		hp.goToConnections();

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Invite user
		 *Input Data: 
			- Select user who has no relation with user to add relation
			- Click on [Invite] (BBB)
		 *Expected Outcome: 
			Invited user successfully*/
		info("Access people list, invite an user");
		connMag.connectToAUser(username1);

		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Accept the friend ( browser 2)
		 *Input Data: 
			- Sign in with user which invited at step 2 (BBB)
			- Select people page 
			- Click on button [accept]
		 *Expected Outcome: 
			- After the user clicks on [accept], the relation between two users has set. The user will be added into users relations user list. 
			- By side each the user, has a [remove] button to user can remove from this relation.=> Two user become friend*/
		info("Invited user accept invitation");
		magAc.signIn(username1, password1);
		hp.goToConnections();	
		connMag.acceptAConnection(DATA_USER1);
		info("Verify after accept");
		connMag.verifyConnection(DATA_USER1, true);

		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Add a comment
		 *Input Data: 
			- Go to my profile and click comment button in AAA's activities
		 *Expected Outcome: 
			Show comment successfully*/ 
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.goToUserByFullName(DATA_NAME_USER1);
		uBase.goToActivityTab();
		String comment=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity1, comment);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment));
	}

	/**
	 *<li> Case ID:122787.</li>
	 *<li> Test Case Name: Delete comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_DeleteComment() {
		info("Test 3: Delete comment");
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
		magAc.signIn(DATA_USER1, DATA_PASS);
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add a comment
		 *Input Data: 
			- Click on comment link under the activity
			- Enter some text into text box comment
			- Click on Comment button
			- Add more comments
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/
		String comment=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity1, comment);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment));

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Delete comment
		 *Input Data: 
			- Select an comment
			- Click on Delete
			- Click OK to confirm deleting
		 *Expected Outcome: 
			This comment is deleted.*/ 
		hpAct.deleteComment(activity1, comment);
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment));
	}

	/**
	 *<li> Case ID:122794.</li>
	 *<li> Test Case Name: Show/hide all comment.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_ShowhideAllComment() {
		info("Test 4: Show/hide all comment");
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
		magAc.signIn(DATA_USER1, DATA_PASS);
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add a comment
		 *Input Data: 
			- Click on comment link under the activity
			- Enter some text into text box comment
			- Click on Comment button
			- Add more comments
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/
		String comment1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity1, comment1);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		
		hpAct.addComment(activity1, comment2);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));

		hpAct.addComment(activity1, comment3);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment3));

		hpAct.addComment(activity1, comment4);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment4));

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Move other page
		 *Input Data: 
			- Select any other page to access
			- Return to activity page
		 *Expected Outcome: 
			-Show content of activity page. There are 2 lastest comments are showed. A link â€œshow all 'number' commentsâ€ is displayed*/
		hp.goToConnections();
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment3));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment4));

		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Show all comment
		 *Input Data: 
			- Click on â€œshow all 'number' commentsâ€ link
		 *Expected Outcome: 
			Display all comments of activity. A link â€œhide all commentsâ€ is show*/
		info("Verify that view all comment links is shown and clickable on it");
		hpAct.showComment(activity1);
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment3));
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment4));
		
		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Hide all comments
		 *Input Data: 
			- Click on â€œhide all commentsâ€ link
		 *Expected Outcome: 
			Hide all comment. A link â€œshow all 'number' commentsâ€ is displayed*/ 
		hpAct.hideComment(activity1);
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment3));
		waitForElementNotPresent(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment4));
	}
}
