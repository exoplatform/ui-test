package org.exoplatform.selenium.platform.forum.functional.forum.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * update chinhdtt
 */
public class Forum_Forum_Forum_WatchUnWatch extends ForumBase {

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumManageTopic fmTopic;
	ForumManagePost fmPost;
	NavigationToolbar nav;
	PeopleProfile pepPro;
	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver);
		fmForum = new ForumManageForum(driver);
		fmTopic = new ForumManageTopic(driver);
		fmPost = new ForumManagePost(driver);
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
		fmForum = new ForumManageForum(driver);
		nav = new NavigationToolbar(driver, this.plfVersion);
		pepPro = new PeopleProfile(driver);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Case ID:106230. 
	 * Test Case Name: Check send notify after adding new topic and it's in pending for censor.
	 * Created by khanhnt at 2013/12/09 14:31:42
	 */
	@Test
	public void test01_CheckSendNotifyAfterAddingNewTopicAndItsInPendingForCensor() {
		info("Test 1: Check send notify after adding new topic and it's in pending for censor");

		String catName = "Category 106230";
		String fmName = "Test 1 new forum";
		String tpName = "Test 1 test censor";
		String censorText = "test censor";

		/*Step 1: Create category, forum
		 *Expected Outcome: 
		- Category, forum are created successfully		*/
		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		setCensorKeywords(censorText);

		/*Step 2: Watch forum/category
		 *Input Data: 
		- Login by the normal user
		- Right click on created forum/category and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Forum/category is being watched		*/
		acc.userSignIn(userType.PUBLISHER);
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, EMAIL_ADDRESS1);
		pepPro.saveCancelUpdateInfo(true);
		goToForums();		
		click(By.linkText(fmName));		
		watchItem(true);

		/*Step 3: Add topic into forum/category that is being watched
		 *Input Data: 
		- Alert message is shown to inform that topic is pending for censor.
		- It's visible for administrator, moderator only with “pending for censor” status
		- Notify mail is only sent to registered email at step 2 when the pending topic is censored
		 *Expected Outcome: 
		- Add new topic with censor content into above forum		*/ 		
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);
		waitForMessage(fmForum.MESSAGE_CENSOR);
		click(ELEMENT_OK_INFOR_POPUP);

		info("Admin censor topic");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(fmName));
		waitForTextPresent(fmForum.CENSORED_TITLE.replace("${title}", tpName));

		//approve censor topic
		fmTopic.censorTopic(tpName);		

		info("Check if e-mail is sent");

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmName).replace(" ${topic}", tpName)), tpName);

		//Delete data test
		switchToParentWindow();		
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		

		info("Nomal user delete infor");
		acc.signOut();
		acc.signIn(DATA_USER2, DATA_PASS);
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "mary.williams@acme.exoplatform.com");
		pepPro.saveCancelUpdateInfo(true);
	}

	/**
	 * Case ID:105699. 
	 * Test Case Name: Check send notify after adding new topic
	 * into forum/category which is watching and it's required for approval.
	 * Created by khanhnt at 2013/12/09 14:31:42
	 */
	@Test
	public void test02_CheckSendNotifyAfterAddingNewTopicIntoForumcategoryWhichIsWatchingAndItsRequiredForApproval() {
		info("Test 2: Check send notify after adding new topic into forum/category which is watching  and it's required for approval");
		String catName = "Category 105699";
		String[] fmName = {"Test 2 new forum",null,null,null,null};
		String tpName = "Test 2 new topic";

		/*Step 1: Create category, forum
		 *Expected Outcome: 
		- Category, forum are created successfully		*/
		goToForums();
		fmCat.addNewCategoryInForum(catName, null, 0, null, "", 0, null);
		fmForum.addForum(catName, fmName, true, null, null, true, 0, null);

		/*Step 2: Watch forum/category
		 *Input Data: 
		- Login by the normal user
		- Right click on created forum/category and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Forum/category is being watched		*/
		acc.userSignIn(userType.PUBLISHER);
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, EMAIL_ADDRESS1);
		pepPro.saveCancelUpdateInfo(true);
		
		goToForums();		
		click(By.linkText(fmName[0]));
		fmForum.watchItem(true);

		/*Step 3: Add topic into forum/category that is being watched
		 *Input Data: 
		- Add new topic into above forum
		 *Expected Outcome: 
		- Alert message is shown to inform that topic is pending for approval.
		- It's visible for administrator, moderator only with â€œpending for approval status
		- Notify mail is only sent to registered email at step 2 when the pending topic is approved		*/ 
		fmTopic.startTopic(tpName, tpName, null, 0, null, true, true);	
		waitForMessage(fmTopic.MESSAGE_WAIT_APPROVE);
		click(ELEMENT_OK_INFOR_POPUP);

		info("Approval topic");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(fmName[0]));
		waitForTextPresent(fmTopic.APPROVE_TITLE.replace("${title}", tpName));		
		click(By.linkText(tpName));		
		fmTopic.approveTopic();

		info("Check if e-mail is sent");		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmName[0]).replace(" ${topic}", tpName)), tpName);

		//Delete data test
		switchToParentWindow();		
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		

		acc.userSignIn(userType.PUBLISHER);
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "mary.williams@acme.exoplatform.com");
		pepPro.saveCancelUpdateInfo(true);
	}

	/**
	 * Case ID:106015. 
	 * Test Case Name: Check send notify after move topic in forum/category that is being watched.
	 * Created by khanhnt at 2013/12/09 14:31:42
	 */
	@Test
	public void test03_CheckSendNotifyAfterMoveTopicInForumcategoryThatIsBeingWatched() {
		info("Test 3: Check send notify after move topic in forum/category that is being watched");
		String catName = "Category 106015" + getRandomNumber();
		String fmName = "Test 3 new forum";
		String fmDes = "Test 3 Destination";
		String tpName = "Test 3 new topic";

		/*Step 1: Create categories, forums, topics
		 *Expected Outcome: 
		- Categories, forums, topics are created successfully		*/
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, EMAIL_ADDRESS1);
		pepPro.saveCancelUpdateInfo(true);

		goToForums();
		fmForum.addCategoryForum(catName, fmName);
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);

		/*Step 2: Watch forum/category
		 *Input Data: 
		- Right click on created category/forum and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Category/Forum is being watched		*/
		fmForum.watchItem(true);

		/*Step 3: Move topic
		 *Input Data: 
		- Move topic in category/forum which watching to a destination forum
		 *Expected Outcome: 
		- Topic is moved successful
		- Move notify is sent to topic's author that is moved and watcher		*/ 		
		goToForumHome();
		click(By.linkText(catName));
		fmForum.quickAddForum(fmDes);
		goToForumHome();
		click(By.linkText(catName));
		click(By.linkText(fmName));
		click(By.linkText(tpName));
		fmTopic.moveTopic(tpName, fmDes);

		info("Check if e-mail is sent");		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmDes).replace("${topic}", tpName)), fmDes);

		//Detele data test
		switchToParentWindow();		
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "john.smith@acme.exoplatform.com");
		pepPro.saveCancelUpdateInfo(true);

	}

	/**
	 * Case ID:105610. 
	 * Test Case Name: Check send notify when add new topic into a forum that is being watched. 
	 * Created by khanhnt at 2013/12/09 14:31:42
	 */
	@Test
	public void test04_CheckSendNotifyWhenAddNewTopicIntoAForumThatIsBeingWatched() {
		info("Test 4: Check send notify when add new topic into a forum that is being watched");
		String catName = "Category 105610";
		String fmName = "Test 4 new forum";
		String tpName = "Test 4 new topic";

		/*Step 1: Create category, forum
		 *Expected Outcome: 
		- Category, forum are created successfully		*/
		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, EMAIL_ADDRESS1);
		pepPro.saveCancelUpdateInfo(true);

		goToForums();
		fmForum.addCategoryForum(catName, fmName);		

		/*Step 2: watch forum
		 *Input Data: 
		- Right click on created forum and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Forum is being watched		*/
		fmForum.watchItem(true);

		/*Step 3: Add topic into forum/category that is being watched
		 *Input Data: 
		- Add new topic into above forum
		 *Expected Outcome: 
		- Add new topic successfully
		- A notify email about new topic created in this forum is sent to registered email address		*/ 
		fmTopic.startTopic(tpName, tpName, "", 0, null, true, true);

		info("Check if e-mail is sent");		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}",
				fmName).replace("${topic}", tpName)), tpName);

		//Delete data test
		switchToParentWindow();		
		goToForumHome();
		click(By.linkText(catName));
		fmCat.deleteCategoryInForum(catName, true);		

		nav.goToMyProfile();
		info("edit profile");
		click(pepPro.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("edit info");
		pepPro.updateBasicInformation(null, null, "john.smith@acme.exoplatform.com");
		pepPro.saveCancelUpdateInfo(true);
	}
}