package org.exoplatform.selenium.platform.restrictedPageEditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.Test;


	public class SitePermissions extends testConfig{
	/**
	*<li> Case ID:128128.</li>
	*<li> Test Case Name: Check permission tab name in Site's Config.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 8)
	public  void test01_CheckPermissionTabNameInSitesConfig() {
		info("Test 1: Check permission tab name in Site's Config");
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
		*Step Name: Step 1: Open Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator 
			- Go to Edit -> Site -> Layout-> Site's Config
		*Input Data: 
			
		*Expected Outcome: 
			- Permission Settings tab is renamed to "Permissions"*/ 
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		waitForAndGetElement(pgCreateWiz.ELEMENT_PERMISSION_TAB);
		
 	}

	/**
	*<li> Case ID:128129.</li>
	*<li> Test Case Name: Check sub-tabs inside Permissions tab in Site's Config.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 5)
	public  void test02_CheckSubtabsInsidePermissionsTabInSitesConfig() {
		info("Test 2: Check sub-tabs inside Permissions tab in Site's Config");
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
		*Step Name: Step 1: Open Permission tab in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout-> Site's Config -> Permission
		*Input Data: 
			
		*Expected Outcome: 
			- Access Permission Settings is renamed to "Access"
			- Edit Permissions Settings ís renamed to "Edit"
			- 2 new tabs are addedMove Apps and Move Containers*/ 
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Check Access tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_ACCESS));
		
		info("Check Edit tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_EDIT));
		
		info("Check Move apps tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		
		info("Check Move containers tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));
	}

	/**
	*<li> Case ID:128131.</li>
	*<li> Test Case Name: Check Delete portlet icon for not granted user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 0)
	public  void test03_CheckDeletePortletIconForNotGrantedUser() {
		info("Test 3: Check Delete portlet icon for not granted user");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "editor";
				
		String name = "Application Registry";
		String idName = "Administration/ApplicationRegistryPortlet";
		
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
		*Step Name: Step 1: Open Move Apps permission settings
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit > Site > Layout > Site's Config > Permission > Move Apps
			- Do not grant Move Apps permission for User in this site
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the move apps permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();		
		info("Add application");
		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
				pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_HOME);	
		
		info("Check added applicaiton");
		navigationToolbar.goToEditSiteLayout();		
		Utils.pause(3000);
		mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "Application Registry"), true);
		waitForAndGetElement(pgCreateWiz.ELEMENT_APPLICATION_DELETE_ICON.replace("${name}", name), 2000,1);
				
		
		info("Set permission");
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move App tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Check "Delete portlet" icon
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit > Sites > Edit Layout
			- Move the mouse over the portlet in the Site Layout
			- Note: + This porlet is not in any container
					+ If you want to forbid User A to delete portlet in any container, 
					you have to not grant Move Apps permission for User A in this container
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete portlet" icon is hidden*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "Application Registry"), true);
		waitForElementNotPresent(pgCreateWiz.ELEMENT_APPLICATION_DELETE_ICON.replace("${name}", name));
		
 	}

	/**
	*<li> Case ID:128132.</li>
	*<li> Test Case Name: Check add apps by drag and drop action for a granted user.</li>
	*<li> Pre-Condition: NO restrictive permission is defined in a container for the granted user</li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 9)
	public  void test04_CheckAddAppsByDragAndDropActionForAGrantedUser() {
		info("Test 4: Check add apps by drag and drop action for a granted user");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "manager";
		
		
		String name = "Account Portlet";
		String idName = "Administration/AccountPortlet";
		
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
		*Step Name: Step 1: Setting Move Apps permission in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout-> Site's Config-> Permission-> Move Apps
			- Grant Move Apps permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move App tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		/*Step number: 2
		*Step Name: Step 2: Drag and drop application
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Sites-> Edit Layout
			- From the composer, move app by drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The apps is added to the site layout*/ 
		
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
				pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_HOME);		
		
		info("Delete the added portlet");
		navigationToolbar.goToEditSiteLayout();
		pgCreateWiz.deleteApplication(name);
 	}

	/**
	*<li> Case ID:128133.</li>
	*<li> Test Case Name: Check move apps by drag and drop for a granted user.</li>
	*<li> Pre-Condition: NO restrictive permission is defined in a container for the granted user
						User A is administrator</li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 7)
	public  void test05_CheckMoveAppsByDragAndDropForAGrantedUser() {
		info("Test 5: Check move apps by drag and drop for a granted user");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "manager";
		
		
		String appSource = "UICompanyNavigationPortlet";
		String appTarget = "UIGroupsNavigationPortlet";
		
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
		*Step Name: Step 1: Open Move Apps permission in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site-> Layout-> Site's Config > Permission-> Move Apps
			- Grant Move apps permission to User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the move apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move App tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Move Apps by drag and drop
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit > Sites > Edit Layout
			- In the site layout, move an application by drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The application is reorganized in the site layout*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		info("Move application");
		pgCreateWiz.moveApplication(appSource,appTarget,87);
		
		info("Check position");
		navigationToolbar.goToEditSiteLayout();
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_APPLICATION_PRECEDING_PORTLET
											.replace("${app1}",appTarget)
											.replace("${app2}",appSource),
											pgCreateWiz.ELEMENT_APPLICATION_FOLLOWING_PORTLET
											.replace("${app1}",appTarget).replace("${app2}",appSource));		
 	}

	/**
	*<li> Case ID:128134.</li>
	*<li> Test Case Name: Check move apps by drag and drop for a not granted user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="error",priority = 4)
	public  void test06_CheckMoveAppsByDragAndDropForANotGrantedUser() {
		info("Test 6: Check move apps by drag and drop for a not granted user");
		
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "editor";
				
		String name = "Organization";
		String idName = "Administration/OrganizationPortlet";
		
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
		*Step Name: Step 1: Open Move Apps Permissions setting in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit > Site > Layout > Site's Config > Permission > Move Apps
			- Do not grant Move Apps permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the move apps permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move App tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		/*Step number: 2
		*Step Name: Step 2: Drag and drop application
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit > Sites > Edit Layout
			- In the site layout, move app by drag and drop to the site 
			- Note: Do not drag and drop app in any container, 
					if you want to forbid User A to move app in container,
					you have to not grant Move Apps permission for User A in container
		*Input Data: 
			
		*Expected Outcome: 
			- User cannot move apps in the site layout*/ 
		
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		pgCreateWiz.addAppWithoutPermission("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
				pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_HOME);
		
 	}

	/**
	*<li> Case ID:128135.</li>
	*<li> Test Case Name: Check add containers by drag and drop for a granted user.</li>
	*<li> Pre-Condition: NO restrictive permission is defined in a container for the granted user</li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 1)
	public  void test07_CheckAddContainersByDragAndDropForAGrantedUser() {
		info("Test 7: Check add containers by drag and drop for a granted user");
		
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "manager";
		
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
		*Step Name: Step 1: Setting Move Containers permission in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission 
			-
			-> Move Containers
			- Grant Move Containers permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Containers permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Add containers to site
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Administration > Sites > Edit Layout
			- From the composer, drag and drop a container to site layout
		*Input Data: 
			
		*Expected Outcome: 
			- The container is moved to Site layout*/ 
		
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		pgCreateWiz.addContainer("oneRow", false);
		Utils.pause(3000);
		
		info("Verify the added container");
		navigationToolbar.goToEditSiteLayout();
		waitForAndGetElement(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_ADDED);
		
		info("Delete the added container");
		pgCreateWiz.deleteContainer("Container");
	}

	/**
	*<li> Case ID:128136.</li>
	*<li> Test Case Name: Check Delete container icon for not granted user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 6)
	public  void test08_CheckDeleteContainerIconForNotGrantedUser() {
		info("Test 8: Check Delete container icon for not granted user");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "editor";
		
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
		*Step Name: Step 1: Open Move Containers permission settings in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission 
			-
			-> Move Containers
			- Do not grant Move Containers permission for User A on this site
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the move containers permission on this site*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Check "Delete Container" icon
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Administration 
			-
			-> Sites 
			-
			-> Edit Layout
			- Move the mouse over a container in the Site Layout
			- Note:+ This container is not included in any container
				   + If you want to forbid User A to delete a container that is included in any other container, 
				    you have to not grant Move Container permission to User A on this container
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete Container" icon is hidden*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_APPLICATION_TAB);
		
		waitForElementNotPresent(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_DELETE);
		
 	}

	/**
	*<li> Case ID:128137.</li>
	*<li> Test Case Name: Check move container by drag and drop for a granted user.</li>
	*<li> Pre-Condition: NO restrictive permission is defined in a container for the granted user</li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 2)
	public  void test09_CheckMoveContainerByDragAndDropForAGrantedUser() {
		info("Test 9: Check move container by drag and drop for a granted user");

		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "manager";
		
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
		*Step Name: Step 1: Open Move Container permission setting in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission 
			-
			-> Containers Containers
			- Grant Move Containers permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the containers apps permission*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Move container
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Administration > Sites > Edit Layout
			- In the site layout, move container by drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- Containers are reorganized in the site layout*/ 

		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		info("Add container to move");
		pgCreateWiz.addContainer("oneRow");
		
		info("Move container");
		navigationToolbar.goToEditSiteLayout();
		pgCreateWiz.moveContainer("Container",pgCreateWiz.ELEMENT_CONTAINER_HOLDER_MOVE,pgCreateWiz.ELEMENT_PORTLET,87);
		
		info("Check position");
		navigationToolbar.goToEditSiteLayout();
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_CONTAINER_PRECEDING_PORTLET,pgCreateWiz.ELEMENT_CONTAINER_FOLLOWING_PORTLET);
		
 	}

	/**
	*<li> Case ID:128138.</li>
	*<li> Test Case Name: Check move container by drag and drop for a not granted user.</li>
	*<li> Pre-Condition: NO restrictive permission is defined in a container for the granted user</li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 3)
	public  void test10_CheckMoveContainerByDragAndDropForANotGrantedUser() {
		info("Test 10 Check move container by drag and drop for a not granted user");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "editor";
		
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
		*Step Name: Step 1: Open Move Containers permission settings in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission 
			-
			-> Move Containers
			- Do not grant Move Containers permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted with the containers apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		navigationToolbar.goToEditSiteLayout();
		
		info("Add container to move");
		pgCreateWiz.addContainer("oneRow",false);
		Utils.pause(3000);
		
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesSiteConfig();
			
		manageLayout.saveChangesSiteLayout();				
		
		/*Step number: 2
		*Step Name: Step 2: Move container
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Administration > Sites > Edit Layout
			- In the site layout, move container by drag and drop
			- Note: + Do not drag and drop app in any container
					+ If you want to forbid User A to move container into container,
					 you have to not grant Move Container permission for User A in this container
		*Input Data: 
			
		*Expected Outcome: 
			- User cannot move containers by drag and drop in the site layout*/ 
		
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		waitForElementNotPresent(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_EMPTY_DRAGANDDROP);
		
 	}
}