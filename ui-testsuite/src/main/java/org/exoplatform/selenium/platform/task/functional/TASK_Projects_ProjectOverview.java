package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

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
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add task");
		mgTask.addTask(prj1, task1);
		info("add project 2 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj2,"", false);
		info("add task");
		mgTask.addTask(prj2, task2);
		
		info("add color to project 1");
		mgProject.selectColor(prj1, color1);
		info("add color to project 2");
		mgProject.selectColor(prj2, color2);
		/*Step number: 2
		*Step Name: Step 2: Check color block of each task
		*Step Description: 
			- Click on Projects on left pane
			- Check list tasks in central pane
		*Input Data: 
			
		*Expected Outcome: 
			There is color block before each task to identify which project it belongs to.*/ 
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		waitForAndGetElement(mgProject.ELEMENT_TASK_COLOR.replace("$task", task1).replace("$color", color1));
		waitForAndGetElement(mgProject.ELEMENT_TASK_COLOR.replace("$task", task2).replace("$color", color2));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		mgProject.selectOpContMenuGivenProject(prj2,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj2, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj2));
 	}

	/**
	*<li> Case ID:128177.</li>
	*<li> Test Case Name: Check contextual menu of one project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-132
	*/
	@Test (groups="pending")
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
		info("open task page");
		taskMgHome.goToTasks();
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 3
		*Step Name: Step 3: Check contextual menu of one project
		*Step Description: 
			- Check contextual menu of one project on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			project has a contextual menu :Edit, Share, Clone, Hide, Delete, Add Project*/
		info("check menu of project manager");
		mgProject.goToContMenuGivenProject(prj1);
		//Edit is not available
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_ADD.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_CLONE.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", prj1));
		
		info("share project to mary");
		mgProject.goToContMenuGivenProject(prj1);
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Share);
		mgProject.shareProject(users, false);
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
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Check contextual menu of one project
		*Step Description: 
			- Check contextual menu of one project on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			project has a contextual menu : Hide*/ 
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		info("check menu of project participant");
		//mgProject.goToContMenuGivenProject(prj1);
		//waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", prj1));
		
		info("login as john");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("open task page");
		taskMgHome.goToTasks();
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
 	}

	/**
	*<li> Case ID:128176.</li>
	*<li> Test Case Name: Check contextual menu of Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG : normal user can Add Project
	*/
	@Test (groups = "pending")
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
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check contextual menu of Projects
		*Step Description: 
			- Check contextual menu of Projects on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects has a contextual menu :Add Project, Show Hidden Projects*/
		mgProject.goToContMenuProject();
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECTS_ADD);
		
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
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Check contextual menu of Projects
		*Step Description: 
			- Check contextual menu of Projects on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects has a contextual menu :Show Hidden Projects*/ 
		mgProject.goToContMenuProject();
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECTS_ADD);
	
 	}

	/**
	*<li> Case ID:128192.</li>
	*<li> Test Case Name: Check group by of tasks in each project in Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,Badd some tasks to project A,B</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-135
	*/
	@Test (groups ="pending")
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
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project1,"", false);
		info("add 2 tasks in to project1");
		mgTask.addTask(project1, task1);
		mgTask.addTask(project1, task2);
		
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project2,"", false);
		info("add 2 tasks in to project2");
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
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		click(mgTask.ELEMENT_GROUPBY_ICON);
		waitForAndGetElement(mgTask.ELEMENT_GROUPBY_ITEM.replace("$item",none));
		waitForAndGetElement(mgTask.ELEMENT_GROUPBY_ITEM.replace("$item",duedate));
		waitForAndGetElement(mgTask.ELEMENT_GROUPBY_ITEM.replace("$item",label));
		waitForAndGetElement(mgTask.ELEMENT_GROUPBY_ITEM.replace("$item", assignee));
		waitForAndGetElement(mgTask.ELEMENT_GROUPBY_ITEM.replace("$item", status));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project1));
		mgProject.selectOpContMenuGivenProject(project2,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project2, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project2));
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
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check no Board on Projects
		*Step Description: 
			- Click on Projects on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			There is no Board on Projects*/ 
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		waitForElementNotPresent(mgProject.ELEMENT_BOARD_VIEW);
 	}

	/**
	*<li> Case ID:128175.</li>
	*<li> Test Case Name: Check project by default.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-140
	*/
	@Test (groups= "pending")
	public  void test06_CheckProjectByDefault() {
		info("Test 6: Check project by default");
		String tooltip =welcomeMesData.getWelcomeMessage(2); 
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open Projects overview
		*Step Description: 
			- Click on Projects overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Projects overview is opened*/
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		
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
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_NO_PROJECT);
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_IMG);
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", "No Project"));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_TOOLTIP.replace("$message", tooltip));
		
 	}

	/**
	*<li> Case ID:128182.</li>
	*<li> Test Case Name: Check Projects view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckProjectsView() {
		info("Test 7: Check Projects view");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check Projects view
		*Step Description: 
			- Check Projects on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Add New Task function is not available on Projects view. 
			- New Task and the blank field to add new task are hidden.*/ 
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		waitForElementNotPresent(mgProject.ELEMENT_ADD_TASK_BTN);
		waitForElementNotPresent(mgProject.ELEMENT_ADD_TASK_TITLE);
 	}

	/**
	*<li> Case ID:128184.</li>
	*<li> Test Case Name: Check Projects view default setting.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-135
	*/
	@Test (groups="pending")
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
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		
		info("add 3 tasks in to project");
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
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		waitForAndGetElement(mgProject.ELEMENT_SORTBY_ITEM.replace("$item", duedate));
		waitForAndGetElement(mgProject.ELEMENT_GROUPBY_ITEM.replace("$item", none));
 	}

	/**
	*<li> Case ID:128186.</li>
	*<li> Test Case Name: Check sort by of tasks in each project in Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,Badd some tasks to project A,B</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-135
	*/
	@Test (groups="pending")
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
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project1,"", false);
		info("add 2 tasks in to project1");
		mgTask.addTask(project1, task1);
		mgTask.addTask(project1, task2);
		
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project2,"", false);
		info("add 2 tasks in to project2");
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
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Projects"));
		click(mgTask.ELEMENT_SORTBY_ICON);
		waitForAndGetElement(mgTask.ELEMENT_SORTBY_ITEM.replace("$item",duedate));
		waitForAndGetElement(mgTask.ELEMENT_SORTBY_ITEM.replace("$item",title));
		waitForAndGetElement(mgTask.ELEMENT_SORTBY_ITEM.replace("$item",createdDate));
		waitForAndGetElement(mgTask.ELEMENT_SORTBY_ITEM.replace("$item", priority));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project1));
		mgProject.selectOpContMenuGivenProject(project2,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project2, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project2));
 	}

	/**
	*<li> Case ID:128178.</li>
	*<li> Test Case Name: Check welcome messages of first access.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedone personal project and one share project are created</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-168
	*/
	@Test (groups = "pending")
	public  void test10_CheckWelcomeMessagesOfFirstAccess() {
		info("Test 10 Check welcome messages of first access");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mes0 = welcomeMesData.getWelcomeMessage(0);
		String mes1= welcomeMesData.getWelcomeMessage(1);
		String mes2= welcomeMesData.getWelcomeMessage(3);
		String mes3= welcomeMesData.getWelcomeMessage(4);
		String[] users= {DATA_USER2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("login as root");
		magAc.signOut();
		magAc.signIn(USER_ROOT, DATA_PASS);
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check case of viewing Projects
		*Step Description: 
			- Click on Projects on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			It displays: No Project*/
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Projects"));
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", "No Project"));
		
		/*Step number: 3
		*Step Name: Step 3: Check case of viewing personal project
		*Step Description: 
			- Click on personal project on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			- It displays: This your personal project. You can share it for work collaboration.
			- Share it is a link to open Share popup*/
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", mes0));
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", mes1));
		
		/*Step number: 4
		*Step Name: Step 4: Check case of viewing shared project
		*Step Description: 
			- Click on shared project on left pane
			- Check central pane
		*Input Data: 
			
		*Expected Outcome: 
			- It displays: This is $FullName's project. There is no task to do.*/ 
		info("share project to mary");
		mgProject.goToContMenuGivenProject(project);
		mgProject.selectOpContMenuGivenProject(project, optionContMenuGivenProject.Share);
		mgProject.shareProject(users, false);

		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		taskMgHome.goToTasks();
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", mes2));
		waitForAndGetElement(mgProject.ELEMENT_PROJECT_WELCOME_TEXT.replace("$message", mes3));
			
		info("login as root");
		magAc.signOut();
		magAc.signIn(USER_ROOT, DATA_PASS);
		info("open task page");
		taskMgHome.goToTasks();
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
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
		String tooltip = welcomeMesData.getWelcomeMessage(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add new project
		*Step Description: 
			- Click on Projects on the left pane
			- Create new project
		*Input Data: 
			
		*Expected Outcome: 
			- New project is created
			- The message Click here to create your first project. and No Project screen are disappeared when there is a project added.*/ 
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Projects"));
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_TOOLTIP.replace("$message", tooltip));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
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
		String tooltip = welcomeMesData.getWelcomeMessage(5);
		
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Add task to one project
		*Step Description: 
			- Click on one project on the left pane
			- Create a task
		*Input Data: 
			
		*Expected Outcome: 
			- Task is added to project
			- The message Let's create your first task. and welcome screen are disappeared when there is a task added.*/ 
		info("add task into project");
		mgTask.addTask(project, task);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_TOOLTIP.replace("$mes", tooltip));
		waitForElementNotPresent(mgProject.ELEMENT_PROJECT_WELCOME_IMG);
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
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
		info("open task page");
		taskMgHome.goToTasks();
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", space));
		
		info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);

 	}}