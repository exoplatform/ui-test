package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.wiki.WikiPermission.permissionType;
import org.testng.annotations.*;


	public class Wildcard_Wiki extends Wildcard_TestConfig_4{

	/**
	*<li> Case ID:128057.</li>
	*<li> Test Case Name: Check "Admin Pages" permission in Intranet Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- There are some wiki pages already exist in Intranet wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAdminPagesPermissionInIntranetWikiSettingsWithWildcardMembershipType() {
		info("Test 1: Check Admin Pages permission in Intranet Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Admin Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			- From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Admin Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission "Admin Pages".*/
		addPermWikiSet(permissionType.Admin_Pages,"");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can administrate Intranet Wiki.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			User A can administrate Wiki:
			- Can Add/Edit wiki page.
			- Can manage wiki pages in More menu.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfAdmPage(true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT administrate Intranet Wiki.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki Settings > Permissions.
			- Untick "Admin Pages" permission for GROUP A.
			- Connect to Intranet with UserA
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/edit the wiki pages.
			- UserA can NOT "Set Permissions" for wiki pages in More menu.*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		removePermWikiSet(permissionType.Admin_Pages,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfAdmPage(false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128342.</li>
	*<li> Test Case Name: Check "Admin Pages" permission in Space Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- There are some wiki pages already exist in Wiki of SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test02_CheckAdminPagesPermissionInSpaceWikiSettingsWithWildcardMembershipType() {
		info("Test 2: Check Admin Pages permission in Space Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Admin Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			- From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Admin Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission "Admin Pages".*/
		addPermWikiSet(permissionType.Admin_Pages,"");
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can administrate Space Wiki.
		*Step Description: 
			- Log in as UserA
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			User A can administrate Wiki:
			- Can Add/Edit wiki page.
			- Can manage wiki pages in More menu.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfAdmPage(true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT administrate Space Wiki.
		*Step Description: 
			- Log in as manager of Space.
			- Go to Space Wiki Settings > Permissions.
			- Untick "Admin Pages" permission for GROUP A.
			- Log in as UserA
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/edit the wiki pages of Space.
			- UserA can NOT "Set Permissions" for wiki pages of Space in More menu.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		removePermWikiSet(permissionType.Admin_Pages,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfAdmPage(false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128058.</li>
	*<li> Test Case Name: Check "Admin Wiki" permission in Intranet Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- There are some wiki pages already exist in Intranet wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAdminWikiPermissionInIntranetWikiSettingsWithWildcardMembershipType() {
		info("Test 3: Check Admin Wiki permission in Intranet Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Admin Wiki" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Admin Wiki" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission "Admin Wiki".*/
		addPermWikiSet(permissionType.Admin_Wiki,"");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA has "Admin Wiki" permission.
		*Step Description: 
			- Log in as userA.
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can access Wiki Settings:
			- UserA can manage wiki pages: Set permissions in More menu.
			- UserA can add/edit wiki pages.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfAdmWiki(true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA does NOT have "Admin Wiki" permission.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki Settings > Permissions.
			- Untick on "Admin Wiki" for GROUP A.
			- Log in as userA.
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT access Wiki Setting.
			- UserA can manage wiki pages: Set permissions in More menu.
			- UserA can add/edit wiki pages.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		removePermWikiSet(permissionType.Admin_Wiki,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfAdmWiki(false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128343.</li>
	*<li> Test Case Name: Check "Admin Wiki" permission in Space Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- There are some wiki pages already exist in Wiki of Space.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckAdminWikiPermissionInSpaceWikiSettingsWithWildcardMembershipType() {
		info("Test 4: Check Admin Wiki permission in Space Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Admin Wiki" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Admin Wiki" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission "Admin Wiki".*/
		addPermWikiSet(permissionType.Admin_Wiki,"");
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2:Check userA has "Admin Wiki" permission.
		*Step Description: 
			- Log in as userA.
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can access Wiki Settings:
			- UserA can manage wiki pages: Set permissions in More menu.
			- UserA can add/edit wiki pages.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfAdmWiki(true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA does NOT have "Admin Wiki" permission.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki Settings > Permissions.
			- Untick on "Admin Wiki" for GROUP A.
			- Log in as userA.
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT access Wiki Setting.
			- UserA can manage wiki pages: Set permissions in More menu.
			- UserA can add/edit wiki pages.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		removePermWikiSet(permissionType.Admin_Wiki,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfAdmWiki(false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128060.</li>
	*<li> Test Case Name: Check "Edit Pages" permission in a wiki page of Intranet Wiki with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- The wiki page PAGE A already existed in Intranet Wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckEditPagesPermissionInAWikiPageOfIntranetWikiWithWildcardMembershipType() {
		info("Test 5: Check Edit Pages permission in a wiki page of Intranet Wiki with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			-Open wiki PAGE A > More > Page Permissions.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Edit Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "Edit Pages".*/
		addPermToWikiPage(title,permissionType.Edit_Pages,"");
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can edit wiki page PAGE A.
		*Step Description: 
			- Log in as userA
			- Go to Intranet Wiki > PAGE A
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view/edit/move/delete the wiki page PAGE A.
			- UserA can add new wiki page under PAGE A.
			- UserA can NOT set permission in PAGE A except userA is onwer of this wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfEditPage(title,true);
		
		/*Step number: 3
		*Step Name: Step 2: Check userA can NOT edit wiki page PAGE A.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki > PAGE A > More > Permissions.
			- Untick on "Edit Pages" for GROUP A.
			- Log in as userA.
			- Go to Intranet Wiki > PAGE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT edit PAGE A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		removePermToWikiPage(title,permissionType.Edit_Pages);
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfEditPage(title,false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128344.</li>
	*<li> Test Case Name: Check "Edit Pages" permission in a wiki page of Space Wiki with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- The wiki page PAGE A already existed in Wiki of SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckEditPagesPermissionInAWikiPageOfSpaceWikiWithWildcardMembershipType() {
		info("Test 6: Check Edit Pages permission in a wiki page of Space Wiki with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			-Open wiki PAGE A > More > Page Permissions.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "Edit Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "Edit Pages".*/
		addPermToWikiPage(title,permissionType.Edit_Pages,space);
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can edit wiki page PAGE A.
		*Step Description: 
			- Log in as userA
			- Go to Space Wiki > PAGE A
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view/edit/move/delete the wiki page PAGE A.
			- UserA can add new wiki page under PAGE A.
			- UserA can NOT set permission in PAGE A except userA is onwer of this wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfEditPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 2: Check userA can NOT edit wiki page PAGE A.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki > PAGE A > More > Permissions.
			- Untick on "Edit Pages" for GROUP A.
			- Log in as userA.
			- Go to Space Wiki > PAGE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT edit PAGE A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		removePermToWikiPage(title,permissionType.Edit_Pages);
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfEditPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128056.</li>
	*<li> Test Case Name: Check "Edit Pages" permission in Intranet Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - User A is member of GROUP A.
	- There are some wiki pages already exist in Intranet wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckEditPagesPermissionInIntranetWikiSettingsWithWildcardMembershipType() {
		info("Test 7: Check Edit Pages permission in Intranet Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with a wildcard membership.
			- Tick on "Edit Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "Edit Pages".*/
		addPermWikiSet(permissionType.Edit_Pages,"");
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can edit the wiki pages.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Intranet Wiki / open any wiki page.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/view/edit/move/delete the intranet wiki pages
			- UserA can NOT set permission in a wiki page except userA is onwer of that wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfEditPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 2: Check userA can NOT edit the wiki pages.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki Settings: Remove "Edit Pages" permission of GROUP A.
			- Log in as userA.
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT edit the intranet wiki pages.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		removePermWikiSet(permissionType.Edit_Pages,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfEditPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128341.</li>
	*<li> Test Case Name: Check "Edit Pages" permission in Space Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- There are some wiki pages already exist in Wiki of SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckEditPagesPermissionInSpaceWikiSettingsWithWildcardMembershipType() {
		info("Test 8: Check Edit Pages permission in Space Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with a wildcard membership.
			- Tick on "Edit Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "Edit Pages".*/
		addPermWikiSet(permissionType.Edit_Pages,space);
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can edit the wiki pages of Space.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Space Wiki / open any wiki page.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/view/edit/move/delete the wiki pages of SPACE A.
			- UserA can NOT set permission in a wiki page except userA is onwer of that wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfEditPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 2: Check userA can NOT edit the wiki pages of Space.
		*Step Description: 
			- Log in as manager of space.
			- Go to Space Wiki Settings: Remove "Edit Pages" permission of GROUP A.
			- Log in as userA.
			- Go to Wiki of SPACE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT edit the wiki pages of SPACE A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		removePermWikiSet(permissionType.Edit_Pages,"");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfEditPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128059.</li>
	*<li> Test Case Name: Check "View Pages" permission in a wiki page of Intranet Wiki with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- The wiki page PAGE A already existed in Intranet Wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckViewPagesPermissionInAWikiPageOfIntranetWikiWithWildcardMembershipType() {
		info("Test 9: Check View Pages permission in a wiki page of Intranet Wiki with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "View Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			- Open wiki page PAGE A > More > Page Permissions.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "View Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "View Pages".*/
		addPermToWikiPage(title,permissionType.View_Pages,"");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view PAGE A.
		*Step Description: 
			- Log in as userA.
			- Go to Intranet Wiki > PAGE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view PAGE A.
			- UserA can NOT add/edit/Set Permission on PAGE A.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfViewPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT view PAGE A.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki > PAGE A > More > Page Permissions.
			- Remove GROUP A.
			- Log in as userA.
			- Go to Intranet Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT view PAGE A.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		deletePermToWikiPage(title, groupA, "");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfViewPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128345.</li>
	*<li> Test Case Name: Check "View Pages" permission in a wiki page of Space Wiki with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- The wiki page PAGE A already existed in SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckViewPagesPermissionInAWikiPageOfSpaceWikiWithWildcardMembershipType() {
		info("Test 10 Check View Pages permission in a wiki page of Space Wiki with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "View Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			- Open wiki page PAGE A > More > Page Permissions.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "View Pages" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission "View Pages".*/
		addPermToWikiPage(title,permissionType.View_Pages,space);
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view PAGE A.
		*Step Description: 
			- Log in as userA.
			- Go to Space Wiki > PAGE A.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view PAGE A.
			- UserA can NOT add/edit/Set Permission on PAGE A.Note:To check this case clearly, should remove "View Pages" permissions on PAGE A of "*:/spaces/SPACE A" .*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfViewPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT view PAGE A.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki > PAGE A > More > Page Permissions.
			- Remove GROUP A.
			- Log in as userA.
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT view PAGE A.Note:To check this case clearly, should remove "View Pages" permissions on PAGE A of "*:/spaces/SPACE A" .*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		deletePermToWikiPage(title, groupA, space);
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfViewPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128055.</li>
	*<li> Test Case Name: Check "View Pages" permission in Intranet Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A.
	- There are some wiki pages already exist in Intranet wiki.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckViewPagesPermissionInIntranetWikiSettingsWithWildcardMembershipType() {
		info("Test 11 Check View Pages permission in Intranet Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		addWikiPage(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "View Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "View Pages" checkbox only.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group with wildcard membership are grant permission to "View Pages".*/
		addPermWikiSet(permissionType.View_Pages,"");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view the wiki pages.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Intranet Wiki / open any wiki page.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view the wiki pages.
			- UserA cannot Add/Edit/Set Permission on the wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfViewPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT view the wiki pages.
		*Step Description: 
			- Log in as admin user.
			- Go to Intranet Wiki Settings > Permission tab: Remove GROUP A.
			- Connect Intranet Wiki with userA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT view the wiki pages.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToWiki();
		deletePermWikiSet(groupA, "");
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToWiki();
		wValidate.checkDisplayOfViewPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}

	/**
	*<li> Case ID:128340.</li>
	*<li> Test Case Name: Check "View Pages" permission in Space Wiki Settings with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP A and SPACE A.
	- There are some wiki pages already exist in Wiki of SPACE A.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckViewPagesPermissionInSpaceWikiSettingsWithWildcardMembershipType() {
		info("Test 12 Check View Pages permission in Space Wiki Settings with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createSpace(space);
		addWikiPageInSpace(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "View Pages" permission for a group with wildcard membership.
		*Step Description: 
			- Log in as manager of SPACE A.
			- Go to Space Wiki.
			-From Wiki > Browse > Wiki Settings > Permission tab.
			- Select Membership: Add GROUP A with wildcard membership.
			- Tick on "View Pages" checkbox only.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group with wildcard membership are grant permission to "View Pages".*/
		addPermWikiSet(permissionType.View_Pages,space);
		addUserSpace(space,arrayUsers.get(0));
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view the wiki pages.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to SPACE A's Wiki / open any wiki page.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view the wiki pages.
			- UserA cannot Add/Edit/Set Permission on the wiki page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfViewPage(title, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userA can NOT view the wiki pages.
		*Step Description: 
			- Log in as Space manager.
			- Go to Space Wiki Settings > Permission tab: Remove GROUP A.
			- Connect Intranet Wiki with userA.
			- Go to Space Wiki.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can NOT view the wiki pages.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		deletePermWikiSet(groupA, space);
		
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToSpecificSpace(space);
		spaHome.goToWikiTab();
		wValidate.checkDisplayOfViewPage(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteAllWikiPages();
 	}}