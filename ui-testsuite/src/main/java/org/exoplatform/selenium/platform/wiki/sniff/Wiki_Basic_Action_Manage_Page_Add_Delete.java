package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Basic_Action_Manage_Page_Add_Delete extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122806.</li>
	 *<li> Test Case Name: Create page using Source Editor.</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122820.</li>
	 *<li> Test Case Name: Delete page with Source Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test02_09_Create_Delete_PageUsingSourceEditor() {
		info("Test 02: Create page using Source Editor");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Create Page with Source Editor
		 *Step Description: 
			- Go to Wiki
			- Click on Add Page -> Blank Page icon in toolbar action
			- Input valid data for Title page and Page's content
			- Click on Save icon in toolbar

		 *Input Data: 

		 *Expected Outcome: 
			Page is added successful and listed in navigation tree*/ 
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,1);
		
		info("Test 08: Delete the page that is created by using source editor");
		/*Step Number: 1
		 *Step Name: Step 1: Delete page with Source Editor
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Delete page action in menu
			- Click on OK button on Confirm message form


		 *Input Data: 

		 *Expected Outcome: 
			Delete page successfully*/ 
		hp.goToWiki();
		wHome.deleteWiki(wiki);
	}

	/**
	 *<li> Case ID:122807.</li>
	 *<li> Test Case Name: Create Page using Rich Text Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122818.</li>
	 *<li> Test Case Name: Delete Page with Rich Text Editor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_08_Create_Delete_PageUsingRichTextEditor() {
		info("Test 01: Create Page using Rich Text Editor");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Create Page with Rich Text Editor
		 *Step Description: 
			- Go to Wiki
			- Click Add Page => Blank Page icon in toolbar action
			- Click on Rich Text  Editor icon in toolbar
			- Input data valid for Title page and Page's content
			- Insert image, link, table and macro... to page's content
			- Click on Save icon in toolbar

		 *Input Data: 

		 *Expected Outcome: 
			- A new page is added successful and displayed with properties 
			- This page is listed in navigation tree*/

		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		
		info("Test 09: Delete the page that is created by using RichText");
		/*Step Number: 1
		 *Step Name: Step 1: Delete Page with Rich Text Editor
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Delete page action in menu
			- Click on OK button on Confirm message form


		 *Input Data: 

		 *Expected Outcome: 
			Delete page successfully*/
		hp.goToWiki();
		wHome.deleteWiki(wiki);
	}

	/**
	 *<li> Case ID:122823.</li>
	 *<li> Test Case Name: Auto Save when adding page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test 
	public  void test03_AutoSaveWhenAddingPage() {
		info("Test 3: Auto Save when adding page");
	
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check auto save when add page
		 *Step Description: 
			- Go to wiki
			- Click [Add Page]  -> [Blank Page] in toolbar action
			- Input  valid data for Title page and Page's content (Ex: Title -Auto Save, Content - Auto Save)
 
		 *Input Data: 

		 *Expected Outcome: 
			- The draft will be saved automatically after 30 seconds (default value) only 
			if there's any modifications in the content or page title.
			After the draft was saved, a notification message will be showed near the title.*/
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePageWithAutoSaveStatus(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(wiki);
	}
	
	/**
	 *<li> Case ID:122834.</li>
	 *<li> Test Case Name: Create page from template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_10_Create_Delete_PageFromTemplate() {
		info("Test 04: Create page from template");
		String template = wTempData.getWikiTemplateRandom();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Create Page from template
		 *Step Description: 
			- Go to wiki porlet
			- Click on Add Page -> From Template icon in toolbar action
			- Choose a template in list and click Select
			- Click on Save icon in toolbar

 
		 *Input Data: 

		 *Expected Outcome: 
			Page is added successful and listed in navigation tree*/
		hp.goToWiki();
		wHome.goToAddTemplateWikiPage();
		wikiMg.addSimpleWikiPageByTemplate(template,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",title),2000,0);
		
		info("Test 10: Delete the page that is created by using from template");
		/*Step Number: 1
		 *Step Name: Step 1: Delete page with template layout
		 *Step Description: 
			- Open an existing page by clicking on page name in navigation tree.
			- Click on More icon on toolbar and select Delete page action in menu
			- Click on OK button on Confirm message form

		 *Input Data: 

		 *Expected Outcome: 
			Delete page successfully*/
		hp.goToWiki();
		wHome.deleteWiki(title);
	}
	
	/**
	 *<li> Case ID:122837.</li>
	 *<li> Test Case Name:Preview template when adding new page from template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_PreviewTemplateWhenAddingNewPageFromTemplate() {
		info("Test 05: Preview template when adding new page from template");
		String template = wTempData.getWikiTemplateRandom();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Preview template
		 *Step Description: 
			- Go to wiki porlet
			- Click on Add Page -> From Template icon in toolbar action
			- Choose a template in list and click Preview

 
		 *Input Data: 

		 *Expected Outcome: 
			Show layout which you choose */
		hp.goToWiki();
		wHome.goToAddTemplateWikiPage();
		wikiMg.previewATemplate(template);
	}
	
	/**
	 *<li> Case ID:122841.</li>
	 *<li> Test Case Name:Auto Save when adding page from template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_AutoSaveWhenAddingPageFromTemplate() {
		info("Test 06: Auto Save when adding page from template");
		String template = wTempData.getWikiTemplateRandom();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Preview template
		 *Step Description: 
			- Go to wiki porlet
			- Click on Add Page -> From Template icon in toolbar action
			- Choose a template in list and click Preview

 
		 *Input Data: 

		 *Expected Outcome: 
			Show layout which you choose */
		hp.goToWiki();
		wHome.goToAddTemplateWikiPage();
		wikiMg.addSimplePageByTemplateWithAutoSave(template,title);
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",template),2000,0);
		
		info("Delete the page");
		hp.goToWiki();
		wHome.deleteWiki(title);
	}
	
	/**
	 *<li> Case ID:122852.</li>
	 *<li> Test Case Name:Resume a draft with save as normal.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_11_Resume_Delete_ADraftWithSaveAsNormal() {
		info("Test 07: Resume a draft with save as normal");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Add new page
		 *Step Description: 
			- Go to Intranet/Wiki
			- Add a page

 
		 *Input Data: 

		 *Expected Outcome: 
			A draft version is saved after 30s */
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePageHasAutoSaveWithoutSave(title,title);
		
		
		/*Step Number: 2
		 *Step Name: Step 2: Resume a draft with save as normal
		 *Step Description: 
			- Close a window of the browser without saving of the page
			- Go to "My drafts"
			- Click on the link "title" of the draft
			- Change title or content of page and click Save

 
		 *Input Data: 

		 *Expected Outcome: 
			- The draft is displayed in the list
			- The page in edit mode is displayed
			- New page is added and don't show in "My draft" list*/
		wHome.goToMyDraft();
		info("The draft is displayed in the list");
		wDraft.resumeADraft(title);
		info("The page in edit mode is displayed");
		rtMode.editSimplePage(newTitle, newTitle);
		wikiMg.saveAddPage();
		info("Verify that the new page is added");
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",newTitle),2000,0);
	    info("Verify that the new page is not shown in my draft list");
		wHome.goToMyDraft();
		waitForElementNotPresent(wDraft.ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", newTitle));
		
		info("Test 11: Delete a draft");
		/*Step Number: 1
		 *Step Name: Step 1: Delete a draft
		 *Step Description: 
			- Go to Browser -> My drafts
			- Select a page and click delete draft
			- A pop up is displayed "Are you sure to delete this draft?"
			- Click OK button
 
		 *Input Data: 

		 *Expected Outcome: 
			Delete draft successfully and don't show in draft list*/
		wDraft.deleteDraft(title);
		
		info("Delete the page ");
		hp.goToWiki();
		wHome.deleteWiki(newTitle);
	}
	
}