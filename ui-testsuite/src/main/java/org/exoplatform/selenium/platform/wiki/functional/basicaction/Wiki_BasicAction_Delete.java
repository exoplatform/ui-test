package org.exoplatform.selenium.platform.wiki.functional.basicaction;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * 
 * @author HangNTT
 * @date: 7/12/2012
 */
public class Wiki_BasicAction_Delete extends ManageDraft {

	ManageAccount magAcc;
	Button button; 
	ManageAlert mAlert; 

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver);
		button = new Button(driver, this.plfVersion);	
		mAlert = new ManageAlert(driver, this.plfVersion);

		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 69743 + 69744
	 * Delete a wiki page with without confirm and confirm message
	 * 
	 */
	@Test
	public void test01_02_deletePageWithoutConfirmAndConfirmMessage(){
		By ELEMENT_PAGE1 = By.linkText("wiki1");
		goToWiki();
		addBlankWikiPage("wiki1", "wiki1", 0);
		click(ELEMENT_PAGE1);
		deleteCurrentWikiPage(true);
		waitForAndGetElement(ELEMENT_PAGE1);
		deleteCurrentWikiPage();
		waitForElementNotPresent(ELEMENT_PAGE1);
	}

	/**
	 * Case ID:70781.
	 * Test Case Name: Delete draft by the cancel of the page.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 * Issue: https://jira.exoplatform.org/browse/WIKI-493. But this issue was fixed
	 */
	@Test
	public  void test03_DeleteDraftByTheCancelOfThePage() {
		info("Test 1: Delete draft by the cancel of the page");
		String title = "Case 70781" + getRandomNumber();
		String content = "Content case 70781";

		/*Edit a wiki page
		 *Input Data: 
		 *Expected Outcome: The draft is saved after 30s		*/
		goToWiki();
		goToAddBlankPage();
		addWikiPageSourceEditor(title, content);
		Utils.pause(30000);
		waitForAndGetElement(ELEMENT_DRAFT_NOTIFY);

		/*
		- Click on the link [Cancel]
		 *Input Data: 
		 *Expected Outcome: A pop up appears "The draft was created. Do you want to keep it?"		*/
		button.cancel();
		waitForAndGetElement(ELEMENT_DRAFT_CONFIRM_POPUP);

		/*Confirm the pop up and click on the button [No]
		 *Input Data: 
		 *Expected Outcome: The Draft is cancelled The Wiki Home page is displayed		*/
		button.no();
		Utils.pause(1000);
		/*Click on the link [My draft]
		 *Input Data: 
		 *Expected Outcome: The draft is deleted from the list of draft		*/ 
		goToManageDraft();
		waitForElementNotPresent(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title));
	}

	/**
	 * Case ID:70782.
	 * Test Case Name: Delete draft from the menu "My Draft".
	 * Pre-Condition: Draft exist on the list "My Draft"
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test04_DeleteDraftFromTheMenuMyDraft() {
		info("Test 2: Delete draft from the menu My Draft");
		String title = "Title 70782";
		String content = "Content 70782";

		/*From the menu [Browse], choose[My draft]
		 *Input Data: 
		 *Expected Outcome: The list of drafts is displayed		*/
		goToWiki();
		goToAddBlankPage();
		addWikiPageSourceEditor(title, content);
		Utils.pause(30000);
		waitForAndGetElement(ELEMENT_DRAFT_NOTIFY);

		/*
		- Select a draft page
		- From the column [Action], click on the icon [Trash]
		 *Input Data: 
		 *Expected Outcome: A pop up is displayed "Are you sure to delete this draft?"		*/

		/*Click on the button [OK]
		 *Input Data: 
		 *Expected Outcome: The Draft is removed from the list		*/ 
		goToManageDraft();
		waitForAndGetElement(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title));
		deleteDraft(title);
	}

	/**
	 * Case ID:70783.
	 * Test Case Name: Delete the draft by saving the page.
	 * Pre-Condition: edit page already saved as draft
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test05_DeleteTheDraftBySavingThePage() {
		info("Test 3: Delete the draft by saving the page");
		String title = "Case 70783";
		String content = "Content 70783";

		/*
		- Go to [My Draft]
		- Select a draft page
		- Edit a draft
		- Click on the button [Save]
		 *Input Data: 
		 *Expected Outcome: 
		- The page is saved
		- The draft version become the published version		*/
		goToWiki();
		goToAddBlankPage();
		addWikiPageSourceEditor(title, content);
		Utils.pause(30000);
		waitForAndGetElement(ELEMENT_DRAFT_NOTIFY);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		/*From the menu [Browse], choose [My drafts]
		 *Input Data: 
		 *Expected Outcome: The page title isn't displayed on the list of drafts		*/ 
		goToManageDraft();
		waitForElementNotPresent(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title));

		//Delete data test
		goToWikiPage("Wiki Home/"+title);
		deleteCurrentWikiPage();
	}

	/**
	 * Case ID:70799.
	 * Test Case Name: View a draft for another user.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test06_ViewADraftForAnotherUser() {
		info("Test 4: View a draft for another user");
		String title = "Case 70799";
		String content = "Content case 70799";

		/*
		- Connect with the user A
		- Go to Intranet/Wiki
		- Add a page
		 *Input Data: 
		 *Expected Outcome: The draft is saved after 30s		*/
		goToWiki();
		goToAddBlankPage();
		addWikiPageSourceEditor(title, content);
		Utils.pause(30000);
		waitForAndGetElement(ELEMENT_DRAFT_NOTIFY);

		/*
		- Connect with the user B
		- Go to Intranet/Wiki
		- From the menu [Browse], choose [My drafts]
		 *Input Data: 
		 *Expected Outcome: The draft created by the user A doesn't appears in the list of drafts of the user B		*/ 
		goToManageDraft();
		waitForAndGetElement(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title));
		info("Login by other user");
		magAcc.userSignIn(userType.DEVELOPER);
		goToWiki();
		goToManageDraft();
		waitForElementNotPresent(ELEMENT_DRAFT_OF_NEW_PAGE.replace("${title}", title));

		//Delete data test
		magAcc.userSignIn(userType.ADMIN);
		goToWiki();
		goToManageDraft();
		deleteDraft(title);
	}

	/**
	 * Case ID:71244.
	 * Test Case Name: Delete image.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test07_DeleteImage() {
		info("Test 5: Delete image");
		String title = "Case 71244";
		String file = "Wiki_Sniff_Attachment_01.jpg";

		/*
		- Add new page or edit a page
		- Click on Rich TextEditor icon in toolbar
		- Choose image in page 
		-Click Image in menu and Select Remove image
		 *Input Data: 
		 *Expected Outcome: 
		- Image is removed successfully		*/ 
		goToWiki();
		goToAddBlankPage();
		Utils.pause(500);
		addWikiPageRichText(title, "");
		insertImageFile(file);
		Utils.pause(500);
		info("Remove attach image");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		mouseOverAndClick(ELEMENT_IMAGE_LINK_REMOVE);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForElementNotPresent(ELEMENT_CHECK_IMAGE.replace("${file}", file));
		switchToParentWindow();
		Utils.pause(500);
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		waitForElementNotPresent(ELEMENT_IMAGE_LINK_REMOVE);
	}

	/**
	 * Case ID:78502.
	 * Test Case Name: Remove attached file.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test08_RemoveAttachedFile() {
		info("Test 6: Remove attached file");
		String file = "Wiki_Sniff_Attachment_01.doc";
		String title = "Case 71245";

		/*
		- Select a page have attach file
		- Click on Rich TextEditor icon in toolbar
		- Select attach file in content
		- Click Link in menu and select Remove Link
		 *Input Data: 
		 *Expected Outcome: 
		- Link remove successful in page		*/ 
		goToWiki();
		Utils.pause(500);
		goToAddBlankPage();
		addWikiPageRichText(title, "");
		insertAttachNewFile(file,"","",true);
		Utils.pause(500);
		info("remove attach link");
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_REMOVE_LINK);
		Utils.pause(500);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_ADD_WIKI_PAGE_FRAME));
		waitForElementNotPresent(By.linkText(title));
		waitForMessage(file,DEFAULT_TIMEOUT,1,2);
	}

	/**
	 * Case ID:71246.
	 * Test Case Name: Remove email address.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test09_RemoveEmailAddress() {
		info("Test 7: Remove email address");
		String title = "Case 71246";
		String email = "test01@exoplatform.com";
		String label = "EmailAddress";
		String tooltip = "Email Address link";

		/*
		- Select a page have email address
		- Click onRich TextEditor icon in toolbar
		- Select email address link in content
		-Click Link in menu and Select Remove link
		 *Input Data: 
		 *Expected Outcome: Link is removed successfully in page		*/ 
		goToWiki();
		goToAddBlankPage();

		addWikiPageRichText(title, "");
		insertEmailLink2WikiPage(email, label, tooltip);
		Utils.pause(500);
		info("Remove email address");
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_REMOVE_LINK);
		
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForElementNotPresent(ELEMENT_CHECK_EMAIL.replace("${label}", label));
		switchToParentWindow();
		
		Utils.pause(500);
		mouseOverAndClick(ELEMENT_LINK);
		waitForElementNotPresent(ELEMENT_REMOVE_LINK);	
	}

	/**
	 * Case ID:71247.
	 * Test Case Name: Remove web page.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test10_RemoveWebPage() {
		info("Test 8 Remove web page");
		String title = "Case 71247";
		String webpage = "google.com";
		String label = "Webpage";
		String tooltip = "Webpage link";
		/*
		- Select a page have web page
		- Click on Rich TextEditor icon in toolbar
		- Choose a web page link
		-Click Link in menu
		- Choose Remove Link
		 *Input Data: 
		 *Expected Outcome: 
		- Web page link is removed successfully		*/
		goToWiki();
		goToAddBlankPage();
		addWikiPageRichText(title, "");
		insertwebpageLink2WikiPage(webpage, label, tooltip);
		Utils.pause(500);
		info("Remove webpage");
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_REMOVE_LINK);
		
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForElementNotPresent(ELEMENT_CHECK_WEB_PAGE.replace("${label}", label));
		switchToParentWindow();
		
		Utils.pause(500);
		mouseOverAndClick(ELEMENT_LINK);
		waitForElementNotPresent(ELEMENT_REMOVE_LINK);	
	}

	/**
	 * Case ID:71248.
	 * Test Case Name: Remove wiki page link.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/10 14:00:20
	 */
	@Test
	public  void test11_RemoveWikiPageLink() {
		info("Test 09 Remove wiki page link");
		String pageLink = "Page Link";
		String title = "Case 71248";
		String content = "Content case 71248";

		/*
		- Select a page have wiki page link
		- Click on Rich TextEditor icon in toolbar
		- Select link in content
		-Click Link in menu and choose Remove Link
		 *Input Data: 
		 *Expected Outcome: 
		- Link is removed successfully		*/ 
		goToWiki();
		addBlankWikiPage(pageLink, content, 0);
		Utils.pause(500);
		goToAddBlankPage();
		addWikiPageRichText(title, "");
		insertPageLink2WikiPage(true, pageLink, "Link to pageLink", "Go to pageLink");
		Utils.pause(500);
		info("Edit page");
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_REMOVE_LINK);
		
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForElementNotPresent(ELEMENT_CHECK_WIKI_PAGE_LINK.replace("${label}", "Link to pageLink"));
		switchToParentWindow();
		
		Utils.pause(500);
		mouseOverAndClick(ELEMENT_LINK);
		waitForElementNotPresent(ELEMENT_REMOVE_LINK);

		//Delete data test
		click(By.linkText(pageLink));
		deleteCurrentWikiPage();
	}
}