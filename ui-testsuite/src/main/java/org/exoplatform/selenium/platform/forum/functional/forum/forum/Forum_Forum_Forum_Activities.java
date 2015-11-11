package org.exoplatform.selenium.platform.forum.functional.forum.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * update chinhdtt
 */
public class Forum_Forum_Forum_Activities extends ForumBase{

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumManageTopic fmTopic;
	ForumManagePost fmPost;
	HomePageActivity homePage;
	Activity activity;
	ManageMember manMember;

	SpaceManagement spaceMan;
	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(plfURL);
		fmCat = new ForumManageCategory(driver, this.plfVersion);
		fmForum = new ForumManageForum(driver, this.plfVersion);
		fmTopic = new ForumManageTopic(driver, this.plfVersion);
		fmPost = new ForumManagePost(driver, this.plfVersion);
		homePage = new HomePageActivity(driver, this.plfVersion);
		spaceMan = new SpaceManagement(driver);
		activity = new Activity();
		manMember = new ManageMember(driver, this.plfVersion);
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		fmForum = new ForumManageForum(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:106238. 
	 * Test Case Name: Add a Forum's activity after create a Topic of intranet's portal. 
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test01_AddAForumsActivityAfterCreateATopicOfIntranetsPortal() {
		info("Test 1: Add a Forum's activity after create a Topic of intranet's portal");

		String catName = "Category 106238" + getRandomNumber();
		String fmName = "Test 1 new forum" + getRandomNumber();
		String tpName = "Test 1 new topic" + getRandomNumber();	

		/*
		- Connect to Intranet
		- Open a Forum
		- Create a Topic
		- Back to the Hompage
		 *Expected Outcome: 
		- An activity of Forum is added to the activity stream
		- The content of the activity is displayed		*/ 
		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);
		click(ELEMENT_HOME_PAGE);
		driver.navigate().refresh();
		homePage.checkNumberOfLineOfContent(tpName, tpName);	
		waitForAndGetElement(By.linkText(tpName));

		//Delete data test
		goToForums();
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
	}

	/**
	 * Case ID:106281. 
	 * Test Case Name: Add a Forum's activity after create a Topic of space. 
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test02_AddAForumsActivityAfterCreateATopicOfSpace() {
		info("Test 2: Add a Forum's activity after create a Topic of space");

		String spaceName = "Space106281" + getRandomNumber();
		String tpName = "Test 2 new topic" + getRandomNumber();
		String tpContent = "Test 2 new content" + getRandomNumber();

		/*
		- Connect to Intranet
		- Click [Join a space]
		- Add new space
		 *Expected Outcome: 
		- New space is added successfully		*/
		spaceMan.goToAllSpaces();
		spaceMan.addNewSpace(spaceName, spaceName);

		/*Go into a spaceOpen forumCreate a new topicBack to the Homepage
		 *Input Data: 
		 *Expected Outcome: 
		- An activity of Forum is added to the activity stream
		- The content of the activity is displayed
		- The activity is clearly mentionning the topic comes from a space		*/ 
		spaceMan.goToSpaceMenu("Forums");		
		fmTopic.quickStartTopic(tpName, tpContent);

		click(ELEMENT_HOME_PAGE);
		driver.navigate().refresh();
		waitForAndGetElement(By.linkText(spaceName));
		waitForAndGetElement(By.linkText(tpName));

		//Delete data test 
		spaceMan.goToAllSpaces();
		spaceMan.deleteSpace(spaceName, 300000);		
	}

	/**
	 * Case ID:106277. 
	 * Test Case Name: Delete a Forum activity from activity stream by owner.
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test03_DeleteAForumActivityFromActivityStreamByOwner() {
		info("Test 3: Delete a Forum activity from activity stream by owner");

		String catName = "Category 106277" + getRandomNumber();
		String fmName = "Test 3 new forum" + getRandomNumber();
		String tpName = "Test 3 new topic" + getRandomNumber();

		/*
		- Connect to Intranet
		- Add a Forum activity ( add a topic in forum)
		 *Expected Outcome: 
		- The Forum activity is displayed in the activity stream		*/
		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);

		/*
		- Back to homepage
		- Move the mouse over the Forum activity
		 *Input Data: 
		 *Expected Outcome: A (X) icon is displayed in the top right panel of the activity		*/
		click(ELEMENT_HOME_PAGE);
		driver.navigate().refresh();
		homePage.checkNumberOfLineOfContent(tpName, tpName);
		waitForAndGetElement(By.linkText(tpName));

		/*
		- Click on the (X) icon
		- Click on OK button of confirmation message
		 *Input Data: 
		 *Expected Outcome: The Forum activity is removed from the activity stream		*/
		homePage.deleteActivity(tpName);

		//Delete data test
		goToForums();
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);				
	}

	/**
	 * Case ID:106295. 
	 * Test Case Name: Delete a Forum activity from space activity stream by not owner. 
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test04_DeleteAForumActivityFromSpaceActivityStreamByNotOwner() {
		info("Test 4: Delete a Forum activity from space activity stream by not owner");

		String spaceName = "Space106295";
		String tpName = "Test 4 new topic";
		String tpContent = "Test 4 new content";

		/*
		- Connect to Intranet
		- Click [Join a space]
		- Add new space
		 *Expected Outcome: 
		- Add new space successfully		*/
		manMember.adminInviteUserAndUserAcceptInvitation(true, true, false, spaceName
				, "", null, ManageAccount.userType.PUBLISHER);
		acc.userSignIn(userType.ADMIN); 

		/*
		- Go to Forum tab
		- Add a topic in forum
		 *Input Data: 
		 *Expected Outcome: 
		- The Forum activity is displayed in the activity stream		*/
		spaceMan.goToSpaceFromMySpaceNavigation(spaceName);
		spaceMan.goToSpaceMenu("Forums");		
		fmTopic.quickStartTopic(tpName, tpContent);

		/*
		- Go to [space setting]
		- Choose [member] tab
		- invite an user in list into space
		- Login by user who invited
		- Click [Join to space]
		- select space above and click [accept]
		 *Input Data: 
		 *Expected Outcome: 
		- Invited/accept user successfully		*/
		/*
		- Go to space activity or the Homepage
		- Move the mouse over the Forum activity
		 *Input Data: 
		 *Expected Outcome: Don't show (X) icon is displayed in the top right panel of the activity		*/ 
		acc.userSignIn(userType.PUBLISHER);

		spaceMan.goToSpaceFromMySpaceNavigation(spaceName);
		spaceMan.goToSpaceMenu("Activity Stream");

		waitForAndGetElement(By.linkText(tpName));
		waitForTextPresent(tpContent);
		homePage.deleteActivity(tpName,false);

		//Delete data test
		acc.userSignIn(userType.ADMIN); 
		spaceMan.goToAllSpaces();
		spaceMan.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:106294. 
	 * Test Case Name: Delete a Forum activity from space activity stream by owner. 
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test05_DeleteAForumActivityFromSpaceActivityStreamByOwner() {
		info("Test 5: Delete a Forum activity from space activity stream by owner");

		String spaceName = "Space106294";
		String tpName = "Test 5 new topic";
		String tpContent = "Test 5 new content";

		/*
		- Connect to Intranet
		- Click [Join a space]
		- Add new space
		 *Expected Outcome: 
		- Add new space successfully		*/
		spaceMan.goToAllSpaces();
		spaceMan.addNewSpace(spaceName, spaceName);

		/*
		- Go to Forum tab
		- Add a topic in forum
		 *Input Data: 
		 *Expected Outcome: 
		- The Forum activity is displayed in the activity stream		*/
		spaceMan.goToSpaceMenu("Forums");		
		fmTopic.quickStartTopic(tpName, tpContent);

		/*
		- Go to space activity or the Homepage
		- Move the mouse over the Forum activity
		 *Input Data: 
		 *Expected Outcome: A (X) icon is displayed in the top right panel of the activity		*/
		/*
		- Click on the (X) icon
		- Click on OK button of confirmation message
		 *Input Data: 
		 *Expected Outcome: The Forum activity is removed from the activity stream		*/ 
		spaceMan.goToSpaceMenu("Activity Stream");
		waitForAndGetElement(By.linkText(tpName));
		waitForTextPresent(tpContent);

		homePage.deleteActivity(tpName);
		waitForElementNotPresent(By.linkText(tpName));

		//Delete data test
		spaceMan.goToAllSpaces();
		spaceMan.deleteSpace(spaceName, 300000);		
	}

	/**
	 * Case ID:106278. 
	 * Test Case Name: Dislike a Forum activity from like icon.
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test06_DislikeAForumActivityFromLikeIcon() {
		info("Test 6: Dislike a Forum activity from like icon");

		String catName = "Category 106278";
		String fmName = "Test 6 new forum";
		String tpName = "Test 6 new topic";
		String post = "Test 6 new post";

		/*
		- Connect to Intranet
		- Go to Forum/topic
		- Add new post
		 *Expected Outcome: 
		- New post is added in topic		*/
		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);
		click(By.linkText(tpName));
		fmPost.postReply(post, post, "", "");

		/*
		- Back to the Homepage
		- Choose a forum activity and click [Like] icon
		 *Input Data: 
		 *Expected Outcome: 
		- The Fourm activity is displayed in the activity stream*like icon + number of likes to 1		*/
		click(ELEMENT_HOME_PAGE);
		info("Like activity");
		homePage.likeOrUnlikeActivity(tpName);

		/*
		- Click on like icon
		 *Input Data: 
		 *Expected Outcome: 
		- The Fourm activity is disliked by the user, the number of like is updated to -1		*/
		info("Dislike activity");
		homePage.likeOrUnlikeActivity(tpName);

		//Delete data test
		goToForums();
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);			
	}

	/**
	 * Case ID:106279. 
	 * Test Case Name: Update Forum's activity after approving a censored post in topic. 
	 * Created by khanhnt at 2013/12/09 14:03:51
	 */
	@Test
	public void test07_UpdateForumsActivityAfterApprovingACensoredPostInTopic() {
		info("Test 7: Update Forum's activity after approving a censored post in topic");

		String catName = "Category 106279" + getRandomNumber();
		String fmName = "Test 7 new forum" + getRandomNumber();
		String tpName = "Test 7 new topic" + getRandomNumber();
		String post = "censor post";
		String censorText = "censor";

		/*
		- Login by admin
		- Connect to Intranet
		- Open a Forums application
		- Click [Administration] on the Forum Administration bar and click [Censor Keywords] from the drop
		-down menu.
		- Enter censored keywords in the Censored Keywords field ( ex: test)
		- Click [Save]
		 *Expected Outcome:  Define censored keywords successfully		*/
		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		setCensorKeywords(censorText);
		fmTopic.quickStartTopic(tpName, tpName);

		/*
		- Login by normal user
		- Go to topic
		- Add posthave censored keywords at step 1
		 *Input Data: 
		 *Expected Outcome: Add post successfully		*/
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		click(By.linkText(fmName));
		click(By.linkText(tpName));
		fmPost.quickReply(post,false);

		waitForMessage(fmForum.MESSAGE_CENSOR);
		click(ELEMENT_OK_INFOR_POPUP);

		/*
		- Go to space activity or the Homepage
		 *Input Data: 
		 *Expected Outcome: Comment related to the post is removed from the activity		*/
		click(ELEMENT_HOME_PAGE);
		waitForTextNotPresent(post);

		/*
		- Login by admin
		- Click [Pending] on action bar
		- Select post and click [Approve]
		 *Input Data: 
		 *Expected Outcome: Approve s post in topic successfully		*/
		acc.userSignIn(userType.ADMIN);
		goToForums();

		goToPendingJob();
		approvePendingJob("Re: "+tpName, post, true);

		click(ELEMENT_HOME_PAGE);
		waitForTextPresent(post);

		/*
		- Go to space activity or the Homepage
		 *Input Data: 
		 *Expected Outcome: Comment corresponding to the post is displayed in comments of the activity.		*/ 
		goToForums();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);			
	}
}
