package org.exoplatform.selenium.platform.restrictedPageEditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.Test;


	public class PermissionSettingUI extends testConfig {
	/**
	*<li> Case ID:128118.</li>
	*<li> Test Case Name: Check the display "Everyone" label on Permission tab.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTheDisplayEveryoneLabelOnPermissionTab() {
		info("Test 1: Check the display Everyone label on Permission tab");
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
		*Step Name: Step 1: Open Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
	
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		/*Step number: 2
		*Step Name: Step 2: Open Access Permission setting in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Add Site 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			 
		*Expected Outcome: 
			- "Everyone" label is displayed*/
		info("Go to Add Site form");
		navigationToolbar.goToEditSiteAddSite();
		waitForAndGetElement(manageLayout.ELEMENT_PROPERTIES_POPUP_PERMISSION_TAB);
		
		info("Go to Permission tab");
		manageLayout.goToPagePermissionTab();
		
		info("Go to Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.
										replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps Permission setting in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Add Site 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" label is displayed*/

		info("Go to Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.
									replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));		
		
		/*Step number: 4
		*Step Name: Step 4: Open Move Containers Permission setting in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Add Site 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" label is displayed*/

		info("Go to Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.
								replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		
		/*Step number: 5
		*Step Name: Step 5: Open Access Permission setting in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Layout 
			-
			-> Page Properties 
			-
			-> Permission Tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" label is displayed*/

		info("Go to Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
				
		info("Go to Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		
		/*Step number: 6
		*Step Name: Step 6: Open Move Apps Permission setting in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Layout 
			-
			-> Page Properties 
			-
			-> Permission Tab 
			-
			-> Move Apps tab
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" label is displayed*/

		info("Go to Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));			
		
		/*Step number: 7
		*Step Name: Step 7: Open Move Containers Permission setting in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Layout 
			-
			-> Page Properties 
			-
			-> Permission Tab 
			-
			-> Move Containers tab
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" label is displayed*/ 
		info("Go to Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		click(manageLayout.ELEMENT_EDIT_PORTLET_ABORT);
 	}

	/**
	*<li> Case ID:128119.</li>
	*<li> Test Case Name: Check default value of "Everyone" checkbox.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultValueOfEveryoneCheckbox() {
		info("Test 2: Check default value of Everyone checkbox");
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
		*Step Name: Step 1: Go to Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 2
		*Step Name: Step 2: Open Site Configuration
		*Step Description: 
			- Go to Edit > Site > Layout > Site's Config > Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			- In "Access" tab: default value of "Everyone" checkbox is un-checked
			- In "Move Apps" tab: default value of "Everyone" checkbox is checked
			- In "Move Containers" tab: default value of "Everyone" checkbox is checked*/

		info("Go to Layout form");
		navigationToolbar.goToEditSiteLayout();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToPagePermissionTab();
		
		info("Go to Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE.
										replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
		boolean isSelected = checkCheckBoxAttribute(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
									.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		if(isSelected){
			assert false;
		} else {
			assert true;
		}
		check(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID), 2000, 0,1);
		
		/*Step number: 3
		*Step Name: Step3: Open Page Properties
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			- In "Access" tab: default value of "Everyone" checkbox is un
			-checked
			- In "Move Apps" tab: default value of "Everyone" checkbox is checked
			- In "Move Containers" tab: default value of "Everyone" checkbox is checked*/ 

 	}

	/**
	*<li> Case ID:128120.</li>
	*<li> Test Case Name: Check Permission table in the Permission tab.</li>
	*<li> Pre-Condition: The label "Everyone" is not checked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckPermissionTableInThePermissionTab() {
		info("Test 3: Check Permission table in the Permission tab");
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
		*Step Name: Step 1: Connect to Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 2
		*Step Name: Step 2: Open Access Permission in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/

		info("Go to Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();		
		
		info("Go to Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
				
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps Permission in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/

		info("Go to Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);	
		
		/*Step number: 4
		*Step Name: Step 4: Open Move Containers Permission in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/

		info("Go to Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);	
		
		button.cancel();
		manageLayout.abortPageUpdate();
		/*Step number: 5
		*Step Name: Step 5: Open Access Permission in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/

		info("Go to Edit Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();
		
		info("Go to Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);		
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 6
		*Step Name: Step 6: Open Move AppsPermission in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/

		info("Go to Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);	
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);		
		
		/*Step number: 7
		*Step Name: Step 7: Open Move ContainersPermission in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- A table is displayed with 3 columns Group ID, Membership, Action*/ 
		info("Go to Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);	
		manageLayout.checkPermissionTable(manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);	
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128121.</li>
	*<li> Test Case Name: Check the work of "Everyone" checkbox.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckTheWorkOfEveryoneCheckbox() {
		info("Test 4: Check the work of Everyone checkbox");
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
		*Step Name: Step 1: Open Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 2
		*Step Name: Step 2: Open Permission form of Site's Config
		*Step Description: 
			- Go to Edit > Site > Layout > Site's Config > Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			- The label "Everyone" is displayed, the label is checked
			- The permissions table and action buttons are hidden*/

		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		info("Check public mode");
		manageLayout.publicMode();
		manageLayout.checkPresentOfActionButtons(true, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 3
		*Step Name: Step 3: Do not give permission to Everyone
		*Step Description: 
			- Uncheck the label "Everyone"
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" check-box is un-checked
			- The permissions table with 3 columns (Group ID, Membership, Action) 
			and action buttons (Add Permission, Save, Cancel) appears*/

		manageLayout.uncheckPublicMode();
		manageLayout.checkPresentOfActionButtons(false, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		button.cancel();
		manageLayout.abortPageUpdate();
		
		/*Step number: 4
		*Step Name: Step 4: Open Permission form of Page Properties
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			- The label "Everyone" is displayed, the label is checked
			- The permissions table and action buttons are hidden*/

		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
		
		manageLayout.publicMode();
		manageLayout.checkPresentOfActionButtons(true,manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 5
		*Step Name: Step 5: Do not give permission to Every one
		*Step Description: 
			- Uncheck the label "Everyone"
		*Input Data: 
			
		*Expected Outcome: 
			- "Everyone" check-box is un-checked
			- The permissions table with 3 columns (Group ID, Membership, Action) and action buttons (Add Permission, Save, Cancel) appears*/ 
		manageLayout.uncheckPublicMode();
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		button.cancel();
		manageLayout.abortPageUpdate();		
 	}

	/**
	*<li> Case ID:128122.</li>
	*<li> Test Case Name: Check the display of Trash icon in the Permission tab.</li>
	*<li> Pre-Condition: The label "Everyone" is not checkedA Group ID is displayed in the Permission table</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckTheDisplayOfTrashIconInThePermissionTab() {
		info("Test 5: Check the display of Trash icon in the Permission tab");
		
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
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
		*Step Name: Stẹp 1: Connect to Intranet
		*Step Description: 
			- Connect to Intranet with an Administrator
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 2
		*Step Name: Step 2: Open Access Permission setting of Site's Config form
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a Trash icon is displayed*/

		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
													manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID,
													manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		
		//Test
		info("Check Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps Permission setting form
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a Trash icon is displayed*/

		info("Check Move Apps");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
					.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		
		/*Step number: 4
		*Step Name: Step 4: Open Move Containers Permission setting form
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout > Site's Config > Permission tab 
			-
			-> Move Containers tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a Trash icon is displayed*/

		info("Check Move Contaners");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		manageLayout.abortPageUpdate();
		/*Step number: 5
		*Step Name: Step 5: Open Access Permission setting of Page Properties form
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a trash icon is displayed*/

		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		
		//Test
		info("Check Access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		
		/*Step number: 6
		*Step Name: Step 6: Open Move Apps Permission setting of Page Properties form
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Apps tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a trash icon is displayed*/
		
		info("Check Move apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
				.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		
		/*Step number: 7
		*Step Name: Step 6: Open Move Containeres Permission setting of Page Properties form
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the Column action of the table, a trash icon is displayed*/ 
		
		info("Check Move container tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128123.</li>
	*<li> Test Case Name: Check the tooltip display of Trash icon in the Permission tab.</li>
	*<li> Pre-Condition: The label "Everyone" is not checkedA Group ID is displayed in the permission table</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckTheTooltipDisplayOfTrashIconInThePermissionTab() {
		info("Test 6: Check the tooltip display of Trash icon in the Permission tab");
		
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
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
		*Step Name: Step 1: Open Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 2
		*Step Name: Step 2: Open Access Permission form in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/

		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps Permission form in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/

		info("Test Move apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));		
		
		/*Step number: 4
		*Step Name: Step 4: Open Move Containers Permission form in Site's Config
		*Step Description: 
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/

		info("Test Move containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));			
		
		button.cancel();
		manageLayout.abortPageUpdate();
		/*Step number: 5
		*Step Name: Step 5: Open Access Permission in Page Properties
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab 
			-
			-> Access
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/

		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",addedGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));				
		
		/*Step number: 6
		*Step Name: Step 6: Open Move Apps Permission in Page Properties
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab 
			-
			-> Move Apps
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/

		info("Test Move apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));				
		/*Step number: 7
		*Step Name: Step 7: Open Move Containers Permission in Page Properties
		*Step Description: 
			- Go to Edit > Page > Edit Layout > View Page Properties > Permission tab 
			-
			-> Move Containers
			- Move the mouse over the trash icon
		*Input Data: 
			
		*Expected Outcome: 
			- A tooltip "Delete" is displayed*/ 
		
		info("Test Move containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		mouseOver(manageLayout.ELEMENT_DELETE_BUTTON
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), true);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_TOOLTIP
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128124.</li>
	*<li> Test Case Name: Delete a permission in Permission tab.</li>
	*<li> Pre-Condition: - The label "Everyone" is not checked
	- An entry permission is displayed in the permission table</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_DeleteAPermissionInPermissionTab() {
		info("Test 7: Delete a permission in Permission tab");
		String permissionGroup="/platform/users";
		String groupPath="Platform/Users";
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
		*Step Name: Step 1: Open Access permission in Site's Config
		*Step Description: 
			- Connect to Intranet Homepage
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
				
		/*Step number: 2
		*Step Name: Step 2: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/

		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps permission in Site's Config
		*Step Description: 
			- Connect to Intranet Homepage
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/
		info("Test move apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		/*Step number: 4
		*Step Name: Step 4: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/
		
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);
		
		/*Step number: 5
		*Step Name: Step 5: Open Move Containers permission in Site's Config
		*Step Description: 
			- Connect to Intranet Homepage
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/
		info("Test move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		/*Step number: 6
		*Step Name: Step 6: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);
	
		button.cancel();
		manageLayout.abortPageUpdate();
		
		/*Step number: 7
		*Step Name: Step 7: Open Access Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/

		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();				
		
		info("Add permisstion to table");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
							manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);

		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		
		/*Step number: 8
		*Step Name: Step 8: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/
		
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 9
		*Step Name: Step 9: Open Move Apps Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move App tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/

		info("Test move apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		
		/*Step number: 10
		*Step Name: Step 10: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/
		
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);
		
		/*Step number: 11
		*Step Name: Step 11: Open Move Containers Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers tab
		*Input Data: 
			
		*Expected Outcome: 
			- In the table permission, group/users are displayed 
			- The trash icon is displayed*/

		info("Test move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));		
		
		/*Step number: 12
		*Step Name: Step 12: Delete permission
		*Step Description: 
			- Click on the delete icon of a permission entry
			- Confirm the delete pop up
		*Input Data: 
			
		*Expected Outcome: 
			- The selected permission is removed*/ 
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128125.</li>
	*<li> Test Case Name: Check the display of "Add Permission" button in the Permission tab.</li>
	*<li> Pre-Condition: The label "Everyone" is not checked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckTheDisplayOfAddPermissionButtonInThePermissionTab() {
		info("Test 8: Check the display of Add Permission button in the Permission tab");
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
		*Step Name: Step 1: Open Intranet Homepage
		*Step Description: 
			- Connect to Intranet with an Administrator User profile
		*Input Data: 
			
		*Expected Outcome: 
			- The Intranet Homepage is displayed*/
		
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();		
		
		/*Step number: 2
		*Step Name: Step 2: Open Access permission form in Site's Config
		*Step Description: 
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/
		
		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);		
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps permission form in Site's Config
		*Step Description: 
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/

		info("Test Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);		
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);		
		
		/*Step number: 4
		*Step Name: Step 4: Open Move Containers permission form in Site's Config
		*Step Description: 
			- Go to Administration 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/

		info("Test Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);		
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);	
		
		button.cancel();
		manageLayout.abortPageUpdate();
		
		/*Step number: 5
		*Step Name: Step 5: Open Access permission form in Page properties
		*Step Description: 
			- Go to Administration 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/
	
		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		driver.navigate().refresh();
		
		//Test
		info("Test access tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);		
		manageLayout.checkPresentOfActionButtons(false, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		/*Step number: 6
		*Step Name: Step 6: Open Move Apps permission form in Page properties
		*Step Description: 
			- Go to Administration 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/
		
		info("Test Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);		
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID);	
		
		/*Step number: 7
		*Step Name: Step 7: Open Move Containers permission form in Page properties
		*Step Description: 
			- Go to Administration 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- "Add Permission" button is displayed*/ 

		info("Test Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);		
		manageLayout.checkPresentOfActionButtons(false,manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID);	
		
		button.cancel();
		manageLayout.abortPageUpdate();
 	}

	/**
	*<li> Case ID:128126.</li>
	*<li> Test Case Name: Select group/users permission in the Permission tab.</li>
	*<li> Pre-Condition: The label "Everyone" is not checked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_SelectGroupusersPermissionInThePermissionTab() {
		info("Test 9: Select group/users permission in the Permission tab");
		String permissionGroup="/platform/users";
		String groupPath="Platform/Users";
		String appNewUser = appGateData.getnameByIndex(3);
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
		*Step Name: Step 1: Open Access Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- Access tab is displayed*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		//Preparation
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
	
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		driver.navigate().refresh();		
		
		/*Step number: 2
		*Step Name: Step 2: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Access permission to this site*/

		info("Add permisstion to table");		
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
								manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID, 
								manageLayout.ELEMENT_PERMISSION_ACCESS_POPUP_TAB_ID);
						
		/*Step number: 3
		*Step Name: Step 3: Open Move Apps Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- Move Apps tab is displayed*/

		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
				
		/*Step number: 4
		*Step Name: Step 4: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Move Apps permission in this site*/

		manageLayout.addPremission(groupPath, "*",permissionGroup, 
									manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
									manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);
	
		
		/*Step number: 5
		*Step Name: Step 5: Open Move Containers Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- Move Containers tab is displayed*/
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
	
		/*Step number: 6
		*Step Name: Step 6: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Move Containers permission in this site*/

		manageLayout.addPremission(groupPath, "*",permissionGroup, 
											manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
											manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);
	
		manageLayout.saveChangesSiteConfig();
		info("Test permission");
		manageLayout.addApplication(pgCreateWiz.ELEMENT_APPLICATION_ADMINISTRATION_TAB,
									pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",appNewUser),
									manageLayout.ELEMENT_APPLICATION_CONTAINER);
		pgCreateWiz.addContainer("oneRow", true);
		
		/*Step number: 7
		*Step Name: Step 7: Open Access Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- Access tab is displayed*/

		
		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
	
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		driver.navigate().refresh();		
		
		info("Test access tab");
					
		/*Step number: 8
		*Step Name: Step 8: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Access permission in this page*/

		info("Add permisstion to table");		
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
											manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID,
											"UIPageFormPopupGroupMembershipSelector");
			
		
		/*Step number: 9
		*Step Name: Step 9: Open Move Apps Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- Move Apps tab is displayed*/

		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		
		/*Step number: 10
		*Step Name: Step 10: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Move Apps permission in this page*/

		manageLayout.addPremission(groupPath, "*",permissionGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
				manageLayout.ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID);

		
		/*Step number: 11
		*Step Name: Step 11: Open Move Containers Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- Move Containers tab is displayed*/

		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		
		/*Step number: 12
		*Step Name: Step 12: Select a membership
		*Step Description: 
			- Click on the button "Add permission"
			- On "Select Permission" form: Select a membership
		*Input Data: 
			
		*Expected Outcome: 
			- Selected membership is displayed in the permission table
			- User in selected membership has Move Containers permission in this page*/ 
		
		manageLayout.addPremission(groupPath, "*",permissionGroup, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
				manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID);
		
		manageLayout.saveChangesPropertiesPopup();
		
		info("Test permission");
		manageLayout.addApplication(pgCreateWiz.ELEMENT_APPLICATION_ADMINISTRATION_TAB,
									pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",appNewUser),
									manageLayout.ELEMENT_PAGE_APPLICATION_CONTAINER);	
		pgCreateWiz.addContainer("oneRow", true);
 	}

	/**
	*<li> Case ID:128262.</li>
	*<li> Test Case Name: Check the display of warning message in case there is no entry in the permission table.</li>
	*<li> Pre-Condition: The label "Everyone" is not checked.
	*	There is no entry in the Permission table in all tabs (Access, Move Apps, Move Containers) on both Site's Config and Page Properties</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckTheDisplayOfWarningMessageInCaseThereIsNoEntryInThePermissionTable() {
		info("Test 10 Check the display of warning message in case there is no entry in the permission table");
		String permissionGroup="/platform/users";
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
		*Step Name: Step 1: Open Access Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/

		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		info("Go to Site Layout form");
		navigationToolbar.goToEditSiteAddSite();
		manageLayout.goToSiteConfigPopup();
		
		info("Go to Permission tab");
		manageLayout.goToSitePermissionTab();
		Utils.pause(3000);
		
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		
		info("Delete permission");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		driver.navigate().refresh();	
		
		info("Test Access tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
		/*Step number: 2
		*Step Name: Step 2: Open Move Apps Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/

		info("Test Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		
		/*Step number: 3
		*Step Name: Step 3: Open Move Containers Permission form in Site's Config
		*Step Description: 
			- Connect to Intranet with an Administrator
			- Go to Edit 
			-
			-> Site 
			-
			-> Layout 
			-
			-> Site's Config 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/

		info("Test Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		manageLayout.abortPageUpdate();
		/*Step number: 4
		*Step Name: Step 4: Open Access Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Access
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/

		info("Go to Page Layout form");
		navigationToolbar.goToEditLayout();
		manageLayout.goToPagePropertiesPopup();
		manageLayout.goToPagePermissionTab();		
		Utils.pause(3000);
				
		info("Uncheck public mode");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		//Uncheck the checked checkbox
		uncheck(manageLayout.ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID),2);
		
		info("Delete permission");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_ACCESS);
		waitForAndGetElement(manageLayout.ELEMENT_DELETE_BUTTON
							.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));		
		manageLayout.deleteGroupPermission(permissionGroup, manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID);
		
		driver.navigate().refresh();	
		
		info("Test Access tab");
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_ACCESS_TAB_ID));
		
		/*Step number: 5
		*Step Name: Step 5: Open Move Apps Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Apps
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/

		info("Test Move Apps tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_APPS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_APPS_TAB_ID));
		
		/*Step number: 6
		*Step Name: Step 6: Open Move Containers Permission form in Page properties
		*Step Description: 
			- Go to Edit 
			-
			-> Page 
			-
			-> Edit Layout 
			-
			-> View Page Properties 
			-
			-> Permission tab 
			-
			-> Move Containers
		*Input Data: 
			
		*Expected Outcome: 
			- There is a warning message displayed as the first row "warning : nobody is granted this permission." in Permission table*/ 
		
		info("Test Move Containers tab");
		manageLayout.gotoPermissionSelector(manageLayout.PERMISSION_SELECTOR_MOVE_CONTAINERS);
		waitForAndGetElement(manageLayout.ELEMENT_PERMISSION_WARNING
						.replace("${tabName}", manageLayout.ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID));
		
		button.cancel();
		manageLayout.abortPageUpdate();
	}

}