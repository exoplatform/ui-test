package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.exoplatform.selenium.platform.PlatformBase;
import org.testng.annotations.*;

public class Calendar_Task extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	TaskManagement task;
	AttachmentFileDatabase fData;
	CalendarManagement cMang;
	UserDatabase userData;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		task= new TaskManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		magAc.signIn(USER_ROOT,PASS_ROOT);
	}


	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:115592.
	 * Test Case Name: Add a task in personal calendar by right click Task on working pane.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_AddATaskInPersonalCalendarByRightClickTaskOnWorkingPane() {
		info("Test 1: Add a task in personal calendar by right click Task on working pane");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115592";
		String content = txData.getContentByArrayTypeRandom(1)+"115592";
		String date = "";
		String time = "";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
			Right click on a cell of working pane, select [Add task]
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Task" is displayed
			- The Default Start date "From" is date corresponding to selected cell
			-The default duration for task is 30 minutes*/
		info("Add a task");
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		task.goToAddTaskByRightClickFromMainPanel(date,time);
		info("Check default time ");
		task.checkSuggestionTaskTimeInQuickForm(time, time, 30);

		/*Step number: 2
		 *Step Name: Step 2: save
		 *Step Description: 
			- Input into fields
			- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
			Task is created in personal calendar*/ 
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}

	/**
	 * Case ID:115593.
	 * Test Case Name: Add a task in personal calendar by click Task on action bar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_AddATaskInPersonalCalendarByClickTaskOnActionBar() {
		info("Test 2: Add a task in personal calendar by click Task on action bar");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115593";
		String content = txData.getContentByArrayTypeRandom(1)+"115593";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
			- Click on [Task] button
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Task" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for task is 30 minutes*/
		info("Add a task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		info("Check default time ");
		task.checkSuggestionTaskTimeInQuickForm(null,null, 30);

		/*Step number: 2
		 *Step Name: Step 2: save
		 *Step Description: 
			- Input into fields
			- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
			Task is created in personal calendar*/ 
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}

	/**
	 * Case ID:115610.
	 * Test Case Name: Add attachment to task.
	 * Case ID:115609.
	 * Test Case Name: Remove attachment of task.
	 * Pre-Condition: Task with attachment is exist
	 * Post-Condition: 
	 */
	@Test
	public  void test03_04_AddRemoveAttachmentOfTask() {
		if ("iexplorer".equals(browser) || "chrome".equals(browser)){
			info("Test 4: Add attachment to task on IE and chrome manually");
		}else{
			info("Test 4: Add attachment to task");
			String titleTask = txData.getContentByArrayTypeRandom(1)+"115610";
			String content = txData.getContentByArrayTypeRandom(1)+"115610";
			String link = fData.getAttachFileByArrayTypeRandom(1);
			/*Step Number: 1
			 *Step Name: Step 1: Open Add task form
			 *Step Description: 
			- Select a calendar, Click Setting icon of this calendar and choose [Add Task] or Click Task button on action bar
			- Input start and end time
			- Click [More Details
			 *Input Data: 

			 *Expected Outcome: 
			Add/Edit Task pop
			-up has appears*/
			info("Add a task");
			hp.goToCalendarPage();
			task.goToAddTaskFromActionBar();
			task.moreDetailsTask();

			/*Step number: 2
			 *Step Name: Step 2: Add attachment
			 *Step Description: 
			- Click [Add Attachment]
			- Browse to file and click save
			 *Input Data: 

			 *Expected Outcome: 
			- Attachment is added to event*/ 
			info("Add attachment");
			task.inputDataTaskInDetailForm(titleTask, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
			task.attachFileToTask(link);
			task.saveAddTaskDetails();
			cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

			info("Test 3: Remove attachment of task");
			/*Step Number: 1
			 *Step Name: Step 1: Open edit task form
			 *Step Description: 
			- Go to calendar
			- Select task which has attachment and edit
			 *Input Data: 

			 *Expected Outcome: 
			- Edit form appears*/

			/*Step number: 2
			 *Step Name: Step 2: Remove attachment
			 *Step Description: 
			- Click [Delete] icon
			- Click Save
			 *Input Data: 

			 *Expected Outcome: 
			- Attachment is deleted*/ 
			info("Remove the attachment");
			cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
			task.removeAttachment(link);
			task.saveAddTaskDetails();

			info("Delete data");
			cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
		}
	}

	/**
	 * Case ID:115612.
	 * Test Case Name: Add a task for shared calendar.
	 * Case ID:115611.
	 * Test Case Name: Edit a task in shared calendar.
	 * Case ID:115613.
	 * Test Case Name: Delete a task in shared calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test05_06_07_AddEditDeleteATaskInSharedCalendar() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"1115611";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"1115611";
		String titleTask2 = txData.getContentByArrayTypeRandom(1)+"2115611";
		String contentTask2 = txData.getContentByArrayTypeRandom(1)+"2115611";
		String calendarName= txData.getContentByArrayTypeRandom(1)+"1115611";
		String calendarColor = "purple";
		String[] groupShare = {DATA_USER2};
		boolean[] edit = {true};

		/*Step Number: 1
		 *Step Name: Step 1: Create & share a calendar
		 *Step Description: 
			- Create personal calendar
			- Share added calendar with edit right
		 *Input Data: 

		 *Expected Outcome: 
			Calendar is created and shared*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
		cMang.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
		cMang.saveAddCalendar();
		waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendarName));
		cMang.shareCalendar(calendarName, groupShare, edit , 1);

		/*Step number: 2
		 *Step Name: Step 2: Add an event to a shared calendar
		 *Step Description: 
			- Login by shared user
			- Click wheel icon of shared calendar and select Add task
			- Input task summary and click Save
		 *Input Data: 

		 *Expected Outcome: 
			- The shared user can see the shared calendar and add task into it.*/
		// add a task to edit by publisher
		info("Test 5: Add a task in shared calendar");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cMang.executeActionCalendar(calendarName, menuOfCalendarOption.ADDTASK);
		info("Check default date");
		task.checkSuggestionTaskTimeInQuickForm(null,null, 30);
		task.inputDataTaskInQuickForm(titleTask, contentTask, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step number: 3
		 *Step Name: Step 3: Edit a task to a shared calendar
		 *Step Description: 
			- Right click on event then choose Edit
			- Update some values
			- Change From time
		 *Input Data: 

		 *Expected Outcome: 
			- To time is automatically set = From Time + 30min*/

		/*Step number: 4
		 *Step Name: Step 4: Save
		 *Step Description: 
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Task in shared calendar is edited
			- Sharing user can see updated task*/ 
		info("Test 6: Edit a task in shared calendar");
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
		task.checkSuggestionTaskTimeInDetailForm(null, null,30);
		task.inputDataTaskInDetailForm(titleTask2, contentTask2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		task.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step number: 5
		 *Step Name: Step 5: Delete a task to a shared calendar
		 *Step Description: 
			- Right click on task then choose Delete
			- Click OK at confirmation
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Task is deleted
			- The sharing user cannot see this task*/ 
		info("Test 7: Delete a task in shared calendar");
		cHome.deleteEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME,null,true,false);

		info("Delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToCalendarPage();
		cMang.deleteCalendar(calendarName,true);
	}

	/**
	 * Case ID:115614.
	 * Test Case Name: Add a task in personal calendar by icon settings of a calendar.
	 * Case ID:115615.
	 * Test Case Name: Edit a task in personal calendar.
	 * Case ID:115616.
	 * Test Case Name: Delete a task in personal calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test08_09_10_AddEditDeleteATaskInPersonalCalendarByIconSettingsOfACalendar() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"1115614";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"1115614";
		String titleTask2= txData.getContentByArrayTypeRandom(1)+"2115614";
		String contentTask2 = txData.getContentByArrayTypeRandom(1)+"2115614";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
			- Mouse over a personal calendar
			- Click [Setting] icon of this personal calendar and choose [Add Task]
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Task" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for task is 30 minutes*/

		/*Step number: 2
		 *Step Name: Step 2: save
		 *Step Description: 
			- Input into fields
			- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
			Task is created in personal calendar*/ 
		info("Test 8: Add a task in personal calendar by icon settings of a calendar");
		hp.goToCalendarPage();
		cMang.executeActionCalendar(userData.fullName.get(0), menuOfCalendarOption.ADDTASK);
		info("Check default date");
		task.checkSuggestionTaskTimeInQuickForm(null,null, 30);
		task.inputDataTaskInQuickForm(titleTask, contentTask, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Test 9: Edit a task in personal calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Edit a task
		 *Step Description: 
			- Add an event 
			- Edit a task
			- Right click on a task and select Edit
			- Update some values
			- Change From time
		 *Input Data: 

		 *Expected Outcome: 
			- To time is automatically set = From Time + 30min*/

		/*Step number: 2
		 *Step Name: Step 2: Save
		 *Step Description: 
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			Event is saved successfully in personal calendar*/ 
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
		task.checkSuggestionTaskTimeInDetailForm(null,null, 30);
		task.inputDataTaskInDetailForm(titleTask2, contentTask2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		task.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);


		info("Test 10 Delete a task in personal calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Delete a task
		 *Step Description: 
			- Add an event 
			- Right click on a existing task and select Delete
			- Click OK at confirmation message
		 *Input Data: 

		 *Expected Outcome: 
			- The task is removed normally.*/ 
		cHome.deleteEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME,null);

	}

	/**
	 * Case ID:115617.
	 * Test Case Name: Add a task in group calendar.
	 * Case ID:115618.
	 * Test Case Name: Edit a task in group calendar.
	 * Case ID:115619.
	 * Test Case Name: Delete a task in group calendar.
	 * Pre-Condition: - A calendar with its Edit right is shared with a user
	 * Post-Condition: 
	 */
	@Test
	public  void test11_12_13_AddEditDeleteATaskInGroupCalendar() {
		String titleTask = txData.getContentByArrayTypeRandom(1)+"1115617";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"1115617";
		String titleTask2= txData.getContentByArrayTypeRandom(1)+"2115617";
		String contentTask2= txData.getContentByArrayTypeRandom(1)+"2115617";
		String groupCalendar = "Content Management";

		info("Test 11 Add a task in group calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Open Add Task form
		 *Step Description: 
			- Login by user who has edit right on a group calendar
			- Click Setting icon of this group calendar and choose [Add Task]
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Task" is displayed
			- The Default Start date "From" is set to Today (System date)
			-The default duration for Task is 30 min*/

		/*Step number: 2
		 *Step Name: Step 2: Add a task to a group calendar
		 *Step Description: 
			- Fill values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- New task for that group calendar is added successfully
			- The other users in shared group can view the task in the group calendar*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cMang.executeActionCalendar(groupCalendar, menuOfCalendarOption.ADDTASK);
		info("Check default date");
		task.checkSuggestionTaskTimeInQuickForm(null,null, 30);
		task.inputDataTaskInQuickForm(titleTask, contentTask, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cHome.verifyIsNotPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Test 12 Edit a task in group calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Edit a task to a group calendar
		 *Step Description: 
			- Login by user who has edit right on a group calendar
			- Add new task into the group calendar
			- Right click on task then choose Edit
			- Update some values
			- Change From time
		 *Input Data: 

		 *Expected Outcome: 
			- To time is automatically set = From Time + 30min*/

		/*Step number: 2
		 *Step Name: Step 2: Save
		 *Step Description: 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- Task is saved successfully
			- The other users in group can view updated task in the group calendar*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
		task.checkSuggestionTaskTimeInDetailForm(null,null, 30);
		task.inputDataTaskInDetailForm(titleTask2, contentTask2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		task.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Test 13 Delete a task in group calendar");
		/*Step Number: 1
		 *Step Name: Step 1: Delete an event to a group calendar
		 *Step Description: 
			- Login by user who has edit right on a group calendar
			- Add new task into the group calendar
			- Right click on task then choose Delete
			- Click OK at confirmation message
		 *Input Data: 

		 *Expected Outcome: 
			- Task is deleted
			- The other users in shared group cannot view the task in the group calendar any more.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToCalendarPage();
		cHome.deleteEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME,null);

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToCalendarPage();
		cHome.verifyIsNotPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);

	}

	/**
	 * Case ID:115631.
	 * Test Case Name: Add new task by clicking Task on action bar or [Add task] in a calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test14_AddNewTaskByClickingTaskOnActionBarOrAddTaskInACalendar() {

		info("Test 14 Add new task by clicking Task on action bar or [Add task] in a calendar");
		info("Add a task in personal calendar by click Task on action bar");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115631";
		String content = txData.getContentByArrayTypeRandom(1)+"115631";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
				- Click on [Task] button
		 *Input Data: 

		 *Expected Outcome: 
				- The pop up "Quick Add Task" is displayed
				- The Default Start date "From" is set to Today (System date)
				-The default duration for task is 30 minutes*/
		info("Add a task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		info("Check default time ");
		task.checkSuggestionTaskTimeInQuickForm(null,null, 30);

		/*Step number: 2
		 *Step Name: Step 2: save
		 *Step Description: 
				- Input into fields
				- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
				Task is created in personal calendar*/ 
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);

		info("Add a task in personal calendar by [Add task] in a calendar");
		String titleTask1 = txData.getContentByArrayTypeRandom(1)+"1115631";
		String content1 = txData.getContentByArrayTypeRandom(1)+"1115631";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
				- Click on [Task] button
		 *Input Data: 

		 *Expected Outcome: 
				- The pop up "Quick Add Task" is displayed
				- The Default Start date "From" is set to Today (System date)
				-The default duration for task is 30 minutes*/
		info("Add a task");
		hp.goToCalendarPage();
		String fullName = userData.fullName.get(0);
		info(fullName);
		cMang.executeActionCalendar(fullName, menuOfCalendarOption.ADDTASK);
		info("Check default date");
		task.checkSuggestionTaskTimeInQuickForm(null, null, 30);

		/*Step number: 2
		 *Step Name: Step 2: save
		 *Step Description: 
				- Input into fields
				- Click [Save]
		 *Input Data: 

		 *Expected Outcome: 
				Task is created in personal calendar*/ 
		task.inputDataTaskInQuickForm(titleTask1, content1, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask1, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Delete data");
		cHome.deleteEventTask(titleTask1, selectViewOption.DAY, selectDayOption.DETAILTIME,null);

	}

	/**
	 * Case ID:115649.
	 * Test Case Name: Drag-drop task.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test15_DragDropTask() {
		info("Test 15 Drag-drop task");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"1115649";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"1115649";
		String dateTime=getDate(1,"MM/dd/yyyy");

		info("Create data test");
		info("Add a task");
		hp.goToCalendarPage();
		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, contentTask, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		String fromTime = waitForAndGetElement(task.ELEMENT_QUICK_INPUT_TASK_FROM_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		String toTime = waitForAndGetElement(task.ELEMENT_QUICK_INPUT_TASK_TO_TIME_INPUT, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
				Step 1: Drag and drop an event/ task
		 *Input Data: 
				- Choose View type from main bar (except List View)
				- Drag & drop added event/ task in working pane
		 *Expected Outcome: 
				- Event /task is moved to new place in working pane
				- time of event is updated according to new place in main pane*/ 
		cHome.goToView(selectViewOption.MONTH);
		dragAndDropToObject(By.xpath(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleTask).replace("$date", getDate(0, "MMM dd yyyy"))),By.xpath(cHome.ELEMENT_ANY_TARGET_DATE.replace("${targetDate}", getDate(1, "MMM dd yyyy"))));
		cHome.goToEditEventTaskFormByRightClick(titleTask, selectViewOption.WEEK, selectDayOption.DETAILTIME,getDate(1,"MMM dd yyyy"));
		task.checkSuggestionTaskTimeInDetailForm(dateTime+" "+fromTime, dateTime+" "+toTime, 30);

		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.MONTH, selectDayOption.DETAILTIME,getDate(1,"MMM dd yyyy"));	

	}

	/**
	 * Case ID:115650.
	 * Test Case Name: Re-size task.
	 * Pre-Condition: 
	 * Post-Condition:
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1351 
	 */
	@Test(groups="pending")
	public  void test16_ReSizeTask() {
		info("Test 16 Re-size task");
		/*Step Number: 1
		 *Step Name: Step 1: Resize an existing event/ task
		 *Step Description: 
				- Choose View type from list (except List View)
				- Resize time of task/event by drag & drop
		 *Input Data: 

		 *Expected Outcome: 
				Time of task/ event is changed depending on the changed size*/
	}

	/**
	 * Case ID:115685.
	 * Test Case Name: Add a Task by click on calendar main pane.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test17_AddATaskByClickOnCalendarMainPane() {
		info("Test 17 Add a Task by click on calendar main pane");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"115685";
		String content = txData.getContentByArrayTypeRandom(1)+"115685";
		String date = getDate(0,"MM/dd/yyyy");
		String time = "18:00";

		/*Step Number: 1
		 *Step Name: Step 1: Add a task
		 *Step Description: 
				- Add a task by right click on main pane
		 *Input Data: 

		 *Expected Outcome: 
				- The pop up "Quick Add task" is displayed
				-Start time is where the click occurs 
				- Duration is 30 min by default (set in file configuration.properties)*/
		info("Add a task");
		hp.goToCalendarPage();
		task.goToAddTaskByRightClickFromMainPanel(date,time);
		info("Check default time ");
		task.checkSuggestionTaskTimeInQuickForm(date+" "+time,date+" "+ "18:30", 30);

		/*Step number: 2
		 *Step Name: Step2: Change From tome
		 *Step Description: 
				- Set new from time for task
		 *Input Data: 

		 *Expected Outcome: 
				To time is automatically set = From Time + 30min*/

		/*Step number: 3
		 *Step Name: Step 3: Save
		 *Step Description: 
				- Click Save
		 *Input Data: 

		 *Expected Outcome: 
				Task is created successfully*/ 
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);

	}

	/**
	 * Case ID:115687.
	 * Test Case Name: Check pop-up reminder.
	 * Pre-Condition: - Setting calendar time zone is your time zone (for Vietnam: GMT+7, Asia/Ho_Chi_Minh)
	 * Post-Condition: 
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups="pending")
	public  void test18_CheckPopupReminder() {
		info("Test 18 Check pop-up reminder");
		/*Step Number: 1
		 *Step Name: Step 1: Check pop up reminder of a task
		 *Step Description: 
				- Add a Task
				- Input Event Summary 
				- Click More Details
				- In Reminders tab, check [Show a notification popup]
				- set [When the event starts in next] value to 5 minutes
				- Click Save
		 *Input Data: 

		 *Expected Outcome: 
				- Task with pop up reminder is created successfully
				- Reminder popup is shown at the time 5 minutes before the event happens*/ 

	}

	/**
	 * Case ID:115688.
	 * Test Case Name: Check email reminder.
	 * Pre-Condition: - Setting calendar time zone is your time zone (for Vietnam: GMT+7, Asia/Ho_Chi_Minh)
	 * Post-Condition: 
	 * PENDING: Refer to : https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups="pending")
	public  void test19_CheckEmailReminder() {
		info("Test 19 Check email reminder");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Check mail reminder of an task</p>
		 *Step Description: 
				In Add new event form:
				- Input Event Summary 
				- Click More Details
				- In Reminders tab, check [Remind by email]
				- Set [Send an email before the event starts in] to 5 minutes
				- Add some valid emails of some user who will receive reminder
				- Click Save
		 *Input Data: 

		 *Expected Outcome: 
				- Task with mail reminder is created successfully
				- Check the mailbox of user who receive reminder that the mail is sent at the right time*/ 

	}
}