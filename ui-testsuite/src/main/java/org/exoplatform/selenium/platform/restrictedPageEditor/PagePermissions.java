package org.exoplatform.selenium.platform.restrictedPageEditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


	public class PagePermissions extends testConfig {
		
	/**
	*<li> Case ID:128140.</li>
	*<li> Test Case Name: Check permission tab name in Page permission.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckPermissionTabNameInPagePermission() {
		info("Test 1: Check permission tab name in Page permission");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties
		*Input Data: 
			
		*Expected Outcome: 
			- Permission Settings tab is renamed to Permissions*/ 
		
		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TAB_NAME.replace("${tabName}", "Permissions"));
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128141.</li>
	*<li> Test Case Name: Check tabs name inside permission tab in Page permission.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckTabsNameInsidePermissionTabInPagePermission() {
		info("Test 2: Check tabs name inside permission tab in Page permission");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission
		*Input Data: 
			
		*Expected Outcome: 
			- Access Permission Settings and Edit Permissions Settings/ are renamed respectively to "Access" and "Edit."*/ 

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		info("Check tab name");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_ACCESS));
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_EDIT));
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128142.</li>
	*<li> Test Case Name: Check new tabs inside permission tab in Page permission.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckNewTabsInsidePermissionTabInPagePermission() {
		info("Test 3: Check new tabs inside permission tab in Page permission");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission
		*Input Data: 
			
		*Expected Outcome: 
			- 2 new tabs are added:Move Apps and Move Containers*/ 

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		info("Check tab name");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));
		
		button.cancel();
		manageLayout.abortPageUpdate();		
 	}

	/**
	*<li> Case ID:128143.</li>
	*<li> Test Case Name: Check Delete portlet icon for not granted user in Page permission.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="error")
	public  void test04_CheckDeletePortletIconForNotGrantedUserInPagePermission() {
		info("Test 4: Check Delete portlet icon for not granted user in Page permission");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Move apps
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the move apps permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		
		info("Set move apps permission - No one can move");
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		info("Save configuration");
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- Move the mouse over the portlet laid in the Page Layout
		*Input Data: 
			
		*Expected Outcome: 
			- The trash icon "Delete portlet" is hidden*/ 

		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");
		
		info("Find portlet");
		mouseOver(manageLayout.ELEMENT_APPLICATION_IN_LAYOUT_PAGE.replace("${name}","Announcement"),true);
		waitForElementNotPresent(manageLayout.ELEMENT_APPLICATION_DELETE_ICON.replace("${name}","Announcement"));
		
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128144.</li>
	*<li> Test Case Name: Check add apps by drag and drop for a granted user in Page permission.</li>
	*<li> Pre-Condition: NOT restrictive permission is defined in a container</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckAddAppsByDragAndDropForAGrantedUserInPagePermission() {
		info("Test 5: Check add apps by drag and drop for a granted user in Page permission");
		String addedGroup="/platform/users";
		String groupPath="Platform/Users";
		
		int index = appLayData.getRandomIndexByType(1);
		String name = appLayData.newTitle.get(index);
		String idName = appLayData.newId.get(index);
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Move apps
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the move apps permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
										.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		}
		
		info("Add permission");
		manageLayout.addPremission(groupPath, "*",addedGroup, 
					manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
					manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- From the composer, drag and drop an app to the page layout
		*Input Data: 
			
		*Expected Outcome: 
			- The app is added to the page layout*/ 
		
		info("Add application");
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");

		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
															pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);
 	}

	/**
	*<li> Case ID:128145.</li>
	*<li> Test Case Name: Check move apps by drag and drop for a granted user in Page permission.</li>
	*<li> Pre-Condition: NOT restrictive permission is defined in a container</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckMoveAppsByDragAndDropForAGrantedUserInPagePermission() {
		info("Test 6: Check move apps by drag and drop for a granted user in Page permission");
		String addedGroup="/platform/users";
		String groupPath="Platform/Users";
		
		int index = appLayData.getRandomIndexByType(1);
		String name = appLayData.newTitle.get(index);
		String idName = appLayData.newId.get(index);
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		
		info("idName = " + idName);
		info("name = " + name);
		
//		String newTitle= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the move apps permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
										.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		}
		
		info("Add permission");
		manageLayout.addPremission(groupPath, "*",addedGroup, 
					manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
					manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- By drag and drop an app in the page layout
		*Input Data: 
			
		*Expected Outcome: 
			- Apps are reorganized in the page layout*/ 

		info("Add application");
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");

		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
															pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);
		
		info("Move application");
		portalManagePages.editPage("Home Page", "");
		pgCreateWiz.moveApplication(name,"Announcement",87);
		
		portalManagePages.editPage("Home Page", "");
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_APPLICATION_PRECEDING_PORTLET
											.replace("${app1}","Announcement")
											.replace("${app2}",name),pgCreateWiz.ELEMENT_APPLICATION_FOLLOWING_PORTLET
											.replace("${app1}","Announcement").replace("${app2}",name));
 	}

	/**
	*<li> Case ID:128146.</li>
	*<li> Test Case Name: Check add apps by drag and drop for a not granted user in Page permission.</li>
	*<li> Pre-Condition: NOT restrictive permission is defined in a container</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckAddAppsByDragAndDropForANotGrantedUserInPagePermission() {
		info("Test 7: Check add apps by drag and drop for a not granted user in Page permission");
		
		String name = "Organization";
		String idName = "Administration/OrganizationPortlet";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Move apps
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the move apps permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
										.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
		info("Set no one can move app");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		}
		
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- From the composer, drag and drop an app to the page layout
		*Input Data: 
			
		*Expected Outcome: 
			- The user cannot move the app to the Page Layout*/ 
		info("Add application");
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");

		pgCreateWiz.addAppWithoutPermission("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION
															.replace("${name}",idName),
															pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);
 	}

	/**
	*<li> Case ID:128147.</li>
	*<li> Test Case Name: Check Delete container icon for not granted user in Page permission.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckDeleteContainerIconForNotGrantedUserInPagePermission() {
		info("Test 8: Check Delete container icon for not granted user in Page permission");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		
		String containerId = "top-social-container";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserAdmin(username2);
		userAndGroup.addUserContentManagement(username1);
		userAndGroup.addUserContentManagement(username2);
		manageLoginOut.signIn(username1,password);
						
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
			
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Containers app
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the containers apps permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
									.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
		info("Set no one can move app");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		}
		
		
		info("Add permission");
		manageLayout.addPremission(groupPath, "editor",addedGroup, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		manageLoginOut.signOut();
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- Move the mouse over the container laid in the Page Layout
		*Input Data: 
			
		*Expected Outcome: 
			- The trash icon "Delete portlet" is hidden*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit page");
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");
		pgCreateWiz.deleteContainerWithoutPermission(containerId);
 	}

	/**
	*<li> Case ID:128148.</li>
	*<li> Test Case Name: Check add container by drag and drop for a granted user in Page permission.</li>
	*<li> Pre-Condition: NOT restrictive permission is defined in a container</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckAddContainerByDragAndDropForAGrantedUserInPagePermission() {
		info("Test 9: Check add container by drag and drop for a granted user in Page permission");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		
		String containerId = contaiData.getContainerIdByIndex(0);
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserAdmin(username2);
		userAndGroup.addUserContentManagement(username1);
		userAndGroup.addUserContentManagement(username2);
		manageLoginOut.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Move containers
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the move containers permission*/

		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
									.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
		info("Set no one can move app");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		}
		
		
		info("Add permission");
		manageLayout.addPremission(groupPath, "manager",addedGroup, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		manageLoginOut.signOut();		
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- From the composer, drag and drop a container to the page layout
		*Input Data: 
			
		*Expected Outcome: 
			- The container is added to the page layout*/ 
		
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit page");
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Home Page", "");		
		pgCreateWiz.addContainer(containerId, true);
 	}

	/**
	*<li> Case ID:128149.</li>
	*<li> Test Case Name: Check move containers by drag and drop for a granted user in Page permission.</li>
	*<li> Pre-Condition: NOT restrictive permission is defined in a container</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckMoveContainersByDragAndDropForAGrantedUserInPagePermission() {
		info("Test 10 Check move containers by drag and drop for a granted user in Page permission");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserAdmin(username2);
		userAndGroup.addUserContentManagement(username1);
		userAndGroup.addUserContentManagement(username2);
		manageLoginOut.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab > Containers apps
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the containers apps permission*/

		homepage.goToConnections();
		
		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
									.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
		info("Set no one can move app");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		}
		
		
		info("Add permission");
		manageLayout.addPremission(groupPath, "manager",addedGroup, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
					manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);
		manageLayout.saveChangesPropertiesPopup();
		manageLayout.saveChangesPageLayout();
		
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go to Edit > Portal > Page > Edit Page
			- By drag and drop a Container in the page layout
		*Input Data: 
			
		*Expected Outcome: 
			User can move container*/ 

		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit page");
		navigationToolbar.goToPotalPages();
		
		info("Add container");
		portalManagePages.editPage("Connections", "");	
		pgCreateWiz.addContainer("oneRow");
		Utils.pause(3000);
		
		info("Move container");
		portalManagePages.editPage("Connections", "");	
		pgCreateWiz.moveContainer("",pgCreateWiz.ELEMENT_CONTAINER_HOLDER_MOVE,
								pgCreateWiz.ELEMENT_PORTLET,87);
	
		info("Verify that the container is changed the position");
		portalManagePages.editPage("Connections", "");	
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_CONTAINER_PRECEDING_PORTLET,
														pgCreateWiz.ELEMENT_CONTAINER_FOLLOWING_PORTLET);
	
 	}

	/**
	*<li> Case ID:128150.</li>
	*<li> Test Case Name: Check Move Apps and Move Containers buttons for Group page.</li>
	*<li> Pre-Condition: The user have the membership defined by UserACL portal.administrator.mstype in the group OR portal.administrator.groups</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckMoveAppsAndMoveContainersButtonsForGroupPage() {
		info("Test 11 Check Move Apps and Move Containers buttons for Group page");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
			- Go to Administration > Portal > Pages
			- Select Type "Group"
			- Search
			- From a Group pages":( Sites explorer page, Branding,...), click on the Edit icon
		*Input Data: 
			
		*Expected Outcome: 
			- The Edit page layout is displayed*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("New Staff", "group");
		waitForAndGetElement(manageLayout.ELEMENT_PAGE_EDIT_LAYOUT_PROPERITES_BTN);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- From Edit Layout, choose View Page Properties > Permissions
		*Input Data: 
			
		*Expected Outcome: 
			- Buttons "Move containers" and "Move apps" are displayed*/ 

		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		info("Check tab name");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));
		
		button.cancel();
		manageLayout.abortPageUpdate();		
 	}

	/**
	*<li> Case ID:128151.</li>
	*<li> Test Case Name: Check Move Apps and Move Containers buttons for Portal page.</li>
	*<li> Pre-Condition: The user have the membership defined by UserACL portal.administrator.mstype in the group OR portal.administrator.groups</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckMoveAppsAndMoveContainersButtonsForPortalPage() {
		info("Test 12 Check Move Apps and Move Containers buttons for Portal page");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navigationToolbar.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		userAndGroup.addUserContentManagement(username1);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
			- Go to Administration > Portal > Pages
			- Select Type "Portal"
			- Search
			- From a Portal pages":( Register, People Directory,...), click on the Edit icon
		*Input Data: 
			
		*Expected Outcome: 
			- The page layout is displayed*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		navigationToolbar.goToPotalPages();
		portalManagePages.editPage("Register", "portal");
		waitForAndGetElement(manageLayout.ELEMENT_PAGE_EDIT_LAYOUT_PROPERITES_BTN);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go toEdit Page > Edit Layout > View Page Properties
		*Input Data: 
			
		*Expected Outcome: 
			- Buttons "Move containers" and "Move apps" are displayed*/ 

		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		info("Check tab name");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));
		
		button.cancel();
		manageLayout.abortPageUpdate();				
 	}
}