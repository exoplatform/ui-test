package org.exoplatform.selenium.platform.wiki.functional.attachment;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*--
 *-- Author: HaKT
 *-- Date: 10 Dec 2012
 **/
/**
 * Migrate to PLF4
 * <li> Update by @author vuna2 </li>
 * updated by anhpp
 */
public class Wiki_Attachment_Add extends BasicAction{
	ManageAccount magAcc ;
	Button button;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		button = new Button(driver);

		magAcc.signIn(DATA_USER1, DATA_PASS);
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 118089
	 * Case 01: Add image file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose an image
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public void test01_AttachAnImageForPage(){

		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="Winter.jpg";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME;
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach one image for wiki page");

		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);

		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry Id: 118087
	 * Case 02: Add pdf file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a pdf file
	 * Verify result
	 * Click Cancel
	 */
	@Test
	public  void test02_AttachPdfFileForPage(){

		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach pdf for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}


	/**
	 * Qmetry ID: 118088
	 * Case 03: Add txt file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a txt file
	 * Verify result
	 * Click Cancel
	 */
	@Test
	public  void test03_AttachTxtFileForPage(){

		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_WiKi_Attachment_TxtFile.txt";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach txt file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry ID: 118091
	 * Case 04: Add office file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose an office file
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public  void test04_AttachOfficeFileForPage(){

		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_Office_file.doc";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach Office file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry ID: 118090
	 * Case 05: Add music file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a music file
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public  void test05_AttachMusicFileForPage(){

		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_AllMyLove.mp3";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach mp3 file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);
		click(ATTACHMENT_FILE_LINK);
		/*switchToNewWindow();
		switchToParentWindow();*/
		assert checkFileExisted(ATTACHMENT_NAME);
		
	}

	/**
	 * Case ID:118094.
	 * Test Case Name: Attach any files for a wiki page.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 * PENDING: CANNOT OPEN NEW TAB BECAUSE WEBDRIVER IS NOT SUPPORTED TO OPEN NEW TAB WHEN CLICK ON THE LINK 
	 */
	@Test(groups="pending")
	public  void test06_AttachAnyFilesForAWikiPage() {
		info("Test 6: Attach any files for a wiki page");
		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");
		String URL = "KS_Wiki_Attachment_pdffile.pdf";
		
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add a new wiki page
		 *Step Description: 
			- Go to [Add Page] 
			-
			-> [Blank Page]/[From Template...]
			- Select [Source Editor]
		 *Input Data: 

		 *Expected Outcome: 
			- Form to add new page appears.*/
		goToAddBlankPage();
		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);
		
		/*Step number: 2
		 *Step Name: Step 2: Add many files
		 *Step Description: 
			- Move scroll at the end of page
			- Click on Upload
			- Select any files, for example: text1.pdf
		 *Input Data: 

		 *Expected Outcome: 
			Files are attached for page.*/
		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);
		click(ATTACHMENT_FILE_LINK);
		
		/*Step number: 3
		 *Step Name: Step 3: View content of attachments
		 *Step Description: 
			- Click any attached file named text1.pdf
		 *Input Data: 

		 *Expected Outcome: 
			Content of text1.pdf is shown in a new tab*/
		String HandleBefore = driver.getWindowHandle();
		info("Open new window");
		Utils.pause(5000);	
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
		Utils.pause(1000);
		info(URL);
		info(driver.getCurrentUrl());
		assert driver.getCurrentUrl().contains(URL);	
		driver.switchTo().window(HandleBefore);
		
		/*Step number: 4
		 *Step Name: Step 4: Save the page with attached PDF files
		 *Step Description: 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- The page is shown with the number of attachments at the Info bar of page (next to Attachment icon)*/
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${number}","1"));

		/*Step number: 5
		 *Step Name: Step 5: View attached PDF file
		 *Step Description: 
			- Click number of attachments next to Attachment icon on the info bar of page
			- Click any file
		 *Input Data: 

		 *Expected Outcome: 
			- Attachments pane is shown at the bottom
			- The file file is shown in another tab*/
		click(ELEMENT_ATTACHMENT_NUMBER_ICON);
		waitForAndGetElement(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		
		/*Step number: 6
		 *Step Name: Step 6: Hide Attachment pane
		 *Step Description: 
			- Click again the number of attachments at the Info bar
		 *Input Data: 

		 *Expected Outcome: 
			- The Attachments pane is disappeared*/
		click(ELEMENT_ATTACHMENT_NUMBER_ICON);
		waitForElementNotPresent(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		
		info("Clear data");
		deleteCurrentWikiPage();	
	}

	/**
	 * Case ID:118093.
	 * Test Case Name: Attach 2 files with the same name.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 * ERROR: https://jira.exoplatform.org/browse/WIKI-924
	 */
	@Test (groups = "pending")
	public  void test07_Attach2FilesWithTheSameName() {
		info("Test 7: Attach 2 files with the same name");
		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");
		
		/*Step Number: 1
		 *Step Name: Step 1: Open form to add a new wiki page
		 *Step Description: 
			- Go to [Add Page] 
			-
			-> [Blank Page]/[From Template...]
		 *Input Data: 

		 *Expected Outcome: 
			- Form to add new page appears.*/
		goToAddBlankPage();
		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);
		
		/*Step number: 2
		 *Step Name: Step 2: Add the first file named doc1.txt
		 *Step Description: 
			- Move scroll at the end of page
			- Click on Upload
			- Select doc1.txt
		 *Input Data: 

		 *Expected Outcome: 
			The file is attached for page.*/
		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);
		
		/*Step number: 3
		 *Step Name: Step 3: Add the second file with the same name doc1.txt
		 *Step Description: 
			- Continue clicking [Upload], then select doc1.txt
		 *Input Data: 

		 *Expected Outcome: 
			One warning shows: Already in use Keep both Replace or Cancel (similar to Upload case in Documents)*/
		attachFileInWiki(ATTACHMENT_PATH, 2);
		
		/*Step number: 4
		 *Step Name: Step 4: Select either of upload options
		 *Step Description: 
			- Select Keep both or Replace or Cancel
		 *Input Data: 

		 *Expected Outcome: 
			- If "Keep both", the file is still uploaded with an index added. For example, doc1.txt[1]
			- If "Replace", the second file will replace the first file.
			- If "Cancel", the second file is not uploaded*/ 
		
		assert checkFileExisted(ATTACHMENT_NAME);
		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Case ID:118097.
	 * Test Case Name: Show Upload New File button of an existing wiki page.
	 * Pre-Condition: - A wiki page is already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 */
	@Test
	public  void test08_ShowUploadNewFileButtonOfAnExistingWikiPage() {
		info("Test 8 Show Upload New File button of an existing wiki page");
		String number=getRandomNumber();
		String DATA_WIKI_PAGE_NAME="wiki"+number;
		String DATA_WIKI_PAGE_CONTENT="content"+number;
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");
		
		/*Step Number: 1
		 *Step Name: Step 1: Open a wiki page
		 *Step Description: 
			- Open a wiki page
		 *Input Data: 

		 *Expected Outcome: 
			- The wiki page is shown*/
		goToAddBlankPage();
		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);
		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${number}", "1"));
		
		/*Step number: 2
		 *Step Name: Step 2: View Attachments pane
		 *Step Description: 
			- Click the number of attachments at Info bar
		 *Input Data: 

		 *Expected Outcome: 
			- The Attachments pane is shown at the bottom
			- All existing attachments are shown in this pane with [Upload New File] button*/ 
		click(ELEMENT_ATTACHMENT_NUMBER_ICON);
		waitForAndGetElement(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		waitForAndGetElement(ELEMENT_UPLOAD_NEW_FILE_BUTTON);
		info("Clear data");
		deleteCurrentWikiPage();	
	}
	
	/**
	 * Case ID:118092
	 * Test Case Name: Show Upload New File button when creating a new wiki page
	 * Pre-Condition: - A wiki page is already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 */
	
     //REMOVED
	
}
