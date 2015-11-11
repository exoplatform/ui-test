/**
 * Created by khanhnt at 2:38:23 PM Nov 18, 2013 
 * 
 */
package org.exoplatform.selenium.platform.forum.functional.forum.administration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.*;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.Utils;

/**
 * @author khanhnt
 * @update chinhdtt 
 */

public class Forum_Forum_Administration_BBCode extends ForumManageAdministration{
	ManageAccount acc;
	ForumManageCategory cat; 
	ForumManageForum forum; 
	ForumManageTopic topic; 
	ForumManagePost post; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		acc = new ManageAccount(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
		cat = new ForumManageCategory(driver, this.plfVersion);
		forum = new ForumManageForum(driver, this.plfVersion);
		topic = new ForumManageTopic(driver, this.plfVersion);
		post = new ForumManagePost(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:106948. 
	 * This TC will test to active a BB Code based on its tag name. 
	 * The bb code must be deactived before this test.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test01_ActivateABBCodeInListAvailable() {
		info("Test 1: Activate a BB Code in list available");
		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		String tagName = "CODE";
		goToForums();
		goToBBCodeManagement();

		/*
		- Tick on check box corresponding of BB code which want to activate
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Selected BB code is activated and effected in posts 		*/ 		
		activeBBcode(tagName,true,false);
	}

	/**
	 * Case ID:106972.
	 * This TC will test to add a BB Code with blank example field.
	 * An alert message will show. The method will get it and print out.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test02_AddABBCodeWhenExampleFieldIsBlank() {
		info("Test 2: Add a BB Code when Example field is blank");
		String tagName = "Tag name test02";
		String replacement = "Char will be replaced";
		String des = "Description of new BB Code";
		String example = "";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Click on Add BBCode button
		 *Input Data: 
		 *Expected Outcome: Add BBCode form is shown property		*/
		/*
		- Do not input data to Example field
		- Input data to other fields
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Show alert message:The field Example" is required		*/ 
		goToForums();
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.SAVE);		
	}

	/**
	 * Case ID:106958.
	 * This test will add a BB code with valide data.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test03_AddABBCodeWhenInputDataValid() {
		info("Test 3: Add a BB Code when input data valid");
		String tagName = "Case 106958 tagname";
		String replacement = "Char will be replaced";
		String des = "Description of new BB Code";
		String example = "example";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Click on Add BBCode button
		 *Input Data: 
		 *Expected Outcome: Add BBCode form is shown property		*/
		/*
		- Input valid data to required fields 
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: New BB Code is added successful and listed in BB Code table		*/
		goToForums();
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.SAVE);

		//Delete data test
		waitForAndGetElement(ELEMENT_BBCODE_ADD_BUTTON);
		deleteBBcode(tagName, true);
	}

	/**
	 * Case ID:106968.
	 * This TC will test to add a BB Code with blank Replacement field.
	 * An alert message will show. The method will get it and print out.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test04_AddABBCodeWhenReplacementFieldIsBlank() {
		info("Test 4: Add a BB Code when Replacement field is blank");
		String tagName = "Tag name test04";
		String replacement = "";
		String des = "Description of new BB Code";
		String example = "example";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Click on Add BBCode button
		 *Input Data: 
		 *Expected Outcome: Add BBCode form is shown property		*/
		/*
		- Do not input data to Replacement field
		- Input data to other fields
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Show alert message:The field "Replacement" is required		*/ 
		goToForums();
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.SAVE);
	}

	/**
	 * Case ID:106963.
	 * This TC will test to add a BB Code with blank TagName field.
	 * An alert message will show. The method will get it and print out.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test05_AddABBCodeWhenTagNameFieldIsBlank() {
		info("Test 5: Add a BB Code when Tag name field is blank");
		String tagName = "";
		String replacement = "Char will be replaced";
		String des = "Description of new BB Code";
		String example = "example";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Click on Add BBCode button
		 *Input Data: 
		 *Expected Outcome: Add BBCode form is shown property		*/
		/*
		- Do not input data to Tag name field
		- Input data to other fields
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Show alert message: The field "Tag Name" is required		*/ 
		goToForums();
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.SAVE);
	}

	/**
	 *  Case ID:106976.
	 *  This TC will test to input a BB Code with valid data
	 *  Then, cancel it.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test06_CancelAddingABBCode() {
		info("Test 6: Cancel adding a BB Code");
		String tagName = "Tag name test06";
		String replacement = "Char will be replaced";
		String des = "Description of new BB Code";
		String example = "example";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Click on Add BBCode button
		 *Input Data: 
		 *Expected Outcome: Add BBCode form is shown property		*/
		/*
		- Input valid data to required fields
		- Click on Close button
		 *Input Data: 
		 *Expected Outcome: There is no BB Code added to BB Code table		*/
		goToForums();
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.CLOSE);
	}

	/**
	 * Case ID:106984.
	 * This TC will cancel deleting a bb code.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test07_CancelDeletingBBCode() {
		info("Test 7: Cancel deleting BB Code");
		String tagName = "CODE";	

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*In BB Code table: Click on Delete icon corresponding of BB Code which want to delete
		 *Input Data: 
		 *Expected Outcome: Confirm message pop up is displayed		*/
		/*In Confirm message: Click on Cancel button
		 *Input Data: 
		 *Expected Outcome: There is no BB Code deleted from BB Code table		*/ 
		goToForums();
		goToBBCodeManagement();	
//		switchToNewWindow();
		deleteBBcode(tagName, false);		
	}

	/**
	 * Case ID:106954.
	 * This test will deactive a bb code.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test08_DeactivatedABBCodeInListAvailable() {
		info("Test 8: Deactivated a BB Code in list available");
		String tagName = "I";
		String catName = "Category 106954";
		String forumName = "Forum01";
		String topicName = "Topic test BB code";
		String postName = "Test italic tag";

		/*
		- Login by the Administrator
		- Go to Forum portlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*
		- Untick on check box corresponding of BB code which want to deactivated
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Selected BB code is deactivated and no effected in posts		*/ 
		goToForums();
		goToBBCodeManagement();
		activeBBcode(tagName,false,false);

		//Test BB code in post
		info("Add category/forum/Topic");
		topic.addCategoryForumTopic(catName, forumName, topicName, topicName);
		click(By.linkText(topicName));
		info("Add post");
		post.postReply(null, "[i]"+postName+"[/i]", "", "");
		waitForElementNotPresent(post.ELEMENT_POST_POPUP_NEW);
		waitForElementNotPresent(By.xpath("//i[text()='"+postName+"']"));

		//Delete data test
		Utils.pause(1000);
		goToForumHome();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);
		info("Active I tag");
		goToBBCodeManagement();
		activeBBcode(tagName,true,true);
	}

	/**
	 * Case ID:106996.
	 * This TC will Delete BB Code.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test09_DeleteABBCodeWhichIsDefault() {
		info("Test 9: Delete a BB Code which is default");
		String tagName = "B";
		String catName = "Category 106996";
		String forumName = "Forum01";
		String topicName = "Topic test BB code";
		String postName = "Test bold tag";
		String replacement = "<b>{param}</b>";
		String des = "Set text in bold";
		String example = "[B]This text is underline[/B]";

		/*
		- Login by the Administrator
		- Go to Forum portlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/
		/*In BB Code table: Click on [Delete] icon corresponding of BB Code which want to delete
		 *Input Data: 
		 *Expected Outcome: Confirm message pop up is displayed		*/
		/*In Confirm message: Click on OK button
		 *Input Data: 
		 *Expected Outcome: 
		- Selected BB code is deleted successful and removed from BB Code table
		- Deleted BB Code haven't affect in all posts		*/ 
		goToForums();
		goToBBCodeManagement();
		switchToNewWindow();
		deleteBBcode(tagName, true);

		//Test BB code in post
		info("Add category/forum/Topic");
		topic.addCategoryForumTopic(catName, forumName, topicName, topicName);
		click(By.linkText(topicName));
		info("Add post");
		post.postReply(null, "[b]"+postName+"[/b]", "", "");
		waitForElementNotPresent(post.ELEMENT_POST_POPUP_NEW);
		waitForElementNotPresent(By.xpath("//b[text()='"+postName+"']"));

		//Delete data test
		Utils.pause(1000);
		goToForumHome();
		click(By.linkText(catName));
		cat.deleteCategoryInForum(catName, true);
		
		info("Add BBcode tag");
		goToBBCodeManagement();
		addBBCode(tagName,replacement, des,example,false,ADDBBCODE_ACTION.SAVE);	
	}

	/**
	 * Case ID:106997.
	 * This TC will add a new BB Code and then delete it.
	 * Created by khanhnt at Nov 18, 2013
	 */
	@Test
	public void test10_DeleteBBCodeThatWasInputted() {
		info("Test 10 Delete BB Code that was inputted");
		String tagName = "Case 106997";
		String replacement = "Char will be replaced";
		String des = "Description of new BB Code";
		String example = "example";
		boolean use = false;

		/*
		- Login by the Administrator
		- Go to Forum porlet
		- Click on [Administration] and select [BB Code ] in drop down menu
		 *Expected Outcome: BB code manager form is shown properly		*/

		/*Input value to required fields and click on Save button
		 *Input Data: 
		 *Expected Outcome: New BB Code is added successful and listed in BB Code table		*/

		/*In BB Code table: Click on Delete icon corresponding of BB Code which added at step above
		 *Input Data: 
		 *Expected Outcome: Confirm message pop up is displayed		*/

		/*In Confirm message: Click on OK button
		 *Input Data: 
		 *Expected Outcome: 
		- Selected BB code is deleted successful and removed from BB Code table
		- Deleted BB Code haven't affect in all posts		*/ 		
		goToForums();
		goToBBCodeManagement();

		addBBCode(tagName,replacement, des,example,use,ADDBBCODE_ACTION.SAVE);		
		deleteBBcode(tagName, true,true);
	}
}
