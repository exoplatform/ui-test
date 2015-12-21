package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class SOC_Space_Space_Activities_Others extends SOC_TestConfig3{

	/**
	*<li> Case ID:122450.</li>
	*<li> Test Case Name: Dislike a Space activity from like icon.</li>
	*<li> Pre-Condition: - At least a space activity is shared
	- The activity is liked by the user A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_DislikeASpaceActivityFromLikeIcon() {
		info("Test 1: Dislike a Space activity from like icon");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Display activity
		*Step Description: 
			- Connect to Intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity is displayed in the activity stream
			*like icon + number of likes to 1*/
		
		info("Add new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Like the Space activity");
		hp.goToSpecificSpace(space);
		hpAct.likeActivity(space);
		
		/*Step number: 2
		*Step Name: Dislike activity
		*Step Description: 
			- Click on like icon
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity is disliked by the user, the number of like is updated to 
			-1*/ 
		info("Dislike the Space activity");
		hpAct.unlikeActivity(space);

 	}

	/**
	*<li> Case ID:122454.</li>
	*<li> Test Case Name: Dislike a User activity for space from like icon.</li>
	*<li> Pre-Condition: - Space is created
	- Space has user activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_DislikeAUserActivityForSpaceFromLikeIcon() {
		info("Test 2: Dislike a User activity for space from like icon");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Like user activity for space
		*Step Description: 
			- Connect to Intranet with User A
			- Select activity added for space by user
			- Click on Like icon
		*Input Data: 
			
		*Expected Outcome: 
			- The User activity is displayed in the activity stream*like icon + number of likes to 1*/
		info("Add new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Add new activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		info("Like the activity");
		hpAct.likeActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Dislike
		*Step Description: 
			- Click on like icon again
		*Input Data: 
			
		*Expected Outcome: 
			- The User activity is disliked by the user, the number of like is updated to 
			-1*/ 
		info("Unlike the activity");
		hpAct.unlikeActivity(activity);

 	}

	/**
	*<li> Case ID:122475.</li>
	*<li> Test Case Name: Comment on your activity.</li>
	*<li> Pre-Condition: - Space is created
	- Activity for space is added</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CommentOnYourActivity() {
		info("Test 3: Comment on your activity");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Show comment form
		*Step Description: 
			- Click on Comment link under the activity
		*Input Data: 
			
		*Expected Outcome: 
			Show a text box allow user add comment for activity*/
		info("Add new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		/*Step number: 2
		*Step Name: Step 2: Add a comment
		*Step Description: 
			- Enter some text into text box comment
			- Click on Comment button
		*Input Data: 
			
		*Expected Outcome: 
			Add a comment successfully. Other user can view activity and its comment. With each comment, some information is display:
			- Avatar of user comment
			- Name of user comment
			- Content of comment
			- Time comment is posted*/ 
		info("Add an comment on space activity");
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(space,comment);
		hpAct.checkFormatComment(space, comment, fullName1);

 	}

	/**
	*<li> Case ID:127056.</li>
	*<li> Test Case Name: Only member of space can see activities in Visible and Open space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_OnlyMemberOfSpaceCanSeeActivitiesInVisibleAndOpenSpace() {
		info("Test 4: Only member of space can see activities in Visible and Open space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Visible and Open space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info(" Create a Visible and Open space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpace(space, space, regist,"",6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Login with User A
			- User A join created space at step 2
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and displayed on homepage activity stream of User A*/
        info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);
		
		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/ 
        info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
		hpAct.checkNotShownActivity(activity1);
		hpAct.checkNotShownActivity(activity2);
		hpAct.checkNotShownActivity(activity3);
		hpAct.checkNotShownActivity(activity4);
		hpAct.checkNotShownActivity(activity5);
		hpAct.checkNotShownActivity(activity6);

 	}

	/**
	*<li> Case ID:127057.</li>
	*<li> Test Case Name: Only member of space can see activities in Visible and Validation space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_OnlyMemberOfSpaceCanSeeActivitiesInVisibleAndValidationSpace() {
		info("Test 5: Only member of space can see activities in Visible and Validation space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Visible and Validation space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		info("Create a Visible and Validation space");
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, space,6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Login with User A
			- User A request to join created space at step 2.
			- Login with Root
			- Accept the space join request of User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- Login with User A
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and displayed on homepage activity stream of User A*/
		info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);
        
		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
        info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/ 
        info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
		hpAct.checkNotShownActivity(activity1);
		hpAct.checkNotShownActivity(activity2);
		hpAct.checkNotShownActivity(activity3);
		hpAct.checkNotShownActivity(activity4);
		hpAct.checkNotShownActivity(activity5);
		hpAct.checkNotShownActivity(activity6);

 	}

	/**
	*<li> Case ID:127058.</li>
	*<li> Test Case Name: Only member of space can see activities in Visible and Closed space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_OnlyMemberOfSpaceCanSeeActivitiesInVisibleAndClosedSpace() {
		info("Test 6: Only member of space can see activities in Visible and Closed space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Visible and Closed space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		info("Create a Visible and Validation space");
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, space,6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Login with User A
			- User A join created space at step 2
			- Login with Root
			- Accept space join request of User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and displayed on homepage activity stream of User A*/
		info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);

		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
        info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/ 
        info("User B login");
  		magAc.signIn(username3, password);
  		Utils.pause(3000);
  		
  		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
  		hpAct.checkNotShownActivity(activity1);
  		hpAct.checkNotShownActivity(activity2);
  		hpAct.checkNotShownActivity(activity3);
  		hpAct.checkNotShownActivity(activity4);
  		hpAct.checkNotShownActivity(activity5);
  		hpAct.checkNotShownActivity(activity6);

 	}

	/**
	*<li> Case ID:127059.</li>
	*<li> Test Case Name: Only member of space can see activities in Hidden and Closed space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_OnlyMemberOfSpaceCanSeeActivitiesInHiddenAndClosedSpace() {
		info("Test 7: Only member of space can see activities in Hidden and Closed space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Hidden and Closed space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		info("Create a Visible and Validation space");
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, space,6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Invite User A to member of created space at step 2.
			- Login with User A
			- Accept space invitation request of Root
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and display on homepage activity stream of User A*/
		info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);
        
		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
        info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/
        info("User B login");
  		magAc.signIn(username3, password);
  		Utils.pause(3000);
  		
  		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
  		hpAct.checkNotShownActivity(activity1);
  		hpAct.checkNotShownActivity(activity2);
  		hpAct.checkNotShownActivity(activity3);
  		hpAct.checkNotShownActivity(activity4);
  		hpAct.checkNotShownActivity(activity5);
  		hpAct.checkNotShownActivity(activity6);

 	}

	/**
	*<li> Case ID:127060.</li>
	*<li> Test Case Name: Only member of space can see activities in Hidden and Open space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_OnlyMemberOfSpaceCanSeeActivitiesInHiddenAndOpenSpace() {
		info("Test 8: Only member of space can see activities in Hidden and Open space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Hidden and Open space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		info("Create a Hidden and Open space");
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, space,6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Invite User A to member of created space at step 2.
			- Login with User A
			- Accept space invitation request of Root
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and displayed on homepage activity stream of User A*/
		info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);
        
		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
        info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/ 
        info("User B login");
  		magAc.signIn(username3, password);
  		Utils.pause(3000);
  		
  		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
  		hpAct.checkNotShownActivity(activity1);
  		hpAct.checkNotShownActivity(activity2);
  		hpAct.checkNotShownActivity(activity3);
  		hpAct.checkNotShownActivity(activity4);
  		hpAct.checkNotShownActivity(activity5);
  		hpAct.checkNotShownActivity(activity6);

 	}

	/**
	*<li> Case ID:127061.</li>
	*<li> Test Case Name: Only member of space can see activities in Hidden and Validation space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_OnlyMemberOfSpaceCanSeeActivitiesInHiddenAndValidationSpace() {
		info("Test 9: Only member of space can see activities in Hidden and Validation space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Start PLF
		*Step Description: 
			- Start PLF with fresh data.
		*Input Data: 
			
		*Expected Outcome: 
			PLF is start successfully.*/

		/*Step number: 2
		*Step Name: Step 2: Create a new space
		*Step Description: 
			- Login with root
			- Create a Hidden and Validation space with name "Test Space"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully.*/
		info("Create a Hidden and Validation space");
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space, space,6000);
		
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		/*Step number: 3
		*Step Name: Step 3: Create two new users
		*Step Description: 
			- Login with root
			- From top navigation: choose Administration 
			-
			-> Users 
			-
			-> Add Users
			- Create two new user: User A and User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are created successfully.
			- User A and User B are not connected*/
		info("Invites UserA to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 4
		*Step Name: Step 4: Join Space
		*Step Description: 
			- Invite User A to member of created space at step 2.
			- Login with User A
			- Accept space invitation request of Root
		*Input Data: 
			
		*Expected Outcome: 
			- User A is member of created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Accept User A to become a member of the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);

		/*Step number: 5
		*Step Name: Step 5: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 1, Activity 2, Activity 3
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2 and displayed on homepage activity stream of User A*/
		info("User A share some activities in the space");
        String activity1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity3=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 1");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity1,"");
        hpAct.checkActivity(activity1);
        
        info("Add activity 2");
        hpAct.addActivity(activity2,"");
        hpAct.checkActivity(activity2);
        
        info("Add activity 3");
        hpAct.addActivity(activity3,"");
        hpAct.checkActivity(activity3);
        
		/*Step number: 6
		*Step Name: Step 6: Connect user
		*Step Description: 
			- User A connects to User B
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User B are connected*/
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.connectToAUser(username3);
		Utils.pause(3000);
		
		info("User B login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User B accepts the connections with User A");
		hp.goToConnections();
		connMag.acceptAConnection(username2);

		/*Step number: 7
		*Step Name: Step 7: Share activities in space
		*Step Description: 
			- User A share some activities in created space at step 2: Activity 4, Activity 5, Activity 6
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are shared on created space at step 2*/
		info("User A login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A share some activities in the space");
        String activity4=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity5=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String activity6=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        info("Add activity 4");
        hp.goToSpecificSpace(space);
        hpAct.addActivity(activity4,"");
        hpAct.checkActivity(activity4);
        
        info("Add activity 5");
        hpAct.addActivity(activity5,"");
        hpAct.checkActivity(activity5);
        
        info("Add activity 6");
        hpAct.addActivity(activity6,"");
        hpAct.checkActivity(activity6);

		/*Step number: 8
		*Step Name: Step 8: Check the ability to see the activities in space
		*Step Description: 
			- Login with User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B do not see any shared activities in Step 5 and Step 7 on activity stream*/ 
		info("User B login");
  		magAc.signIn(username3, password);
  		Utils.pause(3000);
  		
  		info("User B do not see any shared activities in Step 5 and Step 7 on activity stream");
  		hpAct.checkNotShownActivity(activity1);
  		hpAct.checkNotShownActivity(activity2);
  		hpAct.checkNotShownActivity(activity3);
  		hpAct.checkNotShownActivity(activity4);
  		hpAct.checkNotShownActivity(activity5);
  		hpAct.checkNotShownActivity(activity6);

 	}}