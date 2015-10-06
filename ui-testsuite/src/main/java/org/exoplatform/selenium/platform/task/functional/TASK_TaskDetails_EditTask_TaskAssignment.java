package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;

import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskAssignment extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130942.</li>
	*<li> Test Case Name: Check a task can be assigned to only one person.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckATaskCanBeAssignedToOnlyOnePerson() {
		info("Test 1: Check a task can be assigned to only one person");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check a task can be assigned to only one person
		*Step Description: 
			- Click on assignment to select an user
			- To change assignee, the current assigned must be removed first, the new assignee can be input.
		*Input Data: 
			
		*Expected Outcome: 
			- A task can be assigned to only one person
			- new assignee is added*/ 
		mgTask.editTaskAssignee(task1,DATA_NAME_USER1, DATA_USER2);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130940.</li>
	*<li> Test Case Name: Check autocomplete list displaying all users.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAutocompleteListDisplayingAllUsers() {
		info("Test 2: Check autocomplete list displaying all users");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users={DATA_USER2,DATA_USER3};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgProject.addProject(prj1, "", "",false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check autocomplete list displaying all users
		*Step Description: 
			- Click on assignment and input an username. Ex: mar
		*Input Data: 
			
		*Expected Outcome: 
			There is an autocomplete list displaying all users (participants of the project) that matches with input value for Assign To and Coworker fields.*/ 
		mgTask.checkAutoCompleteUser(task1,"a",users);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130945.</li>
	*<li> Test Case Name: Check case there are assignee and coworkers.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckCaseThereAreAssigneeAndCoworkers() {
		info("Test 3: Check case there are assignee and coworkers");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] coworkers = {DATA_USER3,DATA_USER4}; 
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check case there are assignee and coworkers
		*Step Description: 
			- Click on assign to select an assignee and 2 coworkers
		*Input Data: 
			
		*Expected Outcome: 
			the assignment field is displayed with the avatar of the assignee + the number of coworkers. E.g: [Jack Avatar] + 2 coworkers*/ 
		mgTask.editTaskAssignee(task1,DATA_NAME_USER1,DATA_USER2, coworkers);
		mgTask.checkDisplayOfAssigneeCoworker("+2  Coworkers");
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130946.</li>
	*<li> Test Case Name: Check case there are coworkers only.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-153
	*/
	@Test (groups="pending")
	public  void test04_CheckCaseThereAreCoworkersOnly() {
		info("Test 4: Check case there are coworkers only");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] coworkers = {DATA_USER3,DATA_USER4}; 
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check case there are coworkers only
		*Step Description: 
			- Click on assign to select 2 coworkers but not select assignee
		*Input Data: 
			
		*Expected Outcome: 
			Assign field displays number of coworker and the default avatar.*/ 
		mgTask.editTaskAssignee(task1,DATA_NAME_USER1, "", coworkers);
		mgTask.checkDisplayOfOnlyCoworker("+1 Coworkers");
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130944.</li>
	*<li> Test Case Name: Check case there is an assignee only.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckCaseThereIsAnAssigneeOnly() {
		info("Test 5: Check case there is an assignee only");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check case there is an assignee only
		*Step Description: 
			- Click on assign to select an user as assignee but not select coworker
		*Input Data: 
			
		*Expected Outcome: 
			the assignment field is displayed with the avatar of the assignee and his/her full name*/ 
		mgTask.editTaskAssignee(task1,DATA_NAME_USER1, DATA_USER2);
		mgTask.checkDisplayOfAssigneeCoworker(DATA_NAME_USER2);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130939.</li>
	*<li> Test Case Name: Check display of popover when the assignment field is edited.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130941.</li>
	*<li> Test Case Name: Check display of selected user.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_07_CheckDisplayOfPopoverWhenTheAssignmentFieldIsEdited() {
		info("Test 6: Check display of popover when the assignment field is edited");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgProject.addProject(prj1, "", "", false);
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of popover when the assignment field is edited
		*Step Description: 
			- Click on assignment to edit
		*Input Data: 
			
		*Expected Outcome: 
			There is a popover display to define assignee and coworkers*/ 
		/*Step number: 3
		*Step Name: Step 3: Check display of selected user
		*Step Description: 
			- Click on assignment and select an user
		*Input Data: 
			
		*Expected Outcome: 
			The selected user is displayed with full name (under mention style) that is possible to remove.*/
		mgTask.checkTaskAssigneePopup(task1, DATA_USER3, DATA_NAME_USER3);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130943.</li>
	*<li> Test Case Name: Check the coworkers can be assigned to multiple users.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130938.</li>
	*<li> Test Case Name: Check Unassigned is default value of the assignment field.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_09_CheckTheCoworkersCanBeAssignedToMultipleUsers() {
		info("Test 8: Check the coworkers can be assigned to multiple users");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users = {DATA_USER2,DATA_USER3};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgProject.addProject(prj1,"","",false);
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 2
		*Step Name: Step 2: Check Unassigned is default value of the assignment field
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created
			- Unassigned is default value of the assignment field.*/ 
		mgTask.checkTaskAssigneeDefault(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check the coworkers can be assigned to multiple users
		*Step Description: 
			- Click on assignment to assign coworker
			- Continue adding another coworker
		*Input Data: 
			
		*Expected Outcome: 
			the coworkers can be assigned to multiple users*/ 
		mgTask.editTaskUnAssignee(task1, "", users);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}
	/**
	*<li> Case ID:140738.</li>
	*<li> Test Case Name: Check case assigned user are not shared project.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckCaseAssignedUserAreNotSharedProject() {
		info("Test 10: Check case assigned user are not shared project");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- from context menu of Projects, add projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"","",false);
		
		/*Step number: 3
		*Step Name: Step 3: Create taskA in projectA
		*Step Description: 
			- add taskA to projectA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTask(prj1, task1);
		
		/*Step number: 4
		*Step Name: Step 4: Assign taskA to userA
		*Step Description: 
			- assign taskA to userA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is assigned*/
		mgTask.editTaskUnAssignee(task1, DATA_USER3);
		
		/*Step number: 5
		*Step Name: Step 5: Login as userA
		*Step Description: 
			- login as userA
		*Input Data: 
			
		*Expected Outcome: 
			- userA logins successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 6
		*Step Name: Step 6: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 7
		*Step Name: Step 7: Open All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- All Tasks is opened*/
		mgTask.selectOptionTask(optionTask.All_Tasks);
		
		/*Step number: 8
		*Step Name: Step 8: Click on taskA title
		*Step Description: 
			- open taskA
			- click on taskA title
		*Input Data: 
			
		*Expected Outcome: 
			- userA can edit taskA*/ 
		mgTask.openTask(task1);
		mgTask.checkMenuOfTask();
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:141665.</li>
	*<li> Test Case Name: Check case assigned user without project.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckCaseAssignedUserWithoutProject() {
		info("Test 11: Check case assigned user without project");
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
			- add taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Assign taskA to userA
		*Step Description: 
			- assign taskA to userA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is assigned*/
		mgTask.editTaskAssignee(task1, DATA_NAME_USER1, DATA_USER3);
		
		/*Step number: 4
		*Step Name: Step 4: Login as userA
		*Step Description: 
			- login as userA
		*Input Data: 
			
		*Expected Outcome: 
			- userA logins successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 5
		*Step Name: Step 5: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Open All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- All Tasks is opened*/
		mgTask.selectOptionTask(optionTask.All_Tasks);
		
		/*Step number: 7
		*Step Name: Step 7: Click on taskA title
		*Step Description: 
			- open taskA
			- click on taskA title
		*Input Data: 
			
		*Expected Outcome: 
			- userA can edit taskA*/ 
		mgTask.openTask(task1);
		mgTask.checkMenuOfTask();
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}
	/**
	*<li> Case ID:140738.</li>
	*<li> Test Case Name: Check case assigned user does not have edit permission.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-223
	*/
	@Test (groups="pending")
	public  void test10_CheckCaseAssignedUserDoesNotHaveEditPermission() {
		info("Test 10: Check case assigned user does not have edit permission");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Create projectA
		*Step Description: 
			- from context menu of Projects, add projectA
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created*/
		mgProject.addProject(prj1,"","", false);
		
		/*Step number: 3
		*Step Name: Step 3: Create taskA in projectA
		*Step Description: 
			- add taskA to projectA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTask(prj1, task1);
		
		/*Step number: 4
		*Step Name: Step 4: Assign taskA to userA
		*Step Description: 
			- assign taskA to userA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is assigned*/
		mgTask.editTaskUnAssignee(task1, DATA_USER3);
		
		/*Step number: 5
		*Step Name: Step 5: Login as userA
		*Step Description: 
			- login as userA
		*Input Data: 
			
		*Expected Outcome: 
			- userA logins successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 6
		*Step Name: Step 6: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 7
		*Step Name: Step 7: Open All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- All Tasks is opened*/
		mgTask.selectOptionTask(optionTask.All_Tasks);
		
		/*Step number: 8
		*Step Name: Step 8: Click on taskA title
		*Step Description: 
			- open taskA
			- click on taskA title
		*Input Data: 
			
		*Expected Outcome: 
			- cannot click on taskA title*/ 
		mgTask.checkEditableOfTaskTitle(task1,false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1,false);
 	}
}