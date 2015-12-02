package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


public class SOC_Notification_Like_Activities extends SOC_TestConfig{

	/**
	 *<li> Case ID:125087.</li>
	 *<li> Test Case Name: Check Like Notification (1 like).</li>
	 *<li> Case ID:125091.</li>
	 *<li> Test Case Name: Check Like Notification after reading the notification.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_04CheckLikeNotification1Like() {
		info("Test 1: Check Like Notification (1 like)");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		
		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(activity1, true);
		
		info("Test 4: Check Like Notification after reading the notification");
		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		intraNot.goToDetailLikeNotification(username1, true);
		notActivity.checkLikeInActivityViewer("1");
	}

	/**
	 *<li> Case ID:125088.</li>
	 *<li> Test Case Name: Check Like Notification (2 like).</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A and User C are connected
	- User A has posted an activity
	- User B and User C like the posted activity of User A
	- User B has liked User A activity
	- User C has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckLikeNotification2Like() {
		info("Test 2: Check Like Notification (2 like)");
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = username2 + mailSuffixData.getMailSuffixRandom();

		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add 2 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with 2 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(activity, "");
		hpAct.checkActivity(activity);
		
		info("user1 comments in John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		
		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $LAST_AVATAR$USER_LIST like your activity.$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		users.add(username2);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username2);
		intraNot.checkActivityTitleInStatus(activity, true);

		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		intraNot.goToDetailLikeNotification(username2, true);
		notActivity.checkLikeInActivityViewer("2");
	}

	/**
	 *<li> Case ID:125089.</li>
	 *<li> Test Case Name: Check Like Notification (4 like).</li>
	 *<li> Pre-Condition: - User A is connected with User B, User C, User D and User E
	- User A has posted an activity
	- User B has liked User A activity
	- User C has liked User A activity
	- User D has liked User A activity
	- User E has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckLikeNotification4Like() {
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

		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();

		/*Precondition:
		 	- User A is connected with User B, User C, User D and User E
			- User A has posted an activity
			- User B has liked User A activity
			- User C has liked User A activity
			- User D has liked User A activity
			- User E has liked User A activity
			- The notification "Someone likes one of my activities" is activated in the user settings*/
		info("Add 4 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		addUserPage.addUser(username3, password3, email3, username3, username3);
		addUserPage.addUser(username4, password4, email4, username4, username4);

		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		
		info ("Connect with 4 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		connMag.connectToAUser(username3);
		connMag.connectToAUser(username4);

		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(activity, "");
		hpAct.checkActivity(activity);

		info("user 1 likes John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		info("user 2 likes John's activity");
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		info("user 3 likes John's activity");
		magAc.signIn(username3, password3);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		info("user 4 likes John's activity");
		magAc.signIn(username4, password4);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);
		info("Test 3: Check Like Notification (4 like)");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $LAST_AVATAR$LAST2_USERS and $COUNT more like your activity. $ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User E
			- $LAST2_USERSis User E, User D
			- $COUNT is 2
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		info("Check Like notification in intranet notification");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		users.add(username2);
		users.add(username3);
		users.add(username4);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username4);
		intraNot.checkActivityTitleInStatus(activity, true);

		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		info("Check comment notification in activity Viewer");
		intraNot.goToDetailLikeNotification(username4, true);
		notActivity.checkLikeInActivityViewer("4");

	}

	/**
	 *<li> Case ID:125099.</li>
	 *<li> Test Case Name: Check Like Notification on activity with a content.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity with a content attached
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_CheckLikeNotificationOnActivityWithAContent() {
		info("Test 5: Check Like Notification on activity with a content");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	
		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		navTool.goToSiteExplorer();
		SEHome.goToPath("intranet/documents", "Site Management");
		SEHome.goToAddNewContent();
		info("Create new file document");
		creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		creatDoc.addNewWebContent(name, content);
		creatDoc.saveAndClose();
		Utils.pause(5000);
		
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(name);
		
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		
		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of the content is displayed
			- $DATE is the date of the activity*/
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(name, true);
		
		//intraNot.checkUnreadLikeNotification(username1, name, intraNot.ELEMET_JUST_NOW_STRING);
		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		intraNot.goToDetailLikeNotification(username1, true);
		notActivity.checkLikeInActivityViewer("1");

	}

	/**
	 *<li> Case ID:125098.</li>
	 *<li> Test Case Name: Check Like Notification on activity with a link.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity with a link
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_CheckLikeNotificationOnActivityWithALink() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToHomePage();
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = lnkData.getLinkByArrayTypeRandom(1);
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
		String title=waitForAndGetElement(hpAct.ELEMENT_TITLE_BOX.replace("${title}",textDes)).getText();
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(textDes);
		
		info("Test 6: Check Like Notification on activity with a link");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the link is displayed
			- $DATE is the date of the activity*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(title, true);
		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		intraNot.goToDetailLikeNotification(username1, true);
		notActivity.checkLikeInActivityViewer("1");

	}

	/**
	 *<li> Case ID:125101.</li>
	 *<li> Test Case Name: Check Like Notification on activity with a wiki page.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity with a wiki page
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_CheckLikeNotificationOnActivityWithAWikiPage() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePageWithAutoSaveStatus(name,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",name));

		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(name);
		info("Test 7: Check Like Notification on activity with a wiki page");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of the wiki page
			- $DATE is the date of the activity*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(name, true);

		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/
		intraNot.goToDetailLikeNotification(username1, true);
		notActivity.checkLikeInActivityViewer("1");
	}

	/**
	 *<li> Case ID:125100.</li>
	 *<li> Test Case Name: Check Like Notification on activity with an event.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity with an event
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 *PENDING: Do not have activity for my event/my task
	 */
	@Test(groups="pending")
	public  void test08_CheckLikeNotificationOnActivityWithAnEvent() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePageWithAutoSaveStatus(name,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",name));

		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(name);
		
		info("Test 8: Check Like Notification on activity with an event");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: Step 2 : Check notification message
		 *Step Description: 
			- Check the notification message
		 *Input Data: 

		 *Expected Outcome: 
			The activity message is : $AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of the event
			- $DATE is the date of the activity*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(name, true);

		/*Step number: 3
		 *Step Name: Step 3 : Read the notification
		 *Step Description: 
			- Click the notification area
		 *Input Data: 

		 *Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		intraNot.goToDetailLikeNotification(username1, true);
		notActivity.checkLikeInActivityViewer("1");

	}

	/**
	 *<li> Case ID:125090.</li>
	 *<li> Test Case Name: Check Like Notification when a new like is pushed.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_CheckLikeNotificationWhenANewLikeIsPushed() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		
		info("Test 9: Check Like Notification when a new like is pushed");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(activity1, true);
		
		/*Step number: 2
		 *Step Name: Step 2 : push a new notification
		 *Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		 *Input Data: 

		 *Expected Outcome: 
			- The Connect Request is displayed in the notification list of User A*/
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		/*Step number: 3
		 *Step Name: Step 3 : Accept connection request
		 *Step Description: 
			- With User A, [Accept] the Connection Request
		 *Input Data: 

		 *Expected Outcome: 
			- User A and User C are connected*/
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);

		/*Step number: 4
		 *Step Name: Step 4 : Push a new like notification
		 *Step Description: 
			- With User C, like the activity of User A
			- Check the notifications list
		 *Input Data: 

		 *Expected Outcome: 
			- The Like notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		
		/*Step number: 5
		 *Step Name: Step 5 : check the message of the Like notification
		 *Step Description: 
			- Check notification message
		 *Input Data: 

		 *Expected Outcome: 
			- The notification message is : LAST_AVATAR$USER_LIST like your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LIST is User B, User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the last notification of User C*/ 
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		ArrayList<String> users1 = new ArrayList<String>();
		users1.add(username1);
		users1.add(username2);
		String status1 = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users1,true);
		intraNot.checkStatus(status1, username2);
		intraNot.checkActivityTitleInStatus(activity1, true);
	}

	/**
	 *<li> Case ID:125092.</li>
	 *<li> Test Case Name: Check View All after receiving a Like notification (1 like).</li>
	 *<li> Case ID:125096.</li>
	 *<li> Test Case Name: Check View All page after reading a Like notification.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has liked User A activity</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_11_CheckViewAllAfterReceivingALikeNotification1Like() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		info("Test 10 Check View All after receiving a Like notification (1 like)");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		
		/*Step number: 2
		 *Step Name: Step 2 : Check View All page
		 *Step Description: 
			- Go to View All page
		 *Input Data: 

		 *Expected Outcome: 
			The notification is available/displayed in the View All page :$AVATAR$USER likes your activity.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/ 
		intraNot.goToAllNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,false);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(activity1, false);

		info("Test 11 Check View All page after reading a Like notification");
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(users,true);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(activity1, true);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkAvatarInStatus(users,false);
		intraNot.checkStatus(status, username1);
		intraNot.checkActivityTitleInStatus(activity1,false);
	}

	/**
	 *<li> Case ID:125094.</li>
	 *<li> Test Case Name: Check View All page after receiving a Comment notification (4 comments).</li>
	 *<li> Pre-Condition: - User A is connected with User B, User C and User D
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity
	- User D has commented on User A activity
	- User B has commented again on User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 *PENDING: duplicate case with comment activity part
	 */
	@Test(groups="pending")
	public  void test12_CheckViewAllPageAfterReceivingACommentNotification4Comments() {
		info("Test 12 Check View All page after receiving a Comment notification (4 comments)");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A comment notification is displayed in the list*/

		/*Step number: 2
		 *Step Name: Step 2 : Check View All page
		 *Step Description: 
			- Go to View All page
		 *Input Data: 

		 *Expected Outcome: 
			The Intranet Notifications is displayed / available in the View All page : $LAST_AVATAR$USER_LIST and $COUNT more have commented on your activity $ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User B
			- $LAST2_USERSis User D, User B
			- $COUNT is 2
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/ 

	}

	/**
	 *<li> Case ID:125093.</li>
	 *<li> Test Case Name: Check View All page after receiving a Like notification (2 like).</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A and User C are connected
	- User A has posted an activity
	- User B has liked User A activity
	- User C has liked User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_CheckViewAllPageAfterReceivingALikeNotification2Like() {
		info("Test 13 Check View All page after receiving a Like notification (2 like)");
		//Setup data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = username2;
		String email2 = username2 + mailSuffixData.getMailSuffixRandom();

		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add 2 users");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with 2 users");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		connMag.connectToAUser(username2);
		
		info("Add a activity");
		hp.goToHomePage();
		hpAct.addActivity(activity, "");
		hpAct.checkActivity(activity);
		
		info("user1 comments in John's activity");
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity);

		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();

		/*Step number: 2
		 *Step Name: Step 2 : Check View All page
		 *Step Description: 
			- Go to View All page
		 *Input Data: 

		 *Expected Outcome: 
			The Comment notification is displayed / available in the page : $LAST_AVATAR$USER_LIST like your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/ 
		intraNot.goToAllNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		users.add(username2);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,false);
		intraNot.checkStatus(status, username2);
		intraNot.checkActivityTitleInStatus(activity,false);
	}

	/**
	 *<li> Case ID:125095.</li>
	 *<li> Test Case Name: Check View All page right after a new Like is pushed.</li>
	 *<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has liked on User A activity
	- The notification "Someone likes one of my activities" is activated in the user settings</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test14_CheckViewAllPageRightAfterANewLikeIsPushed() {
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		/*Create data test*/
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		addUserPage.addUser(username2, password2, email2, username2, username2);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);
		Utils.pause(3000);
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		hp.goToHomePage();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		
		magAc.signIn(username1, password1);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		hp.goToHomePage();
		hpAct.likeActivity(activity1);
		info("Test 14 Check View All page right after a new Like is pushed");
		/*Step Number: 1
		 *Step Name: Step 1 : Check notifications list
		 *Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		 *Input Data: 

		 *Expected Outcome: 
			- A Like notification is displayed in the list*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();

		/*Step number: 2
		 *Step Name: Step 2 : Push a new notification
		 *Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		 *Input Data: 

		 *Expected Outcome: 
			- The Connection Request is displayed in the notiication list of User A*/
		info ("Connect with user");
		hp.goToConnections();
		connMag.connectToAUser(username2);

		/*Step number: 3
		 *Step Name: Step 3 : Accept the notification request
		 *Step Description: 
			- With User A, [Accept] the Connection Request
		 *Input Data: 

		 *Expected Outcome: 
			- User A and User C are connected*/
		magAc.signIn(username2, password2);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);

		/*Step number: 4
		 *Step Name: Step 4 : Add a new like to the activity
		 *Step Description: 
			- With User C, like the activity of User A
			- Check the notification list
		 *Input Data: 

		 *Expected Outcome: 
			- The Like notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/
		hp.goToHomePage();
		hpAct.likeActivity(activity1);

		/*Step number: 5
		 *Step Name: Step 5 : Check on View All page
		 *Step Description: 
			- Go to View All page
		 *Input Data: 

		 *Expected Outcome: 
			- The Like notification is available/displayed in the View All page, at the top of the page. LAST_AVATAR$USER_LIST like your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LIST is User B, User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the last notification of User C*/ 
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		ArrayList<String> users = new ArrayList<String>();
		users.add(username1);
		users.add(username2);
		String status = notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkAvatarInStatus(users,false);
		intraNot.checkStatus(status, username2);
		intraNot.checkActivityTitleInStatus(activity1, false);

	}}