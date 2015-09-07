package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.testng.annotations.*;


	public class Disable_User_Calendar extends Disable_User_TestConfig{
		public void createUser(){
			searchEmail = userSearchOptionData.getUserSearchOptionByIndex(3);
			membership = portMemPermisData.getContentByIndex(0);
            username = userInfoData.getUserNameByIndex(5)+getRandomString();
			password = userInfoData.getPassWordByIndex(5)+getRandomString();
			lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
			email = EMAIL_ADDRESS2;
			searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
			info("remove existed user with EMAIL_ADDRESS2");
			navToolBar.goToUsersAndGroupsManagement();
			userAndGroup.searchUser(EMAIL_ADDRESS2, searchEmail);
			if(isTextPresent(EMAIL_ADDRESS2))
			userAndGroup.deleteUser();
			
			info("Create new user");
			navToolBar.goToAddUser();
			addUserPage.addUser(username, password, email, username, lastName);
		}
	/**
	*<li> Case ID:127978.</li>
	*<li> Test Case Name: Check already shared calendar of disabled user.</li>
	*<li> Pre-Condition: User A is enabledUser A has shared a Calendar with the User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAlreadySharedCalendarOfDisabledUser() {
		info("Test 1: Check already shared calendar of disabled user");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] userGroup={DATA_USER1};
		boolean[] canEdit={true};
		
		createNewUser();
		magAc.signOut();
		magAc.signIn(username,password);
		info("Create a new calendar and share it");
		hp.goToCalendarPage();
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
		cMang.shareCalendar(calendar, userGroup,canEdit);
		
		/*Step Number: 1
		*Step Name: Step 1: Open Calendar application
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar
		*Input Data: 
			
		*Expected Outcome: 
			- The Calendar shared by the User A is dispalyed*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.checkDisplayOfCalendar(calendar, true);
		
		/*Step number: 2
		*Step Name: Step 2: Open Calendar after disable user
		*Step Description: 
			- Disable the User A
			- Back to Calendar app
		*Input Data: 
			
		*Expected Outcome: 
			- The Calendar shared by the User A is displayed*/ 
		disableUser();
		hp.goToCalendarPage();
		cMang.checkDisplayOfCalendar(calendar, true);
		
		info("delete data");
		cMang.deleteCalendar(calendar, true);
		deleteUser();
 	}

	/**
	*<li> Case ID:127977.</li>
	*<li> Test Case Name: Check disabled user in Group Calendar.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisabledUserInGroupCalendar() {
		info("Test 2: Check disabled user in Group Calendar");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Check the display of disabled user when editing group calendar
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar > Group Calendars > Edit > Show in Groups > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		hp.goToCalendarPage();
		cMang.checkUserSelectorOfGroupCalendar("Users",username,false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127980.</li>
	*<li> Test Case Name: Check disabled user in Participants screen of an event.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127981.</li>
	*<li> Test Case Name: Check disabled user in Reminders screen of an event.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127983.</li>
	*<li> Test Case Name: Check disabled user in Schedule screen of an event.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_05_06_CheckDisabledUserInParticipantsScreenOfAnEvent() {
		info("Test 3: Check disabled user in Participants screen of an event");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Add Participant
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar 
			-
			-> Add/Edit Event 
			-
			-> More Details 
			-
			-> Participants 
			-
			-> Add Participant 
			-
			-> Pick an User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		hp.goToCalendarPage();
		evMg.checkUserSelectorOfEvent(username+" "+lastName, false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127985.</li>
	*<li> Test Case Name: Check disabled user in Reminders screen of a task.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127984.</li>
	*<li> Test Case Name: Check disabled user in Task Delegation screen of a task.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_08_CheckDisabledUserInRemindersScreenOfATask() {
		info("Test 4: Check disabled user in Reminders screen of a task");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Add more reminder
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar 
			-
			-> Add/Edit Task 
			-
			-> More details 
			-
			-> Reminders 
			-
			-> Add More
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		hp.goToCalendarPage();
		tasMg.checkUserSelectorOfTask(username+" "+lastName, false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127976.</li>
	*<li> Test Case Name: Check disabled user in Share Calendar.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckDisabledUserInShareCalendar() {
		info("Test 7: Check disabled user in Share Calendar");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		createNewUser();
		disableUser();
		
		info("Create a new calendar");
		hp.goToCalendarPage();
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
		/*Step Number: 1
		*Step Name: Step 1: Check the display of disabled user when sharing calendar
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar application
			- From a Calendar, choose Share Calendar > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		cMang.checkUserSelectorOfShareCalendar(calendar,username+" "+lastName, false);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:127979.</li>
	*<li> Test Case Name: Check email invitation from edit event for disabled user.</li>
	*<li> Pre-Condition: User A is a participant of event (X)User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckEmailInvitationFromEditEventForDisabledUser() {
		info("Test 9: Check email invitation from edit event for disabled user");
		String event1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String event2=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		createUser();
		info("Add a event and participant");
		hp.goToCalendarPage();		
		evMg.goToAddEventFromActionBar();
		evMg.moreDetailsEvent();
		evMg.inputBasicDetailEvent(event1, content);
		evMg.goToParticipantsTab();
		evMg.goToInvitationParticipantForm();
		evMg.selectUserParticipants(username, content,0);
		evMg.saveInvitationParticipantForm();
		evMg.selectSendInvitation(selectInvitationOption.ALWAYS);
		evMg.saveAddEventDetails();
		waitForElementNotPresent(evMg.ELEMENT_CONFIRM_SEND_INVITATION_MESSAGE);
		cHome.goToView(selectViewOption.WEEK);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",event1));
		
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Edit event
		*Step Description: 
			- Connect to Intranet with User B
			- Edit the event (X)
		*Input Data: 
			
		*Expected Outcome: 
			- The event is edited*/
 	 	magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.openEditEventTaskPopup(event1,selectViewOption.LIST);
		evMg.inputBasicDetailEvent(event2,event2);
		evMg.saveAddEventDetails();
		cHome.goToView(selectViewOption.LIST);
		waitForAndGetElement(cMang.ELEMENT_EVENT_TASK_TITLE.replace("${name}",event2));
		
		/*Step number: 2
		*Step Name: Step 2: Check inbox email
		*Step Description: 
			- Check the Inbox email of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- Email invitation is not received*/ 
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(20000);
		cMang.checkEmailNotificationCalendar(event2,"icalendar.ics","","",false);
        switchToParentWindow();
        
		info("delete data");
		hp.goToCalendarPage();
		cMang.deleteTaskEvent(event2);
		deleteUser();
 	}

	/**
	*<li> Case ID:127982.</li>
	*<li> Test Case Name: Check event reminders email for disabled user.</li>
	*<li> Pre-Condition: reminder of event of a user A is activated</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127986.</li>
	*<li> Test Case Name: Check task reminders email for disabled user.</li>
	*<li> Pre-Condition: reminder of task of a user A is activated</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_11_CheckEventRemindersEmailForDisabledUser() {
		info("Test 10 Check event reminders email for disabled user");
		String event1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String defaultFormatTime = "24 Hours";
		String defaultFormatDate = "MM/dd/yyyy";
		String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
		String defaultDay="Monday";
		
		createNewUser();
		magAc.signOut();
		magAc.signIn(username, password);
		info("GMT+7 time");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,defaultDay,false,selectInvitationOption.ALWAYS);
		cMang.saveSetting();
		
		info("Add an event");		
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(event1,"","","",false);
		evMg.saveQuickAddEvent();
		evMg.checkDisplayOfEvent(event1,true);
		info("Add a task");	
		tasMg.goToAddTaskFromActionBar();
		tasMg.inputDataTaskInQuickForm(task1, "", "", "", false);
		tasMg.saveQuickAddTask();
		evMg.checkDisplayOfEvent(task1,true);
		
		/*Step Number: 1
		*Step Name: Step 1: Disable user
		*Step Description: 
			- Connect to Intranet with User B
			- Disable the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		disableUser();
 	 	
		/*Step number: 2
		*Step Name: Step 2: Check inbox email
		*Step Description: 
			- At the reminder time, Check the Inbox email of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- Event reminders email is not received*/ 
 	 	goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(300000);
		evMg.checkEmailNotificationReminderEvent(event1,false);
		tasMg.checkEmailNotificationReminderTask(task1,false);
        switchToParentWindow();
        
		info("delete data");
		hp.goToCalendarPage();
		cHome.goToView(selectViewOption.LIST);
		cMang.deleteAllTaskEvent();
		deleteUser();
 	}

	/**
	*<li> Case ID:128082.</li>
	*<li> Test Case Name: Check the display of event/task with user after re-enabling.</li>
	*<li> Pre-Condition: User A is disableUser B is admin User A has shared a Calendar for User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckTheDisplayOfEventtaskWithUserAfterReenabling() {
		info("Test 12 Check the display of event/task with user after re-enabling");
		String calendar = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String event = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] userGroup={DATA_USER1};
		boolean[] canEdit={true};
		
		createNewUser();
		magAc.signOut();
		magAc.signIn(username,password);
		info("Create a new calendar and share it");
		hp.goToCalendarPage();
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(calendar, calendar,null);
        cMang.saveAddCalendar();
		cMang.shareCalendar(calendar, userGroup,canEdit);
		
		/*Step Number: 1
		*Step Name: Step 1: Add Event/Task
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Calendar application
			- Add Event/Task in shared calendar by disable user before
		*Input Data: 
			
		*Expected Outcome: 
			- Event/Task is added in shared calendar successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		disableUser();
		
		info("add event to calendar");
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(event,"",getDate(0,"MM/dd/yyyy HH")+":00",getDate(0,"MM/dd/yyyy HH")+":30",false,calendar);
		evMg.saveQuickAddEvent();
		evMg.checkDisplayOfEvent(event,true);
		
		/*Step number: 2
		*Step Name: Step 2: Re-enable user
		*Step Description: 
			- Re-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Calendar shared by the User A is displayed*/
		enableUser();
		
		/*Step number: 3
		*Step Name: Step 3: View event/task
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Calendar application
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the added event/task at Step 1*/ 
		magAc.signOut();
		magAc.signIn(username,password);
		hp.goToCalendarPage();
		evMg.checkDisplayOfEvent(event,true);
		
		info("delete data");
		cMang.deleteCalendar(calendar, true);
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteUser();
 	}}