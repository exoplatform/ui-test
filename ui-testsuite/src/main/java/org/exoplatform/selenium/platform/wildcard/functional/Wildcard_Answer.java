package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Wildcard_Answer extends Wildcard_TestConfig_1{

	/**
	*<li> Case ID:128062.</li>
	*<li> Test Case Name: Check "Moderator" permission in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUPA. UserB is NOT member of GROUPA.
	- Category CatA is in Answers page.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckModeratorPermissionInACategoryWithWildcardMembershipType() {
		info("Test 1: Check Moderator permission in a category with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String cat1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String ques1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		addCatAndQues(cat1,ques1,link);
		/*Step Number: 1
		*Step Name: Step 1:Set "Moderator" permission on a category for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			-Go to Answers > Add/Edit Category CatA > Permissions tab.
			- Select Role: Select GROUPA with wildcard membership.
			- Set this role as Moderator.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in GROUPA are grant the permission to moderate the category CatA.*/
 	 	aCatMg.setPermission(cat1, groupA,true,true);
 	 	
		/*Step number: 2
		*Step Name: Step 2:Check "Moderator" permission of userA in group.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Answers/ Open the category CatA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can edit the CatA.
			- UserA can manage questions in Manage Questions screen.
			- UserA can edit/delete/move the questions in CatA.*/
 	 	magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToAnswer();
 	 	click(aCatMg.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat1));
 	 	qMang.checkMngQuesForm(true, ques1);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check "Moderator" permission of userB.
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Answers/ Open the category CatA.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit the CatA.
			- UserB can NOT see "Manage Questions" button.
			- UserB can NOT edit/delete/move the questions in CatA.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(DATA_USER3,DATA_PASS);
 	 	hp.goToAnswer();
 	 	qMang.checkMngQuesForm(false, ques1);
 	 	
 	 	deleteAllUsers();
 	 	deleteCat(cat1);
 	 	
 	}

	/**
	*<li> Case ID:128061.</li>
	*<li> Test Case Name: Check "Restricted Audience" in a category with wildcard membership type.</li>
	*<li> Pre-Condition: - User A is member of GROUPA. UserB is NOT member of GROUPA.
	- Category CatA is in Answers page.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckRestrictedAudienceInACategoryWithWildcardMembershipType() {
		info("Test 2: Check Restricted Audience in a category with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String cat1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String ques1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = fData.getAttachFileByArrayTypeRandom(1);
		
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		addCatAndQues(cat1,ques1,link);
		/*Step Number: 1
		*Step Name: Step 1:Set "Restricted Audience" of a category for a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as admin user.
			- Go to Answers page > Add/Edit Category CatA > Permission tab.
			- Select Role: select GROUPA with a wildcard permission.
			- Keep checkbox "Restricted Audience" is ticked.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in group with wildcard membership are grant permission to access the answers category.*/
		aCatMg.setPermission(cat1, groupA,true,false);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can access CatA.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Answers page
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can access CatA category.
			- UserA can view the Questions and Answers in CatA.*/
		magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToAnswer();
 	 	aCatMg.checkAccessibilityOfCat(cat1,true, ques1);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT access CatA
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Answers page.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT access CatA category.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(DATA_USER3,DATA_PASS);
 	 	hp.goToAnswer();
 	 	aCatMg.checkAccessibilityOfCat(cat1,false, ques1);
 	 	
 	 	deleteAllUsers();
 	 	deleteCat(cat1);
 	 	
 	}}