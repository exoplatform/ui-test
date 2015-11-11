package org.exoplatform.selenium.platform.wiki.functional.permissions.wikipermissions;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.Permission;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.testng.annotations.*;


public class Wiki_Permissions_WikiPermissions_CheckPermission extends WikiBase{
	ManageAccount magAcc;
	Button button;
	PlatformPermission per;
	NavigationToolbar navTool;
	Permission perWiki;
	BasicAction ba;
	SpaceManagement spMag;
	ManageMember magMem;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		button = new Button(driver);
		per = new PlatformPermission(driver);
		navTool = new NavigationToolbar(driver, this.plfVersion);
		perWiki = new Permission(driver);
		ba = new BasicAction(driver);
		spMag = new SpaceManagement(driver, this.plfVersion);
		magMem = new ManageMember(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:120916.
	 * Test Case Name: Verify View permission of an user.
	 * Pre-Condition: - The User A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test01_VerifyViewPermissionOfAnUser() {
		info("Test 1: Verify View permission of an user");
		String[] user = {"mary"};
		String title = "Delete permission 113606";
		String space = "space113606";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);

		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check permission for a User
		 *Step Description: 
			- Check the User A permission
			- In the permission table, check the View Pages checkbox of the User A, uncheck all other checkbox
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- The permission View Pages is checked
			- Other checkbox are unchecked*/
		perWiki.editSpacePermission(user[0], true, false, false, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if theuser can view the page
		 *Step Description: 
			- Log in as User A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user can view the wiki page but cannot see Edit Page, Add Page; Move Page and Delete Page and Page Permissions from More menu
			- The user can not see Wiki Settings from Browse menu*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		waitForAndGetElement(ELEMENT_MORE_LINK);
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		click(ELEMENT_MORE_LINK);
		waitForElementNotPresent(ELEMENT_DELETE_LINK);
		waitForElementNotPresent(ELEMENT_MOVE_PAGE_LINK);

		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120917.
	 * Test Case Name: Verify Edit permission for a user.
	 * Pre-Condition: - The User A has already the permission: Edit Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test02_VerifyEditPermissionForAUser() {
		info("Test 2: Verify Edit permission for a user");
		String[] user = {"mary"};
		String title = "Delete permission 113607";
		String space = "space113607";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check Edit Pages permission for User A
		 *Step Description: 
			- Check the User A permission
			- In the permission table, check the Edit Pages checkbox of the User A
			- Uncheck permission "Admin Pages" and "Admin Wiki"
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- Edit permission and View permission are selected
			- Other permission are unchecked*/
		perWiki.editSpacePermission(user[0], true, true, false, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if the User A has Edit Pages permission
		 *Step Description: 
			- Login as User A
			- Go to space 
			-
			-> Wiki
			- Select a wiki page
		 *Input Data: 

		 *Expected Outcome: 
			- The user A can view the wiki page and can see Edit Page, Add Page; Move Page and Delete Page from More menu
			- The user can not see Wiki Settings from Browse menu
			- The user A can not see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_INFO_LINK);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_LINK);

		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120918.
	 * Test Case Name: Verify Admin Pages permission of a user.
	 * Pre-Condition: 
	 * Post-Condition: - The User A has already the permission: Admin Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 */
	@Test
	public  void test03_VerifyAdminPagesPermissionOfAUser() {
		info("Test 3: Verify Admin Pages permission of a user");
		String[] user = {"demo"};
		String title = "Delete permission 113638" + getRandomNumber();
		String space = "space113638" + getRandomNumber();

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.DEVELOPER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check Admin Pages permission
		 *Step Description: 
			- Check the User A permission
			- In the permission table, check the Admin Pages checkbox of the User A
			- Uncheck permission "Admin Wiki"
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- All permissions are selected except permission "Admin Wiki"*/
		perWiki.editSpacePermission(user[0], true, true, true, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if theuser A has Admin Pages permission
		 *Step Description: 
			- Login as user A
			- Go to the space 
			-
			-> Wiki
			- Select a wiki page
		 *Input Data: 

		 *Expected Outcome: 
			- The user can not see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.DEVELOPER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_PERMISSION_LINK);
		
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120919.
	 * Test Case Name: Verify Admin Wiki permission for a user.
	 * Pre-Condition: - The User A has already the permission: Admin Wiki in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test04_VerifyAdminWikiPermissionForAUser() {
		info("Test 4: Verify Admin Wiki permission for a user");
		String[] user = {"mary"};
		String title = "Delete permission 113605";
		String space = "space113605";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(1, user);
		perWiki.editSpacePermission(user[0], true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check the Admin Wiki permission for User A
		 *Step Description: 
			- Check the User A permission
			- In the permission table, check the Admin Wiki checkbox of the User A
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- All permission are selected*/
		perWiki.editSpacePermission(user[0], true, true, true, true,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if theuser A has Admin Wiki permission
		 *Step Description: 
			- Login as user A
			- Go to the space 
			-
			-> Wiki
			- Select a wiki page
		 *Input Data: 

		 *Expected Outcome: 
			- The user can see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForAndGetElement(ELEMENT_WIKI_SETTING_LINK);
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_PERMISSION_LINK);

		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120920.
	 * Test Case Name: Verify View permission for a group.
	 * Pre-Condition: - The Group A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test05_VerifyViewPermissionForAGroup() {
		info("Test 5: Verify View permission for a group");
		String[] user = {"Development"};
		String title = "Delete permission 113638" + getRandomNumber();
		String space = "space113638" + getRandomNumber();

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.DEVELOPER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, user);
		perWiki.editSpacePermission("/developers", true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check permission for a Group
		 *Step Description: 
			- Check the Group A permission
			- In the permission table, check the View Pages checkbox of the Group A and uncheck other checkbox
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- The permission View Pages is selected
			- Other permissions are unchecked*/
		perWiki.editSpacePermission("/developers", true, false, false, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if the group can view the page
		 *Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user can view the wiki page but cannot see Edit Page, Add Page; Move Page and Delete Page and Page Permissions from More menu
			- The user can not see Wiki Settings from Browse menu*/ 
		magAcc.userSignIn(userType.DEVELOPER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		waitForAndGetElement(ELEMENT_MORE_LINK);
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		click(ELEMENT_MORE_LINK);
		waitForElementNotPresent(ELEMENT_DELETE_LINK);
		waitForElementNotPresent(ELEMENT_MOVE_PAGE_LINK);
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120921.
	 * Test Case Name: Verify Admin Pages permission of a group.
	 * Pre-Condition: - The Group A has already the permission : Admin Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test06_VerifyAdminPagesPermissionOfAGroup() {
		info("Test 6: Verify Admin Pages permission of a group");
		String[] user = {"Platform/Content Management"};
		String title = "Delete permission 113637";
		String space = "space113637";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, user);
		perWiki.editSpacePermission("/web-contributors", true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Check the Admin Pages permission for Group A
		 *Step Description: 
			- Check the Group A permission
			- In the permission table, check the Admin Pages checkbox of the Group A
			- Uncheck "Admin Wiki" permission
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- All permission are selected except permission "Admin Wiki"*/
		perWiki.editSpacePermission("/web-contributors", true, true, true, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check ifGroup A has Admin Pages permission
		 *Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user can not see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_PERMISSION_LINK);

		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120922.
	 * Test Case Name: Verify Admin Wiki permission for a group.
	 * Pre-Condition: - The Group A has already the permission: Admin Wiki in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test07_VerifyAdminWikiPermissionForAGroup() {
		info("Test 7: Verify Admin Wiki permission for a group");
		String[] user = {"Platform/Content Management"};
		String title = "Delete permission 113635";
		String space = "space113635";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.PUBLISHER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, user);
		perWiki.editSpacePermission("/web-contributors", true, true, true, true,2);

		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Select the Admin Wiki permission
		 *Step Description: 
			- Check the Group A permission
			- In the permission table, check the Admin Wiki checkbox of the Group A
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- All permission are checked*/
		perWiki.editSpacePermission("/web-contributors", true, true, true, true,2);
		/*Step number: 3
		 *Step Name: Step 3: Check ifGroup A has Admin Wiki permission
		 *Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user can see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.PUBLISHER);
		magMem.acceptInvitation(space);
		
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForAndGetElement(ELEMENT_WIKI_SETTING_LINK);
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_PERMISSION_LINK);

		/*By element_space = By.linkText(space);
		info("Go to wiki page of space " + space);
		Utils.pause(1000);
		if (isElementNotPresent(ELEMENT_WIKI_LINK_IN_SPACE)){
			magMem.goToMySpacePage();
			click(element_space);
			Utils.pause(2000);
		}
		click(ELEMENT_WIKI_LINK_IN_SPACE);	
		waitForAndGetElement(ELEMENT_PAGE_NOT_FOUND);*/
		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}

	/**
	 * Case ID:120923.
	 * Test Case Name: Verify Edit permission for a group.
	 * Pre-Condition: - The Group A has already the permission: Edit Pages in the Wiki Settings
	- Some wiki pages are already created in this space
	 * Post-Condition: 
	 */
	@Test
	public  void test08_VerifyEditPermissionForAGroup() {
		info("Test 8: Verify Edit permission for a group");
		String[] userGroup = {"Development"};
		String title = "Delete permission 113636";
		String space = "space113636";

		spMag.goToAllSpaces();
		spMag.addNewSpace(space, space);
		spMag.goToSpaceMenu("Space Settings");
		click(spMag.ELEMENT_MEMBER_TAB);
		magMem.inviteSingleUser(userType.DEVELOPER);
		goToWikiFromSpace(space);

		ba.addBlankWikiPage(title, title, 0);
		perWiki.deleteSpacePermission("*:/spaces/" + space);
		perWiki.addSpacePermission(2, userGroup);
		perWiki.editSpacePermission("/developers", true, true, true, true,2);
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add permission for space
		 *Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		 *Input Data: 

		 *Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		 *Step Name: Step 2: Delete Edit Pages permission for Group A
		 *Step Description: 
			- Check the Group A permission
			- In the permission table, check the Edit Pages checkbox of the Group A
			- Uncheck permission "Admin Pages" and "Admin Wiki"
			- Click Save 
			-
			-> OK
		 *Input Data: 

		 *Expected Outcome: 
			- Edit Pages and View Page permission are selected
			- Other permission are not selected*/
		perWiki.editSpacePermission("/developers", true, true, false, false,2);
		/*Step number: 3
		 *Step Name: Step 3: Check if the Group A has Edit Pages permission
		 *Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		 *Input Data: 

		 *Expected Outcome: 
			- The user can view the wiki page and can see Edit Page, Add Page; Move Page and Delete Page from More menu
			- The user can not see Wiki Settings from Browse menu
			- The user A can not see Page Permissions from More menu*/ 
		magAcc.userSignIn(userType.DEVELOPER);
		magMem.acceptInvitation(space);
		goToWikiFromSpace(space);
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",title));
		waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
		waitForAndGetElement(ELEMENT_EDIT_PAGE_LINK);
		
		click(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_MY_DRAFT);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		
		click(ELEMENT_MORE_LINK);
		waitForAndGetElement(ELEMENT_PAGE_INFO_LINK);
		waitForElementNotPresent(ELEMENT_PAGE_PERMISSION_LINK);

		magAcc.userSignIn(userType.ADMIN);
		spMag.goToAllSpaces();
		spMag.deleteSpace(space);
	}
}
