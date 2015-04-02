package org.exoplatform.selenium.platform.wiki.functional.attachment;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.Set;

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
 *
 */
public class Wiki_Attachment_Add extends BasicAction{
	ManageAccount magAcc ;
	Button button;

	@BeforeMethod
	public void setUpBeforeTest(){
		getDriverAutoSave();
		driver.get(baseUrl);
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
	 * Qmetry ID: 69696
	 * Case 01: Add image file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose an image
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public void test01_AttachAnImageForPage(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_Image1";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_Image_Content";
		String ATTACHMENT_NAME="Winter.jpg";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME;
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach one image for wiki page");

		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);

		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		//click(ELEMENT_CANCEL_BUTTON);
		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry Id: 69694
	 * Case 02: Add pdf file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a pdf file
	 * Verify result
	 * Click Cancel
	 */
	@Test
	public  void test02_AttachPdfFileForPage(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_PDFFile";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_PDFFile_Content";
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach pdf for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		//click(ELEMENT_CANCEL_BUTTON);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}


	/**
	 * Qmetry ID: 69695
	 * Case 03: Add txt file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a txt file
	 * Verify result
	 * Click Cancel
	 */
	@Test
	public  void test03_AttachTxtFileForPage(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_txt_File";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_txt_File_Content";
		String ATTACHMENT_NAME="KS_WiKi_Attachment_TxtFile.txt";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach txt file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		//click(ELEMENT_CANCEL_BUTTON);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry ID: 69698
	 * Case 04: Add office file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose an office file
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public  void test04_AttachOfficeFileForPage(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_Office_File";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_Office_File_Content";
		String ATTACHMENT_NAME="KS_Wiki_Attachment_Office_file.doc";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach Office file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);

		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE);
		//click(ELEMENT_CANCEL_BUTTON);
		waitForTextPresent(MESSAGE_CANCEL_CREATE_PAGE);
		click(button.ELEMENT_OK_BUTTON);
	}

	/**
	 * Qmetry ID: 69697
	 * Case 05: Add music file for page
	 * Create a wiki page
	 * Scroll down, click Upload, choose a music file
	 * Verify result
	 * * Click Cancel
	 */
	@Test
	public  void test05_AttachMusicFileForPage(){

		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_mp3_File";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_mp3_File_Content";
		String ATTACHMENT_NAME="KS_Wiki_Attachment_AllMyLove.mp3";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");

		info("Attach mp3 file for wiki page");
		goToAddBlankPage();

		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ATTACHMENT_FILE_LINK);
		click(ATTACHMENT_FILE_LINK);
		switchToNewWindow();
		switchToParentWindow();
		assert checkFileExisted(ATTACHMENT_NAME);
	}

	/**
	 * Case ID:113787.
	 * Test Case Name: Attach any files for a wiki page.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 */
	@Test
	public  void test06_AttachAnyFilesForAWikiPage() {
		info("Test 6: Attach any files for a wiki page");
		String DATA_WIKI_PAGE_NAME="wiki113787";
		String DATA_WIKI_PAGE_CONTENT="content113787";
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
		click(ATTACHMENT_FILE_LINK);
		Utils.pause(3000);
		/*Step number: 3
		 *Step Name: Step 3: View content of attachments
		 *Step Description: 
			- Click any attached file named text1.pdf
		 *Input Data: 

		 *Expected Outcome: 
			Content of text1.pdf is shown in a new tab*/
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
		{
			if(!windowHandle.equals(parentWindow))
			{
				driver.switchTo().window(windowHandle);
				String actUrl=driver.getCurrentUrl();
				info(URL);
				info(actUrl);
				assert actUrl.contains(URL);	
				driver.close(); 
				driver.switchTo().window(parentWindow); 
			}
		}

		/*Step number: 4
		 *Step Name: Step 4: Save the page with attached PDF files
		 *Step Description: 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			- The page is shown with the number of attachments at the Info bar of page (next to Attachment icon)*/
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));

		/*Step number: 5
		 *Step Name: Step 5: View attached PDF file
		 *Step Description: 
			- Click number of attachments next to Attachment icon on the info bar of page
			- Click any file
		 *Input Data: 

		 *Expected Outcome: 
			- Attachments pane is shown at the bottom
			- The file file is shown in another tab*/
		click(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));
		waitForAndGetElement(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));

		/*Step number: 6
		 *Step Name: Step 6: Hide Attachment pane
		 *Step Description: 
			- Click again the number of attachments at the Info bar
		 *Input Data: 

		 *Expected Outcome: 
			- The Attachments pane is disappeared*/
		click(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));
		waitForElementNotPresent(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));

		info("Clear data");
		deleteCurrentWikiPage();	
	}

	/**
	 * Case ID:113786.
	 * Test Case Name: Attach 2 files with the same name.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 * ERROR: https://jira.exoplatform.org/browse/WIKI-924
	 */
	@Test
	public  void test07_Attach2FilesWithTheSameName() {
		info("Test 7: Attach 2 files with the same name");
		String DATA_WIKI_PAGE_NAME="wiki113786";
		String DATA_WIKI_PAGE_CONTENT="content113786";
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
	}

	/**
	 * Case ID:113790.
	 * Test Case Name: Show Upload New File button of an existing wiki page.
	 * Pre-Condition: - A wiki page is already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 */
	@Test
	public  void test08_ShowUploadNewFileButtonOfAnExistingWikiPage() {
		info("Test 8 Show Upload New File button of an existing wiki page");
		String DATA_WIKI_PAGE_NAME="wiki113790";
		String DATA_WIKI_PAGE_CONTENT="content113790";
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
		waitForAndGetElement(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));

		/*Step number: 2
		 *Step Name: Step 2: View Attachments pane
		 *Step Description: 
			- Click the number of attachments at Info bar
		 *Input Data: 

		 *Expected Outcome: 
			- The Attachments pane is shown at the bottom
			- All existing attachments are shown in this pane with [Upload New File] button*/ 
		click(ELEMENT_ATTACHMENT_NUMBER.replace("${No}", "1"));
		waitForAndGetElement(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		waitForAndGetElement(ELEMENT_UPLOAD_NEW_FILE_BUTTON);
		info("Clear data");
		deleteCurrentWikiPage();	
	}

	/**
	 * Case ID:109768.
	 * Test Case Name: Show Upload New File button when creating a new wiki page
	 * Pre-Condition: - A wiki page is already created
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by phuongdt at 2014/09/24 16:09:43
	 */
	@Test
	public  void test09_ShowUploadNewFileButtonWhenCreatingANewWikiPage() {
		info("Test 9 Show Upload New File button of an existing wiki page");
		String DATA_WIKI_PAGE_NAME="wiki109768";
		String DATA_WIKI_PAGE_CONTENT="content109768";
		String ATTACHMENT_NAME="KS_Wiki_Attachment_pdffile.pdf";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME+"";

		/*Step Number: 1
		 *Step Name: Step 1: Create a wiki page
		 *Step Description: 
			- Go to [Add Page] --> [Blank Page]/[From Template...]
		 *Input Data: 

		 *Expected Outcome: 
			- Form to add new page appears.*/
		goToAddBlankPage();
		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);

		/*Step number: 2
		 *Step Name: Step 2: Show Upload New File button
		 *Step Description: 
			- Scroll down to the bottom
		 *Input Data: 

		 *Expected Outcome: 
			- The Attachments pane is shown at the bottom with [Upload New File] button*/ 
		attachFileInWiki(ATTACHMENT_PATH, 2);
		waitForAndGetElement(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		waitForAndGetElement(ELEMENT_UPLOAD_NEW_FILE_BUTTON);
	}
}