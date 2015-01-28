package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.forum.ForumHomePage.specifMoreActionMenu;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Forum_PublishActivity extends Forum_TestConfig{
		String nameCat ;
		String nameForum;
		/**
		 * Prepare data test
		 * Create a new category,a new forum
		 */
		public void prepareDataTest(){
			info("Create a forum for preparing testing");
			nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber()+"des";
			nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber()+"des";
			hp.goToForum();
			foHome.addCategory(nameCat,"",nameCat);
			foHome.saveChangesAddCategory();
			foHome.addForum(nameForum,"",nameForum);
			foHome.saveChangesAddForum();
			info("Fnished preparing data test");
		}
		/**
		 * Delete all data test
		 * Delete category, forum and topic
		 */
		public void deleteDataTest(){
			info("Delete data test");
			hp.goToForum();
			foHome.goToHomeCategory();
			info("Delete catefory");
			foHome.deleteCategory(nameCat);
			info("Finished deleting data test");
		}
		
		
	/**
	*<li> Case ID:116778.</li>
	*<li> Test Case Name: Check activity after move a topic.</li>
	*<li> Pre-Condition: Topic activity is existed</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by rosso at 2015/01/22 14:38:18</li>
	*/
	@Test
	public  void test01_CheckActivityAfterMoveATopic() {
		info("Test 01: Check activity after move a topic");
		/*Step Number: 1
		*Step Name: Create a category, forum, topic
		*Step Description: 
			- Create a category
			- Create a forum
			- Create a topic
		*Input Data: 
			
		*Expected Outcome: 
			Category, forum, topic are created successfully*/

		/*Step number: 2
		*Step Name: Move a topic
		*Step Description: 
			- Open topic above
			- Click More Action >’ Move
			- Choose the destination forum
		*Input Data: 
			
		*Expected Outcome: 
			- Topic is moved to destination Forum successfully
			- In activity stream, a comment is added into activity,message is "Topic has been moved to: $value.Where $value is :Space>Category>Forum...*/ 
		info("Create data test");
		prepareDataTest();
		String Forum=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String Cat=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String Topic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment ="Topic have been moved to: "+nameCat+">"+nameForum;
		info("Finishing creating data test");
		
		
		hp.goToForum();
		foHome.goToHomeCategory();
		info("Create a second Category");
		foHome.addCategory(Cat,"",Cat);
		foHome.saveChangesAddCategory();
		info("Create a second forum");
		foHome.addForum(Forum,"",Forum);
		foHome.saveChangesAddForum();
		
		info("Start a topic in second forum of second Category");
		foHome.goToStartTopic();
		foHome.startTopic(Topic,Topic, "", "");
		
		info("Move Topic:"+Topic);
		foHome.goToTopic(Topic);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.MOVE);
		
		info("Move the topic to a forum");
		foTopic.moveTopicToForum(nameCat,nameForum);
		info("Verify that the forum is moved to new category");
		waitForAndGetElement(foHome.ELEMENT_CATEGORY_FORUM_BREAD.replace("${forum}",nameForum).replace("${category}",nameCat));
		
		hp.goToHomePage();
		info("Verify that the topic's activity is shown on intranet");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",Topic).replace("{$comment}",comment)));
		info("The activity is shown successfully");
		
		info("Delete topic");
		hp.goToForum();
		foHome.goToHomeCategory();
		foHome.deleteCategory(Cat);
		deleteDataTest();
		info("Test01: finished testing");
 	}

	/**
	*<li> Case ID:116779.</li>
	*<li> Test Case Name: Check topic activity when creating new topic.</li>
	*<li> Pre-Condition: - Topic activity is existed</li>
	*/
	@Test
	public  void test02_CheckTopicActivityWhenCreatingNewTopic() {
		info("Test 2: Check topic activity when creating new topic");
		info("Create data test for test 2");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finish creating data test for test 2");
		/*Step Number: 1
		*Step Name: - Create new category
		*Step Description: 
			- Login and goto Forum application
			- Click [Add Category] 
			- Fill the information and click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- New category is created
			- No activity is added in activity stream*/

		
		/*Step number: 2
		*Step Name: - Create new Forum
		*Step Description: 
			- Click [Add Forum]
			- Fill the information and click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- New forum is created
			- No activity is added in activity stream*/

		/*Step number: 3
		*Step Name: - Create new Topic
		*Step Description: 
			- Click [start Topic]
			- input the information and click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- New Topic is created
			- An activity is added into activity stream
			- Informations that are displayed in the featured content :1. Topic's title2. Rating average over the Topic3. First 4 lines of the topic content4. Number of replies*/ 
		
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		
		hp.goToHomePage();
		aHome.checkActivity(topic1);
		deleteDataTest();
		info("Test 2: Finished testing");
		
	}
    /**
	*<li> Case ID:116780.</li>
	*<li> Test Case Name: Check activity after update topic title.</li>
	*<li> Pre-Condition: - Topic activity is existed</li>
	*/
	@Test
	public  void test03_CheckTopicActivityAfterUpdateTopicTitle(){
		info("Test 3: Check activity after update topic title");
		info("Create data test for test 3");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topicNewName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = "Title has been updated to: ";
		info("Finished Creating data test for test 3");
		
		prepareDataTest();
		
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1,"","");
		
		info("Edit topic:"+topic1);
		foHome.goToTopic(topic1);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.EDIT);
		type(foHome.ELEMENT_START_TOPIC_POPUP_TITLE_FILED, topicNewName, true);
		info("Click on Submit button");
		click(foHome.ELEMENT_SUBMIT_BUTTON);
		info("All changes are saved");
		
		hp.goToHomePage();
		info("Verify that the topic's activity is updated");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topicNewName).replace("{$comment}",comment+topicNewName)));
		info("The topic's activity is updated successfully");
		deleteDataTest();
		info("Test 3: Finish testing");
	}
    /**
	*<li> Case ID:116781.</li>
	*<li> Test Case Name: Check activity after update topic content.</li>
	*<li> Pre-Condition: - Topic activity is existed</li>
	*/
	@Test
	public  void test04_CheckTopicActivityAfterUpdateTopicConent(){
		info("Test 4: Check activity after update topic content");
		info("Create data test for test 4");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newContent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2 ="Content has been edited.";
		info("Finish Creating data test for test 4");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1,"","");
		
		info("Edit topic:"+topic1);
		foHome.goToTopic(topic1);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.EDIT);
		inputFrame(foHome.ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR,newContent);
		info("Click on Submit button");
		click(foHome.ELEMENT_SUBMIT_BUTTON);
		
		hp.goToHomePage();
		info("Verify that the new topic's activity is shown");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topic1).replace("{$comment}",comment2)));
		info("the new topic's activity is shown successfully");
		deleteDataTest();
		info("Test 04: finished testing");
	}
	/**
	 *<li> Case ID:116782.</li>
	 *<li> Test Case Name: Check topic activity after lock/unlock a topic.</li>
	 *<li> Pre-Condition: Topic activity is existed</li>
	 */
	@Test
	public  void test05_CheckTopicActivityAfterLockUnlockTopic(){
		info("Test 5: Check topic activity after lock/unlock a topic");
		info("Create data test for test 5");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment3="Topic has been unlocked.";
		String comment4="Topic has been locked.";
		info("Finished Creating data test for test 5");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1,"","");
		
		info("Lock topic:"+topic1);
		foHome.goToTopic(topic1);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.LOCK);
		info("Verify that Post reply button is not shown when the topic is locked");
		waitForElementNotPresent(foTopic.ELEMENT_POST_REPLY);
		info("The topic is locked successfully");
		
		hp.goToHomePage();
		info("Verify that the topic's activity is shown");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topic1).replace("{$comment}",comment4)));
		info("The topic's activity is shown successfully");
		
		hp.goToForum();
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.UNLOCK);
		info("Verify that Post reply button is shown when the topic is unlocked");
		waitForAndGetElement(foTopic.ELEMENT_POST_REPLY);
		info("The topic is unlocked successfully");
		
		hp.goToHomePage();
		info("Verify that topic's activity is shown");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topic1).replace("{$comment}",comment3)));
		info("The topic's activity is shown successfully");
		deleteDataTest();
		info("Test 05: Finish testing");
	}
	/**
	 *<li> Case ID:116783.</li>
	 *<li> Test Case Name: Check topic activity after delete topic.</li>
	 */
	@Test
	public void test06_CheckActivityAfterDeleteTopic(){
        info("Test 6: Check topic activity after delete topic");
        info("Create data test for test 6");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished Creating data test for test 6");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1,"","");
		
		info("Delete topic:"+topic1);
		foHome.goToTopic(topic1);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenu.DELETE);
		hp.goToHomePage();
		info("Verify that the topic's activity is deleted after the topic is deleted");
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",topic1)));
		info("the topic's activity is deleted sucessfully");
		deleteDataTest();
		info("Test 06: Finish testing");
	}
	/**
	*<li> Case ID:116785.</li>
	*<li> Test Case Name: Add a new poll.</li>
	*/
	@Test
	public  void test07_AddPoll() {
		info("Test 7: Add a new poll");
		info("Create data test for test 7");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String question = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment="A poll has been added to the topic.";
		info("Finish Creating data test for test 7");
		/*Step Number: 1
		*Step Name: - Add new poll
		*Step Description: 
			- Connect to Intranet
			- Open a Forum
			- Add a new topic
			- Goto topic => More Action => add Poll
			- Fill the infomation and click [save]
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- Poll is added to topic
			- A Poll's activity is added to the activity stream*/ 
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		foHome.goToTopic(topic1);
		info("Add a new poll to the topic");
		foTopic.addPoll(question, option1, option2);
		
		hp.goToHomePage();
		info("Verify that topic's activity is added to the stream");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topic1).replace("{$comment}",comment)));
		info("The topic's activity is added to the stream successfully");
		deleteDataTest();
		info("Test 07: Finished testing");
		
	}
	/**
	*<li> Case ID:116784.</li>
	**<li> Test Case Name: Redirect to the poll by clicking on "Vote".</li>
	*/
    @Test
	public void test08_RedirectToThePollByClickingOnVote(){
		info("Test 08:Redirect to the poll by clicking on Vote");
		info("Create data test for test 8");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String question = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finish Creating data test for test 8");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		foHome.goToTopic(topic1);
		info("Add a new poll to the topic");
		foTopic.addPoll(question, option1, option2);
		
		hp.goToHomePage();
		info("Click on Vote of Poll's activity on the stream");
		click(By.xpath(aHome.ELEMENT_ACTIVITY_POLL_VOTE_FOR_POLL.replace("{$name}",question)));
		info("Verify that the page redirects to the poll");
		waitForAndGetElement(foTopic.ELEMENT_EDIT_POLL_MORE_ACTIONS);
		info("the page redirects to the poll successfully");
		deleteDataTest();
		info("Test 08: Finish testing");
	}
    /**
    *<li> Case ID:116787.</li>
	*<li> Test Case Name: Edit a poll.</li>
	*/
    @Test
	public void test09_EditPoll(){
    	info("Test 09: Edit a poll");
		info("Create data test for test 9");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String question = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2="Poll has been updated.";
		info("Finished Creating data test for test 9");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		foHome.goToTopic(topic1);
		info("Add a new poll to the topic");
		foTopic.addPoll(question, option1, option2);
		
		info("Edit a poll");
		foTopic.editPoll(question, option1, option3);
		
		hp.goToHomePage();
		info("Verify that the poll's comment is shown on the stream");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",question).replace("{$comment}",comment2)));
		info("the poll's comment is shown on the stream successfully");
		deleteDataTest();
		info("Test 09: Testing finished");
    }
    /**
    *<li> Case ID:116786.</li>
	*<li> Test Case Name: Delete a poll.</li>
	*/
    @Test
    public void test10_DeletePoll(){
    	info("Test 10: Delete a poll");
		info("Create data test for test 10");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String question = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String option2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment3="Poll has been removed.";
		info("Finished Creating  data test for test 10");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		foHome.goToTopic(topic1);
		info("Add a new poll to the topic");
		foTopic.addPoll(question, option1, option2);
		
		info("Delete the poll");
		foTopic.deletePoll();
		
		hp.goToHomePage();
		info("Verify that the comment is added to the topic on the stream after deleted poll");
		waitForAndGetElement(By.xpath(aHome.ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY.replace("{$title}",topic1).replace("{$comment}",comment3)));
		info("The comment is added to the topic on the stream successfully after deleted poll");
		deleteDataTest();
		info("Test 10: finshed testing");
    }

	/**
	*<li> Case ID:116788.</li>
	*<li> Test Case Name: Jump into Reply form by clicking on "Reply" action.</li>
	*<li> Pre-Condition: a forum activity is already shared in the activity stream</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by rosso at 2015/01/22 14:38:18</li>
	*/
	@Test
	public  void test11_JumpIntoReplyFormByClickingOnReplyAction() {
		info("Test 11 Jump into Reply form by clicking on Reply action");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet Homepage
			- From the activity stream, click on "Reply" action in Forum activity
		*Input Data: 
			
		*Expected Outcome: 
			- The forum application is displayed with the reply form opened*/ 
		info("Create test data");
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished test data");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		foHome.goToStartTopic();
		foHome.startTopic(topic1, topic1, "", "");
		//foHome.goToTopic(topic1);
		
		hp.goToHomePage();
		info("Click on Reply button of the topic:"+topic1);
		click(By.xpath(aHome.ELEMENT_ACTIVITY_TOPIC_REPLY.replace("{$name}",topic1)));
		info("Verify that Reply popup of the topic is shown");
		waitForAndGetElement(foTopic.ELEMENT_TOPIC_POST_A_REPLY_TITLE);
		info("Reply popup of the topic is shown successfully");
		info("Click on cancel button of the post");
		click(foTopic.ELEMENT_TOPIC_CANCEL_A_POST);
		deleteDataTest();
		info("Test 11: finsihed testing");
 	}

	/**
	*<li> Case ID:116789.</li>
	*<li> Test Case Name: Jump into last Reply of Topic.</li>
	*<li> Pre-Condition: - a forum activity is already shared in the activity stream
	- there are some comments on a topic of forum</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by rosso at 2015/01/22 14:38:18</li>
	*/
	@Test
	public  void test12_JumpIntoLastReplyOfTopic() {
		info("Test 12 Jump into last Reply of Topic");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- From the activity stream, click on " View last Reply" action in Forum activity
		*Input Data: 
			
		*Expected Outcome: 
			- The lastest reply of the topic is displayed*/ 
		info("Create test data for test 12");
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String reply = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished test data for test 12");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		info("Create a topic");
		foHome.goToStartTopic();
		foHome.startTopic(topic, topic,"","");
		info("Finished Creating a topic");
		info("Open the topic:"+topic);
		foHome.goToTopic(topic);
		info("Reply the topic");
		foHome.replyTopic(reply, reply, "","");
		
		
		hp.goToHomePage();
		info("Verify that topic's activity with View Last reply icon is shown");
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY.replace("${topic}",topic));
		info("The topic's activity with View Last reply icon is shown successfully");
		info("Click on View Last Reply icon");
		click(aHome.ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY.replace("${topic}",topic));
		info("Verify that the last reply is shown in forum");
		waitForAndGetElement(foHome.ELEMENT_TOPIC_LAST_REPLY.replace("${reply}",reply));
		info("the last reply is shown in forum successfully");
		deleteDataTest();
		info("Test 12: Finish testing");

 	}

	/**
	*<li> Case ID:116790.</li>
	*<li> Test Case Name: Jump to related reply.</li>
	*<li> Pre-Condition: a forum activity is already shared in the activity stream</li>
	*<li> Post-Condition: </li>
	*<li> Done with OSs and browsers : </li>
	*<li> Generated by rosso at 2015/01/22 14:38:18</li>
	*/
	@Test
	public  void test13_JumpToRelatedReply() {
		info("Test 13 Jump to related reply");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- From the activity stream, add a comment to a forum activity
			- Move the mouse over the comment
		*Input Data: 
			
		*Expected Outcome: 
			The "View" action is displayed*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click on the action "View"
		*Input Data: 
			
		*Expected Outcome: 
			- The related reply in the forum is displayed*/ 
		info("Create test data for test 13");
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment =txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Finished test data for test 13");
		prepareDataTest();
		hp.goToForum();
		foHome.goToCategory(nameCat);
		foHome.goToForum(nameForum);
		
		info("Create a topic");
		foHome.goToStartTopic();
		foHome.startTopic(topic, topic,"","");
		info("Finished Creating a topic");
		
		hp.goToHomePage();
		info("Add a comment to the topic's activity");
		aHome.addComment(topic,comment);
		info("Mouse over on the comment");
		mouseOver(aHome.ELEMENT_COMMENT_TEXT.replace("${activityText}",topic).replace("${commentText}",comment),true);
		info("Verifyt that View is shown");
		waitForAndGetElement(aHome.ELEMENT_ACTIVITY_COMMENT_VIEW_HOVEROVER.replace("${comment}",comment),3000,0);
		info("Click on the View icon");
		click(aHome.ELEMENT_ACTIVITY_COMMENT_VIEW_HOVEROVER.replace("${comment}",comment));
		info("Verify that the page redirects to related reply in the forum");
		waitForAndGetElement(foHome.ELEMENT_TOPIC_REPPLY_CONTENT.replace("${content}",comment));
		info("The related reply is shown in forum successfully");
		deleteDataTest();
		info("Test 13: Finish testing");
 	}
}