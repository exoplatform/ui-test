package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;

import org.openqa.selenium.Keys;

import org.testng.annotations.*;


	public class TASK_TaskDetails_CreateTask extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128266.</li>
	*<li> Test Case Name: Check a task can be created by 2 ways.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckATaskCanBeCreatedBy2Ways() {
		info("Test 1: Check a task can be created by 2 ways");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Create task by clicking on New Task button
		*Step Description: 
			- Click on New Task button, the mouse is focused on the blank field below to input task title.
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created*/
		click(mgTask.ELEMENT_ADD_TASK_BTN);
		waitForAndGetElement(mgTask.ELEMENT_ADD_TASK_TITLE).sendKeys(task1);
        Utils.pause(500);
        driver.findElement(mgTask.ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForAndGetElement(mgTask.ELEMENT_TASK_TITLE.replace("$task", task1));
        
		/*Step number: 3
		*Step Name: Step 3: Create task by clicking directly on the blank field
		*Step Description: 
			- Click directly on the blank field on the top of the pane to input task title.
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created*/ 
        click(mgTask.ELEMENT_ADD_TASK_TITLE);
        waitForAndGetElement(mgTask.ELEMENT_ADD_TASK_TITLE).sendKeys(task2);
        Utils.pause(500);
        driver.findElement(mgTask.ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForAndGetElement(mgTask.ELEMENT_TASK_TITLE.replace("$task", task2));
        
        info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
 	}

	/**
	*<li> Case ID:128271.</li>
	*<li> Test Case Name: Check creating task action.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128272.</li>
	*<li> Test Case Name: Check display of new task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckCreatingTaskAction() {
		info("Test 2: Check creating task action");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check display of new task
		*Step Description: 
			- Input new task and Enter on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- The new added task will be displayed on the top below Untitled Task field.*/
		info("add task");
		mgTask.addTask(project, task1);
		waitForAndGetElement(mgTask.ELEMENT_UNTITLEDTASK_AND_TASK_INPUT.replace("$task", task1));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
		
 	}

	/**
	*<li> Case ID:128276.</li>
	*<li> Test Case Name: Check display of tasks when the page is refreshed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are created in Incoming</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-183
	*/
	@Test (groups="pending")
	public  void test04_CheckDisplayOfTasksWhenThePageIsRefreshed() {
		info("Test 4: Check display of tasks when the page is refreshed");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String sort = sortByData.getSortBy(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task in Incoming
		*Step Description: 
			- Input new task on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created*/
		mgTask.selectOptionTask(optionTask.Incoming);
		info("add task");
		mgTask.addTask("Incoming", task1);
		mgTask.addTask("Incoming", task2);
		mgTask.addTask("Incoming", task3);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of tasks when the page is refreshed
		*Step Description: 
			- Refresh the page
		*Input Data: 
			
		*Expected Outcome: 
			- The added tasks will be rearranged following the Group By & Sort By options.*/ 
		driver.navigate().refresh();
		waitForAndGetElement(mgTask.ELEMENT_TASK_ORDER.replace("$num","1").replace("$task", task3));
		waitForAndGetElement(mgTask.ELEMENT_TASK_ORDER.replace("$num","2").replace("$task", task2));
		waitForAndGetElement(mgTask.ELEMENT_TASK_ORDER.replace("$num","3").replace("$task", task1));
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_DEFAULT_SORTBY.replace("$sort",sort));
		
		info("delete task");
		mgTask.deleteTask(task3);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task1);
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task1));
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task2));
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task3));
 	}

	/**
	*<li> Case ID:128270.</li>
	*<li> Test Case Name: Check position to add task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdlabel A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128274.</li>
	*<li> Test Case Name: Check when a task is created in a specific project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_09_CheckPositionToAddTask() {
		info("Test 5: Check position to add task");
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
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task from Incoming
		*Step Description: 
			- Click on Incoming on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/
		mgTask.selectOptionTask(optionTask.Incoming);
		info("add task");
		mgTask.addTask("Incoming", task1);
		
		/*Step number: 3
		*Step Name: Step 3: Create task from project A
		*Step Description: 
			- Click on project A on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add task");
		mgTask.addTask(prj1, task2);
		
		/*Step number: 4
		*Step Name: Step 4: Create task from label A
		*Step Description: 
			- Click on label A on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/ 
		
		info("delete task");
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.deleteTask(task1);
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task1));
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
 	}

	/**
	*<li> Case ID:128267.</li>
	*<li> Test Case Name: Check right pane when creating a task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128268.</li>
	*<li> Test Case Name: Check task detail form when a title is input in the blank row.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_07_CheckRightPaneWhenCreatingATask() {
		info("Test 6: Check right pane when creating a task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		mgTask.selectOptionTask(optionTask.Incoming);
		info("add task");
		mgTask.addTask("Incoming", task1);
		
		/*Step number: 2
		*Step Name: Step 2: Check right pane when creating a task
		*Step Description: 
			- Enter a new task on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- the right pane is opened with task details that can be editable.*/ 
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task1));
		click(mgTask.ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task1));
		click(mgTask.ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK);
		
		info("delete task");
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.deleteTask(task1);
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task1));
 	}

	/**
	*<li> Case ID:128269.</li>
	*<li> Test Case Name: Check the arrow menu on the top of task detail pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckTheArrowMenuOnTheTopOfTaskDetailPane() {
		info("Test 8: Check the arrow menu on the top of task detail pane");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		mgTask.selectOptionTask(optionTask.Incoming);
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Create task A on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created*/
		info("add task");
		mgTask.addTask("Incoming", task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check the arrow menu on the top of task detail pane
		*Step Description: 
			- on right pane, click on arrow menu
		*Input Data: 
			
		*Expected Outcome: 
			- The arrow menu on the top of task detail pane to display other actions: Clone, Watch, Delete.*/ 
		info("check menu of task detail");
		click(mgTask.ELEMENT_RIGHT_PANE_TASK_ARROW_MENU);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_WATCH);
		mouseOverAndClick(mgTask.ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
		waitForElementNotPresent(mgTask.ELEMENT_TASK_TITLE.replace("$task",task1));
 	}

}