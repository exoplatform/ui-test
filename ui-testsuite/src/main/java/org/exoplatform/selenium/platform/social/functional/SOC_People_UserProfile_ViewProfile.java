package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.openqa.selenium.Dimension;
import org.testng.annotations.*;


public class SOC_People_UserProfile_ViewProfile extends SOC_TestConfig{

	/**
	 *<li> Case ID:125316.</li>
	 *<li> Test Case Name: Check page title.</li>
	 *<li> Pre-Condition: - test1 and test2 are connected</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckPageTitle() {
		info("Test 1: Check page title");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile page of another.
		 *Step Description: 
			- Login with test 1.
			- Go to the profile of test2.
			- Check page title (displayed in the browser tab).
		 *Input Data: 

		 *Expected Outcome: 
			While viewing a user profile, the title of the page (displayed in the browser tab) is the display name of the user. If the display name is not set, the full name (first name + last name) is used.*/
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		String title = driver.getTitle();
		assert(title.contentEquals(username2+" "+username2));

		/*Step number: 2
		 *Step Name: Step 2: Go to your profile.
		 *Step Description: 
			- From User menu, select My Profile.
		 *Input Data: 

		 *Expected Outcome: 
			While viewing his own profile, the title of the page (displayed in the browser tab) is My Profile.*/ 
		navTool.goToMyProfile();
		title = driver.getTitle();
		assert(title.contentEquals("My Profile"));
	}

	/**
	 *<li> Case ID:125315.</li>
	 *<li> Test Case Name: Check User Navigation bar.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckUserNavigationBar() {
		info("Test 2: Check User Navigation bar");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to My Profile page
		 *Step Description: 
			- Log in as a user.
			- Select My Profile from the User menu.
		 *Input Data: 

		 *Expected Outcome: 
			The My Profile page is shown.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		navTool.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2: Reduce viewport
		 *Step Description: 
			- Reduce the size of the viewport (reduce browser size for instance).
		 *Input Data: 

		 *Expected Outcome: 
			The User Navigation must be automatically adapted to the viewport. When the viewport is smaller than the navigation, a button "More" is displayed hiding the apps icons.*/ 
		Dimension d = new Dimension(1080,1080);
		driver.manage().window().setSize(d);
		waitForAndGetElement(uBase.ELEMENT_MORE_TAB);
	}

	/**
	 *<li> Case ID:125314.</li>
	 *<li> Test Case Name: Show my profile (all information filled).</li>
	 *<li> Case ID:126706.</li>
	 *<li> Test Case Name: View the profile of unconnected user.</li>
	 *<li> Pre-Condition: - The user A have updated his profile</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_06_ShowMyProfile() {
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String jobTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String url1 = getRandomString()+".com";
		String gender = "Male";
		String im1 = txData.getContentByArrayTypeRandom(1);
		String phone1 = getRandomNumber()+getRandomNumber();
		String email = txData.getContentByArrayTypeRandom(4) + getRandomString()+"@gmail.com";

		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String expJobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");

		String actAboutMe = activityMes.getActivityMessage(3);
		String actExperience = activityMes.getActivityMessage(1);
		String actContactInfo= activityMes.getActivityMessage(0);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		navTool.goToMyProfile();

		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		info("edit contact info");
		myProfile.updateAboutMe(aboutMe);
		myProfile.updateBasicInformation(null, null, email);
		myProfile.updateGenderJob(gender, jobTitle);
		myProfile.updateIms(contactIM.getProfileIM(0), im1,"1");
		myProfile.updatePhone(contactPhone.getContactPhoneData(0), phone1,"1");
		myProfile.updateUrl(url1,"1");
		myProfile.updateExperience(organization,expJobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);

		info("Test 3: Show my profile (all information filled)");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Profile page
		 *Step Description: 
			- Log in as user A.
			- Select My Profile from the User menu.
		 *Input Data: 

		 *Expected Outcome: 
			- My profile is displayed.*/

		/*Step number: 2
		 *Step Name: Step 2: Check layout
		 *Step Description: 
			- Check the mid
			-column.
		 *Input Data: 

		 *Expected Outcome: 
			- The 1st section is named About me and displays to the a paragraph to describe user.
			- The 2nd section is Experience and list of his experiences.
			- The 3rd section is named "Recent Activities" and list the activities of the User A.*/
		info("check after saving");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}", actAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actContactInfo));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actExperience));

		/*Step number: 3
		 *Step Name: Step 3: Check layout
		 *Step Description: 
			- Check the left column.
		 *Input Data: 

		 *Expected Outcome: 
			The section is named "Contact Information" and includes:
			- First Name : text field (mandatory)
			- Last Name (mandatory)
			- Email (mandatory)
			- Job Title
			- Gender
			- Phone
			- IMs:
			- URLs*/
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));
		waitForAndGetElement(myProfile.ELEMENT_JOB_TITLE_INFO.replace("${job}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));

		/*Step number: 4
		 *Step Name: Step 4 : Check layout
		 *Step Description: 
			- Check the right column.
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named "Connections" and display the last connections of the user.*/ 
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE);

		info("Test 6: View the profile of unconnected user");
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Log in as a user1.
			- Click [Connections] link on left navigation.
		 *Input Data: 

		 *Expected Outcome: 
			- The Connections page is shown.*/
		magAc.signIn(username2,password);

		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));

		/*Step number: 2
		 *Step Name: Step 2: View profile page of user2 who is not connected to user1
		 *Step Description: 
			- Click on name of user2.
		 *Input Data: 

		 *Expected Outcome: 
			Show content of user2's profile page, including:
			- Profile: Show all information about user2.
			- Activities: + Not show text box, so user1 cannot add new activity to the user2's AS.+ Display activities of user2.
			- Connections: 4 tabs:+ Everyone: list all users+ My Connections: list users which have connections with user2.+ Request received: show users that sent connection request touser2.+ Request pending: show users that user2 sent connection request.
			- Wiki: wiki page of user2.*/ 
		info("Profile: Show all information about user2.");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));
		waitForAndGetElement(myProfile.ELEMENT_JOB_TITLE_INFO.replace("${job}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE);

		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}", actAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actContactInfo));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actExperience));

		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);

		info("Activities: + Not show text box, so user1 cannot add new activity to the user2's AS.+ Display activities of user2.");
		uBase.goToActivityTab();
		waitForElementNotPresent(hpAct.ELEMENT_COMPOSER_INPUT_FILED);
		
		info("Connection tab");
		uBase.goToConnectionTab();
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB);
		
		info("wiki page");
		uBase.goToWikiTab();
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGE_TEXT);
	}

	/**
	 *<li> Case ID:125313.</li>
	 *<li> Test Case Name: Show my profile (default information).</li>
	 *<li> Pre-Condition: - The user A have not updated his profile yet</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_ShowMyProfile() {
		info("Test 4: Show my profile (default information)");
		String msgAboutMe = activityMes.getActivityMessage(4);
		String msgRecent_me = activityMes.getActivityMessage(5);
		String msg_me = conStatus.getConStatus(6);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		/*Step Number: 1
		 *Step Name: Step 1: Sign in system
		 *Step Description: 
			- Login with User A
			- Click user menu and [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- My profile is displayed*/
		navTool.goToMyProfile();
		assert driver.getTitle().contains("My Profile");
		/*Step number: 2
		 *Step Name: Step 2: Check layout
		 *Step Description: 
			- Check the mid
			-column
		 *Input Data: 

		 *Expected Outcome: 
			- The 1st section is named About me and displays to the user :"Help people find out more about you by sharing information on your profile." followed by a button Edit my Profile which takes the user the edit page.
			- The 2nd sections is name "Recent Activities" and list the activities of the User A*/
		info("check mid-column");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", msgAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_me));

		/*Step number: 3
		 *Step Name: Step 3: Check layout
		 *Step Description: 
			- Check left column
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named "Contact Information"
			- Only the email field is filled*/
		info("check left-column");
		waitForAndGetElement(myProfile.ELEMENT_UIBASICPROFILEPORTLET);
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email1));

		/*Step number: 4
		 *Step Name: Step 4 : Check layout
		 *Step Description: 
			- Check right column
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named Connections and display the last connections of the user*/ 
		info("check right-column");
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);

		info("click on Find connections");
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);

	}

	/**
	 *<li> Case ID:122749.</li>
	 *<li> Test Case Name: View the profile of connected user.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_ViewTheProfileOfConnectedUser() {
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String jobTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String url1 = getRandomString()+".com";
		String gender = "Male";
		String im1 = txData.getContentByArrayTypeRandom(1);
		String phone1 = getRandomNumber()+getRandomNumber();
		String email = txData.getContentByArrayTypeRandom(4) + getRandomString()+"@gmail.com";

		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String expJobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");

		String actAboutMe = activityMes.getActivityMessage(3);
		String actExperience = activityMes.getActivityMessage(1);
		String actContactInfo= activityMes.getActivityMessage(0);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		navTool.goToMyProfile();

		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		info("edit contact info");
		myProfile.updateAboutMe(aboutMe);
		myProfile.updateBasicInformation(null, null, email);
		myProfile.updateGenderJob(gender, jobTitle);
		myProfile.updateIms(contactIM.getProfileIM(0), im1,"1");
		myProfile.updatePhone(contactPhone.getContactPhoneData(0), phone1,"1");
		myProfile.updateUrl(url1,"1");
		myProfile.updateExperience(organization,expJobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);

		info("Test 5: View the profile of connected user");
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Log in as a user1
			- Click [Connections] link on left navigation.
		 *Input Data: 

		 *Expected Outcome: 
			- The Connections page is shown.*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Click [Connect] button on user2 with whom user1 has no connection .
		 *Input Data: 

		 *Expected Outcome: 
			- Connection request is sent to user2.
			- The button [Connect] is changed to [Cancel Request] button respectively.*/
		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);

		/*Step number: 3
		 *Step Name: Step 3: Accept the friend
		 *Step Description: 
			- Log in as user2.
			- Click [Connections] link on left navigation.
			- Click on button [Confirm] on user1.
		 *Input Data: 

		 *Expected Outcome: 
			- A connection between two users is created. 
			- The buttons [Confirm], [Ignore] disappear from user1.
			- The button [Remove Connection] is shown on user1.*/
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(username1);
		
		/*Step number: 4
		 *Step Name: Step 4: View profile page of user's contact
		 *Step Description: 
			- Click on name of user1.
		 *Input Data: 

		 *Expected Outcome: 
			Show content of user1's profile page, including:
			- Profile: Show all information about user1.
			- Activities: show text box, allowing user to add new activity and display activities of user's contact.
			- Connections: 4 tab+ Everyone: list all users+ My Connections: list users which have connections with user1.+ Request received: show users that sent connection request touser1.+ Request pending: show users that user1 sent connection request.
			- Wiki: wiki page of user1.*/
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}", actAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actContactInfo));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actExperience));
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));
		waitForAndGetElement(myProfile.ELEMENT_JOB_TITLE_INFO.replace("${job}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE);
		
		/*Step number: 5
		 *Step Name: Step 5: View the profile of user in your contact
		 *Step Description: 
			- Select Profile.
		 *Input Data: 

		 *Expected Outcome: 
			Show user1's profile. Some things are displayed:
			- Contact Information: + First Name, Last Name, Email.+ Job Title, Gender, Phone, IM, URL if any.
			- About Me: Some general information about user1 if any.
			- Experiences: show all experiences of user1 if any.
			- Avatar of user1: user2 will not see the [Edit my Profile] button so can't edit profile of user1.
			- Recent activities: activities of user1.
			- Connected status: Show the "Connected" status between user1 and user2.
			- Connections: list of user1's connections.*/ 
		magAc.signIn(username1,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		info("Profile: Show all information about user2.");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));
		waitForAndGetElement(myProfile.ELEMENT_JOB_TITLE_INFO.replace("${job}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE);

		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}", actAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actContactInfo));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actExperience));

		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
		waitForAndGetElement(uBase.ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);

		info("Activities: + Not show text box, so user1 cannot add new activity to the user2's AS.+ Display activities of user2.");
		uBase.goToActivityTab();
		waitForAndGetElement(hpAct.ELEMENT_COMPOSER_INPUT_FILED);
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");

		info("Connection tab");
		uBase.goToConnectionTab();
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB);
		waitForAndGetElement(connMag.ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB);
		connMag.goToConnectionTab(selectTabOption.MYCONNECTION);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER1));
		
		info("wiki page");
		uBase.goToWikiTab();
		waitForAndGetElement(wHome.ELEMENT_WIKI_HOME_PAGE_TEXT);
	}
}