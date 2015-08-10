package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskComments extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130968.</li>
	*<li> Test Case Name: Check comment button is disabled in case of no text input in comment field.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130976.</li>
	*<li> Test Case Name: Check comment button is enabled when there is text input in the comment field..</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130969.</li>
	*<li> Test Case Name: Check comment text area can be expanded.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_03_CheckCommentButtonIsDisabledInCaseOfNoTextInputInCommentField() {
		info("Test 1: Check comment button is disabled in case of no text input in comment field");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		for(int i=0;i<8;i++){
			comment=comment+txData.getContentByArrayTypeRandom(1);
		}
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
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1);
		mgTask.openTask(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check comment button is disabled in case of no text input in comment field
		*Step Description: 
			- Check no text input in comment field
		*Input Data: 
			
		*Expected Outcome: 
			Comment button is disabled .*/ 
		mgTask.checkCommentButtonOfTaskComment(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check comment text area can be expanded
		*Step Description: 
			- Input a very long comment in task details
		*Input Data: 
			
		*Expected Outcome: 
			The comment text area is expanded flexibly when the comment input is long.*/ 
		mgTask.checkTextAreaOfTaskComment(comment);
		
		/*Step number: 4
		*Step Name: Step 4: Check comment button is enabled when there is text input in the comment field.
		*Step Description: 
			- Input text into comment field
		*Input Data: 
			
		*Expected Outcome: 
			Comment button is enabled*/ 
		mgTask.checkCommentButtonOfTaskComment(task1, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130975.</li>
	*<li> Test Case Name: Check display of comment time.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDisplayOfCommentTime() {
		info("Test 4: Check display of comment time");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String time = welcomeMesData.getWelcomeMessage(20);
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
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of comment time
		*Step Description: 
			- Add a comment in task details
		*Input Data: 
			
		*Expected Outcome: 
			- Comment is added
			- Display of comment time is the same with time on comment in the activity stream.*/ 
		mgTask.addTaskComment(task1, DATA_NAME_USER1, comment);
		mgTask.checkDisplayOfCommentTime(DATA_NAME_USER1, comment, time);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130972.</li>
	*<li> Test Case Name: Check link View all $CommentNumber comments.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130971.</li>
	*<li> Test Case Name: Check only 2 latest comments are shown.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130973.</li>
	*<li> Test Case Name: Check when clicking View all $CommentNumber comments.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-149
	*/
	@Test (groups="pending")
	public  void test05_06_09_CheckLinkViewAllNumberComments() {
		info("Test 5: Check link View all $CommentNumber comments");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1);
		mgTask.addManyTaskComment(task1, DATA_NAME_USER1, comment, 4);
		
		/*Step number: 3
		*Step Name: Step 3: Check link View all $CommentNumber comments
		*Step Description: 
			- Add 4 comments in task details
		*Input Data: 
			
		*Expected Outcome: 
			View all 4 comments link displays*/ 
		/*Step number: 3
		*Step Name: Step 3: Check only 2 latest comments are shown
		*Step Description: 
			- Add 4 comments in task details
		*Input Data: 
			
		*Expected Outcome: 
			Only 2 latest comments are shown. The older are hidden.*/ 
		mgTask.checkViewAllComments(DATA_NAME_USER1, comment, 4);
		
		/*Step number: 4
		*Step Name: Step 4: Check when clicking View all $CommentNumber comments
		*Step Description: 
			- Click on View all 4 comments
		*Input Data: 
			
		*Expected Outcome: 
			- all comments are displayed fully
			- the link is changed into Hide all $CommentNumber comments.*/
		mgTask.checkHideAllComments(DATA_NAME_USER1, comment, 4);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130970.</li>
	*<li> Test Case Name: Check suggestion list when @mention user in comments.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckSuggestionListWhenMentionUserInComments() {
		info("Test 7: Check suggestion list when @mention user in comments");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] users = {DATA_NAME_USER2,DATA_NAME_USER3};
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
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check suggestion list when @mention user in comments
		*Step Description: 
			- Mention one user in comment of task details. Ex: hello @mar
		*Input Data: 
			
		*Expected Outcome: 
			Suggestion list when @mention user in comments.*/ 
		mgTask.addTaskComment(task1, DATA_NAME_USER1, comment);
		mgTask.checkSuggestionListWhenMention("a", users);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130974.</li>
	*<li> Test Case Name: Check trash icon to delete the comment.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is added to project Auser A create comment A, user B is participant of project A, user C is manager of project A</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-150
	*/
	@Test (groups="pending")
	public  void test08_CheckTrashIconToDeleteTheComment() {
		info("Test 8: Check trash icon to delete the comment");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] manager = {DATA_USER2};
		String[] participant = {DATA_USER3};
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTaskComment(task1, DATA_NAME_USER1, comment);
		mgProject.shareProject(prj1, manager, true);
		mgProject.shareProject(prj1, participant, false);
		
		/*Step Number: 1
		*Step Name: Step 1: Login user A/user C
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Login successfully*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgProject.goToProjects();
		mgTask.openTask(task1);
		
		/*Step number: 4
		*Step Name: Step 4: Check trash icon to delete the comment
		*Step Description: 
			- Hovers oncomment A
		*Input Data: 
			
		*Expected Outcome: 
			there is trash icon to delete the comment.*/
		mgTask.checkTrashIconOfComment(DATA_NAME_USER1, comment, true);
		
		/*Step number: 5
		*Step Name: Step 5: Login user B
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Login successfully*/
		info("login as james");
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 6
		*Step Name: Step 6: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 7
		*Step Name: Step 7: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgProject.goToProjects();
		mgTask.openTask(task1);
		
		/*Step number: 8
		*Step Name: Step 8: Check trash icon to delete the comment
		*Step Description: 
			- Hovers on comment A
		*Input Data: 
			
		*Expected Outcome: 
			there is NOT trash icon to delete the comment.*/ 
		mgTask.checkTrashIconOfComment(DATA_NAME_USER1, comment, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1,false);
 	}
}