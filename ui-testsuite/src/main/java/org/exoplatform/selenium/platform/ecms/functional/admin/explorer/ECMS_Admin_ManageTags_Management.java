/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

/**
 * Created by The eXo Platform SAS
 * Author : Hoang Manh Dung
 * dunghm@exoplatform.com
 * Oct 8, 2012
 */
package org.exoplatform.selenium.platform.ecms.functional.admin.explorer;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.Permission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * PLF4: Add a new test case
 * <li>Add a tag for a document in Content Explorer</li>
 * @author vuna2
 * @date 15th, April, 2013
 */
public class ECMS_Admin_ManageTags_Management extends PlatformBase {

	//Platform
	ManageAccount magAcc;
	UserGroupManagement userGrp;
	Button button;
	NavigationToolbar nav;

	//Ecms
	EcmsBase ecms;
	ECMainFunction ecMain;
	SitesExplorer sitesExp;
	Permission adminPer;
	ActionBar actBar;
	ContentTemplate contentTemp;
	ContextMenu contextMenu;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with "+ DATA_USER1);
		nav = new NavigationToolbar(driver);
		magAcc = new ManageAccount(driver);
		userGrp = new UserGroupManagement(driver);
		button = new Button(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		sitesExp = new SitesExplorer(driver);
		adminPer = new Permission(driver);
		actBar = new ActionBar(driver);
		contentTemp = new ContentTemplate(driver);
		contextMenu = new ContextMenu(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod(){
		info("Logout ECMS");
		//logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Test Edit Tags Function when input valid data
	 * Check Tag Permission in the Content Explorer
	 */
	@Test
	public void test01_02_CheckRightGroupContentExplorer() {
		//Go to tag permission
		ecMain.goToTagPermissionManager();

		//Add tag permission
		userGrp.selectGroupAndMembership("Platform/Content Management", "*");
		click(button.ELEMENT_ADD_BUTTON);

		//Open Edit Tag form
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);
		sitesExp.goToEditTag();
		info("Reset data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		ecMain.goToTagPermissionManager();
		adminPer.removeTagPermission("platform/web-contributors", "*");
	}

	/**
	 * Add a tag for a document in content explorer
	 * Remove a tag for a document in content explorer
	 */
	@Test
	public void test03_04_AddTagForDocumentInContentExplorer(){
		info("-- Create a new File in Content Explorer --");
		nav.goToSiteExplorer();
		actBar.goToAddNewContent();
		contentTemp.createNewFile("Add tag for document", "Add tag for document", "Add tag for document");

		info("-- Adding a tag for File.. --");
		String[] tagName = {"TagName07_1", "TagName07_2", "TagName07_3"};
		sitesExp.addTagForNode(tagName);

		Utils.captureScreen("Add_Tag_For_Document_ContentExplorer");

		info("-- Reset data... --");
		click(sitesExp.ELEMENT_SIDEBAR_FILE_EXPLORER);
		contextMenu.deleteDocument(By.linkText("Add tag for document"));

		sitesExp.goToEditTag();

		sitesExp.deleteTag(tagName);
	}
	
	/**
	 * Add a tag for a document in Content Explorer with name includes special chars
	 * 
	 */
	@Test
	public void test05_06_AddTagWithSpecialsChars(){
		info("-- Create a new File in Content Explorer --");
		String tagName = "TagName07_#@*&^%";
		String tagName2 = "";
		
		nav.goToSiteExplorer();
		actBar.goToAddNewContent();
		contentTemp.createNewFile("Add tag for document", "Add tag for document", "Add tag for document");

		info("-- Adding a tag for File.. --");

		if (waitForAndGetElement(sitesExp.ELEMENT_TAG_LINK, 5000, 0) != null){
			click(sitesExp.ELEMENT_TAG_LINK);
		}else {
			click(sitesExp.ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(sitesExp.ELEMENT_TAG_LINK);
		}
		type(sitesExp.ELEMENT_TAG_NAME, tagName, false);
		click(button.ELEMENT_ADD_BUTTON);
		
		waitForAndGetElement(sitesExp.ELEMENT_WRONG_TAG_NAME_SENTENCE);
		click(sitesExp.ELEMENT_WRONG_TAG_NAME_ACCEPT);
		
		info("Test 06 : try to add a tag name empty ");
		type(sitesExp.ELEMENT_TAG_NAME, tagName2, false);
		click(button.ELEMENT_ADD_BUTTON);
		
		waitForAndGetElement(sitesExp.ELEMENT_EMPTY_TAG_NAME_SENTENCE);
		click(sitesExp.ELEMENT_EMPTY_TAG_NAME_ACCEPT);
		
		click(sitesExp.ELEMENT_SIDEBAR_FILE_EXPLORER);
		contextMenu.deleteDocument(By.linkText("Add tag for document"));
	}
}
