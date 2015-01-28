package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Forum_Category extends Forum_TestConfig{
	/**
	 *<li> Case ID:116687.</li>
	 *<li> Test Case Name: Watch&Unwatch category.</li>
	 *<li> Pre-Condition: Config mail for package to receive notification mail</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups ="pending")
	public  void test01_WatchUnwatchCategory() {
		info("Test 1: Watch&Unwatch category");

		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = "//*[text()='Hi John,']";
		By mail = By.xpath("//*[@role='link']//*[text()= 'James Davis has commented one of your activities']");

		/*Step Number: 1
		 *Step Name: Prepare data: Create a category
		 *Step Description: 
			- Create a category
		 *Input Data: 

		 *Expected Outcome: 
			Category is created successfully.*/

		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumHP.addCategory(nameCat,"",description);
		forumHP.saveChangesAddCategory();

		/*Step number: 2
		 *Step Name: Watch a category
		 *Step Description: 
			- Open this category
			- Click on Watch
			- Add new forum/topic/post
		 *Input Data: 

		 *Expected Outcome: 
			- Category is watched successfully
			- Notification mail is sent to user who is watching category*/
        info("Click on Watch link");
		click(forumHP.ELEMENT_WATCH);
		info("Add a forum in the category");
		forumHP.addForum(nameForum,"",description);
		forumHP.saveChangesAddForum();
		info("Verify that the forum is shown successfully");
		waitForAndGetElement(forumHP.ELEMENT_DETAIL_FORUM_CATEGORY_TITLE.replace("${title}",nameForum));
		info("Create a topic without attached file");
		forumHP.startTopic(topic1, topic1,"","");

		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		info("go to Forum home page");
		hp.goToForum();
		click((forumHP.ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK).replace("${name}", topic1));
		click(forumHP.ELEMENT_TOPIC_REPLY);
		type(forumHP.ELEMENT_TOPIC_REPLY_TITLE, content, true);
		inputFrame(forumHP.ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR, content);
		click(forumHP.ELEMENT_SUBMIT_BUTTON); 
		
		goToMail("fqaexovn@gmail.com", "exoadmin");
		checkAndDeleteMail(mail ,comment);
		switchToParentWindow();
		/*Step number: 3
		 *Step Name: Unwatch a category
		 *Step Description: 
			- Open a watched category
			- Click on Unwatch
			- Add new forum/topic/post
		 *Input Data: 

		 *Expected Outcome: 
			- Category is unwatched successfully!
			- Notification mail is NOT sent*/ 

		info("Add a forum in the category");
		click(forumHP.ELEMENT_CATEGORY_BREADCUMB_HOME);
		click(forumHP.ELEMENT_UNWATCH);
		forumHP.addForum(nameForum,"",description);
		forumHP.saveChangesAddForum();
		info("Verify that the forum is shown successfully");
		waitForAndGetElement(forumHP.ELEMENT_DETAIL_FORUM_CATEGORY_TITLE.replace("${title}",nameForum));
		info("Create a topic without attached file");
		forumHP.startTopic(topic1,description,"","");
		forumHP.deleteCategory(nameCat);
	}

	/**
	 *<li> Case ID:116742.</li>
	 *<li> Test Case Name: Add a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:116743.</li>
	 *<li> Test Case Name: Edit a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:116744.</li>
	 *<li> Test Case Name: Delete a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_03_04_AddEditDeleteACategory() {
		info("Test 2: Add a category");

		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameCat2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Add a category
		 *Step Description: 
			- Go to Forum page
			- Click on Add Category
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Category is added successfully and shown in Forum home*/ 
		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumHP.addCategory(nameCat,"",nameCat);
		forumHP.saveChangesAddCategory();

		info("edit category");
		forumHP.editCategory(nameCat2);
		info("delete category");
		forumHP.deleteCategory(nameCat2);
	}

	/**
	 *<li> Case ID:116745.</li>
	 *<li> Test Case Name: Export/Import a category.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *
	 */
	@Test
	public  void test05_ExportImportACategory() {
		info("Test 5: Export/Import a category");

		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String description = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Prepare data: Create a category
		 *Step Description: 
			- Create a category
		 *Input Data: 

		 *Expected Outcome: 
			Category is created successfully.*/

		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumHP.addCategory(nameCat,"",description);
		forumHP.saveChangesAddCategory();

		/*Step number: 2
		 *Step Name: Export a category
		 *Step Description: 
			- Go to Forum page
			- Click on Administration, then click on Export
			- Select category(s)
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Category is exported successfully*/
        info("Export category");
		click(forumHP.ELEMENT_CATEGORY_BREADCUMB_HOME);
		forumHP.exportCategory(nameCat);

		/*Step number: 3
		 *Step Name: Import a category
		 *Step Description: 
			- Delete this category
			- Click on Administration, click on’ Import
			- Browse a file which is exported above
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Category is imported successfully*/ 
		info("Delete category");
		forumHP.deleteCategory(nameCat);
        info("Import category");
		click(forumHP.ELEMENT_ACTIONBAR_ADMINISTRATION);
		click(forumHP.ELEMENT_ACTIONBAR_ADMIN_IMPORT);
		forumHP.importCat("Downloads/", "ks-export.zip");
	}

	/**
	 *<li> Case ID:116746.</li>
	 *<li> Test Case Name: Export / Import a forum.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_ExportImportAForum() {
		info("Test 6: Export / Import a forum");
		
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Prepare data: create a caterory, forum
		 *Step Description: 
			- Create a category
			- Create some forums in the category
		 *Input Data: 

		 *Expected Outcome: 
			Category, forums are created successfully*/

		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumHP.addCategory(nameCat,"",nameCat);
		forumHP.saveChangesAddCategory();

		info("Add a forum in the category");
		forumHP.addForum(nameForum,"",nameForum);
		forumHP.saveChangesAddForum();


		/*Step number: 2
		 *Step Name: Export forums
		 *Step Description: 
			- Open this category
			- Click on Manage Categoy, click on Export Forum(s)
			- Select forum(s) to export
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Forums are exported successfully*/
		info("Export the forum");
		click(forumHP.ELEMENT_CATEGORY_BREADCUMB_HOME);
		forumHP.goToCategory(nameCat);
		forumHP.exportForum(nameForum);
		
		/*Step number: 3
		 *Step Name: Import forums
		 *Step Description: 
			- Delete forums
			- Click on Manage Categoy, then select ’Import Forum
			- Browse a file
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Forums are imported successfully*/
        info("Import forum");
		forumHP.importForum("Downloads/", "ks-export.zip");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("Delete category");
		hp.goToForum();
		forumHP.deleteCategory(nameCat);
	  }
	}