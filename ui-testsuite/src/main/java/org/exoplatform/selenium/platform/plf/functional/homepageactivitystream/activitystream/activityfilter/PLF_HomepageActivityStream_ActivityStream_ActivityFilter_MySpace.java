/**
* Generated by khanhnt at 2013/12/26 10:26:31
*
* Copyright (C) 2009 eXo Platform SAS.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.exoplatform.selenium.platform.plf.functional.homepageactivitystream.activitystream.activityfilter;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

	/**
	* @author khanhnt
	*
	*/
	public class PLF_HomepageActivityStream_ActivityStream_ActivityFilter_MySpace extends Activity{

		ManageAccount acc; 
		HomePageActivity home;
		NavigationToolbar nav; 
		PeopleConnection pConn; 
		ManageMember mMember; 
		String user = "John Smith";
		String user1= "Jack Miller";	
		SpaceManagement spaceMan;
		@BeforeMethod
		public void beforeMethods(){	
			initSeleniumTest();
			driver.get(baseUrl);
			acc = new ManageAccount(driver,this.plfVersion);
			home = new HomePageActivity(driver,this.plfVersion);
			nav = new NavigationToolbar(driver,this.plfVersion);	
			pConn = new PeopleConnection(driver,this.plfVersion);
			mMember = new ManageMember(driver,this.plfVersion);
			spaceMan = new SpaceManagement(driver,this.plfVersion);
			acc.signIn(DATA_USER1, DATA_PASS);		
		}

		@AfterMethod
		public void afterMethods() {
			info("Logout portal");
			driver.manage().deleteAllCookies();
			driver.quit();
		}

	/**
	* Case ID:77657.
	* Test Case Name: Activity posted from a space should be visible in "My Spaces".
	* Pre-Condition: A space is createdUser is a member of a space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test01_ActivityPostedFromASpaceShouldBeVisibleInMySpaces() {
		info("Test 1: Activity posted from a space should be visible in My Spaces");
		String text = "Test 1 New Activity";
		String spaceName = "Test77657";
		/*
		- Connect to Intranet
		- open a Space
		- Post an activity in the space
		*Input Data: 
		*Expected Outcome: The activity is added to the activity stream of the space		*/
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		spaceMan.goToSpaceMenu("Activity Stream",100000);
		addActivity(true, text, false, "");

		/*
		- Back to Homepage of Intranet
		- Choose the [My spaces] stream
		*Input Data: 
		*Expected Outcome: The activity is added to the stream "My spaces"		*/ 
		nav.goToHomePage();
		selectFileter("My Spaces");
		waitForActivityPresent(text, true);
		
		//remove data
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
	}

	/**
	* Case ID:77661.
	* Test Case Name: Activity posted from a space should be visible in "My Spaces" of other members.
	* Pre-Condition: A space "test" is createdUser A and User B are members in a same space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test02_ActivityPostedFromASpaceShouldBeVisibleInMySpacesOfOtherMembers() {
		info("Test 2: Activity posted from a space should be visible in My Spaces of other members");
		String text = "Test 2 New Activity";
		String spaceName = "Space77661";
		/*
		- Connect to Intranet with User A
		- Open the Space "Test"
		- Post an activity in the stream of the space
		*Input Data: 
		*Expected Outcome: The activity is add to the stream		*/
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		goToMembers(spaceName);
		mMember.inviteSingleUser(userType.DEVELOPER);
		//mMember.inviteSingleUser(DATA_USER2);
		spaceMan.goToSpaceMenu("Activity Stream");
		addActivity(true, text, false, "");
		acc.signOut();

		/*
		- Connect to Intranet with User B
		- Open the stream [My spaces]
		*Input Data: 
		*Expected Outcome: The activity posted by the User A in the space is displayed in the stream "My spaces"		*/ 
		acc.signIn("demo", DATA_PASS);
		mMember.goToMySpacePage();
		mMember.acceptInvitation(spaceName);
		nav.goToHomePage();
		selectFileter("My Spaces");
		waitForActivityPresent(text, true);
		
		//remove data
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
	}

	/**
	* Case ID:77678.
	* Test Case Name: Comment on my activity from "My spaces".
	* Pre-Condition: user is member of space and have an activity on this space created by this user
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test03_CommentOnMyActivityFromMySpaces() {
		info("Test 3: Comment on my activity from My spaces");
		String spaceName = "Space77678";
		String text = "Test 3 new activity";
		/*
		- Connect to Intranet/Homepage
		- In the stream [My spaces], add a comment to my activity
		*Input Data: 
		*Expected Outcome: 
		- The comment is added to the activity		*/

		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		spaceMan.goToSpaceMenu("Activity Stream");
		addActivity(true, text, false, "");
		/*
		- Back to the homepage
		- Switch to the stream [My activities]
		*Input Data: 
		*Expected Outcome: The activity commented is displayed in the stream "My activities"		*/ 
		nav.goToHomePage();
		selectFileter("My Activities");
		waitForActivityPresent(text, true);
		
		//remove data
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
 	}

	/**
	* Case ID:77685.
	* Test Case Name: Activity posted from a space should not be visible in user activity stream by user is not member of space.
	* Pre-Condition: Have an spaceUser and User B aren't member in a same space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test04_ActivityPostedFromASpaceShouldNotBeVisibleInUserActivityStreamByUserIsNotMemberOfSpace() {
		info("Test 4: Activity posted from a space should not be visible in user activity stream by user is not member of space");
		String spaceName = "Space77685";
		String text = "Test 4 new activity";
		/*
		- Connect to Intranet with User A
		- Open a space
		- Post an activity in the space
		*Input Data: 
		*Expected Outcome: Posted an activity on space successfully		*/
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		spaceMan.goToSpaceMenu("Activity Stream");
		addActivity(true, text, false, "");

		/*
		- Back to homepage
		- Go to [My activities] stream
		*Input Data: 
		*Expected Outcome: 
		- The activity added by te user A is displayed in the stream "My activities"		*/
		nav.goToHomePage();
		selectFileter("My Activities");
		waitForActivityPresent(text, true);

		/*
		- Go to [My Profile]
		- Choose [My Activity Stream]
		*Input Data: 
		*Expected Outcome: 
		- The activity added by te user A is displayed in the stream "Activity stream"		*/
		goToActivityStream();
		waitForActivityPresent(text, true);
		acc.signOut();
		/*
		- Connect to Intranet with the User B
		- From [My Connections], choose [Everyone] tab and select the User A
		- Open the "Activity Stream" of the User A
		 *Input Data: 
		 *Expected Outcome: The User B can't view the activity posted by the user A from his space		*/ 
		acc.signIn(DATA_USER4, DATA_PASS);
		nav.goToConnectionPage();
		click(By.linkText(user));
		waitForAndGetElement(ELEMENT_MY_PROFILE_TAB);
		click(ELEMENT_MY_ACTIVITY_STREAM_TAB);
		waitForAndGetElement(ELEMENT_ACTIVITY_STREAM);
		waitForActivityNotPresent(text, true);
	
		//remove data
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
 	}

	/**
	* Case ID:77690.
	* Test Case Name: User can comment on an activity posted in a space where he is not member when he was mentioned in it.
	* Pre-Condition: User B isn't a member of the space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test05_UserCanCommentOnAnActivityPostedInASpaceWhereHeIsNotMemberWhenHeWasMentionedInIt() {
		info("Test 5: User can comment on an activity posted in a space where he is not member when he was mentioned in it");
		String spaceName = "space77690";
		String text = "Test 5 New comment";
		/*
		- Connect to Intranet with User A
		- Open a space
		- Mention user B in the acitivity shared box of the space
		- Share the message
		*Input Data: 
		*Expected Outcome: The message is added to the activity astream where the name of the user B is mentioned		*/
		
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"",150000);
		goToMembers(spaceName);
		spaceMan.goToSpaceMenu("Activity Stream");
		mentionActivity(true,"",user1);
		acc.signOut();

		/*
		- Connect to Intranet with the User B
		- Open the stream "All activities"
		 *Input Data: 
		 *Expected Outcome: The activity which user B is mentioned is displayed		*/
		acc.signIn(DATA_USER4, DATA_PASS);
		nav.goToHomePage();
		selectFileter("My Activities");
		waitForAndGetElement(ELEMENT_USER_MENTION_BY_OTHER_USER.replace("${author}",user).replace("${userName}", user1));
		/*
		- Add a comment to the activity
		*Input Data: 
		*Expected Outcome: The comment is added		*/ 
		addComment(user1, text);
		
		//remove data
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,150000);
 	}

	/**
	* Case ID:77694.
	* Test Case Name: Not Display the dropdown list of stream in Space's activity stream.
	* Pre-Condition: Have an space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test06_NotDisplayTheDropdownListOfStreamInSpacesActivityStream() {
		info("Test 6: Not Display the dropdown list of stream in Space's activity stream");
		String spaceName = "space77694";
		/*
		- Connect to Intranet
		- Open a space
		*Input Data: 
		*Expected Outcome: 
		- The space's activity stream is displayed
		- The dropdown list of stream isn't displayed		*/ 
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		
		waitForAndGetElement(ELEMENT_SPACE_ACTIVITY_STREAM);
		waitForElementNotPresent(ELEMENT_ACTIVITY_DROPDOWN);
		
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
 	}

	/**
	* Case ID:77698.
	* Test Case Name: Show space's activity on activity stream of user.
	* Pre-Condition: User is member of space
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test07_ShowSpacesActivityOnActivityStreamOfUser() {
		info("Test 7: Show space's activity on activity stream of user");
		String spaceName = "space77698";
		String text = "Test 7 activity";
		/*
		- Connect to Intranet with user A
		- Open a Space and share an activity
		- Go to "Activities stream"
		*Input Data: 
		*Expected Outcome: 
		- The space's activity is displayed in the "My activities stream"		*/ 
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");

		spaceMan.goToSpaceMenu("Activity Stream");
		addActivity(true, text, false, "");
		
		goToActivityStream();
		waitForActivityPresent(text, true);
		
		//remove data
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
 	}

	/**
	* Case ID:77699.
	* Test Case Name: Not display space's activity on user's activity stream for other user.
	* Pre-Condition: A space is created.There are some activities posted on space's AS.
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by khanhnt at 2013/12/26 10:26:31
	*/
	@Test
	public  void test08_NotDisplaySpacesActivityOnUsersActivityStreamForOtherUser() {
		info("Test 8: Not display space's activity on user's activity stream for other user");
		String spaceName = "space77699";
		String text1 = "Test 8 activity 1";
		String text2 = "Test 8 activity 2";
		String text3 = "Test 8 activity 3";
		/*
		- Connect to Intranet with user A
		- Open a Space and share an activity
		- Go to "Activities stream"
		*Input Data: 
		*Expected Outcome: 
		- All of the space's activities are displayed in the "My activities stream"		*/
		
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName,"");
		spaceMan.goToSpaceMenu("Activity Stream");
		addActivity(true, text1, false, "");
		addActivity(true, text2, false, "");
		addActivity(true, text3, false, "");
		goToActivityStream();
		waitForActivityPresent(text1, true);
		waitForActivityPresent(text2, true);
		waitForActivityPresent(text3, true);
		acc.signOut();
		/*
		- Connect to Intranet with User B
		- Click on name of the User A
		- Go to activities stream
		*Input Data: 
		*Expected Outcome: 
		- Non of the space's activity is displayed in the stream activityof the user A		*/ 
		acc.signIn(DATA_USER4, DATA_PASS);
		nav.goToConnectionPage();
		click(By.linkText(user));
		waitForAndGetElement(ELEMENT_MY_PROFILE_TAB);
		click(ELEMENT_MY_ACTIVITY_STREAM_TAB);
		waitForAndGetElement(ELEMENT_ACTIVITY_STREAM);
		waitForActivityNotPresent(text1, true);
		waitForActivityNotPresent(text2, true);
		waitForActivityNotPresent(text3, true);
		//remove data
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName);
	}
}