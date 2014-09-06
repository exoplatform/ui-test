package org.exoplatform.selenium.platform.wiki.functional.macro;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.exoplatform.selenium.platform.wiki.RichTextMode;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: LanLTK
 * @date: 03/24/2014
 */

public class Wiki_Macro_Othercheck extends ManageDraft{

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
		magAc.signIn(DATA_USER1,DATA_PASS);; 
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseID: 108295 - Collapse/Expand All macro
	 */
	
	@Test	
	public void test01_CollapseExpandAllMacro(){
		String title = "Page_collapse_expand";
		String contentcolor = "Test color";
		String color ="red";
		String contenterror = "Test Error Message";
		String type = "Error";
		String cont = "";
		
		//Add Jira macro in SourceEditor; Can not add from RichTextMode
		info("Add new wiki page at Rich Text mode:");
		goToWiki();
		goToAddBlankPage();
		addWikiPageRichText(title, cont);
		typeEnterInRichText();
		
		//Save wiki page
		createColorMacro(color, contentcolor);
		createMessageMacro(type,contenterror);
		mouseOverAndClick(ELEMENT_MACRO_LINK);
		click(ELEMENT_MACRO_COLLAPSE_LINK);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement("//span[@class='macro-placeholder' and text()='error']");
		waitForAndGetElement("//span[@class='macro-placeholder' and text()='color']");
		switchToParentWindow();
		
		mouseOverAndClick(ELEMENT_MACRO_LINK);
		click(ELEMENT_MACRO_EXPAND_LINK);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${text}", contentcolor).replace("${color}", "red"));
		waitForAndGetElement(ELEMENT_MACRO_ERROR_MESSAGE.replace("${macro}", contenterror));
		switchToParentWindow();
		
		Utils.javaSimulateKeyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_C);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement("//span[@class='macro-placeholder' and text()='error']");
		waitForAndGetElement("//span[@class='macro-placeholder' and text()='color']");
		switchToParentWindow();
		
		Utils.javaSimulateKeyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_E);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${text}", contentcolor).replace("${color}", "red"));
		waitForAndGetElement(ELEMENT_MACRO_ERROR_MESSAGE.replace("${macro}", contenterror));
		switchToParentWindow();
		
		//Save wiki Page with Code macro
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	
				
		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}
}