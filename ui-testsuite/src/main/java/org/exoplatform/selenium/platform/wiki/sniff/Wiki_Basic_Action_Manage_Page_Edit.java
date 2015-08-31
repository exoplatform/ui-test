package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Basic_Action_Manage_Page_Edit extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122819.</li>
	 *<li> Test Case Name: Edit page with Source Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	
	@Test
	public  void test02_EditPageUsingSourceEditor() {
		info("Test 02: Edit page with Source Editor");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Edit page with Source Editor
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree 
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar

		 *Input Data: 

		 *Expected Outcome: 
			Edit page successfully*/ 
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		info("Verify that the page is editor successfully");
		wHome.goToEditPage();
		rtMode.editSimplePage(wiki2,wiki2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(wiki2);
	}

	/**
	 *<li> Case ID:122817.</li>
	 *<li> Test Case Name: Edit Page with Rich Text Editor</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	
	@Test
	public  void test01_EditPageUsingRichTextEditor() {
		info("Test 1: Edit Page with Rich Text Editor");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Edit Page with Rich Text Editor
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree 
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar

		 *Input Data: 

		 *Expected Outcome: 
			Edit page successfully*/

		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		info("Verify that the page is editor successfully");
		wHome.goToEditPage();
		rtMode.editSimplePage(wiki2, wiki2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki2),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(wiki2);
	}

	/**
	 *<li> Case ID:122824.</li>
	 *<li> Test Case Name: Auto save when editing page</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_AutoSaveWhenEditingPage() {
		info("Test 3: Auto save when editing page");
	
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String wiki2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Check auto save when editing page
		 *Step Description: 
			- Go to Browser -> My draft
			- Select a page and click open it
			- Edit title or content for it
 
		 *Input Data: 

		 *Expected Outcome: 
			While editing the draft will be saved automatically after 30 seconds (default value) only 
			if there's any modifications in the content or page title. */
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePageHasAutoSaveWithoutSave(wiki,wiki);
		
		wHome.goToMyDraft();
		info("The draft is displayed in the list");
		wDraft.resumeADraft(wiki);
		info("The page in edit mode is displayed");
		rtMode.editSimplePageWithAutoSave(wiki2, wiki2);
		
		info("Delete draf");
		wHome.goToMyDraft();
		wDraft.deleteDraft(wiki2);
	}
	
	/**
	 *<li> Case ID:122836.</li>
	 *<li> Test Case Name: Edit  Page with template layout.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_EditPageFromTemplate() {
		info("Test 04:  Edit  Page with template layout");
		String template = wTempData.getWikiTemplateRandom();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Step 1: Edit Page with template layout
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree 
			- Click on Edit icon in toolbar
			- Change content in full page and click on Save icon in toolbar

 
		 *Input Data: 

		 *Expected Outcome: 
			Edit page successfully*/
		hp.goToWiki();
		wHome.goToAddTemplateWikiPage();
		wikiMg.addSimpleWikiPageByTemplate(template,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		wHome.goToEditPage();
		rtMode.editSimplePage(title2, title2);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title2),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title2);
	}
	
	/**
	 *<li> Case ID:122808.</li>
	 *<li> Test Case Name:Edit Paragraph in Page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_EditParagraphInPage() {
		info("Test 05: Edit Paragraph in Page");
		
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = "== "+title+" ==";
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = "=== "+title2+" ===";
		
		/*Step Number: 1
		 *Step Name: Step 1: Edit Paragraph
		 *Step Description: 
			- Open an exiting page that contains some paragraphs inside
			- Click on Edit icon corresponding of paragraph that you want to edit
			- Change content and click on Save icon in tool bar
 
		 *Input Data: 

		 *Expected Outcome: 
			- Selected paragraph in page is edited successful and shown new content
			- Other paragraph's content  in page is remain */
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(title, content);
		//rtMode.addSimplePage(title, content);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		wikiMg.editParagraph(title, content2);
		wikiMg.saveAddPage();
		wHome.deleteWiki(title);
	}
	
	/**
	 *<li> Case ID:122847.</li>
	 *<li> Test Case Name:Edit page when publish activity is checked.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_EditPageWhenPublishActivityIsChecked() {
		info("Test 06: Edit page when publish activity is checked");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = wMessage.getWikiMessage(0)+ " "+newTitle;
		String comment1 = wMessage.getWikiMessage(1);
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page
		 *Step Description: 
			- Go to wiki page
			- Add new page

 
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully */
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToRichTextEditor();
		rtMode.addSimplePage(title, title);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		/*Step Number: 2
		 *Step Name: Step 2: Edit page ( title of page)
		 *Step Description: 
			- Select page above
			- Click Edit page
			- Change title of page or content of page
			- Check Publish Activity
			- Click Save

 
		 *Input Data: 

		 *Expected Outcome: 
			- Edit page successfully */
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check Publish activity
		 *Step Description: 
			- Go to Homepage
 
		 *Input Data: 

		 *Expected Outcome: 
			- Show activity of page after editing
			- Page's title has been updated to: $value.
         */
		hp.goToHomePage();
		hpAct.checkActivity(newTitle);
		hpAct.checkCommentOfActivity(newTitle,comment);
		
		/*Step Number: 4
		 *Step Name: Step 4: Edit page ( content of page)
		 *Step Description: 
			- Select page above
			- Click Edit page
			- Change content of page
			- Check Publish Activity
			- Click Save
 
		 *Input Data: 

		 *Expected Outcome: 
			- Edit page successfully.
         */
		
		hp.goToWiki();
		wHome.goToAPage(newTitle);
		wHome.goToEditPage();
		rtMode.editSimplePage("",newTitle);
		wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_CONTENT.replace("${text}",newTitle),2000,0);
		
		/*Step Number: 5
		 *Step Name: Step 5: Check Publish activity
		 *Step Description: 
			- Go to Homepage
 
		 *Input Data: 

		 *Expected Outcome: 
			- Show activity of page after editing
			-Show "Page's content has been edited." in comment list.
         */
		hp.goToHomePage();
		hpAct.checkActivity(newTitle);
		hpAct.checkCommentOfActivity(newTitle,comment1);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
	
	/**
	 *<li> Case ID:122848.</li>
	 *<li> Test Case Name:Edit page when publish activity is not checked.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_EditAPageWhenPublishActivityIsNotChecked() {
		info("Test 07: Edit page when publish activity is not checked");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Step 1: Add new page
		 *Step Description: 
			- Go to wiki page
			- Add new page

 
		 *Input Data: 

		 *Expected Outcome: 
			- Add new page successfully */
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		/*Step Number: 2
		 *Step Name: Step 2: Edit page ( title of page)
		 *Step Description: 
			- Select page above
			- Click Edit page
			- Change something in title 
			- UnCheck Publish Activity
			- Click Save

 
		 *Input Data: 

		 *Expected Outcome: 
			- Edit page successfully */
		wHome.goToEditPage();
		rtMode.editSimplePage(newTitle,"");
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check Publish activity
		 *Step Description: 
			- Go to Homepage
 
		 *Input Data: 

		 *Expected Outcome: 
			Don't show activity of page after editing
         */
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",newTitle),3000,0);
		
		/*Step Number: 4
		 *Step Name: Step 4: Edit page ( content of page)
		 *Step Description: 
			- Select page above
			- Click Edit page
			- Change something in content
			- UnCheck Publish Activity
			- Click Save
 
		 *Input Data: 

		 *Expected Outcome: 
			- Edit page successfully.
         */
		
		hp.goToWiki();
		wHome.goToAPage(newTitle);
		wHome.goToEditPage();
		rtMode.editSimplePage("",newTitle);
		wikiMg.saveAddPage();
		waitForAndGetElement(wikiMg.ELEMENT_WIKI_PAGE_CONTENT.replace("${text}",newTitle),2000,0);
		
		/*Step Number: 5
		 *Step Name: Step 5: Check Publish activity
		 *Step Description: 
			- Go to Homepage
 
		 *Input Data: 

		 *Expected Outcome: 
			Don't show activity of page after editing
         */
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",newTitle),3000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
	
}