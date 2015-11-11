package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * 
 * @author tult
 * March, 10th, 205
 *
 */

public class ECMS_SE_CreateNode_IllustratedWebContent extends PlatformBase{
		//Platform
		Button button;
		ManageAccount magAcc;
		NavigationToolbar navToolBar;
		ActionBar actBar;
		ManageAlert magAlt;

		//Ecms
		EcmsBase ecms;
		ContextMenu cMenu;
		ContentTemplate cTemplate;
		SitesExplorer sitesExp;

		@BeforeMethod
		public void beforeMethod(){
			initSeleniumTest();
			driver.get(plfURL);
			button = new Button(driver);
			magAcc = new ManageAccount(driver);
			navToolBar = new NavigationToolbar(driver);
			actBar = new ActionBar(driver);
			ecms = new EcmsBase(driver);
			cMenu = new ContextMenu(driver);
			cTemplate = new ContentTemplate(driver,this.plfVersion);
			magAlt = new ManageAlert(driver);
			sitesExp = new SitesExplorer(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
		}

		@AfterMethod
		public void afterMethod() {
			info("-- User signOut --");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
		
		/**
		 * Qmetry ID: 119409
		 * Add Illustrated Web Content content in Web content folder
		 * 
		 */
		@Test
		public void test01_AddIllustratedWebContentInWebContentFolder(){
			String ILLUSTRATED_WEB_CONTENT_TITLE = "ECMS_IlluStratedWebContent_01";
			By wTitle = By.linkText(ILLUSTRATED_WEB_CONTENT_TITLE);

			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			ecms.goToNode("acme/web contents");

			info("Add new Illustrated Web Content");
			actBar.goToAddNewContent();
			cTemplate.createNewIllustratedWebContent(ILLUSTRATED_WEB_CONTENT_TITLE, ILLUSTRATED_WEB_CONTENT_TITLE, "", "", "", "", "");
			waitForAndGetElement(wTitle);

			info("Check the status of the new Illustrated webcontent: Draft");
			actBar.openManagePublicationForm();
			waitForAndGetElement(actBar.ELEMENT_FIRST_REVISION_DATE);
			button.close();
			ecms.goToNode("acme/web contents");
			WebElement element = waitForAndGetElement(sitesExp.ELEMENT_STATUS_DOCUMENT.replace("${title}", ILLUSTRATED_WEB_CONTENT_TITLE).replace("status","Draft"));
			String status = element.getText();
			assert status.contains("Draft") : "Check the status...Failed";
			info("Status is [Draft]...");

			driver.navigate().refresh();
			info("Restore data");
			cMenu.deleteDocument(wTitle);
		}
	
		/**
		 * Qmetry ID: 119411
		 *Add Illustrated Web Content in Web content folder with blank required fields
		 * 
		 */
		@Test
		public void test02_AddIllustratedWebContentInWebContentFolderWithBlankRequiredFields(){
			String ILLUSTRATED_WEB_CONTENT_TITLE = "ECMS_IllustratedWebContent_02" + getRandomNumber();

			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			ecms.goToNode("acme/web contents");

			info("Add new Illustrated Web Content with blank name");
			actBar.goToAddNewContent();
			cTemplate.createNewIllustratedWebContent("", ILLUSTRATED_WEB_CONTENT_TITLE, "", "", "", "", "", "", false);
			/*assert isTextPresent(cTemplate.MESSAGE_NAME_REQUIRED_FIELD);
			info("assert success");
			if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 5000, 0) != null)
				click(button.ELEMENT_OK_BUTTON);
			info("Click success");*/
			magAlt.verifyAlertMessage(cTemplate.MESSAGE_NAME_REQUIRED_FIELD);
			button.close();
		}
		
		/**
		 * Qmetry ID: 119412
		 * Add Illustrated Web Content in Web content with Name in different languages
		 * 
		 */
		@Test
		public void test03_AddIllustratedWebContentInWebContentFolderWithNameInDifferentLanguages(){
			String ILLUSTRATED_WEB_CONTENT_VN_TITLE = "Người_quản_lý_chất_lượng_exo" + getRandomNumber();
			By vnTitle = By.linkText(ILLUSTRATED_WEB_CONTENT_VN_TITLE);

			String ILLUSTRATED_WEB_CONTENT_FR_TITLE = "Développer_eXo_01" + getRandomNumber();
			By frTitle = By.linkText(ILLUSTRATED_WEB_CONTENT_FR_TITLE);

			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			ecms.goToNode("acme/web contents");

			info("Add new Illustrated Web Content with diferent languages");
			actBar.goToAddNewContent();

			info("Add new Illustrated Web Content with name and Content in Vietnamese");
			cTemplate.createNewIllustratedWebContent(ILLUSTRATED_WEB_CONTENT_VN_TITLE, ILLUSTRATED_WEB_CONTENT_VN_TITLE, "", "", "", "", "", "vi");
			waitForAndGetElement(vnTitle);

			info("Add new Illustrated Web Content with name and Content in French");
			ecms.goToNode("acme/web contents");
			actBar.goToAddNewContent();
			cTemplate.createNewIllustratedWebContent(ILLUSTRATED_WEB_CONTENT_FR_TITLE, ILLUSTRATED_WEB_CONTENT_FR_TITLE, "", "", "", "", "", "fr");
			waitForAndGetElement(frTitle);

			info("Restore data");
			cMenu.deleteDocument(vnTitle);
			cMenu.deleteDocument(frTitle);
		}

}
