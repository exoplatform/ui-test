package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceLocator;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDatabase;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ECMS_TestConfig extends PlatformBase {

	ManageLogInOut manageLoginOut;
	Button button;
	UserAddManagement addUserPage;
	UserPageBase userPageBase;
	SpaceLocator spaceLocator;

	String password;
	ArrayList<String> arrayUser;
	ArrayList<String> comments;

	NavigationToolbar navTool;
	SiteExplorerHome siteExplorerHome;
	AttachmentFileDatabase attFileData;
	HomePagePlatform homepage;
	SpaceManagement spaceManage;

	SpaceHomePage spaceHome;
	SpaceSettingManagement spaceSetting;

	UserProfilePage userProfilePage;
	NotificationDatabase notificationEmailData;
	EmailNotifications emailNotifications;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception {
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath, defaultSheet, isUseFile,
				jdbcDriver, dbUrl, user, pass, sqlUser);
		manageLoginOut = new ManageLogInOut(driver);
		button = new Button(driver);
		spaceManage = new SpaceManagement(driver);
		spaceLocator = new SpaceLocator();

		addUserPage = new UserAddManagement(driver);
		userPageBase = new UserPageBase(driver);

		comments = new ArrayList<String>();
		arrayUser = new ArrayList<String>();
		navTool = new NavigationToolbar(driver);
		siteExplorerHome = new SiteExplorerHome(driver);
		attFileData = new AttachmentFileDatabase();
		attFileData.setAttachFileData(attachmentFilePath, defaultSheet,
				isUseFile, jdbcDriver, dbUrl, user, pass, sqlUser);

		homepage = new HomePagePlatform(driver);

		spaceManage = new SpaceManagement(driver);
		spaceHome = new SpaceHomePage(driver);
		spaceSetting = new SpaceSettingManagement(driver);

		userProfilePage = new UserProfilePage(driver);
		notificationEmailData = new NotificationDatabase();
		notificationEmailData.setData(notiEmailFilePath, defaultSheet,
				isUseFile, jdbcDriver, dbUrl, user, pass, sqlAttach);

		emailNotifications = new EmailNotifications(driver);

		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}

	/*
	 * Create a space with 2 users
	 */
	public void initSpaceWithUsers(String spaceName, String user1,
			String user2, String user2FullName, String password) {
		info("User A login");
		manageLoginOut.signIn(user1, password);

		info("User A creates a space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.goToCreateSpace();
		spaceManage.addNewSpaceSimple(spaceName,"",6000);

		info("User A invites UserB to the space");
		homepage.goToSpecificSpace(spaceName);
		spaceHome.goToSpaceSettingTab();
		spaceSetting.goToMemberTab();
		spaceSetting.inviteUser(user2, true, user2FullName);

		info("User B login");
		manageLoginOut.signOut();
		manageLoginOut.signIn(user2, password);
		Utils.pause(3000);

		info("User B accepted to join the space");
		homepage.goToAllSpace();
		Utils.pause(3000);
		spaceManage.acceptAInvitation(spaceName);

		manageLoginOut.signOut();
	}

	/*
	 * Share a document to a space
	 */
	public void shareDocumentToSpace(String fileName, String spaceName,
			String comment) {
		info("Share document to space");
		navTool.goToSiteExplorer();
		Utils.pause(3000);

		siteExplorerHome.uploadFile("TestData/" + fileName);
		Utils.pause(3000);
		// Share file to space
		click(siteExplorerHome.ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace(
				"${title}", fileName));
		siteExplorerHome.shareDocument();
		// Click space list
		click(siteExplorerHome.ELEMENT_SPACE_LIST);
		// Select a space
		click(siteExplorerHome.ELEMENT_SELECTED_SPACE.replace("${spaceName}",
				spaceName));

		// Comment
		if (comment != null && !comment.isEmpty()) {
			type(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_COMMENT, comment,
					false);
		}

		// Click Share button
		click(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON.replace(
				"${name}", "Share"));
	}

	public void deleteSymlink(String spaceName, String fileName) {
		homepage.goToSpecificSpace(spaceName);

		spaceManage.goToDocumentTab();
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		// Open shared folder
		doubleClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER);
		// Find the shared file
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
				.replace("${spaceName}", spaceName).replace("${fileName}",
						fileName));
		// Delete symlink
		rightClickOnElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
				.replace("${spaceName}", spaceName).replace("${fileName}",
						fileName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE);
		// confirm
		waitForAndGetElement(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION
				.replace("${action}", "Delete"));
		click(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION
				.replace("${action}", "Delete"));
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK
				.replace("${spaceName}", spaceName).replace("${fileName}",
						fileName));
	}

	public void checkShareActivityAfterDeleted(String spaceName,
			boolean verifyInSpace) {
		waitForElementNotPresent(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT
				.replace("${author}", DATA_NAME_USER1).replace("${spaceName}",
						spaceName));
		if (verifyInSpace) {
			homepage.goToSpecificSpace(spaceName);
			spaceManage.goToActivityStreamTab();
			waitForElementNotPresent(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE
					.replace("${author}", DATA_NAME_USER1));
		}
	}

}
