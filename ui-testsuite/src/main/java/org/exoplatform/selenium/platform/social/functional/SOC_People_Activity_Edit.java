package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_Activity_Edit extends SOC_TestConfig{

	/**
	 *<li> Case ID:122776.</li>
	 *<li> Test Case Name: Edit description of share link with unlimited chars.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_EditDescriptionOfShareLinkWithUnlimitedChars() {
		info("Test 1: Edit description of share link with unlimited chars");
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Go to my profile page
		 *Input Data: 
			- Sign in system
			- Select Activities page on User Toolbar portlet in the upper right corner of the screen
		 *Expected Outcome: 
			- User activities page is displayed. It focus on activity list*/
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
			Step 2: Share a link
		 *Input Data: 
			- Select activity
			- Click on Attach icon
			- Enter a not exited URL
			- Hit Enter from keyboard or click on [Attach] button on Share link form
			- Double click on description of shared link. Enter unlimited text into this field and hit Enter
			- Enter chars into text box or not
			- Click on [Share] button
		 *Expected Outcome: 
			A link is shared with new title.*/ 
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
	}
}