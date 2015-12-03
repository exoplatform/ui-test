package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.wiki.RichTextEditor.wikiPageLinkTab;
import org.testng.annotations.*;


	public class Wiki_BasicAction_Delete extends WIKI_TestConfig{

	/**
	*<li> Case ID:139425.</li>
	*<li> Test Case Name: Delete page when agree on confirm message.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_DeletePageWhenAgreeOnConfirmMessage() {
		info("Test 1: Delete page when agree on confirm message");
		/*Step Number: 1
		*Step Name: Step 1: Create a page
		*Step Description: 
			- Click [Add Page] 
			-
			-> [Blank Page]/[From Template...]
			- Select [Source Editor] to switch to [Source Editor] mode
			- Put title, content 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode 
			- New page is created successful.*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);

		/*Step number: 2
		*Step Name: Step 2: Delete page when OK on confirm message
		*Step Description: 
			- Select page above
			- Click [More] 
			-
			-> [Delete Page]
			- Click OK on confirm message
		*Input Data: 
			
		*Expected Outcome: 
			Page is deleted successfully*/ 
		info("Page is deleted successfully");
		hp.goToWiki();
		wHome.deleteWiki(title);

 	}

	/**
	*<li> Case ID:139426.</li>
	*<li> Test Case Name: Delete page when cancel on confirm message.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_DeletePageWhenCancelOnConfirmMessage() {
		info("Test 2: Delete page when cancel on confirm message");
		/*Step Number: 1
		*Step Name: Step 1: Create a page
		*Step Description: 
			- Click [Add Page] 
			-
			-> [Blank Page]/[From Template...]
			- Select [Source Editor] to switch to [Source Editor] mode
			- Put title, content 
			- Click [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode 
			- New page is created successful.*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);

		/*Step number: 2
		*Step Name: Step 2: Delete page when cancel on confirm message
		*Step Description: 
			- Select page above
			- Click [More] 
			-
			-> [Delete Page]
			- Click [Cancel] on confirm message
		*Input Data: 
			
		*Expected Outcome: 
			Page is not deleted*/
		info("Page isNOT deleted");
		hp.goToWiki();
		wHome.cancelDeleteWiki(title);

 	}

	/**
	*<li> Case ID:139441.</li>
	*<li> Test Case Name: Delete draft by the cancel of the page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_DeleteDraftByTheCancelOfThePage() {
		info("Test 3: Delete draft by the cancel of the page");
		/*Step Number: 1
		*Step Name: Step 1. Verify the manager draft
		*Step Description: 
			- Select a wiki page
			- Click [Edit]
			- Make changes on the wiki page without saving for 30s
		*Input Data: 
			
		*Expected Outcome: 
			The draft is saved after 30s*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Edit the page");
		String newTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String newContent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(title);
		wHome.goToEditPage();
		wikiMg.goToSourceEditor();
		sourceEditor.editSimplePageWithAutoSave(newTitle, newContent);

		/*Step number: 2
		*Step Name: Step 2. Cancel a page in edit mode
		*Step Description: 
			- Click on the link [Cancel]
		*Input Data: 
			
		*Expected Outcome: 
			A pop up appears "The draft was created. Do you want to keep it?"*/
		
		
		/*Step number: 3
		*Step Name: Step 3. Cancel a page in edit mode
		*Step Description: 
			- Click on the button [No] in the confirmation message
		*Input Data: 
			
		*Expected Outcome: 
			The Draft is cancelled The Wiki Home page is displayed*/
		info("The draft was created. Do you want to keep it?");
		String mess="";
		wikiMg.cancelAddPage();
		wValidate.verifyWarningMessage(mess);
		wHome.confirmWaringMessage(true);

		/*Step number: 4
		*Step Name: Step 4. Verify the manager draft
		*Step Description: 
			- Click [Browse] 
			-
			-> [My Drafts]
		*Input Data: 
			
		*Expected Outcome: 
			The draft is deleted from the list of draft*/ 
		info("The draft is deleted from the list of draft");
		wHome.goToMyDraft();
		wValidate.verifyNotTitleDrafPage(newTitle);
		wValidate.verifyNotTitleDrafPage(title);

 	}

	/**
	*<li> Case ID:139442.</li>
	*<li> Test Case Name: Delete draft from the menu "My Draft".</li>
	*<li> Pre-Condition: Draft exist on the list "My Draft"</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_DeleteDraftFromTheMenuMyDraft() {
		info("Test 4: Delete draft from the menu My Draft");
		/*Step Number: 1
		*Step Name: Step 1: Show My Draft list
		*Step Description: 
			From the menu [Browse], choose[My Drafts]
		*Input Data: 
			
		*Expected Outcome: 
			The list of drafts is displayed*/
		info("Create a draf wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
		
		info("The draft is shown from the list of draft");
		wHome.goToMyDraft();
		wValidate.verifyTitleDrafPage(title);

		/*Step number: 2
		*Step Name: Step 2: Delete the draft
		*Step Description: 
			- Select a draft page
			- From the column [Action], click on the icon [Trash]
		*Input Data: 
			
		*Expected Outcome: 
			A pop up is displayed "Are you sure you want to delete this draft?"*/

		/*Step number: 3
		*Step Name: Step 3: Click OK to confirm
		*Step Description: 
			Click on the button [OK]
		*Input Data: 
			
		*Expected Outcome: 
			The Draft is removed from the list*/ 
		info("A pop up is displayed 'Are you sure you want to delete this draft?'");
		wDraf.deleteDraft(title);

 	}

	/**
	*<li> Case ID:139443.</li>
	*<li> Test Case Name: Delete the draft by saving the page.</li>
	*<li> Pre-Condition: edit page already saved as draft</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_DeleteTheDraftBySavingThePage() {
		info("Test 5: Delete the draft by saving the page");
		/*Step Number: 1
		*Step Name: Step 1: Edit a draft page by Saving
		*Step Description: 
			- Go to [Browse] 
			-
			-> [My Draft]
			- Select a draft page
			- Click a draft to edit it
			- Click on the button [Save]
		*Input Data: 
			
		*Expected Outcome: 
			- The page is saved
			- The draft version become the published version*/
		
		info("Create a draf wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePageHasAutoSaveWithoutSave(title, content);
		
		info("The draft is shown from the list of draft");
		wHome.goToMyDraft();
		wValidate.verifyTitleDrafPage(title);
		wDraf.resumeADraft(title);
		wikiMg.saveAddPage();
		info("The page is saved");
		wValidate.verifyTitleWikiPage(title);

		/*Step number: 2
		*Step Name: Step 2: Verification on the draft manager
		*Step Description: 
			From the menu [Browse], choose [My Drafts]
		*Input Data: 
			
		*Expected Outcome: 
			The page title isn't displayed on the list of drafts*/
		
		info("The draft version become the published version");
		wHome.goToMyDraft();
		wValidate.verifyNotTitleDrafPage(title);
 	}

	/**
	*<li> Case ID:139456.</li>
	*<li> Test Case Name: View a draft for another user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_ViewADraftForAnotherUser() {
		info("Test 6: View a draft for another user");
		/*Step Number: 1
		*Step Name: Step 1: Add a draft page for user A
		*Step Description: 
			- Connect with the user A
			- Go to [Intranet] 
			-
			-> [Wiki]
			- Click [Add Page] 
			-
			-> [Blank Page]/[From Template...]
			- Input values for the page without saving for 30s
		*Input Data: 
			
		*Expected Outcome: 
			The draft is saved after 30s*/
		info("Create a draf wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePageHasAutoSaveWithoutSave(title, content);

		/*Step number: 2
		*Step Name: Step 2: View Draft page by user B
		*Step Description: 
			- Connect with the user B
			- Go to [Intranet] 
			-
			-> [Wiki]
			- From the menu [Browse], choose [My Drafts]
		*Input Data: 
			
		*Expected Outcome: 
			The draft created by the user A doesn't appear in the list of drafts of the user B*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER2,DATA_PASS);
		hp.goToWiki();
		wHome.goToMyDraft();
		wValidate.verifyNotTitleDrafPage(title);

 	}

	/**
	*<li> Case ID:139539.</li>
	*<li> Test Case Name: Delete image.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_DeleteImage() {
		info("Test 7: Delete image");
		/*Step Number: 1
		*Step Name: Step 1: Remove image
		*Step Description: 
			- Add new page or edit a page
			- Ensure the page is in the [Rich Text] editor
			- Click an image in page 
			- Click [Image] in menu and select [Remove Image]
		*Input Data: 
			
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode 
			- If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
			- If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
			- Image is removed successfully*/ 
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String attachedFile = attFileData.getAttachFileByArrayTypeRandom(26);
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title,"");
		richEditor.goToAttachedImageLink();
		richEditor.insertImage("TestData/"+attachedFile,false);
		richEditor.removeImage(attachedFile);

 	}

	/**
	*<li> Case ID:139540.</li>
	*<li> Test Case Name: Remove link of an attached file.</li>
	*<li> Pre-Condition: - A wiki page is created
	- Add attachment(s) to the page</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_RemoveLinkOfAnAttachedFile() {
		info("Test 8: Remove link of an attached file");
		/*Step Number: 1
		*Step Name: Step 1: Remove attach file
		*Step Description: 
			- Select a page which has an attached file
			- Click [Edit]
			- Ensure the page is in the [Rich Text] editor
			- Select the attached file
		*Input Data: 
			
		*Expected Outcome: 
			- Add a link for one of the attachments*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to [Link] in menu 
			- Select [Remove Link]
		*Input Data: 
			
		*Expected Outcome: 
			- The link is removed successfully in thein page. 
			- The title of Link still exists.*/ 
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String attachedFile = attFileData.getAttachFileByArrayTypeRandom(26);
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title,"");
		richEditor.insertAttachedFileLink("TestData/"+attachedFile,false);
		wValidate.verifyInsertedLinkIntoFrame(attachedFile,"");
		richEditor.removeLink(attachedFile);

 	}

	/**
	*<li> Case ID:139541.</li>
	*<li> Test Case Name: Remove link of an email address.</li>
	*<li> Pre-Condition: - A wiki page is created
	- The wiki page contains link to email address already</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_RemoveLinkOfAnEmailAddress() {
		info("Test 9: Remove link of an email address");
		/*Step Number: 1
		*Step Name: Step 1: Remove Email Address
		*Step Description: 
			- Select a page have email address
			- Click [Edit]
			- Ensure the page is in the [Rich Text]editor
			- Select email address link in content
			-Click Link in menu and select [Remove Link]
		*Input Data: 
			
		*Expected Outcome: 
			- If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
			- If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
			- Link is removed successfully in page and the alternative text of Email Address still exists.*/ 
		info("Create a wiki page 2");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tooltip = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label =txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String address=getRandomString()+"@gmail.com";
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title1,content1);
		richEditor.insertEmailLink(address, label, tooltip,false);
		wValidate.verifyInsertedLinkIntoFrame(label, tooltip);
		richEditor.removeLink(label);
 	}

	/**
	*<li> Case ID:139542.</li>
	*<li> Test Case Name: Remove link of web page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_RemoveLinkOfWebPage() {
		info("Test 10 Remove link of web page");
		/*Step Number: 1
		*Step Name: Step 1: Remove link of web page
		*Step Description: 
			- Select a page have web page
			- Click [Edit]
			- Ensure the page is in the [Rich Text]editor
			- Choose a web page link
			- Click [Link] in menu
			- Choose [Remove Link]
		*Input Data: 
			
		*Expected Outcome: 
			- If the page is created in the [Rich Text] mode, the [Edit Page] will be displayed in the [Rich Text] mode.
			- If the page is created in the [Source Editor] mode, the [Edit Page] will be displayed in the [Source Editor] mode.
			- Web page link is removed successfully and title of web page still exists.*/ 
		info("Create a wiki page 2");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tooltip = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String address = "www.google.com";
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title1,content1);
		richEditor.goToWebPageLink();
		richEditor.insertWebLink(address, label, tooltip,false);
		wValidate.verifyInsertedLinkIntoFrame(label, tooltip);
		richEditor.removeLink(label);
 	}

	/**
	*<li> Case ID:139543.</li>
	*<li> Test Case Name: Remove wiki page link.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_RemoveWikiPageLink() {
		info("Test 11 Remove wiki page link");
		/*Step Number: 1
		*Step Name: Step 1: Remove wiki page link
		*Step Description: 
			- Select a page have wiki page link
			- Click [Edit]
			- Ensure the page is in the [Rich Text]editor
			- Select the wiki page link in content
			-Click [Link] in menu and choose [Remove Link]
		*Input Data: 
			
		*Expected Outcome: 
			- Link is removed successfully and title of wiki page still exists*/ 
		info("Create a wiki page 2");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tooltip = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title2,content2);
		richEditor.insertNewWikiPageLink(title1, label, tooltip, wikiPageLinkTab.My_Recent_Changes,false);
		wValidate.verifyInsertedLinkIntoFrame(label, tooltip);
		richEditor.removeLink(label);
 	}}