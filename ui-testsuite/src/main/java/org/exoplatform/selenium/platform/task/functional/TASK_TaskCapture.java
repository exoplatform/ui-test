package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskCapture extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128167.</li>
	*<li> Test Case Name: Check add priority when creating task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAddPriorityWhenCreatingTask() {
		info("Test 1: Check add priority when creating task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String priority = priorityData.getPriorityByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by ! to add priority(Ex: ++task1 !High)
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created 
			- task detail of task1 is opened
			- task1 has priority High*/ 
		hpAct.addActivity("++"+task1+" !"+priority,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDefaultPriority(task1, priority);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128169.</li>
	*<li> Test Case Name: Check assign to when creating task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-233
	*/
	@Test (groups="pending")
	public  void test02_CheckAssignToWhenCreatingTask() {
		info("Test 2: Check assign to when creating task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by @ Ex: ++task1 @mary
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created and assign to mary
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" @"+DATA_USER2,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkTaskAssignee(DATA_NAME_USER2);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128170.</li>
	*<li> Test Case Name: Check coworker when creating task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-233
	*/
	@Test (groups="pending")
	public  void test03_CheckCoworkerWhenCreatingTask() {
		info("Test 3: Check coworker when creating task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by some @, the first is set as assignee, others as coworkers Ex: ++task1 @mary @john @demo
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created, assign to mary, coworker are john and demo
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" @"+DATA_USER2+" @"+DATA_USER1,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkTaskAssignee(DATA_NAME_USER2,DATA_NAME_USER1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128166.</li>
	*<li> Test Case Name: Check create a task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckCreateATask() {
		info("Test 4: Check create a task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by ++ Ex: ++task1
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created 
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDisplayOfTitle(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128171.</li>
	*<li> Test Case Name: Check due date when creating task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-247
	*/
	@Test (groups="pending")
	public  void test05_CheckDueDateWhenCreatingTask() {
		info("Test 5: Check due date when creating task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by ^ Ex: ++task1 ^Tomorrow
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created, due date is Tomorrow
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" ^"+getDate(2,"dd-MMM"),"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDisplayOfDueDate(getDate(2,"dd MMM").toString());
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128168.</li>
	*<li> Test Case Name: Check recognized priorities.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote: ++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckRecognizedPriorities() {
		info("Test 6: Check recognized priorities");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String high = priorityData.getPriorityByArrayTypeRandom(2);
		String medium = priorityData.getPriorityByArrayTypeRandom(3);
		String low = priorityData.getPriorityByArrayTypeRandom(4);
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS with High priority
		*Step Description: 
			- Add a comment follow by ! to add priority(Ex: ++task1 !High)
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created 
			- task detail of task1 is opened
			- task1 has priority High*/
		hpAct.addActivity("++"+task1+" !"+high,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDefaultPriority(task1, high);
		
		/*Step number: 3
		*Step Name: Step 3: Create a task by comment in AS with Medium priority
		*Step Description: 
			- Add a comment follow by ! to add priority(Ex: ++task2 !Medium)
			- Click on task2
		*Input Data: 
			
		*Expected Outcome: 
			- task2 is created 
			- task detail of task2 is opened
			- task2 has priority Medium*/
		hp.goToHomePage();
		hpAct.addActivity("++"+task2+" !"+medium,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task2));
		mgTask.checkDefaultPriority(task2, medium);
		
		/*Step number: 4
		*Step Name: Step 4: Create a task by comment in AS with Low priority
		*Step Description: 
			- Add a comment follow by ! to add priority(Ex: ++task3 !Low)
			- Click on task3
		*Input Data: 
			
		*Expected Outcome: 
			- task3 is created 
			- task detail of task3 is opened
			- task3 has priority Low*/ 
		hp.goToHomePage();
		hpAct.addActivity("++"+task3+" !"+low,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task3));
		mgTask.checkDefaultPriority(task3, low);

		info("delete data");
		mgTask.deleteTask(task3);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task1);

 	}

	/**
	*<li> Case ID:128172.</li>
	*<li> Test Case Name: Check recognized syntax.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-247
	*/
	@Test (groups="pending")
	public  void test07_CheckRecognizedSyntax() {
		info("Test 7: Check recognized syntax");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by ^: TodayTomorrowMonday, Tuesday, Wednesday, Thursday, Friday, Saturday, SundayNext WeekNext Monthdd-mon (e.g : 12-apr)Ex: ++task1 ^Monday
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created with due date Monday
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" ^Today","");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDisplayOfDueDate(getDate(0,"dd MMM").toString());
		
		info("delete data");
		mgTask.deleteTask(task1);

 	}

	/**
	*<li> Case ID:128173.</li>
	*<li> Test Case Name: Check tags when creating task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckTagsWhenCreatingTask() {
		info("Test 8: Check tags when creating task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by # Ex: ++task1 #t1
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created with tag: t1
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" #"+tag,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDisplayOfTag(tag);
		
		info("delete data");
		mgTask.deleteTask(task1);

 	}

	/**
	*<li> Case ID:128174.</li>
	*<li> Test Case Name: Check the tag is auto created when it is detected in a task description.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedNote:++Send Task Management spec for review ^tomorrow !high #urgent #doc"This will create a new task:Title: Send Task Management spec for reviewDescription: emptyDue date: automatically set to tomorrowPriority: HighLabel: urgent, doc</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-234
	*/
	@Test (groups="pending")
	public  void test09_CheckTheTagIsAutoCreatedWhenItIsDetectedInATaskDescription() {
		info("Test 9: Check the tag is auto created when it is detected in a task description");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] tags={tag1,tag2};
		/*Step Number: 1
		*Step Name: Step 1: Login to PLF
		*Step Description: 
			Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Create a task by comment in AS
		*Step Description: 
			- Add a comment follow by # and the tag does not existEx: ++task1 #t1#t2
			- Click on task1
		*Input Data: 
			
		*Expected Outcome: 
			- task1 is created with tag: t1,t2
			- tag t1,t2 are created
			- task detail of task1 is opened*/ 
		hpAct.addActivity("++"+task1+" #"+tag1+" #"+tag2,"");
		click(mgTask.ELEMENT_AS_TASK_LINK.replace("$task", task1));
		mgTask.checkDisplayOfTag(tags);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}