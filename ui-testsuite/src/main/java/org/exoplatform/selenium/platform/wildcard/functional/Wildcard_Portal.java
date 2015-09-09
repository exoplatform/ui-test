package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Wildcard_Portal extends Wildcard_TestConfig_6{

	/**
	*<li> Case ID:128034.</li>
	*<li> Test Case Name: Check "Add Navigation" for a Group Site of an user with wildcard membership type.</li>
	*<li> Pre-Condition: - The UserA is in groupA with manager role or * role.
	- PageA added using "Group Navigation Portlet":Access and Edit Permissions are granted for "*" role of GroupA</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128033.</li>
	*<li> Test Case Name: Check "Manage Group Site" permission of an user with wildcard membership type.</li>
	*<li> Pre-Condition: - The UserA is in groupA with manager role or * role.
	- PageA added using "Group Navigation Portlet":Access and Edit Permissions are granted for "*" role of GroupA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckAddNavigationForAGroupSiteOfAnUserWithWildcardMembershipType() {
		info("Test 1: Check Add Navigation for a Group Site of an user with wildcard membership type");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		addPage(title,groupA,"*");
		/*Step Number: 1
		*Step Name: Step 1:UserA accesses PageA
		*Step Description: 
			- Log in as userA.
			- Access PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can access PageA.
			- Button "Add Navigation" is displayed.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portGrpNav.verifyAddNavigationPerm(title, true, groupA);
		
		/*Step number: 2
		*Step Name: Step 2:Add Navigation for Group Site of GroupA.
		*Step Description: 
			- Click Add Navigation.
			- Select Group Site of GroupA
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can see and add Navigation for GroupA.*/ 
		/*Step number: 2
		*Step Name: Step 2:UserA can manage Group Site
		*Step Description: 
			Manage Group Site of GroupA:
			- Edit Navigation.
			- Edit Properties.
			- Delete Navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can manage Group Sites "GroupA": Edit Navigation, Edit Properties, Delete Navigation.*/ 
		portGrpNav.verifyManageGroupSitePerm(groupA, true);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128032.</li>
	*<li> Test Case Name: Check Access Permission on a Site for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- SiteA already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAccessPermissionOnASiteForAGroupWithWildcardMembershipType() {
		info("Test 3: Check Access Permission on a Site for a group with wildcard membership type");
		String portalName = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		
		/*Step Number: 1
		*Step Name: Step 1:Add "Access" Permission on a site to * role of a group.
		*Step Description: 
			- Log in as admin user.
			- From Administration > Portal > Sites > Edit Site Configuration vs. a site (ex siteA).
			- Click on Permission tab > Access button.
			- Add GROUP1 with * membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to access SiteA.*/
		addPortal(portalName,false,groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can access SiteA
		*Step Description: 
			- Log in by UserA.
			- Access SiteA:http://[server IP]:8080/portal/SiteA
		*Input Data: 
			
		*Expected Outcome: 
			- The UserA can view the SiteA.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMgSite.verifyPermOnSite(portalName, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT access SiteA
		*Step Description: 
			- Log in by UserB.
			- Access SiteA:http://[server IP]:8080/portal/SiteA
		*Input Data: 
			
		*Expected Outcome: 
			- The UserB can NOT view the SiteA.*/ 
		magAc.signOut();
		driver.get(baseUrl);
		magAc.signIn(arrayUsers.get(1),password);
		portMgSite.verifyPermOnSite(portalName, false);
		driver.get(baseUrl);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128040.</li>
	*<li> Test Case Name: Check Application access for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should have "*:/platform/Content Management" role to edit a page/site.
	- A category CatA already existed in Administration > Applications: any user can access CatA.
	- CatA contains some portlets or gadgets.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckApplicationAccessForAGroupWithWildcardMembershipType() {
		info("Test 4: Check Application access for a group with wildcard membership type");
		String categoryName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = appAddGateinData.getRandomIndexByType(1);
		String nameApp = appAddGateinData.newDisplayName.get(index);
		String des = appAddGateinData.newDescription.get(index);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		addUserToGroup(arrayUsers.get(1),"Content Management");
		navTool.goToApplication();
		appRegistry.addACategory(categoryName,categoryName,categoryName );
		appRegistry.addApplicationToCategory(categoryName, nameApp,des);
		
		/*Step Number: 1
		*Step Name: Step 1:Add Permission to access Applications for group with wildcard membership
		*Step Description: 
			- Log in as admin user
			- From Administration > Applications > CatA > Select a Portlet/Gadget > Default Access Permission Settings tab.
			- Add Permission to GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to view application in Portal Composer.*/
		appRegistry.editPortletPermission(categoryName, nameApp, groupA, "*");
		appRegistry.deletePortletPermission("administrators");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view application in CatA.
		*Step Description: 
			- Log in as userA.
			- Edit a Page Layout or Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- Application is displayed in the Portal Composer/Applications tab.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		navTool.goToEditLayout();
		pagEditor.verifyAppPermission(categoryName, nameApp, true);
		pagCW.saveChangesPageEditor();
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT view application in CatA.
		*Step Description: 
			- Log in as userB.
			- Edit a Page Layout or Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- Application is NOT displayed in the Portal Composer/Applications tab.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		navTool.goToSiteExplorer();
		navTool.goToEditLayout();
		pagEditor.verifyAppPermission(categoryName, nameApp, false);
		pagCW.saveChangesPageEditor();
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128039.</li>
	*<li> Test Case Name: Check Category access for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should have "*:/platform/Content Management" role to edit a page/site.
	- A category CatA already existed in Administration > Applications.
	- CatA contains some portlets and gadgets.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckCategoryAccessForAGroupWithWildcardMembershipType() {
		info("Test 5: Check Category access for a group with wildcard membership type");
		String categoryName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		addUserToGroup(arrayUsers.get(1),"Content Management");
		navTool.goToApplication();
		appRegistry.addACategory(categoryName,categoryName,categoryName );
		/*Step Number: 1
		*Step Name: Step 1:Add Permission to access Category for group with wildcard membership
		*Step Description: 
			- Log in as admin user.
			- From Administration > Applications > Edit Category > Permission Setting.
			- Add Access Permission to GROUPs only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to view CatA in Portal Composer.*/
		appRegistry.editCategory(categoryName, "", "", groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view CatA.
		*Step Description: 
			- Log in as userA.
			- Edit a Page Layout or Edit a Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view CatA in Applications tab of Portal Composer.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.goToSiteExplorer();
		navTool.goToEditLayout();
		pagEditor.verifyCatPermission(categoryName, true);
		pagCW.saveChangesPageEditor();
		
		/*Step number: 3
		*Step Name: Step 2:Check userB can NOT view CatA.
		*Step Description: 
			- Log in as userB.
			- Edit a Page Layout or Edit a Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT view CatA in Applications tab of Portal Composer.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		navTool.goToSiteExplorer();
		navTool.goToEditLayout();
		pagEditor.verifyCatPermission(categoryName, false);
		pagCW.saveChangesPageEditor();
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128029.</li>
	*<li> Test Case Name: Check delete action for the wildcard membership type.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckDeleteActionForTheWildcardMembershipType() {
		info("Test 6: Check delete action for the wildcard membership type");
		/*Step Number: 1
		*Step Name: Step 1:Check delete action for wildcard membership.
		*Step Description: 
			- Connect to Intranet with an admin user
			- Go toAdministration > Community > Manage Community > Membership Management.
			- Click on Delete button vs. wildcard membership in Membership List.
		*Input Data: 
			
		*Expected Outcome: 
			- The wildcard membership type is displayed on top of List.
			- User cannot delete the wildcard membership type from Membership Management.*/ 
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToMembershipTab();
		userAndGroup.deleteMembership("*", false);
 	}

	/**
	*<li> Case ID:128036.</li>
	*<li> Test Case Name: Check Edit Permission on a Page for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should have the role "*:/platform/Content Management" to Edit a page/site.
	- PageA already existed: Any user can access PageA.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckEditPermissionOnAPageForAGroupWithWildcardMembershipType() {
		info("Test 7: Check Edit Permission on a Page for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		addUserToGroup(arrayUsers.get(1),"Content Management");
		addPage(title,"","");
		/*Step Number: 1
		*Step Name: Step 1:Assign wildcard membership to group to edit a page.
		*Step Description: 
			- Log in as admin user.
			- From Administration > Portal > Pages > Edit PageA > View Page Properties > Permission tab.
			- Click on Edit button.
			- Add Edit Permission to GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to edit PageA.*/
		navTool.goToEditLayout();
		pagCW.editViewProperties("",groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can edit PageA.
		*Step Description: 
			- Log in asuserA.
			- Connect to portal/Intranet/PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view/edit PageA from menu:
			-> Edit > Page > Edit Layout.
			- UserA can edit PageA from other ways If he/she has permission.
			-> Administration > Portal > Pages > Edit Page.
			-> Administration > Portal > Sites > Edit Navigation > Edit Node's Page.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		navTool.verifyEditPagePerm(title, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit PageA.
		*Step Description: 
			- Log in asuserB.
			- Connect to portal/Intranet/PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit PageA.
			- UserB can NOT see Edit Layout menu.
			- UserB can NOT edit PageA through Admininstration > Portal.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		navTool.verifyEditPagePerm(title, false);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128031.</li>
	*<li> Test Case Name: Check Edit Permission on a Site for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should have the role "*:/platform/Content Management" to edit a site/page.
	- SiteA already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckEditPermissionOnASiteForAGroupWithWildcardMembershipType() {
		info("Test 8: Check Edit Permission on a Site for a group with wildcard membership type");
		String portalName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Content Management");
		addUserToGroup(arrayUsers.get(1),"Content Management");
		addPortal(portalName,true,groupA,"*");
		/*Step Number: 1
		*Step Name: Step 1:Add "Edit" Permissions on a site to * role of a group.
		*Step Description: 
			- Log in as admin user.
			- From Administration > Portal > Sites > Edit Site Configuration vs. a site (ex siteA).
			- Click on Permission tab > Edit button.
			- Add GROUP1 with * membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to edit SiteA.*/

		/*Step number: 2
		*Step Name: Step 2:Check userA can edit SiteA
		*Step Description: 
			- Log in by UserA.
			- Access SiteA:http://[server IP]:8080/portal/SiteA.
			- Edit Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can edit SiteA.*/
		magAc.signOut();
		driver.get(baseUrl);
		magAc.signIn(arrayUsers.get(0), password);
		navTool.verifyEditSitePerm(portalName, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit SiteA
		*Step Description: 
			- Log in by UserB.
			- Access SiteA:http://[server IP]:8080/portal/SiteA.
			- Edit Site Layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit SiteA.*/ 
		magAc.signOut();
		driver.get(baseUrl);
		magAc.signIn(arrayUsers.get(1), password);
		navTool.verifyEditSitePerm(portalName, false);
		driver.get(baseUrl);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128037.</li>
	*<li> Test Case Name: Check Move Apps Permission on a Page for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should be member of Administration group.
	- PageA already existed: Any user can view/edit page.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckMoveAppsPermissionOnAPageForAGroupWithWildcardMembershipType() {
		info("Test 9: Check Move Apps Permission on a Page for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String portletId1 = appLayData.id.get(1);
		String portletName1 = appLayData.title.get(3);
		String tab= appLayData.category.get(1);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Administration");
		addUserToGroup(arrayUsers.get(1),"Administration");
		addPage(title,"","");
		
		/*Step Number: 1
		*Step Name: Step 1: Add Permission to Move App on PageA for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet with an admin user
			-FromAdministration > Portal > Pages > Edit PageA > View Page Properties > Permissions tab.
			- Click on Move Apps button: setting a wildcard permission for a group (User A is member of this group with a wildcard permission).
		*Input Data: 
			
		*Expected Outcome: 
			- User A has permission to Move an application in the page*/
		navTool.goToEditLayout();
		pagCW.editPermInViewProperties(groupA,"*",true,true,false,true);
		
		/*Step number: 2
		*Step Name: Step 2:Check UserA can Move apps on PageA.
		*Step Description: 
			- Log in as userA.
			- Connect to portal/Intranet/PageA.
			- Edit page layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/move the portlets/gadgets on PageA layout by drag & drop.
			- UserA can edit/delete portlets/gadgets on PageA*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		navTool.goToEditLayout();
		pagCW.verifyDragDropAppPerm(tab,portletName1,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId1),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE,true);
		pagEditor.verifyEditDeleteAppPerm(portletName,true);
		pagCW.saveChangesPageEditor();
		
		/*Step number: 3
		*Step Name: Step 3:Check UserB can NOT Move apps on PageA.
		*Step Description: 
			- Log in as userB.
			- Connect to portal/Intranet/PageA.
			- Edit page layout.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT add/move the portlets/gadgets on PageA layout by drag & drop.
			- UserB can NOT edit/delete portlets/gadgets on PageA*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		navTool.goToEditLayout();
		pagCW.verifyDragDropAppPerm("",portletName1,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId1),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE,false);
		pagEditor.verifyEditDeleteAppPerm(portletName,false);
		pagCW.saveChangesPageEditor();
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128038.</li>
	*<li> Test Case Name: Check Move Containers Permission on a Page for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT member of GROUP1.
	- UserA and userB should be member of Administration group.
	- PageA already existed: Any user can access/edit.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckMoveContainersPermissionOnAPageForAGroupWithWildcardMembershipType() {
		info("Test 10 Check Move Containers Permission on a Page for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String containerId = contaiData.getContainerIdByIndex(0);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}","Platform"));
		addUserToGroup(arrayUsers.get(0),"Administration");
		addUserToGroup(arrayUsers.get(1),"Administration");
		addPage(title,"","");
		
		/*Step Number: 1
		*Step Name: Step 1:Add Permission to Move Containers on PageA for a group with wildcard membership.
		*Step Description: 
			- Log in as admin user
			- From Administration > Portal > Pages > Edit PageA > View Page Properties > Permissions tab.
			- Click on Move Containers button.
			- Add Move Containers Permission to GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to move containers on PageA.Note:If each container has a specific restricted permission, the behaviour will follow the specific permission of container.*/
		navTool.goToEditLayout();
		pagCW.editPermInViewProperties(groupA,"*",true,true,true,false);
		
		/*Step number: 2
		*Step Name: Step 2: Check userA can Move Containers on PageA.
		*Step Description: 
			- Log in as userA.
			- Connect to portal/Intranet/PageA.
			- Edit page layout > Container tab.
			-> Try to drag&drop new container to PageA.
			-> Try to move containers on PageA by drag&drop.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can add/move Containers on PageA layout by drag & drop.
			- UserA can delete Containers in PageA layout.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		navTool.goToEditLayout();
		pagCW.verifyDragDropConPerm(containerId, true);
		pagCW.saveChangesPageEditor();
		
		/*Step number: 3
		*Step Name: Step 2: Check userB can NOT Move Containers on PageA.
		*Step Description: 
			- Log in as userB.
			- Connect to portal/Intranet/PageA.
			- Edit page layout > Container tab.
			-> Try to drag&drop new container to PageA.
			-> Try to move containers on PageA by drag&drop.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT add/move Containers on PageA layout by drag & drop.
			- UserB can NOT delete Containers in PageA layout.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		navTool.goToEditLayout();
		pagCW.verifyDragDropConPerm(containerId, false);
		pagCW.saveChangesPageEditor();
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128027.</li>
	*<li> Test Case Name: Check the wildcard membership type in Membership Management.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckTheWildcardMembershipTypeInMembershipManagement() {
		info("Test 11 Check the wildcard membership type in Membership Management");
		String des = "Any membership type";
		/*Step Number: 1
		*Step Name: Step 1:Check wildcard (*) membership type in Membership Management.
		*Step Description: 
			- Connect to Intranet with an admin user
			- Go to Administration > Community > Manage Community > Membership Management.
			- Check Membership name and Description of wildcard (*) membership type.
		*Input Data: 
			
		*Expected Outcome: 
			- The wildcard membership type is displayed on top of list.
			- Wildcard membership is displayed with description: "Any membership type".*/ 
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.verifyMembership("*", des,true);
 	}

	/**
	*<li> Case ID:128028.</li>
	*<li> Test Case Name: Check the wildcard membership type in the Membership list.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128030.</li>
	*<li> Test Case Name: Check the wildcard membership type on Edit Membership of Group Management.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_13_CheckTheWildcardMembershipTypeInTheMembershipList() {
		info("Test 12 Check the wildcard membership type in the Membership list");
		password="123456";
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		/*Step Number: 1
		*Step Name: Step 1:Check wildcard (*) membership type in Membership field of Add Member.
		*Step Description: 
			- Connect to Intranet with an admin user
			- Go toAdministration > Community > Manage Community > Group Management:Select a group.
			- In form Add Member, click on Membership list.
		*Input Data: 
			
		*Expected Outcome: 
			- The wildcard membership type is listed in "Membership" field.*/
		addGroup(groupA);
		
		/*Step number: 2
		*Step Name: Step 2:Check wildcard (*) membership type displayed in Group Info.
		*Step Description: 
			- Add a member with wildcard membership to group.
		*Input Data: 
			
		*Expected Outcome: 
			- Member with wildcard membership is added and listed in Group Info.*/ 
		addUserToGroup(arrayUsers.get(0),groupA);
		userAndGroup.verifyUserMemInTable(arrayUsers.get(0),"*",true);
		
		/*Step number: 2
		*Step Name: Step 2:Check the display of wildcard membership type in Edit Membership form.
		*Step Description: 
			- Click on Edit button vs. the member added to group.
		*Input Data: 
			
		*Expected Outcome: 
			- The "Edit Membership" form is displayed to User Name with wildcard membership.*/
		userAndGroup.verifyMembershipInEditForm(arrayUsers.get(0),"*",true);
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
 	}

	/**
	*<li> Case ID:128042.</li>
	*<li> Test Case Name: Check View Container block for a group with wildcard membership type.</li>
	*<li> Pre-Condition: UserA is member of GROUP1. UserB is NOT member of GROUP1.PageA already existed with at least a container block that contains a portlet inside.Ex: Container block contains a Calendar gadget (with Access Permission for any user).</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckViewContainerBlockForAGroupWithWildcardMembershipType() {
		info("Test 14 Check View Container block for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String containerId = contaiData.getContainerIdByIndex(0);
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		hp.goToHomePage();
		navTool.goToAddPage();
		pagCW.addAPageSimple(title, title);
		/*Step Number: 1
		*Step Name: Step 1:Add View Permission to a group with wildcard membership.
		*Step Description: 
			- Log in as admin user.
			- Go to Administration > Portal > Pages > Edit PageA > Edit a container block > Access Permission.
			- Add Permission to GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to view the Container Block.*/
		info("add container and application into it");
		navTool.goToEditLayout();
		pagCW.addContainer(containerId);
		navTool.goToEditLayout();
		pagCW.addApp("",portletName,pagCW.ELEMENT_APPLICATION_APPLICATION.replace("${name}",portletId),pagCW.ELEMENT_PAGEEDITOR_VIEWPAGE);
		info("set permission");
		navTool.goToEditLayout();
		pagEditor.goToEditPortlet(portletName);
		click(pagCW.ELEMENT_PAGEEDITOR_ACCESS_PERM_BTN,0,true);
        pagCW.selectAccessPerm("","");
        pagCW.saveChangesApplication();
        pagCW.editPermInContainer("Container", groupA, "*", false, true, true);
        
		/*Step number: 2
		*Step Name: Step 2:Check userA can view portlet in container.
		*Step Description: 
			- Log in as UserA.
			- Access PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can see the portlet inside container block.*/
        magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextPresent("Add Navigation");
		
		/*Step number: 3
		*Step Name: Step 2:Check userB can NOT view portlet in container.
		*Step Description: 
			- Log in as UserB.
			- Access PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT see the portlet inside container block.*/ 
		hp.goToHomePage();
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextNotPresent("Add Navigation");
		
		info("delete data");
		hp.goToHomePage();
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128035.</li>
	*<li> Test Case Name: Check View Permission on a Page for a group with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUP1. UserB is NOT a member of GROUP1.
	- PageA already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckViewPermissionOnAPageForAGroupWithWildcardMembershipType() {
		info("Test 15 Check View Permission on a Page for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		addPage(title, "", "");
		/*Step Number: 1
		*Step Name: Step 1:Assign wildcard membership to group to view a page.
		*Step Description: 
			- Log in as admin user.
			- From Administration > Portal > Pages > Edit PageA > View Page Properties > Permission tab. 
			- Click on Access button.
			- Add Access Permission to GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to access PageA*/
		navTool.goToEditLayout();
		pagCW.editPermInViewProperties(groupA,"*",false,true,true,true);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can access PageA.
		*Step Description: 
			- Log in asuserA.
			- Connect to portal/Intranet/PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can access the PageA*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextPresent("Add Navigation");
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT access PageA.
		*Step Description: 
			- Log in asuserA.
			- Connect to portal/Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- The UserB can NOT view/access the PageA*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextNotPresent("Add Navigation");
		
		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}

	/**
	*<li> Case ID:128041.</li>
	*<li> Test Case Name: Check View Portlet permission for a group with wildcard membership type.</li>
	*<li> Pre-Condition: UserA is member of GROUP1. UserB is NOT member of GROUP1.PageA already existed with at least a portlet (ex: Calendar gadget or Bookmark gadget).</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckViewPortletPermissionForAGroupWithWildcardMembershipType() {
		info("Test 16 Check View Portlet permission for a group with wildcard membership type");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		password="123456";
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		
		/*Step Number: 1
		*Step Name: Step 1:Add View Permission to a group with wildcard membership.
		*Step Description: 
			- Log in as admin user
			- From Administration > Portal > Pages > Edit PageA > Edit a Portlet > Access Permission.
			- Add View Permission for GROUP1 only with wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group are grant permission to view portlet.*/
		addPage(title,groupA,"*");
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can view this portlet.
		*Step Description: 
			- Log in as UserA.
			- Access PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view the portlet in PageA.*/
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextPresent("Add Navigation");
		
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT view this portlet.
		*Step Description: 
			- Log in as UserB.
			- Access PageA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT view the portlet in PageA.*/ 
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1), password);
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		waitForTextNotPresent("Add Navigation");

		info("delete data");
		deleteAllUsers();
		deleteGroup(groupA);
		deletePage(title);
 	}}