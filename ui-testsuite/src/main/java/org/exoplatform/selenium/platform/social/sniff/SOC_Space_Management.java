package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_Space_Management extends SOC_TestConfig_1 {

	/**
	 *<li> Case ID:121887.</li>
	 *<li> Test Case Name: Access Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_AccessSpace() {
		info("Test 01: Access Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Access space
		 *Step Description: 
			- Login by user
			- Create new space
			- On Space list, click on space's name or avatar of space
		 *Input Data: 

		 *Expected Outcome: 
			- Display spaces list
			- Show content of space with:
			+ Focus on home space page
			+ All default portlet display: Home space, Discussion, Members, Wiki, Documents Space settings.
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		info("Focus on home space page");
		waitForAndGetElement(hpAct.ELEMENT_COMPOSER_INPUT_FILED,3000,1);
		info("All default portlet is displayed");
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_AGENDA_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_DOCUMENT_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_FORUM_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_MEMBER_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_SPACE_SETTING_PORTLET,2000,1);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_WIKI_PORTLET,2000,1);

		/*info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:121889.</li>
	 *<li> Test Case Name: Add application on Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_AddApplicationOnSpace() {
		info("Test 07: Add application on Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = spAppData.getRandomIndexByType(1);
		String app = spAppData.newApplication.get(index);
		String category = spAppData.newCategory.get(index);
		info("app:"+app);
		info("category:"+category);
		/*Step Number: 1
		 *Step Name: Step 1: Add application
		 *Step Description: 
			- Access Space, select Setting tab/Application
			- Click on Add Application, select application and click on "Install" icon to add
		 *Input Data: 

		 *Expected Outcome: 
			- Application is added on space. 
			- Name of application is display on right of space menu portlet
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		info(" Click on Add Application, select application and click add button");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		setSpaceMg.addApplication(category,app);
		info("Verify that Application is added to space");
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}",app),3000,1);
		info("name of application is shown on right of space menu portlet");
		if(waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_APPLICATION_PORTLET.replace("${app}",app),3000,0)==null){
			hpAct.openMorelist();
			waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_APPLICATION_PORTLET.replace("${app}",app),3000,1);
		}else
			waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_APPLICATION_PORTLET.replace("${app}",app),3000,1);


		/*info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:121891.</li>
	 *<li> Test Case Name: Spaces list.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_SpaceList() {
		info("Test 03: Spaces list");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check All Spaces list
		 *Step Description: 
			- Create new space
			- Login by other user
			- Access Space/All spaces

		 *Input Data: 

		 *Expected Outcome: 
			- Show created space. User can send request to join space.
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		magAc.signIn(username2, password);
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.sendARequestToASpace(space);

	}

	/**
	 *<li> Case ID:121897.</li>
	 *<li> Test Case Name: Create a new Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CreateNewSpace() {
		info("Test 04: Create a new Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Create a new Space
		 *Step Description: 
			- Login Intranet
			- Click on My space on Admin bar
			- Click Add new space and input valid value into create space form
			- Click on Create button

		 *Input Data: 

		 *Expected Outcome: 
			- New space is displayed on My space list of user and Publics space list of other user..
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Publics space list of other user");
		magAc.signIn(username2,password);
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}", space),2000,1);

		magAc.signIn(username1, password);
		hp.goToMySpaces();
		spaMg.searchSpace(space,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);

	}

	/**
	 *<li> Case ID:121911.</li>
	 *<li> Test Case Name: Edit a space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_EditASpace() {
		info("Test 05:Edit a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String filename = fData.getAttachFileByArrayTypeRandom(26);
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Edit a space
		 *Step Description: 
			- Login Intranet
			- Click on My space on Admin bar
			- Click Add new space
			- On My spaces list, click Edit: Edit information, visibility, edit avatar and click save
		 *Input Data: 

		 *Expected Outcome: 
			- Add new space successfully
			- All changed of space is saved. User see it when access space
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Edit the space");
		hp.goToMySpaces();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName, true, "TestData/"+filename);
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);

		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		waitForAndGetElement(spaMg.ELEMENT_SPACE_DESCRIPTION.replace("${space}", newName),2000,1);
		waitForElementNotPresent(spaMg.ELEMENT_SPACE_AVATAR_DEFAULT);
	}
	/**
	 *<li> Case ID:121912.</li>
	 *<li> Test Case Name: Delete a space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_DeleteASpace() {
		info("Test 06:Delete a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Delete a space
		 *Step Description: 
			- Login Intranet
			- Click on My space on Admin bar
			- Add new space
			- Go to My Space page,  select the space 
			- Click on Delete Space icon
			- Click on OK button to confirm
		 *Input Data: 

		 *Expected Outcome: 
			- Space is removed. It doesn't display on My space list of user and all spaces list of other user.
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.searchSpace(space,"");
		spaMg.deleteSpace(space,false);

		magAc.signIn(username2,password);
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);

	}
	/**
	 *<li> Case ID:121916.</li>
	 *<li> Test Case Name: Remove application on space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_RemoveApplicationOnSpace() {
		info("Test 02:Remove application on space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = spAppData.getRandomIndexByType(1);
		info("index:"+index);
		String app = spAppData.newApplication.get(index);
		info("app:"+app);
		String category = spAppData.newCategory.get(index);
		info("cate:"+category);
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Remove application on space
		 *Step Description: 
			- Access Space, select Setting tab/Application
			- Click on Add Application, select application and click on "Install" icon to add
			- Click on remove icon beside application

		 *Input Data: 
		 *Expected Outcome: 
			- Application is move out space, name of one is not display on left menu
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		info(" Click on Add Application, select application and click add button");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		setSpaceMg.addApplication(category,app);
		info("Verify that Application is added to space");
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}",app),3000,1);
		setSpaceMg.removeApplication(app);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_MORE_BTN,3000,1);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_APPLICATION_PORTLET.replace("${app}",app),3000,1);

		/*info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}
	/**
	 *<li> Case ID:121917.</li>
	 *<li> Test Case Name: Check access visible/open space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_CheckAccessVisibleOpenSpace() {
		info("Test 08:Check access visible/open space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] arrayRight ={"open"};
		String urlSpace =baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		String mess="You must be a member of the space "+space+" to access this page.";
		info("urlSpace:"+urlSpace);
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Access with visible/open space
		 *Step Description: 
			- Login by john
			- Add new space with access is: visible/open
			-  Logout
			- Login by mary
			- Access the url of an open space that she is not member of via url :
			/portal/g/:spaces:open/open/forum/
		 * Click on Join link

		 *Input Data: 
		 *Expected Outcome: 
			- New space is added successfully
			- A page with Restricted Area title is displayed
			Message is :  You must be a member of the space Open to access this page. [Join
			- Mary joins the space and is redirected to the initially  requested page.
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		magAc.signIn(username2, password);
		driver.get(urlSpace);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_INFO,2000,1).getText().contains(mess);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_JOIN_BTN,2000,1).click();
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_ACTIVITY_STREAM,3000,1);

		/*magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}
	/**
	 *<li> Case ID:121918.</li>
	 *<li> Test Case Name: Check access hidden space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_CheckAccessHiddenSpace() {
		info("Test 09:Check access hidden space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] arrayRight ={"hidden"};
		String urlSpace =baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check hidden space
		 *Step Description: 
			- Login as user1
			- Add new hidden space
			- Logout
			- Login as user2
			- access the url of an hidden space that she is not member of via url :
			/portal/g/:spaces:hidden/hidden/forum/
		 * - Click on [Find Spaces] link
		 *Input Data: 
		 *Expected Outcome: 
			- Add new space successfully
			- A page with 'Space not Found' title is displayed
			Message is :  No space is available at this URL. [Find Spaces]
			- User2 is redirected to Spaces directory page
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		magAc.signIn(username2, password);
		driver.get(urlSpace);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_TITLE,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_INFO,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_FIND_BTN,2000,1).click();
		waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_TAB,3000,1);

		/*magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}
	/**
	 *<li> Case ID:121919.</li>
	 *<li> Test Case Name: Check access visible/close space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_CheckAccessVisibleCloseSpace() {
		info("Test 10:Check access visible/close space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] arrayRight ={"close"};
		String urlSpace =baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		String mess=" You must be invited by an administrator to the space "+space+" to access this page.";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check visible/close space
		 *Step Description: 
			- Login by John
			- Add new space with visible/closed
			- Logout
			- Login by mary
			- Access the url of an closed space that she is not member of via url :
			/portal/g/:spaces:closed/closed/forum/
		 *Input Data: 
		 *Expected Outcome: 
			- Add new space successfully
			- A page with 'Access Denied' title is displayed
			Message is :  You must be invited by an administrator to the Closed space to access this page.

		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);

		magAc.signIn(username2, password);
		driver.get(urlSpace);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_SPACE_DENIED,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO,2000,1).getText()
		.contains(mess);

		/*magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}
	/**
	 *<li> Case ID:121920.</li>
	 *<li> Test Case Name: Check access visible/validation space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_CheckAccessVisibleValidationSpace() {
		info("Test 11:Check access visible/validation space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String urlSpace =baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		String mess="You must be a member of the space "+space+" to access this page.";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Access visible/validation space
		 *Step Description: 
			- Login by john
			- Add new space with visible/validation 
			- Logout
			- Login by mary
			- access the url of a space requiring validation that she is not member of via url :
			/portal/g/:spaces:validation/validation/forum/
			 - Click on [Request to Join] link
		 *Input Data: 
		 *Expected Outcome: 
			- Add new space successfully
			- A page with Restricted Area title is displayed
			Message is :   You must be a member of the space Validation to access this page. [Request to Join]
			- Restricted Area page remains
		 */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);

		magAc.signIn(username2, password);
		driver.get(urlSpace);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_INFO,2000,1).getText().contains(mess);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN,2000,1).click();
		waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE,2000,1);
		waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_ACCESS_INFO,2000,1);
	}
	/**
	 *<li> Case ID:121921.</li>
	 *<li> Test Case Name: Check when user is member of space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_CheckWhenUserIsMemberOfSpace() {
		info("Test 12:Check when user is member of space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check when user is member of space
		 *Step Description: 
		    - Login by user A
			- Add new space
			- Add new page for space/wiki
			- From the list "More", choose the link "Permalink" and copy this link
			- Login by user B is member of space
			- Paste the permalink
		 *Input Data: 
		 *Expected Outcome: 
			- Add space and wiki of space successfully
			- The member of space can view the page created by the manager
		 */ 
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");

		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,1);

		wHome.goToPermalink();
		String perLink=wHome.getPermalink();
		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaMg.searchSpace(space,"");
		spaMg.acceptAInvitation(space);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_PAGE_LEFTBOX.replace("${title}",wiki));
	}
	/**
	 *<li> Case ID:121922.</li>
	 *<li> Test Case Name: Check when user is not member of space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_CheckWhenUserIsNOTMemberOfSpace() {
		info("Test 13:Check when user is not member of space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check when user is not member of space
		 *Step Description: 
		    - Login by user A
		    - Add new space
		    - Add new page for space/wiki
		    - From the list "More", choose the link "Permalink" and copy this link
		    - Login by user B is not member of space
		    - Paste the permalink
		 *Input Data: 
		 *Expected Outcome: 
			- Add space and wiki of space successfully
			- The "Page Not found" is displayed, the user B cannot view the page
		 */ 
		info("Create space 1 and wiki page 1");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Add new wiki page 1 for space 1");
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,1);

		wHome.goToPermalink();
		String perLink=wHome.getPermalink();
		magAc.signIn(username2, password);
		driver.get(perLink);
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGENOTFOUND);
	}

}