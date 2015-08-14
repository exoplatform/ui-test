package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;
import org.testng.annotations.*;

	public class TASK_TaskDetails_EditTask_Project extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128331.</li>
	*<li> Test Case Name: Check a task belongs to only 1 project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is added to project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128329.</li>
	*<li> Test Case Name: Check filter drop down to select project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created in Incomingproject A, project B are created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckATaskBelongsToOnly1Project() {
		info("Test 1: Check a task belongs to only 1 project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on project A on left pane
			- Click on task A on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- list tasks is opened
			- task A is opened*/
		mgProject.addProject(prj2, "", false);
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check a task belongs to only 1 project
		*Step Description: 
			- on right pane, click on project field to add project B
			- remove project A and add project B
		*Input Data: 
			
		*Expected Outcome: 
			- cannot add project B
			- project B is added in task detail*/ 
		mgTask.searchTaskProject(task1, prj2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
		mgProject.deleteProject(prj2, false);
 	}

	/**
	*<li> Case ID:128328.</li>
	*<li> Test Case Name: Check value of Project is dependent on the location of  Add Task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A, label A are created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckValueOfProjectIsDependentOnTheLocationOfAddTask() {
		info("Test 3: Check value of Project is dependent on the location of  Add Task");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String defaultStatus = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add Task in Incoming
		*Step Description: 
			- Click on Incoming on left pane
			- Input new task > Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created
			- project value is No Project*/
		mgTask.addTaskDirectly(task1,true);
		mgTask.checkTaskDetail(task1,false,"","");
		
		/*Step number: 3
		*Step Name: Step 3: Add Task in project A
		*Step Description: 
			- Click on project A on left pane
			- Input new task > Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created
			- project value is project A*/ 
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task2);
		mgTask.checkTaskDetail(task2,true,prj1,defaultStatus);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.deleteTask(task1);
 	}}