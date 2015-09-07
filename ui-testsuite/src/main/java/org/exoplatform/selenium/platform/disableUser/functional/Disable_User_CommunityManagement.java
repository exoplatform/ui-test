package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Disable_User_CommunityManagement extends Disable_User_TestConfig{
		
	/**
	*<li> Case ID:127955.</li>
	*<li> Test Case Name: Check rows look for enable/disable users.</li>
	*<li> Pre-Condition: Enabled and Disabled users status are available</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127951.</li>
	*<li> Test Case Name: Check the Status Combo list in the Manage Community page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckRowsLookForEnabledisableUsers() {
		info("Test 1: Check rows look for enable/disable users");
		createNewUser();
		String[] users={username,DATA_USER3};
		/*Step Number: 1
		*Step Name: Step 1: Open User Management
		*Step Description: 
			- Log in as admin.
			- Go to Administration 
			-
			-> Community 
			-
			-> Manage Community.
			- Choose the tab "User Management".
		*Input Data: 
			
		*Expected Outcome: 
			- The list of users is displayed.
			- The Status combo list is displayed.*/
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.checkStatusDropBox();
 	 	disableUser();
 	 	
		/*Step number: 2
		*Step Name: Step 2: Select "All" status
		*Step Description: 
			- Click the down-arrow icon of the Status field.
			- Select the status "All".
		*Input Data: 
			
		*Expected Outcome: 
			- Both enabled/disabled users are displayed in the list.
			- Rows of disabled userslook visually different to rows of enabled users.*/ 
 	 	userAndGroup.checkDisplayAllUsers(users);
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127956.</li>
	*<li> Test Case Name: Check toggles and their tooltips for enabled/disabled users.</li>
	*<li> Pre-Condition: Enabled and Disabled users status are available</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckTogglesAndTheirTooltipsForEnableddisabledUsers() {
		info("Test 3: Check toggles and their tooltips for enabled/disabled users");
		createNewUser();
		String[] users={username,DATA_USER3};
		navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.checkStatusDropBox();
 	 	disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Open User Management
		*Step Description: 
			- Log in as admin.
			- Go to Administration 
			-
			-> Community 
			-
			-> Manage Community.
			- Choose the tab "User Management".
		*Input Data: 
			
		*Expected Outcome: 
			- The list of users is displayed: enabled and disabled
			- A new Column "Enabled" is displayed before the column "Action"
			- Toggles are displayed in the column: + For enabled users: toggle displays Yes.+ For disabled user: toggle displays No.*/
 	 	userAndGroup.checkDisplayAllUsers(users);
 	 	userAndGroup.checkDisplayEnableUser(DATA_USER3);
 	 	userAndGroup.checkDisplayDisableUser(username);
 	 	
		/*Step number: 2
		*Step Name: Step 2: Check tooltip of No button.
		*Step Description: 
			- Hover cursor over the toggle "No".
		*Input Data: 
			
		*Expected Outcome: 
			- The tooltip is Enable User.*/

		/*Step number: 3
		*Step Name: Step 3: Check tooltip of Yes button.
		*Step Description: 
			- Hover cursor over the toggle "Yes".
		*Input Data: 
			
		*Expected Outcome: 
			- The tooltip is Disable User*/ 
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127959.</li>
	*<li> Test Case Name: Delete a disabled user by administrator.</li>
	*<li> Pre-Condition: Enable and Disable users status are available</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127958.</li>
	*<li> Test Case Name: Edit a disabled user by administrator.</li>
	*<li> Pre-Condition: Enabled and Disabled users are available.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_05_DeleteADisabledUserByAdministrator() {
		info("Test 4: Delete a disabled user by administrator");
		String name=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Open User Management
		*Step Description: 
			- Log in as admin.
			- Go to Administration 
			-
			-> Community 
			-
			-> Manage Community.
			- Choose the tab "User Management".
		*Input Data: 
			
		*Expected Outcome: 
			- The list of users is displayed: enabled and disabled
			- The column "Action" is displayed with 2 icons: Edit and Delete.*/
		userAndGroup.goToEditUserInfo(username);
		userAndGroup.editUserInfo_AccountTab("", "",name, "");
		
		/*Step number: 2
		*Step Name: Step 2: Delete a disabled user
		*Step Description: 
			- From a Disabled user, click on the icon "Delete".
		*Input Data: 
			
		*Expected Outcome: 
			- The disabled user is removed from the list*/ 
		userAndGroup.deleteUser(username);
	}
 }