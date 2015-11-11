package org.exoplatform.selenium.platform.forum.functional.forum.otheraction;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;

import org.testng.annotations.*;

	/**
	* @author tult
	* @date 3rd April 2015
	*/
	public class Forum_Forum_OtherAction_GoToLastReadPost extends ForumBase{
		ManageAccount acc;
		ForumManageCategory fmCat;
		ForumManageForum fmForum;
		ForumManageTopic fmTopic;
		ForumManagePost fmPost;

		@BeforeMethod
		public void setUpBeforeTest() {
			initSeleniumTest();
			driver.get(plfURL);
			fmCat = new ForumManageCategory(driver,this.plfVersion);
			fmForum = new ForumManageForum(driver,this.plfVersion);
			fmTopic = new ForumManageTopic(driver,this.plfVersion);
			fmPost = new ForumManagePost(driver,this.plfVersion);
			acc = new ManageAccount(driver,this.plfVersion);
			acc.signIn(DATA_USER1, DATA_PASS);
			button = new Button(driver,this.plfVersion);
			alert = new ManageAlert(driver, this.plfVersion);
			goToForums();
		}

		@AfterMethod
		public void afterTest() {
			driver.manage().deleteAllCookies();
			driver.quit();
		}


	/**
	* Case ID:118720.
	* Test Case Name: Check Go to last read post when Forum has topics that is read.
	*/
	@Test
	public void test01_CheckGoToLastReadPostWhenForumHasTopicsThatIsRead() {
		String category = "Test 1 new category" + getRandomNumber();
		String forum = "Test 1 new forum" + getRandomNumber();
		String topic=  "Test 1 new topic" + getRandomNumber();
		String descTopic = "Test 1 description";
		String post1="Test 1 new Post" + getRandomNumber();
		String post2="Test 2 new Post" + getRandomNumber();
		String post3="Test 3 new Post" + getRandomNumber();
		
		/*Step 1: Create category, forum and topics 
		 * Step name: Create category, forum and topics 
		 *Step Description:
			- Login as root  
			- Create category,forum and topics into
		 *Expected Outcome: 
			- Category,forum and topics are added successful*/
		info("Create Category, forum and topics");
		fmTopic.addCategoryForumTopic(category, forum, topic, descTopic);
		click(By.linkText(topic));
		fmPost.quickReply(post1);
		fmPost.quickReply(post2);
		fmPost.quickReply(post3);

		/*Step 2:  Go to last read post of Forum
		 * Step name:  Go to last read post of Forum
		 *Step Description: 
			- Open Forum and click on "Go to last read post" icon in table topic list
		 *Expected Outcome: 
			- Last read post is shown*/ 
		info("Go to the last read post");
		goToForumHome();
		click(By.linkText(forum));
		fmTopic.goToLastReadPost(topic, post3, true);
		
		info("Reset data");
		goToForumHome();
		click(By.linkText(category));
		fmCat.deleteCategoryInForum(category);
 	}


	/**
	* Case ID:118719.
	* Test Case Name: Check Go to last post when Forum has topics that is unread.
	*/
	@Test
	public void test02_CheckGoToLastReadPostWhenForumHasTopicsThatISUnread() {
		String category = "Test 2 new category" + getRandomNumber();
		String forum = "Test 2 new forum" + getRandomNumber();
		String topic=  "Test 2 new topic" + getRandomNumber();
		String descTopic = "Test 2 description";
		String post1="Test 1 new Post" + getRandomNumber();
		String post2="Test 2 new Post" + getRandomNumber();
		String post3="Test 3 new Post" + getRandomNumber();
		/*Step 1: Create category, forum and topics 
		 * Step name: Create category, forum and topics 
		 *Step Description:
			- Login as root  
			- Create category,forum and topics into
		 *Expected Outcome: 
			- Category,forum and topics are added successful*/
		info("Create Category, forum and topics");
		fmTopic.addCategoryForumTopic(category, forum, topic, descTopic);
		click(By.linkText(topic));
		fmPost.quickReply(post1);
		fmPost.quickReply(post2);
		fmPost.quickReply(post3);
		
		/*Step 2:  Go to last  post of Forum
		 * Step name: Go to last  post of Forum
		 *Step Description:
			- Open Forum and click on "Go to last post" icon in table topic list
		 *Expected Outcome: 
			- Category,forum and topics are added successful*/
		acc.signIn(DATA_USER2, DATA_PASS);
		goToForums();
		click(By.linkText(forum));
		fmTopic.goToLastReadPost(topic, post3, false);
		
		info("Reset Data");
		acc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
		click(By.linkText(category));
		fmCat.deleteCategoryInForum(category);

 	}


	/**
	* Case ID:118721.
	* Test Case Name: Check Go to last read post when there is no topic in forum.
	*/
	@Test
	public  void test03_CheckGoToLastReadPostWhenThereIsNoTopicInForum() {
		String category = "Test 3 new category" + getRandomNumber();
		String forum = "Test 3 new forum" + getRandomNumber();
		String topic=  "Test 3 new topic" + getRandomNumber();
		String descTopic = "Test 3 description";
		String post1="Test 1 new Post" + getRandomNumber();
		String post2="Test 2 new Post" + getRandomNumber();
		String post3="Test 3 new Post" + getRandomNumber();
		/*Step 1: Create category, forum and topics 
		 * Step name: Create category, forum and topics 
		 *Step Description:
			- Login as root  
			- Create category,forum and topics into
		 *Expected Outcome: 
			- Category,forum and topics are added successful*/
		info("Create Category, forum and topics");
		fmTopic.addCategoryForumTopic(category, forum, topic, descTopic);
		click(By.linkText(topic));
		fmPost.quickReply(post1);
		fmPost.quickReply(post2);
		fmPost.quickReply(post3);

		/*Step 2:  Go to last read post of Forum
		 * Step name: Go to last read post of Forum
		 *Step Description:
			- Open category, open a forum
			- Click on [Go to the last read post] icon the right-most of table of topic list
		 *Expected Outcome: 
			- Last read post is shown*/
		goToForumHome();
		click(By.linkText(forum));
		fmTopic.goToLastReadPost(topic, post3, true);

		/*Step 3: Delete all topics 
		 * Step name: Delete all topics 
		 *Step Description:
			- Delete all topics
		 *Expected Outcome: 
			- All topics is deleted successful
			- Go to the last read post icon is disappear*/
		fmTopic.deleteTopic(topic);
		waitForElementNotPresent(fmTopic.ELEMENT_GO_TO_LAST_READ_POST.replace("${topic}", topic), 4000);
		
		info("Reset Data");
		goToForumHome();
		click(By.linkText(category));
		fmCat.deleteCategoryInForum(category);
 	}
}
