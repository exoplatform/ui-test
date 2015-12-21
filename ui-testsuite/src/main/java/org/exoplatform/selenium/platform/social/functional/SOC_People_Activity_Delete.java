package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_Activity_Delete extends SOC_TestConfig{

	/**
	 *<li> Case ID:122800.</li>
	 *<li> Test Case Name: Delete your activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_DeleteYourActivity() {
		info("Test 1: Delete your activity");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Add new activities
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
			- Select activity in the left pane
			- Enter some text into text box
			- Click on [Share] button
		 *Expected Outcome: 
			Add an activity successfully:
			- This activity is added into users activities list.User who is in your contact, can view your active on his/her activity list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Delete activity
		 *Input Data: 
			- Select an activity
			- Click on Delete
			- Click OK to confirm deleting
		 *Expected Outcome: 
			The activity is deleted. All comments of activity are deleted too.*/ 
		hpAct.deleteActivity(activity1);
	}
}