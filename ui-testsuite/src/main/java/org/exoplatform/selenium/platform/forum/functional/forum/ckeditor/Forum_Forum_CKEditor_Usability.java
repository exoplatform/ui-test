package org.exoplatform.selenium.platform.forum.functional.forum.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.CKeditor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * 
 * by quynhpt
 * date 14/10/2014
 *
 */
public class Forum_Forum_CKEditor_Usability extends ForumBase {

	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	ManageAccount magAcc;
	ForumManageCategory mngCat;
	NavigationToolbar navToolBar;
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
	 * Set the name for all parameters
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

	/************************* Skin ************************************/

	/**
	 * TC_102570: Check default skin of CKEditor is moono (pending)
	 */

	/**
	 * TC_102571: Change skin of CKEditor to Kama(pending)
	 */
	/************************* Usability *******************************/
	/**
	 * TC_102572: Resize CKEditor
	 * Steps:
	 * 1. Log in as admin
	 * 2. Go to Forum page
	 * 3. Create category and forum
	 * 4. Click [Start topic]
	 * 5. Resize Ckeditor by click resize icon at the botton right Conner of ckeditor
	 * 6. Start drag up and down
	 * Expected:
	 * 6.CKEditor is resized as expected
	 */
	@Test(enabled = true)
	public void test01_ResizeEditor() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("add a new category and a forum");
		mngTopic.addCategoryForum(category, forum);

		info(" open new topic popup");
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);
		waitForAndGetElement(cke.ELEMENT_CKEDITOR_BUTTON_RESIZE);

		info(" Get the form size when the popup is opened");
		Dimension form_size_old = driver
				.findElement(cke.ELEMENT_CKEDITOR_POPUP).getSize();
		info(" the old form size: " + form_size_old.getWidth());
		cke.cke_resize(100, 100);

		info(" Get the new form size");
		Dimension form_size_new = driver
				.findElement(cke.ELEMENT_CKEDITOR_POPUP).getSize();
		info(" the new form size: " + form_size_new.getWidth());
		Utils.pause(3000);
		
		assert (form_size_new.getWidth() > form_size_old.getWidth()):"cannot resize the form";
		
		info("-- The test is successfull--");
		cke.cancelPopup();
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102573: Check action copy-paste
	 * Steps:
	 *1. Log in as admin
	 *2. Go to Forum page
	 *3. Create category and forum
	 *4. Click [Start topic]
	 *5. On Message form, input content
	 *6. Select content and click [Copy] icon on toolbar or use Ctrl+C
	 *7. Select other place and click [Paste] icon on toolbar or Ctrl+V
	 *Expected:
	 *7. Content is copied and pasted successfully
	 */
	@Test(enabled = true)
	public void test02_ActionCopPast() {
		String tex = "Go to the forum and check copy-past action.";

		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();
		cke.inputDataInContent(tex);
		// execute to cut and paste the content
		cke.cke_Copy();
		Utils.pause(1000);
		cke.cke_Paste();
		Utils.pause(1000);
		// save the post
		mngPost.savePostContent();
		Utils.pause(1000);
		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION.replace("${descripTopic}", tex));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102574: Check action cut-past
	 * Steps:
	 *1. Log in as admin
	 *2. Go to Forum page
	 *3. Select a topic
	 *4. Choose [Post reply]
	 *5. On Message form, input content
	 *6. Select content and click [Cut] icon on toolbar or use Ctrl X
	 *7. Select other place and click [Paste] icon on toolbar or use Ctrl+V
	 */
	@Test(enabled = true)
	public void test03_ActionCutPast() {
		String tex = "Go to the forum and check cut-past action.";
		info("Go to the forum portlet");
		mngFru.goToForums();

		info("Create a new category, forum, topic");
		mngTopic.addCategoryForumTopic(category, forum, topic, topic);

		// open the topic
		click(By.linkText(topic));

		// click on Post Reply button
		mngPost.openReplyPostPopUp();
		cke.inputDataInContent(tex);
		// execute to cut and paste the content
		cke.cke_Cut();
		Utils.pause(1000);
		cke.cke_Paste();
		Utils.pause(1000);
		// save the post
		mngPost.savePostContent();
		Utils.pause(1000);
		info("-- Verify the content of the post--");
		waitForAndGetElement(
				ELEMENT_TOPIC_POST_DESCRIPTION.replace("${descripTopic}", tex));
		
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

	/**
	 * TC_102575: Check action Undo/Redo
	 * Steps:
	 *1. Login portal
	 *2. Go to Forum
	 *3. Click [Administration] -> [Notifications]
	 *4. On Content Notification form, Input content
	 *5. Do some actions
	 *6. Click [Undo] icon
	 *==> Undo action successfully
	 *7. Click [Redo]
	 *==> Redo action successfully
	 */
	@Test(enabled = true)
	public void test04_ActionUndoRedo() {
	
		info("Go to the forum portlet");
		mngFru.goToForums();
		goToNotifications();

		cke.cke_Cut();
		Utils.pause(1000);
		cke.cke_Undo();
		Utils.pause(1000);
		WebElement e = waitForAndGetElement(cke.ELEMENT_CKEDITOR_TEXT_CONTENT,
				DEFAULT_TIMEOUT, 1, 2);
		driver.switchTo().frame(e);
		WebElement inputsummary = driver.switchTo().activeElement();
		inputsummary.click();
		
		WebElement el = driver.findElement(By.xpath("html/body"));
		System.out.print("text: " + el.getText() + "\n");
		
		assert el
				.getText()
				.contains(
						"you received this email because you registered for the Forum and Topic Watching notification"):"Cannot Undo/Redo";
	  
		info("-- The test is successfull--");
	   switchToParentWindow();
	   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(ELEMENT_NOTIFY_BUTTON_CLOSE));
	   click(ELEMENT_NOTIFY_BUTTON_CLOSE);
	}

	/**
	 * TC_102582: Maximize/Minize CKEditor
	 * Steps:
	 *1. Log in as admin
	 *2. Go to Forum page
	 *4. Create category and forum
	 *5. Click [Start topic]
	 *6. Maximize/Minimize Ckeditor by click Maximize/Minimize icon at the right Conner of ckeditor
	 *Expected:
	 *6. CKEditor is Maxi/Mini as expected
	 */
	@Test(enabled = true)
	public void test05_MaximizeMin() {
		info("Go to the forum portlet");
		mngFru.goToForums();

		// add a new category and a forum
		mngTopic.addCategoryForum(category, forum);
		// open new topic popup
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);

		// Get minimize size of the area text content
		Dimension form_size_min = driver.findElement(
				cke.ELEMENT_CKEDITOR_TEXT_CONTENT).getSize();

		// click on Maximize button
		cke.cke_Max_Minmize();

		// Get maximize size of the area text content
		Dimension form_size_max = driver.findElement(
				cke.ELEMENT_CKEDITOR_TEXT_CONTENT).getSize();
		
		//Verify that the maximize popup is resize correctly
		assert (form_size_max.getHeight() > form_size_min.getHeight()):"Cannot Maximize the form";
		assert (form_size_max.getWidth() > form_size_min.getWidth()):"Cannot Minimize the form";

		cke.cke_Max_Minmize();
		// Get minimize size of the area text content
		Dimension form_size_min_1 = driver.findElement(
						cke.ELEMENT_CKEDITOR_TEXT_CONTENT).getSize();
		
		//Verify that the minmize popup is resize correctly
		assert (form_size_max.getHeight() > form_size_min_1.getHeight()):"Cannot Minimize the form";
		assert (form_size_max.getWidth() > form_size_min_1.getWidth()):"Cannot Minimize the form";
					
		cke.cancelPopup();
		info("-- The test is successfull--");
		// delete the data test
		goToForumHome();
		click(By.linkText(category));
		mngCat.deleteCategoryInForum(category, true);
	}

}
