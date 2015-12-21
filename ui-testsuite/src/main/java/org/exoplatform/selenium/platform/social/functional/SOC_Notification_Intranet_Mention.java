package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import org.testng.annotations.*;


public class SOC_Notification_Intranet_Mention extends SOC_TestConfig2{

	/**
	 *<li> Case ID:125102.</li>
	 *<li> Test Case Name: Check Mention notifications (in activity message).</li>
	 *<li> Case ID:125105.</li>
	 *<li> Test Case Name: Click the Mention notifications (in activity message).</li>
	 *<li> Pre-Condition: User A has mentioned User B directly in the activity message</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test01_04_CheckMentionNotificationInActivityMessage() throws AWTException{
		info("Test 1: Check Mention notifications (in activity message)");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(username2, activity1);
		
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed in the list*/
		magAc.signIn(username2, password);
		navTool.goToIntranetNotification();
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The notification message is : $AVATAR$USER has mentioned you $ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the notification*/ 
		 arrayUser.add(username2);
		 String status = notiIntranetData.getContentByArrayTypeRandom(7);
		 intraNot.checkAvatarInStatus(arrayUser,true);
		 intraNot.checkStatus(status,username1);
		 intraNot.checkActivityTitleInStatus(activity1, true);
		
		info("Test 4: Click the Mention notifications (in activity message)");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Click the notification
		 *Input Data: 

		 *Expected Outcome: 
			- The user is redirected to the activity viewer with all comment expanded.*/ 
		 intraNot.checkAvatarInStatus(arrayUser,true);
		 intraNot.checkStatus(status,username1);
		 intraNot.checkActivityTitleInStatus(activity1, true);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", activity1).replace("${user}",username2));
	}

	/**
	 *<li> Case ID:125103.</li>
	 *<li> Test Case Name: Check Mention notifications (in comment).</li>
	 *<li> Case ID:125104.</li>
	 *<li> Test Case Name: Click the Mention notifications (in comment).</li>
	 *<li> Pre-Condition: - An wiki activity is generated (create a new page)
	- User A has mentioned User B directly in a comment</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test02_05_CheckMentionNotificationsInComment() throws AWTException {
		info("Test 1: Check Mention notifications (in activity message)");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		String comment=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addCommentWithMentionUser(activity1,username2, comment);
	
		info("Test 2: Check Mention notifications (in comment)");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed in the list*/
		magAc.signIn(username2, password);
		navTool.goToIntranetNotification();
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The notification message is : $AVATAR$USER has mentioned you $ACTIVITY$DATEWhere :
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $ACTIVITY is the name of the wiki page
			- $DATE is the date of the notification*/ 
		 arrayUser.add(username2);
		 String status = notiIntranetData.getContentByArrayTypeRandom(7);
		 intraNot.checkAvatarInStatus(arrayUser,true);
		 intraNot.checkStatus(status,username1);
		 intraNot.checkActivityTitleInStatus(activity1, true);
		
		info("Test 5: Click the Mention notifications (in comment)");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Click the notification
		 *Input Data: 

		 *Expected Outcome: 
			- The user is redirected to the activity viewer with all comment expanded. 
			- The comment where the mention has been done is highlighted*/ 
		 intraNot.goToDetailMentionNotification(username1+" "+username1, true);
		 notiAct.checkCommentExpand(comment,true);

	}

	/**
	 *<li> Case ID:125106.</li>
	 *<li> Test Case Name: Check View All after receiving a Mention notification.</li>
	 *<li> Pre-Condition: User A has mentioned User B</li>
	 *<li> Post-Condition: </li>
	 * @throws AWTException 
	 */
	@Test
	public  void test03_CheckViewAllAfterReceivingAMentionNotification() throws AWTException {
		info("Test 3: Check View All after receiving a Mention notification");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(username2, activity1);

		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed in the list*/
		magAc.signIn(username2, password);
		navTool.goToIntranetNotification();
		String status = notiIntranetData.getContentByArrayTypeRandom(7);
		intraNot.checkStatus(status,username1+" "+username1);
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Go to View All page
		 *Input Data: 

		 *Expected Outcome: 
			- The Mention notification is displayed / available in the page*/ 
		intraNot.goToAllNotification();
		intraNot.checkStatus(status,username1+" "+username1);
	}
}
