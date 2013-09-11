package org.exoplatform.selenium.platform.forum.sniff;

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
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Thuntn
 * @date 12 Sep 2013
 * 
 * This class include cases in folders: More action, Rate, Tag, Watch & Unwatch
 */
public class Forum_Forum_Topic_OtherAction extends ForumBase{
	ManageAccount magAc;
	ForumManageCategory mngCat;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	NavigationToolbar navTool;
	HomePageActivity hpgAct;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		magAc = new ManageAccount(driver);
		mngCat = new ForumManageCategory(driver);
		mngFru = new ForumManageForum(driver);
		mngPost = new ForumManagePost(driver);
		mngTopic = new ForumManageTopic(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**Lock/ Unlock a topic
	 * CaseID: 71206 
	 * This case is duplicated with case 74752
	 */

	/** Open/ Close a topic
	 * CaseID: 71208
	 */
	@Test
	public void test01_OpenCloseTopic() {
		String titleCat = "Category 01";
		String titleForum = "Forum 01";
		String titleTop = "Topic 01";

		info("Lock/Unlock a topic");
		//create category, forum, topic
		addCategoryForumTopic(titleCat, titleForum, titleTop, titleTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.actionOnTopic(3);

		mngTopic.actionOnTopic(4);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true); 
	}

	/** Rate topic
	 * CaseID: 68933
	 */
	@Test
	public void test02_RateTopic() {
		String titleCat = "Category 02";
		String titleForum = "Forum 02";
		String titleTop = "Topic 02";

		info("Rate topic");
		//create category, forum, topic
		addCategoryForumTopic(titleCat, titleForum, titleTop, titleTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.voteTopic(2,true);


		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true); 
	}

	/** Tag for topic
	 * CaseID: 68933
	 */
	@Test
	public void test03_TagTopic() {
		String titleCat = "Category 03";
		String titleForum = "Forum 03";
		String titleTop = "Topic 03";
		String tag = "tag1";

		info("Tag for topic");
		//create category, forum, topic
		addCategoryForumTopic(titleCat, titleForum, titleTop, titleTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		mngTopic.addTagForTopic(tag);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true); 
	}

	/** Watch & Unwatch topic
	 * CaseID: 68915
	 */
	@Test
	public void test04_WatchUnwatchTopic() {
		String titleCat = "Category 04";
		String titleForum = "Forum 04";
		String titleTop = "Topic 04";
		String newTopic = "New topic 04";
		String post2 = "New post 2";
		String mail = "//span/b[text()='[${category}][${forum}] ${topic}']";

		info("Watch & Unwatch topic");
		//create category, forum, topic
		magAc.updateUserProfile(null,null, null, EMAIL_ADDRESS1);
		goToForums();
		addCategoryForumTopic(titleCat, titleForum, titleTop, titleTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		waitForAndGetElement(mngPost.ELEMENT_POST_REPLY_BUTTON);

		mngTopic.watchItem(true);

		mngPost.postReply(newTopic, newTopic, "", "", "");

		goToMail(EMAIL_ADDRESS1,EMAIL_PASS);
		checkAndDeleteMail(By.xpath(mail.replace("${category}",titleCat).replace("${forum}", titleForum).replace("${topic}", titleTop)), titleTop);
		switchToParentWindow();

		mngTopic.watchItem(false);
		mngPost.postReply(post2, post2, "", "", "");

		switchToNewWindow();
		Utils.pause(30000);
		waitForElementNotPresent(mail.replace("${category}",titleCat).replace("${forum}", titleForum).replace("${topic}", titleTop));
		
		switchToParentWindow();
		
		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true); 
	}


	public void addCategoryForumTopic(String cate, String forum, String topic, String descTopic){
		String[] permission = {};
		String[] addForum = {forum, "1",null,null,forum};
		mngCat.addNewCategoryInForum(cate, "1", 0,permission, cate, 0,permission);
		mngFru.addForum(cate, addForum, true, "", "", false,0, permission);
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);
		mngTopic.startTopic(topic, descTopic, "", 0, permission,false, false, false);
	} 
}
