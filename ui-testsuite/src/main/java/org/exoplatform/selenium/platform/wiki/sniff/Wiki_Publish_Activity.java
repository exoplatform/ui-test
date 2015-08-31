package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Publish_Activity extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122864.</li>
	 *<li> Test Case Name: Create new wiki page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition:</li>
	 */
	@Test
	public  void test01_CreateNewWikiPage() {
		info("Test 01: Create new wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String line1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String line2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String line3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String line4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String line5 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =line1+"</br>"+line2+"</br>"+line3+"</br>"+line4+"</br>"+line5;
		
		/*Step Number: 1
		 *Step Name: Step 1:  Add new wiki page
		 *Step Description: 
			- Login and goto intranet
			- Click Wiki on left Navigation to go to Wiki Application
			- Click add page-> Blank page
			- Fill the title and content and click save
		 *Input Data: 

		 *Expected Outcome: 
			Wiki page is created.*/ 
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		info("Verify that the new wiki page is created successfully");
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		/*Step Number: 2
		 *Step Name: Step 2: Check activity stream
		 *Step Description: 
			- Goto homepage and check stream
		 *Input Data: 

		 *Expected Outcome: 
			- An activity is added into stream for wiki page
			- Informations that are displayed in the featured content :
			   1. Wiki page's title
			   2. Wiki page's version
			   3. First 4 lines of the wiki page
		  */ 
		hp.goToHomePage();
		hpAct.checkActivityAddWikiPage(title,content,null);
	
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
	}

	/**
	 *<li> Case ID:122867.</li>
	 *<li> Test Case Name: Delete wiki page</li>
	 *<li> Pre-Condition: the wiki activity is already shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test02_DeleteWikiPage() {
		info("Test 2: Delete wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new wiki page
		 *Step Description: 
			- Login and goto intranet
			- Click Wiki on left Navigation to go to Wiki Application
			- Click add page-> Blank page
			- Fill the title and content and click save
		 *Input Data: 

		 *Expected Outcome: 
			Wiki page is created and an activity is added into activity stream.
			*/
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		info("Verify that the new wiki page is created successfully");
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		info("Verify that an activity is added to the activity stream");
		hp.goToHomePage();
		hpAct.checkActivity(title);
		
		/*Step number: 2
		 *Step Name: Step 2: Delete Wiki Page
		 *Step Description: 
			- Goto Wiki page and click [More] -> [Delete Page]
			- Go to the Homepage to check 
		 *Input Data: 
	
		 *Expected Outcome: 
			Activity of the wiki page is removed from the activity stream*/ 
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",title));
	}

	/**
	 *<li> Case ID:122869.</li>
	 *<li> Test Case Name: Open Wiki page from wiki's activity</li>
	 *<li> Pre-Condition: a wiki activity is already shred in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test03_OpenWikiPageFromWikiActivity() {
		info("Test 3: Open Wiki page from wiki's activity");
	
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- From the activity stream, click on the title of a wiki page displayed in activity of wiki
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki application is opened in the correspond page.*/
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Click on the title of wiki page");
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",title),2000,1).click();
		info("Verify that The wiki application is opened in the correspond page ");
		waitForAndGetElement(wHome.ELEMENT_PAGE_TITLE.replace("${title}",title),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
	}
	
	/**
	 *<li> Case ID:122865.</li>
	 *<li> Test Case Name: Update activity - edit wiki page title</li>
	 *<li> Pre-Condition: the wiki activity is already shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test04_OpenWikiPageFromWikiActivity() {
		info("Test 04: Update activity - edit wiki page title");
	
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Add new wiki page
		 *Step Description: 
			- Login and goto intranet
			- Click Wiki on left Navigation to go to Wiki Application
			- Click add page-> Blank page
			- Fill the title and content and click save
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki page is created
			- An activity is added into activity stream*/
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Verify that an activity is added to the activity stream");
		hp.goToHomePage();
		hpAct.checkActivity(title);
		
		/*Step Number: 2
		 *Step Name: Edit Wiki Page
		 *Step Description: 
			- Goto Wiki page and click Edit
			- Edit a title of a wiki page 
			- Click save and check [Publish Activity]
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki activity is updated
			- A comment is added in the activity: Page's title has been updated to: $value.
          */
		String comment = wMessage.getWikiMessage(0)+ " "+newTitle;
		info("Go to Wiki porlet and select the wiki page created");
		hp.goToWiki();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0).click();
		info("Edit the title of the wiki page and check on published checkbox");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		info("The title of wiki page's activity is updated");
		hp.goToHomePage();
		hpAct.checkActivity(newTitle);
		info("Verify that  A comment is added in the activity: Page's title has been updated to: "+newTitle);
		hpAct.checkCommentOfActivity(newTitle, comment);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
	
	/**
	 *<li> Case ID:122871.</li>
	 *<li> Test Case Name: No comment is added to the activity when edit page not checking Publish activiy</li>
	 *<li> Pre-Condition: the wiki activity is already shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test05_NotAddCommentToActivityWhenEditPageIsNotCheckingPublishActivity() {
		info("Test 05: No comment is added to the activity when edit page not checking Publish activiy");
	
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Add new wiki page
		 *Step Description: 
			- Login and goto intranet
			- Click Wiki on left Navigation to go to Wiki Application
			- Click add page-> Blank page
			- Fill the title and content and click save
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki page is created
			- An activity is added into activity stream*/
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Verify that an activity is added to the activity stream");
		hp.goToHomePage();
		hpAct.checkActivity(title);
		
		/*Step Number: 2
		 *Step Name: Edit wiki page and not check Publish activity
		 *Step Description: 
			- Go to Wiki application and open page above
			- Click [Edit]
			- Edit title or content not check [Publish activity] 
			- Save page
			- Go to intranet homepage
		 *Input Data: 

		 *Expected Outcome: 
			- No comment is added to the activity above.
          */
		String comment = wMessage.getWikiMessage(0)+" "+newTitle;
		info("Go to Wiki porlet and select the wiki page created");
		hp.goToWiki();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0).click();
		info("Edit the title of the wiki page and check on published checkbox");
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		info("The title of wiki page's activity is updated");
		hp.goToHomePage();
		hpAct.checkActivity(newTitle);
		info("Verify that No comment is added to the activity above");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_COMMENT
				.replace("${title}", newTitle)
				.replace("${comment}", comment));
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
	
	/**
	 *<li> Case ID:122866.</li>
	 *<li> Test Case Name: Update activity - edit wiki page with comments</li>
	 *<li> Pre-Condition: the wiki activity is already shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_EditWikiPageWithComments() {
		info("Test 06: Update activity - edit wiki page with comments");
	
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String newContent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newContent1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Add new wiki page
		 *Step Description: 
			- Login and goto intranet
			- Click Wiki on left Navigation to go to Wiki Application
			- Click add page-> Blank page
			- Fill the title and content and click save
		 *Input Data: 

		 *Expected Outcome: 
			- Wiki page is created
			- An activity is added into activity stream*/
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title,content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Verify that an activity is added to the activity stream");
		hp.goToHomePage();
		hpAct.checkActivity(title);
		
		/*Step Number: 2
		 *Step Name: Edit Wiki Page with comment
		 *Step Description: 
			- Goto Wiki page and click Edit
			- Edit wiki page with new content
			- Input comment into Comment field
			- check [Publish Activity] and Click [save]
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki activity is updated
			- A comment is added to  comment list containing the associated comment value
          */
		String comment1 = wMessage.getWikiMessage(1);
		info("Go to Wiki porlet and select the wiki page created");
		hp.goToWiki();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0).click();
		info("Edit the title of the wiki page and check on published checkbox");
		wHome.goToEditPage();
		rtMode.editSimplePage("",newContent);
		wikiMg.addComment(comment);
		wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		info("The content of wiki page's activity is updated");
		hp.goToHomePage();
		hpAct.checkActivity(newContent);
		info("Verify that only A comment that input above is added in the activity");
		hpAct.checkCommentOfActivity(title,comment);
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_COMMENT
				.replace("${title}", title).replace("${comment}", comment1));
		
		/*Step Number: 3
		 *Step Name: Edit Wiki Page with no comment
		 *Step Description: 
			- goto Wiki page and click Edit
			- Edit wiki page with new content
			- Don't Input comment into Comment field
			- check [Publish Activity] and Click [save]
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki activity is updated
			- A message is added: Page's content has been edited in comment list
          */
		info("Go to Wiki porlet and select the wiki page created");
		hp.goToWiki();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0).click();
		info("Edit the title of the wiki page and check on published checkbox");
		wHome.goToEditPage();
		rtMode.editSimplePage("",newContent1);
		wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		info("The content of wiki page's activity is updated");
		hp.goToHomePage();
		hpAct.checkActivity(newContent1);
		
		info("Verify that only message is added in the activity");
		hpAct.checkCommentOfActivity(title,comment1);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
	}
	
	/**
	 *<li> Case ID:122868.</li>
	 *<li> Test Case Name: Update wiki's activity after moving a wiki page</li>
	 *<li> Pre-Condition:the wiki activity is already shared in the activity stream</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_UpdateWikiActivityAfterMovingAWikiPage() {
		info("Test 07: Update wiki's activity after moving a wiki page");
	
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String message = wMessage.getWikiMessage(2)+" intranet > Wiki Home > "+title2+" > "+title1+"";
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open wiki application
			- Move the wiki page to new place
			- Go to the Homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki activity isn't updated
			- A comment is added in the activity: Page has been moved to : $value.
			where $value is :SpaceName>WikiRootPage>WikiRootPage2>..
        */
		info("Create first new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title1,content1);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title1),2000,0);
		
		info("Create second new wiki pages");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(title2,content2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		info("Move page 1 to page 2");
		wikiMg.movePage(title1, title2);
		
		info("The wiki page's activity isnot updated");
		hp.goToHomePage();
		hpAct.checkActivity(title1);
		hpAct.checkActivity(content1);
		hpAct.checkCommentOfActivity(title1, message);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title2);
	}
	
}