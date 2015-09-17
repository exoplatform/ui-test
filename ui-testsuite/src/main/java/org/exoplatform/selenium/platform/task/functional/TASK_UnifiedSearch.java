package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_UnifiedSearch extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131764.</li>
	*<li> Test Case Name: Check accessibility of task result.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- userA is created taskA
	- userB is created taskB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAccessibilityOfTaskResult() {
		info("Test 1: Check accessibility of task result");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToTasks();
		mgTask.addTaskDirectly(task1, true);
		/*Step Number: 1
		*Step Name: Step 1: Login as userA
		*Step Description: 
			- Input in search field: taskB
		*Input Data: 
			
		*Expected Outcome: 
			- taskB is NOT displayed in search result*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToTasks();
		mgTask.addTaskDirectly(task2, true);
		
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,task1,true);
		quickSearch.checkDislayAutoSuggestionOfTask(task1,false,false);
		/*Step number: 2
		*Step Name: Step 2: Search taskA
		*Step Description: 
			- Input in search field: taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is displayed in search result*/ 
		quickSearch.checkDislayAutoSuggestionOfTask(task2,false,true);
		
		info("delete data");
		hp.goToTasks();
		mgTask.deleteTask(task2);
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:140670.</li>
	*<li> Test Case Name: Check display of completed task in task result.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is completed
	- taskB is uncompleted</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131766.</li>
	*<li> Test Case Name: Check display of task quick search.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckDisplayOfCompletedTaskInTaskResult() {
		info("Test 2: Check display of completed task in task result");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToTasks();
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		mgTask.completeTask(task1);
		/*Step Number: 1
		*Step Name: Step 1: Search task
		*Step Description: 
			- Input in search field: task
		*Input Data: 
			
		*Expected Outcome: 
			-taskA is represented by blue tick icon
			-taskB is represented by gray tick icon.*/ 
		navToolBar.goToQuickSearch();
		quickSearch.checkDislayAutoSuggestionOfTask(task1,true,true);
		quickSearch.checkDislayAutoSuggestionOfTask(task2,false,true);
		
		info("delete data");
		hp.goToTasks();
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:131767.</li>
	*<li> Test Case Name: Check display of task search result.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created with description , has due date, is setted priority, belongs to projectA</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-241
	*/
	@Test (groups="pending")
	public  void test04_CheckDisplayOfTaskSearchResult() {
		info("Test 4: Check display of task search result");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String priority = priorityData.getPriorityByArrayTypeRandom(2);
		hp.goToTasks();
		mgProject.addProject(prj1,"", false);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskPriority(task1, priority);
		mgTask.editTaskTag(task1, tag);
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.editTaskDescription(task1, des);
		/*Step Number: 1
		*Step Name: Step 1: Check display of task search result
		*Step Description: 
			- Input in search field: taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is displayed in result with task icon, the task title, en excerpt of the task description, the due date, priority and project name*/ 
		navToolBar.goToQuickSearch();
		quickSearch.checkDisplayOfTaskResult(task1, prj1, priority, "", des,getDate(7,"MMMM dd, yyyy"),false);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:140739.</li>
	*<li> Test Case Name: Check eXo Calendar tasks in Search Administration.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test05_CheckEXoCalendarTasksInSearchAdministration() {
		info("Test 5: Check eXo Calendar tasks in Search Administration");
		/*Step Number: 1
		*Step Name: Step 1: Open Search Administration.
		*Step Description: 
			- Click on Administration> Content>Search
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks (eXo Calendar tasks) connector is disabled*/ 
		navToolBar.goToAdminSearch();
		info("eXo Calendar tasks connector is disabled");
		waitForAndGetElement(seaAdmin.ELEMENT_SEARCHADMIN_ACTION_DISABLE_BUTTON.replace("${type}", "eXo Calendar tasks"));
 	}

	/**
	*<li> Case ID:140740.</li>
	*<li> Test Case Name: Check eXo Calendar tasks in Search portlet setting.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test06_CheckEXoCalendarTasksInSearchPortletSetting() {
		info("Test 6: Check eXo Calendar tasks in Search portlet setting");
		String keyword = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open search result page
		*Step Description: 
			- input keyword> Click to view more result
		*Input Data: 
			
		*Expected Outcome: 
			- search result page is opened*/
		navToolBar.goToQuickSearch();
		quickSearch.search(keyword);
		
		/*Step number: 2
		*Step Name: Step 2: OpenSearch portlet settings
		*Step Description: 
			- Click Edit>Page layout> Edit Search portlet> Edit mode tab
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks (eXo Calendar tasks) filter option is disabled*/ 
		navToolBar.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Tasks (eXo Calendar tasks) filter option is disabled");
		waitForElementNotPresent(pagEditor.ELEMENT_EDIT_PORTLET_SEARCH_OPT_CHECKED.replace("$opt","Tasks in Tasks"));
 	}

	/**
	*<li> Case ID:131762.</li>
	*<li> Test Case Name: Check find tasks based on their title,description and tags.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- holiday is created
	- taskB is created with description: holiday
	- taskC is created with tags: holiday</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:140671.</li>
	*<li> Test Case Name: Check task when clicking on the search result.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created in projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_08_CheckFindTasksBasedOnTheirTitledescriptionAndTags() {
		info("Test 7: Check find tasks based on their title,description and tags");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTaskDirectly(task2, true);
		mgTask.editTaskDescription(task2, task1);
		mgTask.addTaskDirectly(task3, true);
		mgTask.editTaskTag(task3, task1);
		/*Step Number: 1
		*Step Name: Step 1: Check find tasks based on their titles
		*Step Description: 
			- Input in search field: holiday
		*Input Data: 
			
		*Expected Outcome: 
			- taskB,taskC,holiday are displayed in result*/
		navToolBar.goToQuickSearch();
		quickSearch.checkDislayAutoSuggestionOfTask(task3,false,true);
		quickSearch.checkDislayAutoSuggestionOfTask(task2,false,true);
		quickSearch.checkDislayAutoSuggestionOfTask(task1,false,true);
		click(quickSearch.ELEMENT_QUICKSEARCH_TASKS_LINK.replace("$task", task1));
		mgTask.checkDisplayOfTaskInProject(prj1, task1);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131763.</li>
	*<li> Test Case Name: Check unified search connector.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test09_CheckUnifiedSearchConnector() {
		info("Test 9: Check unified search connector");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToTasks();
		mgTask.addTaskDirectly(task1, true);
		/*Step Number: 1
		*Step Name: Step 1: Open Search Admin page
		*Step Description: 
			- Click on Administration > Content > Search
		*Input Data: 
			
		*Expected Outcome: 
			- In list Search:+Content Type : Tasks+Description : eXo Tasks tasks+the connector is enabled by default*/
		navToolBar.goToAdminSearch();
		info("eXo Tasks tasks connector is enabled");
		waitForAndGetElement(seaAdmin.ELEMENT_SEARCHADMIN_ACTION_ENABLE_BUTTON.replace("${type}", "eXo Tasks tasks"));
		
		/*Step number: 2
		*Step Name: Step 2: Open search result page
		*Step Description: 
			- Input keyword in unified search: taskA
			- Click on Show more result
		*Input Data: 
			
		*Expected Outcome: 
			- in autosuggestion list, Tasks is on left column
			- search result page is opened, Tasks is in Search Filter*/
		navToolBar.goToQuickSearch();
		type(quickSearch.ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX,task1,true);
		quickSearch.checkDislayAutoSuggestionOfTask(task1,false,true);
		click(quickSearch.ELEMENT_QUICKSEARCHRESULT_SEE_ALL_SEARCH);
		waitForAndGetElement(quickSearch.ELEMENT_SEARCHRESULT_TASKTYPECHECK);
		
		/*Step number: 3
		*Step Name: Step 3: Open Search Portelt Settings
		*Step Description: 
			- Click on Edit> Page Layout> Edit portlet Search
		*Input Data: 
			
		*Expected Outcome: 
			- In edit mode tab, Tasks is enable*/ 
		navToolBar.goToEditLayout();
		pagEditor.goToEditPortlet(pagEditor.ELEMENT_FRAME_CONTAIN_PORTLET);
		info("Tasks filter option is enabled");
		waitForAndGetElement(pagEditor.ELEMENT_EDIT_PORTLET_SEARCH_OPT_CHECKED.replace("$opt","Tasks in Tasks"));
		
		info("delete data");
		hp.goToTasks();
		mgTask.deleteTask(task1);
 	}}