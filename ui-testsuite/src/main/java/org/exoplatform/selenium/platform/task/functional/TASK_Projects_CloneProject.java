package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
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
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
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
		mgProject.cloneProject(name,false);
		
		/*Step number: 2
		*Step Name: Step 2: Check name of cloned project
		*Step Description: 
			- Clone project A from contextual menu of project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- a cloned project is automatically given the same name prefixed by "Copy of"*/
		info("delete data");
		mgProject.deleteProject("Copy of "+name, false);
		mgProject.deleteProject(name, false);
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
		hp.goToTasks();
		mgProject.addProject(name,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Clone project
		*Step Description: 
			- Clone project A
		*Input Data: 
			
		*Expected Outcome: 
			project A is cloned*/
		/*Step number: 3
		*Step Name: Step 3: Check Cancel button when cloning project
		*Step Description: 
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			Cancel dismisses the dialog without making a change*/ 
		mgProject.cancelCloneProject(name);
		
		info("delete data");
		mgProject.deleteProject(name, false);
 	}

	/**
	*<li> Case ID:130999.</li>
	*<li> Test Case Name: Check cloned project inherits.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A1 is added to project A and has setted permission, description, color, due date</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-187
	*/
	@Test (groups="pending")
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
		hp.goToTasks();
		mgProject.addProject(parent,"", false);
		mgProject.addSubProject(parent,child,child, false);
		mgProject.selectColor(child, color1);
		
		/*Step number: 2
		*Step Name: Step 2: Check cloned project inherits
		*Step Description: 
			- Clone project A1
		*Input Data: 
			
		*Expected Outcome: 
			a cloned project inherits: workflow, permission, description, color, due date and parent project*/ 
		mgProject.cloneProject(child, false);
		mgProject.checkClonedProjectInherits(child, parent,DATA_NAME_USER1,color1,child);
		
		info("delete data");
		mgProject.deleteProject(parent, true,child);
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
		hp.goToTasks();
		mgProject.addProject(prj1,"", false);
		mgTask.addTask(prj1, task1);
		mgProject.addProject(prj2,"", false);
		mgTask.addTask(prj2, task2);
		
		/*Step number: 2
		*Step Name: Step 2: Check when option to clone task is checked
		*Step Description: 
			- Clone project A and its tasks
		*Input Data: 
			
		*Expected Outcome: 
			- project A is cloned
			- all uncompleted task of this project cloned*/
		mgProject.cloneProject(prj1, true,task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check when option to clone task is unchecked
		*Step Description: 
			- Clone project A without its tasks
		*Input Data: 
			
		*Expected Outcome: 
			- project A is cloned
			- all uncompleted task of this project is NOT cloned*/ 
		mgProject.cloneProject(prj2, false,task2);
		
		info("delete data");
		mgProject.deleteProject("Copy of "+ prj1, false);
		mgProject.deleteProject("Copy of "+ prj2, false);
		mgProject.deleteProject(prj1, false);
		mgProject.deleteProject(prj2, false);
	}}