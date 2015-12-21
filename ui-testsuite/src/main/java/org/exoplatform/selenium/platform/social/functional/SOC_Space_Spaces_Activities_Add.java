package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;

	public class SOC_Space_Spaces_Activities_Add extends SOC_TestConfig3{

	/**
	*<li> Case ID:122427.</li>
	*<li> Test Case Name: Add activity after an user joins a public space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_AddActivityAfterAnUserJoinsAPublicSpace() {
		info("Test 1: Add activity after an user joins a public space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Join a public space (by creating new public space or request to join a public space)
		*Input Data: 
			
		*Expected Outcome: 
			- User joins a public space. A comment is added to the space creation activity.*/ 
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes,6000);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("User B accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("A comment is added to the space creation activity");
		hp.goToSpecificSpace(spaceName);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",username2));
		
		info("A comment is added to Home page activity");
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",username2));
		
 	}

	/**
	*<li> Case ID:122466.</li>
	*<li> Test Case Name: Add a Space activity on Intranet home page after create a space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AddASpaceActivityOnIntranetHomePageAfterCreateASpace() {
		info("Test 02: Add a Space activity on Intranet home page after create a space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Add space
		*Step Description: 
			- Connect to Intranet
			- Add a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity is displayed in the activity stream*/

		/*Step number: 2
		*Step Name: Step 2: Display activity
		*Step Description: 
			- Move the mouse over the Space activity
		*Input Data: 
			
		*Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",username1));
		mouseOver(hpAct.ELEMENT_ACTIVITY_SPACE_HEADING.replace("${space}", space),false);
	}

	
	/**
	*<li> Case ID:122637.</li>
	*<li> Test Case Name: Add new your activity.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_AddNewYourActivity() {
		info("Test 03: Add new your activity");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create new space
		*Step Description: 
			- Connect to Intranet
			- Click Join a space button 
			- Add new space

		*Input Data: 
			
		*Expected Outcome: 
			- New space is added*/
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step Number: 2
		*Step Name: Add new activities
		*Step Description: 
			- Enter some text into text box
			- Click on Share button

		*Input Data: 
			
		*Expected Outcome: 
			- New activity is shared*/
		
		info("Add activity");
		hp.goToSpecificSpace(space);
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
	}

	/**
	*<li> Case ID:122562.</li>
	*<li> Test Case Name: Add a share link.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_AddAShareLink() {
		info("Test 17 Add a share link");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create new space
		*Step Description: 
			- Connect to Intranet
			- Click Join a Space button
			- Add new space
		*Input Data: 
			
		*Expected Outcome: 
			- New space is created successfully*/
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: Step 2: Input a link
		*Step Description: 
			- Click on Add Link icon
			- Enter a valid URL
			- Click on Attach button on Share link form
		*Input Data: 
			
		*Expected Outcome: 
			Get information from link successfully. Some information are:
			- Link title
			- Link description
			- URL user has entered
			- If it gets image from link, there is a check box to allows display or not display image
			- Close icon*/
		
		/*Step number: 3
		*Step Name: Step 3: Share a link
		*Step Description: 
			- Enter chars into text box
			- Click on share button
		*Input Data: 
			
		*Expected Outcome: 
			A link is shared with some text on activity.*/ 
		info("user 2 add link");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = linkData.getLinkByArrayTypeRandom(1);
		hpAct.addActivity(textDes, link);
		Utils.pause(2000);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_LINK.replace("${title}",textDes).replace("${link}",link),2000,1);
 	}
}