package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	public class TASK_Filters extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131752.</li>
	*<li> Test Case Name: Check Assignee field.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created, taskA,taskB,taskC are added to projectA
	- taskA is created with none assigne
	- taskB is created and assignee to userB
	- taskC is created and assignee to userC</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test01_CheckAssigneeField() {
		info("Test 1: Check Assignee field");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users = {DATA_USER1,DATA_USER2};
		String[] tasks= {task2,task3};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"","", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.editTaskUnAssignee(task2,DATA_USER1);
		mgTask.addTask(project, task3);
		mgTask.editTaskUnAssignee(task3,DATA_USER2);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/
		
		/*Step number: 4
		*Step Name: Step 4: Check Assignee field
		*Step Description: 
			- Input in Assignee field: userB, userC
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskB and taskC are displayed*/
		mgTask.filterWithAssignee(users);
		mgTask.checkDisplayOfListView(tasks);
		mgTask.checkNotDisplayOfListView(task1);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:131737.</li>
	*<li> Test Case Name: Check changes in the filter pane are reflected instantly in the central pane.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA,taskB are added to projectA
	- taskA is To Do
	- taskB is In Progress</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckChangesInTheFilterPaneAreReflectedInstantlyInTheCentralPane() {
		info("Test 2: Check changes in the filter pane are reflected instantly in the central pane");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String status = flowData.getFlowByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"","", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.editTaskStatus(task2, status);
		
		/*Step number: 2
		*Step Name: Step 2: Add projectA
		*Step Description: 
			- Add projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/

		/*Step number: 3
		*Step Name: Step 3: Check changes in the filter pane are reflected instantly in the central pane
		*Step Description: 
			- Click on Filter and select status: In Progress
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, only displays taskB*/ 
		mgTask.checkDisplayOfListView(task2);
		mgTask.checkNotDisplayOfListView(task1);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:131738.</li>
	*<li> Test Case Name: Check Contain field.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created
	- tkB with description: taskB is created
	- tkC is created</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test03_CheckContainField() {
		info("Test 3: Check Contain field");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Contain field
		*Step Description: 
			- Input in contain field: task
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA and tkB are displayed*/ 

 	}

	/**
	*<li> Case ID:131744.</li>
	*<li> Test Case Name: Check Due pulldown list.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA,taskB,taskC,taskD are added to projectA
	- taskA is created with overdue
	- taskB is created with due Today
	- taskC is created with due Tomorrow
	- taskD is created with due date next week</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test04_CheckDuePulldownList() {
		info("Test 4: Check Due pulldown list");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 4
		*Step Name: Step 4: Check Due pulldown list: Overdue
		*Step Description: 
			- Select Overdue from Due pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA is displayed*/

		/*Step number: 5
		*Step Name: Step 5: Check Due pulldown list: Today
		*Step Description: 
			- Select Today from Due pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskB is displayed*/

		/*Step number: 6
		*Step Name: Step 6: Check Due pulldown list: Tomorrow
		*Step Description: 
			- Select Tomorrow from Due pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskC is displayed*/

		/*Step number: 7
		*Step Name: Step 7: Check Due pulldown list: Upcoming
		*Step Description: 
			- Select Upcoming from Due pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskD is displayed*/ 

 	}

	/**
	*<li> Case ID:141666.</li>
	*<li> Test Case Name: Check filters pane is customized on some views.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test05_CheckFiltersPaneIsCustomizedOnSomeViews() {
		info("Test 5: Check filters pane is customized on some views");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field inside it*/

		/*Step number: 4
		*Step Name: Step 4: Open labelA
		*Step Description: 
			- Click on labelA
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is opened*/

		/*Step number: 5
		*Step Name: Step 5: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Labels field displays labelA but cannot edit it*/

		/*Step number: 6
		*Step Name: Step 6: Open Incoming
		*Step Description: 
			- Click on Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- Incoming is opened*/

		/*Step number: 7
		*Step Name: Step 7: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden*/

		/*Step number: 8
		*Step Name: Step 8: Open Today
		*Step Description: 
			- Click on Today
		*Input Data: 
			
		*Expected Outcome: 
			- Today is opened*/

		/*Step number: 9
		*Step Name: Step 9: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden
			- Assignee field is disabled and displayed with current user
			- Due field is disabled and displayed with Today value.*/

		/*Step number: 10
		*Step Name: Step 10: Open Tomorrow
		*Step Description: 
			- Click on Tomorrow
		*Input Data: 
			
		*Expected Outcome: 
			-Tomorrow is opened*/

		/*Step number: 11
		*Step Name: Step 11: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden
			- Assignee field is disabled and displayed with current user
			- Due field is disabled and displayed with Tomorrow value.*/

		/*Step number: 12
		*Step Name: Step 12: Open Upcoming
		*Step Description: 
			- Click onUpcoming
		*Input Data: 
			
		*Expected Outcome: 
			-Upcoming is opened*/

		/*Step number: 13
		*Step Name: Step 13: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden
			- Assignee field is disabled and displayed with current user
			- Duefield is disabled and displayed with Upcoming value.*/

		/*Step number: 14
		*Step Name: Step 14: Open All Tasks
		*Step Description: 
			- Click onAll Tasks
		*Input Data: 
			
		*Expected Outcome: 
			-All Tasks is opened*/

		/*Step number: 15
		*Step Name: Step 15: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden
			- Assignee field is disabled and displayed with current user*/

		/*Step number: 16
		*Step Name: Step 16: Open Overdue
		*Step Description: 
			- Click on Overdue
		*Input Data: 
			
		*Expected Outcome: 
			-Overdue is opened*/

		/*Step number: 17
		*Step Name: Step 17: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened
			- Status field is hidden
			- Assignee field is disabled and displayed with current user
			- Due field is disabled and displayed with Overdue value*/ 

 	}

	/**
	*<li> Case ID:131739.</li>
	*<li> Test Case Name: Check Label field.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created in Incoming with labelA,labelB</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test06_CheckLabelField() {
		info("Test 6: Check Label field");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open filter in Incoming
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Label field
		*Step Description: 
			- Input in label field: labelA,labelB
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA is displayed*/ 

 	}

	/**
	*<li> Case ID:131735.</li>
	*<li> Test Case Name: Check open of filter.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test07_CheckOpenOfFilter() {
		info("Test 7: Check open of filter");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check open of filter
		*Step Description: 
			- Click onfilter icon on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- It opens a filter screen in the right pane*/ 

 	}

	/**
	*<li> Case ID:131750.</li>
	*<li> Test Case Name: Check Priority pulldown list.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created with Low priority
	- taskB is created with Normal priority</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test08_CheckPriorityPulldownList() {
		info("Test 8: Check Priority pulldown list");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open filter in Incoming
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Priority pulldown list
		*Step Description: 
			- Select Low from Priority pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA is displayed*/ 

 	}

	/**
	*<li> Case ID:131753.</li>
	*<li> Test Case Name: Check Show completed tasks is a checkbox field.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- add projectA
	- taskA is created in projectA
	- taskB is created and mark as complete task in projectA</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test09_CheckShowCompletedTasksIsACheckboxField() {
		info("Test 9: Check Show completed tasks is a checkbox field");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA us opened*/

		/*Step number: 3
		*Step Name: Step 3: Open filter
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 4
		*Step Name: Step 4: Check Show completed tasks is a checkbox field
		*Step Description: 
			- Check Show completed tasks is a checkbox field
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskB and taskA are displayed*/ 

 	}

	/**
	*<li> Case ID:131742.</li>
	*<li> Test Case Name: Check Status pulldown list.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA,taskB,taskC are added to projectA
	- taskA is created with To Do status
	- taskB is created with In Progress status
	- taskC is created with Waiting On status</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test10_CheckStatusPulldownList() {
		info("Test 10 Check Status pulldown list");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open filter in projectA
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 4
		*Step Name: Step 4: Check Status pulldown list
		*Step Description: 
			- Select To Do from Status pulldown list
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA is displayed*/ 

 	}

	/**
	*<li> Case ID:131740.</li>
	*<li> Test Case Name: Check Tag field.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created with tagA,tagB
	- taskB is created with tagB</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test11_CheckTagField() {
		info("Test 11 Check Tag field");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open filter in Incoming
		*Step Description: 
			- Click on filter icon
		*Input Data: 
			
		*Expected Outcome: 
			- filter is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Tag field
		*Step Description: 
			- Input in Tag field: tagA,tagB
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA is displayed*/ 

 	}

	/**
	*<li> Case ID:131736.</li>
	*<li> Test Case Name: Filter can work across projects.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is created in projectA
	- taskB is created in projectB
	- taskB is In Progress</li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test12_FilterCanWorkAcrossProjects() {
		info("Test 12 Filter can work across projects");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open Projects
		*Step Description: 
			- Click on Projects on left menu
		*Input Data: 
			
		*Expected Outcome: 
			- Projects is opened*/

		/*Step number: 3
		*Step Name: Step 3: Filter can work across projects
		*Step Description: 
			- Click on Filter and contains: task
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane, taskA, taskB are displayed*/ 

 	}}