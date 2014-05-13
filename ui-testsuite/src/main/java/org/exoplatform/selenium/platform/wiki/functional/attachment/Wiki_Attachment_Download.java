package org.exoplatform.selenium.platform.wiki.functional.attachment;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**--
 *-- Author: HaNTV
 *-- Date: 22 Jan 2014
 */
/**
 * Migrate to PLF4
 * <li> Update by @author vuna2 </li>
 *
 */
public class Wiki_Attachment_Download extends BasicAction{
	ManageAccount magAcc ;
	Button button;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver);
		button = new Button(driver);
		magAcc.signIn(DATA_USER1,DATA_PASS);;
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 69741
	 * Case 01: Download attachment file to folder
	 * create a wiki page
	 * Scroll down, click Upload, choose an image
	 * Verify result
	 * 
	 * Click Cancel
	 */

	@Test
	public void test01_DownloadAttachment(){
		String DATA_WIKI_PAGE_NAME="Blank_Wiki_Page_With_txt_File";
		String DATA_WIKI_PAGE_CONTENT="Blank_Wiki_Page_With_txt_Content";
		String ATTACHMENT_NAME="ECMS_CSS_File_After.txt";
		String ATTACHMENT_PATH="TestData/"+ATTACHMENT_NAME;
		By ATTACHMENT_FILE_LINK=By.xpath("//*[text()='"+ATTACHMENT_NAME+"']");
		info("Attach one txt file for wiki page");
		goToAddBlankPage();
		addWikiPageSourceEditor(DATA_WIKI_PAGE_NAME, DATA_WIKI_PAGE_CONTENT);
		attachFileInWiki(ATTACHMENT_PATH, 2);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		click(ELEMENT_ATTACHMENT_ICON);
		click(ELEMENT_ATTACHMENT_TITLE.replace("${fileName}", ATTACHMENT_NAME));
		deleteAnAttachment(ATTACHMENT_NAME);
		waitForElementNotPresent(ATTACHMENT_FILE_LINK);
		deleteCurrentWikiPage();
	}
}

