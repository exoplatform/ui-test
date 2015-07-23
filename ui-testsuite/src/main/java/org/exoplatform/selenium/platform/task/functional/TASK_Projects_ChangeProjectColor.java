package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

import org.testng.annotations.*;


	public class TASK_Projects_ChangeProjectColor extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128231.</li>
	*<li> Test Case Name: Check set color from contextual menu.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckSetColorFromContextualMenu() {
		info("Test 1: Check set color from contextual menu");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgProject.addProject(name,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check set color from contextual menu
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Check set color from contextual menu
		*Input Data: 
			
		*Expected Outcome: 
			- A project can be set color by No Color or click on specific color.*/ 
		mgProject.goToContMenuGivenProject(name);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_COLOR_NO_COLOR.replace("$project", name));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$project", name).replace("$color", "red"));
		mgProject.goToContMenuGivenProject(name);
		info("delete data");
		mgProject.selectOpContMenuGivenProject(name,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(name, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", name));
 	}

	/**
	*<li> Case ID:128232.</li>
	*<li> Test Case Name: Check when a color is ticked to project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdsome tasks are added to project A</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-173
	*/
	@Test (groups="pending")
	public  void test02_CheckWhenAColorIsTickedToProject() {
		info("Test 2: Check when a color is ticked to project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color1 = colorData.getClassNameByArrayTypeRandom(1);
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
		
		info("add task into project");
		mgTask.addTask(project, task);
		
		/*Step number: 2
		*Step Name: Step 2: Check when a color is ticked to project
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Tick on a color
		*Input Data: 
			
		*Expected Outcome: 
			- There is a color block that is colorized with the selected color in front of the project title in the left menu.
			- All the tasks are displayed with the color block that is set to its project.*/ 
		info("add color to project");
		mgProject.selectColor(project, color1);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", project).replace("$color", color1));
		waitForAndGetElement(mgProject.ELEMENT_TASK_COLOR.replace("$task", task).replace("$color", color1));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
 	}

	/**
	*<li> Case ID:128233.</li>
	*<li> Test Case Name: Check when No Color is ticked to project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdsome tasks are added to project A</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-173
	*/
	@Test (groups="pending")
	public  void test03_CheckWhenNoColorIsTickedToProject() {
		info("Test 3: Check when No Color is ticked to project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		
		info("add task into project");
		mgTask.addTask(project, task);
		
		/*Step number: 2
		*Step Name: Step 2: Check when No Color is ticked to project
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Tick on No Color
		*Input Data: 
			
		*Expected Outcome: 
			- There is no color block in front of the project title in the left menu.
			- All the tasks of no color project are displayed without the color block.*/ 
		mgProject.goToContMenuGivenProject(project);
		click(mgProject.ELEMENT_LEFT_PANE_COLOR_NO_COLOR.replace("$project", project));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NO_COLOR.replace("$project", project));
		waitForAndGetElement(mgProject.ELEMENT_TASK_NO_COLOR.replace("$task", task));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
 	}}