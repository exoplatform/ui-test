package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;
import org.testng.annotations.*;


	public class TASK_CalendarIntegration extends TASK_TestConfig_1{
		
		public void setTimezone(){
			String defaultFormatTime = "24 Hours";
			String defaultFormatDate = "MM/dd/yyyy";
			String defaultTimeZone = "(GMT +07:00) Asia/Ho_Chi_Minh";
			hp.goToCalendarPage();
			cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
			cMang.changeSettingCalendar("Week",defaultTimeZone,defaultFormatDate.toLowerCase(),defaultFormatTime,null,null,null);
			cMang.saveSetting();
		}
	/**
	*<li> Case ID:131777.</li>
	*<li> Test Case Name: Check a task calendar can be hidden.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckATaskCalendarCanBeHidden() {
		info("Test 1: Check a task calendar can be hidden");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- Add projectA from contextual menu of Projects, with option Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Open Calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- projectA is in Task Calendars*/
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		
		/*Step number: 4
		*Step Name: Step 4: Check a task calendar can be hidden
		*Step Description: 
			- Click on Settings > Displayed Calendars > uncheck projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is hidden in Calendar*/ 
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.goToDisplayCalendarTab();
		cMang.showHideCalendar(prj1+" Tasks", false);
		cMang.saveSetting();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", false);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131773.</li>
	*<li> Test Case Name: Check a task calendar will appear for manager and participants.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckATaskCalendarWillAppearForManagerAndParticipants() {
		info("Test 2: Check a task calendar will appear for manager and participants");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users= {DATA_USER2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- Add projectA from contextual menu of Projects, with option Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Share projectA with userA
		*Step Description: 
			- Share projectA with userA as participant
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is shared*/
		mgProject.shareProject(prj1, users, false);
		
		/*Step number: 4
		*Step Name: Step 4: Check a task calendar will appear for manager and participants
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- projectA is in Task Calendars*/
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		
		/*Step number: 5
		*Step Name: Step 5: Login as userA
		*Step Description: 
			- Login as userA
		*Input Data: 
			
		*Expected Outcome: 
			- login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 6
		*Step Name: Step 6: Check a task calendar will appear for manager and participants
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- projectA is in Task Calendars*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131779.</li>
	*<li> Test Case Name: Check Color of the calendar in Calendar app.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created with red color, with Calendar Integration option</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckColorOfTheCalendarInCalendarApp() {
		info("Test 3: Check Color of the calendar in Calendar app");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color = colorData.getClassNameByArrayTypeRandom(1);
		hp.goToTasks();
		mgProject.addProject(prj1,"", true);
		mgProject.selectColor(prj1, color);
		/*Step Number: 1
		*Step Name: Step 1: Check Color of the calendar in Calendar app
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- calendar projectA has red color*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfColorTaskCal(prj1+" Tasks", color);
		

		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131783.</li>
	*<li> Test Case Name: Check display of readonly task view panel.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-154
	*/
	@Test (groups="pending")
	public  void test04_CheckDisplayOfReadonlyTaskViewPanel() {
		info("Test 4: Check display of readonly task view panel");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String status = flowData.getFlowByArrayTypeRandom(2);
		String priority = priorityData.getPriorityByArrayTypeRandom(2);
		setTimezone();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- Add projectA from contextual menu of Projects, with option Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Add taskA
		*Step Description: 
			- add taskA with workplan to projectA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(0,"dd"), 0, "00:00", "02:00",false);
		mgTask.editTaskDescription(task1, des);
		mgTask.editTaskPriority(task1, priority);
		mgTask.editTaskStatus(task1, status);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		
		/*Step number: 4
		*Step Name: Step 5: Open Calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened*/
		hp.goToCalendarPage();
		
		/*Step number: 5
		*Step Name: Step 6: Open taskA
		*Step Description: 
			- Click on taskA in list view
		*Input Data: 
			
		*Expected Outcome: 
			readonly task view panel should display all fields according to the following mapping:
			- task > task
			- description > note
			- work planned from > Start date
			- work planned to > End date
			- status > status
			- priority > priority
			- assigned to : delegated to*/ 
		tasMg.checkDetailTask(task1, des, getDate(0,"MM/dd/yyyy")+" 00:00", getDate(0,"MM/dd/yyyy")+" 02:00", status, priority, DATA_USER1);
		tasMg.checkPermissionOfTask(task1,false);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1,false);
 	}

	/**
	*<li> Case ID:131771.</li>
	*<li> Test Case Name: Check display of Task Calendar.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131772.</li>
	*<li> Test Case Name: Check each project has its own calendar.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_06_CheckDisplayOfTaskCalendar() {
		info("Test 5: Check display of Task Calendar");
		info("Test 6: Check each project has its own calendar");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- Add projectA from contextual menu of Projects, with option Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Add spaceB
		*Step Description: 
			- Add spaceB
		*Input Data: 
			
		*Expected Outcome: 
			- spaceB is created*/
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		waitForAndGetElement(mgProject.ELEMENT_TASK_APP_ICON);
		
		/*Step number: 4
		*Step Name: Step 4: Open spaceB in Task page
		*Step Description: 
			- Open Task page, tick Calendar Integration of spaceB
		*Input Data: 
			
		*Expected Outcome: 
			- spaceB has calendar integration*/
		hp.goToTasks();
		mgProject.enableCalendar(space1,true);
		
		/*Step number: 5
		*Step Name: Step 5: Check each project has its own calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- projectA,spaceB are in Task Calendars*/
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		cMang.checkDisplayOfTaskCal(space1+" Tasks", true);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1,false);
 	}

	/**
	*<li> Case ID:131775.</li>
	*<li> Test Case Name: Check each user has a task calendar called Tasks.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-239
	*/
	@Test (groups="pending")
	public  void test07_CheckEachUserHasATaskCalendarCalledTasks() {
		info("Test 7: Check each user has a task calendar called Tasks");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create taskA
		*Step Description: 
			- Add taskA with workplan
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.selectOptionTask(optionTask.All_Tasks);
		
		/*Step number: 3
		*Step Name: Step 3: Assignee taskA to me
		*Step Description: 
			- Assignee taskA to me
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is assigneed to me*/
		mgTask.addTaskDirectly(task1,false);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(0,"dd"), 0, "00:00", "02:00",false);
		
		/*Step number: 4
		*Step Name: Step 4: Check each user has a task calendar called Tasks
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- in Task Calendars, a task calendar called Tasks that displays taskA*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal("Tasks", true);
		tasMg.checkDetailTask(task1, " ", " ", " ", " ", " ", DATA_USER1);
		
		info("delete data");
		hp.goToTasks();
		mgTask.selectOptionTask(optionTask.All_Tasks);
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:131780.</li>
	*<li> Test Case Name: Check icon before Work Planned field in task detail.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131781.</li>
	*<li> Test Case Name: Uncheck icon before Work Planned field in task detail.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_12_CheckIconBeforeWorkPlannedFieldInTaskDetail() {
		info("Test 8: Check icon before Work Planned field in task detail");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- Add projectA from contextual menu of Projects, with option Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Add taskA
		*Step Description: 
			- add taskA with workplan to projectA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(0,"dd"), 0, "", "",true);
		
		/*Step number: 4
		*Step Name: Step 4: Click on calendar icon to remove in calendar
		*Step Description: 
			- In task details, click on icon in front of the Work Planned field (tooltip= Click to remove in calendar)
		*Input Data: 
			
		*Expected Outcome: 
			- the icon becomes not highlighted and the tooltip changes to Click to show from calendar*/
		mgTask.showInCalendar(task1,false);
		
		/*Step number: 5
		*Step Name: Step 5: Add taskB
		*Step Description: 
			- add taskB with workplan to projectA
		*Input Data: 
			
		*Expected Outcome: 
			- taskB is created*/
		mgTask.addTask(prj1, task2);
		mgTask.editTaskWorkPlan(task2, getDate(0,"dd"), 0, getDate(2,"dd"), 0, "", "",true);
		
		/*Step number: 6
		*Step Name: Step 6: Open Calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened
			- projectA is in Task Calendars
			- taskB is shown in calendar
			-taskA is not shown in calendar*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		tasMg.checkDisplayOfTask(task2,getDate(0,"dd"),true);
		tasMg.checkDisplayOfTask(task1,getDate(0,"dd"),false);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131787.</li>
	*<li> Test Case Name: Check Task app and Calendar app share the same user preference for Timezone.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- select timezone GTM+7
	- projectA is created with Calendar Integration option
	- taskA is added to projectA with show in calendar option and has workplan</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckTaskAppAndCalendarAppShareTheSameUserPreferenceForTimezone() {
		info("Test 9: Check Task app and Calendar app share the same user preference for Timezone");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		setTimezone();
		hp.goToTasks();
		mgProject.addProject(prj1,"", true);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "00:00", "01:00",false);
		/*Step Number: 1
		*Step Name: Step 1: Open Calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened*/
		hp.goToCalendarPage();
		
		/*Step number: 2
		*Step Name: Step 2: Check Task app and Calendar app share the same user preference for Timezone
		*Step Description: 
			- Click on taskA to check
		*Input Data: 
			
		*Expected Outcome: 
			- workplan of taskA in Calendar app is the same as in Task app*/ 
		tasMg.checkDetailTask(task1, "", getDate(0,"MM/dd/yyyy")+" 00:00", getDate(1,"MM/dd/yyyy")+" 01:00", "", "","");
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131786.</li>
	*<li> Test Case Name: Check tasks from task calendars cannot be searched in Calendar app.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created with Calendar Integration option
	- taskA is added to projectA with show in calendar option, workplan</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckTasksFromTaskCalendarsCannotBeSearchedInCalendarApp() {
		info("Test 10 Check tasks from task calendars cannot be searched in Calendar app");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToTasks();
		mgProject.addProject(prj1,"", true);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "00:00", "01:00",false);
		/*Step Number: 1
		*Step Name: Step 1: Open Calendar
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Calendar is opened*/
		hp.goToCalendarPage();
		
		/*Step number: 2
		*Step Name: Step 2: Search taskA in Calendar app
		*Step Description: 
			- Search taskA in Calendar app
		*Input Data: 
			
		*Expected Outcome: 
			- tasks from task calendars cannot be searched in Calendar app*/ 
		cHome.quickSearchCalendar(task1);
		waitForAndGetElement(cHome.ELEMENT_QUICK_SEARCH_NO_RESULT);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131778.</li>
	*<li> Test Case Name: Check when uncheck  Enable Calendar integration.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created with Calendar Integration option</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckWhenUncheckEnableCalendarIntegration() {
		info("Test 11 Check when uncheck  Enable Calendar integration");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 3
		*Step Name: Step 3: Uncheck Calendar Integration
		*Step Description: 
			- Uncheck Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is not integrated with calendar*/

		/*Step number: 4
		*Step Name: Step 4: Check when uncheckEnable Calendar integration
		*Step Description: 
			- Click on Calendar on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is not in list calendar anymore*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", false);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}
}