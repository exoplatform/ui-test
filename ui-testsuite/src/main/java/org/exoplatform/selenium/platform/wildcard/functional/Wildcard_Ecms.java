package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.administration.ContentAdministration.mainEcmFunctions;
import org.exoplatform.selenium.platform.administration.ContentAdministration.specificEcmFunctions;
import org.exoplatform.selenium.platform.administration.ContentAdministration.specificView;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.testng.annotations.*;


	public class Wildcard_Ecms extends Wildcard_TestConfig_2{

	/**
	*<li> Case ID:128068.</li>
	*<li> Test Case Name: Check "Access Drive" permission with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAccessDrivePermissionWithWildcardMembershipType() {
		info("Test 1: Check Access Drive permission with wildcard membership type");
		/*Step Number: 1
		*Step Name: Step 1:Set "Access Drive" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user
			-Go to Content Administration Explorer > Drives > Add/Edit a Drive > Permission field.
			- Select a group with wildcard membership. (UserA is in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to access this drive.*/
		info("add permission to drive");
		navTool.goToContentAdministration();
		caPage.addGroupPermToDrive("Collaboration", groupA, "*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "Access Drive" permission of an user with wildcard membership in group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / or SE / or Documents of Space.
			- Click on "Show Drives" button.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see this Drive in list.
			- UserA can access this Drive.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		hp.goToDocuments();
		SEHome.checkDisplayOfDrive("Collaboration", true);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128072.</li>
	*<li> Test Case Name: Check "Edit Right" in categories added/edited in ECM with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/ECMS-7219
	*/
	@Test (groups="pending")
	public  void test02_CheckEditRightInCategoriesAddededitedInECMWithWildcardMembershipType() {
		info("Test 2: Check Edit Right in categories added/edited in ECM with wildcard membership type");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String lifeCycle ="Content Addition";
		String nameAction = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String targetPath = "root";
		String workspace="collaboration";
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit Right" on category in ECM for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Advanced > Categories > Add/Edit a category > Set Permissions.
			- Select Membership: add a group with wildcard membership. (User A in this group).
			- Tick on "Add Node Right" and "Remove Right" checkboxes.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to edit the nodes in category from SE.Note:User should have "Edit Right" and "Remove Right" to modify/rename a node.*/
		info("add new category");
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		caPage.goToSpecificFunctions(specificEcmFunctions.CATEGORIES);
		caPage.addCategories(name,nameAction, lifeCycle, targetPath,workspace,targetPath,false,true,true,groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "Edit Right" on category in SE of an user in this group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to SE.
			- Open the category added/edited above.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add new nodes, rename/modify/delete the nodes of category.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath(name, "Collaboration");
		createNewFile(title, content);
		info("Delete new file document");
		SEHome.deleteData(name);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteCategory(name);
 	}

	/**
	*<li> Case ID:128070.</li>
	*<li> Test Case Name: Check "Manage Lock" permission with wildcard membership type.</li>
	*<li> Pre-Condition: - User A is member of group with a wildcard permission
	- A node (File, ...) already exist in SE or Documents of Space.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckManageLockPermissionWithWildcardMembershipType() {
		info("Test 3: Check Manage Lock permission with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Manage Lock" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Repositories > Locks > Manage Lock.
			- Select a group with wildcard membership. (UserA is in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this groups are grant the permission to manage the locks on nodes.*/
		info("add permission to lock");
		navTool.goToContentAdministration();
		caPage.addGroupPermToLock(groupA,"*");
		
		magAc.signOut();
		magAc.signIn(DATA_USER2,DATA_PASS);
		navTool.goToSiteExplorer();
		SEHome.goToPath("documents", "Collaboration");
		createNewFile(title, content);
		info("lock document");
		SEHome.lockNode(title);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Manage Lock" permission of an user in group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to SE or Documents in Space.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can manage the locks on nodes: Lock a node or Unlock a node locked by another user.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath("documents", "Collaboration");
		SEHome.unlockNode(title);
		SEHome.deleteData(title);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128069.</li>
	*<li> Test Case Name: Check "Manage Tags" permission with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission
	- Tags for nodes already exist in Documents, SE, or Documents of Space.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckManageTagsPermissionWithWildcardMembershipType() {
		info("Test 4: Check Manage Tags permission with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		navTool.goToSiteExplorer();
		createNewFile(title, content);
		info("Add a tag to the Content");
		SEHome.selectNode(title);
		SEHome.addTag(tag);
		
		/*Step Number: 1
		*Step Name: Step 1:Set "Manage Tags" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > > Explorer > Tags > Tag Permission Manager.
			- Select Membership: select a group with wildcard membership. (User A is in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to manage tags.*/
		info("add permission to tag");
		navTool.goToContentAdministration();
		caPage.addGroupPermToTag(groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "Manage Tags" permission of an user with wildcard membership in group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / SE / Documents in a Space.
			- Click on Tag Cloud > Edit Tags.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can edit/delete the tags.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.deleteTag(tag);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		navTool.goToSiteExplorer();
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:128064.</li>
	*<li> Test Case Name: Check "Modify Right" in a node with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission
	- A node A (File, ...) already exist in Documents / SE / Documents of Space</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/ECMS-7225
	*/
	@Test (groups="pending")
	public  void test05_CheckModifyRightInANodeWithWildcardMembershipType() {
		info("Test 5: Check Modify Right in a node with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/", "Collaboration");
		createNewFile(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Modify Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user or owner of a node.
			-Go to Documents > Open a node > Permissions.
			- Select Membership: Add a group with wildcard membership. (UserA is in group).
			- Tick on "Modify Right" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to modify the node.*/
		info("add permission to node");
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/"+title, "Collaboration");
		SEHome.goToPermission();
		caPage.addGroupPermToNode(groupA, "*",true,true,false);
		
		/*Step number: 2
		*Step Name: Step 2:Set "Modify Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / SE / Documents in Space.
			- Open the above node.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can view and edit the node*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/"+title, "Collaboration");
		SEHome.editDocument("",content2);
		CreNewDoc.saveAndClose();
		Utils.pause(3000);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/"+title, "Collaboration");
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:128063.</li>
	*<li> Test Case Name: Check "Read Right" in a node with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission
	- A node (File, ...) already exist in SESE is shared for all users, and the view for normal user</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckReadRightInANodeWithWildcardMembershipType() {
		info("Test 6: Check Read Right in a node with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		navTool.goToSiteExplorer();
		SEHome.goToPath("documents", "Collaboration");
		createNewFile(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Read Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user or owner of a node.
			-Go to Documents > Open a node > Permissions.
			- Select Membership: Add a group with wildcard membership. (UserA is in group).
			- Tick on "Read Right" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to view the node.*/
		info("add permission to node");
		/*navTool.goToSiteExplorer();
		SEHome.goToPath("documents/"+title, "Collaboration");*/
		SEHome.goToPermission();
		EcmsPerm.deletePermissionNode("any");
		caPage.addGroupPermToNode(groupA, "*",true,false,false);
		
		/*Step number: 2
		*Step Name: Step 2:Set "Read Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / SE / Documents in Space.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can view the node.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath("documents", "Collaboration");
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title));
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		navTool.goToSiteExplorer();
		SEHome.goToPath("documents/"+title, "Collaboration");
		SEHome.deleteData(title);
 	}

	/**
	*<li> Case ID:128071.</li>
	*<li> Test Case Name: Check "Read Right" in categories added/edited in ECM functions with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckReadRightInCategoriesAddededitedInECMFunctionsWithWildcardMembershipType() {
		info("Test 7: Check Read Right in categories added/edited in ECM functions with wildcard membership type");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String sub = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String lifeCycle ="Content Addition";
		String nameAction = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String targetPath = "root";
		String workspace="collaboration";
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Read Right" on category in ECM for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Advanced > Categories > Add/Edit a category > Set Permissions.
			- Select Membership: add a group with wildcard membership. (User A in this group).
			- Tick on "Read Right" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to view the nodes in category from SE.*/
		info("add new category");
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		caPage.goToSpecificFunctions(specificEcmFunctions.CATEGORIES);
		caPage.addCategories(name,nameAction, lifeCycle, targetPath,workspace,targetPath,true,false,false,groupA,"*");
		caPage.addSubCat(name, sub);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Read Right" on category in SE of an user in this group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to SE.
			- Open the category added/edited above.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can view the nodes in category.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath(name, "Collaboration");
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", sub));
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteCategory(name);
 	}

	/**
	*<li> Case ID:128065.</li>
	*<li> Test Case Name: Check "Remove Right" in a node with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission
	- A node A (File, ...) already exist in Documents</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckRemoveRightInANodeWithWildcardMembershipType() {
		info("Test 8: Check Remove Right in a node with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/", "Collaboration");
		createNewFile(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "Remove Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user or owner of a node.
			-Go to Documents > Open a node > Permissions.
			- Select Membership: Add a group with wildcard membership. (UserA is in group).
			- Tick on "Remove Right" checkbox.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to remove the node.*/
		info("add permission to node");
		/*navTool.goToSiteExplorer();
		SEHome.goToPath("sites/"+title, "Collaboration");*/
		SEHome.goToPermission();
		caPage.addGroupPermToNode(groupA, "*",true,false,true);
		
		/*Step number: 2
		*Step Name: Step 2:Set "Remove Right" permission on a node for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / SE / Documents in Space.
			- Open the above node.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can view and remove the node*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites/", "Collaboration");
		SEHome.deleteData(title);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128337.</li>
	*<li> Test Case Name: Check "Remove Right" in categories added/edited in ECM with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckRemoveRightInCategoriesAddededitedInECMWithWildcardMembershipType() {
		info("Test 9: Check Remove Right in categories added/edited in ECM with wildcard membership type");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String sub = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String lifeCycle ="Content Addition";
		String nameAction = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String targetPath = "root";
		String workspace="collaboration";
		info("add user to content admin group");
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Remove Right" on category in ECM for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Advanced > Categories > Add/Edit a category > Set Permissions.
			- Select Membership: add a group with wildcard membership. (User A in this group).
			- Tick on "Read Right" and "Remove Right" checkboxes.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to remove the nodes in category from SE.*/
		info("add new category");
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		caPage.goToSpecificFunctions(specificEcmFunctions.CATEGORIES);
		caPage.addCategories(name,nameAction, lifeCycle, targetPath,workspace,targetPath,true,false,true,groupA,"*");
		caPage.addSubCat(name, sub);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Remove Right" on category in SE of an user in this group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to SE.
			- Open the category added/edited above.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view/delete the nodes of category.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath(name, "Collaboration");
		SEHome.deleteData(sub);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deleteCategory(name);
 	}

	/**
	*<li> Case ID:128066.</li>
	*<li> Test Case Name: Check "Using dialog form" permission with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckUsingDialogFormPermissionWithWildcardMembershipType() {
		info("Test 10 Check Using dialog form permission with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Using dialog form" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Templates > Documents > Add/Edit a Document Template > Dialog tab > Permission field.
			- Select a group with wildcard membership. (User A in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to Add/Edit the documents that used this template.*/
		info("add permission to template dialog");
		navTool.goToContentAdministration();
		caPage.editPermOfTemplateDialog("Illustrated Web Content",groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "Using dialog form" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / or SE / or Documents in Space.
			- Add/Edit a document using the above template.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the document template, and can add/edit a document that used this template.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToAddNewContent();
		CreNewDoc.createNewDoc(selectDocumentType.ILLUSTRATEDWEBCONTENT);
		CreNewDoc.addNewWebContent(title, content);
		CreNewDoc.saveAndClose();
		Utils.pause(3000);
		SEHome.deleteData(title);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128338.</li>
	*<li> Test Case Name: Check "Using View" permission with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckUsingViewPermissionWithWildcardMembershipType() {
		info("Test 11 Check Using View permission with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tabName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] tab={"View Permissions"};
		String[] actions={"Permissions"};
		specificView[] view ={specificView.ITEM,specificView.WEB};
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		/*Step Number: 1
		*Step Name: Step 1:Set "Using View" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user
			-Go to Content Administration Explorer > Views > Add/Edit a View > Permission tab > Select Membership.
			- Select a group with wildcard membership. (UserA is in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to use this View.*/
		info("add new view");
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.EXPLORER);
		caPage.goToSpecificFunctions(specificEcmFunctions.VIEW);
		caPage.addView(title, tabName, tab,groupA,"*");
		
		info("add new view to a drive");
		caPage.goToSpecificFunctions(specificEcmFunctions.DRIVES);
		caPage.editDrives("Collaboration",view,title);
		
		/*Step number: 2
		*Step Name: Step 2:Check "Using View" permission of an user with wildcard membership in group.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / or SE / or Documents of Space.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the icon of this View in right panel.
			- UserA can use this View.
			- Action buttons that are set in tabs of View will be shown.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.openDrives();
		SEHome.selectADrive("Collaboration");
		SEHome.checkActionInView(title,actions);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128067.</li>
	*<li> Test Case Name: Check "View template" permission  with wildcard membership type.</li>
	*<li> Pre-Condition: - The User A is member of group with a wildcard permission.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckViewTemplatePermissionWithWildcardMembershipType() {
		info("Test 12 Check View template permission  with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites", "Collaboration");
		createNewFile(title, content);
		/*Step Number: 1
		*Step Name: Step 1:Set "View template" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Templates > Documents > Add/Edit a Document Template > View tab > Permission field.
			- Select a group with wildcard membership. (User A in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to view the documents that used this template.*/
		info("add permission to template view");
		navTool.goToContentAdministration();
		caPage.editPermOfTemplateView("Web Content",groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "View template" permission for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents / or SE / or Documents in Space.
			- Open a document that used the above template.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view the document that used the above template.
			- The document is displayed correctly following the document template.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.goToPath("sites", "Collaboration");
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title));
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128073.</li>
	*<li> Test Case Name: Check "View" permission in a Saved Query added/edited in ECM with wildcard membership type.</li>
	*<li> Pre-Condition: - User A is member of group with a wildcard permission.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckViewPermissionInASavedQueryAddededitedInECMWithWildcardMembershipType() {
		info("Test 13 Check View permission in a Saved Query added/edited in ECM with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");		
		/*Step Number: 1
		*Step Name: Step 1:Set "View Right" in a query for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Content Administration > Advanced > Queries > Add/Edit a Query > Add Permissions.
			- Select a group with wildcard membership. (UserA is in this group).
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant permission to view this query.*/
		info("add new query");
		navTool.goToContentAdministration();
		caPage.goToSpecificMainFunctions(mainEcmFunctions.ADVANCED);
		caPage.goToSpecificFunctions(specificEcmFunctions.QUERIES);
		caPage.addQueries(title,"","",groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check "View Right" in a query of an user in group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Documents or SE > Saved Searches.
			- Click on Advanced Search > Saved Query.
		*Input Data: 
			
		*Expected Outcome: 
			- User A can view the saved query in Saved Searches tab.
			- User A can view and execute the saved query.Note:To show Advanced Search: Enable "Show Slidebar" in Preferences.*/ 
		info("login as userA");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		SEHome.checkQueryInSavedQuery(title);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}}