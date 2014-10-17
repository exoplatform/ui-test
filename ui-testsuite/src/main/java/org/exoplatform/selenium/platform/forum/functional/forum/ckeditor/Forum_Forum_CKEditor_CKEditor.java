package org.exoplatform.selenium.platform.forum.functional.forum.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.CKeditor;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * by quynhpt
 * date 14/10/2014
 */
public class Forum_Forum_CKEditor_CKEditor extends ForumBase {

	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	ManageAccount magAcc;
	ForumManageCategory mngCat;
	NavigationToolbar navToolBar;
	HomePageActivity homeActi;
	CKeditor cke;

	String category = "";
	String forum = "";
	String topic = "";
	String post = "";
	String newPost = "";
	String editTopic = "";
	String quotePost = "";
	String tex_message = "";
	String tex_mess_forw = "";

	@BeforeTest
	public void setBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		info("Login with " + DATA_USER1);
		mngFru = new ForumManageForum(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPost = new ForumManagePost(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver);
		homeActi = new HomePageActivity(driver, this.plfVersion);
		cke = new CKeditor(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);

	}

	@BeforeMethod
	public void setBeforeMethod() {
		// Create a new name for category, forum, post
		setUpNameForCatForTopicPost();
	}

	@AfterTest
	public void setAfterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * set the name for all parameters
	 */
	private void setUpNameForCatForTopicPost() {
		// Setup names for category, forum, topic, post and newpost
		String number_Random = getRandomNumber();
		category = "Category_" + number_Random;
		forum = "Forum_" + number_Random;
		topic = "Topic_" + number_Random;
		post = "Post_" + number_Random;
		newPost = "New_post_" + number_Random;
		editTopic = "EditTopic_" + number_Random;
		quotePost = "QuotePost_" + number_Random;
		tex_message = "PrivateMessage_" + number_Random;
		tex_mess_forw = "ForwardPrivMessage_" + number_Random;
	}

	/************************* CKEditor in Forum ***********************/
	/**
	 * TC_102593: Start new topic
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Decorate content
	 * 7. Select other fields
	 * 8. Click Preview/Submit
	 * Expected:
	 * 3.Forum to start topic is shown
	 * 6.Message of topic is filled & decorated
	 * 8. Topic displayed as decorated
	 */
	@Test(enabled = true)
	public void test01_StartNewTopic() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForum(category, forum);
		// add a topic that has description is aligned in center position
		mngTopic.addATopicWithDecorate(topic, topic, "Center");

		// open the topic
		click(By.linkText(topic));

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", topic));

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", topic));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);

	}

	/**
	 * TC_102595: Edit a topic
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Decorate content
	 * 7. Select other fields
	 * 8. Click Preview/Submit
	 * Expected:
	 * 3.Forum to start topic is shown
	 * 6.Message of topic is filled & decorated
	 * 8. Topic displayed as decorated
	 */
	@Test(enabled = true)
	public void test02_EditATopic() {

		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the created topic
		click(By.linkText(topic));
		// Edit topic's description at align left position
		mngTopic.editATopicWithDecorate(editTopic, editTopic, "Align Right");

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", editTopic));

		info("-- Verify the description of the topic is decorated in right position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_RIGHT.replace(
						"${descripTopic}", editTopic));
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102596: Open Topic from activity stream
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Go to intranet activity stream or space activity stream
	 * 8. Click on link of this topic
	 * Expected:
	 * 6.The topic is created
	 * 7. An activity is published on intranet/space activity stream
	 * 8.Topic is open & well displayed in its forum
	 */
	@Test(enabled = true)
	public void test03_OpenATopicFromActivityStream() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// go to intranet activity stream
		info("-- Go to activity stream of the intranet page --");
		navToolBar.goToHomePage();

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				By.xpath(homeActi.ELEMENT_TOPIC_TITLE.replace("${nameTopic}",topic)));
		
		// Click on the link of the topic
		click(By.xpath(homeActi.ELEMENT_TOPIC_TITLE.replace("${nameTopic}",
				topic)));

		info("-- Verify the name of the topic on forum porlet --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", topic));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102597: Post a reply *
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Open this topic
	 * 8. Click Post reply
	 * 9. Fill & decorate message
	 * 10. Select other fields
	 * 11. Click Preview/Submit
	 * Expected:
	 * 6.Topic is created
	 * 9.Message of topic is filled & decorated
	 * 11. Post is displayed & decorated.
	 */
	@Test(enabled = true)
	public void test04_PostAReply() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the created topic
		click(By.linkText(topic));
		mngPost.postReplyWithDecorate(post, post, "Center", "", "");

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", post));

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", post));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102598: Edit a post
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Open this topic
	 * 8. Click Post reply
	 * 9. Fill & decorate message
	 * 10. Select other fields
	 * 11. Click Preview/Submit
	 * 12. Click [Edit] of the post
	 * 13. Edit Post content & decorate it
	 * 14. Click Preview/Submit
	 * Expected:
	 * 6.Topic is created
	 * 9.Message of topic is filled & decorated
	 * 11. Post is displayed & decorated.
	 * 14. Post is displayed & decorated
	 */
	@Test(enabled = true)
	public void test05_EditAPost() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the created topic
		click(By.linkText(topic));
		mngPost.postReplyWithDecorate(post, post, "Center", "", "");
        Utils.pause(1000);
		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", post))
				.getText().contains(post);

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", post));

		// Edit the post
		mngPost.editPostWithDecorate(driver, post, newPost, newPost, newPost,
				"Align Right", "", "");

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", newPost));

		info("-- Verify the description of the topic is bolded--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_RIGHT.replace(
						"${descripTopic}", newPost));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102599: Quote a post
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Open this topic
	 * 8. Click Post reply
	 * 9. Fill & decorate message
	 * 10. Select other fields
	 * 11. Click Preview/Submit
	 * 12. Click [Quote] of the post or quote of topic
	 * 13. Fill content & decorate it
	 * 14. Click Preview/Submit
	 * Expected:
	 * 6.Topic is created
	 * 9.Message of topic is filled & decorated
	 * 11. Post is displayed & decorated.
	 * 14. Post quote is displayed & decorated.
	 */
	@Test(enabled = true)
	public void test06_QuoteAPost() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the created topic
		click(By.linkText(topic));
		mngPost.postReplyWithDecorate(post, post, "Center", "", "");

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", post));

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", post));

		// Quote a post and decorate the text in align right
		mngPost.quotePostWithDecorate(post, quotePost, quotePost, "Align Right");

		info("-- Verify the name of the quote a post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", quotePost));

		info("-- Verify the description of the quote is decorated in right position--");
		waitForAndGetElement(
				ELEMENT_QUOTE_DESCRIPTION_DECORATED_RIGHT.replace(
						"${nameDescQuote}", quotePost).replace(
						"${nameDescPost}", post));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);

	}

	/**
	 * TC_102600: Private Post
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Open this topic
	 * 8. Click on Private Post button
	 * 9. Fill & decorate message
	 * 10. Select other fields
	 * 11. Click Preview/Submit
	 * Expected:
	 * 6.Topic is created
	 * 9.Message of topic is filled & decorated
	 * 11. Private Post is displayed & decorated.
	 */
	@Test(enabled = true)
	public void test08_PrivatePost() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the created topic
		click(By.linkText(topic));
		mngPost.postReplyWithDecorate(post, post, "Center", "", "");

		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", post));

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", post));
		// click on Private Post
		mngPost.privatePostWithDecorate(post, newPost, newPost, "Align Right",
				"", "");

		info("-- Verify the name of the quote a new post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", newPost));
		info("-- Verify the description of the topic is decorated in right position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_RIGHT.replace(
						"${descripTopic}", newPost));

		info("-- Verify the text :'Post Private' is shown beside on the post's tilte-----");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_PRIVATE.replace("${titlePost}", newPost));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);

	}

	/**
	 * TC_102601: Private message
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1. Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Click Private Messages on top right
	 * 4. Select Compose New Message tab
	 * 5. Fil , send to, title
	 * 6. Fill message content
	 * 7. Decorate content
	 * 8. Select other fields
	 * 9. Click Send
	 * 10.Click Sent Messages tab
	 * 11.Click message above
	 * 12.Or Login as sent user
	 * 13.Go to Forum to see private message
	 * Expected:
	 * 9. Message of topic is filled & decorated.Message is sent
	 * 13.Message is displayed as decorated.
	 */
	@Test(enabled = true)
	public void test07_PrivateMessage() {
		mngFru.goToForums();

		// open the private message popup and select Compose New Message
		composePrivateMessagewithDecorate("mary", tex_message, tex_message,
				"Center");

		info("-- Verify the name of the message sent --");
		waitForAndGetElement(
				ELEMENT_PRIVATE_MESSAGE_NAME_SENT.replace("${nameReceiver}",
						"mary").replace("${nameMess}", tex_message));

		// select "Sent Messages" tab
		forwardPrivateMessageWithDecorate(tex_message, "demo", tex_mess_forw,
				"Align Right");

		info("-- Verify the name of the message forwarded --");
		waitForAndGetElement(
				ELEMENT_PRIVATE_MESSAGE_NAME_SENT.replace("${nameReceiver}",
						"demo").replace("${nameMess}", tex_mess_forw));
		
		info("-- The test is successfull--");
		
		// delete the data test
		deletePrivateMessage(tex_mess_forw);
		deletePrivateMessage(tex_message);
		magAcc.signOut();
		
		//login with mary account to delete private message
		magAcc.signIn(DATA_USER2, DATA_PASS);
		mngFru.goToForums();
		goToPrivateMessage();
		deletePrivateMessage(tex_message);
		
		magAcc.signOut();
		//login with demo account to delete private message
		magAcc.signIn(DATA_USER4, DATA_PASS);
		mngFru.goToForums();
		goToPrivateMessage();
		deletePrivateMessage(tex_mess_forw);
	}

	/**
	 * TC_102602: Notification
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1. Log in as admin
	 * 2. Go to intranet Forum
	 * 3. Click Administration/ Notifications
	 * 4. Select New Posts Notification or Moved Notification
	 * 5. Edit or decorate notification content
	 * 6. Click Save
	 * 7. Create a category
	 * 8. Create a forum
	 * 9. Create a topic
	 * 10.Click [Watch]
	 * 11.Open the topic
	 * 12.Add a post to the topic
	 * Expected:
	 * 12.A notification is sent via email with content change as setup at step
	 * 1
	 */
	@Test(enabled = true)
	public void test09_CheckNotificaiton() {
		String tex_content = "ChangeContentNotificaiton_" + getRandomNumber();
		// update address email
		magAcc.updateUserProfile(null, null, null, EMAIL_ADDRESS1);
		// Change content of notification
		info("Go to Administration->Notification");
		mngFru.goToForums();
		changeNotifications(false, "", tex_content);

		// Create a category, a forum, a topic on Foum application
		info("Add a Category, Forum and Post");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// right click to watch the forum
		mngFru.watchItem(true);

		// open the topic
		click(By.linkText(topic));
		mngPost.postReplyWithDecorate(post, post, "Center", "", "");

		info("Check if e-mail is sent");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(
				By.xpath(ELEMENT_GMAIL_EMAIL.replace("${category}", category)
						.replace("${forum}", forum).replace("${topic}", post)),
				post);
		info("-- The test is successfull--");
		// Delete data test
		switchToParentWindow();
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);

		magAcc.updateUserProfile(null, null, null,
				"john.smith@acme.exoplatform.com");
	}

	/**
	 * TC_102603: Reply a Topic from activity stream
	 * Preconditions: For intranet forum, category & forum are created. A space
	 * exists.
	 * Steps:
	 * 1.Log in as admin
	 * 2. Go to intranet Forum or space forum
	 * 3. Start new topic
	 * 4. Fill title
	 * 5. Fill message content
	 * 6. Click Preview/Submit
	 * 7. Go to intranet activity stream or space activity stream
	 * 8. Click on "Replly" link
	 * 9. Fill & decorate message
	 * 10. Save/ Submit
	 * Expected:
	 * 6.The topic is created
	 * 7. An activity is published on intranet/space activity stream
	 * 8.New Post popup is shown.New Post is created & well displayed as
	 * decorated
	 * 
	 */
	@Test(enabled = true)
	public void test10_ReplyATopicFromActivityStream() {
		// Create a category, a forum, a topic on Foum application
		info("Add a topic");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// go to intranet activity stream
		info("-- Go to activity stream of the intranet page --");
		navToolBar.goToHomePage();
        Utils.pause(1000);
		
		// Click on Reply link of the topic
		click(By.xpath(homeActi.ELEMENT_TOPIC_REPLY.replace("${title}", topic)));

		// input new content to new post
		mngPost.postReplyWithDecorateFromActivityStream(newPost, newPost,
				"Center", "", "");
        Utils.pause(1000);
		info("-- Verify the name of the topic --");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", newPost));

		info("-- Verify the description of the topic is decorated in center position--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION_DECORATED_CENTER.replace(
						"${descripTopic}", newPost));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}
}
