package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class SOC_Space_ApplicationManagement extends SOC_TestConfig{
	/**
	*<li> Case ID:122428.</li>
	*<li> Test Case Name: Remove space settings portlet.</li>
	*<li> Pre-Condition: A space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_RemoveSpaceSettingsPortlet() {
		info("Test 2: Remove space settings portlet");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		/*Step Number: 1
		*Step Name: Step 1: Remove space settings portlet from the space
		*Step Description: 
			- Manager access space
			- Go to Space Settings
			- Select [Applications] tab
			- Try to remove Space Settings porlet
		*Input Data: 
			
		*Expected Outcome: 
			There isn't [Remove] button of Space Settings portlet. User can't remove this portlet*/ 
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		waitForElementNotPresent(setSpaceMg.ELEMENT_DELETE_APP_FROM_TOPBAR.replace("{$application}","Space Settings"));
 	}

	/**
	*<li> Case ID:122429.</li>
	*<li> Test Case Name: Display order of space's applications on tool bar.</li>
	*<li> Pre-Condition: a space is createdDefault applications for a space are not impacted by this specification.</li>
	*<li> Post-Condition: </li>
	* FQA is disable because of not installed Answer add-on
	*/
	@Test
	public  void test01_DisplayOrderOfSpacesApplicationsOnToolBar() {
		info("Test 1: Display order of space's applications on tool bar");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		/*Step Number: 1
		*Step Name: Step 1: Check order of space application on tool bar
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- The list of applications of space are displayed in the following order:* Activity Stream* Forum* Wiki* Documents* Agenda* Space Settings* FAQ* Member*/ 
		info("Get list of applications");
		
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);

		spaHome.goToSpace(space);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
	}

	/**
	*<li> Case ID:122430.</li>
	*<li> Test Case Name: Add new application into the space.</li>
	*<li> Pre-Condition: A space is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:122435.</li>
	*<li> Test Case Name: Display a "Space Management" application for administrator.</li>
	*<li> Pre-Condition: a space is createduser is the administrator of space</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:122459.</li>
	*<li> Test Case Name: Remove application of space.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_05_07_AddNewApplicationIntoTheSpace() {
		info("Test 3: Add new application into the space");
		int index = spAppData.getRandomIndexByType(1);
		String app = spAppData.newApplication.get(index);
		String category = spAppData.newCategory.get(index);
		String tab = spAppData.newTab.get(index);
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		/*Step Number: 1
		*Step Name: Step 1: Access Space Application management
		*Step Description: 
			- Login by user who is manager of space
			- Go to Space Settings
			- Select Application tab
		*Input Data: 
			
		*Expected Outcome: 
			- All default applications are displayed*/
		info("goto Space Setting>Application");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		
		/*Step number: 2
		*Step Name: Step 2: Show list applications to add
		*Step Description: 
			On Application tab, click Add Application button
		*Input Data: 
			
		*Expected Outcome: 
			Show form which contains all applications which user has right to use.*/
		
		/*Step number: 3
		*Step Name: Step 3: Add applications into the space
		*Step Description: 
			- Select a application and click on Add button to install application to the space
		*Input Data: 
			
		*Expected Outcome: 
			- Add new application successfully
			- This application is added into application list
			- This application is added into space navigation tool bar*/ 
		info("add more application");
		setSpaceMg.addApplication(category, app);
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}", app));
		mouseOverAndClick(spaHome.ELEMENT_SPACE_MENU_MORE);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_TAB.replace("${tab}", tab));
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- From space's settings, remove an application
		*Input Data: 
			
		*Expected Outcome: 
			- The application is removed from the space's toolbar*/ 
		info("remove application");
		setSpaceMg.deleteApplications(app);
		waitForElementNotPresent(spaHome.ELEMENT_SPACE_MENU_TAB.replace("${tab}", tab));
		waitForElementNotPresent(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}", app));
	}

	/**
	*<li> Case ID:122431.</li>
	*<li> Test Case Name: Check application list on space when user has access permission.</li>
	*<li> Pre-Condition: A space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test04_CheckApplicationListOnSpaceWhenUserHasAccessPermission() {
		info("Test 4: Check application list on space when user has access permission");
        int index = appLayData.getRandomIndexByType(1);
		String portlet = appLayData.newTitle.get(index);
		String category = appLayData.newCategory.get(index);
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String urlSpace=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		
		/*Step Number: 1
		*Step Name: Step 1: Add permission for category and its gadgets to space group
		*Step Description: 
			- Login as admin
			- Go to application registry page
			- Select a category, click Edit icon
			- Add permission for this categorywith space group which has just created at step 1 and membership is {*}
		*Input Data: 
			
		*Expected Outcome: 
			- Category permission is created successfully*/
		navTool.goToApplication();
		appReg.editCategory(category, "", "", "Spaces/"+space,"*");
		appReg.editPortletPermission(category,portlet,"Spaces/"+space,"*");	
		
		/*Step number: 2
		*Step Name: Step 2: Check application on List of add application
		*Step Description: 
			- Login as manager of space
			- Access space
			- Go to Space Settings
			- Go to Application
			- Click Add Application
		*Input Data: 
			
		*Expected Outcome: 
			- Space Settings form is appeared
			- Show all category which user belongs to space group has right to access*/ 
		info("goto Space Setting>Application");
		driver.get(urlSpace);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		
		info("click on add application");
		click(setSpaceMg.ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN);
		Utils.pause(2000);
		waitForAndGetElement(setSpaceMg.ELEMENT_ADD_APPLICATION_POPUP_TITLE,2000,0);
		waitForAndGetElement(setSpaceMg.ELEMENT_ADD_APPLICATION_POPUP_CATEGOGY.replace("${category}",category));
		
		info("click on new added category");
		click(setSpaceMg.ELEMENT_ADD_APPLICATION_POPUP_CATEGOGY.replace("${category}",category));
		waitForAndGetElement(setSpaceMg.ELEMENT_ADD_APPLICATION_POPUP_APPLICATION_ADD_BTN.replace("${app}",portlet));
	}

	/**
	*<li> Case ID:122458.</li>
	*<li> Test Case Name: Display a style of space's applications in tool bar.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	* cannot automate
	*/
	@Test(groups="pending")
	public  void test06_DisplayAStyleOfSpacesApplicationsInToolBar() {
		info("Test 6: Display a style of space's applications in tool bar");
		/*Step Number: 1
		*Step Name: Step 1: Check style of space application
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- The list of applications of space are displayed
			- The list of application is left aligned*/ 

 	}

	/**
	*<li> Case ID:122463.</li>
	*<li> Test Case Name: Display a style of selected item in space's tool bar.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:122464.</li>
	*<li> Test Case Name: Display a hover under item of space's toolbar.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_09_DisplayAStyleOfSelectedItemInSpacesToolBar() {
		info("Test 8: Display a style of selected item in space's tool bar");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		/*Step Number: 1
		*Step Name: Step 1: Open a space
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- The list of applications of space are displayed*/
		
		/*Step number: 2
		*Step Name: Step 2: Select application
		*Step Description: 
			- Select an application from the tool bar
		*Input Data: 
			
		*Expected Outcome: 
			- Application is displayed
			- The icon of application is displayed with another style*/ 
		info("select wiki application");
		spaHome.goToWikiTab();
		waitForAndGetElement(spaHome.ELEMENT_SPACE_WIKI_TAB_ACTIVE,2000,1);
		
		/*Step number: 3
		*Step Name: Step 3: Mouse over item
		*Step Description: 
			- Move the mouse over the item
		*Input Data: 
			
		*Expected Outcome: 
			- An UI hover is displayed under the item*/ 
		waitForAndGetElement(spaHome.ELEMENT_SPACE_WIKI_TAB_CONTENT,2000,1);
	}

	/**
	*<li> Case ID:122465.</li>
	*<li> Test Case Name: Add an application to space's toolbar from "More" menu.</li>
	*<li> Pre-Condition: a space is created with more than 10 applications the size of the browser allows to display X items</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_AddAnApplicationToSpacesToolbarFromMoreMenu() {
		info("Test 10 Add an application to space's toolbar from More menu");
		int index1 = spAppData.getRandomIndexByType(1);
		String app1 = spAppData.newApplication.get(index1);
		String category1 = spAppData.newCategory.get(index1);
		String tab1 = spAppData.newTab.get(index1);
		int index2 = spAppData.getRandomIndexByType(1);
		String app2 = spAppData.newApplication.get(index2);
		String category2 = spAppData.newCategory.get(index2);
		String tab2 = spAppData.newTab.get(index2);
		
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentSpace=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		
		hp.goToMySpaces();
		info("create new space");
		spaMg.addNewSpaceSimple(space,contentSpace,60000);
		/*Step Number: 1
		*Step Name: Step1: Open a space
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- X
			-1 applications are displayed
			- The menu "More is displayed*/
		info("goto Space Setting>Application");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToApplicationTab();
		
		info("add more application");
		setSpaceMg.addApplication(category1, app1);
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}", app1));
		setSpaceMg.addApplication(category2, app2);
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}", app2));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_MORE);
		
		/*Step number: 2
		*Step Name: Step2: Click More button
		*Step Description: 
			- Click on the button "More"
		*Input Data: 
			
		*Expected Outcome: 
			- A vertical pop up is displayed to show other applications*/
		mouseOverAndClick(spaHome.ELEMENT_SPACE_MENU_MORE);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_TAB.replace("${tab}", tab1));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_TAB.replace("${tab}", tab2));
		
		/*Step number: 3
		*Step Name: Step 3: Select Application
		*Step Description: 
			- Select an application from the list
		*Input Data: 
			
		*Expected Outcome: 
			- The application is added to the space's toolbar as (X+1)th item
			- The "More" button is pushed to the right of the toolbar
			- The added application is removed from the vertical pop up of the menu "More"*/ 
		click(spaHome.ELEMENT_SPACE_MENU_TAB.replace("${tab}", tab1));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",tab1));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER.replace("${number}","7").replace("${tab}","More"));
 	}}