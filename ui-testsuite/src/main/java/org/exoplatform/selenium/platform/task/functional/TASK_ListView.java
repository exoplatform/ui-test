package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionNoNewTask;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDateLV;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionGroupBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionSortBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;

import org.testng.annotations.*;


	public class TASK_ListView extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131038.</li>
	*<li> Test Case Name: Check clock icon at the right end of a task row.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	* NOT FULL IMPLEMENT
	*/
	@Test
	public  void test01_CheckClockIconAtTheRightEndOfATaskRow() {
		info("Test 1: Check clock icon at the right end of a task row");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- Add new task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Set due date for created task
		*Step Description: 
			- Set due date for created task
		*Input Data: 
			
		*Expected Outcome: 
			due date is setted*/
		mgTask.setDueDate(task1,getDate(0,"dd MMM yyyy"),getDate(0,"dd"),0);
		
		/*Step number: 4
		*Step Name: Step 4: Check clock icon at the right end of a task row
		*Step Description: 
			- On central pane, hover on due date to snooze a task for later
		*Input Data: 
			
		*Expected Outcome: 
			A clock icon at the right end of a task row allows to snooze a task for later
			- Later Today (+5 hours)
			- Tomorrow Morning (9am the next day)
			- Tomorrow Afternoon (1pm the next day)
			- Next Week (next monday 9am)*/ 
		mouseOver(mgTask.ELEMENT_TASK_TITLE.replace("$task", task1), false);
		waitForAndGetElement(mgTask.ELEMENT_TASK_CLOCK_ICON.replace("$task",task1));
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:131034.</li>
	*<li> Test Case Name: Check display of due date in task row.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131010.</li>
	*<li> Test Case Name: Check display of tasks in list view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome task are created in Incoming</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131033.</li>
	*<li> Test Case Name: Check each row is fronted by a checkbox.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_06_CheckDisplayOfDueDateInTaskRow() {
		info("Test 2: Check display of due date in task row");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task
		*Step Description: 
			- Add new task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of due date in task row
		*Step Description: 
			- Set due date for created task
		*Input Data: 
			
		*Expected Outcome: 
			When a task has a due date, it is written at the right end of the task row in central pane*/
		/*Step number: 2
		*Step Name: Step 2: Check each row is fronted by a checkbox
		*Step Description: 
			-Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- In list view, each row of task is fronted by a checkbox. 
			- When it is clicked, the status of the task is set to Completed. The task is shortly striked through, then it disappears in a fade effect*/
		mgTask.setDueDate(task1,getDate(0,"dd MMM yyyy"),getDate(0,"dd"),0);
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.checkDisplayOfListView(task1);
		mgTask.completeTask(task1);
	
 	}

	/**
	*<li> Case ID:131035.</li>
	*<li> Test Case Name: Check Due Date format.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDueDateFormat() {
		info("Test 4: Check Due Date format");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- Add new task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check Due Date format
		*Step Description: 
			- Set due date for created task: a day in next month for ex
		*Input Data: 
			
		*Expected Outcome: 
			on central pane, Due Date format is MMM DD (e.g Apr 19)*/ 
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:131048.</li>
	*<li> Test Case Name: Check Due Date format in Group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks have due date: no due date,overdue, today, tomorrow, next month....</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-157
	*/
	@Test (groups="pending")
	public  void test05_CheckDueDateFormatInGroupBy() {
		info("Test 5: Check Due Date format in Group by");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check Due Date format
		*Step Description: 
			- Click on Projects menu and group by Due Date
		*Input Data: 
			
		*Expected Outcome: 
			Due Date format in group rows is the same as in rows 
			- Due Date format is MMM DD (e.g Apr 19)
			- due date is displayed respectively as Today, Tomorrow or Yesterday
			-When the Due date is not in the same year,the date format to use is MMM DD, YYY (e.g Dec 31, 2014)*/ 

 	}

	/**
	*<li> Case ID:131043.</li>
	*<li> Test Case Name: Check group by Assignee.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks are assigned to some users</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131045.</li>
	*<li> Test Case Name: Check when clicking on the user full name.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks are assigned to some users</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-216
	*/
	@Test (groups="pending") 
	public  void test07_22_CheckGroupByAssignee() {
		info("Test 7: Check group by Assignee");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.editTaskAssignee(task2, DATA_USER2);
		
		/*Step number: 2
		*Step Name: Step 2: Check group by Assignee
		*Step Description: 
			- Click on Projects menu and group by Assignee
		*Input Data: 
			
		*Expected Outcome: 
			- Group by Assignee groups tasks by Assignee. 
			- Assignee groups are sorted by user full name alphabetically.*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Assignee);
		mgTask.checkGroupBy(true, DATA_NAME_USER1, 1);
		mgTask.checkGroupBy(true, DATA_NAME_USER2, 1);
		mgTask.checkSortOfGroupBy(DATA_NAME_USER1, 1);
		mgTask.checkSortOfGroupBy(DATA_NAME_USER2, 2);
		
		/*Step number: 3
		*Step Name: Step 3: Check when clicking on the user full name
		*Step Description: 
			- Click on one user group
		*Input Data: 
			
		*Expected Outcome: 
			- Page is redirected to user profile page*/ 
		mgTask.checkFullnameLink(DATA_NAME_USER2);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131046.</li>
	*<li> Test Case Name: Check group by Due date.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks have due date: overdue, today, tomorrow, next month....</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckGroupByDueDate() {
		info("Test 8: Check group by Due date");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Check group by Due date
		*Step Description: 
			- Click on Projects menu and group by Due Date
		*Input Data: 
			
		*Expected Outcome: 
			- Group by Due date allows to group tasks according to their Due Date. 
			- Due Date groups are sorted by date (older first)*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Due_Date);
		mgTask.checkGroupBy(true, "Today", 1);
		mgTask.checkGroupBy(true, "Tomorrow", 1);
		mgTask.checkSortOfGroupBy("Today", 1);
		mgTask.checkSortOfGroupBy("Tomorrow", 2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131014.</li>
	*<li> Test Case Name: Check group by of list view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test09_CheckGroupByOfListView() {
		info("Test 9: Check group by of list view");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String none = groupByData.getGroupBy(0);
		String duedate = groupByData.getGroupBy(1);
		String label = groupByData.getGroupBy(2);
		String project = groupByData.getGroupBy(5);
		String status = groupByData.getGroupBy(3);
		String assignee = groupByData.getGroupBy(4);
		String[] groups={none,duedate,label,project,status,assignee};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgTask.addTaskDirectly(task1,true);
		mgTask.addTaskDirectly(task2,true);
		mgTask.addTaskDirectly(task3,true);
		
		/*Step number: 2
		*Step Name: Step 2: Check group by of list view
		*Step Description: 
			- Select List view of Incoming/Today/Tomorrow...
			- Click on Group By
		*Input Data: 
			
		*Expected Outcome: 
			The list view can be grouped by: None, Due Date, Label, Project, Assignee, Status.*/ 
		mgProject.checkGroupByInProjects("Incoming", groups,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}

	/**
	*<li> Case ID:131041.</li>
	*<li> Test Case Name: Check group By Project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-207
	*/
	@Test (groups="pending")
	public  void test10_CheckGroupByProject() {
		info("Test 10 Check group By Project");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check group By Project
		*Step Description: 
			- Click on Projects menu and group by Project
		*Input Data: 
			
		*Expected Outcome: 
			- Group By Project groups tasks by Project. 
			- Project groups are sorted by project title alphabetically.*/ 

 	}

	/**
	*<li> Case ID:131042.</li>
	*<li> Test Case Name: Check group By Project when 2 projects have the same title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are created, 2 projects have the same name among themsome tasks are added into projects</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131040.</li>
	*<li> Test Case Name: Check group By Status when 2 statuses have the same name.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A has workflow A, project B has workflow B, workflow A and B have one status in commonsome tasks are created in project A, project B and added status</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-207
	*/
	@Test (groups="pending")
	public  void test11_13_CheckGroupByProjectWhen2ProjectsHaveTheSameTitle() {
		info("Test 11 Check group By Project when 2 projects have the same title");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check group By Project when 2 projects have the same title
		*Step Description: 
			- Click on Projects menu and group by Project
		*Input Data: 
			
		*Expected Outcome: 
			- Group By Project groups tasks by Project. 
			- Project groups are sorted by project title alphabetically.
			- 2 projects have the same name should be displayed as separate groups.*/ 
		/*Step number: 2
		*Step Name: Step 2: Check group By Status when 2 statuses have the same name
		*Step Description: 
			- Click on Projects menu and group by Status
		*Input Data: 
			
		*Expected Outcome: 
			- Group By Status groups tasks by Status. 
			- Status are sorted by the order of the status in the workflow
			- 2 status have the same name should be displayed as the same*/ 

 	}

	/**
	*<li> Case ID:131039.</li>
	*<li> Test Case Name: Check group By Status.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome project is createdsome tasks are created in created projects and added status</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test12_CheckGroupByStatus() {
		info("Test 12 Check group By Status");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String todo = flowData.getFlowByArrayTypeRandom(1);
		String inprogress = flowData.getFlowByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskStatus(task2, inprogress);
		
		/*Step number: 2
		*Step Name: Step 2: Check group By Status
		*Step Description: 
			- Click on Projects menu and group by Status
		*Input Data: 
			
		*Expected Outcome: 
			Group By Status groups tasks by Status. Status are sorted by the order of the status in the workflow*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Status);
		mgTask.checkGroupBy(true, todo, 1);
		mgTask.checkGroupBy(true, inprogress, 1);
		mgTask.checkSortOfGroupBy(todo, 1);
		mgTask.checkSortOfGroupBy(inprogress, 2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131050.</li>
	*<li> Test Case Name: Check sort by Created Date.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks have different created date</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test14_CheckSortByCreatedDate() {
		info("Test 14 Check sort by Created Date");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] tasks= {task1,task2,task3};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.addTask(prj1, task3);
		
		/*Step number: 2
		*Step Name: Step 2: Check sort by Created Date
		*Step Description: 
			- Click on Projects menu and group by Created Date
		*Input Data: 
			
		*Expected Outcome: 
			Sort by Created Date sorts tasks by there created date follow order from the current to the past.*/ 
		mgTask.selectOptSortBy(optionSortBy.Created_Date);
		mgTask.checkSortByCreatedDate(tasks);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131015.</li>
	*<li> Test Case Name: Check sort by of list view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* NOT FULL IMPLEMENT YET
	*/
	@Test
	public  void test15_CheckSortByOfListView() {
		info("Test 15 Check sort by of list view");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String title = sortByData.getSortBy(1);
		String createdDate = sortByData.getSortBy(2);
		String duedate = sortByData.getSortBy(0);
		String priority = sortByData.getSortBy(3);
		//String rank = sortByData.getSortBy(4);
		String[] sorts={title,priority,createdDate,duedate};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgTask.addTaskDirectly(task1,true);
		mgTask.addTaskDirectly(task2,true);
		
		/*Step number: 2
		*Step Name: Step 2: Check sort by of list view
		*Step Description: 
			- Select List view of Incoming/Today...
			- Click on Sort By
		*Input Data: 
			
		*Expected Outcome: 
			The list view can be sorted by: Title, Created Date, Due Date, Priority, Rank.*/ 
		mgProject.checkSortByInProjects("Incoming", sorts,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:131049.</li>
	*<li> Test Case Name: Check sort by Priority.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks have priority:High, Medium, Low, no priority</li>
	* BUG: https://jira.exoplatform.org/browse/TA-135
	*/
	@Test (groups="pending")
	public  void test16_CheckSortByPriority() {
		info("Test 16 Check sort by Priority");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String high = priorityData.getPriorityByArrayTypeRandom(2);
		String normal = priorityData.getPriorityByArrayTypeRandom(3);
		String low = priorityData.getPriorityByArrayTypeRandom(4);
		String none = priorityData.getPriorityByArrayTypeRandom(1);
		String[] tasks={task4,task3,task2,task1};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.addTask(prj1, task3);
		mgTask.addTask(prj1, task4);
		mgTask.editTaskPriority(task1, high);
		mgTask.editTaskPriority(task2, normal);
		mgTask.editTaskPriority(task3, low);
		mgTask.editTaskPriority(task4, none);
		
		/*Step number: 2
		*Step Name: Step 2: Check sort by Priority
		*Step Description: 
			- Click on Projects menu and group by Priority
		*Input Data: 
			
		*Expected Outcome: 
			Sort by Priority sorts tasks by their priority in the following order : High, Medium, Low, no priority*/ 
		mgTask.selectOptSortBy(optionSortBy.Priority);
		mgTask.checkSortByCreatedDate(tasks);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131047.</li>
	*<li> Test Case Name: Check tasks without a due date are set in No Due Date group.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks have due date: no due date,overdue, today, tomorrow, next month....</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckTasksWithoutADueDateAreSetInNoDueDateGroup() {
		info("Test 17 Check tasks without a due date are set in No Due Date group");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Check tasks without a due date are set in No Due Date group
		*Step Description: 
			- Click on Projects menu and group by Due Date
		*Input Data: 
			
		*Expected Outcome: 
			- Group by Due date allows to group tasks according to their Due Date. 
			- Due Date groups are sorted by date (older first)
			- Tasks without a due date are set in No Due Date group.*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Due_Date);
		mgTask.checkGroupBy(true, "No Due Date", 1);
		mgTask.checkGroupBy(true, "Tomorrow", 1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131013.</li>
	*<li> Test Case Name: Check the ! symbol in blue.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created which has due date is Today</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131012.</li>
	*<li> Test Case Name: Check the ! symbol in red.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created but overdue</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_19_CheckTheSymbolInBlue() {
		info("Test 18 Check the ! symbol in blue");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.setDueDate(task2, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgProject.goToProjects();
		
		/*Step number: 2
		*Step Name: Step 2: Check the ! symbol in blue
		*Step Description: 
			- Select List view in Today menu
		*Input Data: 
			
		*Expected Outcome: 
			The ! symbol in blue before task A to indicate the tasks that need to be done today.*/ 
		mgTask.checkTaskSymbol(task1, true);
		
		/*Step number: 2
		*Step Name: Step 2: Check the ! symbol in red
		*Step Description: 
			- Select List view in Overdue
		*Input Data: 
			
		*Expected Outcome: 
			The ! symbol in red before task A to indicate overdue task that has not been completed on time.*/
		mgTask.checkTaskSymbol(task2, false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131011.</li>
	*<li> Test Case Name: Check top of list view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are created in Incoming</li>
	*<li> Post-Condition: </li>
	* NOT FULL IMPLEMENT YET
	*/
	@Test 
	public  void test20_CheckTopOfListView() {
		info("Test 20 Check top of list view");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check top of list view
		*Step Description: 
			- Select List view of Tasks/Labels/Projects/Overdue ...
		*Input Data: 
			
		*Expected Outcome: 
			On top of the list view
			- New Task button 
			- first row is empty and used fast input of a task except: Labels, Projectsand Overdue view.*/ 
		//mgProject.checkTopOfListView("Labels",false,false);
		mgProject.checkTopOfListView("Projects",false,optionNoNewTask.Projects);
		mgProject.checkTopOfListView("Overdue",false,optionNoNewTask.Overdue);
		mgProject.checkTopOfListView("Incoming",true,optionNoNewTask.Normal);
 	}

	/**
	*<li> Case ID:131044.</li>
	*<li> Test Case Name: Check Unassigned tasks are put in Unassigned group.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome projects are createdsome tasks are added into projects, tasks are assigned to some users, some tasks are not assigned</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-216
	*/
	@Test (groups="pending")
	public  void test21_CheckUnassignedTasksArePutInUnassignedGroup() {
		info("Test 21 Check Unassigned tasks are put in Unassigned group");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		
		/*Step number: 2
		*Step Name: Step 2: Check Unassigned tasks are put in Unassigned group
		*Step Description: 
			- Click on Projects menu and group by Assignee
		*Input Data: 
			
		*Expected Outcome: 
			- Group by Assignee groups tasks by Assignee. 
			- Assignee groups are sorted by user full name alphabetically.
			- Unassigned tasks are put in Unassigned group.*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Assignee);
		mgTask.checkGroupBy(true, DATA_NAME_USER1,1);
		mgTask.checkGroupBy(true, "Unassigned", 1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);

 	}

	/**
	*<li> Case ID:131037.</li>
	*<li> Test Case Name: Check when the Due date is not in the same year.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-157
	*/
	@Test (groups="pending")
	public  void test23_CheckWhenTheDueDateIsNotInTheSameYear() {
		info("Test 23 Check when the Due date is not in the same year");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- Add new task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check when the Due date is not in the same year
		*Step Description: 
			- Set due date for created task: in same year
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, the date format to use is MMM DD, YYY (e.g Dec 31, 2014)*/ 
		mgTask.setDueDate(task1, getDateOfNextYear("dd MMM yyyy", 1), getDate(0, "dd"), 12);
		mgProject.openProject("Incoming");
		mgTask.checkDueDateInListView(optDueDateLV.Year, task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:131036.</li>
	*<li> Test Case Name: Check when the Due date is today, tomorrow or yesterday.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-157
	*/
	@Test (groups= "pending")
	public  void test24_CheckWhenTheDueDateIsTodayTomorrowOrYesterday() {
		info("Test 24 Check when the Due date is today, tomorrow or yesterday");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- Add new task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		mgTask.addTaskDirectly(task2,true);
		mgTask.addTaskDirectly(task3,true);
		mgTask.setDueDate(task1, getDate(0,"dd MMM yyyy"), getDate(0,"dd"),0);
		mgTask.setDueDate(task2, getDate(1,"dd MMM yyyy"), getDate(1,"dd"),0);
		mgTask.setDueDate(task3, getDate(-1,"dd MMM yyyy"), getDate(-1,"dd"),0);
		
		/*Step number: 3
		*Step Name: Step 3: Check when the Due date is today, tomorrow or yesterday
		*Step Description: 
			- Set due date for created task: today,yesterday,tomorrow
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, the due date is displayed respectively as Today, Tomorrow or Yesterday instead of the date format defined in LIST_VIEW_12a*/ 
		mgProject.openProject("Incoming");
		mgTask.checkDueDateInListView(optDueDateLV.Today, task1);
		mgTask.checkDueDateInListView(optDueDateLV.Tomorrow, task2);
		mgTask.checkDueDateInListView(optDueDateLV.Yesterday, task3);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}}