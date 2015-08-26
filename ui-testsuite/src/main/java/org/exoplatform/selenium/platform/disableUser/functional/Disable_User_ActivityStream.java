package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ActivityStream.optionMenuActivity;
import org.testng.annotations.*;


	public class Disable_User_ActivityStream extends Disable_User_TestConfig{
		
		public void createUser() {
			username = userInfoData.getUserNameByIndex(5)+getRandomString();
			password = userInfoData.getPassWordByIndex(5)+getRandomString();
			lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
			email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
            membership = portMemPermisData.getContentByIndex(0);

			info("Create new user");
			navToolBar.goToAddUser();
			addUserPage.addUser(username, password, email, username, lastName);
			navToolBar.goToUsersAndGroupsManagement();
	 	 	userAndGroup.goToGroupTab();
	 	 	click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Platform"));
	 	 	click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", "Administration"));
	 	 	userAndGroup.addUsersToGroup(username, membership, false, true);
	 	 	
	 	 	info ("Connect with user");
			hp.goToConnections();
			connMag.connectToAUser(username);
			magAc.signIn(username, password);
			hp.goToConnections();
			connMag.acceptAConnection(DATA_USER1);
		}
		
	/**
	*<li> Case ID:127963.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable active user in case less than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are less than 20 activities (For example: 5 activities) on All activities stream of User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAllActivitiesStreamOfDisabledUserAfterReenableActiveUserInCaseLessThan20Activities() {
		info("Test 1: Check All Activities stream of disabled user after re-enable active user in case less than 20 activities");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/
		info("Add an activity");
		hp.goToHomePage();
		hpAct.addActivity(activity1, "");
		hpAct.checkActivity(activity1);
		
		/*Step number: 2
		*Step Name: Step 2: Check the ability to see the activity by other enable user
		*Step Description: 
			- From another browser, connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the activity (1) shared by the User B*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.checkActivity(activity1);
		
		/*Step number: 3
		*Step Name: Step 3: Share an activity after disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
			- Share an activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All activities stream*/
		magAc.signOut();
		magAc.signIn(username, password);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(DATA_USER1, searchUserName);
 	 	userAndGroup.enableDisableUser(DATA_USER1, false);
 	 	
 	 	info("Add an activity");
		hp.goToHomePage();
		hpAct.addActivity(activity2, "");
		hpAct.checkActivity(activity2);
		
		info("enable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
 	 	userAndGroup.searchUser(DATA_USER1, searchUserName);
 	 	userAndGroup.enableDisableUser(DATA_USER1, true);
 	 	
		/*Step number: 4
		*Step Name: Step 4: Check the ability to see the activity after re-enable an user
		*Step Description: 
			- Enable again the User A
			- Go to the Intranet homepage of the Use A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B on All activities stream
			- User A only can see the activity (1) that is shared before User A is disabled on All activities stream*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.checkNoActivity(activity2);
		hpAct.checkActivity(activity1);
		
		info("delete data");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
		
 	}

	/**
	*<li> Case ID:131088.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable active user in case more than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are more than 20 activities on All activities stream of User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAllActivitiesStreamOfDisabledUserAfterReenableActiveUserInCaseMoreThan20Activities() {
		info("Test 2: Check All Activities stream of disabled user after re-enable active user in case more than 20 activities");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		info("add many activities");
		hp.goToHomePage();
		for(int i=0;i<21;i++){
			hpAct.addActivity(i+activity1, "");
			hpAct.checkActivity(i+activity1);
		}
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.addActivity(activity2, "");
		hpAct.checkActivity(activity2);
		
		/*Step number: 2
		*Step Name: Step 2: Check the ability to see the activity by other enable user
		*Step Description: 
			- From another browser, connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the activity (1) shared by the User B*/
		magAc.signOut();
		magAc.signIn(username, password);
		hpAct.checkActivity(activity2);
		
		/*Step number: 3
		*Step Name: Step 3: Share an activity after disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
			- Share an activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All activities stream*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, false);
 	 	
 	 	hp.goToHomePage();
 	 	hpAct.addActivity(activity3, "");
		hpAct.checkActivity(activity3);
		
		/*Step number: 4
		*Step Name: Step 4: Check the ability to see the activity after re-enable an user
		*Step Description: 
			- Enable again the User A
			- Go to the Intranet homepage of the Use A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B on All activities stream
			- User A only can see the activity (1) that is shared before User A is disabled on All activities stream*/ 
		info("enable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, true);
 	 	
 	 	magAc.signOut();
		magAc.signIn(username, password);
 	 	hpAct.checkActivity(activity2);
 	 	hpAct.checkNoActivity(activity3);
 	 	
 	 	info("delete data");
 	 	magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
 	 	navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:131074.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable active user via crash addon in case less than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are less than 20 activities (For example: 5 activities) on All Activities stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test03_CheckAllActivitiesStreamOfDisabledUserAfterReenableActiveUserViaCrashAddonInCaseLessThan20Activities() {
		info("Test 3: Check All Activities stream of disabled user after re-enable active user via crash addon in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/

		/*Step number: 4
		*Step Name: Step 4: Connect to intranet with User A
		*Step Description: 
			- Connect to intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/ 

 	}

	/**
	*<li> Case ID:131089.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable active user via crash addon in case more than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are more than 20 activities on All Activities stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test04_CheckAllActivitiesStreamOfDisabledUserAfterReenableActiveUserViaCrashAddonInCaseMoreThan20Activities() {
		info("Test 4: Check All Activities stream of disabled user after re-enable active user via crash addon in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			- Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity (2) is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/

		/*Step number: 4
		*Step Name: Step 4: Connect to intranet
		*Step Description: 
			- Connect to intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/ 

 	}

	/**
	*<li> Case ID:131086.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable inactive user in case less than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A do not log in to plf in 30 days or 45 days, for example), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are less than 20 activities (For example: 5 activities) on All activities stream of User A</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test05_CheckAllActivitiesStreamOfDisabledUserAfterReenableInactiveUserInCaseLessThan20Activities() {
		info("Test 5: Check All Activities stream of disabled user after re-enable inactive user in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share an activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- Activity (2) is shared on All activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Re
		-enable User A
		*Step Description: 
			- Enable again the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enabled.*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see both activity (1) and activity (2) shared by the User B on All activities stream*/ 

 	}

	/**
	*<li> Case ID:131087.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable inactive user in case more than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A do not log in to plf in 30 days or 45 days, for example), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are more than 20 activities on All activities stream of User A</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test06_CheckAllActivitiesStreamOfDisabledUserAfterReenableInactiveUserInCaseMoreThan20Activities() {
		info("Test 6: Check All Activities stream of disabled user after re-enable inactive user in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity 1 on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share activity 2 on All activities stream
		*Step Description: 
			- Change the status of the User A to Disabled
			- Share activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity (2) is shared on All activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Re
		-enable User A
		*Step Description: 
			- Enable again the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enabled*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see both activity (1) and activity (2) shared by the User B on All activities stream*/ 

 	}

	/**
	*<li> Case ID:131094.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable inactive user via crash addon in case less than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A has already not loggin to platform in 30 or 45 days, depending on the configuration), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are less than 20 activities (For example: 5 activities) on All Activities stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test07_CheckAllActivitiesStreamOfDisabledUserAfterReenableInactiveUserViaCrashAddonInCaseLessThan20Activities() {
		info("Test 7: Check All Activities stream of disabled user after re-enable inactive user via crash addon in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-enable User A
		*Step Description: 
			- In User B session, re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enable*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 6
		*Step Name: Step 6: Re
		-active User A
		*Step Description: 
			- Connect intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 7
		*Step Name: Step 7: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/ 

 	}

	/**
	*<li> Case ID:131097.</li>
	*<li> Test Case Name: Check All Activities stream of disabled user after re-enable inactive user via crash addon in case more than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A has already not loggin to platform in 30 or 45 days, depending on the configuration), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are more than 20 activities on All Activities stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li> 
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test08_CheckAllActivitiesStreamOfDisabledUserAfterReenableInactiveUserViaCrashAddonInCaseMoreThan20Activities() {
		info("Test 8: Check All Activities stream of disabled user after re-enable inactive user via crash addon in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-enable User A
		*Step Description: 
			- In User B session, re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enable*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 6
		*Step Name: Step 6: Re
		-active User A
		*Step Description: 
			- Connect intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 7
		*Step Name: Step 7: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/ 

 	}

	/**
	*<li> Case ID:127964.</li>
	*<li> Test Case Name: Check Connection stream of disabled user.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	* Testcase is deleted
	*/
	
	/**
	*<li> Case ID:131084.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable active user in case less than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are less than 20 activities (For example: 5 activities) on Connections stream of User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckConnectionsStreamOfDisabledUserAfterReenableActiveUserInCaseLessThan20Activities() {
		info("Test 10 Check Connections stream of disabled user after re-enable active user in case less than 20 activities");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/
		hp.goToHomePage();
		info("add an activity");
 	 	hpAct.addActivity(activity1, "");
		hpAct.checkActivity(activity1);
		
		/*Step number: 2
		*Step Name: Step 2: Check the ability to see the activity by other enable user
		*Step Description: 
			- From another browser, connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the activity (1) shared by the User B*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.checkActivity(activity1);
		
		/*Step number: 3
		*Step Name: Step 3: Share an activity after disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
			- Share an activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All activities stream*/
		magAc.signOut();
		magAc.signIn(username,password);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(DATA_USER1, searchUserName);
 	 	userAndGroup.enableDisableUser(DATA_USER1, false);
 	 	
 	 	hp.goToHomePage();
 	 	hpAct.addActivity(activity2, "");
		hpAct.checkActivity(activity2);
		/*Step number: 4
		*Step Name: Step 4: Check the ability to see the activity after re
		-enable an user
		*Step Description: 
			- Enable again the User A
			- Go to Connections stream of User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B on Connections stream
			- User A only can see the activity (1) that is shared before User A is disabled on Connections stream*/ 
		info("enable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
 	 	userAndGroup.searchUser(DATA_USER1, searchUserName);
 	 	userAndGroup.enableDisableUser(DATA_USER1, true);
 	 	
 	 	magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.selectOptMenuActivity(optionMenuActivity.Connections);
 	 	hpAct.checkActivity(activity1);
 	 	hpAct.checkNoActivity(activity2);
 	 	
 	 	info("delete data");
 	 	navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:131090.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable active user in case more than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are more than 20 activities on Connections stream of User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckConnectionsStreamOfDisabledUserAfterReenableActiveUserInCaseMoreThan20Activities() {
		info("Test 11 Check Connections stream of disabled user after re-enable active user in case more than 20 activities");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		info("add many activities");
		hp.goToHomePage();
		for(int i=0;i<18;i++){
			hpAct.addActivity(i+activity1, "");
			hpAct.checkActivity(i+activity1);
		}
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.addActivity(activity2, "");
		hpAct.checkActivity(activity2);

		/*Step number: 2
		*Step Name: Step 2: Check the ability to see the activity by other enable user
		*Step Description: 
			- From another browser, connect to Intranet with the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A can see the activity (1) shared by the User B*/
		magAc.signOut();
		magAc.signIn(username, password);
 	 	hpAct.checkActivity(activity2);
 	 	
		/*Step number: 3
		*Step Name: Step 3: Share an activity after disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
			- Share an activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All activities stream*/
 	 	magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, false);
 	 	
 	 	hp.goToHomePage();
 	 	hpAct.addActivity(activity3, "");
		hpAct.checkActivity(activity3);
		
		/*Step number: 4
		*Step Name: Step 4: Check the ability to see the activity after re
		-enable an user
		*Step Description: 
			- Enable again the User A
			- Go to Connections stream of User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B on Connections stream
			- User A only can see the activity (1) that is shared before User A is disabled on Connections stream*/ 
		info("enable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, true);
 	 	
 	 	magAc.signOut();
		magAc.signIn(username, password);
		hpAct.selectOptMenuActivity(optionMenuActivity.Connections);
 	 	hpAct.checkActivity(activity2);
 	 	hpAct.checkNoActivity(activity3);
 	 	
 	 	info("delete data");
 	 	magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
 	 	navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:131085.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable active user via crash addon in case less than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are less than 20 activities (For example: 5 activities) on Connections stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li> 
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test12_CheckConnectionsStreamOfDisabledUserAfterReenableActiveUserViaCrashAddonInCaseLessThan20Activities() {
		info("Test 12 Check Connections stream of disabled user after re-enable active user via crash addon in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			- Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity (2) is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/ 

 	}

	/**
	*<li> Case ID:131091.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable active user via crash addon in case more than 20 activities.</li>
	*<li> Pre-Condition: User A and User B are active usersUser A and User B are connectedUser A is enabled, User B is adminThere are more than 20 activities on Connections stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/ 
	@Test (groups="pending")
	public  void test13_CheckConnectionsStreamOfDisabledUserAfterReenableActiveUserViaCrashAddonInCaseMoreThan20Activities() {
		info("Test 13 Check Connections stream of disabled user after re-enable active user via crash addon in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			- Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity (2) is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CANNOT see the activity (2) shared by the User B
			- User A only can see the activity (1) that is shared before User A is disabled*/ 

 	}

	/**
	*<li> Case ID:131092.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable inactive user in case less than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A do not log in to plf in 30 days or 45 days, for example), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are less than 20 activities (For example: 5 activities) on Connections stream of User A</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test14_CheckConnectionsStreamOfDisabledUserAfterReenableInactiveUserInCaseLessThan20Activities() {
		info("Test 14 Check Connections stream of disabled user after re-enable inactive user in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- Activity (2) is shared on All activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Re
		-enable User A
		*Step Description: 
			- Re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enable*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to intranet withUser A
			- Go to Connections stream of the Use A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see both activity (1) and activity (2) shared by the User B on Connections stream*/ 

 	}

	/**
	*<li> Case ID:131093.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable inactive user in case more than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A do not log in to plf in 30 days or 45 days, for example), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are more than 20 activities on Connections stream of User A</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test15_CheckConnectionsStreamOfDisabledUserAfterReenableInactiveUserInCaseMoreThan20Activities() {
		info("Test 15 Check Connections stream of disabled user after re-enable inactive user in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			- Change the status of the User A to Disabled
			- Share activity (2) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- Activity (2) is shared on All activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Re
		-enable User A
		*Step Description: 
			- Re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enabled*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-active User A
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Connections stream of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see both activity (1) and activity (2) shared by the User B on Connections stream*/ 

 	}

	/**
	*<li> Case ID:131103.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable inactive user via crash addon in case less than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A has already not loggin to platform in 30 or 45 days, depending on the configuration), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are less than 20 activities (For example: 5 activities) on Connections stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test16_CheckConnectionsStreamOfDisabledUserAfterReenableInactiveUserViaCrashAddonInCaseLessThan20Activities() {
		info("Test 16 Check Connections stream of disabled user after re-enable inactive user via crash addon in case less than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-enable User A
		*Step Description: 
			- In User B session, re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enable*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 6
		*Step Name: Step 6: Re
		-active User A
		*Step Description: 
			- Connect intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 7
		*Step Name: Step 7: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/ 

 	}

	/**
	*<li> Case ID:131105.</li>
	*<li> Test Case Name: Check Connections stream of disabled user after re-enable inactive user via crash addon in case more than 20 activities.</li>
	*<li> Pre-Condition: User A is inactive user (User A has already not loggin to platform in 30 or 45 days, depending on the configuration), User A is enabledUser B is active user, User B is adminUser A and User B are connectedThere are more than 20 activities on Connections stream of User A"exo
	-crash
	-tomcat" addon has already installedHow to check the activity via crash addon:
	- telnet localhost 5000
	- repo use container=portal
	- ws login 
	-u root 
	-p <password> social
	- Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test17_CheckConnectionsStreamOfDisabledUserAfterReenableInactiveUserViaCrashAddonInCaseMoreThan20Activities() {
		info("Test 17 Check Connections stream of disabled user after re-enable inactive user via crash addon in case more than 20 activities");
		/*Step Number: 1
		*Step Name: Step 1: Share activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share an activity (1) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream.*/

		/*Step number: 2
		*Step Name: Step 2: Share an activity after disable an user
		*Step Description: 
			-Change the status of the User A to Disabled
			- Share an activity (2) on All Activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled
			- The activity is shared on All Activities stream*/

		/*Step number: 3
		*Step Name: Step 3: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 4
		*Step Name: Step 4: Re
		-enable User A
		*Step Description: 
			- In User B session, re
			-enable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is enable*/

		/*Step number: 5
		*Step Name: Step 5: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/

		/*Step number: 6
		*Step Name: Step 6: Re
		-active User A
		*Step Description: 
			- Connect intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- Homepage intranet is displayed.*/

		/*Step number: 7
		*Step Name: Step 7: Check the ability to see the activity when User A is disabled via console
		*Step Description: 
			- Open console and run following command lines:+ telnet localhost 5000+ repo use container=portal+ ws login 
			-u root 
			-p <password> social+ Go to the activity reference of the user: cd /production/soc:providers/soc:organization/soc:<userName>/soc:streams/soc:connection/soc:<year>/soc:<month>/soc:<day>/soc:<>/
		*Input Data: 
			
		*Expected Outcome: 
			- User A CAN see the both activity (1) and activity (2) shared by the User B*/ 

 	}

	/**
	*<li> Case ID:128227.</li>
	*<li> Test Case Name: Check disabled user in Mention list.</li>
	*<li> Pre-Condition: - User A is admin
	- User B is disabled
	- User A and User B are connected</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_CheckDisabledUserInMentionList() {
		info("Test 18 Check disabled user in Mention list");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, false);
		/*Step Number: 1
		*Step Name: Step 1: Post an activity include mentioning
		*Step Description: 
			- Connect to Intranet with User A
			- Go to All activities stream
			- Post an activity with Mention User B
		*Input Data: 
			
		*Expected Outcome: 
			- User B is not listed in Mention listuser*/ 
 	 	hp.goToHomePage();
		hpAct.checkMentionListUser(username, activity1, false);
		
		info("delete data");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127965.</li>
	*<li> Test Case Name: Check Past activities of disabled user.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CheckPastActivitiesOfDisabledUser() {
		info("Test 19 Check Past activities of disabled user");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String activity3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		createUser();
		info("add an activity");
		hp.goToHomePage();
		hpAct.addActivity(activity1, "");
		hpAct.checkActivity(activity1);
		
		/*Step Number: 1
		*Step Name: Step 1: Share an activity on All activities stream
		*Step Description: 
			- Connect to Intranet with User B
			- Share activity (1) on All activities stream of User B
			- Share activity (2) on activity stream of User A
		*Input Data: 
			
		*Expected Outcome: 
			- The activity (1) is displayed on the All activities stream of User A and User B
			- Activity (2) is displayed on All activities stream of User B and on activity stream of User A*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hpAct.checkActivity(activity1);
		hpAct.addActivity(activity2, "");
		hpAct.checkActivity(activity2);
		
		magAc.signOut();
		magAc.signIn(username,password);
		hpAct.checkActivity(activity2);
		
		/*Step number: 2
		*Step Name: Step 2: Comment and like activity
		*Step Description: 
			- Connect to Intranet with User A
			- Make some comments and like on activity (1)
			- Make some comments and like on activity (2)
			- Share an activity (3) on All activities stream
		*Input Data: 
			
		*Expected Outcome: 
			- Activity (3), Comments and like for activity (1) and activity (2) are displayed*/
		hpAct.likeActivity(activity1);
		hpAct.addCommentUsingJavascript(activity1, comment1);
		hpAct.likeActivity(activity2);
		hpAct.addCommentUsingJavascript(activity2, comment2);
		hpAct.addActivity(activity3, "");
		hpAct.checkActivity(activity3);
		
		/*Step number: 3
		*Step Name: Step 3: Disable an user
		*Step Description: 
			- In the User B session, change the status of the User A to Disabled
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled*/
		
		/*Step number: 4
		*Step Name: Step 4: Check past activities, comment, like of disabled user on All activities stream of connected user
		*Step Description: 
			- Go to the All activities stream of User B
		*Input Data: 
			
		*Expected Outcome: 
			- Past activities of User A (activity (3)), comments and likes of User A for activity (1) and activity (2) still remains.*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		info("Disable user");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, false);
 	 	
 	 	hp.goToHomePage();
		hpAct.checkActivity(activity3);
		hpAct.checkCommentOfActivity(activity1,comment1);
		hpAct.checkCommentOfActivity(activity2,comment2);
		hpAct.checkNumLikeOfActivity (activity1,1);
		hpAct.checkNumLikeOfActivity (activity2,1);
		
		info("delete data");
 	 	navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.selectDisableStatus("All");
		userAndGroup.deleteUser(username);
 	}}