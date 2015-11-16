package org.exoplatform.selenium.platform.restrictedPageEditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


	public class ContainerPermissions extends testConfig{
		
	/**
	*<li> Case ID:128153.</li>
	*<li> Test Case Name: Check Delete portlet icon for NOT granted user with Move apps permission in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDeletePortletIconForNOTGrantedUserWithMoveAppsPermissionInEditSiteMode() {
		info("Test 1: Check Delete portlet icon for NOT granted user with Move apps permission in Edit Site mode");
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Apps
			- Do not grant Move Apps permission for User A in this container
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Apps permission in this container*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "UICompanyNavigationPortlet"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																.replace("${id}", "LeftNavigationPortlet")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move App tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath,membership,addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Check "Delete portlet" button
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Site -> Layout
			- Move the mouse over the portlet in the container at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete portlet" icon is hidden*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit page");
		navigationToolbar.goToEditSiteLayout();
	
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "UICompanyNavigationPortlet"), true);
        Utils.pause(3000);
        
        waitForElementNotPresent(pgCreateWiz.ELEMENT_APPLICATION_DELETE_ICON.replace("${name}", "UICompanyNavigationPortlet"),2000,1);
 	}

	/**
	*<li> Case ID:128154.</li>
	*<li> Test Case Name: Check add apps by drag and drop for granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAddAppsByDragAndDropForGrantedUserInEditPageMode() {
		info("Test 2: Check add apps by drag and drop for granted user in Edit Page mode");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		
		int index = appLayData.getRandomIndexByType(1);
		String name = appLayData.newTitle.get(index);
		String idName = appLayData.newId.get(index);
		
		info("name =" + name + ", idName = " + idName);
		
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Page -> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Apps
			- Grant Move Apps permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the Move apps permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Edit Page Layoout");
		navigationToolbar.goToEditLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
        mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}", "Officebody"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ICON); 
					
//		//Call mouseover twice so that it can go to the selected container
//        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
//        Utils.pause(3000);
//
//        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "UICompanyNavigationPortlet"), true);
//        Utils.pause(3000);
//
//        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
//																.replace("${id}", "LeftNavigationPortlet")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move apps tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath, "manager",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Add new applications
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Page -> Edit Page
			-Add new applications from the composer in this container via drag and drop.
		*Input Data: 
			
		*Expected Outcome: 
			- The apps is added to the containers*/ 

		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Page Layout");
		navigationToolbar.goToEditLayout();
		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
				pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);
 	}

	/**
	*<li> Case ID:128155.</li>
	*<li> Test Case Name: Check move apps in the container by drag and drop for granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckMoveAppsInTheContainerByDragAndDropForGrantedUserInEditSiteMode() {
		info("Test 3: Check move apps in the container by drag and drop for granted user in Edit Site mode");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Apps
			- Grant Move Apps permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the Move apps permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "UICompanyNavigationPortlet"), true);
        Utils.pause(3000);
        
        info("Open permission popup");
        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																.replace("${id}", "LeftNavigationPortlet")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move apps tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath, "manager",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Move Apps
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Site -> Layout
			-In this container via drag and drop, move apps
		*Input Data: 
			
		*Expected Outcome: 
			- The apps are moved in the containers*/ 
	
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit Site Layout");
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
	*<li> Case ID:128156.</li>
	*<li> Test Case Name: Check Delete container icon for NOT granted user with Move Container permission in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDeleteContainerIconForNOTGrantedUserWithMoveContainerPermissionInEditSiteMode() {
		info("Test 4: Check Delete container icon for NOT granted user with Move Container permission in Edit Site mode");
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
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout 
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Containers
			- Do not grant Move Container permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not granted Move Containers permission in this container*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "GroupsNavigationPortlet"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																.replace("${id}", "GroupsNavigationPortlet")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath, membership,addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Check "Delete Container" button
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Site -> Layout
			- Move the mouse over the setting container at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete Container" button is hidden*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Edit page");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "GroupsNavigationPortlet"), true);
        Utils.pause(3000);
        
        waitForElementNotPresent(pgCreateWiz.ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}", "GroupsNavigationPortlet"),2000,1); 	
 	}

	/**
	*<li> Case ID:128157.</li>
	*<li> Test Case Name: Check add container by drag and drop for granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckAddContainerByDragAndDropForGrantedUserInEditPageMode() {
		info("Test 5: Check add container by drag and drop for granted user in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Containers permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Page -> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission > Move Containers
			- Grant Move Container permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Container permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
        mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}", "Officebody"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ICON); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath,membership,addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesPageLayout();
		
		
		/*Step number: 2
		*Step Name: Step 2: Add container
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Page -> Edit Page
			- Add containers from the composer in this container (the chosen container at step 1) via drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The container is added to the container*/ 

		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Page Edit Layout form");
		navigationToolbar.goToEditLayout();
		
		pgCreateWiz.addContainer("oneRow", true);
	}

	/**
	*<li> Case ID:128158.</li>
	*<li> Test Case Name: Check move Container in the container by drag and drop for granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckMoveContainerInTheContainerByDragAndDropForGrantedUserInEditSiteMode() {
		info("Test 6: Check move Container in the container by drag and drop for granted user in Edit Site mode");
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
		String appSource = "LeftBreadCrumbNavigationPortlet";
		String appTarget = "UIGroupsNavigationPortlet";
		/*Step Number: 1
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site-> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Containers
			- Grant Move Container permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Container permission in this container*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Edit Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		selectSiteContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION,
							pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ID.replace("${id}", "NavigationBody"),
							groupPath, membership, addedGroup);
		/*Step number: 2
		*Step Name: Step 2: Move container
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Site -> Edit Layout
			-In the setting container at step 1 via drag and drop, move containers
		*Input Data: 
			
		*Expected Outcome: 
			- The containers are moved in the containers*/ 
		
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		navigationToolbar.goToEditSiteLayout();
        
		pgCreateWiz.addContainer("oneRow", true);
		info("Move application");
		pgCreateWiz.moveContainer("",appSource,appTarget,87);
		
		info("Check position");
		navigationToolbar.goToEditSiteLayout();
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_APPLICATION_PRECEDING_PORTLET
											.replace("${app1}",appTarget)
											.replace("${app2}",appSource),
											pgCreateWiz.ELEMENT_APPLICATION_FOLLOWING_PORTLET
											.replace("${app1}",appTarget).replace("${app2}",appSource));	

 	}

	/**
	*<li> Case ID:128159.</li>
	*<li> Test Case Name: Check buttons Move apps and Move Container for admin users.</li>
	*<li> Pre-Condition: - user1 in administrators group.
	- user2 is member in another group and editor role in Content-contributors group.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckButtonsMoveAppsAndMoveContainerForAdminUsers() {
		info("Test 7: Check buttons Move apps and Move Container for admin users");
		String groupPath="Platform/Content Management";
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
		userAndGroup.addUserContentManagement(username1);
		userAndGroup.addUserContentManagement(username2);
		manageLoginOut.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1:Add Site / Page / Container.
		*Step Description: 
			- Connect to Intranet with an administrator user profile.
			- Add Site / Page / Container.
			- Set Permission in Edit tab so that user2 can edit.
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed.
			- Site / Page are added. Containers added in Site / Page.
			- Edit Permission is set for user2.*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		//Edit Site Configuration
		navigationToolbar.goToPotalSites();
		portalManageSites.goToEditSiteConfig("intranet");
		manageLayout.goToSitePermissionTab();
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_EDIT);
		pgCreateWiz.setupEditPermissions(groupPath, membership);

		
		info("Set Site - edit permission");
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		info("Check Edit tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_EDIT);
		pgCreateWiz.setupEditPermissions(groupPath, membership);
		pgCreateWiz.saveChangesPageEditor();
		
		//Set Page - edit permission
		info("Set Page - edit permission");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
						
		info("Go to Permission tab");
		manageLayout.goToPagePermissionTab();
		info("Click Edit tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_EDIT);
		pgCreateWiz.setupEditPermissions(groupPath, membership);
		pgCreateWiz.saveChangesPageEditor();
		/*Step number: 2
		*Step Name: Step 2:Check "Move Apps" and "Move Containers" displayed for Admin user.
		*Step Description: 
			- Go toEdit > Site > Layout > Site's Config > Permission tab.
			- Go toEdit > Page > Layout > View Page Properties > Permission tab
			- Edit a container > Permission tab.
		*Input Data: 
			
		*Expected Outcome: 
			- Admin user1 can see and use "Move Apps" and "Move Containers" buttons.*/

		/*Step number: 3
		*Step Name: Step 3:Check "Move Apps" and "Move Containers" NOT displayed for other users.
		*Step Description: 
			- Edit Site Layout > Permission tab.
			- Edit Page Layout > Permission tab.
			- Edit Containers > Permission tab.
		*Input Data: 
			
		*Expected Outcome: 
			user2 can NOT see and use "Move Apps" and "Move Containers" buttons.*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		info("Check button for User2");
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Check Move apps tab");
		waitForElementNotPresent(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		
		info("Check Move containers tab");
		waitForElementNotPresent(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));	
		
		button.cancel();
		manageLayout.abortPageUpdate();
		
		//Check button in Page layout
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
						
		info("Go to Permission tab");
		manageLayout.goToPagePermissionTab();
		info("Check tab name");
		waitForElementNotPresent(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_APPS));
		waitForElementNotPresent(manageLayout.ELEMENT_PERMISSION_SELECTOR
				.replace("${tabName}", manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS));
		button.cancel();
		manageLayout.abortPageUpdate();
		
		//check button in a container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}", "Officebody"), true);
	    Utils.pause(3000);

 	}

	/**
	*<li> Case ID:128160.</li>
	*<li> Test Case Name: Check add apps by drag and drop for granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckAddAppsByDragAndDropForGrantedUserInEditSiteMode() {
		info("Test 8: Check add apps by drag and drop for granted user in Edit Site mode");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		
		int index = appLayData.getRandomIndexByType(1);
		String name = appLayData.newTitle.get(index);
		String idName = appLayData.newId.get(index);
		
		info("name =" + name + ", idName = " + idName);
		
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Apps
			- Grant Move Apps permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the Move apps permission*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Call mouseover twice so that it can go to the selected container
        mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
        Utils.pause(3000);

        mouseOver(pgCreateWiz.ELEMENT_CONTAINER_PORTLET_APPLICATION.replace("${name}", "UICompanyNavigationPortlet"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																.replace("${id}", "LeftNavigationPortlet")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move apps tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
	
		WebElement permissionTable = waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
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
		manageLayout.addPremission(groupPath, "manager",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID, "Permission Selector");
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		
		/*Step number: 2
		*Step Name: Step 2: Add new application
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit -> Page -> Edit Page
			-Add new applications from the composer in this container via drag and drop.
		*Input Data: 
			
		*Expected Outcome: 
			- The apps is added to the containers*/ 
		
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Page Layout");
		navigationToolbar.goToEditLayout();
		pgCreateWiz.addApp("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",idName),
				pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);		

 	}

	/**
	*<li> Case ID:128161.</li>
	*<li> Test Case Name: Check add container by drag and drop for granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckAddContainerByDragAndDropForGrantedUserInEditSiteMode() {
		info("Test 9: Check add container by drag and drop for granted user in Edit Site mode");
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
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit -> Site -> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission -> Move Containers
			- Grant Move Container permission for User A in this container
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Containers permission*/
	
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();
		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		selectSiteContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION,
				pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ID.replace("${id}", "NavigationBody"),
				groupPath, membership, addedGroup);
		
		/*Step number: 2
		*Step Name: Step 2: Add container
		*Step Description: 
			- Go to Edit > Site> Layout
			- Connect to Intranet with User A
			- Go to Edit -> Site-> Layout
			- Add containers from the composer in this container (the chosen container at step 1) via drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The container is added to the containers*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		navigationToolbar.goToEditSiteLayout();		
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);	
		pgCreateWiz.addContainer("oneRow", true);
 	}

	/**
	*<li> Case ID:128162.</li>
	*<li> Test Case Name: Check Delete container icon for NOT granted user with Move Container permission in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test(priority = 2)
	public  void test10_CheckDeleteContainerIconForNOTGrantedUserWithMoveContainerPermissionInEditPageMode() {
		info("Test 10 Check Delete container icon for NOT granted user with Move Container permission in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission > Move Containers
			- Do not grant Move Container permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Containers permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToWiki();
		
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
																		.replace("${id}","myWikiPortlet"),
													pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																		.replace("${id}","myWikiPortlet"),
																	groupPath,membership, addedGroup);		
		
		//Check delete icon
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}","myWikiPortlet"), true);
		waitForAndGetElement(pgCreateWiz.ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}","myWikiPortlet"));
		manageLayout.abortPageUpdate();
		/*Step number: 2
		*Step Name: Step 2: Check "Delete Container" button
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- Move the mouse over the setting container at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete Container" button is hidden*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
				
		homepage.goToWiki();
		
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Check delete icon
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}","myWikiPortlet"), true);
		waitForElementNotPresent(pgCreateWiz.ELEMENT_DELETE_CONTAINER_ICON_BY_ID.replace("${id}","myWikiPortlet"));
 	}

	/**
	*<li> Case ID:128163.</li>
	*<li> Test Case Name: Check Delete portlet icon for NOT granted user with Move apps permission in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckDeletePortletIconForNOTGrantedUserWithMoveAppsPermissionInEditPageMode() {
		info("Test 11 Check Delete portlet icon for NOT granted user with Move apps permission in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Apps
			- Do not grant Move Apps permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToWiki();
		
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		selectPageContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
																		.replace("${id}","myWikiPortlet"),
													pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																		.replace("${id}","myWikiPortlet"),
																	groupPath,membership, addedGroup);		
		
		//Check delete icon
		navigationToolbar.goToEditLayout();
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}","myWikiPortlet"), true);
		waitForAndGetElement(pgCreateWiz.ELEMENT_DELETE_PORTLET_ICON_BY_ID.replace("${id}","myWikiPortlet"));
		manageLayout.abortPageUpdate();
		/*Step number: 2
		*Step Name: Step 2: Check "Delete portlet" icon
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit 
			-
			-> Page > Edit Layout
			- Move the mouse over the portlet in the container at Step 1
		*Input Data: 
			
		*Expected Outcome: 
			- "Delete portlet" icon is hidden*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
				
		homepage.goToWiki();
		
		navigationToolbar.goToEditLayout();
		
		//Check delete icon
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID.replace("${id}","myWikiPortlet"), true);
		waitForElementNotPresent(pgCreateWiz.ELEMENT_DELETE_PORTLET_ICON_BY_ID.replace("${id}","myWikiPortlet"));
 	}

	/**
	*<li> Case ID:128164.</li>
	*<li> Test Case Name: Check move apps in the container by drag and drop for granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckMoveAppsInTheContainerByDragAndDropForGrantedUserInEditPageMode() {
		info("Test 12 Check move apps in the container by drag and drop for granted user in Edit Page mode");
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		String membership = "manager";
		
		String appSource = "Announcement";
		String appTarget = "User Activity Stream";
		
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
		*Step Name: Step 1: Setting Move Apps permission for container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Apps
			- Grant Move Apps permission for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted with the Move apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
														.replace("${id}","Officebody"),
														pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ICON,
														groupPath,membership, addedGroup);
				
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		selectPageContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
																		.replace("${id}","Officebody"),
												pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ICON,
												groupPath,membership, addedGroup);		
		/*Step number: 2
		*Step Name: Step 2: Move application
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit 
			-
			-> Page > Edit Layout
			-In this container via drag and drop, move apps
		*Input Data: 
			
		*Expected Outcome: 
			- The apps are moved in the containers*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
				
		navigationToolbar.goToEditLayout();
		
		info("Move application");
		pgCreateWiz.moveApplication(appSource,appTarget,87);
		
		info("Check position");
		navigationToolbar.goToEditLayout();
		pgCreateWiz.checkPositions(pgCreateWiz.ELEMENT_APPLICATION_PRECEDING_PORTLET
											.replace("${app1}",appTarget)
											.replace("${app2}",appSource),
											pgCreateWiz.ELEMENT_APPLICATION_FOLLOWING_PORTLET
											.replace("${app1}",appTarget).replace("${app2}",appSource));
 	}

	/**
	*<li> Case ID:128165.</li>
	*<li> Test Case Name: Check move Container in the container by drag and drop for granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckMoveContainerInTheContainerByDragAndDropForGrantedUserInEditPageMode() {
		info("Test 13 Check move Container in the container by drag and drop for granted user in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Containers
			- Grant Move Container permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is granted Move Container permission in this container*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToDocuments();
		
		//set permission for page
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		manageLayout.saveChangesPropertiesPopup();	
		manageLayout.saveChangesPageLayout();			
		
		//Set permission for the inner container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
				
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
								.replace("${id}", "bottom-document-container"), true);
		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentTopContainer"),
														pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
															.replace("${id}","DocumentTopContainer"),
																		groupPath, membership, addedGroup);
		
		//Set permission for the outer container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);

		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentContainer"),
															pgCreateWiz.ELEMENT_SITE_LAYOUT_DOCUMENTS_CONTAINER_EDIT,
															groupPath, membership, addedGroup);
					
		/*Step number: 2
		*Step Name: Step 2: Move container
		*Step Description: 
			- Connect to Intranet with User A 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			-In the setting container at step 1 via drag and drop, move containers
		*Input Data: 
			
		*Expected Outcome: 
			- The containers are moved in the containers*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		homepage.goToDocuments();
		
		info("Go to Edit Page Layout");
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		pgCreateWiz.addContainer("oneRow", false);
		Utils.pause(3000);
		
		info("Verify the added container");
		navigationToolbar.goToEditLayout();
		waitForAndGetElement(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_ADDED);
	
	}

	/**
	*<li> Case ID:128332.</li>
	*<li> Test Case Name: Check add apps by drag and drop for NOT granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckAddAppsByDragAndDropForNOTGrantedUserInEditPageMode() {
		info("Test 14 Check add apps by drag and drop for NOT granted user in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Apps
			-
			- Do not grant Move Apps permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Apps permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToDocuments();
				
		//Set public mode for Move Container
		//set permission for page
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.checkMoveContainerPublicMode();
		manageLayout.saveChangesPropertiesPopup();	
		manageLayout.saveChangesPageLayout();	
		
		//Set public mode for Move Container of the inner container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
								.replace("${id}", "bottom-document-container"), true);
		selectPageContainerAndSetMoveContainerPublic(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentTopContainer"),
														pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
															.replace("${id}","DocumentTopContainer"));
		
		//Set permission for the outer container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		selectPageContainerAndSetMoveContainerPublic(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
														.replace("${id}","DocumentContainer"),
														pgCreateWiz.ELEMENT_SITE_LAYOUT_DOCUMENTS_CONTAINER_EDIT);
		
		
		//Set Permission for Move Apps
		//Inner container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
				.replace("${id}", "bottom-document-container"), true);
		selectPageContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentTopContainer"),
														pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
															.replace("${id}","DocumentTopContainer"),
																		groupPath, membership, addedGroup);
		
		//Set permission for the outer container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);

		selectPageContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentContainer"),
															pgCreateWiz.ELEMENT_SITE_LAYOUT_DOCUMENTS_CONTAINER_EDIT,
															groupPath, membership, addedGroup);
					
		//set permission for page
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.uncheckMoveAppsPublicMode();
		manageLayout.saveChangesPropertiesPopup();	
		manageLayout.saveChangesPageLayout();	
		/*Step number: 2
		*Step Name: Step 2: Add new applications
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Page
			-Add new applications from the composer in this container via drag and drop.
		*Input Data: 
			
		*Expected Outcome: 
			- The apps is NOT added to the containers*/ 
		manageLoginOut.signIn(username2,password);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToDocuments();
		navigationToolbar.goToEditLayout();
		
		info("Add application");
		pgCreateWiz.addAppWithoutPermission("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION
															.replace("${name}",idName),
															pgCreateWiz.ELEMENT_PAGEEDITOR_VIEWPAGE);
	}

	/**
	*<li> Case ID:128333.</li>
	*<li> Test Case Name: Check add apps by drag and drop for NOT granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckAddAppsByDragAndDropForNOTGrantedUserInEditSiteMode() {
		info("Test 15 Check add apps by drag and drop for NOT granted user in Edit Site mode");
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
		*Step Name: Step 1: Setting Move Apps permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Apps
			- Do not grant Move Apps permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Apps permission in this container*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
				
		//RightBody
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION, true);
		selectSiteContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
																				.replace("${id}","RightBody"),
														pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
														.replace("${id}","RightBody"),
														groupPath, membership, addedGroup);
				
		//the most outer container
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		selectSiteContainerAndSetMoveAppsPermission(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_NAVIGATION,
						pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_PARENT_EDIT_ID.replace("${id}", "NavigationBody"),
						groupPath, membership, addedGroup);
			
		//site permission - set to no-one permission
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		manageLayout.goToSiteConfigPopup();
		manageLayout.goToPagePermissionTab();

		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		/*Step number: 2
		*Step Name: Step 2: Add new application
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Page
			-Add new applications from the composer in this container via drag and drop.
		*Input Data: 
			
		*Expected Outcome: 
			- The apps is NOT added to the containers*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		navigationToolbar.goToEditSiteLayout();
		
		info("Add application");
		pgCreateWiz.addAppWithoutPermission("",name,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION
															.replace("${name}",idName),
															pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_BY_ID
															.replace("${id}", "NavigationBody"));
 	}

	/**
	*<li> Case ID:128334.</li>
	*<li> Test Case Name: Check add container by drag and drop for NOT granted user in Edit Page mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckAddContainerByDragAndDropForNOTGrantedUserInEditPageMode() {
		info("Test 16 Check add container by drag and drop for NOT granted user in Edit Page mode");
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
		*Step Name: Step 1: Setting Move Containers permission in Container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission > Move Containers
			- Do not grant Move Container permission in this container for User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Container permission in this container*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		homepage.goToDocuments();
				
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		
		//Set permission for the inner container
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
								.replace("${id}", "bottom-document-container"), true);
		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentTopContainer"),
														pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
															.replace("${id}","DocumentTopContainer"),
																		groupPath, membership, addedGroup);
		
		//Set permission for the outer container
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);

		selectPageContainerAndSetMoveContainerPermission(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
															.replace("${id}","DocumentContainer"),
															pgCreateWiz.ELEMENT_SITE_LAYOUT_DOCUMENTS_CONTAINER_EDIT,
															groupPath, membership, addedGroup);
					
		//set permission for page
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.uncheckMoveContainerPublicMode();
		manageLayout.saveChangesPropertiesPopup();	
		manageLayout.saveChangesPageLayout();	
		/*Step number: 2
		*Step Name: Step 2: Add container
		*Step Description: 
			- Connect to Intranet with User A<br />
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Page<br />
			- Add containers from the composer in this container (the chosen container at step 1) via drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The container is NOT added to the container*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		homepage.goToDocuments();
		
		info("Go to Edit Page Layout");
		navigationToolbar.goToEditLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		pgCreateWiz.addContainer("oneRow", false);
		Utils.pause(3000);
		
		info("Verify the added container");
		navigationToolbar.goToEditLayout();
		waitForElementNotPresent(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_ADDED);
 	}

	/**
	*<li> Case ID:128335.</li>
	*<li> Test Case Name: Check add container by drag and drop for NOT granted user in Edit Site mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckAddContainerByDragAndDropForNOTGrantedUserInEditSiteMode() {
		info("Test 17 Check add container by drag and drop for NOT granted user in Edit Site mode");
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
		*Step Name: Step 1: Setting Move Container permission in a container
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout
			- From Composer, Select Container tab
			- From a Container, click on the icon "Edit Container"
			- Go to the tab Permission 
			-
			-> Move Containers
			- Do not grant Move Container permission for User A in this container
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT granted Move Containers permission*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		//Set permission for site layout
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteLayout();

		info("Set permission");
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Click Move Container tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.uncheckMoveContainerPublicMode();
		
		manageLayout.saveChangesSiteConfig();
		manageLayout.saveChangesSiteLayout();
		
		//Select a container and set permission
		navigationToolbar.goToEditSiteLayout();
		click(pgCreateWiz.ELEMENT_CONTAINER_TAB);
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_ID
																.replace("${id}", "RightBody"), true);
        Utils.pause(3000);

        clickByJavascript(pgCreateWiz.ELEMENT_PAGEEDITOR_EDITELEMENT_BY_ID
																.replace("${id}", "RightBody")); 
        waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
        info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
																		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesContainerPopup();
		manageLayout.saveChangesSiteLayout();
		/*Step number: 2
		*Step Name: Step 2: Add container
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Edit > Site> Layout
			- Add containers from the composer in this container (the chosen container at step 1) via drag and drop
		*Input Data: 
			
		*Expected Outcome: 
			- The container is NOT added to the container*/ 
		manageLoginOut.signIn(username2,password);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Edit Site Layout");
		navigationToolbar.goToEditSiteLayout();
		
		pgCreateWiz.addContainer("oneRow", false);
		Utils.pause(3000);
		
		info("Verify the added container");
		navigationToolbar.goToEditSiteLayout();
		waitForElementNotPresent(pgCreateWiz.ELEMENT_SITE_LAYOUT_CONTAINER_ADDED);
 	}

	/**
	 * Select a page container and set move container permission
	 * @param containerXPath
	 * @param containerEditXpath
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void selectPageContainerAndSetMoveContainerPermission(Object containerXPath, Object containerEditXpath,
											String groupPath, String membership, String addedGroup){
			
		mouseOver(containerXPath , true);
		Utils.pause(3000);
		
		clickByJavascript(containerEditXpath);
		
		waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
		info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
					.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
		manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
		
		manageLayout.saveChangesContainerPopup();	
		manageLayout.saveChangesPageLayout();	
	}	
	/**
	 * Select site container and set move container permission
	 * @param containerXPath
	 * @param containerEditXpath
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void selectSiteContainerAndSetMoveContainerPermission(Object containerXPath, Object containerEditXpath,
												String groupPath, String membership, String addedGroup){

			mouseOver(containerXPath , true);
			Utils.pause(3000);
			
			clickByJavascript(containerEditXpath);
			
			waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
			
			info("Click Permission tab");
			manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
			.replace("${tabName}", "Access Permission"));
			info("Click Move Containers tab");
			manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
			
			manageLayout.setMoveContainersPermission(groupPath, membership, addedGroup);
			
			manageLayout.saveChangesContainerPopup();	
			manageLayout.saveChangesSiteLayout();
	}	
	/**
	 * Select site container and set move application permission
	 * @param containerXPath
	 * @param containerEditXpath
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void selectSiteContainerAndSetMoveAppsPermission(Object containerXPath, Object containerEditXpath,
											String groupPath, String membership, String addedGroup){

			mouseOver(containerXPath , true);
			Utils.pause(3000);
			
			clickByJavascript(containerEditXpath);
			
			waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
			
			info("Click Permission tab");
			manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
			.replace("${tabName}", "Access Permission"));
			info("Click Move Apps tab");
			manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
			
			manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
			
			manageLayout.saveChangesContainerPopup();	
			manageLayout.saveChangesSiteLayout();
	}		
	
	public void selectPageContainerAndSetMoveContainerPublic(Object containerXPath, Object containerEditXpath){

		mouseOver(containerXPath , true);
		Utils.pause(3000);
		
		clickByJavascript(containerEditXpath);
		
		waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
		info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
		manageLayout.checkMoveContainerPublicMode();
		
		manageLayout.saveChangesContainerPopup();	
		manageLayout.saveChangesPageLayout();	
	}		
	
	/**
	 * Select site container and set move containser public
	 * @param containerXPath
	 * @param containerEditXpath
	 */
	public void selectSiteContainerAndSetMoveContainerPublic(Object containerXPath, Object containerEditXpath){

		mouseOver(containerXPath , true);
		Utils.pause(3000);
		
		clickByJavascript(containerEditXpath);
		
		waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
		info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
		manageLayout.checkMoveContainerPublicMode();
		
		manageLayout.saveChangesContainerPopup();	
		manageLayout.saveChangesSiteLayout();
	}	
	
	/**
	 * Select site container and set move container for no one
	 * @param containerXPath
	 * @param containerEditXpath
	 */
	public void selectSiteContainerAndSetMoveContainerNoOne(Object containerXPath, Object containerEditXpath){

		mouseOver(containerXPath , true);
		Utils.pause(3000);
		
		clickByJavascript(containerEditXpath);
		
		waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
		
		info("Click Permission tab");
		manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
		.replace("${tabName}", "Access Permission"));
		info("Click Move Containers tab");
		manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
		manageLayout.uncheckMoveContainerPublicMode();
		
		manageLayout.saveChangesContainerPopup();	
		manageLayout.saveChangesSiteLayout();
	}		
		
	/**
	 * Select page containser and set move application permission
	 * @param containerXPath
	 * @param containerEditXpath
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void selectPageContainerAndSetMoveAppsPermission(Object containerXPath, Object containerEditXpath,
			String groupPath, String membership, String addedGroup){
			
			mouseOver(containerXPath , true);
			Utils.pause(3000);
			
			clickByJavascript(containerEditXpath);
			
			waitForAndGetElement(pgCreateWiz.ELEMENT_EDITING_CONTAINER_POPUP);
			
			info("Click Permission tab");
			manageLayout.goToPermissionContainer(manageLayout.ELEMENT_CONTAINER_PERMISSION_SELECTOR
			.replace("${tabName}", "Access Permission"));
			info("Click Move Containers tab");
			manageLayout.gotoContainerPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
			
			manageLayout.setMoveAppsPermission(groupPath, membership, addedGroup);
			
			manageLayout.saveChangesContainerPopup();	
			manageLayout.saveChangesPageLayout();	
	}		
	
}
