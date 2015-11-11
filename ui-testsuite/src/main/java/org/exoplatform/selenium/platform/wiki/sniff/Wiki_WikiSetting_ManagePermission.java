package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @date 8-July-2013
 * @author lientm
 */
public class Wiki_WikiSetting_ManagePermission extends BasicAction {

	ManageAccount magAc;
	Button but;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAc = new ManageAccount(driver);
		but = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	public void checkUserNotHaveWikiAdminPermission(String user){
		info("Check user " + user + "just have view wiki permission");
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		mouseOverAndClick(ELEMENT_BROWSE_LINK);
		waitForElementNotPresent(ELEMENT_WIKI_SETTING_LINK);
		magAc.signOut();
	}
	
	public void checkUserHaveWikiAdminPermission(String user){
		info("Check user " + user + "have admin wiki permission");
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		mouseOverAndClick(ELEMENT_BROWSE_LINK);
		waitForAndGetElement(ELEMENT_WIKI_SETTING_LINK);
		magAc.signOut();
	}
	
	public void checkUserHaveOnlyViewPagePermission(String user, String title){
		info("Check user " + user + "just have view page permission");
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		click(By.linkText(title));
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		magAc.signOut();
	}
	
	public void checkUserHaveEditPagePermission(String user, String title){
		info("Check user " + user + "have edit page permission");
		magAc.signIn(user, DATA_PASS);
		goToWiki();
		click(ELEMENT_NODE_WIKI_PAGE.replace("{$node}", title));
		waitForAndGetElement(ELEMENT_EDIT_PAGE_LINK);
		magAc.signOut();
	}
	
	/**CaseId: 70264 + 70267 + 70268 -> Add, edit, delete permission of wiki
	 * => 3 script: add, edit, delete permission of wiki for select user, select group, select membership
	 */
	
	@Test
	public void test01_AddEditDeletePermissionForWiki_ForUser(){
		String user = DATA_USER2;
		String userGroup[] = {user};
	
		info("Add permission for user mary");
		addSpacePermission(1, userGroup, 2);
		editSpacePermission(user, true, false, false, false, 2);
		magAc.signOut();
		
		checkUserNotHaveWikiAdminPermission(user);	
		
		info("Edit permission for user mary");	
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		editSpacePermission(user, true, true, true, true, 2);
		assert waitForAndGetElement(ELEMENT_ADMIN_SPACE_CHECK.replace("{$user}", user),DEFAULT_TIMEOUT, 1, 2).isSelected();
		magAc.signOut();
		
		checkUserHaveWikiAdminPermission(user);
		
		info("Delete permission");
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		deleteSpacePermission(user);
	}
	
	@Test
	public void test02_AddEditDeletePerssionForWiki_ForGroup(){
		String user = DATA_USER4;
		String userGroup[] = {"Development/Select this Group"};
		
		info("Add permission for group Development");
		addSpacePermission(2, userGroup);
		editSpacePermission("developers", true, false, false, false, 2);
		magAc.signOut();
		
		checkUserNotHaveWikiAdminPermission(user);
		
		info("Edit permission for group Development");
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		editSpacePermission("developers", true, true, true, true, 2);
		assert waitForAndGetElement(ELEMENT_ADMIN_SPACE_CHECK.replace("{$user}", "developers"),DEFAULT_TIMEOUT, 1, 2).isSelected();
		magAc.signOut();
		
		checkUserHaveWikiAdminPermission(user);
		
		info("Delete permission");
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		deleteSpacePermission("developers");
	}
	
	@Test
	public void test03_AddEditDeletePermissionForWiki_ForMembership(){
		String userGroup[] = {"Platform/Content Management", "author"};
		String path = "author:/platform/web-contributors";
		String user = DATA_USER3;
		
		info("Add permission for membership " + path);
		addSpacePermission(3, userGroup);
		editSpacePermission(path, true, false, false, false, 2);
		magAc.signOut();
		
		checkUserNotHaveWikiAdminPermission(user);
		
		info("Edit permission for membership " + path);
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		editSpacePermission(path, true, true, true, true, 2);
		assert waitForAndGetElement(ELEMENT_ADMIN_SPACE_CHECK.replace("{$user}", path),DEFAULT_TIMEOUT, 1, 2).isSelected();
		magAc.signOut();
		
		checkUserHaveWikiAdminPermission(user);
		
		info("Delete permission");
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
		deleteSpacePermission(path);
	}
	
	/**CaseId: 70269 + 70270 + 70271 -> Add, edit, delete permission of wiki
	 * => 3 script: add, edit, delete permission of wiki for select user, select group, select membership
	 */
	
	@Test
	public void test04_AddEditDeletePermissionOfPage_ForUser(){
		String title = "Wiki_sniff_permission_title_04";
		String content = "Wiki_sniff_permission_content_04";
		String user = DATA_USER2;
		String userGroup[] = {user};
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");

		addPagePermission(1, userGroup, 1);
		editPagePermission(user, true, false, false, 2);
		magAc.signOut();
		
		checkUserHaveOnlyViewPagePermission(user, title);
		
		info("Edit permission");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		editPagePermission(user, true, true, false, 2);
		magAc.signOut();
		
		checkUserHaveEditPagePermission(user, title);
		
		info("Delete permission");
		goToWikiPage(title, ManageAccount.userType.ADMIN);
		waitForMessage(content);
		deletePagePermission(user);
		deleteCurrentWikiPage();
	}
	
	@Test
	public void test05_AddEditDeletePermissionOfPage_ForGroup(){
		String title = "Wiki_sniff_permission_title_05";
		String content = "Wiki_sniff_permission_content_05";
		String user = DATA_USER4;
		String userGroup[] = {"Development/Select this Group"};
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");

		addPagePermission(2, userGroup);
		goToWikiPage(title);
		editPagePermission("developers", true, false, false, 2);
		magAc.signOut();
		
		checkUserHaveOnlyViewPagePermission(user, title);
		
		info("Edit permission");
		goToWikiPage(title, ManageAccount.userType.ADMIN);
		waitForMessage(content);
		editPagePermission("developers", true, true, false, 2);
		magAc.signOut();
		
		checkUserHaveEditPagePermission(user, title);
		
		info("Delete permission");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deletePagePermission("developers");
		deleteCurrentWikiPage();
	}
	
	@Test
	public void test06_AddEditDeletePermissionForPage_ForMembership(){
		String title = "Wiki_sniff_permission_title_06";
		String content = "Wiki_sniff_permission_content_06";
		String userGroup[] = {"Platform/Content Management", "author"};
		String path = "author:/platform/web-contributors";
		String user = DATA_USER3;
		
		addBlankWikiPage(title, content, 0);
		deletePagePermission("any");

		addPagePermission(3, userGroup);
		editPagePermission(path, true, false, false, 2);
		magAc.signOut();
		
		checkUserHaveOnlyViewPagePermission(user, title);
		
		info("Edit permission");
		goToWikiPage(title, ManageAccount.userType.ADMIN);
		waitForMessage(content);
		editPagePermission(path, true, true, false, 2);
		magAc.signOut();
		
		checkUserHaveEditPagePermission(user, title);
		
		info("Delete permission");
		goToWikiPage("Wiki Home/" + title, ManageAccount.userType.ADMIN);
		deletePagePermission(path);
		deleteCurrentWikiPage();
	}
}
