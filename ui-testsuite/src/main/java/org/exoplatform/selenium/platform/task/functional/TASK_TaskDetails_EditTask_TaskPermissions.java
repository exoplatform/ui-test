package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskPermissions extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128260.</li>
	*<li> Test Case Name: Check accessible permission to a task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is shared to user Atask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAccessiblePermissionToATask() {
		info("Test 1: Check accessible permission to a task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users={DATA_USER2};
		info("pre-condition");
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		mgTask.addTask(prj1, task1);
		mgProject.shareProject(prj1, users,true);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Open project A
		*Step Description: 
			- Click on project A on left pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- task list of project A is opened*/
		mgProject.openProject(prj1);
		
		/*Step number: 4
		*Step Name: Step 4: Check accessible permission to a task
		*Step Description: 
			- Click on task A on central pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- on task detail, from the arrow menu, it allows to do anything on the task.*/ 
		mgTask.openTask(task1);
		mgTask.checkMenuOfTask();
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128264.</li>
	*<li> Test Case Name: Check assignee and coworkers gain access to the task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installeduser A create task A, not add to a project, assign to user B, coworker is user C</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAssigneeAndCoworkersGainAccessToTheTask() {
		info("Test 2: Check assignee and coworkers gain access to the task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("pre-condition");
		hp.goToTasks();
		mgTask.addTaskDirectly(task1,true);
		mgTask.editTaskAssignee(task1, DATA_NAME_USER1, DATA_USER2,DATA_USER3);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user B
		*Step Description: 
			- Login as user B
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();

		/*Step number: 3
		*Step Name: Step 3: Check assignee gain access to the task
		*Step Description: 
			- Click on Incoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is in the list*/
		waitForAndGetElement(mgTask.ELEMENT_TASK_TITLE.replace("$task", task1));
		
		/*Step number: 4
		*Step Name: Step 4: Login as user C
		*Step Description: 
			- Login as user C
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
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
		*Step Name: Step 6: Check coworker gain access to the task
		*Step Description: 
			- Click on Incoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is in the list*/ 
		waitForAndGetElement(mgTask.ELEMENT_TASK_TITLE.replace("$task", task1));
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128265.</li>
	*<li> Test Case Name: Check members and managers of the project gain access to the task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installeduser A is manager of project A, user B, user C are participants of project Auser B create task A in project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckMembersAndManagersOfTheProjectGainAccessToTheTask() {
		info("Test 3: Check members and managers of the project gain access to the task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users={DATA_USER2,DATA_USER3};
		info("pre-condition");
		hp.goToTasks();
		mgProject.addProject(prj1, "", "",false);
		mgProject.shareProject(prj1, users,false);
		
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToTasks();
		mgTask.addTask(prj1, task1);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check managers of the project gain access to the task
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is in the list*/
		mgTask.checkTasksOfProject(prj1,task1);
		
		/*Step number: 4
		*Step Name: Step 4: Login as user C
		*Step Description: 
			- Login as user C
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
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
		*Step Name: Step 6: Check members of the project gain access to the task
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is in the list*/ 
		mgTask.checkTasksOfProject(prj1,task1);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128263.</li>
	*<li> Test Case Name: Check Personal tasks are created accessible only to the creator.</li>
	*<li> Pre-Condition: exo-tasks add-on is installeduser A create task A and not add to a project</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckPersonalTasksAreCreatedAccessibleOnlyToTheCreator() {
		info("Test 4: Check Personal tasks are created accessible only to the creator");
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
		*Step Name: Step 2: Check Personal tasks are created accessible only to the creator
		*Step Description: 
			- Click on Incoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is in the list*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Login as user B
		*Step Description: 
			- Login as user B
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 4
		*Step Name: Step 4: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 5
		*Step Name: Step 5: Check Personal tasks are created accessible only to the creator
		*Step Description: 
			- Click on Incoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is not in the list*/ 
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task1));
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128261.</li>
	*<li> Test Case Name: Check Tasks inherit permissions from the project they are in.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created and shared to user Atask A is added to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckTasksInheritPermissionsFromTheProjectTheyAreIn() {
		info("Test 5: Check Tasks inherit permissions from the project they are in");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users={DATA_USER3};
		info("pre-condition");
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		mgProject.shareProject(prj1, users,false);
		mgTask.addTask(prj1, task1);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Open project A
		*Step Description: 
			- Click on project A on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			- project A is opened*/
		mgTask.checkTasksOfProject(prj1, task1);
		
		/*Step number: 4
		*Step Name: Step 4: Check Tasks inherit permissions from the project they are in
		*Step Description: 
			- Click on task A on the central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks inherit permissions from the project they are in*/ 
		mgTask.openTask(task1);
		mgTask.checkMenuOfTask();
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}}