package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Wildcard_Calendar extends Wildcard_TestConfig_1{

	/**
	*<li> Case ID:128075.</li>
	*<li> Test Case Name: Check "Access" permission in Personal Calendar with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUPA. UserB is NOT member of GROUPA.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAccessPermissionInPersonalCalendarWithWildcardMembershipType() {
		info("Test 1: Check Access permission in Personal Calendar with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		/*Step Number: 1
		*Step Name: Step 1:Share a Personal Calendar to a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as userC.
			-Go to Calendar > Personal Calendars > Share Personal Calendar > Select membership.
			- Add GROUPA with wildcard membership.
			- Untick on "Edit Permission".
		*Input Data: 
			
		*Expected Outcome: 
			- User A has the ability to access the shared calendar.*/
		createCalendar(calendar);
		cMang.shareCalendarToGroup(calendar, groupA, "*", false);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can access Personal Calendar of userC.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Calendar > Shared Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view the Personal Calendar of userC.
			- UserA can Remove/Refresh the calendar of userC.
			- UserA can view only the events and tasks in Calendar of userC.*/
		magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfShareCalendar(true, false, calendar);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT access Personal Calendar of userC.
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Calendar > Shared Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT see Personal Calendar of userC.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(1), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfShareCalendar(false, false, calendar);
 	 	
 	 	deleteAllUsers();
 	 	deleteCalendar(calendar);
 	}

	/**
	*<li> Case ID:128336.</li>
	*<li> Test Case Name: Check "Edit" permission in Personal Calendar with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUPA. UserB is NOT member of GROUPA.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckEditPermissionInPersonalCalendarWithWildcardMembershipType() {
		info("Test 2: Check Edit permission in Personal Calendar with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		/*Step Number: 1
		*Step Name: Step 1:Share a Personal Calendar to a group with wildcard membership.
		*Step Description: 
			- Connect to Intranet as userC
			-Go to Calendar > Personal Calendars > Share > Select membership.
			- Add a group with wildcard membership.
			- Tick on "Edit Permission".
		*Input Data: 
			
		*Expected Outcome: 
			- Users in GROUPA are grant the permission to edit Personal Calendar of userC.*/
		createCalendar(calendar);
		cMang.shareCalendarToGroup(calendar, groupA, "*", true);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can edit Personal Calendar of userC.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Calendar > Shared Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can view/add/edit/delete/export the events and tasks in Personal Calendar of userC.
			- UserA can import/export the Personal Calendar of userC.*/
		magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfShareCalendar(true, true, calendar);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit Personal Calendar of userC.
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Calendar > Shared Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can not edit Personal Calendar of userC.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(1), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfShareCalendar(false, false, calendar);
 	 	
 	 	deleteAllUsers();
 	 	deleteCalendar(calendar);
 	}

	/**
	*<li> Case ID:128074.</li>
	*<li> Test Case Name: Check "Edit" permission on Group Calendar with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUPA. UserB is NOT member of GROUPA.
	- A Group Calendar CAL C already existed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckEditPermissionOnGroupCalendarWithWildcardMembershipType() {
		info("Test 3: Check Edit permission on Group Calendar with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit" permission for a group with wildcard membership on a Group Calendar.
		*Step Description: 
			- Connect to Intranet with calendar moderator
			-Go to Calendar > Group Calendars > Add/Edit a Group Calendar CAL C > Show in Groups tab.
			- Add a group, then select Role: select wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to edit the group calendar.*/
		createCalendar(calendar,groupA);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can edit group calendar CAL C.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Calendar > Group Calendars > CAL C.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can edit/import/export the group calendar CAL C.
			- UserA can view/add/edit/delete/export the events and tasks in CAL C.*/
		magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfCalendar(true, true, calendar);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit group calendar CAL C.
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Calendar > Group Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit the group calendar CAL C.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(1), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfCalendar(false, false, calendar);
 	 	
 	 	deleteAllUsers();
 	 	deleteCalendar(calendar);
 	}

	/**
	*<li> Case ID:128339.</li>
	*<li> Test Case Name: Check "Edit" permission on Space Calendar with wildcard membership type.</li>
	*<li> Pre-Condition: - UserA is member of GROUPA. UserB is NOT member of GROUPA.
	- A Space Calendar CAL C already existed: UserC is member of space. UserA and userB are NOT member of space.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckEditPermissionOnSpaceCalendarWithWildcardMembershipType() {
		info("Test 4: Check Edit permission on Space Calendar with wildcard membership type");
		String groupA = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String password="123456";
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create new user");
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(2,password);
		addGroup(groupA);
		addUserToGroup(arrayUsers.get(0),groupA);
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		/*Step Number: 1
		*Step Name: Step 1:Set "Edit" permission for a group with wildcard membership on a Space Calendar.
		*Step Description: 
			- Connect to Intranet as userC.
			-Go to Calendar > Group Calendars > Edit a Space Calendar CAL C > Show in Groups tab.
			- Add a group, then select Role: select wildcard membership.
		*Input Data: 
			
		*Expected Outcome: 
			- Users in this group are grant the permission to edit the space calendar.*/
		editPermGroupCalendar(space, groupA);
		
		/*Step number: 2
		*Step Name: Step 2:Check userA can edit Space's calendar CAL C.
		*Step Description: 
			- Connect to Intranet with UserA
			- Go to Calendar > Group Calendars > CAL C.
		*Input Data: 
			
		*Expected Outcome: 
			- UserA can edit/import/export the space calendar CAL C.
			- UserA can view/add/edit/delete/export the events and tasks in CAL C.*/
		magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(0), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfCalendar(true, true, space);
 	 	
		/*Step number: 3
		*Step Name: Step 3:Check userB can NOT edit Space's calendar CAL C.
		*Step Description: 
			- Connect to Intranet with UserB
			- Go to Calendar > Group Calendars.
		*Input Data: 
			
		*Expected Outcome: 
			- UserB can NOT edit the space calendar CAL C.*/ 
 	 	magAc.signOut();
 	 	magAc.signIn(arrayUsers.get(1), password);
 	 	hp.goToCalendarPage();
 	 	cMang.checkAccessibilityOfCalendar(false, false, space);
 	 	
 	 	deleteAllUsers();
 	}}