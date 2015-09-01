package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;

public class SOC_Notification_Intranet_View_All extends SOC_TestConfig_3{

	/**
	 * @author tult
	 * date 20/04/2015
	 */
	@BeforeMethod
	public void setUpBeforeMethod(){
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
	}
	
	
	/**
	 *<li> Case ID:123026.</li>
	 *<li> Test Case Name: Check the work of links on View All page.</li>
	 *<li> Case ID:123005.</li>
	 *<li> Test Case Name: Check UI of the View All page.</li>
	 * @throws AWTException 
	 */
	@Test
	public void test01_02_CheckTheWorkOfLinksOnViewAllPage_ChecUIOfTheViewAllPage() throws AWTException{
		//set Data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
	
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		String space1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		/*Precondition:
		 	The user has following notifications 
				- New User
				- Space Invitation
				- Space Join Request
				- Like
				- Comment
				- Connection request
				- Post on My stream
				- Post on My Space
				- Connect	ion request (read)
				- Post in My stream (read)*/
		info("Create user1 and enable new user and like notifications for user1");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("sign in user1 and enable new user and like notifications");
		magAc.signIn(username1, password1);
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		
		info("Create notifications for user 1");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		addUserPage.addUser(username4, password4, email4, username4, username4);
		
		info("Sign in as user 1 and connect to user 2");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("Add activity");
		hp.goToHomePage();
		hpAct.addActivity(activity1,"");
		hpAct.checkActivity(activity1);
		
		info("Add a new space and ivite user 2");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space1, space1);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");
		
		info("User 2 comments to user1's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		hp.goToHomePage();
		hpAct.addCommentUsingJavascript(activity1, comment);
		
		info("user 2 likes user1's activity");
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		
		info("user2 mentions user1 in activity stream");
		hp.goToHomePage();
		hpAct.mentionUserActivity(username1, activity4);
		hpAct.checkActivity(activity4);
		
		info("user 2 posts activity in user1's activity stream");
		hp.goToConnections();
		connMag.goToUserByUserName(username1);
		navTool.goToMyActivities();
		hpAct.addActivity(activity2,"");
		hpAct.checkActivity(activity2);
		
		info("User 2 posts activity in activity stream of user1's space");
		hp.goToAllSpace();
		spaMg.acceptAInvitation(space1);
		
		info("user posted an activity in space");
		hpAct.addActivity(activity3,"");
		hpAct.checkActivity(activity3);
		
		info("Sign in as user 3 and create a space, invite user1 to join space and connect to user1");
		magAc.signIn(username3, password3);
		info("User 3 create a space");
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space2, space2);
		info("User3 invites user1 to join space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username1,false,"");
		info("user3 connects to user 1");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("Sign in as user 4 and request to join user1's space");
		magAc.signIn(username4, password4);
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Open View All page
		 *Step Description: 
			- Login
			- Click the notifications icon in the top navigation
			- Click View All button
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the View All page*/
		magAc.signIn(username1, password1);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		/*Step Number: 2
		 *Step Name: Step 2: Check Connection Request notification
		 *Step Description: 
			 - Click on the Connection Request notification
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirect to the profile of User A*/
		waitForAndGetElement(intraNot.ELEMENT_CONNECT_NOTIFICATION.replace("${fullName}", username3));
		click(intraNot.ELEMENT_CONNECT_NOTIFICATION.replace("${fullName}", username3));
		Utils.pause(1000);
		info("Verify that user was redidected to user A's profile");
		waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", username3));
		Utils.pause(1000);
		/*Step Number: 3
		 *Step Name: Step 3: Check New User notification
		 *Step Description: 
			 - Click the New User notification
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the profile of User B*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailNewUserJoinIntranet(username2, true);
		//intraNot.goToUserProfileFromIntranetNotificationWithNewUserJoined(username2, "1", true);
		
		/*Step Number: 4
		 *Step Name: Step 4: Check Space Invitation notification
		 *Step Description: 
			 - Click the Space Invitation notification
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the home of space 1*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		waitForAndGetElement(intraNot.ELEMENT_SPACE_INVITATION.replace("${space}", space2));
		click(intraNot.ELEMENT_SPACE_INVITATION.replace("${space}", space2));
		Utils.pause(1000);
		info("Verify user is not redirected to home page of space");
		waitForTextPresent(intraNot.ELEMENT_MSG_SPACE_INVITATION_CLICK_ON_NOTIFICATION.replace("${space}", space2));
		Utils.pause(1000);
		/*Step Number: 5
		 *Step Name: Step 5: Check Space Join Request notification
		 *Step Description: 
			 - Click on the Space Join Request notification
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the home of space 1*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		waitForAndGetElement(intraNot.ELEMENT_REQUEST_JOIN_SPACE_NO_TIME.replace("${userName}", username4).replace("${space}", space1));
		Utils.pause(1000);
		click(intraNot.ELEMENT_REQUEST_JOIN_SPACE_NO_TIME.replace("${userName}", username4).replace("${space}", space1));
		info("Verify user is redirected to home page of space");
		waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", space1));
		Utils.pause(1000);
		/*Step Number: 6
		 *Step Name: Step 6: Check Comment notification
		 *Step Description: 
			 - Click on the Comment notification
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailCommentNotification(activity1,false);
		notAct.checkCommentInActivityViewer(comment, "", true);
		
		/*Step Number: 7
		 *Step Name: Step 7: Check Like notification
		 *Step Description: 
			 - Click on the Like notification
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded..*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailLikeNotification(username2, false);
		notAct.checkLikeInActivityViewer("1");
		
		/*Step Number: 8
		 *Step Name: Step 8: Check Mention notification
		 *Step Description: 
			 - Click on the Mention notification
		 *Input Data: 
		 *Expected Outcome: 
			- The user is redirected to the activity in the activity viewer with all comments expanded*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailMentionNotification(username2, false);
		notAct.checkMentionInActivityViewer(activity4);
		
		/*Step Number: 9
		 *Step Name: Step 9: Check Post on My Space notification
		 *Step Description: 
			 - Click on the Post on My Space notification
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed  in the activity viewer with all comments expanded*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailPostInSpace(space1, false);
		notAct.checkMentionInActivityViewer(activity3);
		
		/*Step Number: 10
		 *Step Name: Step 10: Check Post on My Stream notification
		 *Step Description: 
			 - Click on the Post on My Stream notification
		 *Input Data: 
		 *Expected Outcome: 
			- The activity is displayed  in the activity viewer with all comments expanded*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToDetailPostInMyActivity(username2, false);
		notAct.checkMentionInActivityViewer(activity2);
		
		/*Step Number: 11
		 *Step Name: Step 11: Check Notification Settings link
		 *Step Description: 
			 -Click on the [Notification Settings] link
		 *Input Data: 
		 *Expected Outcome: 
			- The link redirects the user to his settings page*/
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.goToNotificationSettings();
		
		info("Reset Data");
		info("Delete users");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
		userAndGroup.deleteUser(username2);
		userAndGroup.deleteUser(username3);
		userAndGroup.deleteUser(username4);
	}
	
}
