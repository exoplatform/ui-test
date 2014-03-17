package org.exoplatform.selenium.platform.forum.functional.forum.topic;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 22 Jan 2014
 */
public class Forum_Forum_Topic_ActiveSortWatchAndUnwatch extends ForumBase{

	ManageAccount acc;
	ForumManageCategory cat;
	ForumManageForum forum;
	ForumManageTopic topic;
	ForumManagePost post; 
	Button button; 
	ManageAlert alert; 
	SettingSearchPage ssPage; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		cat = new ForumManageCategory(driver, this.plfVersion);			
		forum = new ForumManageForum(driver, this.plfVersion); 
		topic = new ForumManageTopic(driver, this.plfVersion); 
		post = new ForumManagePost(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		ssPage = new SettingSearchPage(driver);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:72677.
	 * Test Case Name: Check send notify when add post in topic or forum or category that is being watched.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test01_CheckSendNotifyWhenAddPostInTopicOrForumOrCategoryThatIsBeingWatched() {
		info("Test 1: Check send notify when add post in topic or forum or category that is being watched");
		String catName = "Category 72677";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {"john"};
		String description = "Description Category 72677";
		int setPermission = 1;
		String []userGroup = {"john"};
		String []addForum = {"Forum 72677", "1", "Open", "Unlocked", "Description of forum 001"};
		String title = "Topic 72677";
		String message = "Topic 72677";
		String []userGroupTopic = {""};

		/*Step 1: Create category, forum, topic
		 *Input Data: 
		- Login as root to create new category, forum, topic
		 *Expected Outcome: 
		- Category, forum, topic are created successfully		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		goToForums();
		info("Step 1: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup, false);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title, message, "", 0, userGroupTopic, false, false);

		/*Step 2: Watch topic/forum/category
		 *Input Data: 
		- Right click on created topic/forum/category and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Topic/forum/category is being watched		*/
		info("Step 2: Watch topic/forum/category");
		topic.watchItem(true);
		alert.acceptAlert();

		/*Step 3: Add post into topic/forum/category that is being watched
		 *Input Data: 
		- Add post into topic of above forum/category that is being watched
		 *Expected Outcome: 
		- Add new post successfully
		- A notify email about new post are sent to registered email address at step 2		*/ 
		info("Add post into topic");
		click(By.linkText(title));
		post.postReply(title, message, "", "", "");

		//Check email
		Utils.pause(1000);
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), title);

		// Clean data test
		switchToParentWindow();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	} 

	/**
	 * Case ID:72755.
	 * Test Case Name: Check send notify when add private post in topic or forum or category that is being watched.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test02_CheckSendNotifyWhenAddPrivatePostInTopicOrForumOrCategoryThatIsBeingWatched() {
		info("Test 2: Check send notify when add private post in topic or forum or category that is being watched");
		String catName = "Category 72755";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 72755";
		int setPermission = 1;
		String []userGroup = {""};
		String []addForum = {"Forum 72755", "1", "Open", "Unlocked", "Description of forum 001"};
		String title = "Topic 72755";
		String message = "Topic 72755";
		String []userGroupTopic = {""};

		/*
		- Login as root to create new category, forum, topic
		 *Input Data: 
		 *Expected Outcome: 
		- Category, forum, topic are created successfully		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		goToForums();
		info("Step 1: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title, message, "", 0, userGroupTopic, false, false);

		/*
		- Login by another user
		- Right click on created category/forum/topic and select [Watch]
		 *Input Data: 
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Category/forum/topic is being watched		*/
		info("Step 2: Watch topic/forum/category");
		topic.watchItem(true);
		alert.acceptAlert();
		acc.signOut();

		/*
		- Login by the normal user
		- Add a private post into topic above or into a topic of forum/category above
		 *Input Data: 
		 *Expected Outcome: 
		- Private post is added
		- Notify mail is sent to registered email addresses (if they register at step 2). Only watching user receive email, the poster will not receive email.		*/ 
		acc.userSignIn(userType.DEVELOPER);
		info("Add post into topic");
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		post.privatePost(title, title, message, "", "", "");
		acc.signOut();

		//Check email
		acc.userSignIn(userType.ADMIN);
		Utils.pause(1000);
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), message);

		// Clean data test
		switchToParentWindow();
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:72824.
	 * Test Case Name: Check send notify after adding new post and it's required for approval.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test03_CheckSendNotifyAfterAddingNewPostAndItsRequiredForApproval() {
		info("Test 3: Check send notify after adding new post and it's required for approval");
		String catName = "Category 72824";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 72824";
		int setPermission = 0;
		String []userGroup = {""};
		String []addForum = {"Forum 72824", "1", "Open", "Unlocked", "Description of forum 001"};
		String title = "Topic 72824";
		String message = "Topic 72824";

		/*Step 1: Create category, forum, topic
		 *Input Data: 
		- Login as root to create new category, forum
		- Create new topic with checked 'Moderation post' option
		 *Expected Outcome: 
		- Category, forum, topic are created successfully		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		goToForums();
		info("Step 1: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title, message, "", 0, userGroup, false, false, false, false, false, true, false);

		/*Step 2: Watch category/forum/topic
		 *Input Data: 
		- Login by another user
		- Right click on created category/forum/topic and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Category/forum/topic is being watched		*/
		info("Step 2: Watch topic/forum/category");
		topic.watchItem(true);
		alert.acceptAlert();
		acc.signOut();

		/*Step 3: Add post
		 *Input Data: 
		- Login by the normal user
		- Add new post into topic with 'Moderation post' option created at step 1
		 *Expected Outcome: 
		- New post is added
		- It's visible for administrator, moderator only with pending for approval status
		- Notify mail is only sent to registered email at step 2 when the pending topic is approved		*/ 
		info("Step 3: Add post");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		info("Add post into topic");
		post.postReply(title, message, "", "", "");
		acc.signOut();
		info("Admin check moderator");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));		
		post.approvePost(title);

		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), title);

		// Clean data test
		switchToParentWindow();
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:72879.
	 * Test Case Name: Check send notify after adding new post and it's pending for being censored.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test04_CheckSendNotifyAfterAddingNewPostAndItsPendingForBeingCensored() {
		info("Test 4: Check send notify after adding new post and it's pending for being censored");
		String catName = "Category 72879";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 72879";
		int setPermission = 0;
		String []userGroup = {""};
		String []addForum = {"Forum 72879", "1", "Open", "Unlocked", "Description of forum 001"};
		String title = "Topic 72879";
		String message = "Topic 72879";
		String key = "Test"; 

		/*Step 1: Define censored keyword
		 *Input Data: 
		- Login by the administrator to define word(s) that need to be approved
		 *Expected Outcome: 
		- Censored keyword(s) is defined		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		goToForums(); 
		setCensorKeywords(key);

		/*Step 2: Create topic
		 *Input Data: 
		- Login by the Administrator/Moderator create new category, forum, thread
		 *Expected Outcome: 
		- Category, forum, thread are created		*/
		info("Step 2: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title, message, "", 0, userGroup, false, false);

		/*Step 3: Add watching
		 *Input Data: 
		- Right click on created category/forum/topic and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Category/forum/topic is being watched		*/
		info("Step 3: Watch topic/forum/category");
		topic.watchItem(true);
		alert.acceptAlert();
		acc.signOut();

		/*Step 4: Add new post on category/ forum/topic that is being watched
		 *Input Data: 
		- Use normal user to add new post contains defined censored keyword above in watched category/forum/topic 
		 *Expected Outcome: 
		- Post is added with 'Pending for censor' status, no one else can see this post before it's censored except administrator/moderator
		- Notify mail is only sent to registered email at step 2 when the pending topic is censored.		*/ 
		info("Step 4: Add new post on category/ forum/topic that is being watched");
		acc.userSignIn(userType.DEVELOPER);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		info("Add post into topic");
		post.postReply(key, key, "", "", "");
		alert.acceptAlert();
		acc.signOut();

		info("Admin check censor key");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		post.censorPost(key);

		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), title);

		// Clean data test
		switchToParentWindow();
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:72929.
	 * Test Case Name: Check send notify in case the user no longer have view permission on the category or forum or topic he added watch.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test05_CheckSendNotifyInCaseTheUserNoLongerHaveViewPermissionOnTheCategoryOrForumOrTopicHeAddedWatch() {
		info("Test 5: Check send notify in case the user no longer have view permission on the category or forum or topic he added watch");
		String catName = "Category 72929";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 72929";
		int setPermission = 0;
		String []userGroup = {""};
		String []addForum = {"Forum 72929", "1", "Open", "Unlocked", "Description of forum 001"};
		String title = "Topic 72929";
		String message = "Topic 72929";
		String []userGroupEdit = {"john"};
		String titleEdit = "Edit Topic 72929";
		String messageEdit = "Edit 72929";
		String catNameEdit = "Update 72929";
		int setPermissionEdit = 1;

		/*Step 1: Create category, forum, topic
		 *Input Data: 
		- Login as root to create new category, forum
		 *Expected Outcome: 
		- Category, forum, topic are created successfully		*/

		goToForums();
		info("Step 1: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title, message, "", 0, userGroup, false, false);
		acc.signOut();

		/*Step 2: Watch category/forum/topic
		 *Input Data: 
		- Login by another user
		- Right click on created category/forum/topic and select [Watch]
		- Click [Save]
		 *Expected Outcome: 
		- Category/forum/topic is being watched		*/
		acc.userSignIn(userType.DEVELOPER);
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		info("Step 2: Watch topic/forum/category");
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		topic.watchItem(true);
		alert.acceptAlert();
		acc.signOut();

		/*Step 3: Check send mail in case the user still have view permission on the category/forum/topic he added watch
		 *Input Data: 
		- Add post in to the category/forum/topic which is being watched
		 *Expected Outcome: 
		- Notify mail is sent to registered email of the user who added watch		*/
		info("User Admin add post");
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		post.postReply(title, message, "", "", "");
		acc.signOut();

		//User 2 check email 
		info("User 2 check email.");
		acc.userSignIn(userType.DEVELOPER);
		Utils.pause(500);
		info("Check email");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title)), message);
		
		Utils.pause(500);
		switchToParentWindow();
		acc.signOut();

		/*Step 4: Do set view permission for the user on the category/forum/topic he added watch
		 *Input Data: 
		- Login as root to restrict view permission on the category which the being watched forum/topic belong to
		- Or Login as root/ moderator or the creator to limit view permission on the being watched topic
		 *Expected Outcome: 
		- The user at step 2 no longer have view permission on the forum/topic he added watch		*/
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catName));
		cat.editCategoryInForum(catNameEdit, order, chooseRestricted, restricted, description, setPermissionEdit, userGroupEdit, false, false, false, true);

		/*Step 5: Check send mail in case the user no longer have view permission on the forum/topic he added watch
		 *Input Data: 
		- Add post in to the category/forum/topic which is being watched but the user no longer have view permission
		 *Expected Outcome: 
		- Notify mail is not sent to the user who added watch on the forum/topic but do not have view permission any more.		*/ 
		click(By.linkText(addForum[0]));
		click(By.linkText(title));
		info("Add post2 into topic");
		post.postReply(titleEdit, messageEdit, "", "", "");
		acc.signOut();

		//check email	
		info("User2 check notify email again");
		acc.userSignIn(userType.DEVELOPER);
		Utils.pause(500);
		//		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		info("Go to gmail");
		switchToNewWindow();
		Utils.pause(5000);
		waitForElementNotPresent(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catNameEdit).replace("${forum}", addForum[0]).replace("${topic}", title)));

		// Clean data test
		switchToParentWindow();
		acc.signOut();
		acc.userSignIn(userType.ADMIN);
		goToForums();
		click(By.linkText(catNameEdit));
		cat.deleteCategoryInForum(catNameEdit, true);	
	}

	/**
	 * Case ID:73014.
	 * Test Case Name: Check send notify when move post in topic or forum or category that is being watched.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/22 16:02:46
	 */
	@Test
	public  void test06_CheckSendNotifyWhenMovePostInTopicOrForumOrCategoryThatIsBeingWatched() {
		info("Test 6: Check send notify when move post in topic or forum or category that is being watched");
		String catName = "Category 73014";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 73014";
		int setPermission = 0;
		String []userGroup = {""};
		String []addForum = {"Forum 73014", "1", "Open", "Unlocked", "Description of forum 001"};
		String title1 = "Topic1";
		String title2 = "Topic2";
		String titlePost = "Post 73014";
		String message = "Message 73014";
		String notify = "Your post: \"Post 73014\" has been moved to topic \"Topic2\"";

		/*Step 1: Create categories, forums, topics and posts into
		 *Input Data: 
		- Login by the administrator to create new categories, forums, topics and posts into
		 *Expected Outcome: 
		- Categories, forums, topics and posts are created successfully		*/
		acc.updateUserProfile(null, null, null,EMAIL_ADDRESS1);
		goToForums();
		info("Step 1: Create forum");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title1, title1, "", 0, userGroup, false, false);
		topic.startTopic(title2, title2, "", 0, userGroup, false, false);
		info("Add post into topic");
		click(By.linkText(title1));
		post.postReply(titlePost, message,"", "", "");

		/*Step 2: Watch topic/forum/category
		 *Input Data: 
		- Right click on created category/forum/topic and select [Watch]
		 *Expected Outcome: 
		- Alert message is shown to inform watching successfully.
		- Category/Forum/topic is being watched*/		
		info("Step 3: Watch topic/forum/category");
		topic.watchItem(true);
		alert.acceptAlert();		

		/*Step 3: Move post
		 *Input Data: 
		- Move post in category/forum/topic which watching to a destination topic
		 *Expected Outcome: Post is moved successful
		- Move notify is sent to post's author that is moved and watcher*/		 
		info("Move post");
		post.movePost(titlePost, title2);

		//Check email
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}",catName).replace("${forum}", addForum[0]).replace("${topic}", title2)), notify);

		// Clean data test
		switchToParentWindow();
		goToForums();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}

	/**
	 * Case ID:72353.
	 * Test Case Name: Activate topic.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/24 16:58:00
	 */
	@Test(groups="pending")
	public  void test07_ActivateTopic() {
		info("Test 7: Activate topic");
		/*Step 1: Create categories, forums, topics
		 *Input Data: 
		- Login as root
		- Create categories, forums, topic
		 *Expected Outcome: Categories, forums and topics are created successful		*/

		/*Step 2: Run Automating prune
		 *Input Data: 
		- Goto Forum administration form/Auto
		-Prune tab: setting pruned for added forum at step 1 and Run its
		 *Expected Outcome: Pruned topic in forum is displayed with a note: â€œ(This topic is Inactivingâ€ beside topic's title when view by administrator/forum's moderator		*/

		/*Step 3: View Inactive topic by normal user
		 *Input Data: 
		- Login by normal user 
		- Open forum which Run pruned at step 2
		 *Expected Outcome: Pruned topic in forum isn't displayed in list of selected forum		*/

		/*Step 4: Active topic
		 *Input Data: 
		- Login as root / forum's moderator
		- Tick on Inactive topic(s)
		- Click Moderation then select Active
		 *Expected Outcome: 
		- Topic(s) is activated
		- The note alert that it is waiting for active beside its title in topic list disappears
		- Actived topic(s) becomes visible with everyone		*/ 
	}
	/**
	 * Case ID:72354.
	 * Test Case Name: Sort topic by rating.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/24 17:26:42
	 */
	@Test
	public  void test07_SortTopicByRating() {
		info("Test 2: Sort topic by rating");
		String catName = "Category 72354";
		String order = "1";
		int chooseRestricted = 1;
		String []restricted = {""};
		String description = "Description Category 72354";
		int setPermission = 0;
		String []userGroup = {""};
		String []addForum = {"Forum 72354", "1", "Open", "Unlocked", "Description of forum 001"};
		String title1 = "Topic1";
		String title2 = "Topic2";

		/*Step 1: Create categories, forums, topics
		 *Input Data: 
		- Login as root
		- Create categories, forums, topic
		 *Expected Outcome: Categories, forums and topics are created successful		*/
		goToForums();
		info("Step 1: Create categories, forums, topics");
		cat.goToAddCategory();
		cat.addNewCategoryInForum(catName, order, chooseRestricted, restricted, description, setPermission, userGroup);
		forum.goToAddForum();
		forum.inputDataInAddForumTab_addForum(catName, addForum);
		button.save();
		topic.startTopic(title1, title1, "", 0, userGroup, false, false);

		/*Step 2: Show vote form
		 *Input Data: 
		- Open topic which want to vote
		- Click Rate button in topic navigation bar
		 *Expected Outcome: 
		-Vote form is displayed with 5 gray stars by default.		*/

		/*Step 3: Choose number of star to vote
		 *Input Data: 
		- Move the cursor on gray star till it turns to yellow, click on the last yellow star
		 *Expected Outcome: 
		- Topic is voted with number stars is the number of yellow stars		*/
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(By.linkText(title1));
		topic.voteTopic(5, true);		

		/*Step 4: open forum
		 *Input Data: Open forum which contains topics voted
		 *Expected Outcome: Voted topics are listed with yellow stars		*/
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		waitForAndGetElement(ssPage.ELEMENT_RESULT_FORUM_VOTE);

		/*Step 5: Sort topic by rating
		 *Input Data: In Rating column: click on Rate icon 
		 *Expected Outcome: Topics are sorted by rate in ascending/descending		*/ 
		topic.startTopic(title2, title2, "", 0, userGroup, false, false);
		click(By.linkText(catName));
		click(By.linkText(addForum[0]));
		click(topic.ELEMENT_RATE_RATING);	

		// Clean data test
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);	
	}	
}