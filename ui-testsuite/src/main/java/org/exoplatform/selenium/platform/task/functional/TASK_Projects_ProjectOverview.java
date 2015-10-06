package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Projects_ProjectOverview extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128185.</li>
	*<li> Test Case Name: Check color block of each task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added to created projects</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckColorBlockOfEachTask() {
		info("Test 1: Check color block of each task");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color1 = colorData.getClassNameByArrayTypeRandom(1);
		String color2 = colorData.getClassNameByArrayTypeRandom(3);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		mgTask.addTask(prj1, task1);
		mgProject.addProject(prj2,"","", false);
		mgTask.addTask(prj2, task2);
		
		/*Step number: 2
		*Step Name: Step 2: Check color block of each task
		*Step Description: 
			- Click on Projects on left pane
			- Check list tasks in central pane
		*Input Data: 
			
		*Expected Outcome: 
			There is color block before each task to identify which project it belongs to.*/ 
		mgProject.selectColor(prj1, color1,task1);
		mgProject.selectColor(prj2, color2,task2);

		info("delete data");
		mgProject.deleteProject(prj1, false);
		mgProject.deleteProject(prj2, false);
 	}

	/**
	*<li> Case ID:128177.</li>
	*<li> Test Case Name: Check contextual menu of one project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test02_CheckContextualMenuOfOneProject() {
		info("Test 2: Check contextual menu of one project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users= {DATA_USER2};
		/*Step Number: 1
		*Step Name: Step 1: Login as manager
		*Step Description: 
			- Login as manager
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		
		/*Step number: 3
		*Step Name: Step 3: Check contextual menu of one project
		*Step Description: 
			- Check contextual menu of one project on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			project has a contextual menu :Edit, Share, Clone, Hide, Delete, Add Project*/
		mgProject.checkMenuGivenProjectOfUser(prj1, true);
		mgProject.shareProject(prj1,users, false);
		
		/*Step number: 4
		*Step Name: Step 4: Login as paticipant of one project (not manager)
		*Step Description: 
			- Login as paticipant of one project (not manager)
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 5
		*Step Name: Step 5: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Check contextual menu of one project
		*Step Description: 
			- Check contextual menu of one project on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			project has a contextual menu : Hide*/ 
		mgProject.checkMenuGivenProjectOfUser(prj1, false);
		
		info("login as john");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128176.</li>
	*<li> Test Case Name: Check contextual menu of Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckContextualMenuOfProjects() {
		info("Test 3: Check contextual menu of Projects");
		/*Step Number: 1
		*Step Name: Step 1: Login as manager
		*Step Description: 
			- Login as manager
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check contextual menu of Projects
		*Step Description: 
			- Check contextual menu of Projects on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects has a contextual menu :Add Project, Show Hidden Projects*/
		mgProject.checkMenuProjectsOfUser(true);
	
		/*Step number: 4
		*Step Name: Step 4: Login as paticipant of one project (not manager)
		*Step Description: 
			- Login as paticipant of one project (not manager)
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/
		info("login as demo");
		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		
		/*Step number: 5
		*Step Name: Step 5: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Check contextual menu of Projects
		*Step Description: 
			- Check contextual menu of Projects on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects has a contextual menu :Add Project,Show Hidden Projects*/ 
		mgProject.checkMenuProjectsOfUser(true);
 	}

	/**
	*<li> Case ID:128192.</li>
	*<li> Test Case Name: Check group by of tasks in each project in Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,Badd some tasks to project A,B</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test04_CheckGroupByOfTasksInEachProjectInProjects() {
		info("Test 4: Check group by of tasks in each project in Projects");
		String project1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String none = groupByData.getGroupBy(0);
		String duedate = groupByData.getGroupBy(1);
		String label = groupByData.getGroupBy(2);
		String status = groupByData.getGroupBy(3);
		String assignee = groupByData.getGroupBy(4);
		String project = groupByData.getGroupBy(5);
		String[] groups = {none,duedate,status,assignee,project,label};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project1,"","", false);
		mgTask.addTask(project1, task1);
		mgTask.addTask(project1, task2);
		mgProject.addProject(project2,"","", false);
		mgTask.addTask(project2, task3);
		mgTask.addTask(project2, task4);
		
		/*Step number: 2
		*Step Name: Step 2: Check group by of tasks in each project in Projects
		*Step Description: 
			- Click on Projects on left pane
			- Click on Group byon central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks of each project are Group by: None, Assignee, Project, Label, Due Date, Status*/ 
		mgProject.checkGroupByInProjects("Projects",groups,false);
		
		info("delete data");
		mgProject.deleteProject(project1, false);
		mgProject.deleteProject(project2, false);
 	}

	/**
	*<li> Case ID:128183.</li>
	*<li> Test Case Name: Check no Board on Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckNoBoardOnProjects() {
		info("Test 5: Check no Board on Projects");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check no Board on Projects
		*Step Description: 
			- Click on Projects on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			There is no Board on Projects*/ 
		mgProject.checkDisplayOfListBoard("Projects",false);
 	}

	/**
	*<li> Case ID:128175.</li>
	*<li> Test Case Name: Check project by default.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-140
	*/
	/**
	*<li> Case ID:128182.</li>
	*<li> Test Case Name: Check Projects view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test (groups= "pending")
	public  void test06_07_CheckProjectByDefault() {
		info("Test 6: Check project by default");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open Projects overview
		*Step Description: 
			- Click on Projects overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects overview is opened*/
		/*Step number: 3
		*Step Name: Step 3: Check Projects on central pane
		*Step Description: 
			- Check Projects on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- There is no project created by default in the project list on central pane
			- big icon project is displayed 
			- label No Project is displayed
			- tooltip: Click here to create your first project.*/ 
		mgProject.checkProjectsByDefault();
 	}
	
	/**
	*<li> Case ID:128184.</li>
	*<li> Test Case Name: Check Projects view default setting.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test08_CheckProjectsViewDefaultSetting() {
		info("Test 8: Check Projects view default setting");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String none = groupByData.getGroupBy(0);
		String duedate = sortByData.getSortBy(0);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"","", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.addTask(project, task3);
		
		/*Step number: 2
		*Step Name: Step 2: Check Projects view default setting
		*Step Description: 
			- Click on Projects on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects view default settings are:
			- Group by: None
			- Sort by: Due Date*/ 
		mgProject.checkDefaultGroupSort("Projects", none, duedate,false);
 	}

	/**
	*<li> Case ID:128186.</li>
	*<li> Test Case Name: Check sort by of tasks in each project in Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,Badd some tasks to project A,B</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test09_CheckSortByOfTasksInEachProjectInProjects() {
		info("Test 9: Check sort by of tasks in each project in Projects");
		String project1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String duedate = sortByData.getSortBy(0);
		String title = sortByData.getSortBy(1);
		String createdDate = sortByData.getSortBy(2);
		String priority = sortByData.getSortBy(3);
		String[] sorts = {duedate,title,createdDate,priority};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project1,"","", false);
		mgTask.addTask(project1, task1);
		mgTask.addTask(project1, task2);
		mgProject.addProject(project2,"","", false);
		mgTask.addTask(project2, task3);
		mgTask.addTask(project2, task4);
		
		/*Step number: 2
		*Step Name: Step 2: Check sort by of tasks in each project in Projects
		*Step Description: 
			- Click on Projects on left pane
			- Click on Sort byon central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks of each project are Sorted by: Title, Created Date, Due Date, Priority.*/ 
		mgProject.checkSortByInProjects("Projects", sorts,false);
		
		info("delete data");
		mgProject.deleteProject(project1, false);
		mgProject.deleteProject(project2, false);
 	}

	/**
	*<li> Case ID:128178.</li>
	*<li> Test Case Name: Check welcome messages of first access.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedone personal project and one share project are created</li>
	*<li> Post-Condition: </li>
	* BUG
	*/
	@Test (groups="pending")
	public  void test10_CheckWelcomeMessagesOfFirstAccess() {
		info("Test 10 Check welcome messages of first access");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("login as root");
		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check case of viewing Projects
		*Step Description: 
			- Click on Projects on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			It displays: No Project*/
		mgProject.checkProjectsByDefault();
		
		/*Step number: 3
		*Step Name: Step 3: Check case of viewing personal project
		*Step Description: 
			- Click on personal project on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			- It displays: No Task*/
		mgProject.addProject(project,"","", false);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128180.</li>
	*<li> Test Case Name: Check welcome screen is disppeared when adding project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedno project is added</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckWelcomeScreenIsDisppearedWhenAddingProject() {
		info("Test 11 Check welcome screen is disppeared when adding project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add new project
		*Step Description: 
			- Click on Projects on the left pane
			- Create new project
		*Input Data: 
			
		*Expected Outcome: 
			- New project is created
			- The message Click here to create your first project. and No Project screen are disappeared when there is a project added.*/ 
		mgProject.addProject(project,"","", false);
		mgProject.checkProjectDetailByDefault(project);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128179.</li>
	*<li> Test Case Name: Check welcome screen is disppeared when adding task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedno task is added to project</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckWelcomeScreenIsDisppearedWhenAddingTask() {
		info("Test 12 Check welcome screen is disppeared when adding task");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String defaultStatus= flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Add task to one project
		*Step Description: 
			- Click on one project on the left pane
			- Create a task
		*Input Data: 
			
		*Expected Outcome: 
			- Task is added to project
			- The message Let's create your first task. and welcome screen are disappeared when there is a task added.*/ 
		mgTask.addTask(project, task);
		mgTask.checkTaskDetail(task,true,project,defaultStatus);
		
		info("delete project");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128181.</li>
	*<li> Test Case Name: Project is added when the app is added to the space.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedspace0 is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_ProjectIsAddedWhenTheAppIsAddedToTheSpace() {
		info("Test 13 Project is added when the app is added to the space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Add Task application to space
		*Step Description: 
			- Goto space0 setting 
			- Add Task application to space0
		*Input Data: 
			
		*Expected Outcome: 
			Task application is added to space*/
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		waitForAndGetElement(mgProject.ELEMENT_TASK_APP_ICON);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened
			- space0 project is added in left pane under Projects*/ 
		hp.goToTasks();
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", space));
		
		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);
 	}
	}