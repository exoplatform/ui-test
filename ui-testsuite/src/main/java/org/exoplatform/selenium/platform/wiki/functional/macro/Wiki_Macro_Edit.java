package org.exoplatform.selenium.platform.wiki.functional.macro;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.exoplatform.selenium.platform.wiki.RichTextMode;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: LanLTK
 * @date: 03/24/2014
 */

public class Wiki_Macro_Edit extends ManageDraft{

	ManageAccount magAc;
	Button button;
	WikiBase magWiki;
	RichTextMode magRTM;
	BasicAction magWikiAction;

	@BeforeMethod
	public void setUpBeforeTest(){
	    getDriverAutoSave();
		magAc = new ManageAccount(driver);
		button = new Button(driver);
		magWiki = new WikiBase();
		magRTM = new RichTextMode();
		magWikiAction = new BasicAction();
		magAc.signIn("john", "gtn"); 
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 *  CaseId 71282-78535: Edit JIRA macro
	 */
	
	@Test	
	public void test01_Edit_Jira_Macro(){
		String title = "Page 71281_71282";
		String content = "{{jira URL='https://jira.exoplatform.org/' style='table'}} SOC-123 {{/jira}}";
		String newcontent = "{{jira URL='https://jira.exoplatform.org/' style='table'}} ECMS-235 {{/jira}}";
		
		//Add Jira macro in SourceEditor; Can not add from RichTextMode
		info("Add new wiki page at Rich Text mode:");
		goToWiki();
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	
		 
		//Check the availability of JIRA macro
		waitForAndGetElement(By.xpath("//a[@href='https://jira.exoplatform.org/browse/SOC-123']"));
		
		//Edit page
		editWikiPage(title, newcontent, 0);
		
		//Check the availability of JIRA macro
		waitForAndGetElement(By.xpath("//a[@href='https://jira.exoplatform.org/browse/ECMS-235']"));
		
		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}
}
