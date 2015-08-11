package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskChanges extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130985.</li>
	*<li> Test Case Name: Check display of add label in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*NOT IMPLEMENT YET
	*/
	@Test
	public  void test01_CheckDisplayOfAddLabelInTaskChange() {
		info("Test 1: Check display of add label in task change");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/

		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Edit label for task A
		*Input Data: 
			
		*Expected Outcome: 
			label is added*/

		/*Step number: 4
		*Step Name: Step 4: Check display of add label in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName labeled $Label*/ 

 	}

	/**
	*<li> Case ID:130982.</li>
	*<li> Test Case Name: Check display of assign task in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDisplayOfAssignTaskInTaskChange() {
		info("Test 3: Check display of assign task in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(14);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Assign task A to user A
		*Input Data: 
			
		*Expected Outcome: 
			task A is assign to user A*/
		mgTask.editTaskAssignee(task1, DATA_USER2);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of assign task in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName assigned to $AssigneeFullName*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text,DATA_NAME_USER2);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130981.</li>
	*<li> Test Case Name: Check display of change project in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-156
	*/
	@Test (groups="pending")
	public  void test04_CheckDisplayOfChangeProjectInTaskChange() {
		info("Test 4: Check display of change project in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(13);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Change project of task A
		*Input Data: 
			
		*Expected Outcome: 
			project is changed*/
		mgTask.editTaskProject(task1, prj1);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of change project in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName moved into @Project.*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text,prj1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130984.</li>
	*<li> Test Case Name: Check display of change task status in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-156
	*/
	@Test (groups="pending")
	public  void test05_CheckDisplayOfChangeTaskStatusInTaskChange() {
		info("Test 5: Check display of change task status in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(16);
		String status = flowData.getFlowByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in project A
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Edit status of task A
		*Input Data: 
			
		*Expected Outcome: 
			status is updated*/
		mgTask.editTaskStatus(task1, status);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of change task status in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName marked $TaskStatus*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text,status);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130977.</li>
	*<li> Test Case Name: Check display of create task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckDisplayOfCreateTask() {
		info("Test 6: Check display of create task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(9);
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
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of create task in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName created task. Ex: John Smith created task*/ 
		mgTask.openTask(task1);
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130979.</li>
	*<li> Test Case Name: Check display of edit task description in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckDisplayOfEditTaskDescriptionInTaskChange() {
		info("Test 7: Check display of edit task description in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(11);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Edit desciption of task A
		*Input Data: 
			
		*Expected Outcome: 
			Description is updated*/
		mgTask.editTaskDescription(task1, des);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of edit task description in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName edited description.*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130980.</li>
	*<li> Test Case Name: Check display of edit task due date in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckDisplayOfEditTaskDueDateInTaskChange() {
		info("Test 8: Check display of edit task due date in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(12);
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
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task
		*Step Description: 
			- Set due date for task
		*Input Data: 
			
		*Expected Outcome: 
			Due Date is setted*/
		mgTask.setDueDate(task1, getDate(1, "dd MMM yyyy"),getDate(1, "dd"),0);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of edit task due date in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName edited due date.*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130978.</li>
	*<li> Test Case Name: Check display of edit task title in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130989.</li>
	*<li> Test Case Name: Check time format of all changes logs in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_13_CheckDisplayOfEditTaskTitleInTaskChange() {
		info("Test 9: Check display of edit task title in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(10);
		String time = welcomeMesData.getWelcomeMessage(20);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Edit title of task A
		*Input Data: 
			
		*Expected Outcome: 
			Title is updated*/
		mgTask.editTaskTitle(task1, title);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of edit task title in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName edited title.*/ 
		/*Step number: 4
		*Step Name: Step 4: Check time format of all changes logs in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			All changes logs are displayed with the time format similar to time on the activity stream.*/
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		mgTask.checkDisplayOfChangeTime(DATA_NAME_USER1, text,time);
		
		info("delete data");
		mgTask.deleteTask(title);
 	}

	/**
	*<li> Case ID:130986.</li>
	*<li> Test Case Name: Check display of edit work plan in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created with work plan</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckDisplayOfEditWorkPlanInTaskChange() {
		info("Test 10 Check display of edit work plan in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(18);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Edit work plan of task A
		*Input Data: 
			
		*Expected Outcome: 
			Work plan is updated*/
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(0,"dd"), 1," "," ",true);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of edit work plan in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName edited work plan*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130988.</li>
	*<li> Test Case Name: Check display of mark task as done in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-156
	*/
	@Test (groups="pending")
	public  void test11_CheckDisplayOfMarkTaskAsDoneInTaskChange() {
		info("Test 11 Check display of mark task as done in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(19);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task A
		*Step Description: 
			- Add New Task in project A
		*Input Data: 
			
		*Expected Outcome: 
			task A is created*/
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Click on icon complete task
		*Step Description: 
			- Click on icon complete task
		*Input Data: 
			
		*Expected Outcome: 
			task A is changed to done*/
		mgTask.completeTask(task1);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of mark task as done in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName completed this task.*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130983.</li>
	*<li> Test Case Name: Check display of resign task in task change.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and assign to user A</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-156
	*/
	@Test (groups="pending")
	public  void test12_CheckDisplayOfResignTaskInTaskChange() {
		info("Test 12 Check display of resign task in task change");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String text = welcomeMesData.getWelcomeMessage(15);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.addTaskDirectly(task1);
		mgTask.editTaskAssignee(task1, DATA_USER2);
		
		/*Step number: 3
		*Step Name: Step 3: Edit task A
		*Step Description: 
			- Remove user A from assignee
		*Input Data: 
			
		*Expected Outcome: 
			user A is removed*/
		mgTask.removeAssigneeOfTask(task1, DATA_NAME_USER2);
		
		/*Step number: 4
		*Step Name: Step 4: Check display of resign task in task change
		*Step Description: 
			- Click on tab Changes on task details to view change
		*Input Data: 
			
		*Expected Outcome: 
			$FullName unassigned from $AssigneeFullName*/ 
		mgTask.checkDisplayOfChange(DATA_NAME_USER1, text,DATA_NAME_USER2);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}
}