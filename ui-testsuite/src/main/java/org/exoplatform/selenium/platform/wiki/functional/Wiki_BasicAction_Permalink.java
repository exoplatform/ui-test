package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.wiki.WikiPermission.permissionType;
import org.testng.annotations.*;


	public class Wiki_BasicAction_Permalink extends WIKI_TestConfig{

	/**
	*<li> Case ID:139492.</li>
	*<li> Test Case Name: Restrict button for granted user.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_RestrictButtonForGrantedUser() {
		info("Test 1: Restrict button for granted user");
		/*Step Number: 1
		*Step Name: Step 1; Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Input Data: 
			
		*Expected Outcome: 
			The page is public*/
		
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Make public the page");
		//wHome.restrictedPage(true);
		wHome.publicPage(true);
		
		info("Verify that the page is published");
		wValidate.verifyPublishedPage();

		/*Step number: 2
		*Step Name: Step 2: Open Page permission
		*Step Description: 
			From the list [More], choose the link [Page permission]
		*Input Data: 
			
		*Expected Outcome: 
			The "Page permission" is displayed*/
		info("Go to Page permission");
		wHome.goToPermissions();

		/*Step number: 3
		*Step Name: Step 3: Set permission for page
		*Step Description: 
			Cick on the icon [Select a User] Check to the user to grantClick on the button "Add"
		*Input Data: 
			
		*Expected Outcome: 
			The user is added to the list with "View Page" checked and the "Page edit" box isn't selected*/
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wValidate.verifyEditPermisison(arrayUsers.get(0),false);
		wValidate.verifyViewPermisison(arrayUsers.get(0),true);
		
		/*Step number: 4
		*Step Name: Step 4: Edit permission
		*Step Description: 
			Select the icon [Edit page]Click on the button [Save]
		*Input Data: 
			
		*Expected Outcome: 
			Edit permission successfully*/
		wPermission.selectPermission(arrayUsers.get(0),permissionType.Edit_Pages);
        wPermission.savePermisison();
        
		/*Step number: 5
		*Step Name: Step 5: Copy "permalink"
		*Step Description: 
			- Select [More] 
			-> Choose [Permalink]
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 
			
		*Expected Outcome: 
			Copy link successfully*/
        wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();

		/*Step number: 6
		*Step Name: Step 6: View permalink
		*Step Description: 
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
		*Input Data: 
			
		*Expected Outcome: 
			The page is dispalyed*/
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(1000);
        wValidate.verifyPageContent(title, content);
        
		/*Step number: 7
		*Step Name: Step 7: Check "Permalink" link
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 
			
		*Expected Outcome: 
			The pop up "Permalink" is displayed" and the button "Restricted" is played*/ 
        wHome.goToPermalink();
        waitForAndGetElement(wHome.ELEMENT_MAKE_RESTRICT_BUTTON);
        wHome.closePermalinkPopup();

 	}

	/**
	*<li> Case ID:139493.</li>
	*<li> Test Case Name: Restrict button for granted user without "Edit page" permission.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_RestrictButtonForGrantedUserWithoutEditPagePermission() {
		info("Test 2: Restrict button for granted user without Edit page permission");
		/*Step Number: 1
		*Step Name: Step 1: Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Input Data: 
			
		*Expected Outcome: 
			The page is public*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Make public the page");
		//wHome.restrictedPage(true);
		wHome.publicPage(true);
		
		info("Verify that the page is published");
		wValidate.verifyPublishedPage();

		/*Step number: 2
		*Step Name: Step 2: Open Page permission
		*Step Description: 
			From the list [More], choose the link [Page permission]
		*Input Data: 
			
		*Expected Outcome: 
			The "Page permission" is displayed*/
		info("Go to Page permission");
		wHome.goToPermissions();

		/*Step number: 3
		*Step Name: Step 3: Set permission for page
		*Step Description: 
			Cick on the icon "Select a User" Check to the user to grantClick on the button "Add"
		*Input Data: 
			
		*Expected Outcome: 
			The user is added to the list with "View Page" and the "Page edit" box isn't selected*/
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wValidate.verifyEditPermisison(arrayUsers.get(0),false);
		wValidate.verifyViewPermisison(arrayUsers.get(0),true);
		wPermission.savePermisison();
		
		/*Step number: 4
		*Step Name: Step 4: Copy "permalink"
		*Step Description: 
			- Select [More] 
			-> Choose [Permalink]
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 
			
		*Expected Outcome: 
			Copy link successfully*/
		wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();

		/*Step number: 5
		*Step Name: Step 5: View permalink
		*Step Description: 
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with the granted user
		*Input Data: 
			
		*Expected Outcome: 
			The page is dispalyed*/
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(1000);
        wValidate.verifyPageContent(title, content);
       

		/*Step number: 6
		*Step Name: Step 6: Check "Permalink" link
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 
			
		*Expected Outcome: 
			The pop up "Permalink" is displayed and the button "Restricted" isn't displayed*/
        wHome.goToPermalink();
        waitForElementNotPresent(wHome.ELEMENT_MAKE_RESTRICT_BUTTON);
        wHome.closePermalinkPopup();

 	}

	/**
	*<li> Case ID:139494.</li>
	*<li> Test Case Name: Restrict button for no granted user.</li>
	*<li> Pre-Condition: The parent of the page created is with a public status 
	- The user no granted isn't also the creator the page and the Space manager</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_RestrictButtonForNoGrantedUser() {
		info("Test 3: Restrict button for no granted user");
		/*Step Number: 1
		*Step Name: Step 1: Add public page
		*Step Description: 
			Go to Intranet/WikiCreate a public page
		*Input Data: 
			
		*Expected Outcome: 
			The page is public*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Make public the page");
		//wHome.restrictedPage(true);
		wHome.publicPage(true);
		
		info("Verify that the page is published");
		wValidate.verifyPublishedPage();

		/*Step number: 2
		*Step Name: Step 2: Copy "permalink"
		*Step Description: 
			- Select More 
			-> Choose Permalink
			- From the page "Permalink", Copy the url of the page to share
		*Input Data: 
			
		*Expected Outcome: 
			Copy link successfully*/
		wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();

		/*Step number: 3
		*Step Name: Step 3: View permalink
		*Step Description: 
			- Login with other user
			- Paste the link in a BrowserClick Enter from the Keyboard and connect with a user
		*Input Data: 
			
		*Expected Outcome: 
			The page is dispalyed*/
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(1000);
        wValidate.verifyPageContent(title, content);

		/*Step number: 4
		*Step Name: Step 4: Check "Permalink" link
		*Step Description: 
			From the list "More", choose the link "Permalink"
		*Input Data: 
			
		*Expected Outcome: 
			- The pop up " Permalink" is displayed 
			- the button "Restrict" isn't displayed*/ 
        wHome.goToPermalink();
        waitForElementNotPresent(wHome.ELEMENT_MAKE_RESTRICT_BUTTON);
        wHome.closePermalinkPopup();

 	}

	/**
	*<li> Case ID:139513.</li>
	*<li> Test Case Name: Access to page by an user who is not member of the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_AccessToPageByAnUserWhoIsNotMemberOfTheSpace() {
		info("Test 4: Access to page by an user who is not member of the space");
		/*Step Number: 1
		*Step Name: Step 1: Add new page for space when set permission for page
		*Step Description: 
			- Login by admin
			- Add new space
			- Go to space/wiki page
			- Add new page 
			- Set permission for page: remove any permission
			- Select [More] 
			-> choose [Permalink]
			- Copy the link
		*Input Data: 
			
		*Expected Outcome: 
			- Add new page have permission successfully
			- Copy link successfully*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space,6000);
		Utils.pause(2000);
		
		info("Create a wiki page");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		
		info("Copy permalink");
		wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();
        
		/*Step number: 2
		*Step Name: Step 2: Check permalink when user is not member of space
		*Step Description: 
			- From the list [More], choose the link [Permalink]
			- Copy the link
			- Connect with User B, not a member in the space
			- Paste the permalink
			- Click [Enter] from the keyboard
		*Input Data: 
			
		*Expected Outcome: 
			The "Page Not found" is displayed, the user B cannot view the page*/ 
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(1000);
        wValidate.verifyPageNotFound();

 	}

	/**
	*<li> Case ID:139514.</li>
	*<li> Test Case Name: Access to Page permission by the page creator.</li>
	*<li> Pre-Condition: the user is the space manager and the creator of the page</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_AccessToPagePermissionByThePageCreator() {
		info("Test 5: Access to Page permission by the page creator");
		/*Step Number: 1
		*Step Name: Step 1: Add wiki page for space
		*Step Description: 
			- Login with user A is space manager
			- Go to Space/wiki
			- Create a page
		*Input Data: 
			
		*Expected Outcome: 
			a page is created by the manager of the space*/
		
		info("Create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space,6000);
		Utils.pause(2000);
		
		info("Create a wiki page");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		
		
		/*Step number: 2
		*Step Name: Step 2: Check show Page Permission
		*Step Description: 
			From the list [More], choose the link [Permalink]
		*Input Data: 
			
		*Expected Outcome: 
			The pop up "Permalink" is displayedThe button "Manage permission" is displayed*/ 
		info("Verify that The button 'Manage permission' is displayed");
		wHome.goToPermalink();
		waitForAndGetElement(wHome.ELEMENT_MANAGER_PERMISSION_BTN);
        wHome.closePermalinkPopup();


 	}

	/**
	*<li> Case ID:139516.</li>
	*<li> Test Case Name: Access to space setting by a page creator.</li>
	*<li> Pre-Condition: User B is member in the spaceUser B is creator of the pageUser B isn't an admin in the space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_AccessToSpaceSettingByAPageCreator() {
		info("Test 6: Access to space setting by a page creator");
		/*Step Number: 1
		*Step Name: Step 1: Add new space
		*Step Description: 
			- Login with user A ( ex: john)
			- Create new space with name is "test" ( user A is space manager)
			- Go to Space/wiki
			- Create a page
		*Input Data: 
			
		*Expected Outcome: 
			A wiki's page is created by the manager of the space*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space,6000);
		Utils.pause(2000);
		
		info("Create a wiki page");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);

		/*Step number: 2
		*Step Name: Step 2: Add member into space
		*Step Description: 
			- Go to Settings
			- Choose member tab
			- Add user B ( ex: mary) is member of space
		*Input Data: 
			
		*Expected Outcome: 
			Add user successfully*/
		info("John invites User to the space");
		hp.goToSpecificSpace(space);
		spaHome.goToSpaceSettingTab();
		spSettingMg.goToMemberTab();
		spSettingMg.inviteUser(arrayUsers.get(0),true,arrayUsers.get(0));
		
		/*Step number: 3
		*Step Name: Step 3: Login by user B to accept is member
		*Step Description: 
			- Login by mary
			- Click join a space
			- Select space "test" and click accept
		*Input Data: 
			
		*Expected Outcome: 
			Mary is member of space*/
		info("User accepted to become a member of the space");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		hp.goToAllSpace();
		spaMg.acceptAInvitation(space);

		/*Step number: 4
		*Step Name: Step 4: Access to space setting by member of space
		*Step Description: 
			- Go to Space/Wiki
			- User B, member of the space, create a page in the space
		*Input Data: 
			
		*Expected Outcome: 
			- A page wiki is created in the space
			- User B has not access to the space setting for the wiki application*/ 
		info("Create a wiki page");
		String title1= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title1, content1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title1);
		
		info("User cannot see Space setting tab");
		waitForElementNotPresent(spaHome.ELEMENT_SPACE_SPACE_SETTINGS);
 	}

	/**
	*<li> Case ID:139517.</li>
	*<li> Test Case Name: Display ancestor Restricted page in left tree panel.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_DisplayAncestorRestrictedPageInLeftTreePanel() {
		info("Test 7: Display ancestor Restricted page in left tree panel");
		/*Step Number: 1
		*Step Name: Step 1: Create a page by user A
		*Step Description: 
			- Connect to Intranet/Wiki with UserA
			- Create a page: Page 1
		*Input Data: 
			
		*Expected Outcome: 
			The Page 1 is created*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		/*Step number: 2
		*Step Name: Step 2: Set permission for Page 1
		*Step Description: 
			- From the list [More], choose [Page Permission]
			- Remove 'any' permission
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			Set permission for page successful*/
		info("Remove any permission");
		wHome.goToPermissions();
		wPermission.deletePermission("any");
		wPermission.savePermisison();

		/*Step number: 3
		*Step Name: Step 2: Add sub
		-page
		*Step Description: 
			- Add a sub page: Page 1.1
			- Add View permission to User B
		*Input Data: 
			
		*Expected Outcome: 
			The Page 1.1 is created*/
		info("Add sub page");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(title);
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title1, content1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title1);
		
		info("Add View permission to User B");
		wHome.goToPermissions();
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wValidate.verifyEditPermisison(arrayUsers.get(0),false);
		wValidate.verifyViewPermisison(arrayUsers.get(0),true);
		wPermission.savePermisison();

		/*Step number: 4
		*Step Name: step 3: Copy permalink
		*Step Description: 
			- Open Page 1.1
			- From the list [More], choose the link [Permalink] copy the link to share
		*Input Data: 
			
		*Expected Outcome: 
			Copy permalink successfully*/
		info("Copy permalink");
		wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();

		/*Step number: 5
		*Step Name: Step 3: Check permalink for user B for sub
		-page
		*Step Description: 
			- Connect with User B
			-Paste the link in a Browser
			- Click Enter from the Keyboard
		*Input Data: 
			
		*Expected Outcome: 
			- In the left tree panel the page 1 is labelled Restricted in italic. 
			- It has a tooltip : this page is restricted, you don't have permissions to view it. 
			- The restricted ancestors are not clickable.*/ 
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(2000);
        wValidate.verifyRestrictedPageHasChildPage();

 	}

	/**
	*<li> Case ID:139518.</li>
	*<li> Test Case Name: Display descendant Restricted page in left tree panel.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_DisplayDescendantRestrictedPageInLeftTreePanel() {
		info("Test 8: Display descendant Restricted page in left tree panel");
		/*Step Number: 1
		*Step Name: Step 1: Add new page is public
		*Step Description: 
			- Connect to Intranet/Wiki with User A
			- Create a page is public: Page 1
		*Input Data: 
			
		*Expected Outcome: 
			The page 1 is public*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Make public the page");
		wHome.restrictedPage(true);
		
		info("Verify that the page is published");
		wValidate.verifyPublishedPage();

		/*Step number: 2
		*Step Name: Step 2: Add sub
		-page is restricted
		*Step Description: 
			- Add a sub page: Page 1.1
			- From the list [More], choose [Permalink]
			- Click on the button "Restricted"
		*Input Data: 
			
		*Expected Outcome: 
			The page 1.1 is restricted*/
		info("Add sub page");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(title);
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title1, content1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title1);
		
		info("Restricted the sub page");
		wHome.goToPermalink();
		wHome.restrictedPage();
		wValidate.verifyRestrictedPage();

		/*Step number: 3
		*Step Name: Step 3: Copy permalink
		*Step Description: 
			- Copy the link to share
		*Input Data: 
			
		*Expected Outcome: 
			Copy link successfully*/
		info("Copy permalink");
		wHome.goToPermalink();
        String permalink = wHome.getPermalink();
        wHome.closePermalinkPopup();

		/*Step number: 4
		*Step Name: Step 4: View permalink with user does not have permission
		*Step Description: 
			- Connect with the User B
			- Open the page 1.1
		*Input Data: 
			
		*Expected Outcome: 
			- The Page not found message is displayed*/
        magAc.signOut();
        driver.get(permalink);
        magAc.signIn(arrayUsers.get(0), password);
        Utils.pause(2000);
        wValidate.verifyPageNotFound();

		/*Step number: 5
		*Step Name: Step 5: Check on wiki home
		*Step Description: 
			Back to Intranet/Wiki
		*Input Data: 
			
		*Expected Outcome: 
			- In the left tree panel, the page 1.1 isn't displayed*/ 
        hp.goToWiki();
        wValidate.verifyNotTitleWikiPage(title1);

 	}

	/**
	*<li> Case ID:139554.</li>
	*<li> Test Case Name: Create a wiki page in a Space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CreateAWikiPageInASpace() {
		info("Test 9: Create a wiki page in a Space");
		/*Step Number: 1
		*Step Name: Step1: Add new page for wiki of space
		*Step Description: 
			- Select a space
			- Go to Space/wiki
			- Create a page
		*Input Data: 
			
		*Expected Outcome: 
			The page is addedIn the buttom of the page is displayed to "Restricted"*/ 
		info("Create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space,6000);
		Utils.pause(2000);
		
		info("Create a wiki page");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		
		info("Verify that the page is restricted");
		wValidate.verifyRestrictedPage();

 	}

	/**
	*<li> Case ID:139556.</li>
	*<li> Test Case Name: Permission Indicator is refreshed when changing Public/Restrict.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_PermissionIndicatorIsRefreshedWhenChangingPublicRestrict() {
		info("Test 10 Permission Indicator is refreshed when changing Public/Restrict");
		/*Step Number: 1
		*Step Name: - Open [Permalink] Dialog
		*Step Description: 
			- Goto Wiki
			- Create a new wiki page
			- Click [More]
			->[Permalink]
		*Input Data: 
			
		*Expected Outcome: 
			[Permalink] dialog is displayed*/
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		

		/*Step number: 2
		*Step Name: - Make the page public/restrict
		*Step Description: 
			- Click on button [Make Public] or [Retstrict] on [Permalink] dialog
		*Input Data: 
			
		*Expected Outcome: 
			the Permission Indicator is refreshed accordingly.*/ 
		info("Make public the page");
		wHome.restrictedPage(true);
		
		info("Verify that the page is published");
		wValidate.verifyPublishedPage();
		
		info("Make restricted the page");
		wHome.restrictedPage();
		
		info("Verify that the page is restricted");
		wValidate.verifyRestrictedPage();

 	}}