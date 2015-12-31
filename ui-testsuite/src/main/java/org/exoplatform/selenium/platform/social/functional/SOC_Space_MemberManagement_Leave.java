package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


	public class SOC_Space_MemberManagement_Leave extends SOC_TestConfig{

	/**
	*<li> Case ID:122560.</li>
	*<li> Test Case Name: Sends invitation after request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_SendsInvitationAfterRequest() {
		info("Test 1: Sends invitation after request");
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
		*Step Name: -
		*Step Description: 
			Step 1: Two users login on two different browser
		*Input Data: 
			- User A signs in and select Space page on Browser 1
			- User B signs in and select Space page on Browser 2
		*Expected Outcome: 
			Log in successfully*/
		String browser1= driver.getWindowHandle();
		
		WebDriver driver2 = new FirefoxDriver();
        driver2.get(baseUrl);
        String browser2= driver2.getWindowHandle();
        
        ManageLogInOut magAc2;
        HomePagePlatform hp2;
        SpaceManagement spaMg2;
        magAc2 = new ManageLogInOut(driver2);
        hp2 = new HomePagePlatform(driver2);
        spaMg2 = new SpaceManagement(driver2);
		magAc2.signIn(username2,password);
		
		
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Create a new Space on a browser 1
		*Input Data: 
			- User A licks on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 driver.switchTo().window(browser1);
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: User A accesses member tab on browser 1
		*Input Data: 
			- Click on [Edit space] icon or access the space then access [Setting space] portlet
			- Select Member tab
		*Expected Outcome: 
			Show member tab*/
         spaHome.goToSpaceSettingTab();
         setSpaceMg.goToMemberTab();
         
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: View All space on browser 2
		*Input Data: 
			- User B select All spaces list
		*Expected Outcome: 
			All spaces are shown*/
         driver2.switchTo().window(browser2);
         driver2.manage().window().maximize();
         hp2.goToAllSpace();
         
		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 5: send request to join into the Space on browser 2
		*Input Data: 
			- User B selects the space which has created at step 2 
			and clicks on [request to join]
		*Expected Outcome: 
			Send request successfully*/
         spaMg2.requestToJoinSpace(space,true);
         driver2.close();
		/*Step number: 6
		*Step Name: -
		*Step Description: 
			Step 6: Send invitation to user B on browser 1
		*Input Data: 
			- User A selects user B to send invitation
			- Click on [invite] button
		*Expected Outcome: 
			User B is listed in pending list.*/
         driver.switchTo().window(browser1);
         spaHome.goToSpaceSettingTab();
         setSpaceMg.inviteUser(username2, true,fullName2);

 	}

	/**
	*<li> Case ID:122566.</li>
	*<li> Test Case Name: Members leave the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_MembersLeaveTheSpace() {
		info("Test 2: Members leave the space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Request to join space by step 1
		*Input Data: 
			- Login by another user ( excepted root)
			- Go to Spaces public 
			- Click [Request to join]
		*Expected Outcome: 
			- Login in successfully 
			- In public space: display space which added at step 1*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space,true);
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Accept request to join
		*Input Data: 
			- Login with user which created space at step 1
			- Go to Spaces manage/Pending
			- Click Validate
		*Expected Outcome: 
			- Login in successfully
			- In my spaces of invited user: display space which added at step 1*/
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.acceptRequest(username2);
	     
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Member leave the space
		*Input Data: 
			- Select My spaces list
			- Selects the space and click on [leave] button
		*Expected Outcome: 
			User has removed from list member of the space and space group. User can't access this space now.*/ 
	     magAc.signIn(username2,password);
	     hp.goToMySpaces();
	     spaMg.searchSpace(space, "");
	     spaMg.leaveSpace(space);
	     
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     waitForElementNotPresent(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",username2));
	     
	     magAc.signIn(USER_ROOT,PASS_ROOT);
	     navTool.goToUsersAndGroupsManagement();
         userGroupMg.goToGroupTab();
         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
 		 String[] arrayGroupPath ={groups.get(0),space};
 		 userGroupMg.selectGroup(arrayGroupPath);
 		 waitForElementNotPresent(userGroupMg.ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}",username2));
 	}

	/**
	*<li> Case ID:122581.</li>
	*<li> Test Case Name: Sends request after invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_SendsRequestAfterInvitation() {
		info("Test 3: Sends request after invitation");
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
		*Step Name: -
		*Step Description: 
			Step 1: Two users login on two different browser
		*Input Data: 
			- User A signs in and select Space page on Browser 1
			- User B signs in and select Space page on Browser 2
		*Expected Outcome: 
			Log in successfully*/
		String browser1 = driver.getWindowHandle(); 
		
		WebDriver driver2 = new FirefoxDriver();
        driver2.get(baseUrl);
        String browser2 = driver2.getWindowHandle(); 
        
        ManageLogInOut magAc2;
        HomePagePlatform hp2;
        SpaceManagement spaMg2;
        magAc2 = new ManageLogInOut(driver2);
        hp2 = new HomePagePlatform(driver2);
        spaMg2 = new SpaceManagement(driver2);
		magAc2.signIn(username2,password);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Create a new Space on a browser 1
		*Input Data: 
			- User A licks on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 driver.switchTo().window(browser1);
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: View All space on browser 2
		*Input Data: 
			- User B clicks on All spaces
		*Expected Outcome: 
			Show all spaces*/
		 driver2.switchTo().window(browser2);
		 driver2.manage().window().maximize();
		 hp2.goToAllSpace();
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Send invitation to user B on browser 1
		*Input Data: 
			- User A Click on [Edit space] icon or access the space then access [Setting space]
			- Select Member tab
			- Select user B to send invitation
			- Click on [invite] button
		*Expected Outcome: 
			Send invitation successfully.*/
		 driver.switchTo().window(browser1);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(username2, true,fullName2);
		  
		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 5: send request to join into the Space on browser 2
		*Input Data: 
			- User B selects the space which has created at step 2 
		*Expected Outcome: 
			Button "Request to join" is changed to "Accept|Ignore", Mary does not become member of space*/ 
		 driver2.switchTo().window(browser2);
		 spaMg2.searchSpace(space, "");
		 spaMg2.waitForAndGetElement(spaMg2.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN.replace("${space}",space),2000,1);
		 spaMg2.waitForAndGetElement(spaMg2.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
		 driver2.close();
 	}

	/**
	*<li> Case ID:122596.</li>
	*<li> Test Case Name: Remove a manager.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_RemoveAManager() {
		info("Test 4: Remove a manager");
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
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invited user
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter valid user name or select a user from list
			- Click [invite] icon
		*Expected Outcome: 
			Send invitation successfully:
			- On screen ofinvitation sender: if no has list Invited user, display list invited user, else add name of user was invited into list.*/
		 
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(username2, true,fullName2);
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Make a user to become manager of the space
		*Input Data: 
			- The manager of space which has created at step 1, signs in and selects the space
			- Click [edit space] icon or access the space and select [Space settings] portlet
			- Select [members] tab
			- At list members, selects member which has added at step 2 and click on [make leader] icon
		*Expected Outcome: 
			- User become manager of spaceUser can edit and access [Space settings] portlet.*/
		 magAc.signIn(username2,password);
		 hp.goToAllSpace();
		 spaMg.acceptAInvitation(space);
		 
		 magAc.signIn(username1,password);
		 hp.goToSpecificSpace(space);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.changeRole(fullName2);
		 
		 magAc.signIn(username2,password);
		 hp.goToSpecificSpace(space);
		 waitForAndGetElement(spaMg.ELEMENT_NAVIGATION_SPACE_SETTING_TAB,2000,1);

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Remove manager of user
		*Input Data: 
			- The Manager of space which has created at step 1, signs in and selects this space
			- Click [edit space] icon or access the space and select [Space Settings] portlet
			- Select [members] tab
			- At list members, selects member which has added at step 2 and click on [Remove Leader] button
		*Expected Outcome: 
			- User becomes normal member, canaccess the space but can't edit and accesses Space settings portlet
			- Back to focus on home page of space and remove space setting on list applications on space*/ 
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.changeRole(fullName2);
		 spaMg.goToActivityStreamTab();
		 waitForElementNotPresent(spaMg.ELEMENT_NAVIGATION_SPACE_SETTING_TAB,2000,1);

 	}

	/**
	*<li> Case ID:122613.</li>
	*<li> Test Case Name: Make a user to become the manager of the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_MakeAUserToBecomeTheManagerOfTheSpace() {
		info("Test 5: Make a user to become the manager of the space");
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
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invited user
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter valid user name or select a user from list
			- Click [invite] icon
		*Expected Outcome: 
			Send invitation successfully:
			- On screen ofinvitation sender: if no has list Invited user, display list invited user, else add name of user was invited into list.*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(username2, true,fullName2);
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Accept invitation
		*Input Data: 
			- Invited user logs in
			- Go to Spaces/invitations list
			- Accept invited of the space
		*Expected Outcome: 
			- User be come member of the space*/
		 magAc.signIn(username2,password);
		 hp.goToAllSpace();
		 spaMg.acceptAInvitation(space);
		 
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Make a user to become manager of the space
		*Input Data: 
			- The manager of space which has created at step 1, signs in and selects the space
			- Click [edit space] icon or access the space and select [Space settings] portlet
			- Select [members] tab
			- At list members, selects member which has added at step 2 and click on [make lesder] icon
		*Expected Outcome: 
			- User become manager of spaceUser can edit and access [Space settings] portlet.*/ 
		 magAc.signIn(username1,password);
		 hp.goToSpecificSpace(space);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.changeRole(fullName2);
		 
		 magAc.signIn(username2,password);
		 hp.goToSpecificSpace(space);
		 waitForAndGetElement(spaMg.ELEMENT_NAVIGATION_SPACE_SETTING_TAB,2000,1);
		 
 	}

	/**
	*<li> Case ID:122625.</li>
	*<li> Test Case Name: Manager of space leaves space when only has one member in space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_ManagerOfSpaceLeavesSpaceWhenOnlyHasOneMemberInSpace() {
		info("Test 6: Manager of space leaves space when only has one member in space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Leave space
		*Input Data: 
			- Select My space list
			- On the space has created at step 1, click on Leave icon
		*Expected Outcome: 
			Show message to user known: â€œYou are the last manager of this space. You need to promote another member as manager of the space before you can leave it. â€*/ 
		 ArrayList<String> warningTextArray=spWarnMessg.getArrayContentByType(2);
		 String warningText=warningTextArray.get(0);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.changeRole(fullName1);
		 waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_WARNING_MESSAGE.replace("${warningText}", warningText),2000,1);
		 button.ok();
 	}

	/**
	*<li> Case ID:122615.</li>
	*<li> Test Case Name: Root leaves the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_RootLeavesTheSpace() {
		info("Test 7: Root leaves the space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Add member for space
		*Input Data: 
			- Add user Root into space group or invite user Root to join in Space
		*Expected Outcome: 
			- Add member successfully*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(USER_ROOT,false,"");
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Member leave the space
		*Input Data: 
			- Root signs in and select the space which has created at step 1
			- Root leaves from this space
		*Expected Outcome: 
			User Root has removed from list member of the space and space group. 
			But Root still has access and edit right on space*/ 
		 magAc.signIn(USER_ROOT, PASS_ROOT);
		 hp.goToAllSpace();
		 spaMg.searchSpace(space, "");
		 spaMg.leaveSpace(space);
		 spaMg.goToSpace(space);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.goToMemberTab();
		 waitForElementNotPresent(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",DATA_NAME_ROOT));
 	}

	/**
	*<li> Case ID:122635.</li>
	*<li> Test Case Name: Members leave the space ( by invited).</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_MembersLeaveTheSpace() {
		info("Test 8: Members leave the space ( by invited)");
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
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invited user
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter valid user name or select a user from list
			- Click [invite] icon
		*Expected Outcome: 
			Send invitation successfully:
			- On screen ofinvitation sender: if no has list Invited user, display list invited user, else add name of user was invited into list.*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(username2, true,fullName2);
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Accept invitation
		*Input Data: 
			- Login with invited user
			- Go to Spaces manage/Invitation
			- Click accept
		*Expected Outcome: 
			- Login in successfully
			- In my spaces of invited user: display space which added at step 1*/
		 magAc.signIn(username2,password);
		 hp.goToAllSpace();
		 spaMg.acceptAInvitation(space);
		 
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Member leave the space
		*Input Data: 
			- Select My spaces list
			- Selects the space and click on [leave] button
		*Expected Outcome: 
			User has removed from list member of the space and space group. User can't access this space now.*/ 
		 hp.goToMySpaces();
		 spaMg.searchSpace(space, "");
		 spaMg.leaveSpace(space);
		 hp.goToMySpaces();
		 waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space));
		 
		 magAc.signIn(username1,password);
		 hp.goToSpecificSpace(space);
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.goToMemberTab();
		 waitForElementNotPresent(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName2));
		 
		 magAc.signIn(USER_ROOT,PASS_ROOT);
		 navTool.goToUsersAndGroupsManagement();
         userGroupMg.goToGroupTab();
         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
 		 String[] arrayGroupPath ={groups.get(0),space};
 		 userGroupMg.selectGroup(arrayGroupPath);
 		 waitForElementNotPresent(userGroupMg.ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}",username2));
 	}
}