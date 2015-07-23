package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

import org.testng.annotations.*;


	public class TASK_Projects_Subproject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128220.</li>
	*<li> Test Case Name: Check a project can have any number of sub-projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAProjectCanHaveAnyNumberOfSubprojects() {
		info("Test 1: Check a project can have any number of sub-projects");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj12 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj13 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgProject.addProject(prj1,"", false);
	
		/*Step number: 2
		*Step Name: Step 2: Add some sub
		-projects
		*Step Description: 
			- Add Project from contextual menu of project A: project A1, project A2, project A3
		*Input Data: 
			
		*Expected Outcome: 
			- project A1, project A2, project A3 are added to parent project A*/ 
		info("add project from project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",""));
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj12,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",""));
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj13,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",""));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj12));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj13));
	}

	/**
	*<li> Case ID:128221.</li>
	*<li> Test Case Name: Check a sub-project can have any number of sub-projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A, add project A1 as sub-project of project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckASubprojectCanHaveAnyNumberOfSubprojects() {
		info("Test 2: Check a sub-project can have any number of sub-projects");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj111 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj112 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj113 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgProject.addProject(prj1,"", false);
		info("add project from project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Create some sub-projects in a sub-project
		*Step Description: 
			- Add Project from contextual menu of project A1: project A11, project A12, project A13
		*Input Data: 
			
		*Expected Outcome: 
			- project A11, project A12, project A13 are added to parent: project A1*/ 
		info("add project from project 11");
		mgProject.selectOpContMenuGivenProject(prj11, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj111,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",prj11));
		mgProject.selectOpContMenuGivenProject(prj11, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj112,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",prj11));
		mgProject.selectOpContMenuGivenProject(prj11, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj113,"", false);
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj1).replace("$child",prj11));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj111));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj112));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj113));
	}

	/**
	*<li> Case ID:128223.</li>
	*<li> Test Case Name: Check Sub-projects DO NOT inherit the calendar from parent.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project Aadd some tasks to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckSubprojectsDONOTInheritTheCalendarFromParent() {
		info("Test 3: Check Sub-projects DO NOT inherit the calendar from parent");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add task into project 1");
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Create sub
		-project of project A
		*Step Description: 
			- Add Project from contextual menu of project A: project A1
		*Input Data: 
			
		*Expected Outcome: 
			- project A1 is added to parent project A
			- project A1 is empty. It is not inherit tasks from project A*/ 
		info("add project to project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		waitForElementNotPresent(mgProject.ELEMENT_TASK_TITLE.replace("$task", task1));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		
 	}

	/**
	*<li> Case ID:128222.</li>
	*<li> Test Case Name: Check Sub-projects inherit permission from their parent.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A, share project A with user Auser B is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckSubprojectsInheritPermissionFromTheirParent() {
		info("Test 4: Check Sub-projects inherit permission from their parent");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};
		String[] user2 = {DATA_USER3};
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
		mgProject.addProject(prj1,"", false);
		info("add participant");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Share);
		mgProject.shareProject(user1, false);
		
		/*Step number: 2
		*Step Name: Step 2: Create sub-project of project A
		*Step Description: 
			- Add Project from contextual menu of project A: project A1
		*Input Data: 
			
		*Expected Outcome: 
			- project A1 is added to parent project A
			- in Project Overview, manager is creator, paticipant is user A (permission is inherit from parent)*/
		info("add project to project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		mgProject.selectOpContMenuGivenProject(prj11, optionContMenuGivenProject.Share);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT.replace("$user",DATA_NAME_USER2));
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER.replace("$user",DATA_NAME_USER1));
	
		/*Step number: 3
		*Step Name: Step 3: Share sub
		-project to user B
		*Step Description: 
			- from contextual menu ofproject A1, share project A1 with user A
		*Input Data: 
			
		*Expected Outcome: 
			- project A1 is shared to user B*/ 
		info("share project 11 to james");
		mgProject.shareProject(user2, false);
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
 	}

	/**
	*<li> Case ID:130996.</li>
	*<li> Test Case Name: Check Sub-projects inherit workflow from their parent.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSubprojectsInheritWorkflowFromTheirParent() {
		info("Test 5: Check Sub-projects inherit workflow from their parent");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		info("add task into project 1");
		mgTask.addTask(prj1, task1);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_TASK_WORKFLOW_TEXT.replace("$flow","To Do"));
		
		/*Step number: 2
		*Step Name: Step 2: Check Sub-projects inherit workflow from their parent
		*Step Description: 
			- Create sub project A1 of project A
		*Input Data: 
			
		*Expected Outcome: 
			Sub-projects inherit workflow from their parent at creation time. They can evolve separately afterwards.*/ 
		info("add project to project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		info("add task into project 11");
		mgTask.addTask(prj11, task11);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_TASK_WORKFLOW_TEXT.replace("$flow","To Do"));
 	
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
	}}