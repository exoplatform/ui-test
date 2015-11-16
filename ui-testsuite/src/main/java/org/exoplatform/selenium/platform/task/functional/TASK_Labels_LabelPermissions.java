package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Labels_LabelPermissions extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131564.</li>
	*<li> Test Case Name: Check label permission.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckLabelPermission() {
		info("Test 1: Check label permission");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add labelA
		*Step Description: 
			- Add labelA from menu of Labels> Add Label
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is created*/
		mgLabel.addLabel(label1);
		
		/*Step number: 3
		*Step Name: Step 3: Login as userA
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 4
		*Step Name: Step 4: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 5
		*Step Name: Step 5: Check label permission
		*Step Description: 
			- Check label in Labels
		*Input Data: 
			
		*Expected Outcome: 
			- no label displays
			- userA cannot see labelA*/ 
		mgLabel.checkDisplayOfLabel(false, label1);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgLabel.deleteLabel(label1);
		
 	}}