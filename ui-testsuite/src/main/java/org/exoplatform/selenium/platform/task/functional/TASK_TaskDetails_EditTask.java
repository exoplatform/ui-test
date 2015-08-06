package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;

	public class TASK_TaskDetails_EditTask extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128326.</li>
	*<li> Test Case Name: Check editable field on a task details pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created in project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128327.</li>
	*<li> Test Case Name: Save a being edited field on task details pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created in project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckEditableFieldOnATaskDetailsPane() {
		info("Test 1: Check editable field on a task details pane");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTask = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj2, "", false);
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on project A on left pane
			- Click on task A on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- list task of project A is opened
			- task detail of task A is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check editable field on a task details pane
		*Step Description: 
			- Hover on any editable field on a task details pane
		*Input Data: 
			
		*Expected Outcome: 
			- there is a light border and Click to edit tooltip to indicate it is possible to edit the field*/ 
		/*Step number: 3
		*Step Name: Step 3: Save a being edited field on task details pane
		*Step Description: 
			- Click on one field to edit, mouse click out the field area or press Enter from the keyboard
		*Input Data: 
			
		*Expected Outcome: 
			- Task detail is saved*/ 
		mgTask.editTaskProject(task1, prj2);
		mgTask.editTaskTitle(task1, newTask);
		mgTask.editTaskDescription(newTask, task1);
		mgTask.editTaskStatus(newTask, inProgress);
		mgTask.editTaskAssignee(newTask, DATA_USER2, DATA_USER3);
		mgTask.editTaskTag(newTask, tag);
		mgTask.editTaskWorkPlan(newTask,getDate(0,"dd"),0, getDate(1,"dd"),2, "01:00", "02:30",false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
		mgProject.deleteProject(prj2, false);
 	}
}