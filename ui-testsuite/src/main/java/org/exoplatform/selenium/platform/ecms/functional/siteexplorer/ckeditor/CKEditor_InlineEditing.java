package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsAcme;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.testng.annotations.*;

	/**
	* @author eXo
	*
	*/
	public class CKEditor_InlineEditing extends PlatformBase{

		Button button;
		ManageAccount magAcc;
		NavigationToolbar navToolBar;
		ActionBar actBar;
		ManageAlert magAlt;

		//Ecms
		EcmsBase ecms;
		EcmsPermission ecmsPer;
		EcmsAcme acme;
		ContextMenu cMenu;
		ContentTemplate cTemplate;

		@BeforeMethod
		public void beforeMethod(){
			initSeleniumTest();
			driver.get(baseUrl);
			button = new Button(driver, this.plfVersion);
			magAcc = new ManageAccount(driver, this.plfVersion);
			navToolBar = new NavigationToolbar(driver, this.plfVersion);
			actBar = new ActionBar(driver, this.plfVersion);
			ecms = new EcmsBase(driver, this.plfVersion);
			ecmsPer = new EcmsPermission(driver);
			cMenu = new ContextMenu(driver);
			cTemplate = new ContentTemplate(driver, this.plfVersion);
			magAlt = new ManageAlert(driver);
			acme = new EcmsAcme(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
		}

		@AfterMethod
		public void afterMethod() {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
	/**
	*<li> Case ID:119842.</li>
	*<li> Test Case Name: Cancel inline editing by clicking Cancel icon.</li>
	*/
	@Test
	public  void test01_CancelInlineEditingByClickingCancelIcon() {
		info("Test 1: Cancel inline editing by clicking Cancel icon");
		String title ="Ice";
		driver.get(baseUrl+"/acme");
		
		navToolBar.changeEditModeEnable();
		acme.editContentByTitle(title);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ALL_ICE);
		click(acme.ELEMENT_SELECT_BOLD_ICE);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_CANCEL_ICE);
		navToolBar.changeEditMode();
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		
		/*Step Number: 1
		*Step Name: Change to edit mode
		*Step Description: 
			- Log in as admin
			- Go to acme home page
			- Change to edit mode
		*Input Data: 
			
		*Expected Outcome: 
			Edit mode is activated*/

		/*Step number: 2
		*Step Name: Edit content
		*Step Description: 
			- Double click a content
			- Edit something
			- Select edited content then click B to make it bold or use some feature of CK editor
			- Click Refuse icon (red color)
		*Input Data: 
			
		*Expected Outcome: 
			- CKEditor is shown to allow user to edit
			- Edited content is not saved, Content still is original content*/ 

 	}

	/**
	*<li> Case ID:119841.</li>
	*<li> Test Case Name: Save inline editing by clicking Accept icon.</li>
	*<li> Pre-Condition: Package is started with all extensions</li>
	*/
	@Test
	public  void test02_SaveInlineEditingByClickingAcceptIcon() {
		info("Test 2: Save inline editing by clicking Accept icon");
		
		String title ="Ice";
		driver.get(DEFAULT_PLF_URL +"/acme");
		
		navToolBar.changeEditModeEnable();
		acme.editContentByTitle(title);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ALL_ICE);
		click(acme.ELEMENT_SELECT_BOLD_ICE);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ACCEPT_ICE);

		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		Utils.pause(5000);
		driver.navigate().refresh();
		
		/*Step Number: 1
		*Step Name: Change to edit mode
		*Step Description: 
			- Log in as admin
			- Go to acme home page
			- Change to edit mode
		*Input Data: 
			
		*Expected Outcome: 
			Edit mode is activated*/

		/*Step number: 2
		*Step Name: Edit content
		*Step Description: 
			- Double click a content
			- Edit something
			- Select edited content then click B to make it bold or use some feature of CK editor
			- Click Accept icon (green color)
		*Input Data: 
			
		*Expected Outcome: 
			- CKEditor is shown to allow user to edit
			- Edited content is saved and displayed as edited*/ 
		info("restore data");
		acme.editContentByTitle(title);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ALL_ICE);
		click(acme.ELEMENT_SELECT_BOLD_ICE_ON);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ACCEPT_ICE);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		Utils.pause(5000);
 	}

	/**
	*<li> Case ID:119843.</li>
	*<li> Test Case Name: Save inline editing by clicking outside of content.</li>
	*
	*/
	@Test
	public  void test03_SaveInlineEditingByClickingOutsideOfContent() {
		info("Test 3: Save inline editing by clicking outside of content");
		
		String title ="Ice";
		driver.get(DEFAULT_PLF_URL +"/acme");
		
		navToolBar.changeEditModeEnable();
		acme.editContentByTitle(title);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ALL_ICE);
		click(acme.ELEMENT_SELECT_BOLD_ICE);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		//click in an other place
		mouseOverAndClick(navToolBar.ELEMENT_MENU_EDIT_LINK);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		Utils.pause(5000);
		driver.navigate().refresh();
		/*Step Number: 1
		*Step Name: Change to edit mode
		*Step Description: 
			- Log in as admin
			- Go to acme home page
			- Change to edit mode
		*Input Data: 
			
		*Expected Outcome: 
			Edit mode is activated*/

		/*Step number: 2
		*Step Name: Edit content
		*Step Description: 
			- Double click a content
			- Edit something
			- Select edited content then click B to make it bold or use some feature of CK editor
			- Click outside of editing content
		*Input Data: 
			
		*Expected Outcome: 
			- CKEditor is shown to allow user to edit
			- Edited content is saved and displayed as edited*/ 
		info("restore data");
		acme.editContentByTitle(title);
		waitForAndGetElement(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ALL_ICE);
		click(acme.ELEMENT_SELECT_BOLD_ICE_ON);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		click(acme.ELEMENT_SELECT_ACCEPT_ICE);
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		Utils.pause(5000);
 	}
}
