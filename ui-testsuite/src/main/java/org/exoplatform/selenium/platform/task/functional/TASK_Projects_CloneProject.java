package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

import org.testng.annotations.*;


	public class TASK_Projects_CloneProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128234.</li>
	*<li> Test Case Name: Check a project can be cloned from Arrow menu > Clone.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128236.</li>
	*<li> Test Case Name: Check clone action of project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131000.</li>
	*<li> Test Case Name: Check confirm dialog when cloning project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128235.</li>
	*<li> Test Case Name: Check name of cloned project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_05_06_CheckAProjectCanBeClonedFromArrowMenuClone() {
		info("Test 1: Check a project can be cloned from Arrow menu > Clone");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mes0 = welcomeMesData.getWelcomeMessage(7);
		String mes1 = welcomeMesData.getWelcomeMessage(8);
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
		*Step Name: Step 2: Check confirm dialog when cloning project
		*Step Description: 
			- Clone project A
		*Input Data: 
			
		*Expected Outcome: 
			A confirmation dialog opens :
			-title : Confirmation
			-message : You are about to clone $Project project. Please confirm
			- checkbox : also clone uncompleted tasks
			- actions : [Clone] [Cancel]*/ 
		info("clone project");
		mgProject.selectOpContMenuGivenProject(name, optionContMenuGivenProject.Clone);
		waitForAndGetElement(mgProject.ELEMENT_CLONE_PROJECT_CANCEL_BUTTON);
		waitForAndGetElement(mgProject.ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
		waitForAndGetElement(mgProject.ELEMENT_CONFIRMATION_POPUP_TITLE);
		waitForAndGetElement(mgProject.ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX_TEXT.replace("$mes",mes0));
		waitForAndGetElement(mgProject.ELEMENT_CLONE_PROJECT_POPUP_MESSAGE.replace("$project",name).replace("$mes",mes1));
		mgProject.cloneProject(name,false);
		
		/*Step number: 2
		*Step Name: Step 2: Check name of cloned project
		*Step Description: 
			- Clone project A from contextual menu of project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- a cloned project is automatically given the same name prefixed by "Copy of"*/
		waitForAndGetElement(mgTask.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+name));
		info("delete data");
		mgProject.selectOpContMenuGivenProject(name,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(name, false);
		mgProject.selectOpContMenuGivenProject(name,optionContMenuGivenProject.Delete);
		mgProject.deleteProject("Copy of "+name, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", name));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+name ));
 	}

	/**
	*<li> Case ID:131001.</li>
	*<li> Test Case Name: Check Cancel button when cloning project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckCancelButtonWhenCloningProject() {
		info("Test 2: Check Cancel button when cloning project");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(name,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Clone project
		*Step Description: 
			- Clone project A
		*Input Data: 
			
		*Expected Outcome: 
			project A is cloned*/
		info("clone project");
		mgProject.selectOpContMenuGivenProject(name, optionContMenuGivenProject.Clone);
		
		/*Step number: 3
		*Step Name: Step 3: Check Cancel button when cloning project
		*Step Description: 
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			Cancel dismisses the dialog without making a change*/ 
		click(mgProject.ELEMENT_CLONE_PROJECT_CANCEL_BUTTON);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+name));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(name,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(name, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", name));
		
 	}

	/**
	*<li> Case ID:130999.</li>
	*<li> Test Case Name: Check cloned project inherits.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A1 is added to project A and has setted permission, description, color, due date</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckClonedProjectInherits() {
		info("Test 4: Check cloned project inherits");
		String parent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color1 = colorData.getClassNameByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(parent,"", false);
		mgProject.selectOpContMenuGivenProject(parent,optionContMenuGivenProject.Add_Project);
		mgProject.addProject(child,child, false);
		info("add color to child");
		mgProject.selectColor(child, color1);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", child).replace("$color", color1));
		
		/*Step number: 2
		*Step Name: Step 2: Check cloned project inherits
		*Step Description: 
			- Clone project A1
		*Input Data: 
			
		*Expected Outcome: 
			a cloned project inherits: workflow, permission, description, color, due date and parent project*/ 
		info("clone child");
		mgProject.selectOpContMenuGivenProject(child, optionContMenuGivenProject.Clone);
		mgProject.cloneProject(child, false);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+child));
		info("check inherits");
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", "Copy of "+child).replace("$color", color1));
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Copy of "+child));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_MANAGER_NAME.replace("$user", DATA_NAME_USER1));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project",parent));
		waitForAndGetElement(mgProject.ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", child));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(parent,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(parent, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", parent));
	}
	
	/**
	*<li> Case ID:131003.</li>
	*<li> Test Case Name: Check when option to clone task is checked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdadd some uncompleted tasks to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckWhenOptionToCloneTaskIsChecked() {
		info("Test 7: Check when option to clone task is checked");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add task into project");
		mgTask.addTask(prj1, task1);
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj2,"", false);
		info("add task into project");
		mgTask.addTask(prj2, task2);
		
		/*Step number: 2
		*Step Name: Step 2: Check when option to clone task is checked
		*Step Description: 
			- Clone project A and its tasks
		*Input Data: 
			
		*Expected Outcome: 
			- project A is cloned
			- all uncompleted task of this project cloned*/
		info("clone project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Clone);
		mgProject.cloneProject(prj1, true);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+prj1));
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+prj1));
		waitForAndGetElement(mgProject.ELEMENT_TASK_TITLE.replace("$task","Copy of "+task1));
		
		/*Step number: 3
		*Step Name: Step 3: Check when option to clone task is unchecked
		*Step Description: 
			- Clone project A without its tasks
		*Input Data: 
			
		*Expected Outcome: 
			- project A is cloned
			- all uncompleted task of this project is NOT cloned*/ 
		info("clone project 2 without uncompleted task");
		mgProject.selectOpContMenuGivenProject(prj2, optionContMenuGivenProject.Clone);
		mgProject.cloneProject(prj2, false);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+prj2));
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+prj2));
		waitForElementNotPresent(mgProject.ELEMENT_TASK_TITLE.replace("$task","Copy of "+task2));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		mgProject.selectOpContMenuGivenProject(prj2,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj2, false);
		mgProject.selectOpContMenuGivenProject("Copy of "+ prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject("Copy of "+ prj1, false);
		mgProject.selectOpContMenuGivenProject("Copy of "+ prj2,optionContMenuGivenProject.Delete);
		mgProject.deleteProject("Copy of "+ prj2, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+ prj2));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",prj2));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",prj1));
	}}